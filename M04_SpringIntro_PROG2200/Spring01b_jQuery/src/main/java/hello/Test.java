package hello;

import java.util.Random;
import java.util.*;



    public class Test {

        private static int x;

        public static void main(String... args) throws InterruptedException {

            System.out.println("Random number generator");





            while(true) {
                Random rand = new Random(); //instance of random class
                int upperbound = 101;
                //generate random values from 0-100
                int int_random = rand.nextInt(upperbound);
                double double_random=rand.nextDouble();


//                x++;
                System.out.println(int_random);
                Thread.sleep(1000);
            }

        }
    }
