package linearRegression;

import java.math.BigDecimal;
import java.util.Map;

/**
 * description:
 * 使用最小二乘法实现线性回归预测;
 * 预测函数为: y = ax + b;
 * @author zhunhuang, 2020/12/1
 */
public class LinearRegression {

    /**
     * 截距,b
     */
    private double intercept = 0.0;
    /**
     * 斜率,a
     */
    private double slope = 0.0;

    public LinearRegression(Map<Double, Double> initData) {
        init(initData);
    }

    private void init(Map<Double, Double> initData){
        /*
         * x、y平均值
         */
        double averageX, averageY;
        /*
         * 求斜率的上下两个分式的值
         */
        double slopeUp, slopeDown;

        if (initData.size() > 0) {
            //数据个数
            int number = 0;
            //x值、y值总和
            double sumX = 0;
            double sumY = 0;
            slopeUp = 0;
            slopeDown = 0;
            for (Double x : initData.keySet()) {
                if (x == null || initData.get(x) == null) {
                    continue;
                }
                number++;
                sumX = add(sumX, x);
                sumY = add(sumY, initData.get(x));
            }
            //求x，y平均值
            averageX = div(sumX, (double) number);
            averageY = div(sumY, (double) number);

            for (Double x : initData.keySet()) {
                if (x == null || initData.get(x) == null) {
                    continue;
                }

                slopeUp = add(slopeUp, mul(sub(x, averageX), sub(initData.get(x), averageY)));
                slopeDown = add(slopeDown, mul(sub(x, averageX), sub(x, averageX)));

            }

            // 计算斜率和截距
            if (slopeUp != 0 && slopeDown != 0) {
                slope = slopeUp / slopeDown;
            }
            intercept = averageY - averageX * slope;
        }
    }

    /**
     * 根据x值预测y值
     *
     * @param x x值
     * @return y值
     */
    public double getY(double x) {
        return add(intercept,mul(slope,x));
    }

    /**
     * 根据y值预测x值
     *
     * @param y y值
     * @return x值
     */
    public double getX(double y) {
        return div(sub(y,intercept),slope);
    }


    /**
     * ================================================以下代码是double高精度运算================================================
     */
    private static final int DEF_DIV_SCALE = 10;

    /**
     * * 两个Double数相加 *
     *
     * @param v1 *
     * @param v2 *
     * @return Double
     */
    private static Double add(Double v1, Double v2) {
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return b1.add(b2).doubleValue();
    }

    /**
     * * 两个Double数相减 *
     *
     * @param v1 *
     * @param v2 *
     * @return Double
     */
    private static Double sub(Double v1, Double v2) {
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return b1.subtract(b2).doubleValue();
    }

    /**
     * * 两个Double数相乘 *
     *
     * @param v1 *
     * @param v2 *
     * @return Double
     */
    private static Double mul(Double v1, Double v2) {
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return b1.multiply(b2).doubleValue();
    }

    /**
     * * 两个Double数相除 *
     *
     * @param v1 *
     * @param v2 *
     * @return Double
     */
    private static Double div(Double v1, Double v2) {
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * ================================================以上代码是double高精度运算================================================
     */

}
