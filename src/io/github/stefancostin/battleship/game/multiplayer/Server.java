package io.github.stefancostin.battleship.game.multiplayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;

import io.github.stefancostin.battleship.utils.ClosedConnectionException;
import io.github.stefancostin.battleship.utils.Constants;

public class Server extends Player {
	private BufferedReader br;
	private InputStreamReader in;
	private PrintStream ps;
	private Socket socket;
	private ServerSocket serverSocket;
	
	public void run() throws IOException {
		System.out.println("Server waiting for client...");
		try {
			// Establishing connection
			serverSocket = new ServerSocket(Constants.PORT);
			serverSocket.setSoTimeout(15000);
			socket = serverSocket.accept();
			System.out.println("Client connection established.");
			
			// Establishing Input Drivers
			in = new InputStreamReader(socket.getInputStream());
			br = new BufferedReader(in);
			
			// Establishing Output Drivers
			ps = new PrintStream(socket.getOutputStream());
		} catch (SocketTimeoutException e) {
			System.out.println("Client connection timout. Connection has ended to prevent port from being used.");
		} 
	}
	
	public String read() throws IOException {
		String input = "";
		input = br.readLine();
		if (input == null) {
			throw new ClosedConnectionException("Server has closed the connection.");
		}
		return input;
	}
	
	public void post(String output) {
		output = output == null ? "" : output;
		ps.println(output);
	}
	
	public void close() throws IOException {
		System.out.println("Closing...");
		
		ps.close();
		try {
			br.close();
		} finally {
			try {
				in.close();
			} finally {
				serverSocket.close();
			}
		}	
	}
	
	@Override
	protected void finalize() {
		try {
			this.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
