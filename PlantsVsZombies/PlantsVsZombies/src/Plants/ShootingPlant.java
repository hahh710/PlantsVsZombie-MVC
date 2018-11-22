

package Plants;
/**
 *  The ShootingPlant is a class which extends Plants and uses the methods from plants. 
 * ShootingPlant is a type of plant which shoots zombies which are in its row and cause them to lose health.
 * 
 * @author Group 1
 * @since November 4,2018

 */

public class ShootingPlant  extends Plants{
	

	private final int cost = 100;
	private final int sunPoints = 50;
	private int health = 100;
	private static int turn;
	
	public ShootingPlant() {
		super.setStringtype("SP");
		super.setDamage(25);
		super.setHealth(100);
		super.setCost(cost);
		super.setName("ShootingPlant");
	}
	public ShootingPlant(int health){
		this.health = health;
		super.setDamage(25);
		super.setStringtype("F    ");
		super.setName("ShootingPlant");
	}
	public int getCost() {
		return cost;
	}
	
	public static int getTurn() {
		return turn;
	}
	public static void setTurn(int turn) {
		ShootingPlant.turn = turn;
	}

}
