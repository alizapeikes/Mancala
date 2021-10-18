package Mancala;

public class Mancala {
	private int totalM;
	
	/**Creates an constructor and sets the Total of the mancala to zero
	 */
	public Mancala() {
		totalM = 0;
	}
	
	/**Adds the parameter amount to the mancala.
	 * @param amount The amount of pebbles to add to the mancala.
	 */
	public void addMancala(int amount) {
		totalM += amount;
	}
	
	/**
	 * Adds one pebble to the mancala.
	 */
	public void add1Mancala() {
		totalM++;
	}
	
	/**
	 * Gets the total amount of pebbles in the mancala.
	 * @return
	 */
	public int getTotalM() {
		return totalM;
	}
}
