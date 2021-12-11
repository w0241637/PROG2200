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

    /**
     * set random float range
     * @param min
     * @param max
     * @return
     */
    public static float nextFloatRange(float min, float max) {
        return (new Random().nextFloat() * (max - min)) + min;
    }

    private boolean isStationary = false;
    private boolean doesNotFall = false;
    public boolean mostRecent = false;
    public boolean testingObject = false;




    /**
     * increase velocity
     */
    public void faster() {}

    /**
     * lower velocity
     */
    public void slower() {}

    /**
     * increase position y
     */
    public void up() {}

    /**
     * lower position y
     */
    public void down() {}

    /**
     * increase x position
     */
    public void left() {}

    /**
     * lower x position
     */
    public void right() {}


    /**
     * constuctor
     */
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

    /**
     * constructor
     * @param mesh
     */
    public GameItem(Mesh mesh) {
        this();
        this.meshes = new Mesh[]{mesh};
    }

    /**
     * constructor
     * @param meshes
     */
    public GameItem(Mesh[] meshes) {
        this();
        this.meshes = meshes;
    }

    /**
     * get position
     * @return position
     */
    public Vector3f getPosition() {
        return position;
    }

    /**
     * get position
     * @return position as int
     */
    public int getTextPos() {
        return textPos;
    }

    public boolean isSelected() {
        return selected;
    }

    /**
     * set position of this item
     * @param x
     * @param y
     * @param z
     */
    public final void setPosition(float x, float y, float z) {
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;

    }

    /**
     * set velocity of this item, if all 0, set isStationary as true
     * @param x
     * @param y
     * @param z
     */
    public final void setVelocity(float x, float y, float z) {
        this.velocity.x = x;
        this.velocity.y = y;
        this.velocity.z = z;
        if ((x == 0) || (y == 0) || (z == 0)) {
            this.isStationary = true;  // zero velocity, this item is stationary
        }
    }

    /**
     * get scale
     * @return
     */
    public float getScale() {
        return scale;
    }

    /**
     * set scale
     * @param scale
     */
    public final void setScale(float scale) {
        this.scale = scale;
    }

    /**
     * get rotation
     * @return
     */
    public Quaternionf getRotation() {
        return rotation;
    }

    /**
     * set rotation
     * @param q
     */
    public final void setRotation(Quaternionf q) {
        this.rotation.set(q);
    }

    /**
     * set rotation velocity
     * @param q
     */
    public final void setRotationVel(Quaternionf q) {
        this.rotationVel.set(q);
    }

    /**
     * get mesh
     * @return mesh
     */
    public Mesh getMesh() {
        return meshes[0];
    }

    /**
     * get mesh
     * @return meshes[]
     */
    public Mesh[] getMeshes() {
        return meshes;
    }

    /**
     * set meshes
     * @param meshes
     */
    public void setMeshes(Mesh[] meshes) {
        this.meshes = meshes;
    }

    /**
     * set mesh
     * @param mesh
     */
    public void setMesh(Mesh mesh) {
        this.meshes = new Mesh[]{mesh};
    }

    /**
     * remove objects
     */
    public void cleanup() {
        int numMeshes = this.meshes != null ? this.meshes.length : 0;
        for (int i = 0; i < numMeshes; i++) {
            this.meshes[i].cleanUp();
        }
    }

    /**
     * boolean is selected
     * @param selected
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * set position with integer type
     * @param textPos
     */
    public void setTextPos(int textPos) {
        this.textPos = textPos;
    }

    /**
     * @return boolean
     */
    public boolean isInsideFrustum() {
        return insideFrustum;
    }

    /**
     * setter
     * @param insideFrustum
     */
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

    /**
     * reduce velocity a little
     */
    public void floorSlow() {
                   this.velocity.y = this.velocity.y * 0.9f;
//        this.velocity.y = this.velocity.y - 0.005f;

    }

    /**
     * Item does not move
     */
    public void doesNotMove() {
        this.isStationary = true;
    }

    /**
     * unused method to prevent items from falling
     */
    public void antiGravity(){
        this.doesNotFall = true;
    }

    /**
     * used to simulate gravity
     */
    public void gravity(){
        this.velocity.y = this.velocity.y - 0.00005f; //comment out for class demo
    }

    /**
     * collision with another object.
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

    /**
     * bounce on collision in a random direction
     */
    public void rebound(){
        this.velocity.x = -this.velocity.x + nextFloatRange(-0.0001f, 0);
        this.velocity.y = -this.velocity.y + nextFloatRange(-0.0001f, 0);
        this.velocity.z = -this.velocity.z + nextFloatRange(-0.0001f, 0);


    }

}
