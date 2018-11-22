

package Plants;
/**
 *  The Sunflower is a class which extends Zombies and uses the methods from zombies. 
 * This allows for high cohesion.
 * 
 * @author Group 1
 * @since November 4,2018

 */
public class Sunflower extends Plants {

	private final int cost  = 50;
	public Sunflower(){
		super.setStringtype("S");
		super.setHealth(100);
		super.setSpeed(1);
		super.setName("Sunflower");
	}

	public int getCost() {
		return cost;
	}
	
	

}
