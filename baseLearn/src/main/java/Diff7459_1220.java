import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.util.*;

/**
 * description:
 *
 * @author zhunhuang, 2019/12/23
 */
public class Diff7459_1220 {

    public static void main(String[] args) throws IOException {
        String base = FileUtils.fileRead("/Users/zhunhuang/Desktop/1220-7459-转账-核对/base.txt");
        String[] split = base.split("\n");
        System.out.println(split.length);

        String fee = FileUtils.fileRead("/Users/zhunhuang/Desktop/1220-7459-转账-核对/fee.txt");
        String[] feeSplit = fee.split("\n");
        System.out.println(split.length);
        Set feeSet = new HashSet();
        feeSet.addAll(Arrays.asList(feeSplit));
        int diff = 0;
        for (String s : split) {
            if (!feeSet.contains(s)) {
                System.out.println(s);
                diff++;
            }
        }
        System.out.println(diff);
    }
}
