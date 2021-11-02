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

public class Main {



    public static void main(String[] args) {

        Inventory.setInventoryCount(20);

        int x = Inventory.inventoryCount;

        thread customer01 = new thread(x,"Tim","abc",0);
        customer01.start();

        thread customer02 = new thread(x,"Fernie","123",0);
        customer02.start();

        thread customer03 = new thread(x,"Lucy","doe ray mee",0);
        customer03.start();

        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(10);
                System.out.println(Inventory.getStaticThread());


                if(Inventory.inventoryCount <= 0){
                        customer01.stopThis();
                        customer02.stopThis();
                        customer03.stopThis();
                    System.out.println( "pens in Tims pockets "+customer01.pensInPocket);
                    System.out.println( "pens in Ferines pockets "+customer02.pensInPocket);
                    System.out.println( "pens in Lucys pockets "+customer03.pensInPocket);
                }



                //String s = Data.getStaticThreadperson();
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }


    }


}

//                if(Inventory.inventoryCount <= 0){
//                        customer01.interrupt();
//                        customer02.interrupt();
//                        customer03.interrupt();
//                        }
