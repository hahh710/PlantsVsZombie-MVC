package Model;

import java.io.Serializable;

import Plants.Chomper;
import Plants.PotatoMine;
import Plants.PlantStore;
import Plants.Plants;
import Plants.ShootingPlant;
import Plants.Sunflower;
import Zombies.RugbyZombie;
import Zombies.SprintZombie;
import Zombies.WalkingZombie;
import Zombies.Zombies;

public class Action implements Serializable {

	//private Layout layout;
	//private Zombies zombie;
	//private int health;
	//private Status status;


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Action() {
		//this.layout = lay;
		//status = Status.inProgress;
	}


	/**
	 * startAction which handles results of the plants, zombies and game.
	 *
	 * @param  layout  layout of the game
	 * @param  gameTurn the current turn
	 * @return      result of the game
	 */
	public Layout startAction(Layout layout) {
		//this.currentTurn = gameTurn;
		Layout lay = plantShoot(layout);
		lay = behaveZombie(layout);
		lay = updateStatus(layout);
		return lay;
	}	

	public Layout updateStatus(Layout layout) {
		if(layout.getStatus() == Status.start) {
			return layout;
		}else {
			for(int i=0;i<layout.getGameGrid().length;i++) {
				if(layout.getGameGrid()[i][0] instanceof Zombies) {
					layout.setStatus(Status.loose);
					return layout;
				}
			}
			for(int i=0;i<layout.getGameGrid().length;i++) {
				for(int j=0;j<layout.getGameGrid()[0].length;j++) {
					if(layout.getGameGrid()[i][j] instanceof Zombies) {
						layout.setStatus(Status.inProgress);
						//this.status= Status.inProgress;
						//break;
						return layout;
					}	
				}
			}
		}
		//this.status = Status.win;
		layout.setStatus(Status.win);
		return layout;
	}
	/**
	 * plantShoot which handles the shooting plants abilities.
	 *
	 * @param None
	 * @return None
	 */
	public Layout plantShoot(Layout layout) {
		Zombies zombie;
		int health;
		for(int i=0 ; i <layout.getGameGrid().length; i++) {
			for(int j=0; j < layout.getGameGrid()[0].length ; j++) {
				if (layout.getGameGrid()[i][j] instanceof Sunflower) {
					Plants temp = (Plants)layout.getGameGrid()[i][j];

				}if (layout.getGameGrid()[i][j] instanceof ShootingPlant) { // search if plant is a shooter
					for (int index = 0; index < layout.getGameGrid()[i].length; index++) { // iterate through that plant shooter's
						// row to find a zombie
						if (layout.getGameGrid()[i][index] instanceof Zombies) { // zombies in the same row
							ShootingPlant attackingPlant = new ShootingPlant(); // making instance to figure out the
							attackingPlant = (ShootingPlant) (layout.getGameGrid()[i][j]);
							zombie = (Zombies) (layout.getGameGrid()[i][index]); // get instance of zombie
							health = zombie.getHealth();
							zombie.setHealth(health - attackingPlant.getDamage()); // reduce health

							if (zombie.getHealth() <= 0) {
								//buttonArray[i][index].setText("");
								layout.getGameGrid()[i][index] = null; // zombie dead

							}else { 
								zombie.getHealth(); 
							}
							// int healthUpdate = health - attackingPlant.getDamage();
							System.out.println("Zombie at " + i + " " + index + " " + "has " + zombie.getHealth()  + " health");

							//break;
							return layout;
						}
					}
				}else if (layout.getGameGrid()[i][j] instanceof PotatoMine) { // search if plant is a PotatoeMine Object
					for (int index = 0; index < layout.getGameGrid()[i].length; index++) { // iterate through that plant row to find a zombie
						if (layout.getGameGrid()[i][index] instanceof Zombies) { // zombies in the same row

							zombie = (Zombies) (layout.getGameGrid()[i][index]); // get instance of zombie

							if (layout.getGameGrid()[i][index] == layout.getGameGrid()[i][j+1]) { //if that instance is in the next index of the array 
								//of the potato mine, kill the zombie

								//buttonArray[i][index].setText("");
								layout.getGameGrid()[i][index] = null; // zombie dead
								System.out.println(zombie.getStringtype() + "is dead.");

								//buttonArray[i][j].setText("");
								layout.getGameGrid()[i][j] = null; //plant destroyed

							}
							//break;
							return layout;
						}

					}

				}
				else if (layout.getGameGrid()[i][j] instanceof Chomper) { // search if plant is a PotatoeMine Object

					for (int index = 0; index < layout.getGameGrid()[i].length; index++) { // iterate through that plant row to find a zombie
						if (layout.getGameGrid()[i][index] instanceof Zombies) { // zombies in the same row
							Chomper attackingPlant = new Chomper(); // making instance to figure out the
							attackingPlant = (Chomper) (layout.getGameGrid()[i][j]);
							zombie = (Zombies) (layout.getGameGrid()[i][index]); // get instance of zombie

							if (layout.getGameGrid()[i][index] == layout.getGameGrid()[i][j+1]) { //if that instance is in the next index of the array 
								//of the chomper plant, attack the zombie

								health = zombie.getHealth();
								zombie.setHealth(health - attackingPlant.getDamage()); // reduce health
								int healthUpdate = health - attackingPlant.getDamage();
								System.out.println("Zombie at " + i + " " + index + " " + "has " + healthUpdate + " health");
								if (zombie.getHealth() <= 0) {
									//buttonArray[i][j+1].setText("");
									layout.getGameGrid()[i][j+1] = null; // zombie dead
									System.out.println(zombie.getStringtype() + "is dead.");
								}
								if (attackingPlant.getHealth() <= 0) {
									//buttonArray[i][j].setText("");
									layout.getGameGrid()[i][j] = null;
								}
							}
							//break;
							return layout;
						}
					}
				}
			}
		}
		return layout;
	}


