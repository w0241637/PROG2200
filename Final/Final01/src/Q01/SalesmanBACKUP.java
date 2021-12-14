////1) [40%] Write a Java program with 20 threads that access common inventory data.
//// The inventory data consists of 5 different items for sale,
//// each with their own inventory count (at least 20 of each item), a name, and a description.
//
//// Each salesman thread loops selling one inventory item at a time (randomly selected of the different items).
//// It takes 100 milliseconds to process the selling of one inventory item (use a sleep to simulate the activity of a sale).
//// Other threads must wait until a sale completes before their sale can proceed, but only if it is the same item type.
//// The other item types may sell without delay.
//// Use an inventory class that maintains the inventory data and ensures items are sold exactly once and provides the sale method which simulates the delay.
//// Keep track of the total number of sales and sales that each thread has made...that is, don't sell more inventory than you have!
//// Tweak thread delays as you wish, provided there is a mix of selling threads,
//// but a change in delays (i.e. new delays) must not break the controlled sales of the salesman threads operating on the inventory data.
////
////
////        Logs show the following:
////        After the inventory is sold, [10%] each salesman thread prints its own thread-safe sales
////        (label this properly, so it can be seen which salesman sold what).
////
////        [30%] Sales of different types don’t wait for each other and items of the same type are thread-safe.
////        Only sales of the same type must wait.
////
////        [10%] If a salesman asks for several items, then the salesman can get several items (per remaining inventory).
////        (for example…   item_count = getItems(3, pens) to try to get 3 pens, but maybe only get 2)
//
//
//
//package Q01;
//
//import java.util.ArrayList;
//import java.util.Random;
//
//public class SalesmanBACKUP extends Thread{
//
//    public String name;
//    ArrayList <Inventory> soldItems;
//
//    Salesman(String name){
//        this.name = name;
//    }
//
//    public void run(){
//
//        try {
//            for (int i = 0; i < 1000000; i++) {
//                Thread.sleep(100);
//
//
//                System.out.println(name + " start (");
//                sell();
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//
//    } //run
//
//
//
//
//
//    public synchronized void sell() throws InterruptedException {
//        int sellAmount = randomRange(1,4);
//
//        switch (randomRange(1,2)){
//            case 1:
//                switchFuncPens();
////        System.out.println(name+"\t 1");
////        System.out.println(name+"\t pens in inventory before transaction: "+ Inventory.getPenCount());
////        if(Inventory.getPenCount() < sellAmount){
////            System.out.println(name+"\t customer wants more then salesman can provide. Customer demand: "+sellAmount);
////        } else {
////            System.out.println(name+"\t Customer demand: "+sellAmount);
////            Inventory.setPenCount(Inventory.getPenCount() - sellAmount);
////        }
////        System.out.println(name+"\t pens in inventory after transaction: "+Inventory.getPenCount());
//                break;
//
//            case 2:
//                System.out.println(name+"\t 2");
//
//                System.out.println(name+"\t pencils in inventory before transaction: "+ Inventory.getPencilCount());
//                if(Inventory.getPencilCount() < sellAmount){
//                    System.out.println(name+"\t customer wants more then salesman can provide. Customer demand: "+sellAmount);
//                } else {
//                    System.out.println(name+"\t Customer demand: "+sellAmount);
//                    Inventory.setPencilCount(Inventory.getPencilCount() - sellAmount);
//                }
//                System.out.println(name+"\t pencils in inventory after transaction: "+Inventory.getPencilCount());
//                break;
//
//            case 3:
//                System.out.println(name+"\t 3");
//
//                System.out.println(name+"\t erasers in inventory before transaction: "+ Inventory.getEraserCount());
//                if(Inventory.getEraserCount() < sellAmount){
//                    System.out.println(name+"\t customer wants more then salesman can provide. Customer demand: "+sellAmount);
//                } else {
//                    System.out.println(name+"\t Customer demand: "+sellAmount);
//                    Inventory.setEraserCount(Inventory.getEraserCount() - sellAmount);
//                }
//                System.out.println(name+"\t erasers in inventory after transaction: "+Inventory.getEraserCount());
//                break;
//
//            case 4:
//                System.out.println(name+"\t 4");
//
//                System.out.println(name+"\t crayons in inventory before transaction: "+ Inventory.getCrayonCount());
//                if(Inventory.getCrayonCount() < sellAmount){
//                    System.out.println(name+"\t customer wants more then salesman can provide. Customer demand: "+sellAmount);
//                } else {
//                    System.out.println(name+"\t Customer demand: "+sellAmount);
//                    Inventory.setCrayonCount(Inventory.getCrayonCount() - sellAmount);
//                }
//                System.out.println(name+"\t crayons in inventory after transaction: "+Inventory.getCrayonCount());
//                break;
//
//            case 5:
//                System.out.println(name+"\t 5");
//
//                System.out.println(name+"\t markers in inventory before transaction: "+ Inventory.getMarkerCount());
//                if(Inventory.getMarkerCount() < sellAmount){
//                    System.out.println(name+"\t customer wants more then salesman can provide. Customer demand: "+sellAmount);
//                } else {
//                    System.out.println(name+"\t Customer demand: "+sellAmount);
//                    Inventory.setMarkerCount(Inventory.getMarkerCount() - sellAmount);
//                }
//                System.out.println(name+"\t markers in inventory after transaction: "+Inventory.getMarkerCount());
//                break;
//        }
//
//
//
//
////if(Inventory.getPenCount() < sellAmount){
//////    this.stopThis();
////    System.out.println("customer wants more then salesman can provide");
////} else {
////    Inventory.setPenCount(Inventory.getPenCount() - sellAmount);
////}
//
//
//
//
//
//
//
//
////Inventory.setPenCount(Inventory.getPenCount() - sellAmount);
////    Pens.setInventory(Pens.getInventory() - sellAmount);
//
////    System.out.println("pens in Pens after transaction: "+Pens.getInventory());
//
////    if (Inventory.getPenCount() <= 0){
//        if (Inventory.getCombinedInventory() <= 0){
//
//            this.stopThis();
//        }
//
//        System.out.println(")end " + name);
//    }
//
//    public synchronized void switchFuncPens(){
//
//        int sellAmount = randomRange(1,4);
//
//        System.out.println(name+"\t 1");
//        System.out.println(name+"\t pens in inventory before transaction: "+ Inventory.getPenCount());
//        if(Inventory.getPenCount() < sellAmount){
//            System.out.println(name+"\t customer wants more then salesman can provide. Customer demand: "+sellAmount);
//        } else {
//            System.out.println(name+"\t Customer demand: "+sellAmount);
//            Inventory.setPenCount(Inventory.getPenCount() - sellAmount);
//        }
//        System.out.println(name+"\t pens in inventory after transaction: "+Inventory.getPenCount());
//    }
//
//
//
//
//    public void stopThis(){
////    this.interrupt();
//        this.stop();
//    }
//
//    public int randomRange(int min, int max) {
//        Random random = new Random();
//        return random.nextInt(max - min) + min;
//    }
//
//
//
//}
