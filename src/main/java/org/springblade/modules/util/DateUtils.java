package org.springblade.modules.util;

import cn.hutool.core.date.DateUtil;

import java.text.DateFormat;

import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.Calendar;

import java.util.Date;
import java.util.Locale;


public class DateUtils {

	/**

	 * 通过Date类获取时间

	 * @return

	 */

	public static String getDateByDate(){

		Date date = new Date();

		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-HH-dd HH:mm:ss");

		String dateStr=dateFormat.format(date);

		System.out.println(dateStr);

		return dateStr;

	}
	//java获取当前月的天数
	public static Integer getDayOfMonth(){
		Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
		int day=aCalendar.getActualMaximum(Calendar.DATE);
		return day;
	}



	/**

	 * 通过Calendar类获取时间

	 * @return

	 */

	public static String getDateByCalendar(){

		Calendar calendar = Calendar.getInstance();

		Date date=calendar.getTime();

		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-HH-dd HH:mm:ss");

		String dateStr=dateFormat.format(date);

		System.out.println(dateStr);

		return dateStr;

	}



	/**

	 * 通过字符串获取时间

	 * @param dateStr

	 * @return

	 */

	public static Date formString(String dateStr){

		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-HH-dd HH:mm:ss");

		try {

			Date date=dateFormat.parse(dateStr);

			System.out.println(date);

			return date;

		} catch (ParseException e) {

			e.printStackTrace();

		}

		return null;

	}



	/**

	 * 通过时间戳获取时间

	 * @param time

	 * @return

	 */

	public static String getByLong(long time){

		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-HH-dd HH:mm:ss");

		String date=dateFormat.format(time);

		return date;

	}



	/**

	 * 获取系统时间，时间戳

	 * @return

	 */

	public static long getCurrentTime(){

//方式一

// Date date = new Date();

// long time=date.getTime();



//方式二

		long time=System.currentTimeMillis();

		return time;

	}



	/**

	 * 获取当前年月日

	 * @return

	 */

	public static void getYearMonthDay(){

//第一种，通过Calendar类获取

		Calendar now = Calendar.getInstance();

		System.out.println("年: " + now.get(Calendar.YEAR));

		System.out.println("月: " + (now.get(Calendar.MONTH) + 1) + "");

		System.out.println("日: " + now.get(Calendar.DAY_OF_MONTH));

		System.out.println("时: " + now.get(Calendar.HOUR_OF_DAY));

		System.out.println("分: " + now.get(Calendar.MINUTE));

		System.out.println("秒: " + now.get(Calendar.SECOND));

		System.out.println("当前时间毫秒数：" + now.getTimeInMillis());

//第二种，通过Date类获取

		Date date = new Date();

		DateFormat df1 = DateFormat.getDateInstance();//日期格式，精确到日

		System.out.println(df1.format(date));

		DateFormat df2 = DateFormat.getDateTimeInstance();//可以精确到时分秒

		System.out.println(df2.format(date));

		DateFormat df3 = DateFormat.getTimeInstance();//只显示出时分秒

		System.out.println("只显示出时分秒:"+df3.format(date));

		DateFormat df4 = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL); //显示日期，周，上下午，时间(精确到秒)

		System.out.println("显示日期，周，上下午，时间(精确到秒):"+df4.format(date));

		DateFormat df5 = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG); //显示日期,上下午，时间(精确到秒)

		System.out.println("显示日期,上下午，时间(精确到秒):"+df5.format(date));

		DateFormat df6 = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT); //显示日期，上下午,时间(精确到分)

		System.out.println("显示日期，上下午,时间(精确到分):"+df6.format(date));

		DateFormat df7 = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM); //显示日期，时间(精确到分)

		System.out.println("显示日期，时间(精确到分):"+df7.format(date));



		String [] dates=new SimpleDateFormat("yyyy-MM-dd").format(date).split("-");

		String year=dates[0];

		String month=dates[1];

		String day=dates[2];

		String [] months=new SimpleDateFormat("HH:mm:ss").format(date).split(":");

		String hour=dates[0];

		String minute=dates[1];

		String seconde=dates[2];

	}



	/**

	 * 获取前一段时间/后一段时间

	 */

	public static void beforTime(){

//根据现在时间计算

		Calendar now = Calendar.getInstance();

		now.add(Calendar.YEAR, 1); //现在时间是1年后

		System.out.println(now);

		now.add(Calendar.YEAR, -1); //现在时间是1年前

		System.out.println(now);



//根据某个特定的时间 date (Date 型) 计算

		Calendar specialDate = Calendar.getInstance();

		specialDate.setTime(new Date()); //注意在此处将 specialDate 的值改为特定日期

		specialDate.add(Calendar.YEAR, 1); //特定时间的1年后

		System.out.println(specialDate);

		specialDate.add(Calendar.YEAR, -1); //特定时间的1年前

		System.out.println(specialDate);

	}



	/**

	 * 计算两个日期相差多少小时，分钟，毫秒

	 */

	public static String betweenDay(Long diff) throws ParseException {

		long nd = 1000 * 24 * 60 * 60;

		long nh = 1000 * 60 * 60;

		long nm = 1000 * 60;

		long ns = 1000;

// 计算差多少天

		long day = diff / nd;

// 计算差多少小时

		long hour = diff % nd / nh;

// 计算差多少分钟

		long min = diff % nd % nh / nm;

// 计算差多少秒//输出结果

		long sec = diff % nd % nh % nm / ns;

		String between = hour+":"+min+":"+sec;

		return between;

	}

	//获取当天时间
	public static String getTodayDate() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateNowStr = sdf.format(d);
		return dateNowStr;
	}
	//获取当天时间
	public static String getTodayDateMonth() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String dateNowStr = sdf.format(d);
		return dateNowStr;
	}
	//获取昨天时间
	public static String getYestoryDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE,-1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String yestoday = sdf.format(calendar.getTime());
		return yestoday;
	}


	public static Date getNowDate() {
		Date d = new Date();

		return d;
	}
	public static Date getNow_yyyyMM(){
		Date format = DateUtil.parse(DateUtils.getTodayDate(), "yyyy-MM");
		return format;
	}

	public static void main(String[] args) {

// getDateByDate();

// formString("2021-15-27 15:42:44");

// getYearMonthDay();



	}

}
