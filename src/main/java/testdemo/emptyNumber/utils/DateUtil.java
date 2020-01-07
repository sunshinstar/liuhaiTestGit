package testdemo.emptyNumber.utils;


import cn.stylefeng.roses.core.util.ToolUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author hjt001
 * <p>
 * 时间操作工具类
 */
public class DateUtil {

    private static Calendar calendar = Calendar.getInstance();

    /**
     * 获取YYYY格式
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 获取YYYY格式
     */
    public static String getYear(Date date) {
        return formatDate(date, "yyyy");
    }

    /**
     * 得到月份
     *
     * @param date
     * @return
     */
    public static String getMonth(Date date) {
        return formatDate(date, "MM");
    }

    /**
     * 获取YYYY-MM-DD格式
     */
    public static String getDay() {
        Date date = new Date();
        String formatDate = formatDate(date, "yyyy-MM-dd");
        date = null;
        return formatDate;
    }

    /**
     * 获取当前秒
     *
     * @return
     */
    public static int getSecond() {
        calendar.setTime(parseTime(getTime()));
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 获取YYYY-MM-DD格式
     */
    public static String getDay(Date date) {
        return formatDate(date, "yyyy-MM-dd");
    }

    /**
     * 获取YYYY-MM-DD格式
     */
    public static String getDay(Date date, String pattern) {
        return formatDate(date, pattern);
    }

    /**
     * 获取YYYYMMDD格式
     */
    public static String getDays() {
        return formatDate(new Date(), "yyyyMMdd");
    }

    /**
     * 获取YYYYMMDD格式
     */
    public static String getDays(Date date) {
        return formatDate(date, "yyyyMMdd");
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     */
    public static String getTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss.SSS格式
     */
    public static String getMsTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
    }

    /**
     * 获取YYYYMMDDHHmmss格式
     */
    public static String getAllTime() {
        return formatDate(new Date(), "yyyyMMddHHmmss");
    }

    /**
     * 获取YYYYMMDDHHmmss格式
     */
    public static String getAllTime(Date date) {
        return formatDate(date, "yyyyMMddHHmmss");
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     */
    public static String getTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String formatDate(Date date, String pattern) {
        String formatDate = null;
        if (StringUtils.isNotBlank(pattern)) {
            formatDate = DateFormatUtils.format(date, pattern);
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 日期比较，如果s>=e 返回true 否则返回false)
     *
     * @author luguosui
     */
    public static boolean compareDate(String s, String e) {
        if (parseDate(s) == null || parseDate(e) == null) {
            return false;
        }
        return parseDate(s).getTime() >= parseDate(e).getTime();
    }

    /**
     * 格式化日期
     */
    public static Date parseDate(String date) {
        return parse(date, "yyyy-MM-dd");
    }

    /**
     * 格式化日期
     */
    public static String parseDateAndGetDays(String date) {
        LocalDate ldt = LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE);
        return ldt.plusDays(-1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    /**
     * 格式化日期
     */
    public static Date parseTimeMinutes(String date) {
        return parse(date, "yyyy-MM-dd HH:mm");
    }

    /**
     * 格式化日期
     */
    public static Date parseTime(String date) {
        return parse(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 格式化日期
     */
    public static Date parse(String date, String pattern) {
        try {
            return DateUtils.parseDate(date, pattern);
        } catch (ParseException e) {
            try {
                return DateUtils.parseDate(date, "yyyy/MM/dd HH:mm:ss");
            } catch (ParseException ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }

    /**
     * 格式化日期
     */
    public static String format(Date date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 把日期转换为Timestamp
     */
    public static Timestamp format(Date date) {
        return new Timestamp(date.getTime());
    }

    /**
     * 校验日期是否合法
     */
    public static boolean isValidDate(String s) {
        return parse(s, "yyyy-MM-dd HH:mm:ss") != null;
    }

    /**
     * 校验日期是否合法
     */
    public static boolean isValidDate(String s, String pattern) {
        return parse(s, pattern) != null;
    }

    public static int getDiffYear(String startTime, String endTime) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(
                    startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
            return years;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return 0;
        }
    }

    /**
     * <li>功能描述：时间相减得到天数
     */
    public static long getDaySub(String beginDateStr, String endDateStr) {
        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;

        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);

        return day;
    }

    /**
     * 得到两个时间相隔月数
     *
     * @param beginDateStr
     * @param endDateStr
     * @return
     * @throws ParseException
     */
    public static long getMonthSub(String beginDateStr, String endDateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        try {
            bef.setTime(sdf.parse(beginDateStr));
            aft.setTime(sdf.parse(endDateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
        return month + result;
    }

    /**
     * 得到n天之后的日期
     */
    public static String getAfterDayDate(String days) {
        int daysInt = Integer.parseInt(days);

        // java.util包
        Calendar calendar = Calendar.getInstance();

        // 日期减 如果不够减会将月变动
        calendar.add(Calendar.DATE, daysInt);
        Date date = calendar.getTime();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(date);

        return dateStr;
    }

    /**
     * 得到n天之前的日期
     */
    public static String getBoferDayDate(String days) {
        int daysInt = Integer.parseInt(days);

        // java.util包
        Calendar calendar = Calendar.getInstance();

        // 日期减 如果不够减会将月变动
        calendar.add(Calendar.DATE, -daysInt);
        Date date = calendar.getTime();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(date);

        return dateStr;
    }

    /**
     * 将时间戳转换成正常时间
     *
     * @param time
     * @param format
     * @return
     */
    public static String format(long time, String format) {
        if (time == 0) {
            return null;
        }
        Date date = new Date();
        date.setTime(time);
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 验证超时工作时间
     *
     * @param beginTimeStr 开始时间
     * @param endTimeStr   结束时间
     * @return
     */
    public static boolean checkWorkTime(String beginTimeStr, String endTimeStr) {

        if (ToolUtil.isEmpty(beginTimeStr) || ToolUtil.isEmpty(endTimeStr)) {
            return true;
        }
        String configBeginTimeStr = DateUtil.getDay() + " " + beginTimeStr;
        String configEndTimeStr = DateUtil.getDay() + " " + endTimeStr;
        Date beginTime = DateUtil.parseTime(configBeginTimeStr);
        Date endTime = DateUtil.parseTime(configEndTimeStr);
        //当前时间和工作时间进行比较,返回true，表示在工作范围内
        long currentTime = System.currentTimeMillis();
        if (currentTime > beginTime.getTime() && currentTime < endTime.getTime()) {
            return true;
        }
        return false;
    }

    /**
     * 将时间戳转换成正常时间
     *
     * @param time
     * @return
     */
    public static Date format(long time) {
        if (time == 0) {
            return null;
        }
        Date date = new Date();
        date.setTime(time);
        return date;
    }

    /**
     * 得到n天之后的日期
     */
    public static String getAfterSomeDayDate(String day, String days) {
        int daysInt = Integer.parseInt(days);

        // java.util包
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parseDate(day));
        // 日期减 如果不够减会将月变动
        calendar.add(Calendar.DATE, daysInt);
        Date date = calendar.getTime();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(date);

        return dateStr;
    }

    /**
     * 得到n天之后的日期
     */
    public static String getAfterSomeMonthDate(String day, String month) {
        int daysInt = Integer.parseInt(month);
        //获得一个日历的实例
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
            //初始日期
            date = sdf.parse(day);
        } catch (Exception e) {

        }
        //设置日历时间
        c.setTime(date);
        //在日历的月份上增加6个月
        c.add(Calendar.MONTH, daysInt);
        //的到你想要得6个月后的日期
        String strDate = sdf.format(c.getTime());
        return strDate;
    }

    /**
     * 得到n天之后是周几
     */
    public static String getAfterDayWeek(String days) {
        int daysInt = Integer.parseInt(days);

        // java.util包
        Calendar calendar = Calendar.getInstance();
        // 日期减 如果不够减会将月变动
        calendar.add(Calendar.DATE, daysInt);
        Date date = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);

        return dateStr;
    }

    /**
     * 取得两个日期间隔秒数（日期1-日期2）
     *
     * @param one 日期1
     * @param two 日期2
     * @return 间隔秒数
     */
    public static long getDiffSeconds(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / 1000;
    }


    /**
     * 获取某月的最后一天
     *
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, Integer.parseInt(getYear(date)));
        //设置月份
        cal.set(Calendar.MONTH, Integer.parseInt(getMonth(date)) - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);

        return cal.getTime();
    }

    /**
     * 获取某月的第一天
     *
     * @return
     */
    public static Date getMinDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, Integer.parseInt(getYear(date)));
        //设置月份
        cal.set(Calendar.MONTH, Integer.parseInt(getMonth(date)) - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);

        return cal.getTime();
    }

    /**
     * 获取某月的最后一天
     *
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, Integer.parseInt(getYear(date)));
        //设置月份
        cal.set(Calendar.MONTH, Integer.parseInt(getMonth(date)) - 1);
        //设置天
        cal.set(Calendar.DAY_OF_MONTH, 1);

        return cal.getTime();
    }

    /**
     * 获得当月指定天数零时零分零秒
     *
     * @return
     */
    public static Date getDateByMonth(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, i);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取某天的23:59:59秒时间戳
     *
     * @param date
     * @return
     */
    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取某天的00:00:00秒时间戳
     *
     * @param date
     * @return
     */
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static int getWeeksByChooseDay() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 根据 年、月 获取对应的月份 的 天数
     *      
     */
    public static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 获得连续日期  yyyy-MM
     *
     * @param minDate
     * @param maxDate
     * @return
     */
    public static List<String> getMonthBetween(String minDate, String maxDate, List dateList) {
        try {
            ArrayList<String> result = new ArrayList<>();
            //格式化为年月
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

            Calendar min = Calendar.getInstance();
            Calendar max = Calendar.getInstance();

            min.setTime(sdf.parse(minDate));
            min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

            max.setTime(sdf.parse(maxDate));
            max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

            Calendar curr = min;
            while (curr.before(max)) {
                result.add(sdf.format(curr.getTime()));
                curr.add(Calendar.MONTH, 1);
            }
            dateList.addAll(result);
            return dateList;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 初始化日期   yyyy-MM-dd
     *
     * @param start
     * @param over
     * @return
     */
    public static List getDayBetweenStartTimeAndEndTime(String start, String over, List dateList) {
        Date begin = parseDate(start);
        Date end = parseDate(over);
        Calendar tempStart = Calendar.getInstance();
        do {
            tempStart.setTime(begin);
            //日期
            String day = DateUtil.getDay(tempStart.getTime());
            //初始化日期
            dateList.add(day);
            //日期加1
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
            begin = tempStart.getTime();
        } while (begin.getTime() <= end.getTime());
        return dateList;
    }

    /**
     * 时间戳转日期字符串
     *
     * @param timestamp
     * @param pattern
     * @return
     */
    public static String timestampToDateString(String timestamp, String pattern) {
        try {
            Long timestampLong = Long.valueOf(timestamp);
            Date date = new Date(timestampLong);
            SimpleDateFormat simpleDateFormat = null;
            if (ToolUtil.isEmpty(pattern)) {
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            } else {
                simpleDateFormat = new SimpleDateFormat(pattern);
            }
            return simpleDateFormat.format(date);

        } catch (Exception e) {
            return "";
        }
    }


    /**
     * 日期字符串转时间戳(默认yyyy-MM-dd) ,两个参数格式需一致
     *
     * @param dateString
     * @param pattern
     * @return
     */
    public static String dateStringToTimestamp(String dateString, String pattern) {
        try {
            SimpleDateFormat simpleDateFormat = null;
            if (ToolUtil.isEmpty(pattern)) {
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            } else {
                simpleDateFormat = new SimpleDateFormat(pattern);
            }
            Long tmp = simpleDateFormat.parse(dateString).getTime();
            return tmp.toString();

        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获得当天0点时间
     *
     * @return
     */
    public static Long getTimesmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 获得当天24点时间(也就是下一天的零点零时零分零秒的时间)
     *
     * @return
     */
    public static Long getTimesnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 获得本周一0点时间
     *
     * @return
     */
    public static Long getTimesWeekmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTimeInMillis();
    }

    /**
     * 获得本周日24点时间
     *
     * @return
     */
    public static Long getTimesWeeknight() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return (cal.getTime().getTime() + (7 * 24 * 60 * 60 * 1000));
    }

    /**
     * 获得本月第一天0点时间
     *
     * @return
     */
    public static Long getTimesMonthmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTimeInMillis();
    }

    /**
     * 获取当前时间前n分钟
     *
     * @param minute
     * @return
     */
    public static String getCurrentTime(int minute) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Calendar beforeTime = Calendar.getInstance();
        // 3分钟之前的时间
        beforeTime.add(Calendar.MINUTE, -minute);
        Date beforeD = beforeTime.getTime();
        String time = sdf.format(beforeD);
        return time;
    }

    /**
     * 格式化毫秒
     */
    public static Date parseTimes(String date) {
        return parse(date, "yyyy-MM-dd HH:mm:ss.SSS");
    }

    /**
     * 获得本月最后一天24点时间
     *
     * @return
     */
    public static Long getTimesMonthnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 24);
        return cal.getTimeInMillis();
    }

    /**
     * 判断当前是否是传入时间的N分钟之前
     *
     * @param time    传入时间 时间戳
     * @param minutes N 分钟之前
     * @return
     */
    public static boolean isNowBeforeMinute(Long time, int minutes) {
        if (ToolUtil.isEmpty(time)) {
            return false;
        }
        LocalDateTime gmtTimingLD = LocalDateTime.ofEpochSecond(time / 1000, 0, ZoneOffset.ofHours(8));
        LocalDateTime localDateTime = gmtTimingLD.minusMinutes(minutes);
        return LocalDateTime.now().isBefore(localDateTime);
    }

    /**
     * 判断当前是否是传入时间的N分钟之后
     *
     * @param time    传入时间 时间戳
     * @param minutes N 分钟之前
     * @return
     */
    public static boolean isNowAfterMinute(Long time, int minutes) {
        if (ToolUtil.isEmpty(time)) {
            return false;
        }
        LocalDateTime gmtTimingLD = LocalDateTime.ofEpochSecond(time / 1000, 0, ZoneOffset.ofHours(8));
        LocalDateTime localDateTime = gmtTimingLD.plusMinutes(minutes);
        return LocalDateTime.now().isAfter(localDateTime);
    }

    /**
     * LocalDateTime 转时间戳
     *
     * @param localDateTime
     * @return
     */
    public static Long localDateTimeToTimestamp(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 判断传进来的日期是不是当天的日期
     *
     * @param date
     * @return
     */
    public static boolean isNow(Date date) {
        //当前时间
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        //获取今天的日期
        String nowDay = sf.format(now);
        String day = sf.format(date);
        return day.equals(nowDay);
    }

    public static void main(String[] args) {
        System.out.println(isNow(new Date()));
    }
}
