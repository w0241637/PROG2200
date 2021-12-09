package org.lwjglb.Network.server;

import org.lwjglb.engine.items.Vehicle02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

class ServerBackup {
	  public static void main(String[] args) {
//	    String ip = "192.168.0.194";// getIpAddress();
		  String ip = "192.168.0.164";// getIpAddress();

		  String s = null;

		  Vehicle02 timcar = new Vehicle02("Vehicle02",1,2,3,0,0,0);





	    System.out.println("Server IP => " + ip);

	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    System.out.print("Enter String to send to client => ");
        try {
			s = br.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}



	    if (ip != "false") {
	        try {
	            ServerSocket srvr = new ServerSocket(4444);

	        Socket client = null;
	        try {

	            client = srvr.accept();  //Sit and wait

				Vehicle02 timcar2 = new Vehicle02("Vehicle02",1,2,3,0,0,0);




				PrintWriter out = new PrintWriter(client.getOutputStream(), true);

	            //client.
	            System.out.print("Sending ip address: '" + ip + "'\n");
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