
package Zombies;
/**
 * The WalkingZombie is a class which extends Zombies and uses the methods from zombies.  
 * This type of zombie walks at a slow pace hence the name.
 * Setting an abstract class for both Plants and Zombies allows for high cohesion.
 * 
 * @author Group 1
 * @since November 4,2018

 */
public class WalkingZombie extends Zombies{
	
/*Initial Status for WalkingZombie
 *  speed = 1 tile for 2 turns,
 *  health = 100
 *  attackDamage = 50
 *  Weapon = null;
 * 
 */
	
	public WalkingZombie() {

		super.setName("WalkingZombie");
		super.setStringtype("WK");

		super.setSpeed(1);
		super.setHealth(100);
		super.setAttackDamage(50);
		super.setWeapon(null);
	}
}
