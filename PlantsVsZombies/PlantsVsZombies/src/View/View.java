package View;

import java.awt.*;
import javax.swing.*;
import Controller.Controller;
import Model.Layout;
import Zombies.RugbyZombie;
import Zombies.SprintZombie;
import Zombies.WalkingZombie;
import Zombies.Zombies;
import Plants.Chomper;
import Plants.Plants;
import Plants.PotatoMine;
import Plants.ShootingPlant;
import Plants.Sunflower;

public class View extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton[][] buttons;
	private JSplitPane split;
	private JPanel optionPanel,storePanel, gridPanel;
	private JButton easy,inte,hard,help;
	private JButton purchase,waveContinue;
	private Controller controller;
	private JMenuBar playMenuBar;
	private JMenu gameMenu,optionMenu;
	private JMenuItem undo,redo,save,load,newGame,exit;
	private JLabel points,status;
	private ButtonGroup group;
	private JRadioButton buySunflower,buyShooterPlant,buyPotatoMine,buyChomper;

	public View (Controller c) {
		controller = c;
		super.setTitle("Plant Vs Zombie");
		buttons = new JButton[5][7];
		split = new JSplitPane();
		optionPanel = new JPanel();
		storePanel =  new JPanel();
		gridPanel = new JPanel();
		group = new ButtonGroup();
		//super.setLayout();

		//setPreferredSize(new Dimension(1000,1200));
		super.setSize(1000, 800);
		super.setLayout(new GridLayout(3, 1));
		optionPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));
		super.add(optionPanel,BorderLayout.NORTH);

		//super.add();               // due to the GridLayout, our splitPane will now fill the whole window

		// let's configure our splitPane:
		//split.setOrientation(JSplitPane.VERTICAL_SPLIT);  // we want it to split the window verticaly

		super.add(storePanel);
		super.add(gridPanel);
		storePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
		storePanel.setLayout(new GridLayout(6, 2));

		// split.setTopComponent(storePanel); 
		//split.setBottomComponent(gridPanel);  
		gridPanel.setLayout(new GridLayout(5,7));
		gridPanel.setSize(750,1000);

		initiateOptionPanel();
		initiateStorePanel();
		initiateGridPanel();
		initiateMenuBar();
		setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}

	public void initiateMenuBar() {
		playMenuBar = new JMenuBar();
		
		gameMenu = new JMenu("Game");
		playMenuBar.add(gameMenu);
		
		optionMenu = new JMenu("Option");
		playMenuBar = new JMenuBar();
		playMenuBar.add(gameMenu);
		playMenuBar.add(optionMenu);
		
		//save,load,newGame,exit;
		undo = new JMenuItem("undo");
		undo.setActionCommand("Undo");
		undo.addActionListener(controller);
		optionMenu.add(undo);
		
		redo = new JMenuItem("redo");
		redo.setActionCommand("Redo");
		redo.addActionListener(controller);
		optionMenu.add(redo);
		super.setJMenuBar(playMenuBar);
		
		
		newGame= new JMenuItem("new Game");
		newGame.setActionCommand("newGame");
		newGame.addActionListener(controller);
		gameMenu.add(newGame);
		
		save= new JMenuItem("Save");
		save.setActionCommand("save");
		save.addActionListener(controller);
		gameMenu.add(save);
		
		load= new JMenuItem("Load");
		load.setActionCommand("load");
		load.addActionListener(controller);
		gameMenu.add(load);
		
		exit= new JMenuItem("Exit");
		exit.setActionCommand("exit");
		exit.addActionListener(controller);
		gameMenu.add(exit);
		
		this.setJMenuBar(playMenuBar);
		
	}


	

	public void loadlayout(Layout layout) {
		Object[][] grid = layout.getGameGrid();
		for(int i =0; i< 5; i++) {
			for(int j =0 ; j<7 ; j++) {
				if(grid[i][j] instanceof Plants) {
					Plants temp = (Plants) grid[i][j];
					if(temp instanceof ShootingPlant) {
						buttons[i][j].setText(temp.getStringtype());
						Icon myIcon = new ImageIcon("C:\\Users\\Hun\\Desktop\\School\\2018 Fall\\SYSC 3110\\Project_temp\\Assets\\repee.png");
						buttons[i][j].setIcon(myIcon);
					}else if(temp instanceof Sunflower) {
						buttons[i][j].setText(temp.getStringtype());
						Icon myIcon = new ImageIcon("C:\\Users\\Hun\\Desktop\\School\\2018 Fall\\SYSC 3110\\Project_temp\\Assets\\resun.png");
						buttons[i][j].setIcon(myIcon);
					}else if(temp instanceof PotatoMine) {
						buttons[i][j].setText(temp.getStringtype());
						Icon myIcon = new ImageIcon("C:\\Users\\Hun\\Desktop\\School\\2018 Fall\\SYSC 3110\\Project_temp\\Assets\\repotato.png");
						buttons[i][j].setIcon(myIcon);
					}else if(temp instanceof Chomper) {
						buttons[i][j].setText(temp.getStringtype());
						Icon myIcon = new ImageIcon("C:\\Users\\Hun\\Desktop\\School\\2018 Fall\\SYSC 3110\\Project_temp\\Assets\\rechomper.png");
						buttons[i][j].setIcon(myIcon);
					}
				}else if(grid[i][j] instanceof Zombies) {
					Zombies temp = (Zombies) grid[i][j];
					if(temp instanceof WalkingZombie) {
						buttons[i][j].setText(temp.getStringtype());
						Icon myIcon = new ImageIcon("C:\\Users\\Hun\\Desktop\\School\\2018 Fall\\SYSC 3110\\Project_temp\\Assets\\rewalking.png");
						//Image img = ((ImageIcon) myIcon).getImage() ; 
						//Image newimg = img.getScaledInstance( NEW_WIDTH, NEW_HEIGHT,  java.awt.Image.SCALE_SMOOTH ) ; 
						buttons[i][j].setIcon(myIcon);
					}else if(temp instanceof SprintZombie) {
						buttons[i][j].setText(temp.getStringtype());
						Icon myIcon = new ImageIcon("C:\\Users\\Hun\\Desktop\\School\\2018 Fall\\SYSC 3110\\Project_temp\\Assets\\resprint.png");
						//Image img = ((ImageIcon) myIcon).getImage() ;  
						//Image newimg = img.getScaledInstance( NEW_WIDTH, NEW_HEIGHT,  java.awt.Image.SCALE_SMOOTH ) ; 
						buttons[i][j].setIcon(myIcon);
						//buttons[i][j].disable();
						//buttons[i][j].setEnabled(false); 
					}else if(temp instanceof RugbyZombie) {
						buttons[i][j].setText(temp.getStringtype());
						Icon myIcon = new ImageIcon("C:\\Users\\Hun\\Desktop\\School\\2018 Fall\\SYSC 3110\\Project_temp\\Assets\\rerugby.png");
						//Image img = ((ImageIcon) myIcon).getImage() ;  
						//Image newimg = img.getScaledInstance( NEW_WIDTH, NEW_HEIGHT,  java.awt.Image.SCALE_SMOOTH ) ; 
						buttons[i][j].setIcon(myIcon);
						//buttons[i][j].disable();
						//buttons[i][j].setEnabled(false); 
					}
				}else{
					buttons[i][j].setText("");
					Icon myIcon = new ImageIcon("C:\\Users\\Hun\\Desktop\\School\\2018 Fall\\SYSC 3110\\Project_temp\\Assets\\regrass.png");
					buttons[i][j].setIcon(myIcon);
					//buttons[i][j].setIcon(null);
					buttons[i][j].setEnabled(true);
				}
			}
		}
		
	}
	/*
	 * Level Buttons
	 * -Easy
	 * -Intermediate
	 * -Hard
	 */
	public void enableLevelButtons() {
		easy.setEnabled(true);
		inte.setEnabled(true);
		hard.setEnabled(true);
	}
	public void disableLevel() {
		easy.setEnabled(false);

		inte.setEnabled(false);

		hard.setEnabled(false);

	}

	/*
	 * functionButton contains 
	 * -Purchase
	 * -Simulate Wave
	 */
	public void enableFunctionButtons() {
		//enablebuttons();
		purchase.setEnabled(true);
		waveContinue.setEnabled(true);
	}
	
	public void disEnableFunctionButtons() {
		//enableButtons(false);	
		purchase.setEnabled(false);
		waveContinue.setEnabled(false);
	}

	/*
	 * Grid Buttons
	 */
	public void enableButtons(boolean flag) {
		for(int i =0; i< 5; i++) {
			for(int j =0 ; j<7 ; j++) {
				buttons[i][j].setEnabled(flag);
			}
		}
	}

	public void enablePurchaseButton(boolean flag) {
		purchase.setEnabled(flag);
	}
	
	public void undostatus(boolean flag) {
		if(flag == true) {
			undo.setEnabled(true);
		}else {
			undo.setEnabled(true);
		}
	}
	public void redostatus(boolean flag) {
		if(flag == true) {
			redo.setEnabled(true);
		}else {
			redo.setEnabled(true);
		}
	}
	
	
	public void win() {
		JOptionPane.showMessageDialog(this.gridPanel, "Congratulations. You beat the Zombies!", "Winner!",
				JOptionPane.INFORMATION_MESSAGE);
		int result = JOptionPane.showConfirmDialog(this.gridPanel, "Would you like to quit the game?" , "Quit" , JOptionPane.OK_CANCEL_OPTION);
		if (result == 0){
			System.exit(0);
		}
	}

	public void loose() {
		JOptionPane.showMessageDialog(this.gridPanel, "GAME OVER! YOU HAVE FAILED TO PROTECT YOUR GARDEN.",
				"Try Again!! Next Time!", JOptionPane.INFORMATION_MESSAGE);
		for(int i =0; i< 5; i++) {
			for(int j =0 ; j<7 ; j++) {
				buttons[i][j].setEnabled(false);
			}
		}
		this.enableLevelButtons();

	}
	public void updatePoints(int sunpoint) {
		points.setText("Sun Points :"+ String.valueOf(sunpoint) + " Points");
	}
	public void updateInfo(String info) {
		status.setText(info);
		status.setForeground(Color.red);
	}
	
	public void promptMessage(String message) {
		JOptionPane.showMessageDialog(this.gridPanel,message, "Message", JOptionPane.INFORMATION_MESSAGE);
	}

	public void helpPrompt() {
		String helpText = "	Help - How to play Plants Vs Zombies.\r\n" + " \r\n"
				+ "--------------------------------------------------------------------\r\n"
				+ "	The goal of this game is to defense from the zombies.\r\n" + "\r\n"
				+ "Each Difficulty contains different numbers and type of zombies. \r\n" + "\r\n"
				+ "Sun-point is the virtual Money to purchase the plants in the store. \r\n"
				+ "PeaShooter Plant - PeaShooter attacks zombie in the same row.\r\n"
				+ "Sun-flower - The user can get 50 Sun-points on each wave. \r\n" + "\r\n" + "\r\n" + "Each wave, \r\n"
				+ "One zombie is randomly spawned in the board.\r\n"
				+ "The user purchases the plants according to your own Strategies.\r\n" + "\r\n" + "\r\n" + "\r\n"
				+ "\r\n" + "Once the user purchases the plants, \r\n" + "\r\n"
				+ "the user can place the plants on the board by typing row and column.\r\n" + "\r\n" + "\r\n" + "\r\n"
				+ "			Good Luck!! \r\n" + "\r\n"
				+ "--------------------------------------------------------------------";
		JOptionPane.showMessageDialog(this, helpText);

	}
	
	public void initiateOptionPanel() {
		easy = new JButton("Easy");
		easy.setActionCommand("easy");
		optionPanel.add(easy);
		easy.addActionListener(controller);
		inte = new JButton("Intermediate");
		inte.setActionCommand("Intermediate");
		optionPanel.add(inte);
		inte.addActionListener(controller);
		hard = new JButton("Hard");
		hard.setActionCommand("Hard");
		optionPanel.add(hard);
		hard.addActionListener(controller);
		help = new JButton("Help");
		optionPanel.add(help);
		help.setActionCommand("Help");
		help.addActionListener(controller);
	}
	public void initiateStorePanel() {
		JLabel menu1 = new JLabel("Welcome to the PVZ Store");

		storePanel.add(menu1);

		points = new JLabel("Sunpoints : ");
		storePanel.add(points);
		JLabel msg = new JLabel("");
		storePanel.add(msg);

		JLabel sunFlower = new JLabel("Purchase SunFlower Plant");
		storePanel.add(sunFlower);
		JLabel sunFlowerCost = new JLabel("50 Sun Points");
		storePanel.add(sunFlowerCost);
		buySunflower = new JRadioButton();

		buySunflower.addActionListener(controller);
		buySunflower.setActionCommand("buySunflower");
		storePanel.add(buySunflower);
		JLabel Shooter = new JLabel("Purchase Shooter Plant");
		storePanel.add(Shooter);
		JLabel peaShooter = new JLabel("100 Sun Points");
		storePanel.add(peaShooter);
		buyShooterPlant = new JRadioButton();
		buyShooterPlant.addActionListener(controller);
		buyShooterPlant.setActionCommand("buyShooterPlant");
		storePanel.add(buyShooterPlant);



		//text for the potato mine buying options
		JLabel potatoMine = new JLabel("Purchase Potato Mine"); 
		storePanel.add(potatoMine);
		JLabel potatoMineCost = new JLabel ("150 Sun Points");
		storePanel.add(potatoMineCost);
		buyPotatoMine = new JRadioButton();
		buyPotatoMine.addActionListener(controller);
		buyPotatoMine.setActionCommand("buyPotatoMine");
		storePanel.add(buyPotatoMine);

		//text for the Chomper buying options
		JLabel chomper = new JLabel("Purchase Chomper"); 
		storePanel.add(chomper);
		JLabel chomperCost = new JLabel ("200 Sun Points");
		storePanel.add(chomperCost);
		buyChomper = new JRadioButton();
		buyChomper.setActionCommand("buyChomper");
		storePanel.add(buyChomper);


		purchase = new JButton("Purchase");
		purchase.setActionCommand("Purchase");
		purchase.addActionListener(controller);
		storePanel.add(purchase);
		purchase.setEnabled(true);
		waveContinue = new JButton("Simulate Wave");
		waveContinue.setActionCommand("simulate");
		waveContinue.addActionListener(controller);
		storePanel.add(waveContinue);
		status = new JLabel();
		status.setForeground(Color.blue);

		//redo = new JMenuItem("redo");
		group.add(buyShooterPlant);
		group.add(buySunflower);
		group.add(buyPotatoMine);
		group.add(buyChomper);

		storePanel.add(status);
	}

	public void initiateGridPanel() {
		for(int i =0; i< 5; i++) {
			for(int j =0 ; j<7 ; j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].setActionCommand("grid");
				buttons[i][j].putClientProperty("row",i);
				buttons[i][j].putClientProperty("col",j);
				buttons[i][j].addActionListener(controller);
				gridPanel.add(buttons[i][j]);
			}
		}
	}
	public JButton[][] getButtons() {
		return buttons;
	}


	public void setButtons(JButton[][] buttons) {
		this.buttons = buttons;
	}


	public ButtonGroup getGroup() {
		return group;
	}

	//public boolean groupSelected() {
	// buySunflower,buyShooterPlant,buyPotatoMine,buyChomper;
	//	group.isSelected()
	//}


	public JButton getEasy() {
		return easy;
	}


	
	public JRadioButton getBuySunflower() {
		return buySunflower;
	}

	public JRadioButton getBuyShooterPlant() {
		return buyShooterPlant;
	}

	public JRadioButton getBuyPotatoMine() {
		return buyPotatoMine;
	}

	public JRadioButton getBuyChomper() {
		return buyChomper;
	}

}