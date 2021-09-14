package russ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

// Avoid importing more than should
//import java.util.*;

public class russ {

	/**
	 * 
	 * An introduction of collections by way of a sample. The sample uses:
	 * String, Set, SortedSet, List, Map, SortedMap.
	 * 
	 * Also internal representations employed below are: HashSet, TreeSet,
	 * HashMap, TreeMap.
	 * 
	 * Collections tutorial found at...
	 * http://java.sun.com/docs/books/tutorial/collections/TOC.html#interfaces
	 * http://java.sun.com/docs/books/tutorial/collections/index.html
	 * 
	 * @author Russell Shanahan
	 * @param args -
	 *            not used
	 */

	public static void main(String[] args) {

		String[] sample1 = { "i", "came", "i", "saw", "i", "left" };
		String[] sample2 = { "u", "came", "to", "see", "that", "i", "left" };

//		muckWithaString(sample1, sample2);

//		muckWithaSet(sample1, sample2);

//		muckWithaList(sample1, sample2);

		muckWithaMap(sample1, sample2);

	}

	/**
	 * Print the array
	 * 
	 * @param strs
	 */
	public static void printArray(String[] strs) {
		// print out the array
		for (int i = 0; i < strs.length; i++) {
			System.out.print(" " + strs[i]);
		}
		System.out.println();
	}

	/**
	 * Strings
	 * 
	 * @param strs1
	 * @param strs2
	 */
	public static void muckWithaString(String[] strs1, String[] strs2) {
		System.out.println("Muck with a String using this array: ");
		printArray(strs1);
		printArray(strs2);

		// initialize local variable
		int compare = 0;

		// look for "came" in the array
		String it = "came";
		for (int i = 0; i < strs1.length; i++) {
			compare = it.compareTo(strs1[i]);
			System.out.print(" compare " + it + " to " + strs1[i] + " = "
					+ compare);
			if (compare == 0)
				System.out.println(" ***** wow " + it + " equals " + strs1[i]);
			else
				System.out.println();
		}

		// Comparing strings in the arrays
		for (int i = 0; i < strs1.length; i++) {
			for (int j = 0; j < strs2.length; j++) {
				compare = strs1[i].compareTo(strs2[j]);
				if (compare == 0)
					System.out.println(" ***** wow " + strs1[i] + " at " + i
							+ " equals " + strs2[j] + " at " + j);
			}
		}

		// Tell the world we're done for now
		System.out.println();
		System.out.println("...Done mucking with a String");
		System.out.println("====================================");
	}

	/**
	 * Sets
	 * 
	 * @param strs1
	 * @param strs2
	 */
	public static void muckWithaSet(String[] strs1, String[] strs2) {
		System.out.print("Muck with a Set using this array: ");
		printArray(strs1);

		// Make a set out of the array given
		Set<String> s1 = new HashSet<String>(50, 30); // initialCapacity and loadFactor
		for (String a : strs1)
			// new for stmt for Java Version 5.0, refer to ...
			// http://java.sun.com/docs/books/tutorial/java/nutsandbolts/for.html
			if (!s1.add(a)) // !s.add means it WASN'T added, failed because dup.
				System.out.println("Duplicate detected: " + a);
		// Sets can be printed directly, s has no duplicates
		System.out.println("Set size=" + s1.size() + "  s1=" + s1);

		// Make another set from second array passed in
		Set<String> s2 = new HashSet<String>();
		for (String a : strs2)
			s2.add(a); // add each element of strs2

		// Show the process of making an intersection set
		Set<String> s3 = new HashSet<String>();
		System.out.println("S1=>" + s1 + " S2=>" + s2 + " S3=>" + s3);
		s3 = s2; // initialize s3 with data from s2
		System.out.println("S1=>" + s1 + " S2=>" + s2 + " S3=>" + s3);
		s3.retainAll(s1); // Keep in s3 only elements found in s1
		System.out.println("S1=>" + s1 + " S2=>" + s2 + " S3=>" + s3);

		// Sorted set, a TreeSet complies with SortedSet
		SortedSet<String> s4 = new TreeSet<String>();
		for (String a : strs1)
			s4.add(a); // add each element of strs1
		// Note 1: toString not guaranteed to be sorted
		System.out.println(" Sorted S1 in new s4=>" + s4);

		// Export s3 to string array and print
		System.out.println("Back to an array");
		// Note 2: toArray IS guaranteed to be sorted
		String[] strs3 = s4.toArray(new String[0]);
		printArray(strs3);

		// Tell the world we're done for now
		System.out.println();
		System.out.println("...Done mucking with a Set");
		System.out.println("====================================");
	}

