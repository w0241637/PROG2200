//1) 4) [60%] Export your log output to excel and draw histograms.
// Use the working version of question 1 (snippets show real log was used, showing where the log writing is done).
// Generate these histograms (x-axis and y-axis doesn’t really matter, that's a guide, what matters is that your log data can be plotted):
//a)	Save/show the run’s raw data outputted from the program (so I can verify your diagram).
//b)	[20%] Total sales of each Salesman Histogram: each salesman (x-axis) and number of total sold items (y-axis).
//c)	[20%] Number of Items sold by a given Salesman Histogram: 1 salesman (x-axis) and number of items of each type sold by that salesman (y-axis).
//d)	[20%] 1 Item per all Salesman Histogram: 1 item type (x-axis) and number sold by each salesman (y-axis).

package Q04;

import java.util.Random;

public class Salesman extends Thread{

    public int totalSales, penSales, pencilSales, eraserSales, crayonSales, markerSales;
    public String name;
//    ArrayList <Inventory> soldItems;

    Salesman(String name){
        this.name = name;
        this.totalSales = totalSales;
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

switch (randomRange(1,6)){

    case 1:
                System.out.println(name+"\t pens in inventory before transaction: "+ Inventory.getPenCount());
        int pensSold = switchFuncPens();
        System.out.println(name+"\t pens in inventory after transaction: "+ Inventory.getPenCount());
        this.totalSales = this.totalSales +pensSold;
        this.penSales = this.penSales + pensSold;
        break;

    case 2:
        System.out.println(name+"\t pencils in inventory before transaction: "+ Inventory.getPencilCount());
        int pencilsSold = switchFuncPencils();
        System.out.println(name+"\t pencils in inventory after transaction: "+ Inventory.getPencilCount());
        this.totalSales = this.totalSales +pencilsSold;
        this.pencilSales = this.pencilSales + pencilsSold;

        break;

    case 3:
        System.out.println(name+"\t erasers in inventory before transaction: "+ Inventory.getEraserCount());
        int ErasersSold = switchFuncErasers();
        System.out.println(name+"\t erasers in inventory after transaction: "+ Inventory.getEraserCount());
        this.totalSales = this.totalSales +ErasersSold;
        this.eraserSales = this.eraserSales + ErasersSold;

        break;

    case 4:
        System.out.println(name+"\t crayons in inventory before transaction: "+ Inventory.getCrayonCount());
        int crayonsSold = switchFuncCrayon();
        System.out.println(name+"\t crayons in inventory after transaction: "+ Inventory.getCrayonCount());
        this.totalSales = this.totalSales +crayonsSold;
        this.crayonSales = this.crayonSales + crayonsSold;

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
        this.totalSales = this.totalSales +markersSold;
        this.markerSales = this.markerSales + markersSold;

        break;
}




//if(Inventory.getPenCount() < sellAmount){
////    this.stopThis();
//    System.out.println("customer wants more then salesman can provide");
//} else {
//    Inventory.setPenCount(Inventory.getPenCount() - sellAmount);
//}








//Inventory.setPenCount(Inventory.getPenCount() - sellAmount);
//    Pens.setInventory(Pens.getInventory() - sellAmount);

//    System.out.println("pens in Pens after transaction: "+Pens.getInventory());

//    if (Inventory.getPenCount() <= 0){
        if (Inventory.getCombinedInventory() <= 0){
            sleep(2222);
            System.out.println(this.name +" sold "+this.totalSales);
            MyLog.writeString(this.name+","+this.totalSales+","+this.penSales+","+this.pencilSales+","+this.eraserSales+","+this.crayonSales+","+this.markerSales);
//            sleep(2222);
//            MyLog.writeString(this.name+","+this.penSales+","+this.pencilSales+","+this.eraserSales+","+this.crayonSales+","+this.markerSales);
            this.stopThis();
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
