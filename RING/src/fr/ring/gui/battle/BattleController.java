package fr.ring.gui.battle;

import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.KeyControl;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;
import fr.ring.capacites.Capacite;
import fr.ring.gui.MainScreenGameState;
import fr.ring.gui.map.MapGameState;
import fr.ring.personnage.*;

public class BattleController implements InputProviderListener{
	
	public static final int DEFENSE_IMMEDIATE = 0, DEFENSE_CALCULEE = 1, DEFENSE_ENCAISSE = 2;
	public static int nbTours = 0;
	public static ArrayList<Capacite> capButton = new ArrayList<Capacite>();
	public static BattleHud hud;
	
	private double atk = 0;
	private Capacite capAtk;
	private BattlePlayer player;
	private BattleEnnemy ennemy;
	private Personnage j1;
	private GameContainer container;
	private StateBasedGame game;
	private Image buttonImage;
	private CapController cc;
	private DefenseController dc;
	
	


		
	public BattleController(BattlePlayer player, BattleEnnemy ennemy,GameContainer container,  StateBasedGame game) throws SlickException{
		this.player = player;
		this.ennemy = ennemy;
		this.game = game;
		this.container = container;
		buttonImage = new Image("fr/ring/gui/ressources/hud/hud_button.png");
	}
	
	public enum BattleCommand implements Command {
		ATTACK, REDUCED_ATTACK, DEFEND, REDUCED_DEFEND, HEAL, SURRENDER
	}
	
	public enum CapCommand implements Command {		
		CAP_1, CAP_2, CAP_3, CAP_4, CAP_5, CAP_6, CAP_7, CAP_8, CAP_9, CAP_10,
	}
	
	class DefenseController implements InputProviderListener{
		
		private Personnage p;
		
		public DefenseController (Personnage p){
			this.p = p;
		}
		
		@Override
		public void controlPressed(Command command) {
			switch((BattleCommand) command){
			
			case DEFEND:
				if(p instanceof IA){
					atk = resolutionAttaque(p, p.getCAP().get(0));
					capAtk = p.getCAP().get(0);
					hud.addLog(p.getNom() + " vous a attaqué !");
				}
				else{
					BattleGameState.provider.setActive(false);
					initCapButton(p, "Defense");
					cc = new CapController(p, (BattleCommand)command);
					BattleGameState.capProvider.addListener(cc);
				}
				break;
				
			case REDUCED_DEFEND:
				BattleGameState.provider.setActive(false);
				initCapButton(p, "Defense");
				cc = new CapController(p, BattleCommand.REDUCED_DEFEND);
				BattleGameState.capProvider.addListener(cc);
				break;
			case REDUCED_ATTACK:
				BattleGameState.provider.setActive(false);
				initCapButton(p, "Attaque");
				cc = new CapController(p, BattleCommand.REDUCED_ATTACK);
				BattleGameState.capProvider.addListener(cc);
				break;
			default:
				break;
			}
		}

		@Override
		public void controlReleased(Command arg0) {
			
		}
		
	}
	
	class CapController implements InputProviderListener {
		private Personnage p;
		private BattleCommand bc;
		
		public CapController(Personnage p, BattleCommand bc) {
			this.p = p;
			this.bc = bc;
		}

		@Override
		public void controlPressed(Command command) {
			IA ia = (IA) ennemy.getP();
			switch((CapCommand) command){
			case CAP_1:
				capControlPressedAction(p, ia,  bc, 0);
				break;
			case CAP_2:
				capControlPressedAction(p, ia,  bc, 1);
			case CAP_3:
				capControlPressedAction(p, ia,  bc, 2);
			case CAP_4:
				capControlPressedAction(p, ia,  bc, 3);
			case CAP_5:
				capControlPressedAction(p, ia,  bc, 4);
			case CAP_6:
				capControlPressedAction(p, ia,  bc, 5);
			case CAP_7:
				capControlPressedAction(p, ia,  bc, 6);
			case CAP_8:
				capControlPressedAction(p, ia,  bc, 7);
			case CAP_9:
				capControlPressedAction(p, ia,  bc, 8);
			case CAP_10:
				capControlPressedAction(p, ia,  bc, 9);
			}
		}

