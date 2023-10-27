package com.matheusdoedev.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
	private Socket socket;
	
	public ClientHandler(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			Scanner scan = new Scanner(System.in);
			boolean isFinish = true;
			
			while (isFinish) {
				String response = in.readLine();
				System.out.println("User message: " + response);
				
				System.out.println("Enter your message:");
				String message = scan.next();
				out.println(message);
				
				if (response.toUpperCase().equals("BYE")) {
					isFinish = false;
				}
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		} finally {
			try {
				socket.close();
				System.out.println("Connection close with the client: " + socket.getInetAddress().getHostAddress());	
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
	}
}
