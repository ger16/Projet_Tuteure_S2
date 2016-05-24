package fr.ring.gui.battle;

import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.state.StateBasedGame;

import fr.ring.capacites.Arme;
import fr.ring.capacites.Bouclier;
import fr.ring.capacites.Capacite;
import fr.ring.capacites.Remede;
import fr.ring.capacites.SortAttaque;
import fr.ring.capacites.SortDefense;
import fr.ring.capacites.SortSoin;
import fr.ring.gui.MainScreenGameState;
import fr.ring.gui.battle.BattleGameState.BattleCommand;
import fr.ring.personnage.Personnage;

public class BattleController implements Command, InputProviderListener{
	
	public static final int DEFENSE_IMMEDIATE = 0, DEFENSE_CALCULEE = 1, DEFENSE_ENCAISSE = 2;
	private BattlePlayer player;
	private BattleEnnemy ennemy;
	private StateBasedGame game;
	private BattleHud hud;
		
	public BattleController(BattlePlayer player, BattleEnnemy ennemy, StateBasedGame game){
		this.player = player;
		this.ennemy = ennemy;
		this.game = game;
	}
	
	@Override
	public void controlPressed(Command command) {
    	switch ((BattleCommand) command){
			case ATTACK: 
				ennemy.getP().setVIT(ennemy.getP().getVIT() - (int)resolutionAttaque(player.getP(), player.getP().getCAP().get(0))); break;
			case DEFEND: resolutionDefense(player.getP(), resolutionAttaque(ennemy.getP(), ennemy.getP().getCAP().get(0)), player.getP().getCAP().get(0), DEFENSE_IMMEDIATE); break;
			case HEAL:   resolutionSoin(player.getP(), player.getP().getCAP().get(1));break;
			case SURRENDER:game.enterState(MainScreenGameState.ID);break;
			default: break;
    	}
	}

	@Override
	public void controlReleased(Command arg0) {
				
	}
	
	public double resolutionAttaque(Personnage p, Capacite c){
		double EFF = 0;
		if(c instanceof Arme){
			Arme a = new Arme((Arme)c);
			if(a.attaquer(p)){
				EFF = a.efficaciteAttaque(p);
			}
		}
		else if(c instanceof SortAttaque){
			SortAttaque s = new SortAttaque((SortAttaque)c);
			if(s.attaquer(p)){
				EFF = s.efficaciteAttaque(p);
			}
		}
		else if(c instanceof Bouclier){
			Bouclier b = new Bouclier((Bouclier)c);
			if(b.attaquer(p)){
				EFF = b.efficaciteAttaque(p);
			}
		}
	    if (player.getP().getVIT() <= 0)
	        game.enterState(MainScreenGameState.ID);
	    if(EFF != 0)
	    	hud.addLog("Degats de votre attaque : " + EFF);
	    else
	    	hud.addLog("Vous n'avez pas réussi votre attaque");
		return EFF;		
	}
	
