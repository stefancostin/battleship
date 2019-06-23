package io.github.stefancostin.battleship.game.multiplayer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class Server extends Player {
	ServerSocket serverSocket;
	
	public void run() {
		System.out.println("Server waiting for client...");
		try {
			serverSocket = new ServerSocket(9006);
			serverSocket.setReuseAddress(true);
//			serverSocket.bind(new InetSocketAddress(9006));
			socket = serverSocket.accept();
			System.out.println("Client connection established.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.close();
	}
	
	public void close() {
		try {
			serverSocket.close();
			System.out.println("closed");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
