import java.util.Map;
import java.util.Properties;

/**
 * description:
 * create: 2019-04-28
 * https://www.cnblogs.com/jianlun/p/4773711.html
 * @author zhun.huang
 */
public class Test {

	public static void main(String[] args) {
		printEnvInfo();
		printJAVA_HOME();
		printProperties();
	}

	private static void printEnvInfo() {
		final Map<String, String> sysEnv = System.getenv();
		for (Map.Entry<String, String> entry : sysEnv.entrySet()) {
			System.out.println("环境变量[" + entry.getKey() + "]: " + entry.getValue());
		}
		System.out.println();
	}

	private static void printJAVA_HOME(){
		System.out.println("输出JAVA_HOME变量：");
		System.out.println("JAVA_HOME: " + System.getenv("JAVA_HOME"));
	}

	private static void printProperties(){
		final Properties properties = System.getProperties();
		System.out.println(properties);
	}
}
