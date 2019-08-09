import com.sun.org.apache.bcel.internal.util.ClassPath;

import java.util.Date;

/**
 * description:
 * create: 2019-07-03
 *
 * @author zhun.huang
 */
public class Test {

	public static void main(String[] args) {
		System.out.println(Test.class.getResource("").getPath());
		System.out.println(ClassPath.SYSTEM_CLASS_PATH);
		System.out.println(new Date(0));
	}
}
