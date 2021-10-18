package Mancala;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Welcome to Mancala!\n");
		
		//Asks the users to enter names for the two players
		System.out.println("Please enter the name of player 1: ");
		String player1 = input.nextLine();
		
		System.out.println("Please enter the name of player 2: ");
		String player2 = input.nextLine();
		
		//Creates MancalaGame object/
		MancalaGame game = new MancalaGame(player1, player2);
		System.out.println("Press enter to start the game!");
		input.nextLine();
		
		//Starts the game.
		game.playGame();
		
	}
}
