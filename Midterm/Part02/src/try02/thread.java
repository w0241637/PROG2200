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

public class thread extends Thread{

    public int inventoryCount;
    public String name;
    public String description;
    public int pensInPocket;


public thread(int inventoryCount, String name, String description, int pensInPocket){
    this.inventoryCount = inventoryCount;
    this.name = name;
    this.description = description;
    this.pensInPocket = pensInPocket;

}

    @Override
    public void run() {
        System.out.println("Start name = " + this.name);
        try {
            for (int i = 0; i < 1000000; i++) {
//                Thread.sleep(30);
                int x = Inventory.inventoryCount;

                Inventory.setStaticThread(--x, this.name, this.description);
                //System.out.println("Loop thread " + i + " "+ this.name);
                ++this.pensInPocket;
                Thread.sleep(30);


            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void stopThis(){
//    this.interrupt();
    this.stop();
    }


}
