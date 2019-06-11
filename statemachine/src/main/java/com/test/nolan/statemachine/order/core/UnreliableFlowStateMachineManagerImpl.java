package com.test.nolan.statemachine.order.core;

import com.test.statemachine.api.ExecutorInfo;
import com.test.statemachine.api.FlowTrait;
import com.test.statemachine.api.recorder.ActionExecutedRecord;
import com.test.statemachine.core.AbstractFlowStateMachineManager;
import com.test.statemachine.core.FlowStateMachineConfig;
import com.test.statemachine.core.defination.FlowPath;
import com.test.statemachine.core.defination.FlowPathFinder;
import com.test.statemachine.core.service.FlowActionExecuteService;
import com.test.statemachine.core.service.UnreliableFlowActionExecuteServiceImpl;
import com.test.statemachine.spi.context.BaseContext;
import com.test.nolan.statemachine.order.bean.OrderFlowTrait;
import com.test.nolan.statemachine.order.bean.OrderFlowContext;
import com.test.nolan.statemachine.order.condition.InitOrderCondition;
import com.test.nolan.statemachine.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zhun.huang
 * @create: 2017-12-07 下午10:03
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class UnreliableFlowStateMachineManagerImpl  extends AbstractFlowStateMachineManager<OrderFlowContext, OrderFlowTrait>{

    private static final Logger logger  = LoggerFactory.getLogger(UnreliableFlowStateMachineManagerImpl.class);

    private FlowPathFinder flowPathFinder;

    @Resource
    private InitOrderCondition initOrderCondition;
    @Resource
    private OrderService orderService;

    public UnreliableFlowStateMachineManagerImpl(FlowStateMachineConfig<OrderFlowContext, OrderFlowTrait> config) {
        super(config);
    }

    @Override
    protected FlowActionExecuteService getExecuteService(FlowPathFinder flowPathFinder, FlowStateMachineConfig<OrderFlowContext, OrderFlowTrait> flowStateMachineConfig) {
        this.flowPathFinder = flowPathFinder;
        return new UnreliableFlowActionExecuteServiceImpl(flowPathFinder, config);
    }

    @Override
    public FlowTrait changeState(FlowTrait flowTrait, String toState, int currentVersion, ExecutorInfo executorInfo) throws Exception {
        return changeState(flowTrait, toState, currentVersion, executorInfo, null);
    }

    @Override
    public FlowTrait changeState(FlowTrait flowTrait, String toState, int currentVersion, ExecutorInfo executorInfo, String changeReason) throws Exception {
        return changeState(flowTrait.flowId(), toState, currentVersion, executorInfo, changeReason);
    }

    @Override
    public FlowTrait changeState(String flowId, String toState, int currentVersion, ExecutorInfo executorInfo) throws Exception {

        return changeState(flowId, toState, currentVersion, executorInfo, null);
    }

    @Override
    public FlowTrait changeState(String flowId, String toState, int currentVersion, ExecutorInfo executorInfo, String changeReason) throws Exception {
        OrderFlowContext context = (OrderFlowContext)config.getFlowContextFactory().createContext(flowId, executorInfo, config.getMachineKey());
        invokeTransition(context, toState, changeReason);
        return context.getFlow();
    }

    @Override
    public FlowTrait generateFlow(FlowTrait flowTrait, ExecutorInfo executorInfo) throws Exception {
        OrderFlowContext context = (OrderFlowContext) config.getFlowContextFactory().createContext(flowTrait, executorInfo, config.getMachineKey());
        logger.info("校验 {} 是否满足初始化条件", flowTrait.flowId());
        initOrderCondition.check(context);
        logger.info("{} 校验条件[{}]成功", flowTrait.flowId(),initOrderCondition.getLabel());
        List<ActionExecutedRecord> actionExecutedRecords = executeService.generateAndSaveActionRecord(context);
        executeService.executeActions(context.getFlow(), context, actionExecutedRecords);
        return context.getFlow();
    }

    @Override
    protected <T extends BaseContext> void invokeTransition(T context, String targetState, String changeReason) throws Exception {
        logger.debug("{} 执行状态跃迁", context.getFlowId());
        FlowPath flowPath = executeService.changeStateAndSaveActionRecord(context, targetState, changeReason);
        // 执行所有plugin的before
        executeService.executeBeforePlugin(context.getFlow(), context, flowPath.getPlugins());
        try {
            executeService.checkMeetAllConditions(context.getFlow(), context, flowPath.getConditions());
            // 执行此次跃迁的所有action
            executeService.executeActions(context.getFlow(), context, flowPath.getExecutedRecords());
            // 执行完毕，变更目标状态
            context.getFlow().changeState(targetState);
            // 更新订单
            orderService.updateFlow((OrderFlowTrait) context.getFlow(), orderService.buildOperationLog((OrderFlowContext) context));
        } catch (Throwable t) {
            throw new Exception(t);
        } finally {
            // 执行所有plugin的after
            executeService.executeAfterPlugin(context.getFlow(), context, flowPath.getPlugins());
        }
    }
}
