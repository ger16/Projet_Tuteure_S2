package fr.ring.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;

public class BattleHud {
	
	public static final int SPACE = 5, ATTAQUER = 0, DEFENDRE = 1, SOIGNER = 2, CAPITULER = 3;
	
	private MouseOverArea attackButton;
	private MouseOverArea defendButton;
	private MouseOverArea healButton;
	private MouseOverArea surrenderButton;
	
	public void init(GameContainer container) throws SlickException{
		Image buttonImage = new Image("fr/ring/gui/ressources/hud/hud_button.png");
	}
	
	public void render(GameContainer container, Graphics g){
		
	}
}
