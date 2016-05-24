package fr.ring.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.ring.gui.CreateCharacterGameState.ButtonListener;
import fr.ring.gui.map.MapGameState;

public class SkillAssignmentGameState extends BasicGameState {

	public static final int ID = 2;
	public static final int	ADD_FORCE = 0, ADD_DEX = 1, ADD_INT = 2, ADD_CON = 3, REM_FORCE = 4, REM_DEX = 5, REM_INT = 6, REM_CON = 7, TERMINER = 8;
	public static final int SPACE = 50;
	public static final int Y_PADDING = 3, X_PADDING  = 7;
	
	
	private StateBasedGame game;
	private GameContainer container;
	private Image background;
	
	private MouseOverArea forceButton;
	private	MouseOverArea dexteriteButton;
	private MouseOverArea intelligenceButton;
	private MouseOverArea concentrationButton;
	
	private MouseOverArea moinsForceButton;
	private MouseOverArea plusForceButton;
	private	MouseOverArea moinsDexteriteButton;
	private	MouseOverArea plusDexteriteButton;
	private MouseOverArea moinsIntelligenceButton;
	private MouseOverArea plusIntelligenceButton;
	private MouseOverArea moinsConcentrationButton;
	private MouseOverArea plusConcentrationButton;
	
	private MouseOverArea terminerButton;
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.game = game;
		this.container = container;
		this.background = new Image("fr/ring/gui/ressources/map/background/main_menu_background.png");
		Image buttonImage = new Image("fr/ring/gui/ressources/hud/hud_button.png");
		Image buttonImage2 = new Image("fr/ring/gui/ressources/hud/hud_boutton2.png");
		this.forceButton = new MouseOverArea(container, buttonImage, getXCentre(container, buttonImage), getYCentre(container, buttonImage));
		this.dexteriteButton = new MouseOverArea(container, buttonImage, getXCentre(container, buttonImage), getYCentre(container, buttonImage) + SPACE);
		this.intelligenceButton = new MouseOverArea(container, buttonImage, getXCentre(container, buttonImage), getYCentre(container, buttonImage) + SPACE * 2);
		this.concentrationButton = new MouseOverArea(container, buttonImage, getXCentre(container, buttonImage), getYCentre(container, buttonImage) + SPACE * 3);
		this.moinsForceButton = new MouseOverArea(container, buttonImage2, getXCentre(container, buttonImage2) - SPACE * 2, getYCentre(container, buttonImage2), new ButtonListener(REM_FORCE));
		this.plusForceButton = new MouseOverArea(container, buttonImage2, getXCentre(container, buttonImage2) + SPACE * 2, getYCentre(container, buttonImage2), new ButtonListener(ADD_FORCE));
		this.moinsDexteriteButton = new MouseOverArea(container, buttonImage2, getXCentre(container, buttonImage2) - SPACE * 2, getYCentre(container, buttonImage2) + SPACE, new ButtonListener(REM_DEX));
		this.plusDexteriteButton = new MouseOverArea(container, buttonImage2, getXCentre(container, buttonImage2) + SPACE * 2, getYCentre(container, buttonImage2) + SPACE, new ButtonListener(ADD_DEX));
		this.moinsIntelligenceButton = new MouseOverArea(container, buttonImage2, getXCentre(container, buttonImage2) - SPACE * 2, getYCentre(container, buttonImage2) + SPACE * 2, new ButtonListener(REM_INT));
		this.plusIntelligenceButton = new MouseOverArea(container, buttonImage2, getXCentre(container, buttonImage2) + SPACE * 2, getYCentre(container, buttonImage2) + SPACE * 2, new ButtonListener(ADD_INT));
 		this.moinsConcentrationButton = new MouseOverArea(container, buttonImage2, getXCentre(container, buttonImage2) - SPACE * 2, getYCentre(container, buttonImage2) + SPACE * 3, new ButtonListener(REM_CON));
		this.plusConcentrationButton = new MouseOverArea(container, buttonImage2, getXCentre(container, buttonImage2) + SPACE * 2, getYCentre(container, buttonImage2) + SPACE * 3, new ButtonListener(ADD_CON));
		this.terminerButton = new MouseOverArea(container, buttonImage, getXCentre(container, buttonImage), getYCentre(container, buttonImage) + SPACE * 6, new ButtonListener(TERMINER));
				
		
	}
	
	public int getXCentre(GameContainer container, Image buttonImage){
		return container.getWidth() / 2 - buttonImage.getWidth() / 2;
	}
	
	public int getYCentre(GameContainer container, Image buttonImage){
		return container.getHeight() / 2 - buttonImage.getHeight() / 2;
	}
	
	class ButtonListener implements ComponentListener{
		private int val;
		
		public ButtonListener(int val){
			this.val = val;			
		}

		@Override
		public void componentActivated(AbstractComponent arg0) {
			switch(val){
			case ADD_FORCE : 
				if(MapGameState.p.evolutionFOR())
					MapGameState.p.setFOR(MapGameState.p.getFOR() + 1);
				break;
			case ADD_DEX :	
				if(MapGameState.p.evolutionDEX())
					MapGameState.p.setDEX(MapGameState.p.getDEX() + 1);
				break;
			case ADD_INT : 
				if(MapGameState.p.evolutionINT())
					MapGameState.p.setINT(MapGameState.p.getINT() + 1);
				break;
			case ADD_CON :
				if(MapGameState.p.evolutionCON())
					MapGameState.p.setCON(MapGameState.p.getCON() + 1);
				break;
			case REM_FORCE :
				MapGameState.p.setFOR(MapGameState.p.getFOR() - 1);
				break;
			case REM_DEX :
				MapGameState.p.setDEX(MapGameState.p.getDEX() - 1);
				break;
			case REM_INT :
				MapGameState.p.setINT(MapGameState.p.getINT() - 1);
				break;
			case REM_CON :
				MapGameState.p.setCON(MapGameState.p.getCON() - 1);
				break;
			case TERMINER :
				try{
					game.getState(MapGameState.ID).init(container, game);
				}
				catch(SlickException e){
					e.getMessage();
				}
				game.enterState(MapGameState.ID);
				break;
			}
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.setColor(Color.white); 
		background.draw(0, 0, container.getWidth(), container.getHeight());
		g.drawString("Attribuez vos points de compétences :", (container.getWidth() / 2) - X_PADDING * 20, (container.getHeight() / 2) - SPACE * 2);
		g.setColor(Color.black);
		forceButton.render(container, g);
		g.drawString("For = " + MapGameState.p.getFOR(), forceButton.getX() + X_PADDING * 2, forceButton.getY() + Y_PADDING);
		dexteriteButton.render(container, g);
		g.drawString("Dex = " + MapGameState.p.getDEX(), dexteriteButton.getX() + X_PADDING * 2, dexteriteButton.getY() + Y_PADDING);
		intelligenceButton.render(container, g);
		g.drawString("Intel = " + MapGameState.p.getINT(), intelligenceButton.getX() + X_PADDING * 2, intelligenceButton.getY() + Y_PADDING);
		concentrationButton.render(container, g);
		g.drawString("Con = " + MapGameState.p.getCON(), concentrationButton.getX() + X_PADDING * 2, concentrationButton.getY() + Y_PADDING);
		moinsForceButton.render(container, g);
		g.drawString("-", moinsForceButton.getX() + X_PADDING, moinsForceButton.getY() + Y_PADDING);
		plusForceButton.render(container, g);
		g.drawString("+", plusForceButton.getX() + X_PADDING, plusForceButton.getY() + Y_PADDING);
		moinsDexteriteButton.render(container, g);
		g.drawString("-", moinsDexteriteButton.getX() + X_PADDING, moinsDexteriteButton.getY() + Y_PADDING);
		plusDexteriteButton.render(container, g);
		g.drawString("+", plusDexteriteButton.getX() + X_PADDING, plusDexteriteButton.getY() + Y_PADDING);
		moinsIntelligenceButton.render(container, g);
		g.drawString("-", moinsIntelligenceButton.getX() + X_PADDING, moinsIntelligenceButton.getY() + Y_PADDING);
		plusIntelligenceButton.render(container, g);
		g.drawString("+", plusIntelligenceButton.getX() + X_PADDING, plusIntelligenceButton.getY() + Y_PADDING);
		moinsConcentrationButton.render(container, g);
		g.drawString("-", moinsConcentrationButton.getX() + X_PADDING, moinsConcentrationButton.getY() + Y_PADDING);
		plusConcentrationButton.render(container, g);
		g.drawString("+", plusConcentrationButton.getX() + X_PADDING, plusConcentrationButton.getY() + Y_PADDING);
		terminerButton.render(container, g);
		g.drawString("Terminer", terminerButton.getX() + X_PADDING * 2, terminerButton.getY() + Y_PADDING);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

	}

	@Override
	public int getID() {
		return ID;
	}

}
