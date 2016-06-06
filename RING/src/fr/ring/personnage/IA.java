package fr.ring.personnage;

import java.util.ArrayList;

import fr.ring.capacites.Capacite;
import fr.ring.gui.battle.BattleController.BattleCommand;


public abstract class IA extends Personnage {

	public IA() {
		super();
	}

	public IA(String nom, int fOR, int dEX, int iNT, int cON,
			ArrayList<Capacite> cAP) {
		super(nom, fOR, dEX, iNT, cON, cAP);
	}

	public IA(Personnage hero) {
		super(hero);
	}

	@Override
	public boolean evolutionFOR() {
		return false;
	}

	@Override
	public boolean evolutionDEX() {
		return false;
	}

	@Override
	public boolean evolutionINT() {
		return false;
	}

	@Override
	public boolean evolutionCON() {
		return false;
	}

	public BattleCommand IAprocess(BattleCommand bc) {
		switch(bc){
		case ATTACK:
			return BattleCommand.DEFEND;
		case DEFEND:
			return BattleCommand.ATTACK;
		case HEAL:
			return BattleCommand.ATTACK;
		default:
			return null;	
		}
	}

}