		@Override
		public void controlReleased(Command arg0) {
		
		}
	}
	
	@Override
	public void controlPressed(Command command) {
		Personnage p;
		if(nbTours == 0)
			p = j1;
		else
			p = joueurActif();
		/*if(p.equals(ennemy.getP())){
			BattleGameState.provider.setActive(false);
		}*/
		if(p.equals(player.getP())){
			switch ((BattleCommand) command){
			case ATTACK:
				if(p instanceof IA){
					atk = resolutionAttaque(p, p.getCAP().get(0));
					capAtk = p.getCAP().get(0);
					hud.addLog(p.getNom() + " vous a attaqué !");
				}
				else{
					BattleGameState.provider.setActive(false);
					initCapButton(p, "Attaque");
					cc = new CapController(p, (BattleCommand)command);
					BattleGameState.capProvider.addListener(cc);			
				}					
				break;
			case DEFEND: 
				if(p instanceof IA){
					resolutionDefense(p, atk, p.getCAP().get(0));
					hud.addLog(p.getNom() + " s'est défendu !");
				}
				else{
					BattleGameState.provider.setActive(false);
					initCapButton(p, "Defense");
					dc = new DefenseController(p);
					BattleGameState.defenseProvider.addListener(dc);
					
				}
			case HEAL:
				resolutionSoin(p, p.getCAP().get(1));
				break;
			case SURRENDER:
				game.enterState(MapGameState.ID);break;
			default: break;
			}
		}
	}

	@Override
	public void controlReleased(Command arg0) {
				
	}
	
	public void capControlPressedAction(Personnage p, IA ia, BattleCommand bc, int i){
		if(bc == BattleCommand.ATTACK){
			atk = resolutionAttaque(p, BattleController.capButton.get(0));
			capAtk = BattleController.capButton.get(0);
			controlPressed(ia.IAprocess(bc));
			BattleGameState.capProvider.removeListener(cc);
			BattleGameState.provider.setActive(true);
		}
		else if(bc == BattleCommand.DEFEND){
			resolutionDefense(p, atk, BattleController.capButton.get(0));
			controlPressed(ia.IAprocess(bc));
			BattleGameState.defenseProvider.removeListener(dc);
			BattleGameState.capProvider.removeListener(cc);
			BattleGameState.provider.setActive(true);
		}
		else if(bc == BattleCommand.REDUCED_DEFEND){
			resolutionDefenseDiminuee(p, atk, BattleController.capButton.get(0));
			controlPressed(ia.IAprocess(BattleCommand.DEFEND));
			BattleGameState.defenseProvider.removeListener(dc);
			BattleGameState.capProvider.removeListener(cc);
			BattleGameState.provider.setActive(true);
		}
		else if(bc == BattleCommand.REDUCED_ATTACK){
			resolutionAttaqueDiminuee(p, BattleController.capButton.get(0));
			controlPressed(ia.IAprocess(BattleCommand.ATTACK));
			BattleGameState.defenseProvider.removeListener(dc);
			BattleGameState.capProvider.removeListener(cc);
			BattleGameState.provider.setActive(true);
		}
		else if(bc == BattleCommand.HEAL){
			resolutionSoin(p, BattleController.capButton.get(0));
			controlPressed(ia.IAprocess(BattleCommand.HEAL));
			BattleGameState.capProvider.removeListener(cc);
			BattleGameState.provider.setActive(true);
		}
	}
	
	public void initCapButton(Personnage p, String nomInterface){
		switch(nomInterface){
		case "Attaque":
			for(int i=0; i<p.getCAP().size(); i++){
				if(p.getCAP().get(i).containInterfaces("Attaque")){HEAL
					capButton.add(p.getCAP().get(i));
				}
			}
			break;
		case "Defense":
			for(int i=0; i<p.getCAP().size(); i++){
				if(p.getCAP().get(i).containInterfaces("Defense")){
					capButton.add(p.getCAP().get(i));
				}
			}
				break;
		case "Soin":
			for(int i=0; i<p.getCAP().size(); i++){
				if(p.getCAP().get(i).containInterfaces("Soin")){
					capButton.add(p.getCAP().get(i));
				}
			}
			break;
		}
		hud.addLog("Quelle compétence choississez vous ?");
		for(int i=0; i<capButton.size(); i++){
			hud.addLog((i + 1) + " : " + capButton.get(i).getNom());
		}
	}	


	
	public void cleanCapButton(){
		for(int i=0; i<capButton.size(); i++){
			capButton.remove(i);
		}
	}
	
