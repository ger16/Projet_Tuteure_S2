package fr.ring.gui.map;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.ring.gui.NameAssignState;
import fr.ring.personnage.*;

public class MapGameState extends BasicGameState{
	
	public static final int  ID = 3;
	
	public static Personnage p = new Guerrier();
	private Map map = new Map();
	private Player player;
	private Camera camera;
	StateBasedGame  game;
	private TriggerController triggers; 
	
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		
		this.game = game;
		player = new Player(p, map);
		camera = new Camera(player);
		triggers = new TriggerController(map, player, game);
		map.init();
		player.init();
		PlayerController controller = new PlayerController(player, game);
		container.getInput().addKeyListener(controller);		
	}
	
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		NameAssignState.enterName.setAcceptingInput(false);
		player.setP(p);
		player.init();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.setBackground(Color.black);
		camera.place(container, g);
		map.renderBackground();
		player.render(g);
		map.renderForeground();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		this.triggers.update();
		player.update(delta);
		camera.update(container);
	}

	@Override
	public int getID() {
		return ID;
	}
}
