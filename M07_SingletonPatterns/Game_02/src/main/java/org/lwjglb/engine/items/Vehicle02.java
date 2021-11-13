package org.lwjglb.engine.items;

import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.lwjglb.engine.graph.Mesh;
import org.lwjglb.engine.items.GameItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Vehicle02 extends GameItem{


    public static float nextFloatRange(float min, float max) {
        return (new Random().nextFloat() * (max - min)) + min;
    }

    public boolean testingObject = false;

    public boolean doesNotFall = true;

    public boolean mostRecent = true;

    public void setAsNewest(){
        this.mostRecent = true;
    }

    public void setAsOld(){
        this.mostRecent = false;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vehicle02(Mesh[] addMesh) {
        super(addMesh);
        setPosition(nextFloatRange(10.00f, -10.0f), 8.f, nextFloatRange(10.00f, -10.0f));
        setVelocity(0.006f * nextFloatRange(1f, -1f), 0.000001f, 0.006f * nextFloatRange(1f, -1f));
    }


    private final double threshold = 1.1;

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

    public void faster() {
        this.velocity.x = this.velocity.x * 1.1f;
        this.velocity.y = this.velocity.y * 1.1f;
        this.velocity.z = this.velocity.z * 1.1f;
    }

    public void slower() {
        this.velocity.x = this.velocity.x * 0.9f;
        this.velocity.y = this.velocity.y * 0.9f;
        this.velocity.z = this.velocity.z * 0.9f;
    }

    public void up() {
        this.position.y = this.position.y + 0.1f;
    }

    public void down() {
        this.position.y = this.position.y - 0.2f;
    }

    public void left() {
        this.position.x = this.position.x + 0.2f;
    }

    public void right() {
        this.position.x = this.position.x - 0.2f;
    }




}