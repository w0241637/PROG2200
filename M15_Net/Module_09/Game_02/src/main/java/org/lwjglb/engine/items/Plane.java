package org.lwjglb.engine.items;

import org.joml.Quaternionf;
import org.lwjglb.engine.graph.Mesh;
import org.lwjglb.engine.loaders.assimp.StaticMeshesLoader;
import org.lwjglb.game.GameGUI;

import javax.persistence.Entity;
import java.util.Random;

/**
 * Plane class extends Vehicle02
 */
@Entity
public class Plane extends Vehicle02 {

    public boolean doesNotFall = true;

    public boolean mostRecent = true;

    public String objType = "Plane";


    /**
     * float range for random parameters
     * @param min
     * @param max
     * @return
     */
    public static float nextFloatRange(float min, float max) {
        return (new Random().nextFloat() * (max - min)) + min;
    }

    /**
     * to determine if this is most recent object added to screen
     */
    public void setAsNewest(){
        this.mostRecent = true;
    }

    /**
     * not most recent
     */
    public void setAsOld(){
        this.mostRecent = false;
    }

    public void setTestingObject(){
        this.testingObject = true;
    }

    /**
     * empty constructor
     */
    public Plane(){}

    /**
     * constructor with default position and velocity
     * @param addMesh
     */
    public Plane(Mesh[] addMesh) {
        super(addMesh);
        setPosition(nextFloatRange(10.00f, -10.0f), 8.f, nextFloatRange(10.00f, -10.0f));
        setVelocity(0.006f * nextFloatRange(1f, -1f), 0.000001f, 0.006f * nextFloatRange(1f, -1f));
    }

    /**
     * getter
     * @return
     */
    public String getObjType() {
        return objType;
    }

    /**
     * setter for mesh
     */
    public void setDefaultMesh() {
        try {
            this.setMeshes(StaticMeshesLoader.load("src/main/resources/models/russ/toyPlane.obj", "src/main/resources/models/russ"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * setter for rotation and scale
     */
    public void setRotateAndScale(){
        java.util.Random r = new java.util.Random();
        this.setScale(0.30f);  //house needs to be shrunk
        this.setRotationVel(new Quaternionf(0.06f * r.nextFloat(), 0.08f * r.nextFloat(), 0.09f * r.nextFloat(), 0.0f));    }

}
