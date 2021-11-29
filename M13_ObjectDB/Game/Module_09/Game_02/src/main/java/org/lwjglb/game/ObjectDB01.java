package org.lwjglb.game;

import org.lwjglb.engine.items.Car;
import org.lwjglb.engine.items.Motorcycle;
import org.lwjglb.engine.items.Plane;
import org.lwjglb.engine.items.Vehicle02;

public class ObjectDB01 {

    public static void main(String[] args) {

        com.objectdb.Enhancer.enhance("org.lwjglb.engine.items.Vehicle02");
        com.objectdb.Enhancer.enhance("org.lwjglb.engine.items.Car");
        com.objectdb.Enhancer.enhance("org.lwjglb.engine.items.Plane");
        com.objectdb.Enhancer.enhance("org.lwjglb.engine.items.Motorcycle");

//        com.objectdb.Enhancer.enhance("org.lwjglb.engine.game.objectdb_jdo_05.Shape");

        ManageJDO01 g = new ManageJDO01("Shapes01.odb");


//        Vehicle02 v1 = new Vehicle02("Vehicle02",1,2.0f,3.0f,4.0f,5.0f,6.0f);
//        g.saveNew(v1);

//        Car c1 = new Car("Car",1,2.0f,3.0f,4.0f,5.0f,6.0f);
//        g.saveNew(c1);
//
//        Plane p1 = new Plane("Plane",1,2.0f,3.0f,4.0f,5.0f,6.0f);
//        g.saveNew(p1);
//
//        Motorcycle m1 = new Motorcycle("Plane",1,2.0f,3.0f,4.0f,5.0f,6.0f);
//        g.saveNew(m1);

        g.dumpObjects(Vehicle02.class);
        g.emptyDB(Vehicle02.class);
        g.close();
        System.out.println("get here?");
    }
}
