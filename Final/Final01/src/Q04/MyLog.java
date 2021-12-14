package Q04;



import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class MyLog{

    private static MyLog logInstance = null;
    private static String now;
//    private static String pathName;
    private BufferedReader bufferedReader = null;
    private static String pathName = "/Users/tim/Desktop/finalLogOutput/Log.csv";
    private int logCount = 1;


    /**
     * empty constructor
     */
    private MyLog(){
    }

    /**
     * singleton pattern
     * @return instance of class
     */
    public synchronized static MyLog getInstance(){
        if(logInstance == null){
            setNow();
            logInstance = new MyLog();
        }
        return logInstance;
    }

    /**
     * write line to file
     * @param data
     */
    public static void writeString(String data) {

        File file = new File(pathName);
        FileWriter fr = null;
        BufferedWriter br = null;
        String dataWithNewLine=data+System.getProperty("line.separator");
        try{
            fr = new FileWriter(file, true);
            br = new BufferedWriter(fr);
//            for(int i = noOfLines; i>0; i--){
                br.write(dataWithNewLine);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * get current date and time
     */
    public static void setNow(){
        now = getRightNow();
    }







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



}

