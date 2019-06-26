package io.github.stefancostin.battleship.game.multiplayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

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
			serverSocket = new ServerSocket(Constants.port);
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
//		catch (IOException e ) {
//			e.printStackTrace();
////			this.close();
//		}
//		finally {			
//			try {
//				this.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
	}
	
	public String read() {
		String input = null;
		try {
			input = br.readLine();
			System.out.println("client said: " + input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}
	
	public void post(String output) {
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
		
//		ps.close();
//		try {
//			br.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				in.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					serverSocket.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}		
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
