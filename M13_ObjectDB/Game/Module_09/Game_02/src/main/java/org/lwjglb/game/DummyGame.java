package org.lwjglb.game;

import org.joml.*;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjglb.engine.IGameLogic;
import org.lwjglb.engine.MouseInput;
import org.lwjglb.engine.Scene;
import org.lwjglb.engine.SceneLight;
import org.lwjglb.engine.Window;
import org.lwjglb.engine.graph.Camera;
import org.lwjglb.engine.graph.Mesh;
import org.lwjglb.engine.graph.Renderer;
import org.lwjglb.engine.graph.lights.DirectionalLight;
import org.lwjglb.engine.graph.lights.PointLight;
import org.lwjglb.engine.graph.weather.Fog;
import org.lwjglb.engine.items.*;
import org.lwjglb.engine.loaders.assimp.StaticMeshesLoader;

import java.lang.Math;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.Random;


/**
 * Russ <RS> changed this game code to put a bunch
 * of moving GameItems on the screen.
 * <p>
 * To understand what's going on, do these steps:
 * - look at IGameLogic ... the game has several key entry points...understand those.
 * <p>
 * - Follow the use of "camera"  the camera is how you see the scene.  Easiest way to
 * to understand it is put the camera away a bit and pointing back towards the scene, and leave it alone.
 * <p>
 * - Follow the creation of a mesh, being added to the scene.  You can make your own mesh with Blender.
 * <p>
 * - Blender: save your mesh as an OBJ file with cube projection including Normals,
 * UVs, Materials, and Triangulate Faces.
 * <p>
 * - public void input(Window window, MouseInput mouseInput) => process keystrokes sent to the Graphics window.
 * <p>
 * - public void update(float interval, MouseInput mouseInput, Window window) called many times per second.  Use
 * this method for synched game logic. Processing within events is sloppy, and pron to threading
 * errors (and done a lot in this sample).
 */
public class DummyGame implements IGameLogic {



    private static final float MOUSE_SENSITIVITY = 0.2f;

    private final Vector3f cameraInc;

    private final Renderer renderer;

    private final Camera camera;

//    made public Oct 12
//    made static Nov 22
    public static Scene scene;

    private static final float CAMERA_POS_STEP = 0.40f;

    private float angleInc;

    private float lightAngle;

    private boolean firstTime;

    private boolean sceneChanged;

    private Vector3f pointLightPos;

    private GameItem t;

    List<Vehicle02> testItems = new ArrayList<>();

    List<Vehicle02> dbItems = new ArrayList<>();
//    List<Object> dbObjects = new ArrayList<>();
    List<Vehicle02> dbObjects = new ArrayList<>();

    Random rand = new Random();
    float min = 0.001f,
            max = 0.005f;



    public static float nextFloatRange(float min, float max) {
        return (new Random().nextFloat() * (max - min)) + min;
    }

    public DummyGame() {
        renderer = new Renderer();
        camera = new Camera();
        cameraInc = new Vector3f(0.0f, 0.0f, 0.0f);
        angleInc = 0;
        lightAngle = 90;
        firstTime = true;

    }



    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);

        scene = new Scene();

        myDateTime.setRightNow();


        MyLog mylog = MyLog.getInstance();
        mylog.test();

        com.objectdb.Enhancer.enhance("org.lwjglb.engine.items.Vehicle02");
        com.objectdb.Enhancer.enhance("org.lwjglb.engine.items.Car");
        com.objectdb.Enhancer.enhance("org.lwjglb.engine.items.Plane");
        com.objectdb.Enhancer.enhance("org.lwjglb.engine.items.Motorcycle");
        ManageJDO01 g = new ManageJDO01("Shapes01.odb");


        dbHelper02.startDB();

dbHelper02.createTable();
//dbHelper02.dropTable(); //unused
//        dbHelper02.RowTest(); //unused
//        dbHelper02.update();//unused

        //todo create objects
//        System.out.println("obj dump");
        startJdoObjects();
        g.getObjects(Vehicle02.class);

        dbHelper02.createObjs();
//        dbHelper02.deleteAllRows();//unused

//        dbHelper02.printAll();//unused

        /// Make a mesh using blender (or whatever utility),
        /// save as an OBJ file with cube projection including Normals,
        /// UVs, Materials, and Triangulate Faces.  The Mesh can then be
        /// loaded into this game, with position, velocity, rotation, rotation velocity and scale
        /// Also note that the same mesh can be loaded more than obce with different parameters, say
        /// a different scale.
        /// The mesh alone can't move...so we make it a GameItem, which can move/rotate/scale
        /// Then we add all the GameItems to the "scene"
//        Mesh[] houseMesh = StaticMeshesLoader.load("src/main/resources/models/house/house.obj", "src/main/resources/models/house");
//        GameItem house = new GameItem(houseMesh);
//        house.setVelocity(nextFloatRange(0.001f, 0.005f), nextFloatRange(0.001f, 0.005f), nextFloatRange(0.001f, 0.005f));
//        house.setRotationVel(new Quaternionf(0.06f, 0.01f, 0.03f, 0.0f));
//        house.setScale(nextFloatRange(0.0010f, 0.0500f));
//        t = house;