	public double resolutionDefense(Personnage p,double EFF_A, Capacite c, int decision){
		double EFF_D = 0;
		
		if(decision == DEFENSE_IMMEDIATE){
			if(c instanceof Bouclier){
				Bouclier b = new Bouclier((Bouclier)c);
				if(b.seDefendre(p)){
					EFF_D = b.efficaciteDefense(p);
				}
				if(p == this.player.getP())
					this.ennemy.getP().setVIT(this.ennemy.getP().getVIT() - ((int)EFF_A - (int)EFF_D));
				else
					this.player.getP().setVIT(this.ennemy.getP().getVIT() - ((int)EFF_A - (int)EFF_D));
			}
			else if(c instanceof SortDefense){
				SortDefense s = new SortDefense((SortDefense)c);
				if(s.seDefendre(p)){
					EFF_D = s.efficaciteDefense(p);
				}
				if(p == this.player.getP())
					this.ennemy.getP().setVIT(this.ennemy.getP().getVIT() - ((int)EFF_A - (int)EFF_D));
				else
					this.player.getP().setVIT(this.ennemy.getP().getVIT() - ((int)EFF_A - (int)EFF_D));
			}
			else if(c instanceof Arme){
				Arme a = new Arme((Arme)c);
				if(a.seDefendre(p)){
					EFF_D = a.efficaciteDefense(p);
				}
				if(p == this.player.getP())
					this.ennemy.getP().setVIT(this.ennemy.getP().getVIT() - ((int)EFF_A - (int)EFF_D));
				else
					this.player.getP().setVIT(this.ennemy.getP().getVIT() - ((int)EFF_A - (int)EFF_D));
			}
		}
		else if(decision == DEFENSE_CALCULEE){
			if(c instanceof Bouclier){
				Bouclier b = new Bouclier((Bouclier)c);
				b.seDefendre(p);
				b.setPBA(b.getPBA() - b.getPBA()/4);
				if(b.seDefendre(p)){
					EFF_D = b.efficaciteDefense(p);
					EFF_D -= EFF_D/4;
				}
				if(p == this.player.getP())
					this.ennemy.getP().setVIT(this.ennemy.getP().getVIT() - ((int)EFF_A - (int)EFF_D));
				else
					this.player.getP().setVIT(this.ennemy.getP().getVIT() - ((int)EFF_A - (int)EFF_D));
			}
			else if(c instanceof SortDefense){
				SortDefense s = new SortDefense((SortDefense)c);
				s.seDefendre(p);
				s.setPBA(s.getPBA() - s.getPBA()/4);
				if(s.seDefendre(p)){
					EFF_D = s.efficaciteDefense(p);
					EFF_D -= EFF_D/4;
				}
				if(p == this.player.getP())
					this.ennemy.getP().setVIT(this.ennemy.getP().getVIT() - ((int)EFF_A - (int)EFF_D));
				else
					this.player.getP().setVIT(this.ennemy.getP().getVIT() - ((int)EFF_A - (int)EFF_D));
			}
			else if(c instanceof Arme){
				Arme a = new Arme((Arme)c);
				a.seDefendre(p);
				a.setPBA(a.getPBA() - a.getPBA()/4);
				if(a.seDefendre(p)){
					EFF_D = a.efficaciteDefense(p);
					EFF_D -= EFF_D/4;
				}
				if(p == this.player.getP())
					this.ennemy.getP().setVIT(this.ennemy.getP().getVIT() - ((int)EFF_A - (int)EFF_D));
				else
					this.player.getP().setVIT(this.ennemy.getP().getVIT() - ((int)EFF_A - (int)EFF_D));
			}
		}
		else{
			if(p == this.player.getP())
				this.ennemy.getP().setVIT(this.ennemy.getP().getVIT() - (int)EFF_A);
			else
				this.player.getP().setVIT(this.ennemy.getP().getVIT() - (int)EFF_A);
		}
	    if (player.getP().getVIT() <= 0)
	        game.enterState(MainScreenGameState.ID);
	    if(EFF_D != 0)
	    	hud.addLog("Nombre de PV absorbés : " + EFF_D);
	    else
	    	hud.addLog("Vous n'avez pas réussi votre défense");
		return EFF_D;
	}
	
	public double resolutionSoin(Personnage p, Capacite c){
		double EFF = 0;
		if(c instanceof SortSoin){
			SortSoin s = new SortSoin((SortSoin)c);
			if(s.seSoigner(p)){
				EFF = s.efficaciteSoin(p);
				p.setVIT(p.getVIT() + (int)EFF);
				if(p.getVIT()> 200 -(p.getFOR()+ p.getDEX() + p.getINT() + p.getCON()) + p.getEXP()*3)
					p.setVIT(200 -(p.getFOR()+ p.getDEX() + p.getINT() + p.getCON()) +  p.getEXP()*3);
			}
		}
		else if(c instanceof Remede){
			Remede r = new Remede((Remede)c);
			if(r.seSoigner(p)){
				EFF = r.efficaciteSoin(p);
				p.setVIT(p.getVIT() + (int)EFF);
			}
		}
	    if(EFF != 0)
	    	hud.addLog("Nombre de PV regagnés : " + EFF);
	    else
	    	hud.addLog("Vous n'avez pas réussi votre soin");
		return EFF;
	}
	
	public void setHud(BattleHud hud) {
		this.hud = hud;
	}
}
