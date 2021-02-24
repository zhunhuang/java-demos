package linearRegression;

import java.util.HashMap;
import java.util.Map;

/**
 * description:
 * https://cloud.tencent.com/developer/article/1690571
 * @author zhunhuang, 2020/12/1
 */
public class Test {

    public static void main(String[] args) {
        Map<Double, Double> data = new HashMap<>(10);
        data.put(0.0,1.0);
        data.put(1.0,2.0);
        data.put(2.0,3.0);
        data.put(3.0,4.0);
        data.put(4.0,5.0);
        data.put(5.0,6.0);

        /**
         * 根据数据集,获取预测函数
         */
        LinearRegression linearRegression = new LinearRegression(data);

        // 根据X预测Y
        double y = linearRegression.getY(6);
        System.out.println(y);

        // 根据Y预测X
        double x = linearRegression.getX(7);
        System.out.println(x);

    }
}
