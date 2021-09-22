//Use the Java Thread programs shown in class as a starting point for this assignment.
//        [10%] Create static data to represent Dorthy, her favorite character and her favorite color.
//        [20%] Create threads for the Tin Man (Silver), Scarecrow (Brown), and Cowardly Lion (Yellow), that each set Dorthy's favorite character to themselves, and a color.
//        [10%] You must use an Enumeration for the color.
//        Have 2 versions of the program
//        [20%] Version 1: no thread locking….the data is constantly corrupted when printed from the main line.
//        [20%] Version 2: thread safe…the data is always fully one set or another, never a combination.
//        Just three threads is not enough, you'll need about 100 or more of each (Tin Man, Scarecrow, and Cowardly Lion), all trying to be Dorthy's favorite. Use an appropriate collection(s) for this.


package synchMethods.nsync;

/**
 * @author Ezra, then Russ
 * @description Try's to change data of a person
 */
public class thread extends Thread {

    /**
     * This copy of data to use to overwrite
     */
    public String name;
    private Dorthy.OzColours colour;
//    public Object colour;


//    /**
//     * thread receives person information
//     * @param s Name
//     * @param p Phone
//     * @param c Cell
//     * @param e Email
//     */
    
//    working well
//    public thread(String name, Object colour) {
//        this.name = name;
//        this.colour = colour;
//    }

    public thread(String name, Dorthy.OzColours colour) {
        this.name = name;
        this.colour = colour;
    }

    /**
     * Loop setting our name, phone, etc.. into static area.
     */
    @Override
    public void run() {
        System.out.println("Start name = " + this.name);
        try {
            for (int i = 0; i < 1000000; i++) {
                Dorthy.setStaticThreadperson(this.name, this.colour);
                //System.out.println("Loop thread " + i + " "+ this.name);
                Thread.sleep(30);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
