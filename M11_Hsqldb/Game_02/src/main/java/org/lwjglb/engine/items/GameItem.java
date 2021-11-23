package org.lwjglb.engine.items;

import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.lwjglb.engine.graph.Mesh;

import java.util.Random;

public class GameItem  extends Thread {

    private boolean selected;

    private Mesh[] meshes;

    public  Vector3f position;
    public   Vector3f velocity;

    /**
     * Russ <RS> added max to keep moving items inside the frustum.
     */
    private  Vector3f max = new Vector3f(15,15,15);

    private float scale;

    private  Quaternionf rotation;
    private  Quaternionf rotationVel;

    private int textPos;
    
    private boolean disableFrustumCulling;

    private boolean insideFrustum;

    private final double threshold = 1.1;
    private Vector3f slower = new Vector3f(1.1f, 1.1f, 1.1f);

    public static float nextFloatRange(float min, float max) {
        return (new Random().nextFloat() * (max - min)) + min;
    }

    private boolean isStationary = false;
    private boolean doesNotFall = false;
    public boolean mostRecent = false;
    public boolean testingObject = false;


    //inherited functions
    public void setAsNewest(){}
    public void setAsOld(){}
    public void faster() {}
    public void slower() {}
    public void up() {}
    public void down() {}
    public void left() {}
    public void right() {}




    public GameItem() {
        selected = false;
        position = new Vector3f(0, 0, 0);
        velocity = new Vector3f(0, 0, 0);
        scale = 1;
        rotation = new Quaternionf();
        rotationVel = new Quaternionf();
        textPos = 0;
        insideFrustum = true;
        disableFrustumCulling = false;
        this.start();
    }

    public GameItem(Mesh mesh) {
        this();
        this.meshes = new Mesh[]{mesh};
    }

    public GameItem(Mesh[] meshes) {
        this();
        this.meshes = meshes;
    }

    public Vector3f getPosition() {
        return position;
    }

    public int getTextPos() {
        return textPos;
    }

    public boolean isSelected() {
        return selected;
    }

    public final void setPosition(float x, float y, float z) {
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;

    }

    public final void setVelocity(float x, float y, float z) {
        this.velocity.x = x;
        this.velocity.y = y;
        this.velocity.z = z;
        if ((x == 0) || (y == 0) || (z == 0)) {
            this.isStationary = true;  // zero velocity, this item is stationary
        }

//        this.velocity.x = this.velocity.x - 1;
//        this.velocity.y = this.velocity.y - 1;
    }

    public float getScale() {
        return scale;
    }

    public final void setScale(float scale) {
        this.scale = scale;
    }

    public Quaternionf getRotation() {
        return rotation;
    }

    public final void setRotation(Quaternionf q) {
        this.rotation.set(q);
    }

    public final void setRotationVel(Quaternionf q) {
        this.rotationVel.set(q);
    }

    public Mesh getMesh() {
        return meshes[0];
    }

    public Mesh[] getMeshes() {
        return meshes;
    }

    public void setMeshes(Mesh[] meshes) {
        this.meshes = meshes;
    }

    public void setMesh(Mesh mesh) {
        this.meshes = new Mesh[]{mesh};
    }

    public void cleanup() {
        int numMeshes = this.meshes != null ? this.meshes.length : 0;
        for (int i = 0; i < numMeshes; i++) {
            this.meshes[i].cleanUp();
        }
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setTextPos(int textPos) {
        this.textPos = textPos;
    }

    public boolean isInsideFrustum() {
        return insideFrustum;
    }

    public void setInsideFrustum(boolean insideFrustum) {
        this.insideFrustum = insideFrustum;
    }
    
    public boolean isDisableFrustumCulling() {
        return disableFrustumCulling;
    }

    public void setDisableFrustumCulling(boolean disableFrustumCulling) {
        this.disableFrustumCulling = disableFrustumCulling;
    }

    /**
     * Russ <RS> added a thread to GameItems to allow items to move on their own.
     */
    public void run() {
        System.out.println("Running Shape...");
        while (!isStationary) {
//            if (!doesNotFall){gravity();}
            gravity();
            this.move();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
//            while (!doesNotFall){
//                this.gravity();
//            }
    /**
     * Russ adde this method to nudge the items slightly.
     */

    void move() {

        // Rotate (spin)
        this.rotation = this.rotation.add(this.rotationVel);

        // Translate (move sideways)
        this.position = this.position.add(this.velocity);

        // Keep within bounds
        if (Math.abs(this.position.x) > Math.abs(max.x)) {
            this.velocity.x = -this.velocity.x;
        }


        if (Math.abs(this.position.y) > Math.abs(max.y)) {
//            this.velocity.y = -this.velocity.y;
            this.floorSlow();
        }


        if (Math.abs(this.position.z) > Math.abs(max.z)) {
            this.velocity.z = -this.velocity.z;

        }

        //Tim m gravity

        if (this.position.y > 14.5) {
            this.velocity.y = this.velocity.y * -1;
        }
//
        if (this.position.y < -14.9){
            this.velocity.y = this.velocity.y * -1;
            this.floorSlow();
    }
//        gravity();
    }

    public void floorSlow() {
                   this.velocity.y = this.velocity.y * 0.9f;
//        this.velocity.y = this.velocity.y - 0.005f;

    }


    public void doesNotMove() {
        this.isStationary = true;
    }

    public void antiGravity(){
        this.doesNotFall = true;
    }

    public void gravity(){
        this.velocity.y = this.velocity.y - 0.00005f; //comment out for class demo
    }

    public boolean DoesItCollide(GameItem that) {

        // Don't collide with itself
        if (this!=that) {

            Vector3f pos1 = this.position;
            Vector3f pos2 = that.position;

            if (pos1.distance(pos2) < threshold) {
                System.err.print(" this.velocity=" + this.velocity.toString());
                this.velocity.negate();
                that.velocity.negate();
                System.err.println("  ###  this.velocity=" + this.velocity.toString());
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void slowDown() {
        this.velocity = this.velocity.mul(slower);
    }

    public void rebound(){
        this.velocity.x = -this.velocity.x + nextFloatRange(-0.0001f, 0);
        this.velocity.y = -this.velocity.y + nextFloatRange(-0.0001f, 0);
        this.velocity.z = -this.velocity.z + nextFloatRange(-0.0001f, 0);

//        this.velocity = this.velocity.mul(slower);



    }

}
