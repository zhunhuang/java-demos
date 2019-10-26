package jdkProxy;

/**
 * @author: zhun.huang
 * @create: 2018-05-25 上午10:29
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class Audi implements Car {

    @Override
    public void drive(String driverName, String carName) {
        System.out.println("Audi is driving... " + "driverName: " + driverName + ", carName" + carName);
    }

    @Override
    @MyLogAnnotation
    public void drive2(String driverName, String carName) {
        System.out.println("drive2 is driving... " + "driverName: " + driverName + ", carName" + carName);
    }
}
