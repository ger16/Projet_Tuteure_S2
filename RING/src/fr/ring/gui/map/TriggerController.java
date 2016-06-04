package fr.ring.gui.map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.ring.gui.battle.BattleGameState;

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
				if("teleport".equals(map.getObjectType(objectID))){
					game.enterState(BattleGameState.ID);
				}
				else if("heal".equals(map.getObjectType(objectID))){
					player.getP().setVITMax();
				}
			}
		}
	}
	
	private boolean isInTrigger(int id) {
		return player.getP().getX() > map.getObjectX(id)
			&& player.getP().getX() < map.getObjectX(id) + map.getObjectWidth(id)
			&& player.getP().getY() > map.getObjectY(id)
			&& player.getP().getY() < map.getObjectY(id) + map.getObjectHeight(id);
	}	
	
	private void teleport(int objectID) {
		player.getP().setX(Float.parseFloat(map.getObjectProperty(objectID, "dest-x", Float.toString(player.getP().getX()))));
		player.getP().setY(Float.parseFloat(map.getObjectProperty(objectID, "dest-y", Float.toString(player.getP().getY()))));
	}
}
