package org.lwjglb.engine.items;



import org.lwjgl.system.CallbackI;


import java.io.*;


public class MyLog{

    private static MyLog logInstance = null;
    private static String now;
    private BufferedReader bufferedReader = null;
    private String pathName = "C:\\Users\\timot\\Desktop\\gameLogs\\Log_"+now+".csv";
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


    /**
     * get current date and time
     */
    public static void setNow(){
        now = myDateTime.getRightNow();
    }

    /**
     * getter
     * @return
     */
    public int getLogCount() {
        return logCount;
    }

    /**
     * setter
     * @param logCount
     */
    public void setLogCount(int logCount) {
        this.logCount = logCount;
    }

    public void test(){
//        dateTime();
        System.out.println("this thing on?");
    }

}