	/*if(layout.getGameGrid()[i][j] instanceof ShootingPlant) { //search if plant is a shooter
				for (int index =0 ; index<layout.getGameGrid()[i].length; index++) { // iterate through that plant shooter's row to find a zombie
					if (layout.getGameGrid()[i][index] instanceof Zombies) { // zombies in the same row
						ShootingPlant attackingPlant = new ShootingPlant(); // making instance to figure out the attack damage of the plant
						zombie = (Zombies) (layout.getGameGrid()[i][index]); //get instance of zombie
						health = zombie.getHealth();
						zombie.setHealth(health - attackingPlant.getDamage()); // reduce health 
						int healthUpdate = health - attackingPlant.getDamage();
						System.out.println("Zombie at "+ i + " " + j + " " + "has " + healthUpdate + " health");
						if (zombie.getHealth() <= 0) {
							layout.getGameGrid()[i][index] =null; // zombie dead
							System.out.println(zombie.getStringtype() + "is dead." );
						}
					}
				}						 
			}*/
	/** behaveZombie - Zombie will move or attack if it is able. 
	 * Check List
	 *- check front tile is empty (doesn't matter zombie reaches [i][0] because isgameovesr() would check everytime)
	 *		-check Zombie is movable
	 *			-yes move 
	 *- check front tile is plant
	 *		-check Zombie is movable
	 *			-yes attack 
	 *				- invoke ZombieAttack(), replace Plant object to returned plant 	 *
	 * 
	 * @param  None
	 * @return None
	 */
	public Layout behaveZombie(Layout layout) {
		//turn = new Turn();
		for(int i=0 ; i <layout.getGameGrid().length; i++) {
			for(int j=1; j < layout.getGameGrid()[0].length ; j++) { // J starts at 1 because if it is 0, it will get error. 
				if(layout.getGameGrid()[i][j] instanceof Zombies){ // checking that is Zombie class
					Zombies temp = (Zombies)layout.getGameGrid()[i][j]; // Copying the zombie object
					//if(turn.canZombieMove(temp,currentTurn)){ // checking the Zombie Object is movable.
					if(layout.getGameGrid()[i][j-1] == null) {
						layout.setObject(i, j, null); // empty the previous spot
						layout.placeObjectOnGrid(i, j-1, temp);	//place zombie
					}else if (layout.getGameGrid()[i][j-1] instanceof Zombies) { // later we can pile up 2 zombies
						System.out.println("There is a Zombie in front of");
					}
					else if (layout.getGameGrid()[i][j-1] instanceof Plants) { // Attacking the Plant! Using zombieAttack();
						Plants at = (Plants)zombieAttack((Zombies)layout.getGameGrid()[i][j],(Plants)layout.getGameGrid()[i][j-1]);
						if(at.getHealth() <= 0) {
							layout.setObject(i, j-1, at);
						}
					}else {
						System.out.println("error here");
					}
				}
			}
		}
		return layout;
	}







	/** Zombie Attacks(zombie, Plant) - Zombie attacks plant, this function is used in behaveZombie();
	 * - zombie Attack p
	 * - check Plant health <= 0 
	 * 		- yes; return null; 
	 * 		- no ; return attackedplant;
	 *
	 * @param  z  an absolute URL giving the base location of the image
	 * @param  p the location of the image, relative to the url argument
	 * @return      the image at the specified URL
	 * @see         Image
	 */
	public Plants zombieAttack(Zombies z, Plants p) {
		Plants attackedPlant = p;
		attackedPlant.attacked(z.attack());
		System.out.println(p.getName()+ " gets attacked by " + z.getName() +"!!");
		System.out.println("Plant has " + attackedPlant.getHealth() + " health");
		if (attackedPlant.getHealth() <= 0) {
			System.out.println(attackedPlant.getName() + " is killed by " + z.getName() + "!!");
			//attackedPlant = null;
		}
		return attackedPlant;
	}



	/** isGameOver
	 * Returns true or false depending on whether the game is over or not
	 *
	 * @param  None
	 * @return  Boolean
	 */
	public Boolean isGameOver(Layout layout) {
		for(int i=0 ; i <layout.getGameGrid().length; i++) {
			if(layout.getObject(i,0) instanceof Zombies) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean gameClear(Layout layout) {
		Boolean flag = true;
		for(int i=0 ; i <layout.getGameGrid().length; i++) {
			for(int j=0; j < layout.getGameGrid()[0].length ; j++) {
				if(layout.getObject(i,j) instanceof Zombies) {
					flag = false;
				}
			}
		}
		return flag;
	}

}