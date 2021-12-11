package org.lwjglb.engine.items;

import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.lwjglb.engine.graph.InstancedMesh;
import org.lwjglb.engine.graph.Mesh;
import org.lwjglb.engine.items.GameItem;
import org.lwjglb.engine.loaders.assimp.StaticMeshesLoader;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
public class Vehicle02 extends GameItem implements Serializable {

    public boolean testingObject = false;

    public boolean doesNotFall = true;

    public boolean mostRecent = true;

    public String objType = "Vehicle02";

    public float objX,objY,objZ,objDx,objDy,objDz;

    /**
     * empty constructor
     */
    public Vehicle02() {
    }

    /**
     * constructor for SQL Databases
     * @param objType
     * @param x
     * @param y
     * @param z
     * @param dx
     * @param dy
     * @param dz
     */
    public Vehicle02(String objType, float x, float y, float z, float dx, float dy, float dz) {
    }

    /**
     * Constructor
     * @param addMesh
     */
    public Vehicle02(Mesh[] addMesh) {
        super(addMesh);
        setPosition(nextFloatRange(10.00f, -10.0f), 8.f, nextFloatRange(10.00f, -10.0f));
        setVelocity(0.006f * nextFloatRange(1f, -1f), 0.000001f, 0.006f * nextFloatRange(1f, -1f));
    }

    /**
     * getter
     * @return String of object type
     */
    public String getObjType() {
        return objType;
    }

    /**
     * getter
     * @return Vector3f position
     */
    public Vector3f getPosition() {
        return position;
    }

    /**
     * distinguishes the most recent object added to the screen from the others
     */
    public void setAsNewest(){
        this.mostRecent = true;
    }

    /**
     * distinguishes the most recent object added to the screen from the others
     */
    public void setAsOld(){
        this.mostRecent = false;
    }

    /**
     *
     * @param min
     * @param max
     * @return random float between two parameters
     */
    public static float nextFloatRange(float min, float max) {
        return (new Random().nextFloat() * (max - min)) + min;
    }

    /**
     * sets class specific mesh
     */
    public void setDefaultMesh() {
                try {
            this.setMeshes(StaticMeshesLoader.load("src/main/resources/models/cube.obj", "src/main/resources/models"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * sets class specific rotation and scale
     */
    public void setRotateAndScale(){
    java.util.Random r = new java.util.Random();
    this.setRotationVel(new Quaternionf(0.06f * r.nextFloat(), 0.08f * r.nextFloat(), 0.09f * r.nextFloat(), 0.0f));
}



    private final double threshold = 1.1;

    /**
     * detects if two objects are within a specified threshold
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
//                this.rebound();
//                that.rebound();
//                this.velocity.negate();
//                that.velocity.negate();
                System.err.println("  ###  this.velocity=" + this.velocity.toString());
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * start the object thread
     */
    public void run() {
        System.out.println("Running Shape...");
        boolean isStationary = false;

        while (!isStationary) {
            if (!doesNotFall){gravity();}
            this.move();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * increase velocity
     */
    public void faster() {
        this.velocity.x = this.velocity.x * 1.1f;
        this.velocity.y = this.velocity.y * 1.1f;
        this.velocity.z = this.velocity.z * 1.1f;
    }

    /**
     * lower velocity
     */
    public void slower() {
        this.velocity.x = this.velocity.x * 0.9f;
        this.velocity.y = this.velocity.y * 0.9f;
        this.velocity.z = this.velocity.z * 0.9f;
    }

    /**
     * increase y position
     */
    public void up() {
        this.position.y = this.position.y + 0.1f;
    }

    /**
     * decrease y position
     */
    public void down() {
        this.position.y = this.position.y - 0.2f;
    }

    /**
     * increase x position
     */
    public void left() {
        this.position.x = this.position.x + 0.2f;
    }

    /**
     * decrease x position
     */
    public void right() {
        this.position.x = this.position.x - 0.2f;
    }

    /**
     * @return objx
     */
    public float getObjX() {
        return objX;
    }

    /**
     * setter
     * @param objX
     */
    public void setObjX(float objX) {
        this.objX = objX;
    }

    /**
     * getter
     * @return objY
     */
    public float getObjY() {
        return objY;
    }

    /**
     * setter
     * @param objY
     */
    public void setObjY(float objY) {
        this.objY = objY;
    }

    /**
     * getter
     * @return objZ
     */
    public float getObjZ() {
        return objZ;
    }

    /**
     * setter
     * @param objZ
     */
    public void setObjZ(float objZ) {
        this.objZ = objZ;
    }

    /**
     * getter
     * @return objDx
     */
    public float getObjDx() {
        return objDx;
    }

    /**
     * setter
     * @param objDx
     */
    public void setObjDx(float objDx) {
        this.objDx = objDx;
    }

    /**
     * getter
     * @return objDy
     */
    public float getObjDy() {
        return objDy;
    }

    /**
     * setter
     * @param objDy
     */
    public void setObjDy(float objDy) {
        this.objDy = objDy;
    }

    /**
     * getter
     * @return objDz
     */
    public float getObjDz() {
        return objDz;
    }

    /**
     * setter
     * @param objDz
     */
    public void setobjDz(float objDz) {
        this.objDz = objDz;
    }

}