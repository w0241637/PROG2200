package org.lwjglb.engine.items;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class myDateTime {



    private static String rightNow;

    /**
     * set current time
     */
    public static void setRightNow() {
        rightNow = dateTime();
    }

    /**
     * get current time
     * @return
     */
    public static String getRightNow(){
        return rightNow;
    }


    /**
     *
     * @return String date and time
     */
    public static String dateTime(){
        LocalDateTime myDateObj = LocalDateTime.now();
        System.out.println("Before formatting: " + myDateObj);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss"); //code little messy from troubleshooting the : in filename

        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println("After formatting: " + formattedDate);
        return formattedDate;
    }

    /**
     *
     * @return String of time
     */
    public static String justTime(){
        LocalDateTime myDateObj = LocalDateTime.now();
        System.out.println("Before formatting: " + myDateObj);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH-mm-ss"); //code little messy from troubleshooting the : in filename

        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println("After formatting: " + formattedDate);
        return formattedDate;
    }

}
