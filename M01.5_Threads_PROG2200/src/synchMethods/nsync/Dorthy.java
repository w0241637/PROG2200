//Use the Java Thread programs shown in class as a starting point for this assignment.
//        [10%] Create static data to represent Dorthy, her favorite character and her favorite color.
//        [20%] Create threads for the Tin Man (Silver), Scarecrow (Brown), and Cowardly Lion (Yellow),
//        that each set Dorthy's favorite character to themselves, and a color.
//        [10%] You must use an Enumeration for the color.
//        Have 2 versions of the program
//        [20%] Version 1: no thread locking….the data is constantly corrupted when printed from the main line.
//        [20%] Version 2: thread safe…the data is always fully one set or another, never a combination.
//        Just three threads is not enough, you'll need about 100 or more of each (Tin Man, Scarecrow, and Cowardly Lion), all trying to be Dorthy's favorite. Use an appropriate collection(s) for this.


package synchMethods.nsync;

/**
 * @author Ezra, then Russ, then Tim description: class to hold a persons data.
 */

public class Dorthy {

	public enum ozcol {
		a, b, c
	}

	public enum OzColours {
		BROWN, YELLOW, SILVER
	}

	public static String favChar;
	public static OzColours favColour;

	/**
	 * Constructor for static data area. Set a default.
	 * 
	 * @param name
	 * @param colour
	 */

	public Dorthy(String name, OzColours colour) {
		Dorthy.favChar = name;
		Dorthy.OzColours OzColour = colour;
		// mySleep(20);
	}

//	Working well
//	public Dorthy(String name, Object colour) {
//		Dorthy.favChar = name;
//		Dorthy.favColour = colour;
//		// mySleep(20);
//	}

	/**
	 * Get the string version of the data appended. We get all one set of data
	 * this way.
	 * 
	 * @return A string which is the data fields appended.
	 */
	public synchronized static String getStaticThreadperson() {
		mySleep(20);

		return "favourite character = " + Dorthy.favChar + " Favourite Colour = " + Dorthy.favColour;
	}


	/**
	 * Set the static fields here.
	 * 
	 * @param name
	 * @param colour
	 */
	public synchronized static void setStaticThreadperson(String name, OzColours colour) {
		//public synchronized static void setStaticThreadperson(String name, long phone, long cell, String email) {
		Dorthy.favChar = name;
		mySleep(20);
		Dorthy.favColour = colour;
		mySleep(20);
	}

	public static void mySleep(int d) {
		try {
			Thread.sleep(d);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
