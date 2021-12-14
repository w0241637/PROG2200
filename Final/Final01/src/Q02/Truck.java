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

public class Truck extends Thread{

    private static int orderNum;

    public static int getOrderNum() {
        return orderNum;
    }



    public void run(){

        try {
            for (int i = 0; i < 1000000; i++) {
                Thread.sleep(100);
                System.out.println("Truck start (");


                mySleep(5000);

                orderNum = reStock();




                System.out.println(")end Truck");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    } //run

    public static int reStock(){

        if (Inventory.getPenCount() < 3 ){

//            Worker w1 = new Worker();
//            w1.start();
//            Inventory.restockPens(20);
//            System.out.println("Truck restock PENS");
            return 1;
        }
        if (Inventory.getPencilCount() < 3 ){
            return 2;

        }
        if (Inventory.getEraserCount() < 3 ){
            return 3;

        }
        if (Inventory.getCrayonCount() < 3 ){return 4;

        }
        if (Inventory.getMarkerCount() < 3 ){return 5;
        }
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



}
