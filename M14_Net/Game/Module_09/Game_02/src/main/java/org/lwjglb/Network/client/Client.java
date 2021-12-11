package org.lwjglb.Network.client;


//import java.io.BufferedReader;
import org.lwjglb.engine.items.Vehicle02;
import org.lwjglb.game.ManageJDO01;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;


public class Client {

	private static Socket clnt;

	public static void main(String[] args) {
		System.out.print(RunSocketClient());
	}

	/**
	 * Get Vehicle02 object from server
	 * @return
	 */
	public static String RunSocketClient() {
		try {
			System.out.println("Socket started up");
			clnt = new Socket("192.168.0.164",4444);  // IP Address of the server

			InputStream inputStream = clnt.getInputStream();

			ObjectInputStream objIn = new ObjectInputStream(inputStream);

			Vehicle02 timcar = new Vehicle02();

			try {
				Object obj01 = objIn.readObject();

				System.out.println("here is obj01(from server) before casting: "+obj01);
				Vehicle02 obj = (Vehicle02) obj01;
//
				obj.getObjX();
				System.out.println(obj.getObjX());


				obj.setDefaultMesh();
				obj.setRotateAndScale();
				obj.setPosition(obj.getObjX(), obj.getObjY(), obj.getObjZ());
				obj.setVelocity(obj.getObjDx(), obj.getObjDy(), obj.getObjDz());
//				scene.addGameItem(obj);

				//add to db for later
				obj.setObjX(obj.getPosition().x);
				obj.setObjY(obj.getPosition().y);
				obj.setObjZ(obj.getPosition().z);
				obj.setObjDx(obj.velocity.x);
				obj.setObjDy(obj.velocity.y);
				obj.setobjDz(obj.velocity.z);


				ManageJDO01 g = new ManageJDO01("Shapes01.odb");
				g.saveNew(obj);


			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}


			String fromServer = "hi from client end of runsocketClient";
//			fromServer = in.readLine();

			return fromServer;
		} catch (IOException e) {
			return "nothing";
		}
	}
}