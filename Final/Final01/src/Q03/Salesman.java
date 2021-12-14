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



package Q03;

import java.util.Random;

public class Salesman extends Thread{

    public int sales;
    public String name;
//    ArrayList <Inventory> soldItems;

    Salesman(String name){
        this.name = name;
        this.sales = sales;
    }


public void run(){

    try {
        for (int i = 0; i < 1000000; i++) {
            Thread.sleep(100);
            System.out.println(name + " start (");
        sell();
            System.out.println(")end " + name);
        }
    } catch (InterruptedException e) {
        e.printStackTrace();
    }


} //run




public void sell() throws InterruptedException {
//    int sellAmount = randomRange(1,4);

switch (randomRange(1,3)){

    case 1:
                System.out.println(name+"\t pens in inventory before transaction: "+ Inventory.getPenCount());
        int pensSold = switchFuncPens();
        System.out.println(name+"\t pens in inventory after transaction: "+ Inventory.getPenCount());
        this.sales = this.sales+pensSold;
        break;

    case 2:
        System.out.println(name+"\t pencils in inventory before transaction: "+ Inventory.getPencilCount());
        int pencilsSold = switchFuncPencils();
        System.out.println(name+"\t pencils in inventory after transaction: "+ Inventory.getPencilCount());
        this.sales = this.sales+pencilsSold;
        break;

    case 3:
        System.out.println(name+"\t erasers in inventory before transaction: "+ Inventory.getEraserCount());
        int ErasersSold = switchFuncErasers();
        System.out.println(name+"\t erasers in inventory after transaction: "+ Inventory.getEraserCount());
        this.sales = this.sales+ErasersSold;
        break;

    case 4:
        System.out.println(name+"\t crayons in inventory before transaction: "+ Inventory.getCrayonCount());
        int crayonsSold = switchFuncCrayon();
        System.out.println(name+"\t crayons in inventory after transaction: "+ Inventory.getCrayonCount());
        this.sales = this.sales+crayonsSold;
        break;

    case 5:

        System.out.println(name+"\t markers in inventory before transaction: "+ Inventory.getMarkerCount());
        int markersSold = switchFuncMarker();

        System.out.println(name+"\t markers in inventory after transaction: "+ Inventory.getMarkerCount());
        this.sales = this.sales+markersSold;
        break;
}

        if (Inventory.getCombinedInventory() <= 0){
            sleep(2222);
            System.out.println(this.name +" sold "+this.sales);
            this.stopThis();
    }

}
 //not synchronized with other threads
public static int switchFuncPens(){
    System.out.println("1 *** start");

    int sellAmount = randomRange(1,4);

    System.out.println("\t pens in inventory before transaction: "+ Inventory.getPenCount());

    if(Inventory.getPenCount() < sellAmount){
        System.out.println("\t customer wants more then salesman can provide. Customer demand: "+sellAmount);
    } else {
        System.out.println("\t Customer demand: "+sellAmount);
        Inventory.setPenCount(Inventory.getPenCount() - sellAmount);
        System.out.println("1 *** end");
        return sellAmount;
    }
    System.out.println("1 *** end");
    return 0;
}
//not pointing to a single spot in ram
    public synchronized int switchFuncPencils(){

        System.out.println("2 start");

        int sellAmount = randomRange(1,4);
        if(Inventory.getPencilCount() < sellAmount){
            System.out.println("\t customer wants more then salesman can provide. Customer demand: "+sellAmount);
        } else {
            System.out.println("\t Customer demand: "+sellAmount);
            Inventory.setPencilCount(Inventory.getPencilCount() - sellAmount);
            System.out.println("2 end");
            return sellAmount;

        }
        System.out.println("2 end");
        return 0;
    }

    public static synchronized int switchFuncErasers(){
        System.out.println("3 start");

        int sellAmount = randomRange(1,4);

        if(Inventory.getEraserCount() < sellAmount){
            System.out.println("\t customer wants more then salesman can provide. Customer demand: "+sellAmount);
        } else {
            System.out.println("\t Customer demand: "+sellAmount);
            Inventory.setEraserCount(Inventory.getEraserCount() - sellAmount);
            System.out.println("3 end");
            return sellAmount;

        }
        System.out.println("3 end");
        return 0;
    }

    public static synchronized int switchFuncCrayon(){
        System.out.println("4 start");

        int sellAmount = randomRange(1,4);

        if(Inventory.getCrayonCount() < sellAmount){
            System.out.println("\t customer wants more then salesman can provide. Customer demand: "+sellAmount);
        } else {
            System.out.println("\t Customer demand: "+sellAmount);
            Inventory.setCrayonCount(Inventory.getCrayonCount() - sellAmount);
            System.out.println("4 end");
            return sellAmount;

        }
        System.out.println("4 end");
        return 0;
    }

    public static synchronized int switchFuncMarker(){
        System.out.println("5 start");

        int sellAmount = randomRange(1,4);

        if(Inventory.getMarkerCount() < sellAmount){
            System.out.println("\t customer wants more then salesman can provide. Customer demand: "+sellAmount);
        } else {
            System.out.println("\t Customer demand: "+sellAmount);
            Inventory.setMarkerCount(Inventory.getMarkerCount() - sellAmount);
            System.out.println("5 end");
            return sellAmount;

        }
        System.out.println("5 end");
        return 0;
    }


    public void stopThis(){
//    this.interrupt();
        this.stop();
    }

    public static void sleepThis() throws InterruptedException {
//        this.wait(222);
//        this.wait();
    }

    public static void mySleep(int d) {
        try {
            Thread.sleep(d);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static int randomRange(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }



}
