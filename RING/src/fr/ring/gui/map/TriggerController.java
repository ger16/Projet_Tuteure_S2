package fr.ring.gui.map;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.ring.gui.battle.BattleGameState;
import fr.ring.personnage.IA;
import fr.ring.personnage.Imp;
import fr.ring.personnage.Personnage;
import fr.ring.capacites.*;

public class TriggerController {
	
	public static IA boss;
	
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
				if("boss1".equals(map.getObjectType(objectID))){
					boss = new Imp("Imp1", 30,20,15,15, new ArrayList<Capacite>());
					boss.initBattle();
					game.enterState(BattleGameState.ID);
				}
				else if("boss2".equals(map.getObjectType(objectID))){
					boss = new Imp("Imp2", 32,22,16,16, new ArrayList<Capacite>());
					boss.initBattle();
					boss.setEXP(3);
					game.enterState(BattleGameState.ID);
				}
				else if("boss3".equals(map.getObjectType(objectID))){
					boss = new Imp("Imp3", 34,24,16,16, new ArrayList<Capacite>());
					boss.initBattle();
					boss.setEXP(5);
					game.enterState(BattleGameState.ID);
				}
				else if("boss4".equals(map.getObjectType(objectID))){
					boss = new Imp("Imp4", 38,24,16,16, new ArrayList<Capacite>());
					boss.initBattle();
					boss.setEXP(7);
					game.enterState(BattleGameState.ID);
				}
				else if("boss5".equals(map.getObjectType(objectID))){
					boss = new Imp("Imp5", 40,26,17,17, new ArrayList<Capacite>());
					boss.initBattle();
					boss.setEXP(9);
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
	
	public static void exitTrigger(Personnage p){
		p.setY(p.getY() + 75.0f);
	}
	
	private void teleport(int objectID) {
		player.getP().setX(Float.parseFloat(map.getObjectProperty(objectID, "dest-x", Float.toString(player.getP().getX()))));
		player.getP().setY(Float.parseFloat(map.getObjectProperty(objectID, "dest-y", Float.toString(player.getP().getY()))));
	}
}
