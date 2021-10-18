package Mancala;

public class Player {
	private String name;
	private Pit[] pits;
	private Mancala mancala;
	
	/**
	 * Creates a constructor that initializes name, mancala, and pits array
	 * @param name The name of the user.
	 */
	public Player(String name) {
		this.name = name;
		mancala = new Mancala();
		pits = new Pit[6];
		//Fills the pits array with type Pits objects
		for(int i = 0; i < pits.length; i++) {
			pits[i] = new Pit();
		}
	}
	
	/**
	 * Returns the Pits array.
	 * @return An array of pits.
	 */
	public Pit[] getPits() {
		return pits;
	}
	
	/**
	 * Returns the name of the player 
	 * @return The name of a player
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the player's Mancala object.
	 * @return The player's mancala
	 */
	public Mancala getMancala() {
		return mancala;
	}
	
	/**
	 * Adds whatever pits are left and adds them to the players mancala.
	 * @return The sum of the remaining pebbles in the pit.
	 */
	public int addRemainingPits() {
		int sum = 0;
		for(int i = 0; i < pits.length; i++) {
			sum += pits[i].getTotalPebbles();
		}
		return sum;
	}
	
	/**
	 * Checks to see if all the pits totals are equal to 0.
	 * @return returns true if all the pits totals equal zero
	 */
	public boolean allEmpty(){
		boolean allEmpty = true;
		for(int i = 0; i < pits.length; i++) {
			if(pits[i].getTotalPebbles() != 0) {
				allEmpty = false;
			}
		}
		return allEmpty;
	}
}