	/**
	 * Lists
	 * 
	 * @param strs1
	 * @param strs2
	 */
	public static void muckWithaList(String[] strs1, String[] strs2) {
		System.out.print("Muck with a List using this array: ");
		printArray(strs1);

		// Make a set out of the array given
		List<String> s1 = new ArrayList<String>(64);  // initialCapacity
		for (String a : strs1)
			s1.add(a);
		System.out.println("Set size=" + s1.size() + "  s1=" + s1);

		// Some simple gets
		System.out.println("S1[0]=>" + s1.get(0));
		System.out.println("S1[1]=>" + s1.get(1));
		System.out.println("S1[2]=>" + s1.get(2));

		// Set up other lists and print, not very interesting
		List<String> s2 = new ArrayList<String>();
		for (String a : strs2)
			s2.add(a);
		List<String> s3 = new ArrayList<String>();
		System.out.println("S1=>" + s1 + " S2=>" + s2 + " S3=>" + s3);

		// iterate through the list
		System.out.print("Iterate: ");
		for (Iterator<String> iter = s1.iterator(); iter.hasNext();) {
			String str = (String) iter.next();
			System.out.print("." + str);
		}
		System.out.println();

		// Search through the List
		for (Iterator<String> iter = s1.iterator(); iter.hasNext();) {
			String str = (String) iter.next();
			System.out.println("Search: " + str + " found at "
					+ s1.indexOf(str));
		}

		// Sort one of the lists
		System.out.println(" S2=>" + s2);
		Collections.sort(s2);
		System.out.println(" sorted S2=>" + s2);
		Collections.shuffle(s2);
		System.out.println(" shuffled S2=>" + s2);

		// Tell the world we're done for now
		System.out.println();
		System.out.println("...Done mucking with a List");
		System.out.println("====================================");
	}

	/**
	 * Maps
	 * 
	 * @param strs1
	 * @param strs2
	 */
	private static void muckWithaMap(String[] strs1, String[] strs2) {
		System.out.print("Muck with a Map using this array: ");
		printArray(strs1);

		// Make a Map out of the array given
		Map<String, String> s1 = new HashMap<String, String>(4,4); // initialCapacity and loadFactor
		s1.put("Name", "Russell Shanahan");
		s1.put("Phone", "555-1234");
		s1.put("Email", "my.email@nsscc.ca");
		s1.put("Postal Code", "2B1 UR2");
		System.out.println("size=" + s1.size() + "  s1=" + s1);

		// Some simple gets
		System.out.println("Name=>" + s1.get("Name"));
		System.out.println("Phone=>" + s1.get("Phone"));
		System.out.println("Email=>" + s1.get("Email"));
		System.out.println("Postal Code=>" + s1.get("Postal Code"));

		// Parts of the Map
		System.out.println("Keys=" + s1.keySet());
		System.out.println("Values=" + s1.values());
		System.out.println("EntrySet=" + s1.entrySet());

		// Iterate across the keys
		for (String key : s1.keySet())
			System.out.println(key + " => " + s1.get(key));

		// use c-style print formatting
		System.out.printf("s1.containsValue(\"Russell Shanahan\") => "
				+ s1.containsValue("Russell Shanahan") + "\n");

		// SortedMap sorts by the keys, uses a TreeMap
		SortedMap<String, String> s4 = new TreeMap<String, String>();
		s4.put("Name", "Russell Shanahan");
		s4.put("Phone", "555-1234");
		s4.put("Email", "my.email@nsscc.ca");
		s4.put("Postal Code", "2B1 UR2");
		System.out.println("Sorted Map=" + s4);

		// Tell the world we're done for now
		System.out.println();
		System.out.println("...Done mucking with a Map");
		System.out.println("====================================");
	}
}
