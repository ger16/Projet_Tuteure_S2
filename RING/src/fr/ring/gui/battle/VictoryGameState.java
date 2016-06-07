package fr.ring.gui.battle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.InputAdapter;

import fr.ring.gui.SkillAssignmentGameState;
import fr.ring.gui.map.MapGameState;
import fr.ring.gui.map.TriggerController;

import shionn.slick.ui.TextArea;
import shionn.slick.ui.align.VerticalAlignement;

public class VictoryGameState extends BasicGameState {
	
	public static final int ID = 6, SPACE = 5;
	
	private StateBasedGame game;
	private Image background;
	private TextArea log;
	Input i; 
	
	public VictoryGameState() {
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.game = game;
		background = new Image("fr/ring/gui/ressources/map/background/victoryScreen.jpg");
		log = new TextArea(container.getWidth()/3 + SPACE * 10, container.getHeight()/2, container.getWidth()/2, container.getHeight()/3);
		log.setBottomUp(true);
		log.setDefaultFont(container.getDefaultFont());
	}
	
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		if(MapGameState.p.getEXP() < 20){
			MapGameState.p.setEXP(MapGameState.p.getEXP() + 1);
			SkillAssignmentGameState.SKILL_LVL_MAX ++;
			addLog("Vous disposez d'un point de competence a attribuer, appuyez sur la touche ENTREE pour continuer");
		}
	}
	
	public void keyPressed(int key, char c){
		if(key == Input.KEY_ENTER){
			TriggerController.exitTrigger(MapGameState.p);
			game.enterState(SkillAssignmentGameState.ID);
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.setColor(Color.white);
		background.draw(0, 0, container.getWidth(), container.getHeight());
		log.render();
		g.setBackground(Color.black);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		 
	}
	
	public void addLog(String text) {
		log.addFirstText(text, VerticalAlignement.LEFT);
	}

	@Override
	public int getID() {
		return ID;
	}

}
