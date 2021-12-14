package Q02;

public class Worker extends Thread{

    public void run(){

        for (int i = 0; i < 1000000; i++) {
//                Thread.sleep(1000);
            mySleep(1000);
//                System.out.println("Worker start (");

loadTruck();

//                System.out.println(")end Worker");
        }
    } //run{




    public static void loadTruck(){

        int ordernum = Truck.getOrderNum();

        switch(ordernum) {

            case 1:
                mySleep(100);
                Inventory.restockPens(20);
                System.out.println("WORKER restock PENS");

            case 2:
                mySleep(200);

                Inventory.restockPencils(200);
                System.out.println("WORKER restock PENCILS");


            case 3:
                mySleep(300);

                Inventory.restockErasers(200);
                System.out.println("WORKER restock ERASERS");


            case 4:
                mySleep(400);

                Inventory.restockCrayons(200);

                System.out.println("WORKER restock CRAYONS");


            case 5:
                mySleep(500);

                Inventory.restockMarkers(200);
                System.out.println("WORKER restock MARKERS");

            }


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