//        Mesh[] cubeMesh = StaticMeshesLoader.load("src/main/resources/models/cube.obj", "src/main/resources/models");
//        GameItem cube = new GameItem(cubeMesh);
//        cube.setPosition(nextFloatRange(1.00f, 15.0f), nextFloatRange(1.00f, 15.0f), nextFloatRange(1.00f, 15.00f));
//        cube.setVelocity(nextFloatRange(0.001f, 0.005f), nextFloatRange(0.001f, 0.005f), nextFloatRange(0.001f, 0.005f));
//        cube.setRotationVel(new Quaternionf(nextFloatRange(-0.1f, 0.1f), nextFloatRange(-0.1f, 0.1f), nextFloatRange(-0.1f, 0.1f), 0.0f));


//        Mesh[] russMesh = StaticMeshesLoader.load("src/main/resources/models/russ/Chevrolet_Camaro_SS_Low.obj", "src/main/resources/models/russ");
//        GameItem russ_shape_01 = new GameItem(russMesh);
//        russ_shape_01.setPosition(nextFloatRange(0.001f, 0.01f), nextFloatRange(0.001f, 0.01f), nextFloatRange(0.001f, 0.01f));
//        russ_shape_01.setVelocity(nextFloatRange(0.001f, 0.005f), nextFloatRange(0.001f, 0.005f), nextFloatRange(0.001f, 0.005f));
//        russ_shape_01.setRotationVel(new Quaternionf(nextFloatRange(-0.1f, 0.1f), nextFloatRange(-0.1f, 0.1f), nextFloatRange(-0.1f, 0.1f), 0.0f));
//        russ_shape_01.setScale(nextFloatRange(0.010f, 0.500f));

//        Mesh[] russMesh2 = StaticMeshesLoader.load("src/main/resources/models/russ/toyPlane.obj", "src/main/resources/models/russ");
//        GameItem russ_shape_02 = new GameItem(russMesh2);
//        russ_shape_02.setPosition(15.00f, 10.000f, .000f);
//        russ_shape_02.setVelocity(-0.005f, 0.000001f, 0.000001f);
//        russ_shape_02.setScale(0.50f);
//
////        Mesh[] torusMesh = StaticMeshesLoader.load("src/main/resources/models/russ/MIsil.obj", "src/main/resources/models/russ");
//        Mesh[] torusMesh = StaticMeshesLoader.load("src/main/resources/models/russ/Motorcycle.obj", "src/main/resources/models/russ");
////        Mesh[] torusMesh = StaticMeshesLoader.load("src/main/resources/models/russ/torus.obj", "src/main/resources/models/russ");
//        GameItem russ_shape_03 = new GameItem(torusMesh);
//        russ_shape_03.setPosition(-15.00f, 10.000f, 0.000f);
//        russ_shape_03.setVelocity(0.005f, 0.000001f, 0.000001f);
//        russ_shape_03.setRotationVel(new Quaternionf(0.004f, 0.015f, 0.05f, 0.0f));
//        russ_shape_03.setScale(0.50f);

        Mesh[] terrainMesh = StaticMeshesLoader.load("src/main/resources/models/terrain/terrain.obj", "src/main/resources/models/terrain");
        GameItem terrain = new GameItem(terrainMesh);
        terrain.setPosition(0.00f, -16.000f, 0.000f);
        terrain.setScale(100.0f);
        terrain.doesNotMove();

//        scene.setGameItems(new GameItem[]{house, cube, russ_shape_01, terrain});
        scene.setGameItems(new GameItem[]{terrain});


        // Shadows
        scene.setRenderShadows(true);

        // Fog
        Vector3f fogColour = new Vector3f(0.5f, 0.5f, 0.5f);
        scene.setFog(new Fog(true, fogColour, 0.02f));

        // Setup  SkyBox
        float skyBoxScale = 100.0f;
        SkyBox skyBox = new SkyBox("src/main/resources/models/skybox.obj", new Vector4f(0.65f, 0.65f, 0.65f, 1.0f));
        skyBox.setScale(skyBoxScale);
        scene.setSkyBox(skyBox);

        // Setup Lights
        setupLights();

        // Set camera position and rotation to look back at our scene
        camera.setPosition(-17.0f, 17.0f, -30.0f);
        camera.setRotation(20.0f, 140.0f, 0.0f);


        // Set up timer for logging
        List<GameItem> logItems = scene.getgameItems();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
        int timerInt = 0;






            @Override
            public void run() {
                //what you want to do every real world second
                testTimedLog();
                testCollide();
                System.out.println("seconds passed: " + timerInt);
//                testList();
                timerInt++;
            }
        }, 0, 1000);//wait 0 ms before doing the action and do it evry 1000ms (1second)
