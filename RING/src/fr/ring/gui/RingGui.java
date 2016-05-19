package fr.ring.gui;

import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

public class RingGui extends BasicGame{
	
	private GameContainer container;
	private TiledMap map;
	
	public RingGui(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		this.map.render(0, 0);
		
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		this.container = container;
		this.map = new TiledMap("fr/ring/gui/ressources/map/ring_map.tmx");		
		
	}
    
	public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) {
            container.exit();
        }
    }

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		
		
	}
	
    public static void main(String[] args) throws SlickException {
	        new AppGameContainer(new RingGui("Ring"), 1920, 1080, false).start();
    }

}
