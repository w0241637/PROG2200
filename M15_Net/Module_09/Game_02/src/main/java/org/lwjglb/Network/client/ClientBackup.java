package org.lwjglb.Network.client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class ClientBackup {

	  private static Socket clnt;

	public static void main(String[] args) {
	    System.out.print(RunSocketClient());
	  }

	  public static String RunSocketClient() {
	    try {
			System.out.println("Socket started up");
//	        clnt = new Socket("192.168.0.194",4444);  // IP Address of the server
			clnt = new Socket("192.168.0.164",4444);  // IP Address of the server

			BufferedReader in = new BufferedReader(new InputStreamReader(clnt.getInputStream()));

	        String fromServer;
	        fromServer = in.readLine();

	        return fromServer;
	    } catch (IOException e) {
	        return "nothing";
	    }
	  }
	}