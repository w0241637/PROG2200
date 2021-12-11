package org.lwjglb.engine.items;

import org.joml.Quaternionf;
import org.lwjglb.engine.graph.Mesh;
import org.lwjglb.engine.loaders.assimp.StaticMeshesLoader;

import javax.persistence.Entity;
import java.util.Random;

/**
 * Car class extends Vehicle02 class
 */
@Entity
public class Car extends Vehicle02 {

    public boolean doesNotFall = true;

    public boolean mostRecent = true;

    public String objType = "Car";


    /**
     * set most recent
     */
    public void setAsNewest(){
        this.mostRecent = true;
    }

    /**
     * set as not most recent
     */
    public void setAsOld(){
        this.mostRecent = false;
    }

    /**
     * for random range
     * @param min
     * @param max
     * @return
     */
    public static float nextFloatRange(float min, float max) {
        return (new Random().nextFloat() * (max - min)) + min;
    }

    /**
     * this type of object
     * @return object type
     */
    public String getObjType() {
        return objType;
    }


    /**
     * empty constructor
     */
    public Car(){}

    /**
     * constructor with default position and velocity
     * @param addMesh
     */
    public Car(Mesh[] addMesh){
        super(addMesh);
        setPosition(nextFloatRange(10.00f, -10.0f), -15.000f, nextFloatRange(10.00f, -10.0f));
        setVelocity(0.006f * nextFloatRange(1f, -1f), 0.000001f, 0.006f * nextFloatRange(1f, -1f));
        try {
            setMeshes(StaticMeshesLoader.load("src/main/resources/models/russ/Chevrolet_Camaro_SS_Low.obj", "src/main/resources/models/russ"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * set default mesh
     */
    public void setDefaultMesh() {
        try {
            Mesh[] mesh = StaticMeshesLoader.load("src/main/resources/models/russ/Chevrolet_Camaro_SS_Low.obj", "src/main/resources/models/russ");
            this.setMeshes(mesh);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * set rotation and scale
     */
    public void setRotateAndScale(){
        java.util.Random r = new java.util.Random();
        this.setScale(0.10f);
        this.setRotationVel(new Quaternionf(0.00f, 0.5f, 0.0f , 0.0f));    }

    /**
     * prevents car from moving up
     */
    public void up() {
        System.out.println("Cars can't fly!");
    }

}
