package fr.ring.gui.battle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.ring.gui.SkillAssignmentGameState;
import fr.ring.gui.map.MapGameState;

import shionn.slick.ui.TextArea;
import shionn.slick.ui.align.VerticalAlignement;

public class VictoryGameState extends BasicGameState {
	
	public static final int ID = 6, SPACE = 5;
	
	private StateBasedGame game;
	private Image background;
	private TextArea log;
	
	public VictoryGameState() {
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		background = new Image("fr/ring/gui/ressources/map/background/victoryScreen.jpg");
		log = new TextArea(container.getWidth()/3 + SPACE * 10, container.getHeight()/2, container.getWidth()/2, container.getHeight()/3);
		log.setBottomUp(true);
		log.setDefaultFont(container.getDefaultFont());
	}
	
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		if(MapGameState.p.getEXP() < 20){
			MapGameState.p.setEXP(MapGameState.p.getEXP() + 1);
			SkillAssignmentGameState.SKILL_LVL_MAX += 1;
			addLog("Vous dispose d'un point de competence a attribuer, appuyez sur la touche ENTREE pour continuer");
			Input i = new Input(Input.KEY_ENTER);
			i.addKeyListener(new enterListener());
		}
	}
	
	class enterListener implements KeyListener{

		@Override
		public void inputEnded() {}

		@Override
		public void inputStarted() {}

		@Override
		public boolean isAcceptingInput() {return true;}

		@Override
		public void setInput(Input arg0) {}

		@Override
		public void keyPressed(int arg0, char arg1) {
			game.enterState(SkillAssignmentGameState.ID);
		}

		@Override
		public void keyReleased(int arg0, char arg1) {}
		
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
