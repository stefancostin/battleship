package io.github.stefancostin.battleship.game.multiplayer;

import java.io.IOException;
import java.net.Socket;

public class Client extends Player {

	public void run() {
		try {
			socket = new Socket("localhost", 9086);
			System.out.println("Client started.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
	}
	
	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
