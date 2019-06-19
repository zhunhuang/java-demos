package file;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * description:
 * create: 2019-06-18
 *
 * @author zhun.huang
 */
public class FileUtil {


	public static void main(String[] args) throws IOException {
		final String resource = Thread.currentThread().getContextClassLoader().getResource("./").getPath();
		System.out.println(resource);
		File file = new File(resource+"test.txt");
		FileUtils.write(file,"hello,你好吗??丷还, 当前时间是:" +System.currentTimeMillis(),"utf-8");
		final String s = FileUtils.readFileToString(file, "utf-8");
		System.out.println(s);
	}

}
