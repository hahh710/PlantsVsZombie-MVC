package Plants;

import java.io.Serializable;

import Model.CoolDown;
import Model.Layout;

/**
 *  The PlantStore is a class which contains all the methods common between all the plant
 *  purchases and handling the costs on the plants and stores the sun points.
 * 
 * 
 * @author Group 1
 * @since November 4,2018

 */
public class PlantStore implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Plants price;
	private  int sunPoints;
	private CoolDown coolDown;


	public PlantStore() {
		sunPoints = 150;
		coolDown = new CoolDown();
	}
	public PlantStore(int sunp) {
		this.sunPoints = sunp;
	}
	
	public  int getSunPoints() {
		return sunPoints;
	}

	public  void setSunPoints(int sunPoints) {
		this.sunPoints = sunPoints;
	}

	public void nextTurn() {
		coolDown.turnOver();
	}
	public void incrementSunPoints(Layout layout) {
		int sunPlant = 0;
		for(int i=0 ; i <layout.getGameGrid().length; i++) {
			for(int j=0; j < layout.getGameGrid()[0].length ; j++) {
				if(layout.getGameGrid()[i][j] instanceof Sunflower) {
					sunPlant++;
				}
			}
		}
		sunPoints = sunPoints+(sunPlant*50);
	}


	public Plants getPrice() {
		return price;
	}

	public void setPrice(Plants price) {
		this.price = price;
	}
	/*
	public void purchaseStartOfGame(Layout layout , boolean start) {
		if (start == true) {
			hasGameStarted = true;
			sunPoints = 150;
			System.out.println("");
			System.out.println("You have " + sunPoints + " points to start with!");
			System.out.println("Spend wisely.....");
			storeMenu(sunPoints , hasGameStarted , layout);
		}
		else {
			storeMenu(getSunPoints() , hasGameStarted , layout);
		}
		
	
		

	}
	/**
	 * 	validatePurchase, makes sure you can purchase the plant with sufficent SunPoints
	 * 	@plant- which plant you are buying
	 * 	@currentBalnce - the number of currentBalance currently have
	 * 	@return none
	 */
	public boolean validatePurchase(Plants plant ) {
		if((sunPoints - plant.getCost()) >= 0){
			return true;
		}else {
			return false;
		}
	}
	
	public boolean validateCoolDown(Plants plant) {
		return coolDown.validatePurchase(plant);
	}
	
	/**
	 * purchase method, updates the balance of the amount of sunpoints, depending
	 * on which plant has been purchased
	 * 	@param plant - the type of plant that has been bought
	 * 	@param currentBalance - number of sun points the user currently has
	 * 	@return none
	 */
	public String purchase(Plants plant) {
		String info = coolDown.purchasePlant(plant);
		int updatedBalance = getSunPoints() - plant.getCost();
		setSunPoints(updatedBalance);
		return info;
	}

	
	public CoolDown getCoolDown() {
		return coolDown;
	}
}


