package test;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author: zhun.huang
 * @create: 2018-03-17 下午12:40
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(FileUtils.getTempDirectory().getName());
        System.out.println(FileUtils.getTempDirectoryPath());
        System.out.println(FileUtils.getUserDirectory());
        System.out.println(Main.class.getResource("").getPath());
        System.out.println(Main.class.getResource("/").getPath());
        String classPath = "classpath:myResource.txt";
        System.out.println("aa");
        System.out.println(Main.class.getClassLoader().getResource("").getPath());
        System.out.println(Main.class.getClassLoader().getResource("./myResource.txt").getPath());
        System.out.println(Main.class.getResource("/myResource.txt").getPath());
        System.out.println(Main.class.getResourceAsStream("/myResource.txt"));
        List<String> strings = FileUtils.readLines(new File(Main.class.getClassLoader().getResource(classPath.replace("classpath:", "")).getPath()));
        System.out.println(strings);
    }

    public void testCSV(){

    }
}
