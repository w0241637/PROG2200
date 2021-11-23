package org.lwjglb.engine.items;



import org.lwjgl.system.CallbackI;


import java.io.*;


public class MyLog{

    private static MyLog logInstance = null;
    private static String now;
    private BufferedReader bufferedReader = null;
    private String pathName = "C:\\Users\\timot\\Desktop\\gameLogs\\Log_"+now+".csv";
    private int logCount = 1;


    private MyLog(){
    }

    public synchronized static MyLog getInstance(){
        if(logInstance == null){
            setNow();
            logInstance = new MyLog();
        }
        return logInstance;
    }

    public void writeString(String data) {
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

    public void test(){
//        dateTime();
        System.out.println("this thing on?");
    }

//    public String dateTime(){
//        LocalDateTime myDateObj = LocalDateTime.now();
//        System.out.println("Before formatting: " + myDateObj);
//        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss");
//
//        String formattedDate = myDateObj.format(myFormatObj);
//        System.out.println("After formatting: " + formattedDate);
//        return formattedDate;
//    }
//
    public static void setNow(){
        now = myDateTime.getRightNow();
    }


    public int getLogCount() {
        return logCount;
    }

    public void setLogCount(int logCount) {
        this.logCount = logCount;
    }

}

