package com.esrlabs.typeracer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Race implements Runnable {

	private int socket = 0;
	private int racers = 0;
	private ServerSocket server = null;

	public Race(int socket, int racers) {
		this.socket = socket;
		this.racers = racers;
	}

	public void run() {
		try {

			startServer();
			Client[] clients = waitForClients();
			startRace(clients);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void startRace(Client[] clients) throws Exception {
		countdownClients(clients);
		go(clients);
		
	}

	private void go(Client[] clients) {
		for (Client client : clients) {
			client.go("We are awesome!");
		}
	}

	private void countdownClients(Client[] clients) throws Exception {
		for (int i = 3; i > 0; --i) {
			for (Client client : clients) {
				client.sendCountdown(i);
			}
			Thread.sleep(1000);
		}
	}

	private Client[] waitForClients() throws Exception {
		Client[] clients;
		clients = new Client[racers];
		for (int i = 0; i < racers; ++i) {
			clients[i] = new Client(server.accept());
		}
		return clients;
	}

	private void startServer() throws Exception {
		server = new ServerSocket(socket);

	}
}
