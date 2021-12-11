package org.lwjglb.engine.items;

import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.lwjglb.engine.graph.Mesh;
import org.lwjglb.engine.loaders.assimp.StaticMeshesLoader;
import org.lwjglb.game.GameGUI;

import javax.persistence.Entity;
import java.util.Random;

@Entity
public class Motorcycle extends Vehicle02 {

    public String objType = "Motorcycle";

    public boolean mostRecent = true;

    private final double threshold = 1.1;


    /**
     * empty constructor
     */
    public Motorcycle(){}

    /**
     * float range for random
     * @param min
     * @param max
     * @return
     */
    public static float nextFloatRange(float min, float max) {
        return (new Random().nextFloat() * (max - min)) + min;
    }

    /**
     * setter for most recent object
     */
    public void setAsNewest(){
        this.mostRecent = true;
    }

    /**
     * setter for not most recent
     */
    public void setAsOld(){
        this.mostRecent = false;
    }

    public void setTestingObject(){
        this.testingObject = true;
    }


    /**
     * constructor with default position and velocity
     * @param addMesh
     */
    public Motorcycle(Mesh[] addMesh) {
        super(addMesh);
        setPosition(nextFloatRange(10.00f, -10.0f), -15.000f, nextFloatRange(10.00f, -10.0f));
        setVelocity(0.06f * nextFloatRange(1f, -1f), 0.000001f, 0.06f * nextFloatRange(1f, -1f));
    }

    /**
     * setter default mesh
     */
    public void setDefaultMesh() {
        try {
            this.setMeshes(StaticMeshesLoader.load("src/main/resources/models/russ/Motorcycle.obj", "src/main/resources/models/russ"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * getter
     * @return objType
     */
    public String getObjType() {
        return objType;
    }

    /**
     * collide with other objects
     * @param that
     * @return
     */
    public boolean DoesItCollide(GameItem that) {

        // Don't collide with itself
        if (this!=that) {

            Vector3f pos1 = this.position;
            Vector3f pos2 = that.position;

            if (pos1.distance(pos2) < threshold) {
                System.err.print(" this.velocity=" + this.velocity.toString());
                this.velocity.x = 0;
                this.velocity.y = 0;
                this.velocity.z = 0;

//                this.velocity.negate();
                that.velocity.negate();
                System.err.println("  ###  this.velocity=" + this.velocity.toString());
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * setter rotation and scale
     */
    public void setRotateAndScale(){
        java.util.Random r = new java.util.Random();
        this.setScale(0.30f);
        this.setRotationVel(new Quaternionf(0.00f, 0.0f, 0.0f , 0.0f));
    }

}
