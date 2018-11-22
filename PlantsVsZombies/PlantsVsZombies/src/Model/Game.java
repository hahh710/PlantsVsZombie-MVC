package Model;

import java.util.InputMismatchException;
import java.util.Scanner;

import Controller.TimeLine;
import Plants.Chomper;
import Plants.PlantStore;
import Plants.Plants;
import Plants.PotatoMine;
import Plants.ShootingPlant;
import Plants.Sunflower;
import Zombies.Zombies;
import Zombies.RugbyZombie;
import Zombies.SprintZombie;
import Zombies.WalkingZombie;

import java.util.*;
import java.io.*;  

public class Game implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Layout layout;


	private PlantStore store;
	private Action action;
	private Plants purchasedPlant;
	//private TimeLine timeline;
	private int level;
	//private int gameTurns = 1;
	private int zombieCounter;

	public Game() {
		this.layout = new Layout();
		this.store = new PlantStore();
		purchasedPlant = null;

	}

	public Game(Game game) {
		this(game.getLayout(),game.getStore(),game.getZombieCounter(),game.getLevel());
	}

	public Game (Layout layout , PlantStore store,int zombieCounter,int level) {
		this.layout = layout;
		this.store = store;
		this.zombieCounter = zombieCounter;
		this.level =level;
	}

	public String purchasePlant(String plant,boolean moneyFlag,boolean coolDownFlag) {
		Plants ps = stringToObject(plant);
		String info="";
		if(moneyFlag && coolDownFlag) {	
			info = store.purchase((Plants)ps) ;
			purchasedPlant = (Plants)ps;
			//info= info + "\n"+ "You bought "+ plant +"\n";
			return info;
		}else if(moneyFlag == false) {
			return "You don't have enough money.";
		}else if(coolDownFlag== false && moneyFlag==true){
			info ="CoolDown is remaining! Wait for : " + store.getCoolDown().cooldownRemaining(ps) + " turns.";
			return info;
		}
		return info; 
	}
	public boolean coolDownValidate(String plant) {
		Plants ps = stringToObject(plant);
		if(store.validateCoolDown(ps)) {
			return true;
		}else return false;
	}

	public boolean purchaseValidate(String plant) {
		if(plant.equals("Chomper")){
			Plants plants = new Chomper();
			if(store.validatePurchase(plants)) {
				return true;
			}else{
				return false;
			}
		}else if(plant.equals("PotatoMine")){
			Plants plants = new PotatoMine();
			if(store.validatePurchase(plants)) {
				return true;
			}else{
				return false;
			}
		}else if(plant.equals("ShootingPlant")){
			Plants plants = new ShootingPlant();
			if(store.validatePurchase(plants)) {
				return true;
			}else{
				return false;
			}
		}else if(plant.equals("Sunflower")){
			Plants plants = new Sunflower();
			if(store.validatePurchase(plants)) {
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	public Plants stringToObject(String plant) {
		Plants plants; 
		if(plant.equals("Sunflower")){
			plants = new Sunflower();

		}else if(plant.equals("PotatoMine")){
			plants = new PotatoMine();
		}else if(plant.equals("ShootingPlant")){
			plants = new ShootingPlant();
		}else {
			plants = new Chomper();
		}
		return plants;
	}
	public Layout simulate() {

		//layout = this.action.plantShoot();
		if(layout.getStatus() == Status.start) {
			startLevel(level);
		}else {
			action = new Action();
			layout = action.startAction(layout);
			spownZombies();
		}
		store.nextTurn();
		store.incrementSunPoints(layout);
		return layout;
	}


	public void startLevel(int level) {
		levelDistinguish(level);
		spownZombies();
		layout.setStatus(Status.inProgress);
	}

	public void levelDistinguish(int level) {
		this.level = level;
		if(level == 1) {
			zombieCounter = 4;
		}else if(level == 2) {
			zombieCounter = 6;
		}else if(level == 3) {
			zombieCounter = 8;
		}
	}

	public boolean checkObjectGrid(int row,int col) {
		if(layout.getGameGrid()[row][col] == null) {
			return true;
		}else return false;
	}
	public String placePlantOnGrid(int row, int col) {
		if(purchasedPlant == null) {
			return "Please Buy the Plant";
		}else {
			layout.placePlantOnGrid(row, col, purchasedPlant);
			String info = "You placed "+ purchasedPlant.getName() + "at "+ row+ col;
			purchasedPlant = null;
			return info;
		}

	}
	public void spownZombies(){
		/*
		boolean gamedone = false;
		if(!(zombieCounter <=1)) {
			layout.placeSpawnZombieOnGrid(new WalkingZombie());
		}*/
		//layout.print();
		//while(gamedone == false) {
		if(zombieCounter == 0) {
		}else {
			if(level == 1) {
				layout.placeSpawnZombieOnGrid(new WalkingZombie());
			}else if(level == 2) {
				layout.placeSpawnZombieOnGrid(new SprintZombie());
				layout.placeSpawnZombieOnGrid(new WalkingZombie());
			}else if(level == 3) {
				layout.placeSpawnZombieOnGrid(new SprintZombie());
				layout.placeSpawnZombieOnGrid(new RugbyZombie());
			}
			zombieCounter--;
			//layout.print();
			//store.purchaseStartOfGame(layout , false);
			//Action action = new Action(this.layout);
			//layout.setGameGrid(action.startAction().getGameGrid());

			//layout.print();
		}	
	}




	public Plants getPurchasedPlant() {
		return purchasedPlant;
	}

	public Layout getLayout() {
		return layout;
	}

	public PlantStore getStore() {
		return store;
	}

	public Action getAction() {
		return action;
	}

	public int getLevel() {
		return level;
	}

	public int getZombieCounter() {
		return zombieCounter;
	}

	public void start(int rows, int colomns) {
		layout.createGrid(rows, colomns);
	}
	/*
	public void promptStart(){
		//int gameMode = gameDifficulty();
		//reader = new Scanner(System.in);  
		System.out.println("Below is a 5x7 grid layout:");


		if(gameMode == 1) {
			System.out.println("Easy mode selected. Zombie types include: Walking Zombies. Move up 1 tile each time ");
			zombieCounter = 4;
		}else if(gameMode == 2){
			System.out.println("Medium mode selected. Zombie types include: ");
			zombieCounter =6;
		}
		else if(gameMode==3){
			System.out.println("Hard mode selected. Zombie types include: ");
			zombieCounter = 8;
		}

		start(gameMode);
	}*/
	public int getSunpoint() {
		return store.getSunPoints();
	}
	public int gameDifficulty(int mode) {
		try {
			//System.out.println("There are 3 levels of difficulty");
			//System.out.println("1).Easy --- 2).Medium --- 3).Hard --- 9) Help");
			//reader = new Scanner(System.in); 
			//int mode = reader.nextInt();	
			if (mode == 1) {
				System.out.println("Easy mode has been selected...Good Luck!");
				//set off variable for game difficulty
				return 1;
			}
			else if (mode == 2) {

				return 2;
			}

			else if(mode == 3) {
				return 3;
			}

		}
		catch (InputMismatchException e ) {

		}
		//user input
		return 0 ;
	}
	public void redo() {

	}

	public void undo() {

	}

	public void save() {

	}

	public void load() {

	}

	/*
	public static void main(String args[]) {

		Layout layout = new Layout();
		PlantStore store = new PlantStore();
		// We will test wave by after using beginWave = new wave(1);
		Game game = new Game(layout, store);

		game.promptStart();

	}*/
}
