package base;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.*;

public class DateUtilTest {

    @Test
    public void getDayOfWeek() throws ParseException {

        Date date = DateUtils.parseDate("2020-04-13", "yyyy-MM-dd");
        assertEquals(0, DateUtil.getDayOfWeek(date));

        date = DateUtils.parseDate("2020-04-19", "yyyy-MM-dd");
        assertEquals(6, DateUtil.getDayOfWeek(date));
    }

    @Test
    public void getDayOfMonth() throws ParseException {
        Date date = DateUtils.parseDate("2020-04-19", "yyyy-MM-dd");
        assertEquals(19, DateUtil.getDayOfMonth(date));
    }
}