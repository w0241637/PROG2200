//1) [40%] Write a Java program with 20 threads that access common inventory data.
// The inventory data consists of 5 different items for sale,
// each with their own inventory count (at least 20 of each item), a name, and a description.
// Each salesman thread loops selling one inventory item at a time (randomly selected of the different items).
// It takes 100 milliseconds to process the selling of one inventory item (use a sleep to simulate the activity of a sale).
// Other threads must wait until a sale completes before their sale can proceed, but only if it is the same item type.
// The other item types may sell without delay.
// Use an inventory class that maintains the inventory data and ensures items are sold exactly once and provides the sale method which simulates the delay.
// Keep track of the total number of sales and sales that each thread has made...that is, don't sell more inventory than you have!
// Tweak thread delays as you wish, provided there is a mix of selling threads,
// but a change in delays (i.e. new delays) must not break the controlled sales of the salesman threads operating on the inventory data.
//
//
//        Logs show the following:
//        After the inventory is sold, [10%] each salesman thread prints its own thread-safe sales
//        (label this properly, so it can be seen which salesman sold what).
//
//        [30%] Sales of different types don’t wait for each other and items of the same type are thread-safe.
//        Only sales of the same type must wait.
//
//        [10%] If a salesman asks for several items, then the salesman can get several items (per remaining inventory).
//        (for example…   item_count = getItems(3, pens) to try to get 3 pens, but maybe only get 2)


package Q01;

public class Inventory {

    private static Inventory logInstance = null;

    public static int penCount, pencilCount, eraserCount, crayonCount, markerCount;

    public static String penName = "Bic", penDescription = "Blue Ink",
            pencilName = "sketcher", pencilDescription = "B2",
            eraserName = "Rubbermade", eraserDescription = "Pink",
            crayonName = "Crayola", crayonDescription = "whole box (64)",
            markerName = "Colour-er", markerDescription = "box on 8";

//    private Inventory(){
//    }
//
//    public synchronized static Inventory getInstance(){
//        if(logInstance == null){
//            logInstance = new Inventory();
//        }
//        return logInstance;
//    }

    public Inventory(){}

    public Inventory(int penCount, int pencilCount, int eraserCount, int crayonCount, int markerCount){
        this.penCount = penCount;
        this.pencilCount = pencilCount;
        this.eraserCount = eraserCount;
        this.crayonCount = crayonCount;
        this.markerCount = markerCount;

    }

//    public static void penStuff(int penCount){
//        penName = "Bic";
//        penDescription = "blue ink";
//        Inventory.penCount = penCount;
//    }



public static void reload(int amount) throws InterruptedException {
        Inventory.setPenCount(amount);
}

    public static void reloadAll(int amount) throws InterruptedException {
        Inventory.setPenCount(amount);
        Inventory.setPencilCount(amount);
        Inventory.setMarkerCount(amount);
        Inventory.setEraserCount(amount);
        Inventory.setCrayonCount(amount);

    }


    public static int getCombinedInventory() throws InterruptedException {
       int total = getPenCount()+getPencilCount()+getEraserCount()+getCrayonCount()+getMarkerCount();

               return total;
    }










    public static synchronized int getPenCount(){
        return penCount;
    }

    public static synchronized void setPenCount(int penCount){

        Inventory.penCount = penCount;
    }

    public static synchronized int getPencilCount() {
        return pencilCount;
    }

    public static synchronized void setPencilCount(int pencilCount) {
        Inventory.pencilCount = pencilCount;
    }

    public static synchronized int getEraserCount() {
        return eraserCount;
    }

    public static synchronized void setEraserCount(int eraserCount) {
        Inventory.eraserCount = eraserCount;
    }

    public static synchronized int getCrayonCount() {
        return crayonCount;
    }

    public static synchronized void setCrayonCount(int crayonCount) {
        Inventory.crayonCount = crayonCount;
    }

    public static synchronized int getMarkerCount() {
        return markerCount;
    }

    public static synchronized void setMarkerCount(int markerCount) {
        Inventory.markerCount = markerCount;
    }

    public static String getPenName() {
        return penName;
    }

    public static void setPenName(String penName) {
        Inventory.penName = penName;
    }

    public static String getPenDescription() {
        return penDescription;
    }

    public static void setPenDescription(String penDescription) {
        Inventory.penDescription = penDescription;
    }






}


//class Pens extends Inventory {
//    public static String name, description;
//    public static int inventory;
//
//    public static int getInventory() {
//        return inventory;
//    }
//
//    public static void setInventory(int inventory) {
//        Pens.inventory = inventory;
//    }
//
//    public Pens() {
//        this.name = "pen";
//        this.description = "ball point, blue ink";
//        this.inventory = inventory;
//    }
//
//}
//
//    class Pencil extends Inventory{
//        public static String name, description;
//        public static int inventory;
//
//        public static int getInventory() {
//            return inventory;
//        }
//
//        public static void setInventory(int inventory) {
//            Q01.Pens.inventory = inventory;
//        }
//
//        public Pencil(){
//            this.name = "pencil";
//            this.description = "yellow, hexagonal";
//            this.inventory = inventory;
//        }
//}