package edu.baylor.ecs.csi3471.HasslerHarrison.module2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarExercise2 {
    public String processDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date copyDate = dateFormat.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(copyDate);
            calendar.add(Calendar.HOUR_OF_DAY, 2);
            calendar.add(Calendar.MINUTE, 2);

            Date newDate = calendar.getTime();
            return dateFormat.format(newDate);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


        public static void main(String[] args){
            CalendarExercise2 calendarExercise2 = new CalendarExercise2();

            String inputDate1 = "2020-08-01 23:00:00";
            String expectedDate1 = "2020-08-02 01:02:00";
            String result1 = calendarExercise2.processDate(inputDate1);

            System.out.println("Test 1 - Rolling to another day:");
            System.out.println("Expected: " + expectedDate1);
            System.out.println("Received: " + result1);
            System.out.println();

            String inputDate2 = "2020-08-01 9:30:00";
            String expectedDate2 = "2020-08-01 11:32:00";
            String result2 = calendarExercise2.processDate(inputDate2);

            System.out.println("Test 2 - Staying in the current day:");
            System.out.println("Expected: " + expectedDate2);
            System.out.println("Received: " + result2);
        }



}
