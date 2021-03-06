import com.googlecode.aviator.AviatorEvaluator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * description:
 *
 * @author zhunhuang, 2019/11/6
 */
@RunWith(JUnit4.class)
public class TestAviator {

    @Test
    public void testAdd() {
        Long result = (Long) AviatorEvaluator.execute("1+2+3");

        Assert.assertEquals(6,result.longValue());
    }

    @Test
    public void testSub(){
        Long result = (Long) AviatorEvaluator.execute("10-2-3");

        Assert.assertEquals(5,result.longValue());
    }

    @Test
    public void testMulti(){
        Double result = (Double) AviatorEvaluator.execute("10*0.1");

        Assert.assertEquals(1,result.longValue());
    }

    @Test
    public void testDivide(){
        Long result = (Long) AviatorEvaluator.execute("10/2");

        Assert.assertEquals(5,result.longValue());
    }

    @Test
    public void testNegative(){
        Long result = (Long) AviatorEvaluator.execute("1-10");

        Assert.assertEquals(-9,result.longValue());
    }

    @Test
    public void testParameter() {
        long tradeAmount = 100;
        double feeRate = 0.1d;
        Map<String,Object> env = new HashMap<>();
        env.put("tradeAmount",tradeAmount);
        env.put("feeRate",feeRate);

        Double result = (Double) AviatorEvaluator.execute("tradeAmount*feeRate",env);

        Assert.assertEquals(Double.valueOf(tradeAmount*feeRate).longValue(),result.longValue());
    }

    @Test
    public void testBigDecimal() {
        BigDecimal tradeAmount = BigDecimal.valueOf(100);
        BigDecimal feeRate = BigDecimal.valueOf(0.38);
        Map<String,Object> env = new HashMap<>();
        env.put("tradeAmount",tradeAmount);
        env.put("feeRate",feeRate);

        BigDecimal result = (BigDecimal) AviatorEvaluator.execute("tradeAmount*feeRate",env);

        Assert.assertEquals(tradeAmount.multiply(feeRate).setScale(0,BigDecimal.ROUND_HALF_UP),result.setScale(0,BigDecimal.ROUND_HALF_UP));
    }

    @Test
    public void test3BigDecimal() {
        BigDecimal tradeAmount = BigDecimal.valueOf(100);
        BigDecimal feeRate = BigDecimal.valueOf(0.38);
        Map<String,Object> env = new HashMap<>();
        env.put("tradeAmount",tradeAmount);
        env.put("tradeCount",50000);
        env.put("feeRate",feeRate);

        BigDecimal result = (BigDecimal) AviatorEvaluator.execute("tradeCount > 50000 ? tradeAmount*feeRate : 1000*tradeAmount*feeRate",env);
        System.out.println(result.longValue());

        Assert.assertEquals(tradeAmount.multiply(feeRate).setScale(0,BigDecimal.ROUND_HALF_UP),result.setScale(0,BigDecimal.ROUND_HALF_UP));
    }

    @Test
    public void test4BigDecimal() {
        BigDecimal tradeAmount = BigDecimal.valueOf(100);
        BigDecimal feeRate = BigDecimal.valueOf(0.38);
        Map<String,Object> env = new HashMap<>();
        env.put("tradeAmount",tradeAmount);
        env.put("tradeCount",50012);
        env.put("feeRate",feeRate);
        Object execute = AviatorEvaluator.execute("tradeCount > 50000? 100.0*(tradeCount-50000):0.0", env);
        System.out.println(execute.getClass());

        Double result = (Double) AviatorEvaluator.execute("tradeCount > 50000? 100.0*(tradeCount-50000):0.0",env);
        Double result2 = (Double) AviatorEvaluator.execute("tradeCount <= 50000? 0.0: 1000000.0",env);
        System.out.println(result.longValue()+result2.longValue());
        System.out.println();
    }

    @Test
    public void test5BigDecimal() {
        BigDecimal tradeAmount = BigDecimal.valueOf(100);
        BigDecimal feeRate = BigDecimal.valueOf(0.38);
        Map<String,Object> env = new HashMap<>();
        env.put("tradeAmount",tradeAmount);
        env.put("tradeCount",50000);
        env.put("feeRate",feeRate);

        BigDecimal result = (BigDecimal) AviatorEvaluator.execute("tradeAmount > 50000? : 0.01*(tradeAmount-50000)",env);
        BigDecimal result2 = (BigDecimal) AviatorEvaluator.execute("tradeAmount <= 50000? : 1000",env);
        System.out.println(result.longValue());

        Assert.assertEquals(tradeAmount.multiply(feeRate).setScale(0,BigDecimal.ROUND_HALF_UP),result.setScale(0,BigDecimal.ROUND_HALF_UP));
    }


}
