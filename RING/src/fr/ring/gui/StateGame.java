package fr.ring.gui;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class StateGame extends StateBasedGame {
	
	public StateGame(String name) {
		super(name);		
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new MainScreenGameState());
		addState(new CreateCharacterGameState());
		addState(new SkillAssignmentGameState());
		addState(new MapGameState());
		addState(new BattleGameState());

	}
	
	public static void main(String[] args) throws SlickException{
		new AppGameContainer(new StateGame("Ring"), 1920, 1080, false).start();
	}

}
