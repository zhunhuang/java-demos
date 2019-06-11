import com.test.tdd.Calculator;
import org.junit.Test;

/**
 * description:
 *
 * @author zhun.huang 2019-01-24
 */
public class CalculatorTest {

	@Test(expected = NumberFormatException.class)
	public void emptyString() {
		Calculator.add("", "");
	}

	@Test(expected = NumberFormatException.class)
	public void nullTest() {
		Calculator.add(null, "");
	}
}
