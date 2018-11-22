
package Plants;

import java.io.Serializable;

/**
 *  The Plant is an abstract class which contains all the methods common between all the plants
 * be it their health or the damage they give for example.
 * 
 * @author Group 1
 * @since November 4,2018

 */

public abstract class Plants implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String stringtype;
	private int health;
	private int cost;
	private int sunPoints;
	private int speed;
	private int damage;
	private String name;
	private int cooldown = 2; //defalut Cooldown takes 1 turns
	
	public int getDamage() {
		return damage;
	}

	public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public String getStringtype() {
		return stringtype;
	}

	public void setStringtype(String stringtype) {
		this.stringtype = stringtype;
	}

	public Plants(int cost , int sunPoints , int heath){
		this.cost = cost;
		this.sunPoints = sunPoints;
		health = 100;
	
	}
	public Plants(int health){
		this.health = health;
		
	}

	public Plants() {
		
	}
	
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	

	public int getHealth() {
		return this.health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public void damageDealt(int hitpoints){
		int reducedHealth = this.health - hitpoints;
		setHealth (reducedHealth);
	}
	
	public void attacked(int damage) {
		this.health = this.health - damage;
		
	}
	public boolean ismovable(int turns , int speed) {
		if (turns == 0) {
			return false;
		}
		if ((turns%speed) == 0)
			return true;
		else 
			return false;
	}

	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	
}
