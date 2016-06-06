package fr.ring.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


import fr.ring.gui.battle.BattleGameState;
import fr.ring.gui.battle.BattlePlayer;
import fr.ring.gui.map.MapGameState;


public class NameAssignState extends BasicGameState{
	
	public static final int ID = 5, SPACE = 70;
	public static TextField enterName;
	
	private StateBasedGame game;
	private GameContainer container;
	private Image background;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.game = game;
		this.container = container;
		this.background = new Image("fr/ring/gui/ressources/map/background/main_menu_background.png");
		enterName = new TextField(container, container.getDefaultFont(), container.getWidth()/2 - SPACE, container.getHeight()/2, 150, 30);
		enterName.setAcceptingInput(true);
		enterName.keyPressed(Input.KEY_ENTER, '\n');
		enterName.addListener(new ButtonListener());
	}
	
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		enterName.setText("");
		enterName.setAcceptingInput(true);
	}
	
	class ButtonListener implements  ComponentListener{

		@Override
		public void componentActivated(AbstractComponent arg0) {
			enterName.setAcceptingInput(false);
			MapGameState.p.setNom(enterName.getText());
			game.enterState(SkillAssignmentGameState.ID);				
		}
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.setColor(Color.white);
		background.draw(0, 0, container.getWidth(), container.getHeight());
		g.drawString("Entrez le nom de votre personnage puis appuyez sur la touche ENTREE", enterName.getX() - MainScreenGameState.X_PADDING * 15, enterName.getY() - SPACE);
		enterName.render(this.container, g);
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
