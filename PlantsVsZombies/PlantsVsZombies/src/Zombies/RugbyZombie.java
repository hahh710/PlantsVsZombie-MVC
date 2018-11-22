
package Zombies;
/**
 * The WalkingZombie is a class which extends Zombies and uses the methods from zombies.  
 * This type of zombie walks at a slow pace hence the name.
 * Setting an abstract class for both Plants and Zombies allows for high cohesion.
 * 
 * @author Group 1
 * @since November 4,2018

 */
public class RugbyZombie extends Zombies{
	
/*Initial Status for WalkingZombie
 *  speed = 1 tile for 2 turns,
 *  health = 150
 *  attackDamage = 80
 *  Weapon = null;
 * 
 */
	
	public RugbyZombie() {

		super.setName("RugbyZombie");
		super.setStringtype("RZ");

		super.setSpeed(1);
		super.setHealth(150);
		super.setAttackDamage(80);
		super.setWeapon(null);
	}
}
