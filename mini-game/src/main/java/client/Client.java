package client;
import java.io.*;
import java.net.*;

/**
 * Client class is responsible for:
 * <li> getting and sending infromation to server machine </li>
 * @author Mansoureh Aghabeig
 *
 */
public class Client {
	private  Socket  clientSocket; // socket for connecting to server machine.
	private  int numberOfBoxes;    // numberOfBoxes of the mini-game.
	private String serverAddress;  // server address of server
	private String serverPort;     // server port of server
	private InputStream inputStream; 
	private InputStreamReader inputStreamReader;
	private BufferedReader bufferedReader;
	private OutputStream outputStream;
	private OutputStreamWriter outputStreamWriter;
	private BufferedWriter bufferedWriter;
	public String finalProfit;  // final profit client obtains 

	/**
	 * Main constructors of Client class, it invokes the connect method to set a connection to the server.
	 * @throws Exception 
	 */
	public Client(String serverAddress, String serverPort) throws Exception{
		this.serverAddress = serverAddress;
		this.serverPort = serverPort;
		connect(serverAddress,serverPort);
	}
	
	/**
	 * Connects to server machine and define bufferReader and bufferWriter for reading and writing to the server.
	 * @throws Exception
	 */
	public void connect(String ServerAddress, String ServerPort) throws Exception {
		clientSocket = new Socket(ServerAddress,Integer.parseInt(ServerPort));
		inputStream = clientSocket.getInputStream();
		inputStreamReader = new InputStreamReader(inputStream);
		bufferedReader = new BufferedReader(inputStreamReader);

		outputStream = clientSocket.getOutputStream();
		outputStreamWriter = new OutputStreamWriter(outputStream);
		bufferedWriter = new BufferedWriter(outputStreamWriter);
		bufferedWriter.flush();
	}

	/**
	 * Re-start the Game.
	 * @throws Exception
	 */
	public void startGame() throws Exception {
		clientSocket.close();
		connect(serverAddress,serverPort);
		bufferedReader.readLine().trim(); // make empty bufferedReader 
	}

	/** 
	 * calculates the obtained profits of user and sending it to Game class.
	 * @throws Exception
	 */
	public String calculateProfit () throws Exception{
		String data = bufferedReader.readLine().trim();
		return finalProfit = data;
	}

	/**
	 * Gets number of boxes from server and sending to Game class.
	 * @return
	 * @throws Exception
	 */
	public int getBoxes() throws Exception{
		String data = bufferedReader.readLine().trim();
		numberOfBoxes = Integer.parseInt(data);
		return numberOfBoxes;
	}
}
