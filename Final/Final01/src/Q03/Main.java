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

public class Main {

        public static void main(String[] args) throws InterruptedException {


                Inventory.reloadAll(20);


                Salesman s1 = new Salesman("Fernie");
                s1.start();

                Salesman s2 = new Salesman("Lucy");
                s2.start();

                Salesman s3 = new Salesman("Tim");
                s3.start();
//
//                Salesman s4 = new Salesman("Bob");
//                s4.start();
//
//                Salesman s5 = new Salesman("Ted");
//                s5.start();
//
//                Salesman s6 = new Salesman("Frank");
//                s6.start();
//
//                Salesman s7 = new Salesman("Lisa");
//                s7.start();
//
//                Salesman s8 = new Salesman("Jane");
//                s8.start();
//
//                Salesman s9 = new Salesman("Carolyn");
//                s9.start();
//
//                Salesman s10 = new Salesman("Tom Jones");
//                s10.start();
//
//                Salesman s11 = new Salesman("John");
//                s11.start();
//
//                Salesman s12 = new Salesman("Sam");
//                s12.start();
//
//                Salesman s13 = new Salesman("Jordan");
//                s13.start();
//
//                Salesman s14 = new Salesman("Paul");
//                s14.start();
//
//                Salesman s15 = new Salesman("Simon");
//                s15.start();
//
//                Salesman s16 = new Salesman("Judy");
//                s16.start();
//
//                Salesman s17 = new Salesman("Art");
//                s17.start();
//
//                Salesman s18 = new Salesman("Garfunkel");
//                s18.start();
//
//                Salesman s19 = new Salesman("Fernie's evil twin");
//                s19.start();
//
//                Salesman s20 = new Salesman("Anna");
//                s20.start();


        }
}
