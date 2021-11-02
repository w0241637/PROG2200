//Write a Java program with three threads that access common inventory data.
//        The inventory data consists of
//        an inventory count (at least 10), a name, and a description.
//        [10%] Each thread (at least 3) loops selling one inventory item at a time.
//        It takes one second to process the selling of one inventory item
//        (use a sleep to simulate the activity of a sale).
//        Other threads must wait until a sale completes before their sale can proceed.
//        [10%] Use an inventory class that maintains the inventory data
//        and ensures only one item is sold at a time,
//        and provides the sale method which delays for one second.
//        [10%] Keep track of the total number of sales and sales that each
//        thread has made...that is, don't sell more inventory than you have!
//        Tweak thread delays as you wish,
//        provided there is a mix [10%]of selling threads,
//        but a change in delays must not break the safety of the threads
//        operating on the inventory data.

package try02;

public class Inventory {



    public static int inventoryCount;
    public static String name;
    public static String description;



    public Inventory(int Count, String name, String description){
        Inventory.inventoryCount = Count;
        this.name = name;
        this.description = description;
    }



    public static int getInventoryCount() {
        return inventoryCount;
    }

    public static void setInventoryCount(int inventoryCount) {
        Inventory.inventoryCount = inventoryCount;
        mySleep(50);

    }




    public synchronized static void setStaticThread(int Count, String name, String description) {
Inventory.inventoryCount = Count;
        mySleep(20);
Inventory.name = name;
        mySleep(20);
Inventory.description = description;
        mySleep(20);


    }


    public synchronized static String getStaticThread() {
        mySleep(20);

        return "inventory count: " + Inventory.inventoryCount + " name: " + Inventory.name + " description: "+Inventory.description;
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
