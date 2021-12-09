package org.lwjglb.engine.items;

import org.lwjglb.engine.graph.Mesh;
import org.lwjglb.game.GameGUI;

import javax.persistence.Entity;
import java.util.Random;

@Entity
public class Plane extends Vehicle02 {


    public static float nextFloatRange(float min, float max) {
        return (new Random().nextFloat() * (max - min)) + min;
    }

//    public boolean testingObject = false;

    public boolean doesNotFall = true;

    public boolean mostRecent = true;

    public void setAsNewest(){
        this.mostRecent = true;
    }

    public void setAsOld(){
        this.mostRecent = false;
    }

    public void setTestingObject(){
        this.testingObject = true;
    }

    public String objType = "Plane";

    //nov 28
//    public Plane(String type, float x, float y, float z, float dx, float dy, float dz) {
//        super();
//    }



    public Plane(Mesh[] addMesh) {
        super(addMesh);
        setPosition(nextFloatRange(10.00f, -10.0f), 8.f, nextFloatRange(10.00f, -10.0f));
        setVelocity(0.006f * nextFloatRange(1f, -1f), 0.000001f, 0.006f * nextFloatRange(1f, -1f));
    }

    public String getObjType() {
        return objType;
    }

//    public void run() {
//        System.out.println("Running Shape...");
//        boolean isStationary = false;
//
//        while (!isStationary) {
//            if (!doesNotFall){gravity();}
//            this.move();
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }

//    public void faster() {
//        this.velocity.x = this.velocity.x * 1.1f;
//        this.velocity.y = this.velocity.y * 1.1f;
//        this.velocity.z = this.velocity.z * 1.1f;
//    }

//    public void slower() {
//        this.velocity.x = this.velocity.x * 0.9f;
//        this.velocity.y = this.velocity.y * 0.9f;
//        this.velocity.z = this.velocity.z * 0.9f;
//    }

//    public void up() {
//        this.position.y = this.position.y + 0.1f;
//    }
//
//    public void down() {
//        this.position.y = this.position.y - 0.2f;
//    }
//
//    public void left() {
//        this.position.x = this.position.x + 0.2f;
//    }
//
//    public void right() {
//        this.position.x = this.position.x - 0.2f;
//    }
}
//    nextFloatRange(0.001f, 0.01f), nextFloatRange(0.001f, 0.01f), nextFloatRange(0.001f, 0.01f)
//            Planeshape.setRotationVel(new Quaternionf(0.06f * r.nextFloat(), 0.08f * r.nextFloat(), 0.09f * r.nextFloat(), 0.0f));
