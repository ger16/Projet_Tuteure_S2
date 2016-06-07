package fr.ring.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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
import fr.ring.personnage.Personnage;

public class LoadCharacterGameState extends BasicGameState {
	
	public static final int ID = 8, SPACE = 5, Y_PADDING = 3, X_PADDING = 13;
	
	private StateBasedGame game;
	private GameContainer container;
	private File f;
	private ArrayList<MouseOverArea> loadCharacter;
	private Image background;
	private Image buttonImage;
	
	public LoadCharacterGameState() {
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.game = game;
		this.container = container;
		background = new Image("fr/ring/gui/ressources/map/background/main_menu_background.png");
		buttonImage = new Image("fr/ring/gui/ressources/hud/hud_button.png");
		loadCharacter = new ArrayList<MouseOverArea>();
		f = new File("Sauvegarde");
		if(!f.exists()){
			f.mkdir();
		}
		for(int i=0; i < f.listFiles().length; i++)	
			loadCharacter.add(new MouseOverArea(container, buttonImage, getXCentre(container, buttonImage) , getYCentre(container, buttonImage) - SPACE * i*10, new ButtonListener(f.listFiles()[i].getName().split(".txt")[0])));
	}
	
	public int getXCentre(GameContainer container, Image buttonImage){
		return container.getWidth() / 2 - buttonImage.getWidth() / 2;
	}
	
	public int getYCentre(GameContainer container, Image buttonImage){
		return container.getHeight() / 2 - buttonImage.getHeight() / 2;
	}
	
	class ButtonListener implements ComponentListener{
		private String nom;		
		
		public ButtonListener(String nom){
			this.nom = nom;
		}
		
		@Override
		public void componentActivated(AbstractComponent arg0) {
			try {
				MapGameState.p = Personnage.chargerHero(nom);
				System.out.println(MapGameState.p);
				System.out.println(MapGameState.p.getCAP().get(0));
				game.enterState(MapGameState.ID);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		background.draw(0, 0, container.getWidth(), container.getHeight());
		for(int i=0; i < loadCharacter.size(); i++){
			loadCharacter.get(i).render(container, g);
			g.drawString(f.listFiles()[i].getName().split(".txt")[0] , loadCharacter.get(i).getX() + X_PADDING, loadCharacter.get(i).getY() + Y_PADDING);
		}
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
