package client;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Main class is responsible for starting the mini-game.
 * @author Mansoureh Aghabeig
 *
 */
public class Main {

	/**
	 * Read connecting information and generate an instance of Game class
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String[] configInformation = new String[2];
		Main rp = new Main();  
		configInformation = rp.getPropValues();
		new Game(new Client(configInformation[0],configInformation[1])).createAndDisplayGUI();
	}

	/**
	 * Read Server information from Config file which contains: 1- Server Address 2- Server Port.
	 * @return Server information
	 * @throws IOException
	 */
	InputStream inputStream;
	@SuppressWarnings("finally")
	public String[] getPropValues() throws IOException {
		String[] configInformation = new String[2];

		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			// get the property value and print it out
			configInformation[0] = prop.getProperty("ServerAddress");
			configInformation[1] = prop.getProperty("ServerPort");

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
			return configInformation;
		}
	}
}
