package hello;

//import java.util.Timer;

import java.util.*;

public class test2 {

        public void givenUsingTimer_whenSchedulingTaskOnce_thenCorrect() {
            TimerTask task = new TimerTask() {
                public void run() {
                    System.out.println("Task performed on: " + new Date() + "n" +
                            "Thread's name: " + Thread.currentThread().getName());
                }
            };
            Timer timer = new Timer("Timer");

            long delay = 1000L;
            timer.schedule(task, delay);
        };




}