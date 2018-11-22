
package Zombies;
/**
 * The SprintZombie is a class which extends Zombies and uses the methods from zombies.  
 * This type of zombie walks at a fast pace hence the name.
 * Setting an abstract class for both Plants and Zombies allows for high cohesion.
 * 
 * @author Group 1
 * @since November 4,2018

 */
public class SprintZombie extends Zombies{
	
/*Initial Status for SprintZombie
 *  speed = 5 tile for 2 turns,
 *  health = 100
 *  attackDamage = 50
 *  Weapon = null;
 * 
 */
	
	public SprintZombie() {

		super.setName("SprintZombie");
		super.setStringtype("SZ");

		super.setSpeed(5);
		super.setHealth(100);
		super.setAttackDamage(50);
		super.setWeapon(null);
	}
}
