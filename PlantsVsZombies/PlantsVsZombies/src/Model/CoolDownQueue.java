
package Model;


import java.io.Serializable;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.PriorityQueue;
import java.util.Queue;
import Plants.Plants;
import Plants.ShootingPlant;
import Plants.Sunflower;

/**
 * 
 * This Class is CoolDownQueue for Storing, checking the plants cool-down for each plants.
 * We decided to use queue to store, trace, and validate each plant's cool-down for further extension.
 * Queue data-structure is first in and first out. 
 * 
 * We are using Observe pattern to notify every plant CoolDown queue to reduce the cool-down.
 */
public class CoolDownQueue implements Observer,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	Queue<Plants> queue; 
	
	public CoolDownQueue(Plants plant) {
		queue = new LinkedList<Plants>();
		this.name = plant.getName();
		addCooldown(plant);
	}
	/**
	 * depends on the type of the plant, it checks the cool-down of the plants
	 * and add it to the queue.
	 * 
	 * @param plant
	 */
	
	public void addCooldown(Plants plant) {
		
		for(int i=0; i<plant.getCooldown(); i++) {
			queue.add(plant);
		}		

		System.out.println(plant.getName() + "'s Cool Down : " + (getRemaining()) +"turns.");
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		queue.poll();
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Queue<Plants> getQueue() {
		return queue;
	}
	public void setQueue(PriorityQueue<Plants> queue) {
		this.queue = queue;
	}

	public int getRemaining() {
		return queue.size();
	}
	
	public boolean isEmpty() {
		return this.queue.isEmpty();
	}
	
	public void removeCooldown() {
		queue.remove();
	}

}

