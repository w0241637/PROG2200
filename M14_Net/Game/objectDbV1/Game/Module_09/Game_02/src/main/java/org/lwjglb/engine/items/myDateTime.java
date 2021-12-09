package org.lwjglb.engine.items;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class myDateTime {



    private static String rightNow;

    public static void setRightNow() {
        rightNow = dateTime();
    }

    public static String getRightNow(){
        return rightNow;
    }




    public static String dateTime(){
        LocalDateTime myDateObj = LocalDateTime.now();
        System.out.println("Before formatting: " + myDateObj);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss"); //code little messy from troubleshooting the : in filename

        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println("After formatting: " + formattedDate);
        return formattedDate;
    }

    public static String justTime(){
        LocalDateTime myDateObj = LocalDateTime.now();
        System.out.println("Before formatting: " + myDateObj);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH-mm-ss"); //code little messy from troubleshooting the : in filename

        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println("After formatting: " + formattedDate);
        return formattedDate;
    }

}
