package org.lwjglb.game;

//might want java.util.List
import java.util.List;
//import org.hsqldb.lib.List;
import org.lwjglb.engine.items.Vehicle02;

public interface Vehicle_Interface{

    List<Vehicle02> findAll();

    int save(Vehicle02 vehicle02);

}