//        timer.cancel();//stop the timer

    } //init

    public void testList(){
        for (Vehicle02 item : dbItems){
            final String objType = item.getObjType();

            final float x = item.position.x;
            final float y = item.position.y;
            final float z = item.position.z;

            final float dx = item.velocity.x;
            final float dy = item.velocity.y;
            final float dz = item.velocity.z;

            System.out.println("item: " + item + "\tx: " + x + "\ty: " + y + "\tz: " + z);
            System.out.println("objType: " + objType + "\tdx: " + dx + "\tdy: " + dy + "\tdz: " + dz);
        }
    }


    private void setupLights() {
        SceneLight sceneLight = new SceneLight();
        scene.setSceneLight(sceneLight);

        // Ambient Light
        sceneLight.setAmbientLight(new Vector3f(0.3f, 0.3f, 0.3f));
        sceneLight.setSkyBoxLight(new Vector3f(1.0f, 1.0f, 1.0f));

        // Directional Light
        float lightIntensity = 1.0f;
        Vector3f lightDirection = new Vector3f(0, 1, 1);
        DirectionalLight directionalLight = new DirectionalLight(new Vector3f(1, 1, 1), lightDirection, lightIntensity);
        sceneLight.setDirectionalLight(directionalLight);

        pointLightPos = new Vector3f(0.0f, 25.0f, 0.0f);
        Vector3f pointLightColour = new Vector3f(0.0f, 1.0f, 0.0f);
        PointLight.Attenuation attenuation = new PointLight.Attenuation(1, 0.0f, 0);
        PointLight pointLight = new PointLight(pointLightColour, pointLightPos, lightIntensity, attenuation);
        sceneLight.setPointLightList(new PointLight[]{pointLight});
    }

    @Override
    public void input(Window window, MouseInput mouseInput) {
        sceneChanged = false;
        cameraInc.set(0, 0, 0);
        if (window.isKeyPressed(GLFW_KEY_W)) {
            sceneChanged = true;
            cameraInc.z = -1;
        } else if (window.isKeyPressed(GLFW_KEY_S)) {
            sceneChanged = true;
            cameraInc.z = 1;
        }
        if (window.isKeyPressed(GLFW_KEY_A)) {
            sceneChanged = true;
            cameraInc.x = -1;
        } else if (window.isKeyPressed(GLFW_KEY_D)) {
            sceneChanged = true;
            cameraInc.x = 1;
        }
        if (window.isKeyPressed(GLFW_KEY_Z)) {
            sceneChanged = true;
            cameraInc.y = -1;
        } else if (window.isKeyPressed(GLFW_KEY_X)) {
            sceneChanged = true;
            cameraInc.y = 1;
        }
        if (window.isKeyPressed(GLFW_KEY_LEFT)) {
            sceneChanged = true;
            angleInc -= 0.05f;
        } else if (window.isKeyPressed(GLFW_KEY_RIGHT)) {
            sceneChanged = true;
            angleInc += 0.05f;
        } else {
            angleInc = 0;
        }
        if (window.isKeyPressed(GLFW_KEY_UP)) {
            sceneChanged = true;
            pointLightPos.y += 0.5f;
        } else if (window.isKeyPressed(GLFW_KEY_DOWN)) {
            sceneChanged = true;
            pointLightPos.y -= 0.5f;
        }
        if (window.isKeyPressed(GLFW_KEY_SPACE)) {
            sceneChanged = true;
            this.addMeshOnScreen();
        }
        if (window.isKeyPressed(GLFW_KEY_LEFT_SHIFT)) {
            sceneChanged = true;
            this.removeAll = true;
        }
        if (window.isKeyPressed(GLFW_KEY_T)) {
            try {
                testingFunc();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    java.util.Random r = new java.util.Random();

    @Override
    public void update(float interval, MouseInput mouseInput, Window window) {

        // Since we move gameItems in the background (with their own
        // thread, all the time, so cause the lighting/shadows to be recomputed
        sceneChanged = true;

        // Clear screen?
        if (GameGUI.getClearCommand()) {
            try {
                //todo delete db contents
                ManageJDO01 g = new ManageJDO01("Shapes01.odb");
                g.emptyDB(Vehicle02.class);
                dbHelper02.deleteAllRows();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            removeAll = true;
        }

        // If reset, set this gameItem back to a location.
        if (GameGUI.getResetCommand()) {
            t.setPosition(11.00f, 11.000f, 15 * r.nextFloat());
            t.setVelocity(0.002f, 0.001f, 0.003f);
            t.setRotation(new Quaternionf(2.6f, 4.7f, 3.9f, 0.0f));
            t.setRotationVel(new Quaternionf(0.006f, 0.007f, 0.0009f, 0.0f));
        }

        // If adding, add a gameItem
        if (GameGUI.getAddCommand()) {
            addMeshOnScreen();
        }

        if (GameGUI.getAddCarCommand()) {
            List<GameItem> items = scene.getgameItems();
            for (GameItem objs : items){
                objs.mostRecent = true;
            }
            addCarMeshOnScreen();
        }

        if (GameGUI.getAddPlaneCommand()) {
            List<GameItem> items = scene.getgameItems();
            for (GameItem objs : items){
                objs.mostRecent = true;
            }

            addPlaneMeshOnScreen();
        }

        if (GameGUI.getFasterCommand()) {
            List<GameItem> gameItems = scene.getgameItems();
            System.out.println("Faster Pressed!!!!");
            for (GameItem objs : gameItems){
                if (!objs.mostRecent) {
                    objs.faster();
                }
            }
        }

        if (GameGUI.getSlowerCommand()) {
            List<GameItem> gameItems = scene.getgameItems();
            System.out.println("Slower Pressed!!!!");
            for (GameItem objs : gameItems){
                if (!objs.mostRecent) {
                    objs.slower();
                }
            }
        }

        if (GameGUI.getUpCommand()) {
            List<GameItem> Items = scene.getgameItems();

            System.out.println("Up Pressed!!!!");
            for (GameItem objs : Items){
                if (!objs.mostRecent) {
                    objs.up();
                }
            }
        }

        if (GameGUI.getDownCommand()) {
            List<GameItem> Items = scene.getgameItems();

            System.out.println("Down Pressed!!!!");
            for (GameItem objs : Items){
                if (!objs.mostRecent) {
                    objs.down();
                }
            }
        }

        if (GameGUI.getLeftCommand()) {
            List<GameItem> Items = scene.getgameItems();

            System.out.println("Left Pressed!!!!");
            for (GameItem objs : Items){
                if (!objs.mostRecent) {
                    objs.left();
                }
            }
        }

        if (GameGUI.getRightCommand()) {
            List<GameItem> Items = scene.getgameItems();

            System.out.println("Right Pressed!!!!");
            for (GameItem objs : Items){
                if (!objs.mostRecent) {
                    objs.right();
                }
            }
        }

        if (GameGUI.getAddMotorcycleCommand()) {
            List<GameItem> items = scene.getgameItems();
            for (GameItem objs : items){
                objs.mostRecent = true;
            }
            addMotorcycleMeshOnScreen();
        }




        List<GameItem> gameItems = scene.getgameItems();
        for (GameItem outer : gameItems) {
            for (GameItem inner : gameItems) {
                if (outer != inner) {
                    boolean collide = inner.DoesItCollide(outer);
                    if (collide) {
                        outer.rebound();
                        inner.rebound();
//                        testCollide();
//                        System.out.println("COLLISION:\t\t "+gameItems+"inner x: " + inner.position.x + "\t\ty: " + inner.position.y + "\t\tz: " + inner.position.z);
//                        System.out.println("COLLISION:\t\t outer x: " + outer.position.x + "\t\ty: " + outer.position.y + "\t\tz: " +outer.position.z);
//                        outer.doesNotMove();
                    }
                }
            }
        }






//        get most recent object

        if (mouseInput.isRightButtonPressed()) {
            // Update camera based on mouse            
            Vector2f rotVec = mouseInput.getDisplVec();
            camera.moveRotation(rotVec.x * MOUSE_SENSITIVITY, rotVec.y * MOUSE_SENSITIVITY, 0);
            sceneChanged = true;
        }

        // Update camera position
        camera.movePosition(cameraInc.x * CAMERA_POS_STEP, cameraInc.y * CAMERA_POS_STEP, cameraInc.z * CAMERA_POS_STEP);

        lightAngle += angleInc;
        if (lightAngle < 0) {
            lightAngle = 0;
        } else if (lightAngle > 180) {
            lightAngle = 180;
        }
        float zValue = (float) Math.cos(Math.toRadians(lightAngle));
        float yValue = (float) Math.sin(Math.toRadians(lightAngle));
        Vector3f lightDirection = this.scene.getSceneLight().getDirectionalLight().getDirection();
        lightDirection.x = 0;
        lightDirection.y = yValue;
        lightDirection.z = zValue;
        lightDirection.normalize();

        // Update view matrix
        camera.updateViewMatrix();
    }

    /* saved if i muck up */

//    public void addMeshOnScreen() {
//        try {
//            Mesh[] addMesh = StaticMeshesLoader.load("src/main/resources/models/cube.obj", "src/main/resources/models");
//            GameItem shape = new GameItem(addMesh);
//
//
//
////            Mesh[] addMesh = StaticMeshesLoader.load("src/main/resources/models/house/house.obj", "src/main/resources/models/house");
////            Mesh[] addMesh = StaticMeshesLoader.load("src/main/resources/models/russ/russ9.obj", "src/main/resources/models/russ");
////            GameItem shape = new Vehicle02(addMesh);
////            shape.setScale(0.10f);  //house needs to be shrunk
//            shape.setPosition(5.00f * r.nextFloat(), 5.000f * r.nextFloat(), 15 * r.nextFloat());
//            shape.setVelocity(0.005f * r.nextFloat(), 0.004f * r.nextFloat(), 0.003f * r.nextFloat());
//            shape.setRotationVel(new Quaternionf(0.06f * r.nextFloat(), 0.08f * r.nextFloat(), 0.09f * r.nextFloat(), 0.0f));
//            scene.addGameItem(shape);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void addMeshOnScreen() {
        try {

            ManageJDO01 g = new ManageJDO01("Shapes01.odb");

            Mesh[] addMesh = StaticMeshesLoader.load("src/main/resources/models/cube.obj", "src/main/resources/models");
            Vehicle02 shape = new Vehicle02(addMesh);

            shape.setPosition(5.00f * r.nextFloat(), 5.000f * r.nextFloat(), 15 * r.nextFloat());
            shape.setVelocity(0.005f * r.nextFloat(), 0.004f * r.nextFloat(), 0.003f * r.nextFloat());
            shape.setRotationVel(new Quaternionf(0.06f * r.nextFloat(), 0.08f * r.nextFloat(), 0.09f * r.nextFloat(), 0.0f));
            scene.addGameItem(shape);

//                        Vehicle02 v1 = new Vehicle02("Vehicle02",1f,2f,3f,4f,5f,6f);
//            g.saveNew(v1);
//            g.saveNew(shape);
            dbObjects.add(shape);
//            dbObjects.add(v1);
            g.close();
            dbItems.add(shape);

        } catch (Exception e) {
            e.printStackTrace();
        }
//        dbItems.add(shape);
    }

//    Add Car
    public void addCarMeshOnScreen() {
        try {
//            ManageJDO01 g = new ManageJDO01("Shapes01.odb");


            Mesh[] addMesh = StaticMeshesLoader.load("src/main/resources/models/russ/Chevrolet_Camaro_SS_Low.obj", "src/main/resources/models/russ");
            Car carShape = new Car(addMesh);
            carShape.setScale(0.10f);
            carShape.setRotationVel(new Quaternionf(0.00f, 0.5f, 0.0f , 0.0f));
            scene.addGameItem(carShape);

            dbItems.add(carShape);
            dbObjects.add(carShape);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


//    Add Plane
    public void addPlaneMeshOnScreen() {
        try {
            Mesh[] addMesh = StaticMeshesLoader.load("src/main/resources/models/russ/toyPlane.obj", "src/main/resources/models/russ");
//            Mesh[] addMesh = StaticMeshesLoader.load("src/main/resources/models/russ/russ9.obj", "src/main/resources/models/russ");
            Plane Planeshape = new Plane(addMesh);
            Planeshape.setScale(0.30f);  //house needs to be shrunk
//            Planeshape.setPosition(13.0f, 8.f, 0.0f);
//            Planeshape.setVelocity(-0.005f, 0.000001f, 0.000001f);
            Planeshape.setRotationVel(new Quaternionf(0.06f * r.nextFloat(), 0.08f * r.nextFloat(), 0.09f * r.nextFloat(), 0.0f));
            Planeshape.antiGravity();
            Planeshape.mostRecent = true;
            scene.addGameItem(Planeshape);

            dbItems.add(Planeshape);
            dbObjects.add(Planeshape);


        } catch (Exception e) {
            e.printStackTrace();
        }
//        russ_shape_02.setPosition(15.00f, 10.000f, .000f);
//        russ_shape_02.setVelocity(-0.005f, 0.000001f, 0.000001f);
//        russ_shape_02.setScale(0.50f);
    }

    //    Add Motorcycle
    public void addMotorcycleMeshOnScreen() {
        try {
            Mesh[] addMesh = StaticMeshesLoader.load("src/main/resources/models/russ/Motorcycle.obj", "src/main/resources/models/russ");
//            Mesh[] addMesh = StaticMeshesLoader.load("src/main/resources/models/russ/Chevrolet_Camaro_SS_Low.obj", "src/main/resources/models/russ");
            Motorcycle cycleShape = new Motorcycle(addMesh);
            cycleShape.setScale(0.30f);
            cycleShape.setRotationVel(new Quaternionf(0.00f, 0.0f, 0.0f , 0.0f));
            scene.addGameItem(cycleShape);

            dbItems.add(cycleShape);
            dbObjects.add(cycleShape);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearScreen() {
        try {
            // Remove everything, then put back the basics
            scene.removeAll();

            Mesh[] terrainMesh = StaticMeshesLoader.load("src/main/resources/models/terrain/terrain.obj", "src/main/resources/models/terrain");
            GameItem terrain = new GameItem(terrainMesh);
            terrain.setPosition(0.00f, -16.000f, 0.000f);
            terrain.setScale(100.0f);
            terrain.doesNotMove();

            scene.setGameItems(new GameItem[]{terrain});

            // Setup  SkyBox
            float skyBoxScale = 100.0f;
            SkyBox skyBox = new SkyBox("src/main/resources/models/skybox.obj", new Vector4f(0.65f, 0.65f, 0.65f, 1.0f));
            skyBox.setScale(skyBoxScale);
            scene.setSkyBox(skyBox);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    // clear objects, add 4 objects to testitems array
    public void testingFunc() throws Exception {
        clearScreen();
        MyLog mylog = MyLog.getInstance();
        int logTimes = mylog.getLogCount();
        mylog.writeString("Test "+logTimes+" Start "+myDateTime.justTime());
        mylog.setLogCount(++logTimes);

        try {
            Mesh[] addMeshTest01 = StaticMeshesLoader.load("src/main/resources/models/russ/toyPlane.obj", "src/main/resources/models/russ");
//            Mesh[] addMesh = StaticMeshesLoader.load("src/main/resources/models/russ/russ9.obj", "src/main/resources/models/russ");
            Plane PlaneshapeTest01 = new Plane(addMeshTest01);
            PlaneshapeTest01.setScale(0.30f);  //house needs to be shrunk
            PlaneshapeTest01.setPosition(0.00f, 10.00f, 0.0f);
            PlaneshapeTest01.setVelocity(0.005f, 0.000001f, 0.000001f);
            PlaneshapeTest01.setRotationVel(new Quaternionf(0.06f * r.nextFloat(), 0.08f * r.nextFloat(), 0.09f * r.nextFloat(), 0.0f));
            PlaneshapeTest01.antiGravity();
            PlaneshapeTest01.mostRecent = true;
            PlaneshapeTest01.testingObject = true;
            scene.addGameItem(PlaneshapeTest01);

            Mesh[] addMeshTest02 = StaticMeshesLoader.load("src/main/resources/models/russ/toyPlane.obj", "src/main/resources/models/russ");
//            Mesh[] addMesh = StaticMeshesLoader.load("src/main/resources/models/russ/russ9.obj", "src/main/resources/models/russ");
            Plane PlaneshapeTest02 = new Plane(addMeshTest02);
            PlaneshapeTest02.setScale(0.30f);  //house needs to be shrunk
            PlaneshapeTest02.setPosition(15.00f, 10.000f, 0.000f);
            PlaneshapeTest02.setVelocity(0.005f, 0.000001f, 0.000001f);
            PlaneshapeTest02.setRotationVel(new Quaternionf(0.06f * r.nextFloat(), 0.08f * r.nextFloat(), 0.09f * r.nextFloat(), 0.0f));
            PlaneshapeTest02.antiGravity();
            PlaneshapeTest02.testingObject = true;
            PlaneshapeTest02.mostRecent = true;
            scene.addGameItem(PlaneshapeTest02);


            Mesh[] addMeshTest03 = StaticMeshesLoader.load("src/main/resources/models/russ/Chevrolet_Camaro_SS_Low.obj", "src/main/resources/models/russ");
            Car carShapeTest01 = new Car(addMeshTest03);
            carShapeTest01.setScale(0.10f);
            carShapeTest01.setPosition(15.00f, -15.000f, 0.000f);
            carShapeTest01.setVelocity(-0.00f, 0.00000f, 0.00000f);
            carShapeTest01.setRotationVel(new Quaternionf(0.00f, 0.5f, 0.0f , 0.0f));
            carShapeTest01.testingObject = true;
            scene.addGameItem(carShapeTest01);

            Mesh[] addMeshTest04 = StaticMeshesLoader.load("src/main/resources/models/russ/Chevrolet_Camaro_SS_Low.obj", "src/main/resources/models/russ");
            Car carShapeTest02 = new Car(addMeshTest04);
            carShapeTest02.setScale(0.10f);
            carShapeTest02.setPosition(10.00f, -15.000f, 0.000f);
            carShapeTest02.setVelocity(0.0005f, -0.0000001f, -0.0000001f);
            carShapeTest02.setRotationVel(new Quaternionf(0.00f, 0.5f, 0.0f , 0.0f));
            carShapeTest02.testingObject = true;
            scene.addGameItem(carShapeTest02);

            testItems.add(PlaneshapeTest01);
            testItems.add(PlaneshapeTest02);
            testItems.add(carShapeTest01);
            testItems.add(carShapeTest02);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }



//    List<Vehicle02> testItems = new ArrayList<>();


    //print item and position, write item and position
        public void testTimedLog(){
            for (Vehicle02 item : testItems){
                final float x = item.position.x;
                final float y = item.position.y;
                final float z = item.position.z;
                System.out.println("item: " + item + "\tx: " + x + "\ty: " + y + "\tz: " + z);

                MyLog mylog = MyLog.getInstance();
                mylog.writeString(item +","
                        + x +","
                        + y +","
                        + z +","
                        + myDateTime.justTime());
            }
        }

        // get position and if collide, print to screen and log
        public void testCollide(){
            for (Vehicle02 item : testItems){
                final float x = item.position.x;
                final float y = item.position.y;
                final float z = item.position.z;
                for(Vehicle02 otherItem : testItems){
                    if (item != otherItem) {
                        boolean collide = item.DoesItCollide(otherItem);
                        if (collide) {
                            System.out.println("Collision !!!");
                            System.out.println("item: " + item + "\tx: " + x + "\ty: " + y + "\tz: " + z);
                            System.out.println("otherItem: " + otherItem + "\tx: " + x + "\ty: " + y + "\tz: " + z);

                            MyLog mylog = MyLog.getInstance();
                            mylog.writeString(item +","
                                    + x +","
                                    + y +","
                                    + z +","
                                    + myDateTime.justTime() +","
                                    + "collision");
                        }
                    }
                }
            }
        }




    public void insertRows() throws SQLException {
        for (Vehicle02 item : dbItems){

            final String objType = item.getObjType();

            final float x = item.position.x;
            final float y = item.position.y;
            final float z = item.position.z;

            final float dx = item.velocity.x;
            final float dy = item.velocity.y;
            final float dz = item.velocity.z;

            //todo insert the items to database
//            dbHelper02.insertRow(objType,x,y,z,dx,dy,dz);

            System.out.println("item: " + item + "\tx: " + x + "\ty: " + y + "\tz: " + z);
            System.out.println("objType: " + objType + "\tdx: " + dx + "\tdy: " + dy + "\tdz: " + dz);
        }
    }

    //todo
    public void insertJdoObjects(){
        ManageJDO01 g = new ManageJDO01("Shapes01.odb");

        for (Vehicle02 objects : dbObjects){



            if (objects.getObjType() == "Car"){
                System.out.println("you got a car there buddy");
                Car cube = null;
                try {
                    cube = new Car();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                cube.setObjX(objects.getPosition().x);
                cube.setObjY(objects.getPosition().y);
                cube.setObjZ(objects.getPosition().z);
                cube.setObjDx(objects.velocity.x);
                cube.setObjDy(objects.velocity.y);
                cube.setobjDz(objects.velocity.z);
                g.saveNew(cube);

            }
            else if (objects.getObjType() == "Plane"){
                System.out.println("GOT Plane");
                Plane cube = new Plane();

                cube.setObjX(objects.getPosition().x);
                cube.setObjY(objects.getPosition().y);
                cube.setObjZ(objects.getPosition().z);
                cube.setObjDx(objects.velocity.x);
                cube.setObjDy(objects.velocity.y);
                cube.setobjDz(objects.velocity.z);
                cube.setDefaultMesh();

                g.saveNew(cube);
            }
            else if (objects.getObjType() == "Motorcycle"){
                System.out.println("GOT Motorcycle");
                Motorcycle cube = new Motorcycle();

                cube.setObjX(objects.getPosition().x);
                cube.setObjY(objects.getPosition().y);
                cube.setObjZ(objects.getPosition().z);
                cube.setObjDx(objects.velocity.x);
                cube.setObjDy(objects.velocity.y);
                cube.setobjDz(objects.velocity.z);
                cube.setDefaultMesh();

                g.saveNew(cube);

            } else{
                System.out.println("cube town baby");
                Vehicle02 cube = new Vehicle02();

                cube.setObjX(objects.getPosition().x);
                cube.setObjY(objects.getPosition().y);
                cube.setObjZ(objects.getPosition().z);
                cube.setObjDx(objects.velocity.x);
                cube.setObjDy(objects.velocity.y);
                cube.setobjDz(objects.velocity.z);
                cube.setDefaultMesh();

                g.saveNew(cube);
            }


//            Vehicle02 cube = new Vehicle02("Vehicle02",objects.position.x,objects.position.y,objects.position.z,objects.velocity.x,objects.velocity.y,objects.velocity.z);

            System.out.println("# saved objects: "+ objects);
//            System.out.println("## saved objects: "+ cube);
//
        }


//        Vehicle02 cube2 = new Vehicle02("Vehicle02",1f,2f,3f,4f,5f,6f);
//        cube2.setObjX(999f);
//        g.saveNew(cube2);
//
////        System.out.println("saved objects: "+ objects);
//        System.out.println("### saved objects: "+ cube2);
    }
 //todo
    public void startJdoObjects(){


        ManageJDO01 g = new ManageJDO01("Shapes01.odb");

        List<Object> results;
        results = g.getObjects(Vehicle02.class);
        for (Object obj : results){
            Vehicle02 obj2 = (Vehicle02) obj;

            if(Objects.equals(obj2.getObjType(), "Car")){
                System.out.println("$$$ starting Car $$$");
                Car shape = new Car();
                shape.setPosition(obj2.getObjX(), obj2.getObjY(), obj2.getObjZ());
                shape.setVelocity(obj2.getObjDx(), obj2.getObjDy(), obj2.getObjDz());
                shape.setScale(0.10f);
                shape.setRotationVel(new Quaternionf(0.00f, 0.5f, 0.0f , 0.0f));
                shape.setDefaultMesh();
                scene.addGameItem(shape);
            } else if(Objects.equals(obj2.getObjType(), "Plane")){
                System.out.println("$$$ starting Plane  $$$");
                Plane shape = new Plane();
                shape.setPosition(obj2.getObjX(), obj2.getObjY(), obj2.getObjZ());
                shape.setVelocity(obj2.getObjDx(), obj2.getObjDy(), obj2.getObjDz());
                shape.setRotationVel(new Quaternionf(0.06f * r.nextFloat(), 0.08f * r.nextFloat(), 0.09f * r.nextFloat(), 0.0f));
                shape.setScale(0.30f);
                shape.setDefaultMesh();
                scene.addGameItem(shape);

            } else if(Objects.equals(obj2.getObjType(), "Motorcycle")){
                Motorcycle cycleShape = new Motorcycle();
                cycleShape.setScale(0.30f);
                cycleShape.setPosition(obj2.getObjX(), obj2.getObjY(), obj2.getObjZ());
                cycleShape.setVelocity(obj2.getObjDx(), obj2.getObjDy(), obj2.getObjDz());
                cycleShape.setRotationVel(new Quaternionf(0.00f, 0.0f, 0.0f , 0.0f));
                cycleShape.setDefaultMesh();
                scene.addGameItem(cycleShape);

            } else {
                Vehicle02 shape = new Vehicle02();
                System.out.println("$$$ starting Veh $$$");
                shape.setPosition(obj2.getObjX(), obj2.getObjY(), obj2.getObjZ());
                shape.setVelocity(obj2.getObjDx(), obj2.getObjDy(), obj2.getObjDz());
                shape.setRotationVel(new Quaternionf(0.06f * r.nextFloat(), 0.08f * r.nextFloat(), 0.09f * r.nextFloat(), 0.0f));
                shape.setDefaultMesh();
                scene.addGameItem(shape);
            }





            g.dumpObjects(Vehicle02.class);

            System.out.println("obj-> "+obj+" <-obj");

            System.out.println("obyType: "+obj2.getObjType());
            System.out.println("this: "+obj2.objX);
            System.out.println(obj2.position.x);
            System.out.println(obj2.position.x());
            System.out.println("x: "+obj2.getObjX());
            System.out.println("y: "+obj2.getObjY());
            System.out.println("z: "+obj2.getObjZ());
            System.out.println("dx: "+obj2.getObjDx());
            System.out.println("dy: "+obj2.getObjDy());
            System.out.println("dz: "+obj2.getObjDz());

            System.out.println(obj2.getPosition().y);
            System.out.println(obj2.getPosition().z);
            System.out.println(obj2.getTextPos());


//            try {
//                obj2.setMeshes(StaticMeshesLoader.load("src/main/resources/models/cube.obj", "src/main/resources/models"));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }

        System.out.println("results here-> "+results + "<- results here!!!");

    }

//saved if i muck up nov 29
//    public void startJdoObjects(){
//
//
//        ManageJDO01 g = new ManageJDO01("Shapes01.odb");
//
//
//
//        List<Object> results;
//        results = g.getObjects(Vehicle02.class);
//        for (Object obj : results){
//            System.out.println("obj-> "+obj+" <-obj");
//            Vehicle02 obj2 = (Vehicle02) obj;
//            System.out.println("obyType: "+obj2.getObjType());
//            try {
//                obj2.setMeshes(StaticMeshesLoader.load("src/main/resources/models/cube.obj", "src/main/resources/models"));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        System.out.println("results here-> "+results + "<- results here!!!");
//
//    }


//    List<Object> results;
//    results = g.getObjects(Shape.class);
//		for (Object t : results) {
//        Thread t2 = (Thread) t;
//        t2.start();
//    }






    public static void addDbMeshOnScreen(String objType, float x, float y, float z, float dx, float dy, float dz) {
        try {

            if(Objects.equals(objType, "Car")){
                Mesh[] addMesh = StaticMeshesLoader.load("src/main/resources/models/russ/Chevrolet_Camaro_SS_Low.obj", "src/main/resources/models/russ");
                Car shape = new Car(addMesh);
                shape.setPosition(x,y,z);
                shape.setVelocity(dx, dy, dz);
                shape.setScale(0.10f);
                shape.setRotationVel(new Quaternionf(0.00f, 0.5f, 0.0f , 0.0f));
                scene.addGameItem(shape);
            } else if (Objects.equals(objType, "Plane")){
                Random r = new Random();
                Mesh[] addMesh = StaticMeshesLoader.load("src/main/resources/models/russ/toyPlane.obj", "src/main/resources/models/russ");
                Plane shape = new Plane(addMesh);
                shape.setScale(0.30f);
                shape.setPosition(x,y,z);
                shape.setVelocity(dx, dy, dz);
                shape.setRotationVel(new Quaternionf(0.06f * r.nextFloat(), 0.08f * r.nextFloat(), 0.09f * r.nextFloat(), 0.0f));
                shape.antiGravity();
                shape.mostRecent = true;
                scene.addGameItem(shape);
            } else if (Objects.equals(objType, "Motorcycle")){
                Mesh[] addMesh = StaticMeshesLoader.load("src/main/resources/models/russ/Motorcycle.obj", "src/main/resources/models/russ");
                Motorcycle shape = new Motorcycle(addMesh);
                shape.setPosition(x,y,z);
                shape.setVelocity(dx, dy, dz);
                shape.setScale(0.30f);
                shape.setRotationVel(new Quaternionf(0.00f, 0.0f, 0.0f , 0.0f));
                scene.addGameItem(shape);
            } else {
                Mesh[] addMesh = StaticMeshesLoader.load("src/main/resources/models/cube.obj", "src/main/resources/models");
                Vehicle02 shape = new Vehicle02(addMesh);
                shape.setPosition(x,y,z);
                shape.setVelocity(dx, dy, dz);
                scene.addGameItem(shape);
            }
//            shape.setPosition(x,y,z);
//            shape.setVelocity(dx, dy, dz);
//            scene.addGameItem(shape);


//            dbItems.add(shape);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // set flag to indicate to remove all meshes.
    boolean removeAll = false;

    @Override
    public void render(Window window) {

        if (this.removeAll) {
            this.clearScreen();
            this.removeAll = false;  //toggle
        }
        if (firstTime) {
            sceneChanged = true;
            firstTime = false;
        }

        renderer.render(window, camera, scene, sceneChanged);
    }

    @Override
    public void cleanup() {
        ManageJDO01 g = new ManageJDO01("Shapes01.odb");
//g.emptyDB(Vehicle02.class);
        try {
            insertJdoObjects();
            insertRows();
            //todo print objects?
            dbHelper02.printAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
//        g.close();
        renderer.cleanup();
        scene.cleanup();
        System.out.println("close viewing window");

    }
}
