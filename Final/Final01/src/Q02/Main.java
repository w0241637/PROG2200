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

public class Main {

        public static void main(String[] args) throws InterruptedException {


                Inventory.reloadAll(20);

                Worker w1 = new Worker();
                w1.start();

                Truck t1 = new Truck();
                t1.start();

                Salesman s1 = new Salesman("Fernie");
                s1.start();

                Salesman s2 = new Salesman("Lucy");
                s2.start();

                Salesman s3 = new Salesman("Tim");
                s3.start();

                Salesman s4 = new Salesman("Bob");
                s4.start();

                Salesman s5 = new Salesman("Ted");
                s5.start();

                Salesman s6 = new Salesman("Frank");
                s6.start();

                Salesman s7 = new Salesman("Lisa");
                s7.start();

                Salesman s8 = new Salesman("Jane");
                s8.start();

                Salesman s9 = new Salesman("Carolyn");
                s9.start();

                Salesman s10 = new Salesman("Tom Jones");
                s10.start();

                Salesman s11 = new Salesman("John");
                s11.start();

                Salesman s12 = new Salesman("Sam");
                s12.start();

                Salesman s13 = new Salesman("Jordan");
                s13.start();

                Salesman s14 = new Salesman("Paul");
                s14.start();

                Salesman s15 = new Salesman("Simon");
                s15.start();

                Salesman s16 = new Salesman("Frank");
                s16.start();

                Salesman s17 = new Salesman("Art");
                s17.start();

                Salesman s18 = new Salesman("Garfunkel");
                s18.start();

                Salesman s19 = new Salesman("Fernie's evil twin");
                s19.start();

                Salesman s20 = new Salesman("Anna");
                s20.start();




//                if (Pens.getInventory() <= 0){
//                        s1.stopThis();
//                }



//                for (int i = 0; i < 100; i++) {
//                        try {
//                                Thread.sleep(10);
////                                System.out.println(Dorthy.getStaticThreadperson());
//                                //String s = Data.getStaticThreadperson();
//                        } catch (InterruptedException e1) {
//                                // TODO Auto-generated catch block
//                                e1.printStackTrace();
//                        }
//
//                        //System.out.println(thread.personChange.name + " " + thread.personChange.cell_number + " " + thread.personChange.phone_number + " " + thread.personChange.email);
//                }

        }
}
