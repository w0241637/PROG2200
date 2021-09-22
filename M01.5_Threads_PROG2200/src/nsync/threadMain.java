//Use the Java Thread programs shown in class as a starting point for this assignment.
//        [10%] Create static data to represent Dorthy, her favorite character and her favorite color.
//        [20%] Create threads for the Tin Man (Silver), Scarecrow (Brown), and Cowardly Lion (Yellow), that each set Dorthy's favorite character to themselves, and a color.
//        [10%] You must use an Enumeration for the color.
//        Have 2 versions of the program
//        [20%] Version 1: no thread locking….the data is constantly corrupted when printed from the main line.
//        [20%] Version 2: thread safe…the data is always fully one set or another, never a combination.
//        Just three threads is not enough, you'll need about 100 or more of each (Tin Man, Scarecrow, and Cowardly Lion), all trying to be Dorthy's favorite. Use an appropriate collection(s) for this.

package nsync;

/**
 * Make a few threads compete for a static set of fields.
 * 
 * @author Ezra, then Russ
 * 
 */
public class threadMain {

//    public enum OzColours {
//        BROWN, YELLOW, SILVER
//    }
    /**
     * @param args
     */



    public static void main(String[] args) {



//    	Dorthy.ozcol myOz = Dorthy.ozcol.b;
    	
//    	Dorthy dorthy = new Dorthy("Data1", 1);

    	
//    	dorthy.favChar = "russ";
    	
        thread tinman = new thread("the SILVER Tin Man", Dorthy.OzColours.SILVER);
        tinman.start();

        thread scarecrow = new thread("the BROWN Scarecrow", Dorthy.OzColours.BROWN);
        scarecrow.start();

        thread lion = new thread("the YELLOW Cowardly Lion", Dorthy.OzColours.YELLOW);
        lion.start();

        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(10);
                System.out.println(Dorthy.getStaticThreadperson());
                //String s = Data.getStaticThreadperson();
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        //System.out.println(thread.personChange.name + " " + thread.personChange.cell_number + " " + thread.personChange.phone_number + " " + thread.personChange.email);
        }

    }
}
