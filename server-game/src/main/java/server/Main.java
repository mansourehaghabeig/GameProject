package server;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Main class is responsible for: 
 * <li> reading numberOfBoxes from config file </li>
 * <li> running server on machine </li>
 * 
 * @author Mansoureh Aghabeig
 *
 */
public class Main {

	/**
	 * Read connecting information and generate an instance of server class
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Main rp = new Main();  
		String[] configInformation = new String[2];
		configInformation = rp.getPropValues();
		new Server(configInformation[0], configInformation[1]);
		Server.main(null);
	}

	InputStream inputStream;
	/**
	 * Read from Config file information of server which is: 1- numberOfBoxes, 2- ServerPort
	 * @return Config Information
	 * @throws IOException
	 */
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
			configInformation[0] = prop.getProperty("NumberOfBox");
			configInformation[1] = prop.getProperty("ServerPort");

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
			return configInformation;
		}
	}


}
