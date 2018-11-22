package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToggleButton;

import Model.Game;
import Model.Status;
import Plants.Plants;
import Plants.ShootingPlant;
import Plants.Sunflower;
import View.View;

public class Controller implements ActionListener{
	private View view;
	private Game game;
	private TimeLine timeline;


	public Controller() {
		game = new Game();
		view = new View(this);
		startView();
		timeline = new TimeLine();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("easy")) {
			this.game.levelDistinguish(1);
			//this.game.startLevel(1);
			view.disableLevel();
			view.enableFunctionButtons();
			updateView();
			view.promptMessage("\t"+"Level is 'Easy' "+ "\n"+ "Simulate the Game!");
			timeline = new TimeLine(game);
		}else if(e.getActionCommand().equals("Intermediate")) {
			this.game.levelDistinguish(2);
			//this.game.startLevel(1);
			view.disableLevel();
			view.enableFunctionButtons();
			updateView();
			view.promptMessage("\t"+"Level is 'Intermediate' "+ "\n"+ "Simulate the Game!");
			timeline = new TimeLine(game);
		}else if(e.getActionCommand().equals("Hard")) {
			this.game.levelDistinguish(3);
			//this.game.startLevel(1);
			view.disableLevel();
			view.enableFunctionButtons();
			updateView();
			view.promptMessage("\t"+"Level is 'Hard' "+ "\n"+ "Simulate the Game!");
			timeline = new TimeLine(game);
		}else if(e.getActionCommand().equals("Help")){
			view.helpPrompt();
		}else if (e.getActionCommand().equals("Undo")){
			undo();
			updateView();
		}else if (e.getActionCommand().equals("Redo")){
			redo();
			updateView();
		}else if (e.getActionCommand().equals("newGame")){
			this.game = new Game();
			//this.view = new View(this);
			startView();
		}else if (e.getActionCommand().equals("save")){
			
		}else if (e.getActionCommand().equals("load")){
			
		}else if (e.getActionCommand().equals("exit")){
			System.exit(0);
		}
		else if (e.getActionCommand().equals("simulate")) {
			simulate();
		}else if (e.getActionCommand().equals("Purchase")) {
			String info ="";
			boolean moneyFlag = false;
			boolean coolDownFlag = false;
			String plant = "";
			if(view.getBuySunflower().isSelected()) { 
				plant = "Sunflower";
				if(game.purchaseValidate(plant)) {
					if(game.coolDownValidate(plant)) {
						view.disEnableFunctionButtons();
						moneyFlag = true;
						coolDownFlag = true;
					}else {
						moneyFlag = true;
						coolDownFlag = false;
					}
				}else {
					moneyFlag = false;
				}
				info = game.purchasePlant(plant,moneyFlag,coolDownFlag);
			}else if (view.getBuyShooterPlant().isSelected()) {
				plant = "ShootingPlant";
				if(game.purchaseValidate(plant)) {
					if(game.coolDownValidate(plant)) {
						view.disEnableFunctionButtons();
						moneyFlag = true;
						coolDownFlag = true;
					}else {
						moneyFlag = true;
						coolDownFlag = false;
					}
				}else {
					moneyFlag = false;
				}
				info = game.purchasePlant(plant,moneyFlag,coolDownFlag);
					
			}else if (view.getBuyPotatoMine().isSelected()) {
				plant = "PotatoMine";
				if(game.purchaseValidate(plant)) {
					if(game.coolDownValidate(plant)) {
						view.disEnableFunctionButtons();
						moneyFlag = true;
						coolDownFlag = true;
					}else {
						moneyFlag = true;
						coolDownFlag = false;
					}
				}else {
					moneyFlag = false;
				}
				info = game.purchasePlant(plant,moneyFlag,coolDownFlag);
			}else if (view.getBuyChomper().isSelected()) {
				plant = "Chomper";
				if(game.purchaseValidate(plant)) {
					if(game.coolDownValidate(plant)) {
						view.disEnableFunctionButtons();
						moneyFlag = true;
						coolDownFlag = true;
					}else {
						moneyFlag = true;
						coolDownFlag = false;
					}
				}else {
					moneyFlag = false;
				}
				info = game.purchasePlant(plant,moneyFlag,coolDownFlag);
			}
			view.updateInfo(info);
			updateView();
		}
		else if(e.getActionCommand().equals("grid")){
			if(game.getPurchasedPlant()==null ) {
				//view.promptMessage(("Please Buy the Plant"));
			}else{
				JButton btn = (JButton)e.getSource();
				int row = (int)btn.getClientProperty("row");
				int col = (int)btn.getClientProperty("col");
				if(game.checkObjectGrid(row, col)) {
					view.updateInfo(game.placePlantOnGrid(row, col));
					view.loadlayout(game.getLayout());
					view.enableFunctionButtons();
					save();
				}
			}	
		}
	}
	
	public void save() {
		timeline.addNext(this.game);
	}	
	public void undo() {
		if(!timeline.isUndoAvailable()) {
			view.promptMessage("This is very First State, Can't Undo");
		}else {
			this.game=timeline.undo();
		}
	}
	public void redo() {
		if(!timeline.isRedoAvailable()) {
			view.promptMessage("This is the Last State, Can't Redo");
		}else {
			this.game=timeline.redo();
		}
	}

	public void simulate() {
		game.simulate();
		updateView();
		checkStatus(game.getLayout().getStatus());
		save();
	}
	
	public void startView() {
		//view.updatePoints(game.getSunpoint());
		view.updateInfo("Hope You Enjoy!!");
		view.enableLevelButtons();
		view.loadlayout(game.getLayout());
		view.disEnableFunctionButtons();
	}
	
	public void updateView() {
		view.updatePoints(game.getSunpoint());
		view.loadlayout(game.getLayout());
	}

	public void checkStatus(Status status) {
		if (status == Status.win){
			view.win();
			this.game = new Game();
			view.loadlayout(game.getLayout());
		}else if(status == Status.loose) {
			view.loose();
			this.game = new Game();
			view.loadlayout(game.getLayout());
			view.disEnableFunctionButtons();
		}
	}

	public static void main(String[] arg) {
		//Layout layout = new Layout();
		//	
		Controller c = new Controller();
		//View v = new View(c);
	}
}
