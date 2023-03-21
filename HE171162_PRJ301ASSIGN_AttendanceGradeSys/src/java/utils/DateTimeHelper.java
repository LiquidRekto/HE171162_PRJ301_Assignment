/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.*;


public class DateTimeHelper {
    
    public static Map<Integer, java.sql.Date[]> getAllWeekRangesOfYear(int year) {
        Map<Integer, java.sql.Date[]> weekMap = new HashMap<Integer, java.sql.Date[]>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        ArrayList<java.sql.Date[]> listWeekRanges = new ArrayList<>();
        int maxWeeks = calendar.getActualMaximum(Calendar.WEEK_OF_YEAR);
        for (int i = 1; i <= maxWeeks; i++) {
            calendar.set(Calendar.WEEK_OF_YEAR, i);
            java.sql.Date[] range = new java.sql.Date[2];
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            range[0] = convertUtilToSqlDate(calendar.getTime());
            calendar.add(Calendar.DATE, 6);
            range[1] = convertUtilToSqlDate(calendar.getTime());
            weekMap.put(i, range);
        }
        return weekMap;
    }
    
    public static int getWeekNumFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }
    
    public static int getCurrentWeekRangeNum() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }
    
    public static java.sql.Date[] getCurrentWeekRange() {
        java.sql.Date[] range = new java.sql.Date[2];
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        
        // Gets Monday
        range[0] = convertUtilToSqlDate(keepOnlyDatePart(calendar.getTime()));

        //// Gets Sunday
        calendar.add(Calendar.DATE, 6);
        range[1] = convertUtilToSqlDate(keepOnlyDatePart(calendar.getTime()));
        
        return range;
    }
    
    public static java.sql.Date getTodayDate() {
        return (convertUtilToSqlDate(keepOnlyDatePart(Calendar.getInstance().getTime())));
    }
    
    public static java.sql.Timestamp getTodayDateWithTime() {
        return (convertUtilToSqlTimestamp(Calendar.getInstance().getTime()));
    }
    
    public static ArrayList<java.sql.Date> getListDatesOfWeekYear(int weekNum, int year) {
        Calendar cl = Calendar.getInstance();
        cl.set(Calendar.YEAR,year);
        cl.set(Calendar.WEEK_OF_YEAR, weekNum);
        ArrayList<java.sql.Date> dates = new ArrayList<>();
        cl.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        java.sql.Date loop = convertUtilToSqlDate(keepOnlyDatePart(cl.getTime()));
        cl.add(Calendar.DATE, 6);
        java.sql.Date to = convertUtilToSqlDate(cl.getTime());
        while(loop.compareTo(to) <= 0)
        {
            dates.add(loop);
            java.util.Date d = convertSqlToUtilDate(loop);
            d = addDays(d, 1);
            d = keepOnlyDatePart(d);
            loop = convertUtilToSqlDate(d);
        }
        return dates;
    }
    
    public static int getCurrentYear() {
        Calendar cl = Calendar.getInstance();
        return cl.get(Calendar.YEAR);
    }
    
    
    public static ArrayList<Integer> generateFiveYearRange() {
        Calendar cl = Calendar.getInstance();
        ArrayList<Integer> years = new ArrayList<>();
        int beginYear = cl.get(Calendar.YEAR) - 2;
        for (int i = 0; i < 5; i++) {
            years.add(beginYear);
            beginYear++;
        }
        return years;
    }

    public static ArrayList<java.sql.Date> getListDates(java.sql.Date from, java.sql.Date to) {
        ArrayList<java.sql.Date> dates = new ArrayList<>();
        java.sql.Date loop = from;
        while(loop.compareTo(to) <= 0)
        {
            dates.add(loop);
            java.util.Date d = convertSqlToUtilDate(loop);
            d = addDays(d, 1);
            d = keepOnlyDatePart(d);
            loop = convertUtilToSqlDate(d);
        }
        return dates;
    }

    public static java.util.Date addDays(java.util.Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    public static java.sql.Date convertUtilToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static java.util.Date convertSqlToUtilDate(java.sql.Date sqlDate) {
        return new java.util.Date(sqlDate.getTime());
    }
    
    public static java.sql.Timestamp convertUtilToSqlTimestamp(java.util.Date date) {
        return new java.sql.Timestamp(date.getTime());
    }

    public static java.util.Date keepOnlyDatePart(java.util.Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

}