	public Personnage JoueurPremierTour(){
		if(player.getP().getEXP() > ennemy.getP().getEXP())
			j1 = player.getP();
		else if(player.getP().getEXP() == ennemy.getP().getEXP()){
			Random rand = new Random();
			if(rand.nextInt(2) == 0)
				j1 = player.getP();			
			else
				j1 = ennemy.getP();
		}
		else
			j1 = ennemy.getP();
		hud.addLog("Le joueur " + j1.getNom() + " commence");
		if(j1.equals(ennemy.getP()) && ennemy.getP() instanceof IA){
			controlPressed(BattleCommand.ATTACK);
		}			
		return j1;		
	}
	
	public Personnage joueurActif() {
		Personnage p;
		if(j1.equals(player.getP())){
			if(nbTours % 2 != 0)
				p = ennemy.getP();
			else
				p = player.getP();
		}
		else{
			if(nbTours % 2 != 0)
				p = player.getP();
			else
				p = ennemy.getP();
		}
		nbTours++;
		return p;
	}
	
	public double resolutionAttaque(Personnage p, Capacite c){
		double EFF = 0;
		if(c.attaquer(p))
			EFF = c.efficaciteAttaque(p);
	    if(EFF != 0)
	    	BattleController.hud.addLog("Degats de votre attaque : " + EFF);
	    else
	    	BattleController.hud.addLog("Vous n'avez pas réussi votre attaque");
		return EFF;		
	}
	
	public double resolutionAttaqueDiminuee(Personnage p, Capacite c){
		double EFF = 0;
		c.attaquer(p);
		c.setPBA(c.getPBA() - c.getPBA()/4);
		if(c.attaquer(p)){
			EFF = c.efficaciteAttaque(p);
			EFF -= EFF/4;
		}
	    if(EFF != 0)
	    	BattleController.hud.addLog("Degats de votre attaque : " + EFF);
	    else
	    	BattleController.hud.addLog("Vous n'avez pas réussi votre attaque");
		return EFF;
	}
	
	public double resolutionDefense(Personnage p,double EFF_A, Capacite c){
		double EFF_D = 0;
		if(c.seDefendre(p))
			EFF_D = c.efficaciteDefense(p);
		if(p.equals(player.getP()))
			this.ennemy.getP().setVIT(this.ennemy.getP().getVIT() - (EFF_A - EFF_D));
		else
			this.player.getP().setVIT(this.ennemy.getP().getVIT() - (EFF_A - EFF_D));
	    if(EFF_D != 0)
	    	hud.addLog("Nombre de PV absorbés : " + EFF_D);
	    else
	    	hud.addLog("Vous n'avez pas réussi votre défense");
		return EFF_D;
	}
	
	public double resolutionDefenseDiminuee(Personnage p,double EFF_A, Capacite c){
		double EFF_D = 0;
		c.seDefendre(p);
		c.setPBA(c.getPBA() - c.getPBA()/4);
		if(c.seDefendre(p)){
			EFF_D = c.efficaciteDefense(p);
			EFF_D -= EFF_D/4;
		}
		if(p .equals(player.getP()))
			this.ennemy.getP().setVIT(this.ennemy.getP().getVIT() - (EFF_A - EFF_D));
		else
			this.player.getP().setVIT(this.ennemy.getP().getVIT() - (EFF_A - EFF_D));
	    if(EFF_D != 0)
	    	hud.addLog("Nombre de PV absorbés : " + EFF_D);
	    else
	    	hud.addLog("Vous n'avez pas réussi votre défense");
		return EFF_D;
	}
	
	public double resolutionSoin(Personnage p, Capacite c){
		double EFF = 0;
		if(c.seSoigner(p)){
			EFF = c.efficaciteSoin(p);
			p.setVIT(p.getVIT() + (int)EFF);
			if(p.getVIT() > p.getVITMax())
				p.setVITMax();
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
	
	public BattleHud getHud(){
		return hud;
	}
}
