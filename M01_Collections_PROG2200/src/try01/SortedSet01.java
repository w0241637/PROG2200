// Sorted Set:
// Write a Java program to sort a Java array of 10 names, without writing a sort routine (use SortedSet).
// Print the results.

package try01;

import java.util.Arrays;
import java.util.TreeSet;
import java.util.SortedSet;


public class SortedSet01 {


    public static void main(String[] args){
        System.out.println("is this thing on?");

        String Names[] = {"Tim", "Emily", "Greg", "Jon", "Andy", "Adam", "Brendon", "Brandon", "Evan", "Jonathan", "Mike"};
        Arrays.sort(Names);
        System.out.println(Arrays.toString(Names));


        String[] namesArray = { "Tim", "Emily", "Greg", "Jon", "Andy", "Adam", "Brendon", "Brandon", "Evan", "Jonathan", "Mike" };

        SortedSet<String> sortedSet = new TreeSet(Arrays.asList(namesArray));
        for(String i : sortedSet){
            System.out.println(i);
        }

    }
}
