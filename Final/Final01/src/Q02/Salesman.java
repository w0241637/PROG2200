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
//            System.out.println(name + " start (");
        sell();
//            System.out.println(")end " + name);
        }
    } catch (InterruptedException e) {
        e.printStackTrace();
    }


} //run






    public void sell() throws InterruptedException {
//    int sellAmount = randomRange(1,4);

switch (randomRange(1,6)){

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
//        System.out.println(name+"\t 5");

        System.out.println(name+"\t markers in inventory before transaction: "+ Inventory.getMarkerCount());
        int markersSold = switchFuncMarker();
//        if(Inventory.getMarkerCount() < sellAmount){
//            System.out.println(name+"\t customer wants more then salesman can provide. Customer demand: "+sellAmount);
//        } else {
//            System.out.println(name+"\t Customer demand: "+sellAmount);
//            Inventory.setMarkerCount(Inventory.getMarkerCount() - sellAmount);
//        }
        System.out.println(name+"\t markers in inventory after transaction: "+ Inventory.getMarkerCount());
        this.sales = this.sales+markersSold;
        break;
}



// out of stock
        if (Inventory.getCombinedInventory() <= 0){
            sleep(2222);
            System.out.println(this.name +" sold "+this.sales);
//            this.stopThis();
    }

}


public static synchronized int switchFuncPens(){
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

    public static synchronized int switchFuncPencils(){

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

//        System.out.println("\t 5");
//    System.out.println("\t pens in inventory before transaction: "+ Inventory.getPenCount());
        if(Inventory.getMarkerCount() < sellAmount){
            System.out.println("\t customer wants more then salesman can provide. Customer demand: "+sellAmount);
        } else {
            System.out.println("\t Customer demand: "+sellAmount);
            Inventory.setMarkerCount(Inventory.getMarkerCount() - sellAmount);
            System.out.println("5 end");
            return sellAmount;

        }
//    System.out.println("\t pens in inventory after transaction: "+Inventory.getPenCount());
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
