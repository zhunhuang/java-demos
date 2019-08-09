package test;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

/**
 * @author: zhun.huang
 * @create: 2018-03-17 下午12:40
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class Main {

    @Test
    public void getUserDirectory() {
        System.out.println(FileUtils.getTempDirectory().getName());
        System.out.println(FileUtils.getTempDirectoryPath());
        System.out.println(FileUtils.getUserDirectory());
        System.out.println(this.getClass().getResource("").getPath());
        System.out.println(this.getClass().getResource("/").getPath());
        System.out.println(this.getClass().getClassLoader().getResource("").getPath());
        System.out.println(this.getClass().getResource("/myResource.txt").getPath());
        System.out.println(this.getClass().getResource("/myResource.txt").getPath());
        System.out.println(this.getClass().getResourceAsStream("/myResource.txt"));
    }

    public void testCSV(){

    }
}
