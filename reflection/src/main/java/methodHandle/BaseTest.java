package methodHandle;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * description:
 * create: 2019-08-09
 * -XX:+UnlockDiagnosticVMOptions -XX:+ShowHiddenFrames 添加该jvm参数, 以实现打印被JVM隐藏的异常栈
 * @author zhun.huang
 */
public class BaseTest {

	static class Horse {
		public static void race(String a){
			System.out.println("马跑步:" + a);
			// 主动打印栈
			new Exception().printStackTrace();
		}
	}

	static class Deer {
		public void race(String a){
			System.out.println("鹿跑步:" + a);
			// 主动打印栈
			new Exception().printStackTrace();
		}
	}

	public static void main(String[] args) throws Throwable {
		final MethodHandles.Lookup lookup = MethodHandles.lookup();
		final MethodType methodType = MethodType.methodType(void.class, String.class);
		final MethodHandle horseRace = lookup.findStatic(Horse.class, "race", methodType);
		final MethodHandle deerRace = lookup.findVirtual(Deer.class, "race", methodType);
		horseRace.invokeExact("我是大马呀");
		deerRace.invokeExact(new Deer(),"我是小鹿呀");
	}
}
