package fr.ring.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class BattleGameState extends BasicGameState {
	
	public static final int ID = 4;
	
	private StateBasedGame game;
	private Image background;
	private SpriteSheet hero;
	private SpriteSheet ennemy;
	
	
	public BattleGameState() {
	
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		background = new Image("fr/ring/gui/ressources/map/background/background2.png");
		

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		background.draw(0, 0, container.getWidth(), container.getHeight());

	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
	

	}

	@Override
	public int getID() {
		
		return this.ID;
	}

}
