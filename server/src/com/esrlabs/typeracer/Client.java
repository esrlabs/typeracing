package com.esrlabs.typeracer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {

	private final Socket client;
	private BufferedReader fromClient;
	private PrintWriter toClient;
	private final String name;

	public Client(Socket client) throws Exception {
		this.client = client;
		fromClient = new BufferedReader(new InputStreamReader(
				client.getInputStream(), "UTF-8"));
		toClient = new PrintWriter(client.getOutputStream());
		name = parseConnectCommand(fromClient.readLine());
		System.out.println("name = '" + name + "'");
		start();
	}

	@Override
	public void run() {
		
		try {
			while (true) {
				int ch = fromClient.read();
				
				if(ch == -1) {
					break;
				}
				System.out.println("Char = " + (char)ch + " int=" + ch);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	String parseConnectCommand(String readLine) {
		int first = readLine.indexOf(' ');
		return readLine.substring(first + 1);
	}

	public void sendCountdown(int i) {
		toClient.format("countdown %s\n", i);
		toClient.flush();
	}

	public void go(String racetext) {
		toClient.format("go %s\n", racetext);
		toClient.flush();
	}

	private void sendToClient(String format, Object... args) {
		toClient.format(format, args);
		toClient.flush();
	}

}
