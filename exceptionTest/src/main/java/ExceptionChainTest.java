/**
 * description:
 *
 * @author zhunhuang, 2021/1/20
 */
public class ExceptionChainTest {

    public static void main(String[] args) {
        //        用户下单; 1:查询用户信息; 2:生成订单; 3: 订单通知
        userTrade();
    }

    public static void userTrade(){

        try {

            try {
                System.out.println("1:查询用户信息");
                throw new IllegalArgumentException("接口超时");
            } catch (Exception e) {
                throw new RuntimeException("查询用户信息失败",e);
            }
//
//            try {
//                System.out.println("1:查询用户信息");
//                throw new IllegalArgumentException("接口超时");
//            } catch (Exception e) {
//                throw new RuntimeException("生成订单");
//            }
//
//            try {
//                System.out.println("1:查询用户信息");
//                throw new IllegalArgumentException("接口超时");
//            } catch (Exception e) {
//                throw new RuntimeException("订单通知");
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
