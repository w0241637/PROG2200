package org.lwjglb.Network.server;

//import java.io.BufferedReader;
import org.lwjglb.engine.items.Vehicle02;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

class Server {


	public static void main(String[] args) {
//	    String ip = "192.168.0.194";// getIpAddress();
		String ip = "192.168.0.164";// getIpAddress();

		String s = null;






		System.out.println("Server IP => " + ip);

//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		Vehicle02 timcar = new Vehicle02("Vehicle02",1,2,3,0,0,0);
		Vehicle02 timcar = new Vehicle02();


		java.util.Random r = new java.util.Random();

		timcar.setObjX(5.00f * r.nextFloat());
		timcar.setObjY(5.00f * r.nextFloat());
		timcar.setObjZ(5.00f * r.nextFloat());
		timcar.setObjDx(0.005f * r.nextFloat());
		timcar.setObjDy(0.005f * r.nextFloat());
		timcar.setobjDz(0.005f * r.nextFloat());

		System.out.print("Enter String to send to client => ");
//					s = br.readLine();
//		s = timcar.returnHello();


		if (ip != "false") {
			try {
				ServerSocket srvr = new ServerSocket(4444);

				Socket client = null;
				try {

					client = srvr.accept();  //Sit and wait





					PrintWriter out = new PrintWriter(client.getOutputStream(), true);

					OutputStream outputStream = client.getOutputStream();
					ObjectOutputStream objOut = new ObjectOutputStream(outputStream);
					//client.
					System.out.print("Sending ip address: '" + ip + "'\n");

					objOut.writeObject(timcar);
					objOut.close();


					out.print(ip+" says " + s);
					out.close();
					client.close();
					srvr.close();
				} catch(Exception e) {
					System.out.print(String.format("Accept failed: %s",e));
				}
			} catch (Exception e) {
				System.out.print(String.format("Could not listem on port: %s",e));
			}
		}
		else
		{
			System.out.print("Could not get ip address");
		}
	}

	public static String getIpAddress() {
		InetAddress host;
		String ipString = "false";

		try {
			host = InetAddress.getLocalHost();
			ipString = host.getHostAddress();
		} catch (UnknownHostException e) {
			System.out.println(e);
		}

		return ipString;
	}
}