package edu.baylor.ecs.csi3471.HasslerHarrison.module2;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
class CalendarExercise1 {
    public void printDate(Calendar calendar1){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = calendar1.getTime();
        String newDate = dateFormat.format(date);
        System.out.println(newDate);

    }

    public static void main(String[] args){
        Calendar currentCalendar = Calendar.getInstance();
        CalendarExercise1 calendarExercise1 = new CalendarExercise1();
        Calendar backCalendar = (Calendar) currentCalendar.clone();
        backCalendar.add(Calendar.MONTH, -2);
        calendarExercise1.printDate(backCalendar);

        Calendar forwardCalender = (Calendar) currentCalendar.clone();
        forwardCalender.add(Calendar.MONTH, 2);
        calendarExercise1.printDate(forwardCalender);



    }

}
