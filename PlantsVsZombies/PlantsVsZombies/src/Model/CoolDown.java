package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Queue;

import Plants.Plants;


/**
 * @author Hun
 * This Class is CoolDown for Storing, checking the plants cool-down for each plants.
 * We decided to use queue to store, trace, and validate each plant's cool-down for further extention.
 * Queue data-structure is first in and first out. 
 * the functionalities of the queue ex) pop, push fit with automated reduction cool-down. 
 * 
 * We are using Observe pattern to notify every plant CoolDown queue to reduce the cool-down.
 */
public class CoolDown extends Observable implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//contains the queue of each plants
	ArrayList<CoolDownQueue> queueList; 
	String status="";
	
	
	public CoolDown() {
		queueList=new ArrayList<CoolDownQueue>(); 
	}

	public ArrayList<CoolDownQueue> getQueueList() {
		return queueList;
	}
	public String purchasePlant(Plants plant) {
		if(queueList.isEmpty()){
			CoolDownQueue queue = new CoolDownQueue(plant);
			this.queueList.add(queue);
			this.addObserver(queue);
			
			return status = "You bought" + plant.getName() + "CoolDown :" + (plant.getCooldown() -1) ;
		}else {
			boolean check = false;
			for(CoolDownQueue queue : queueList) {
				if(queue.getName().equals(plant.getName())) {
					check = true;
					getPlantQueue(plant).addCooldown(plant);
					return status = "You bought" + plant.getName() + "CoolDown :" + (plant.getCooldown() -1);
				}
			}
			if(check == false) {
				CoolDownQueue queue = new CoolDownQueue(plant);
				this.queueList.add(queue);
				this.addObserver(queue);
				//this.queueList.add(new CoolDownQueue(plant));
				return status = "You bought" + plant.getName() + "CoolDown :" + (plant.getCooldown() -1) ;
			}
		}
		return status;
	}
	public void turnOver(){
		setChanged();
		notifyObservers();
	}

	/**
	 * It return the number of for cooldown of the given plants the turns the queue and return how many turns is remaining.
	 * @param plant
	 * @return int
	 */

	public CoolDownQueue getPlantQueue(Plants plant) {
		for(int i = 0; i <queueList.size();i++ ) {
			if(queueList.get(i).getName().equals(plant.getName())) {
				return queueList.get(i);
			}
		}
		return null; // In case of error
	}
	
	public int cooldownRemaining(Plants plant) {
		for(CoolDownQueue queue : queueList) {
			if(queue.name.equals(plant.getName())) {
				return queue.getRemaining();
			}
		}
		return -1; // error if there isn't queue;
	}
	
	public String getString() {
		return status;
	}

	/**
	 * When user buy the plant, First check, if it is been purchased before,
	 * then just addCooldown based on the
	 * @param plant
	 */
	public boolean validatePurchase(Plants plant) {
		if(!queueList.isEmpty()){
			for(CoolDownQueue queue : queueList) {
				if(queue.getName().equals(plant.getName())) {
						if (queue.getQueue().isEmpty()) {
							return true;
						}else {
							return false;
						}
				}
			}
		} 
		return true;
		//return false;
	}
	
}

