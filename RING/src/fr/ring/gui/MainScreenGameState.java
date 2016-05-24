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

public class MainScreenGameState extends BasicGameState {
	
	public static  final int ID = 0 , SPACE = 50, Y_PADDING = 3, X_PADDING = 13, JOUER = 0, CREER_PERSONNAGE = 1, QUITTER = 2;
	private Image background;
	private String title;
	private StateBasedGame game;
	
	private MouseOverArea jouerBoutton;
	private MouseOverArea creerPersonnageBoutton;
	private MouseOverArea quitterBoutton;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.game = game;
		this.title = "RING";
		this.background = new Image("fr/ring/gui/ressources/map/background/main_menu_background.png");
		Image buttonImage = new Image("fr/ring/gui/ressources/hud/hud_button.png");
		jouerBoutton = new MouseOverArea(container, buttonImage, getXCentre(container, buttonImage) , getYCentre(container, buttonImage), new ButtonListener(container, JOUER));
		creerPersonnageBoutton = new MouseOverArea(container, buttonImage, getXCentre(container, buttonImage), getYCentre(container, buttonImage) + SPACE, new ButtonListener(container, CREER_PERSONNAGE));
		quitterBoutton = new MouseOverArea(container, buttonImage, getXCentre(container, buttonImage), getYCentre(container, buttonImage) + SPACE * 2, new ButtonListener(container, QUITTER));
	}
	
	public int getXCentre(GameContainer container, Image buttonImage){
		return container.getWidth() / 2 - buttonImage.getWidth() / 2;
	}
	
	public int getYCentre(GameContainer container, Image buttonImage){
		return container.getHeight() / 2 - buttonImage.getHeight() / 2;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.setColor(Color.white); 
		background.draw(0, 0, container.getWidth(), container.getHeight());
		g.drawString("RING", (container.getWidth()/2) - X_PADDING * 3, (container.getHeight()/2) - SPACE * 3);
		g.setColor(Color.black); 
		jouerBoutton.render(container, g);
		g.drawString("Jouer", jouerBoutton.getX() + X_PADDING, jouerBoutton.getY() + Y_PADDING);
		creerPersonnageBoutton.render(container, g);
		g.drawString("Créer joueur", creerPersonnageBoutton.getX() + X_PADDING, creerPersonnageBoutton.getY() + Y_PADDING);
		quitterBoutton.render(container, g);
		g.drawString("Quitter", quitterBoutton.getX() + X_PADDING, quitterBoutton.getY() + Y_PADDING);
		
		

	}
	
	class ButtonListener implements ComponentListener{
		private int val;
		private GameContainer container;
		
		public ButtonListener(GameContainer container, int val){
			this.container = container;
			this.val = val;			
		}
		
		@Override
		public void componentActivated(AbstractComponent arg0) {
			switch(val){
			case JOUER : game.enterState(MapGameState.ID);break;
			case CREER_PERSONNAGE : game.enterState(CreateCharacterGameState.ID);break;
			case QUITTER : container.exit();break;
			}
		}
		
		
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
