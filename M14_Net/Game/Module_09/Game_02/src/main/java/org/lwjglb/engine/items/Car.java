package org.lwjglb.engine.items;

import org.joml.Quaternionf;
import org.lwjglb.engine.graph.Mesh;
import org.lwjglb.engine.loaders.assimp.StaticMeshesLoader;

import javax.persistence.Entity;
import java.util.Random;

@Entity
public class Car extends Vehicle02 {

    public boolean doesNotFall = true;

    public boolean mostRecent = true;

    //nov 28
//    public Car(String car, float x, float y, float z, float dx, float dy, float dz) {
//        super();
//    }

    public void setAsNewest(){
        this.mostRecent = true;
    }

    public void setAsOld(){
        this.mostRecent = false;
    }

    public static float nextFloatRange(float min, float max) {
        return (new Random().nextFloat() * (max - min)) + min;
    }

    public String getObjType() {
        return objType;
    }

    public String objType = "Car";



    public Car(){}

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

    public void setDefaultMesh() {
        try {
            Mesh[] mesh = StaticMeshesLoader.load("src/main/resources/models/russ/Chevrolet_Camaro_SS_Low.obj", "src/main/resources/models/russ");
            this.setMeshes(mesh);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setRotateAndScale(){
        java.util.Random r = new java.util.Random();
        this.setScale(0.10f);
        this.setRotationVel(new Quaternionf(0.00f, 0.5f, 0.0f , 0.0f));    }
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
//
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

    public void up() {
//        this.position.y = this.position.y + 0.2f;
        System.out.println("Cars can't fly!");
    }

//    public void getDefaultMesh() {
//        try {
//            this.setMeshes(StaticMeshesLoader.load("src/main/resources/models/russ/Chevrolet_Camaro_SS_Low.obj", "src/main/resources/models/russ"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

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
