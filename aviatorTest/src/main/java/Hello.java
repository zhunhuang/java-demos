import com.googlecode.aviator.AviatorEvaluator;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * description:
 *
 * @author zhunhuang, 2019/11/6
 */
@RunWith(JUnit4.class)
public class Hello {
    public static void main(String[] args) {
        Long result = (Long) AviatorEvaluator.execute("1+2+3");
        System.out.println(result);
    }

}
