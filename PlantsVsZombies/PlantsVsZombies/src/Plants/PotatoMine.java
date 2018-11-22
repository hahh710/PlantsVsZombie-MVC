package Plants;

public class PotatoMine extends Plants{
	
	private final int cost = 150;
	private int health = 100;
	private static int turn;
	
	public PotatoMine() {
		super.setStringtype("PM");
		super.setDamage(1000);
		super.setHealth(health);
		super.setCost(cost);
		super.setName("PotatoMine");
	}
	public PotatoMine(int health){
		this.health = health;
		super.setDamage(1000);
		super.setStringtype("PM    ");
		super.setName("PotatoMine");
	}
	public int getCost() {
		return cost;
	}
	
	public static int getTurn() {
		return turn;
	}
	public static void setTurn(int turn) {
		PotatoMine.turn = turn;
	}
}
