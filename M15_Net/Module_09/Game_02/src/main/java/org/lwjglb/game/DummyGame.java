package org.lwjglb.game;

import org.joml.*;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjglb.Network.client.Client;
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

    java.util.Random r = new java.util.Random();


    List<Vehicle02> testItems = new ArrayList<>();

    List<Vehicle02> dbItems = new ArrayList<>();
    List<Vehicle02> dbObjects = new ArrayList<>();

    Random rand = new Random();
    float min = 0.001f,
            max = 0.005f;



    public static float nextFloatRange(float min, float max) {
        return (new Random().nextFloat() * (max - min)) + min;
    }

    /**
     * constructor
     */
    public DummyGame() {
        renderer = new Renderer();
        camera = new Camera();
        cameraInc = new Vector3f(0.0f, 0.0f, 0.0f);
        angleInc = 0;
        lightAngle = 90;
        firstTime = true;

    }


    /**
     * open the window, accept commands, display items
     * @param window
     * @throws Exception
     */
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

        System.out.println(Client.RunSocketClient());


        // todo switch between ObjectDB and SQLite here
//        startSQLdbObjects();   // this for SQLite/HSQLDB
        startJdoObjects();      // this for ObjectDB



        g.getObjects(Vehicle02.class);
        g.close();



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


            /**
             * 1 second timer. used for console log and .csv log
             */
            @Override
            public void run() {
                testTimedLog();
                testCollide();
                System.out.println("seconds passed: " + timerInt);
//                testList();
                timerInt++;
            }
        }, 0, 1000);//wait 0 ms before doing the action and do it evry 1000ms (1second)
//        timer.cancel();//stop the timer

    } //init

    /**
     * make and display lighting
     */
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

    /**
     * accept input from keyboard
     * @param window
     * @param mouseInput
     */
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

    /**
     * Update the view based on input
     * @param interval
     * @param mouseInput
     * @param window
     */
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
                g.close();
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

        if (GameGUI.getAddNetworkCommand()) {
            List<GameItem> items = scene.getgameItems();
            for (GameItem objs : items){
                objs.mostRecent = true;
            }
            System.out.println("Doing Netowrk things");
            System.out.println(Client.RunSocketClient());
            ManageJDO01 g = new ManageJDO01("Shapes01.odb");

//            startJdoObjects();      // this for ObjectDB

            g.getObjects(Vehicle02.class);
            g.close();
            System.out.println("done doing network things");
        }



        List<GameItem> gameItems = scene.getgameItems();
        for (GameItem outer : gameItems) {
            for (GameItem inner : gameItems) {
                if (outer != inner) {
                    boolean collide = inner.DoesItCollide(outer);
                    if (collide) {
                        outer.rebound();
                        inner.rebound();

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


    /**
     * add a cube
     */
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

    /**
     * add a car
     */
    public void addCarMeshOnScreen() {
        try {

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

    /**
     * add a plane
     */
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
    }

    /**
     * add a motorcycle
     */
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

    /**
     * clear objects from screen
     */
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

    /**
     * clear objects, add 4 objects to testitems array
     * @throws Exception
     */
    public void testingFunc() throws Exception {
        clearScreen();
        MyLog mylog = MyLog.getInstance();
        int logTimes = mylog.getLogCount();
        mylog.writeString("Test "+logTimes+" Start "+myDateTime.justTime());
        mylog.setLogCount(++logTimes);

        try {
            Mesh[] addMeshTest01 = StaticMeshesLoader.load("src/main/resources/models/russ/toyPlane.obj", "src/main/resources/models/russ");
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


    /**
     * print item and position, write item and position
     */
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

    /**
     * get position and if collide, print to screen and log
     */

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


    /**
     * SQL to add object values to database
     * @throws SQLException
     */
    public void insertRows() throws SQLException {
        for (Vehicle02 item : dbItems){

            final String objType = item.getObjType();

            final float x = item.position.x;
            final float y = item.position.y;
            final float z = item.position.z;

            final float dx = item.velocity.x;
            final float dy = item.velocity.y;
            final float dz = item.velocity.z;

            dbHelper02.insertRow(objType,x,y,z,dx,dy,dz);

            System.out.println("item: " + item + "\tx: " + x + "\ty: " + y + "\tz: " + z);
            System.out.println("objType: " + objType + "\tdx: " + dx + "\tdy: " + dy + "\tdz: " + dz);
        }
    }

    /**
     * add JDO objects to Database
     */
    public void insertJdoObjects02(){
        ManageJDO01 g = new ManageJDO01("Shapes01.odb");
        System.out.println("here?");

        for (Vehicle02 objects : dbObjects){

            objects.setObjX(objects.getPosition().x);
            objects.setObjY(objects.getPosition().y);
            objects.setObjZ(objects.getPosition().z);
            objects.setObjDx(objects.velocity.x);
            objects.setObjDy(objects.velocity.y);
            objects.setobjDz(objects.velocity.z);
            g.saveNew(objects);
        }
    }


    /**
     * if using SQL, call this method to start objects
     */
    public void startSQLdbObjects(){

        try {
            ArrayList<Vehicle02> items = dbHelper02.createObjs02();

            for (Vehicle02 obj : items){

                System.out.println( obj.getObjX());
                System.out.println(obj.getObjType());
                System.out.println("obj end");
                addDbMeshOnScreen(
                        obj.getObjType(),
                        obj.getObjX(),
                        obj.getObjY(),
                        obj.getObjZ(),
                        obj.getObjDx(),
                        obj.getObjDy(),
                        obj.getObjDz());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * if using JDO Database, call this method to start objects
     */
    public void startJdoObjects(){

        ManageJDO01 g = new ManageJDO01("Shapes01.odb");

        List<Object> results;
        results = g.getObjects(Vehicle02.class);
        for (Object obj : results){
            Vehicle02 obj2 = (Vehicle02) obj;

            obj2.setDefaultMesh();
            obj2.setRotateAndScale();
            obj2.setPosition(obj2.getObjX(), obj2.getObjY(), obj2.getObjZ());
            obj2.setVelocity(obj2.getObjDx(), obj2.getObjDy(), obj2.getObjDz());
            scene.addGameItem(obj2);

            g.dumpObjects(Vehicle02.class);

        }

        System.out.println("results here-> "+results + "<- results here!!!");

    }

    /**
     * for SQL databse to create items
     * @param objType
     * @param x
     * @param y
     * @param z
     * @param dx
     * @param dy
     * @param dz
     */
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // set flag to indicate to remove all meshes.
    boolean removeAll = false;

    /**
     * GameEngine.java
     * @param window
     */
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


    /**
     * close window and save objects
     */
    @Override
    public void cleanup() {
        ManageJDO01 g = new ManageJDO01("Shapes01.odb");
//g.emptyDB(Vehicle02.class);
        try {
            insertJdoObjects02();
//            insertJdoObjects();
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
