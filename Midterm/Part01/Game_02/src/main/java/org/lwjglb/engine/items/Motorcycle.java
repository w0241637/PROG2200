package org.lwjglb.engine.items;

import org.joml.Vector3f;
import org.lwjglb.engine.graph.Mesh;
import org.lwjglb.game.GameGUI;

import java.util.Random;

public class Motorcycle extends Vehicle02 {


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


    public Motorcycle(Mesh[] addMesh) {
        super(addMesh);
        setPosition(nextFloatRange(10.00f, -10.0f), -15.000f, nextFloatRange(10.00f, -10.0f));
        setVelocity(0.06f * nextFloatRange(1f, -1f), 0.000001f, 0.06f * nextFloatRange(1f, -1f));
    }

    private final double threshold = 1.1;

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

}
