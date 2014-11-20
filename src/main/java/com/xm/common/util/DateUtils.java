package com.xm.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class DateUtils {
	public static String dateStrFormat(Date date, String formatStr) {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		String str = format.format(date);
		return str;
	}
	
	public static Date dateParse(Date date, String formatStr) {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		String str = format.format(date);
		try {
			return format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String dateStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("MM月dd日 hh:mm");
		String str = format.format(date);
		return str;
	}
	
	public static String dateStr(Date date,String f) {
		SimpleDateFormat format = new SimpleDateFormat(f);
		String str = format.format(date);
		return str;
	}

	public static String dateStr2(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String str = format.format(date);
		return str;
	}
	public static String dateStr5(Date date) {
		// 招商贷需求变更 短信时间由12小时改为24小时制 高才 2012/08/12 update start
		// SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日hh时mm分ss秒");
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
		// 高才 2012/08/12 update end
		String str = format.format(date);
		return str;
	}
	public static String dateStr3(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = format.format(date);
		return str;
	}

	public static String dateStr4(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = format.format(date);
		return str;

	}

	public static String dateStr6(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		String str = format.format(date);
		return str;
	}
	
	public static String dateStr7(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String str = format.format(date);
		return str;
	}
	
	public static String dateStr8(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("MM-dd");
		String str = format.format(date);
		return str;
	}
	
	public static String dateStr9(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String str = format.format(date);
		return str;
	}

	/**
	 * 将秒转换成时间
	 * @param times
	 * @return
	 */
	public static Date getDate(String times) {
		long time = Long.parseLong(times);
		return new Date(time*1000);
	}

	public static String dateStr(String times) {
		return dateStr(getDate(times));
	}
	public static String dateStr2(String times) {
		return dateStr2(getDate(times));
	}
	public static String dateStr3(String times) {
		return dateStr3(getDate(times));
	}
	public static String dateStr4(String times) {
		return dateStr4(getDate(times));
	}
	public static String dateStr5(String times) {
		return dateStr5(getDate(times));
	}
	public static String dateStr7(String times) {
		return dateStr7(getDate(times));
	}
	public static long getTime(Date date) {
		return date.getTime() / 1000;
	}

	public static int getDay(Date d){
		Calendar cal=Calendar.getInstance();
		cal.setTime(d);
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * s - 表示 "yyyy-mm-dd" 形式的日期的 String 对象 
	 * @param f
	 * @return
	 */
	public static Date valueOf(String s){
		final int YEAR_LENGTH = 4;
        final int MONTH_LENGTH = 2;
        final int DAY_LENGTH = 2;
        final int MAX_MONTH = 12;
        final int MAX_DAY = 31;
        int firstDash;
        int secondDash;
        int threeDash=0;
        int fourDash=0;
        Date d = null;

        if (s == null) {
            throw new java.lang.IllegalArgumentException();
        }

        firstDash = s.indexOf('-');
        secondDash = s.indexOf('-', firstDash + 1);
        if(s.contains(":")){
	        threeDash=s.indexOf(':');
	        fourDash=s.indexOf(':', threeDash+1);
        }
        if ((firstDash > 0) && (secondDash > 0) && (secondDash < s.length()-1)) {
            String yyyy = s.substring(0, firstDash);
            String mm = s.substring(firstDash + 1, secondDash);
            String dd="";
            String hh="";
            String MM="";
            String ss="";
            if(s.contains(":")){
	             dd = s.substring(secondDash + 1,threeDash-3);
	             hh=s.substring(threeDash-2, threeDash);
	             MM=s.substring(threeDash+1, fourDash);
	             ss=s.substring(fourDash+1);
            }else{
            	dd = s.substring(secondDash + 1);
            }
            if (yyyy.length() == YEAR_LENGTH && mm.length() == MONTH_LENGTH &&
                dd.length() == DAY_LENGTH) {
                int year = Integer.parseInt(yyyy);
                int month = Integer.parseInt(mm);
                int day = Integer.parseInt(dd);
                int hour=0;
                int minute=0 ;
                int second=0;
                if(s.contains(":")){
                 hour = Integer.parseInt(hh);
                 minute = Integer.parseInt(MM);
                 second = Integer.parseInt(ss);
                }
                if (month >= 1 && month <= MAX_MONTH) {
                    int maxDays = MAX_DAY;
                    switch (month) {
                        // February determine if a leap year or not
                        case 2:
                            if((year % 4 == 0 && !(year % 100 == 0)) || (year % 400 == 0)) {
                                maxDays = MAX_DAY-2; // leap year so 29 days in February
                            } else {
                                maxDays = MAX_DAY-3; //  not a leap year so 28 days in February 
                            }
                            break;
                        // April, June, Sept, Nov 30 day months
                        case 4:
                        case 6:
                        case 9:
                        case 11:
                            maxDays = MAX_DAY-1;
                            break;
                    }
                    if (day >= 1 && day <= maxDays) {
                        Calendar cal=Calendar.getInstance();
                        cal.set(year, month - 1, day,hour,minute,second);
                        cal.set(Calendar.MILLISECOND, 0);
                        d=cal.getTime();
                    }
                }
            }
        }
        if (d == null) {
            throw new java.lang.IllegalArgumentException();
        }
        return d;
	}
	
	/**
	 * @author lijie
	 * @param Begin
	 * @param end
	 * 传入开始时间 和 结束时间 格式如：2012-09-07
	 * @return
	 * 返回Map  获取相隔多少年 get("YEAR")及为俩个时间年只差，月 天，类推
	 *  Key ：
	 *  YEAR MONTH DAY
	 *  如果开始时间 晚于 结束时间 return null；
	 */
	
	public static Map getApartTime(String Begin, String end) {
		  String[] temp = Begin.split("-");
	        String[] temp2 = end.split("-");
	        if (temp.length > 1 && temp2.length > 1) {
	            Calendar ends = Calendar.getInstance();
	            Calendar begin = Calendar.getInstance();

	            begin.set(NumberUtils.getInt(temp[0]),
	                    NumberUtils.getInt(temp[1]), NumberUtils.getInt(temp[2]));
	            ends.set(NumberUtils.getInt(temp2[0]),
	                    NumberUtils.getInt(temp2[1]), NumberUtils.getInt(temp2[2]));
	            if (begin.compareTo(ends) < 0) {
	                Map map = new HashMap();
	                ends.add(Calendar.YEAR, -NumberUtils.getInt(temp[0]));
	                ends.add(Calendar.MONTH, -NumberUtils.getInt(temp[1]));
	                ends.add(Calendar.DATE, -NumberUtils.getInt(temp[2]));
	                map.put("YEAR", ends.get(Calendar.YEAR));
	                map.put("MONTH", ends.get(Calendar.MONTH) + 1);
	                map.put("DAY", ends.get(Calendar.DATE));
	                return map;
	            }
	        }
	        return null;
	}
	
	public static Date rollHour(Date d,int hour){
		Calendar cal=Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.HOUR_OF_DAY, hour);
		return cal.getTime();
	}
	
	public static Date rollDay(Date d,int day){
		Calendar cal=Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.DAY_OF_MONTH, day);
		return cal.getTime();
	}
	public static Date rollMon(Date d,int mon){
		Calendar cal=Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.MONTH, mon);
		return cal.getTime();
	}
	public static Date rollYear(Date d,int year){
		Calendar cal=Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.YEAR, year);
		return cal.getTime();
	}
	public static Date rollDate(Date d,int year,int mon,int day){
		Calendar cal=Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.YEAR, year);
		cal.add(Calendar.MONTH, mon);
		cal.add(Calendar.DAY_OF_MONTH, day);
		return cal.getTime();
	}
	
	
	/**
	 * 获取当前时间的秒数字符串
	 * @return
	 */
	public static String getNowTimeStr(){
		String str=Long.toString(System.currentTimeMillis() / 1000);
		return str;
	}
	
	// v1.6.6.2 RDPROJECT-277 xx 2013-10-22 start
	/**
	 * 获取当前时间-时间戳
	 * @return
	 */
	public static int getNowTime(){
		return Integer.parseInt((System.currentTimeMillis() / 1000)+"");
	}
	// v1.6.6.2 RDPROJECT-277 xx 2013-10-22 end
	
	public static String getTimeStr(Date time){
		long date = time.getTime();
		String str=Long.toString(date / 1000);
		return str;
	}
	public static String getTimeStr(String dateStr,String format) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date;
		date = sdf.parse(dateStr);
		String str=DateUtils.getTimeStr(date);
		return str;
	}
	
	public static String rollMonth(String addtime,String time_limit){
		Date t=DateUtils.rollDate(DateUtils.getDate(addtime), 0, NumberUtils.getInt(time_limit),0);
		return t.getTime()/1000+"";
	}
	
	public static String rollDay(String addtime,String time_limit_day){
		Date t=DateUtils.rollDate(DateUtils.getDate(addtime), 0, 0,NumberUtils.getInt(time_limit_day));
		return t.getTime()/1000+"";
	}
	
	public static Date getIntegralTime(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	public static Date getLastIntegralTime(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	public static Date getLastSecIntegralTime(Date d){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(d.getTime());
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static long getTime(String format){
		long t=0;
		if(StringUtils.isBlank(format)) return t;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		try {
			date = sdf.parse(format);
			t=date.getTime()/1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return t;
	}
	//获取本周日的日期
	public static String getCurrentWeekday() {    
        int weeks = 0;    
        int mondayPlus = DateUtils.getMondayPlus();    
        GregorianCalendar currentDate = new GregorianCalendar();    
        currentDate.add(GregorianCalendar.DATE, mondayPlus+6);    
        Date monday = currentDate.getTime();    
            
        DateFormat df = DateFormat.getDateInstance();    
        String preMonday = df.format(monday);    
        return preMonday;    
    }    
 // 获得当前日期与本周日相差的天数    
    private static int getMondayPlus() {    
        Calendar cd = Calendar.getInstance();    
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......    
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK)-1;         //因为按中国礼拜一作为第一天所以这里减1    
        if (dayOfWeek == 1) {    
            return 0;    
        } else {    
            return 1 - dayOfWeek;    
        }    
    }    
        
    //获得本周一的日期    
    public  static String getMondayOFWeek(){    
    	int weeks = 0;    
         int mondayPlus = DateUtils.getMondayPlus();    
         GregorianCalendar currentDate = new GregorianCalendar();    
         currentDate.add(GregorianCalendar.DATE, mondayPlus);    
         Date monday = currentDate.getTime();    
             
         DateFormat df = DateFormat.getDateInstance();    
         String preMonday = df.format(monday);    
         return preMonday;    
    }    
    //获取当前月第一天：
    public static String getFirstDayOfMonth(String first){
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
   	  	Calendar c = Calendar.getInstance();   
   	  	c.add(Calendar.MONTH, 0);
   	  	c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
   	  	first = format.format(c.getTime());
   	  	return first;
    }
    //获取当月最后一天
    public static String getLastDayOfMonth(String last){
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
    	Calendar ca = Calendar.getInstance();   
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH)); 
        last = format.format(ca.getTime());
    	return last;
    }
    
     /**
      * 日期月份处理
      * @param d 时间
      * @param month 相加的月份，正数则加，负数则减
      * @return
      */
     public static Date timeMonthManage(Date d , int month){
         Calendar rightNow = Calendar.getInstance();
         rightNow.setTime(d);
         rightNow.add(Calendar.MONTH, month);
         return rightNow.getTime();
     }
     
     /**
      * 获取指定年月的最后一天
      * @param year_time 指定年
      * @param month_time 指定月
      * @return
      */
     public static Date monthLastDay(int year_time , int month_time){
    	Calendar cal = Calendar.getInstance();
    	cal.set(year_time, month_time, 0,23,59,59);
        return cal.getTime();
     } 
     
     
     /**
      * 获取指定年月的第一天
      * @param year_time 指定年
      * @param month_time 指定月
      * @return
      */
     public static Date monthFirstDay(int year_time , int month_time){
    	Calendar cal = Calendar.getInstance();
    	cal.set(year_time, month_time-1, 1,0,0,0);
        return cal.getTime();
     } 
     
     /**
      * 获取指定时间月的第一天
      * @param d 指定时间，为空代表当前时间
      * @return
      */
     public static Date currMonthFirstDay(Date d){
    	Calendar cal = Calendar.getInstance();
    	if(d != null) cal.setTime(d);
    	cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));  
    	cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH), cal.get(Calendar.DATE),0,0,0);
        return cal.getTime();
     } 
     
     /**
      * 获取指定时间月的最后一天
      * @param d 指定时间，为空代表当前时间
      * @return
      */
     public static Date currMonthLastDay(Date d){
    	Calendar cal = Calendar.getInstance();
    	if(d != null) cal.setTime(d);
    	cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
    	cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH), cal.get(Calendar.DATE),23,59,59);
        return cal.getTime();
     }
     /**
      * 获取指定时间的年
      * @param date 指定时间
      * @return
      */
     public static int getTimeYear(Date date){
    	if(date == null) date = new Date();
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
        return cal.get(Calendar.YEAR);
     } 
     /**
      * 获取指定时间的月
      * @param date 指定时间
      * @return
      */
     public static int getTimeMonth(Date date){
    	if(date == null) date = new Date();
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
        return cal.get(Calendar.MONTH)+1;
     } 
     
     /**
      * 获取指定时间的天
      * @param date 指定时间
      * @return
      */
     public static int getTimeDay(Date date){
    	if(date == null) date = new Date();
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
        return cal.get(Calendar.DATE);
     } 
     
     public static Date getFirstSecIntegralTime(Date d){
 		Calendar cal = Calendar.getInstance();
 		cal.setTimeInMillis(d.getTime());
 		cal.set(Calendar.HOUR_OF_DAY, 0);
 		cal.set(Calendar.SECOND, 0);
 		cal.set(Calendar.MINUTE, 0);
 		cal.set(Calendar.MILLISECOND, 0);
 		cal.set(Calendar.DATE, 0);
 		return cal.getTime();
 	}
   
     /**
      * 获取指定时间天的结束时间
      * @param d
      * @return
      */
     public static Date getDayEndTime(long d){
    	Date day = new Date( d * 1000);
    	if(d <= 0) day = new Date();
    	Calendar cal = Calendar.getInstance();
     	if(day != null) cal.setTimeInMillis(day.getTime());
     	cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH), cal.get(Calendar.DATE),23,59,59);
        return cal.getTime();
     }
     
     /**
      * 获取指定时间天的开始时间
      * @param d
      * @return
      */
     public static Date getDayStartTime(long d){
    	Date day = new Date( d * 1000);
     	if(d <= 0) day = new Date();
     	Calendar cal = Calendar.getInstance();
      	if(day != null) cal.setTimeInMillis(day.getTime());
      	cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH), cal.get(Calendar.DATE),0,0,0);
         return cal.getTime();
      }
	/*public static void main(String[] args) {
		
		try {
//			date = sdf.parse("2013-01-30");
//			long t = getTime(new Date());
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			long b = 1360515600;
			//System.out.println(getTimeMonth(new Date()));
			System.out.println(f.format(monthLastDay(2007,3)));
			
			
			
			
			String aa = DateUtils.getTimeStr(DateUtils.rollMon(DateUtils.getDate(b+""),-12));
			
			//System.out.println(aa +"  >>>>>>");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new Date();
		System.out.println(dateStr2(new Date()));
		
	}*/
}
