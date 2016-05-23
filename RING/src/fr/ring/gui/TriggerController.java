package fr.ring.gui;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class TriggerController {
	
	private Map map;
	private Player player;
	private StateBasedGame game;
	
	public TriggerController(Map map, Player player, StateBasedGame game){
		this.map = map;
		this.player = player;
		this.game = game;
	}
	
	public void update() throws SlickException {
		for(int objectID = 0; objectID < map.getObjectCount(); objectID++){
			if(isInTrigger(objectID)){
				changeState();
			}
		}
	}
	
	private boolean isInTrigger(int id) {
		return player.p.getX() > map.getObjectX(id)
			&& player.p.getX() < map.getObjectX(id) + map.getObjectWidth(id)
			&& player.p.getY() > map.getObjectY(id)
			&& player.p.getY() < map.getObjectY(id) + map.getObjectHeight(id);
	}	
	
	/*private void teleport(int objectID) {
		player.p.setX(Float.parseFloat(map.getObjectProperty(objectID, "dest-x", Float.toString(player.p.getX()))));
		player.p.setY(Float.parseFloat(map.getObjectProperty(objectID, "dest-y", Float.toString(player.p.getY()))));
	}*/
	
	public void changeState(){
		game.enterState(BattleGameState.ID);
		
	}
}
