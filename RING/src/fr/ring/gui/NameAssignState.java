package fr.ring.gui;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.InputListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.InputAdapter;

import fr.ring.gui.map.MapGameState;

public class NameAssignState extends BasicGameState{
	
	public static final int ID = 5, SPACE = 70;
	
	private StateBasedGame game;
	private GameContainer container;
	private Image background;
	private UnicodeFont font;
	private TextField enterName;
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
	
	class ButtonListener implements  ComponentListener{

		@Override
		public void componentActivated(AbstractComponent arg0) {
			MapGameState.p.setNom(enterName.getText());
			try{
				game.getState(MapGameState.ID).init(container, game);
			}
			catch(SlickException e){
				e.getMessage();
			}
			game.enterState(MapGameState.ID);				
		}
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.setColor(Color.white);
		background.draw(0, 0, container.getWidth(), container.getHeight());
		g.drawString("Entrez le nom de votre personnage", enterName.getX() - MainScreenGameState.X_PADDING * 5, enterName.getY() - SPACE);
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
