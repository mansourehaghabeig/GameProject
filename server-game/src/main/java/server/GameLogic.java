package server;
import java.util.Random;

/**
 * GameLogic class is responsible to specify logic of game.
 * * @author Mansoureh Aghabeig
 *
 */

public class GameLogic {
	private int sumProfit = 0; // the amount of profit, client can obtain
	String[] valueGame = {"100","200","200","500","500","500","500","500","more","over","over","over"};  // different situation may happen in game
	int[] chkValueGame ={1,1,1,1,1,1,1,1,1,1,1,1}; // define which situation in game is used or not used.
	
	/**
	 * Assign an unused situation to the clicked boxes and define current situation in the game.
	 * @param data (the idOfBoxes client sends to server
	 * @return the current situation in game
	 */
	public String makeStuffHappen() {
		
		int randomValue;
		boolean exitLoop =  true;  // define when should exit from loop for assigning situation to clicked box.
		do {
			Random r = new Random();
			randomValue = r.nextInt(12);
			if (chkValueGame[randomValue]== 1){
				exitLoop = false;
				chkValueGame[randomValue] = 0;
			}
		}while (exitLoop);

		if (valueGame[randomValue] == "more"){
			return "more" + sumProfit;
		}

		if (valueGame[randomValue] == "over"){
			return "over" + sumProfit;
		}

		sumProfit += Integer.parseInt(valueGame[randomValue]);
		return Integer.toString(sumProfit);

	}

}
