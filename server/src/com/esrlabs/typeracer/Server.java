package com.esrlabs.typeracer;

public class Server {
	
	// server <port> <# racers>
	public static void main(String[] args) throws Exception {
		int socket = Integer.parseInt(args[0]);
		int racers = Integer.parseInt(args[1]);
		
		Race race = new Race(socket, racers);

		System.out.println("Race started");
		Thread thread = new Thread(race);
		thread.start();
		
		thread.join();
	}

}
