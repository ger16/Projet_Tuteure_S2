package fr.ring.gui.battle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;

import shionn.slick.ui.TextArea;
import shionn.slick.ui.align.VerticalAlignement;

import fr.ring.gui.battle.BattleController.BattleCommand;

public class BattleHud {
	
	public static final int ATTAQUER = 0, DEFENDRE = 1, SOIGNER = 2, CAPITULER = 3;
	public static final int SPACE = 5;
	public static final int Y_PADDING = 3; 
	public static final int X_PADDING = 13;
	
	private StateBasedGame game;
	private BattleController controller;
	
	private TextArea log;
	private MouseOverArea attackButton;
	private MouseOverArea defendButton;
	private MouseOverArea healButton;
	private MouseOverArea surrenderButton;
	
	public BattleHud(BattleController controller, StateBasedGame game){
		this.controller = controller;
		this.game = game;
	}
	
	public void init(GameContainer container, StateBasedGame game) throws SlickException{
		this.game = game;
		Image buttonImage = new Image("fr/ring/gui/ressources/hud/hud_button.png");
		attackButton = new MouseOverArea(container, buttonImage, SPACE, container.getHeight() - (buttonImage.getHeight() + SPACE*4) * 5, new ButtonListener(ATTAQUER));
		defendButton = new MouseOverArea(container, buttonImage, SPACE, container.getHeight() - (buttonImage.getHeight() + SPACE*4) * 4, new ButtonListener(DEFENDRE));
		healButton = new MouseOverArea(container, buttonImage, SPACE, container.getHeight() - (buttonImage.getHeight() + SPACE*4) * 3, new ButtonListener(SOIGNER));
		surrenderButton = new MouseOverArea(container, buttonImage, SPACE, container.getHeight() - (buttonImage.getHeight() + SPACE*4) * 2, new ButtonListener(CAPITULER));
		log = new TextArea(attackButton.getWidth() + SPACE * 3, attackButton.getY(), container.getWidth() - attackButton.getWidth() - SPACE * 3, buttonImage.getHeight() * 3 + SPACE * 5);
		log.setBottomUp(true);
		log.setDefaultFont(container.getDefaultFont());
		this.controller.setHud(this);
	}	
	
	
	public void render(GameContainer container, Graphics g){
		g.setColor(Color.black);
		attackButton.render(container, g);
		g.drawString("Attaquer", attackButton.getX() + X_PADDING, attackButton.getY() + Y_PADDING);
		defendButton.render(container, g);
		g.drawString("Defendre", defendButton.getX() + X_PADDING, defendButton.getY() + Y_PADDING);
		healButton.render(container, g);
		g.drawString("Soigner", healButton.getX() + X_PADDING, healButton.getY() + Y_PADDING);
		surrenderButton.render(container, g);
		g.drawString("Capituler", surrenderButton.getX() + X_PADDING, surrenderButton.getY() + Y_PADDING);
		log.render();
	}
	
	public void addLog(String text) {
		log.addFirstText(text, VerticalAlignement.LEFT);
	}

		
	class ButtonListener implements ComponentListener{
		
		private int val;
		
		public ButtonListener(int val){
			this.val = val;
		}
		
		@Override
		public void componentActivated(AbstractComponent arg0) {
			switch(val){
			case ATTAQUER : controller.controlPressed(BattleCommand.ATTACK);break;
			case DEFENDRE : controller.controlPressed(BattleCommand.DEFEND);break;
			case SOIGNER : controller.controlPressed(BattleCommand.HEAL);break;
			case CAPITULER : controller.controlPressed(BattleCommand.SURRENDER);break;
				
			}
		}
		
	}
	
	public TextArea getLog(){
		return log;
	}

	public MouseOverArea getAttackButton() {
		return attackButton;
	}

	public void setAttackButton(MouseOverArea attackButton) {
		this.attackButton = attackButton;
	}

	public MouseOverArea getDefendButton() {
		return defendButton;
	}

	public void setDefendButton(MouseOverArea defendButton) {
		this.defendButton = defendButton;
	}

	public MouseOverArea getHealButton() {
		return healButton;
	}

	public void setHealButton(MouseOverArea healButton) {
		this.healButton = healButton;
	}

	public MouseOverArea getSurrenderButton() {
		return surrenderButton;
	}

	public void setSurrenderButton(MouseOverArea surrenderButton) {
		this.surrenderButton = surrenderButton;
	}
}
