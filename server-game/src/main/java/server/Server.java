package server;
import java.net.*;

/**
 * Server class is responsible:
 * <li> Start Server </li>
 * <li> Assign a new Thread to each client </li>
 *
 * @author Mansoureh Aghabeig
 *
 */
public class Server
{

	private static int numberOfBoxes;  // define number of gift boxes
	private static int serverPort;    //  define server port of server

	/**
	 * main constructor which set numberOfBoxes for game.
	 * @param numberOfBoxes
	 */
	public Server(String numberOfBoxes, String serverPort){
		this.numberOfBoxes = Integer.parseInt(numberOfBoxes);
		this.serverPort = Integer.parseInt(serverPort);
	}

	/**
	 * main method defines serverSocket and start a thread for each client.
	 * @param argv
	 * @throws Exception
	 */
	public static void main(String argv[]) throws Exception
	{
		System.out.println(" Server is Running  " );
		ServerSocket serverSocket = new ServerSocket(serverPort);

		while(true)
		{
			final Socket clientSocket = serverSocket.accept();
			ThreadHandling thread = new ThreadHandling(clientSocket, numberOfBoxes);
			thread.start();

		}
	}

}
