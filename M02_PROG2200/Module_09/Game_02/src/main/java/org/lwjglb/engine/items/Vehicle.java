package org.lwjglb.engine.items;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.lwjglb.engine.graph.Mesh;

import java.util.Random;

public class Vehicle extends GameItem{


    public boolean selected;

    public Mesh[] meshes;

    public Vector3f position;
    public Vector3f velocity;

    /**
     * Russ <RS> added max to keep moving items inside the frustum.
     */
    public  Vector3f max = new Vector3f(15,15,15);

    public Quaternionf rotation;

    public final double threshold = 1.1;
    public Vector3f slower = new Vector3f(1.1f, 1.1f, 1.1f);

    public static float nextFloatRange(float min, float max) {
        return (new Random().nextFloat() * (max - min)) + min;
    }

    public boolean isStationary = false;
    public boolean doesNotFall = false;


    public Vehicle(Mesh[] addMesh) {
        selected = false;
        position = new Vector3f(0, 0, 0);
        velocity = new Vector3f(0, 0, 0);
        float scale = 1;
        rotation = new Quaternionf();
        Quaternionf rotationVel = new Quaternionf();
        int textPos = 0;
        boolean insideFrustum = true;
        boolean disableFrustumCulling = false;
        this.start();
        System.out.println();
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
