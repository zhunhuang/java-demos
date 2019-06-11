import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhun.huang
 * @create: 2017-11-26 下午10:36
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class Test {
    private String a = new String("123");
    private Integer b = 1;

    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        while (true) {
            list.add(new Test());
        }
    }

}
