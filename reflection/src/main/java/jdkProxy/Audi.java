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
        System.err.println("Audi is driving... " + "driverName: " + driverName + ", carName" + carName);
    }

}
