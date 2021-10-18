package Mancala;
import java.util.*;

public class MancalaGame {
	
	private Player player1;
	private Player player2;
	private Player[] playerList;
	
	/**
	 * Creates a constructor that receives the names of the two players.
	 * @param name1 The name of the first player.
	 * @param name2 The name of the second player.
	 */
	public MancalaGame(String name1, String name2) {
		player1 = new Player(name1);
		player2 = new Player(name2);
		
		playerList = new Player[2];
		playerList[0] = player1;
		playerList[1] = player2;
	}
	
	/**
	 * Starts the game
	 */
	public void playGame() {
		//Initiates the current player to be 0 in the ArrayList of players.
		int currPlayer = 0;
		
		//checks to see that no full side of pits are empty of pebbles and the game should not end.
		do {
			play(currPlayer);
			//Changes the current player to be the opposite of the the original current player.
			currPlayer = (currPlayer + 1) % 2;
		}while(!endGame());
		
		isWinner();
	}
	
	/**
	 * Executes a play of a player.
	 * @param currPlayer The current player 
	 */
	public void play(int currPlayer) {
		boolean again;
		//Gets the pits of the current player and other player and assings it to pits1 and pits2 accordingly.
		Pit[] pits1 = playerList[currPlayer].getPits();
		Pit[] pits2 = playerList[(currPlayer + 1) % 2].getPits();
		
		//Executes as long at is still the same players turn.
		do {
			displayBoard(currPlayer, pits1, pits2);
			again = takeTurn(currPlayer, pits1, pits2);
		}
		while(again);
	}
	
	/**
	 * Displays teh board 
	 * @param currPlayer The player that is currently up.
	 * @param pits1 The pits of the player whose turn it is
	 * @param pits2 The pits of the the player is awaiting his turn.
	 */
	public void displayBoard(int currPlayer, Pit[] pits1, Pit[] pits2) {

		System.out.print("     ");
		for(int i = 5; i >= 0; i--) {
			System.out.print(pits2[i].getTotalPebbles() + "  ");
		}
	
		System.out.println();
		System.out.println(playerList[(currPlayer + 1) % 2].getMancala().getTotalM()+ "\t\t\t" + playerList[currPlayer].getMancala().getTotalM());
		
		System.out.print("     ");
		for(int j = 0; j < 6; j++) {
			System.out.print(pits1[j].getTotalPebbles() + "  ");
		}
	}
	
	/**
	 * The current player actually takes his turn
	 * @param currPlayer The player whose turn it is
	 * @param pits1 The pits of the player whose turn it is
	 * @param pits2 The pits of the the player is awaiting his turn.
	 * @return true, if the current player gets another turn. false, if the players turn is up.
	 */
	public boolean takeTurn(int currPlayer, Pit[] pits1, Pit[] pits2) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("\n" + playerList[currPlayer].getName());
		System.out.println("Please choose a number pit:(1-6)");
		int pitNum = input.nextInt() - 1;
		//Validates the user is entering a number from 1-6 and the pit number is not empty of pits.
		while(pitNum < 0 || pitNum > 5 || pits1[pitNum].getTotalPebbles() == 0) {
			System.out.println("Invalid Choice! Plese pick a number pit:");
			pitNum = input.nextInt() - 1;
		}
		System.out.println();
		
		//Gets the amount of pebbles in the chosen pit
		int pebbleAmount = pits1[pitNum].getTotalPebbles();
		//Removes all the pebbles from the chosen pit
		pits1[pitNum].removePebbles();
		
		//Creates a reference to the last pit by adding the pit number with the pebble amount.
		int lastPit = pitNum + pebbleAmount;
		
		int i, b;
		for(i = pitNum + 1, b = 0; b < pebbleAmount; i++, b++) {
			//Increments pits1 if the pit number is between 1-6.
			if(i % 13 < 6) {
				pits1[i % 13].addPebble();	
				
			//Increments the mancala if the last pit is equal to 6 or 19 which represents the mancala. 
			} else if(i == 6 || i == 19) {
				playerList[currPlayer].getMancala().add1Mancala();
			
			//Increments pits2 if the last pit number is between 7 and 12.
			}else if(i % 13 < 13) {
				pits2[i % 7].addPebble();
			}	
		}		
		
		//Checks to see if the last pit the player landed on is on his side.
		if(lastPit % 13 < 6) {
			
			//Checks to see that the pit that the player landed in has one pebble(the last one he dropped)
			if(pits1[lastPit % 13].getTotalPebbles() == 1) {
				//Gets the amount of pebbles in the opposite pit.
				int opposite = 5 - (lastPit % 13);
				//Adds the amount of pebbles in the opposite pit and the last pit the player dropped.
				playerList[currPlayer].getMancala().addMancala(pits2[opposite].getTotalPebbles() + 1);
				//Removes the pebbles of the opposite pit
				pits2[opposite].removePebbles();
				//Removes the pebble of the pit the player landed on.
				pits1[lastPit % 13].removePebbles();
			}	
		}	

		//Checks to see if the player landed in his mancala and if the game should end
		if((lastPit == 6 || lastPit == 19) && !endGame()) {
			return true;
		}
		return false;
	}

	/**
	 * Checks to see if the game should end - if a players pits are all empty.
	 * @return true  if game should end, false if game should continue.
	 */
	public boolean endGame() {
		if(player1.allEmpty() || player2.allEmpty()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Calculates the winner and displays the winner
	 */
	public void isWinner() {
		
		//Adds up the remaining pits of the player whose pits are not empty
		if(player1.allEmpty()) {
			int extraPeb2 = player2.addRemainingPits();
			System.out.println(extraPeb2);
			player2.getMancala().addMancala(extraPeb2);
		} else {
			int extraPeb1 = player1.addRemainingPits();
			System.out.println(extraPeb1);
			player1.getMancala().addMancala(extraPeb1);
		}
		
		//Gets the total of each players mancala.
		int player1Total = player1.getMancala().getTotalM();
		int player2Total = player2.getMancala().getTotalM();
		
		System.out.println(player1.getName() + "'s Total: " + player1Total);
		System.out.println(player2.getName() + "'s Total: " + player2Total);
		
		//Calculates the winner based on the players total pebbles in their mancala.
		if(player1Total > player2Total) {
			System.out.println("Congratulations " + player1.getName() + ", you are the winner!");
		} else if (player2Total > player1Total) {
			System.out.println("Congratulations " + player2.getName() + ", you are the winner!");
		}else {
			System.out.println("The game tied!");
		}
	}
	
}
