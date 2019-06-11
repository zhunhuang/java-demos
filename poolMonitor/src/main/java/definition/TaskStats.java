package definition;

/**
 * description:
 * 定义池资源中具体执行的每一个子任务应该统计的指标
 * 比如：
 *      汇总数据：当前执行的方法总数量，方法平均执行时长
 *      task数据：任务执行时长，执行的任务方法名/任务名
 *      dubbo接口：超时率，超时配置，P99时长等
 *      sql任务： dao层方法名，方法入参，执行时长，该方法的sql并行执行数量
 *      http调用：请求url，响应statusCode，响应时间，
 * @author zhun.huang 2019-03-20
 */
public class TaskStats {
}
