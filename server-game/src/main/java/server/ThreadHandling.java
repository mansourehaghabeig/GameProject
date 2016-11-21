package server;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * ThreadHandling class is responsible for handling each client as a seprated thread.
 * @author Mansoureh Aghabeig
 *
 */
public class ThreadHandling extends Thread {
	private Socket clientSocket; // socket for each client.
	private int numberOfBoxes;   // numberOfBoxes of game
	private boolean notGameOver; // define if the game over for the current client
	private GameLogic gameLogic; // instance of GameLogic class

	/**
	 * Main constructor which is setting:
	 * <li> socket for the current client </li>
	 * <li> numberOfBoxes for game </li>
	 * <li> generate an instance from GameLogic class </li>
	 * <li> setting current situation of game to not finished yet </li>
	 *  
	 * @param clientSocket
	 * @param numberOfBoxes
	 */
	public ThreadHandling(Socket clientSocket, int numberOfBoxes){
		this.clientSocket = clientSocket;
		this.numberOfBoxes = numberOfBoxes;
		this.notGameOver = true;
		this.gameLogic = new GameLogic();
	}

	/**
	 * run the current thread:
	 * <li> send numberOfBoxes to client application </li>
	 * <li> send current situation of game to client application </li>
	 */
	public void run(){

		try {
			OutputStream outputStream = clientSocket.getOutputStream();
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
			BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
			bufferedWriter.write(numberOfBoxes + "\n");
			bufferedWriter.flush();

			// Repeat the loop till game will be finished (reach to game over property)
			while(notGameOver) {
				try {
					String response = gameLogic.makeStuffHappen();
					if (response.contains("over")) {
						notGameOver = false;
					}
					bufferedWriter.write(response + "\n");
					bufferedWriter.flush();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
