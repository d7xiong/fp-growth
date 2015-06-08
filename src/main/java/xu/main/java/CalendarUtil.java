
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by xu on 2015/5/22.
 */
public class CalendarUtil {

    /**
     * 计算下月 00:00:00 毫秒数
     * @return
     */
    public static Long generateNextMonthStartMillions(){
        return generateStartMillionsByMonthIndex(1);
    }

    /**
     * 计算上上月 00:00:00 毫秒数
     * @return
     */
    public static Long generateBeforeLastMonthStartMillions(){
        return generateStartMillionsByMonthIndex(-2);
    }

    /**
     * 计算上月 00:00:00 毫秒数
     * @return
     */
    public static Long generateLastMonthStartMillions(){
        return generateStartMillionsByMonthIndex(-1);
    }

    /**
     * 计算本月 00:00:00 毫秒数
     * @return
     */
    public static Long generateThisMonthStartMillions(){
        return generateStartMillionsByMonthIndex(0);
    }

    /**
     * 计算上上周 00:00:00 毫秒数
     * @return
     */
    public static Long generateBeforeLastWeekStartMillions(){
        return generateStartMillionsByWeekIndex(-2);
    }

    /**
     * 计算上周 00:00:00 毫秒数
     * @return
     */
    public static Long generateLastWeekStartMillions(){
        return generateStartMillionsByWeekIndex(-1);
    }

    /**
     * 计算下周 00:00:00 毫秒数
     * @return
     */
    public static Long generateNextWeekStartMillions(){
        return generateStartMillionsByWeekIndex(1);
    }


    /**
     * 计算本周 00:00:00 毫秒数
     * @return
     */
    public static Long generateThisWeekStartMillions(){
        return generateStartMillionsByWeekIndex(0);
    }

    /**
     * 计算今日 00:00:00 毫秒数
     * @return
     */
    public static Long generateTodayStartMillions(){
        return generateStartMillionsByDayIndex(0);
    }

    /**
     * 计算明日 00:00:00 毫秒数
     * @return
     */
    public static Long generateTomorrowStartMillions(){
        return generateStartMillionsByDayIndex(1);
    }

    /**
     * 计算昨日 00:00:00 毫秒数
     * @return
     */
    public static Long generateYesterdayStartMillions(){
        return generateStartMillionsByDayIndex(-1);
    }

    /**
     * 计算前天 00:00:00 毫秒数
     * @return
     */
    public static Long generateBeforeYesterdayStartMillions(){
        return generateStartMillionsByDayIndex(-2);
    }

    /**
     * 计算0点时刻的毫秒数
     * @param dayIndex 0 for today, 1 for tomorrow, -1 for yesterday, -2 for before yesterday ...
     * @return
     */
    public static Long generateStartMillionsByDayIndex(int dayIndex){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, dayIndex);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 计算0点时刻毫秒数
     * @param weekIndex 0 for this week, 1 for next week, -1 for last week ...
     * @return
     */
    public static Long generateStartMillionsByWeekIndex(int weekIndex){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_MONTH, weekIndex);
        calendar.set(Calendar.DAY_OF_WEEK, 2);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 计算0点时刻的毫秒数
     * @param monthIndex 0 for this month, 1 for next month, -1 for last month ...
     * @return
     */
    public static Long generateStartMillionsByMonthIndex(int monthIndex){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, monthIndex);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static void testGenerateStartMillions(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println("==========today start==========");
        Long todayStartMillions = generateTodayStartMillions();
        System.out.println(sdf.format(todayStartMillions));
        System.out.println(todayStartMillions);

        System.out.println("==========tomorrow start==========");
        Long tomorrowStart = generateTomorrowStartMillions();
        System.out.println(sdf.format(tomorrowStart));
        System.out.println(tomorrowStart);

        System.out.println("==========yesterday start==========");
        Long yesterdayStart = generateYesterdayStartMillions();
        System.out.println(sdf.format(yesterdayStart));
        System.out.println(yesterdayStart);

        System.out.println("==========before yesterday start=======");
        Long beforeYesterdayStart = generateBeforeYesterdayStartMillions();
        System.out.println(sdf.format(beforeYesterdayStart));
        System.out.println(beforeYesterdayStart);

        System.out.println("==========this week start==========");
        Long thisWeekStart = generateThisWeekStartMillions();
        System.out.println(sdf.format(thisWeekStart));
        System.out.println(thisWeekStart);

        System.out.println("==========next week start==========");
        Long nextWeekStart = generateNextWeekStartMillions();
        System.out.println(sdf.format(nextWeekStart));
        System.out.println(nextWeekStart);

        System.out.println("==========last week start==========");
        Long lastWeekStart = generateLastWeekStartMillions();
        System.out.println(sdf.format(lastWeekStart));
        System.out.println(lastWeekStart);

        System.out.println("==========before last week start==========");
        Long beforeLastWeekStart = generateBeforeLastWeekStartMillions();
        System.out.println(sdf.format(beforeLastWeekStart));
        System.out.println(beforeLastWeekStart);

        System.out.println("==========this month start==========");
        Long thisMonthStart = generateThisMonthStartMillions();
        System.out.println(sdf.format(thisMonthStart));
        System.out.println(thisMonthStart);

        System.out.println("==========next month start==========");
        Long nextMonthStart = generateNextMonthStartMillions();
        System.out.println(sdf.format(nextMonthStart));
        System.out.println(nextMonthStart);

        System.out.println("==========last month start==========");
        Long lastMonthStart = generateLastMonthStartMillions();
        System.out.println(sdf.format(lastMonthStart));
        System.out.println(lastMonthStart);

        System.out.println("==========before last month start==========");
        Long beforeLastMonthStart = generateBeforeLastMonthStartMillions();
        System.out.println(sdf.format(beforeLastMonthStart));
        System.out.println(beforeLastMonthStart);

    }

    public static void main(String args[]) {

        testGenerateStartMillions();

    }
}
