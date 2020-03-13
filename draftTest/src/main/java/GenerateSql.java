import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * description:
 *
 * @author zhunhuang, 2020/2/21
 */
public class GenerateSql {

    public static void main(String[] args) throws ParseException {
        String parttern = "yyyyMMdd";
        String base = "";

        Date start = DateUtils.parseDate("20200601",parttern);
        Date end = DateUtils.parseDate("20210101",parttern);
        Date iter = start;
        while (iter.before(end)) {
            System.out.println(base.replace("${date}", DateFormatUtils.format(iter,parttern)));
            iter = DateUtils.addDays(iter,1);
        }
    }
}
