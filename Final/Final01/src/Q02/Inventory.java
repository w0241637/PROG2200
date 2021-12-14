//2) [30%] Bonus Restock: Copy #1 to a new project,
// and add ... a re-stock function, that when inventory gets below some count (say 3 items remaining),
// the re-stock thread orders a truck load of those items to arrive, and a few seconds later (say 5 seconds),
// a truck load amount of items (say 100 items) arrives. (i.e. calls the invRestock(20, pens) … or whatever).
//
//        Logs show the following:
//        When inventory is low (your own specified level), the truck load of inventory arrives with re-stock.
//        Re-stock is thread safe with the salesmen threads selling the items, without using busy waits [15%].
//
//        Items are sold, salesman are selling and waiting, and trucks arriving with new inventory [15%], and all this is thread-safe.
//
//        ...and this breaks the flow of the earlier question, in that the inventory may never end,
//        and salesmen never actually dump their sales “at the end”, so you can show salesman sales at the time of restock,
//        or another convenient time (which can be used to get your mark)


package Q02;

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



    public static void restockPens(int amount){

            Inventory.setPenCount(amount);
    }

    public static void restockPencils(int amount){
        Inventory.setPencilCount(amount);
    }
    public static void restockErasers(int amount){
        Inventory.setEraserCount(amount);
    }
    public static void restockCrayons(int amount){
        Inventory.setCrayonCount(amount);
    }
    public static void restockMarkers(int amount){
        Inventory.setMarkerCount(amount);
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