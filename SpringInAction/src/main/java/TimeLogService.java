/**
 * Created by 黄准 on 17-9-17.
 * Email:nolan.zhun@gmail.com
 */
public class TimeLogService {

    private Long beginTime;
    private Long endTime;

    public void beforeLog(){
        beginTime = System.currentTimeMillis();
        System.out.println("当前时间为:"+beginTime);
        System.out.println("开始执行方法");
    }

    public void afterLog(){
        endTime = System.currentTimeMillis();
        System.out.println("方法执行结束,执行时长"+ (endTime-beginTime));
    }
}
