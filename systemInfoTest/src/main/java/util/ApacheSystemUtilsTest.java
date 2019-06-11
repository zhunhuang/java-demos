package util;

import org.apache.commons.lang3.SystemUtils;

import java.io.File;

/**
 * description: 通过工具类获取相关系统参数
 * create: 2019-04-28
 *
 * @author zhun.huang
 */
public class ApacheSystemUtilsTest {

	public static void main(String[] args) {
		final File javaHome = SystemUtils.getJavaHome();
		System.out.println(javaHome.getAbsolutePath());

		System.out.println(SystemUtils.USER_TIMEZONE);

		System.out.println("当前用户家目录：　" + SystemUtils.getUserHome());
		System.out.println("当前工作目录：" + SystemUtils.getUserDir());
		System.out.println("当前系统换行符：" + SystemUtils.LINE_SEPARATOR);

		System.out.println(SystemUtils.JAVA_RUNTIME_VERSION);
		System.out.println(SystemUtils.JAVA_CLASS_VERSION);
		System.out.println(SystemUtils.JAVA_VERSION);
		System.out.println(SystemUtils.JAVA_VM_VERSION);
	}
}
