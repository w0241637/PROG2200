package org.lwjglb.game;

import org.lwjglb.engine.items.Car;
import org.lwjglb.engine.items.Motorcycle;
import org.lwjglb.engine.items.Plane;
import org.lwjglb.engine.items.Vehicle02;

public class ObjectDB01 {

    /**
     * enhance, as in initialize objects to be stored in Database
     * @param args
     */
    public static void main(String[] args) {

        com.objectdb.Enhancer.enhance("org.lwjglb.engine.items.Vehicle02");
        com.objectdb.Enhancer.enhance("org.lwjglb.engine.items.Car");
        com.objectdb.Enhancer.enhance("org.lwjglb.engine.items.Plane");
        com.objectdb.Enhancer.enhance("org.lwjglb.engine.items.Motorcycle");

        ManageJDO01 g = new ManageJDO01("Shapes01.odb");

        g.dumpObjects(Vehicle02.class);
        g.emptyDB(Vehicle02.class);
        g.close();
        System.out.println("get here?");
    }
}
