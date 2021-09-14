package russ;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class ParameterizeDeclarations {

	public static void main(String[] args) {

		// The idea is, the sooner you catch errors the better (cheaper)
		// so catching errors at "compile time" is best.
		
		// Java complains that Set and TreeSet declarations are not parameterized.
		// which means at run-time you could put any type (boolean, Integer, String, ...)
		// and that causes bugs.
        String Names[] = {"Emily", "Greg", "Jon", "Andy", "Adam", "Brendon", "Brandon", "Evan", "Jonathan", "Mike"};
        Set mySort = new TreeSet(Arrays.asList(Names));
        //Set<String> mySort = new TreeSet<String>(Arrays.asList(Names));
        System.out.println("Sorted = " + mySort);

        // Force set to hold only strings, reduces bugs in casting.
        Set<String> mySort2 = new TreeSet<String>(Arrays.asList("Emily", "Greg", "Jon", "Andy", "Adam", "Brendon", "Brandon", "Evan", "Jonathan", "Mike"));
        System.out.println("Sorted2 = " + mySort2);

				
	}

}
