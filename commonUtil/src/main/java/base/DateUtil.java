package base;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * description:
 * https://blog.csdn.net/iplayvs2008/article/details/41910835
 *
 * @author zhunhuang, 2019/11/19
 */
public class DateUtil {

    public String format(Date date, String format) {
        return DateFormatUtils.format(date, format);
    }

    public static void main(String[] args) {
        System.out.println(DateFormatUtils.format(getCurrentStartDate(), "yyyyMMddHHmmssSSS"));
        System.out.println(DateFormatUtils.format(getCurrentMonthStartDate(), "yyyyMMddHHmmssSSS"));
        System.out.println(DateFormatUtils.format(getCurrentMonthEndDate(), "yyyyMMddHHmmssSSS"));
        System.out.println(DateFormatUtils.format(getNextMonthStartDate(), "yyyyMMddHHmmssSSS"));
        System.out.println();
    }

//    public String generateCycleNo(FeeCycle feeCycle, Date judgeTime) {
//        switch (feeCycle) {
//            case REAL_TIME:
//                return org.apache.commons.lang3.time.DateFormatUtils.format(judgeTime,"yyyyMMddHHmmss");
//            case CYCLE_DAY:
//                return org.apache.commons.lang3.time.DateFormatUtils.format(judgeTime,"yyyyMMdd");
//            case CYCLE_MONTH:
//                return org.apache.commons.lang3.time.DateFormatUtils.format(judgeTime,"yyyyMM");
//            default:
//                throw new RuntimeException("不支持该类型:"+ feeCycle.name());
//        }
//    }

    public static Date getCurrentStartDate() {
        try {
            return DateUtils.addDays(DateUtils.parseDate(DateFormatUtils.format(new Date(), "yyyyMMdd"), "yyyyMMdd"), 1);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date getThisDayEndTime(Date judgeTime) {
        try {
            return DateUtils.parseDate(DateFormatUtils.format(judgeTime, "yyyyMMdd"), "yyyyMMdd");
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date getCurrentMonthStartDate() {
        try {
            return DateUtils.parseDate(DateFormatUtils.format(new Date(), "yyyyMM"), "yyyyMM");
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date getCurrentMonthEndDate() {
        try {
            return DateUtils.addDays(DateUtils.addMonths(DateUtils.parseDate(DateFormatUtils.format(new Date(), "yyyyMM"), "yyyyMM"),1),-1);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date getNextMonthStartDate() {
        try {
            return DateUtils.addMonths(DateUtils.parseDate(DateFormatUtils.format(new Date(), "yyyyMM"), "yyyyMM"), 1);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取本周的周一开始时间
     *
     * @param date 判断时间
     * @return 判断时间所在周的开始时间
     */
    public static Date getDomesticThisWeekStartTime(Date date) {
        //获得入参的日期
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);

        // 获得入参日期是一周的第几天
        int dayOfWeekOfToDay = cd.get(Calendar.DAY_OF_WEEK);
        // 获取本周的第几天, 周一是第0天,周日是第6天
        int domesticDayOfWeek = dayOfWeekOfToDay == Calendar.SUNDAY ? 6 : dayOfWeekOfToDay - 2;

        cd.add(Calendar.DATE, -domesticDayOfWeek);
        return getThisDayEndTime(cd.getTime());
    }

    public static long getDayOfWeek(Date date) {
        //获得入参的日期
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);

        // 获得入参日期是一周的第几天
        int dayOfWeekOfToDay = cd.get(Calendar.DAY_OF_WEEK);
        // 获取本周的第几天, 周一是第0天,周日是第6天
        return dayOfWeekOfToDay == Calendar.SUNDAY ? 6 : dayOfWeekOfToDay - 2;
    }

    public static long getDayOfMonth(Date date) {
        //获得入参的日期
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);

        return cd.get(Calendar.DAY_OF_MONTH);
    }
}
