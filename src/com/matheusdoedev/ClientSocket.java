package com.matheusdoedev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket {
	private final static String HOSTNAME = "localhost";
	private final static int PORT = 1234;
	
	public static void main() {
		try {
			Socket socket = new Socket(HOSTNAME, PORT);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			Scanner scan = new Scanner(System.in);
			boolean isFinish = true;
			
			while (isFinish) {
				// send a message to the server
				System.out.println("Enter a message:");
				String msg = scan.next();
				out.println(msg);
				
				// read the server response
				String response = in.readLine();
				System.out.println("Server message: " + response);
				
				if (msg.toUpperCase().equals("BYE")) {
					isFinish = false;
					socket.close();
					scan.close();
				}
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
