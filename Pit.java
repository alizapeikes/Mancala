package Mancala;

public class Pit {
	private int totalPebbles;
	
	/**Creates a constructor that initializes a pit to 4.
	 */
	public Pit() {
		totalPebbles = 4;
	}
	
	/**
	 * Adds one pebble to a pit.
	 */
	public void addPebble() {
		totalPebbles++;
	}
	
	/**
	 * Removes all pebbles from a pit.
	 */
	public void removePebbles() {
		totalPebbles = 0;
	}
	
	/**
	 * Gets the total amount of pebbles in a pit
	 * @return Total amount of pebbles
	 */
	public int getTotalPebbles() {
		return totalPebbles;
	}
}
