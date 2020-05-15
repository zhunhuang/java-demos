package streams;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description:
 *
 * @author zhunhuang, 2020/4/1
 */
public class EmptyTest {

    public static void main(String[] args) {
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3);

        List<Integer> collect = integers.stream().filter(s -> s > 5).collect(Collectors.toList());
        System.out.println(collect);


    }
}
