package fr.ring.gui.battle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import shionn.slick.ui.TextArea;

public class GameOverGameState extends BasicGameState {
	
	public static final int ID = 7;
	
	private StateBasedGame game;
	private Image background;
	
	
	public GameOverGameState() {
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {

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
