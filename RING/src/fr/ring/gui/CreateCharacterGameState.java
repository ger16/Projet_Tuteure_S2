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

import fr.ring.gui.map.MapGameState;
import fr.ring.personnage.Chasseur;
import fr.ring.personnage.Guerrier;
import fr.ring.personnage.Mage;
import fr.ring.personnage.Personnage;

public class CreateCharacterGameState extends BasicGameState {
	
	public static final int ID = 1, SPACE = 200, GUERRIER = 0, MAGE = 1, CHASSEUR = 2;
	
	private Personnage p;
	
	private StateBasedGame game;
	private Image background;
	private MouseOverArea guerrierButton;
	private MouseOverArea mageButton;
	private MouseOverArea chasseurButton;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.game = game;
		Image buttonImage = new Image("fr/ring/gui/ressources/hud/hud_button.png");
		this.background = new Image("fr/ring/gui/ressources/map/background/main_menu_background.png");
		this.guerrierButton = new MouseOverArea(container, buttonImage, getXCentre(container, buttonImage) - SPACE, getYCentre(container, buttonImage), new ButtonListener(GUERRIER));
		this.mageButton =  new MouseOverArea(container, buttonImage, getXCentre(container, buttonImage), getYCentre(container, buttonImage), new ButtonListener(MAGE));
		this.chasseurButton =  new MouseOverArea(container, buttonImage, getXCentre(container, buttonImage) + SPACE, getYCentre(container, buttonImage), new ButtonListener(CHASSEUR));
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
		public void componentActivated(AbstractComponent arg0){
			switch(val){
			case GUERRIER :
				p = new Guerrier();
				MapGameState.p = p;
				game.enterState(SkillAssignmentGameState.ID);break;
			case MAGE : 
				p = new Mage();
				MapGameState.p = p;
				game.enterState(SkillAssignmentGameState.ID);break;
			case CHASSEUR :
				p = new Chasseur();
				MapGameState.p = p;
				game.enterState(SkillAssignmentGameState.ID);break;
			}
		}
	}
		
		

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.setColor(Color.white); 
		background.draw(0, 0, container.getWidth(), container.getHeight());
		g.drawString("Choisissez votre classe : ", (container.getWidth() / 2) - MainScreenGameState.X_PADDING*10, (container.getHeight() / 2) - (SPACE/2));
		g.setColor(Color.black);
		guerrierButton.render(container, g);
		g.drawString("Guerrier", guerrierButton.getX() + MainScreenGameState.X_PADDING, guerrierButton.getY() + MainScreenGameState.Y_PADDING);
		mageButton.render(container, g);
		g.drawString("Mage", mageButton.getX() + MainScreenGameState.X_PADDING, mageButton.getY() + MainScreenGameState.Y_PADDING);
		chasseurButton.render(container, g);
		g.drawString("Chasseur", chasseurButton.getX() + MainScreenGameState.X_PADDING, chasseurButton.getY() + MainScreenGameState.Y_PADDING);		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		
	}

	@Override
	public int getID() {
		return ID;
	}

}
