// Hashmap01.java
// HashMap:
// Write a Java program to store the car model, year, colour, and price of cars on a used car lot.
// Print the results.

package try01;

import java.util.HashMap;

public class HashMap01 {

    public static void main(String[] args) {


        Car carTest1 = new Car("Honda", 1990, "Red", 1200);
        Car carTest2 = new Car("Subaru", 2010, "Silver", 2500);

        System.out.println(String.format("%s %d %s %d ", carTest1.model, carTest1.year, carTest1.colour, carTest1.price));
        System.out.println(String.format("%s %d %s %d ", carTest2.model, carTest2.year, carTest2.colour, carTest2.price));

    HashMap<String, String> car1 = new HashMap<String, String>();
        car1.put("model", "Honda");
        car1.put("year", "1999");
        car1.put("colour", "Blue");
        car1.put("price", "$1200");

    HashMap<String, String> car2 = new HashMap<String, String>();
        car2.put("model", "Subaru");
        car2.put("year", "2007");
        car2.put("colour", "Grey");
        car2.put("price", "$1600");

        System.out.println(car1);
        System.out.println(car2);



    }
}
