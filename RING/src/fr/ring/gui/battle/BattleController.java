package fr.ring.gui.battle;

import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.BasicCommand;
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
	
	public static int nbTours = 0;
	public static ArrayList<Capacite> capButton = new ArrayList<Capacite>();
	
	private BattleHud hud;	
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
	private KeyControl kF1 = new KeyControl(Input.KEY_F1);
	private KeyControl kF2 = new KeyControl(Input.KEY_F2);
		
	public BattleController(BattlePlayer player, BattleEnnemy ennemy,GameContainer container,  StateBasedGame game) throws SlickException{
		this.player = player;
		this.ennemy = ennemy;
		this.game = game;
		this.container = container;
		buttonImage = new Image("fr/ring/gui/ressources/hud/hud_button.png");
	}
	
	public enum BattleCommand implements Command {
		ATTACK, REDUCED_ATTACK, DEFEND, REDUCED_DEFEND, VOIR_ATTAQUE, HEAL, SURRENDER
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
					resolutionDefense(p, atk, p.getCAP().get(0));
				}
				else{
					BattleGameState.provider.setActive(false);
					initCapButton(p, "Defense");
					cc = new CapController(p, (BattleCommand)command);
					BattleGameState.capProvider.addListener(cc);
				}
				break;
			
			case VOIR_ATTAQUE:
				BattleGameState.defenseProvider.clearCommand(BattleCommand.VOIR_ATTAQUE);
				BattleGameState.defenseProvider.unbindCommand(kF1);
				BattleGameState.defenseProvider.unbindCommand(kF2);
				hud.addLog("Appuyez sur F1 pour vous defendre ou sur F2 pour encaisser l'attaque puis attaquer en retour");
				BattleGameState.defenseProvider.bindCommand(kF1, BattleCommand.REDUCED_DEFEND);
				BattleGameState.defenseProvider.bindCommand(kF2, BattleCommand.REDUCED_ATTACK);
				break;
			
			case REDUCED_DEFEND:
				BattleGameState.provider.setActive(false);
				BattleGameState.defenseProvider.clearCommand(BattleCommand.REDUCED_DEFEND);
				initCapButton(p, "Defense");
				cc = new CapController(p, BattleCommand.REDUCED_DEFEND);
				BattleGameState.capProvider.addListener(cc);
				break;
			case REDUCED_ATTACK:
				BattleGameState.provider.setActive(false);
				BattleGameState.defenseProvider.clearCommand(BattleCommand.REDUCED_ATTACK);
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
				break;
			case CAP_3:
				capControlPressedAction(p, ia,  bc, 2);
				break;
			case CAP_4:
				capControlPressedAction(p, ia,  bc, 3);
				break;
			case CAP_5:
				capControlPressedAction(p, ia,  bc, 4);
				break;
			case CAP_6:
				capControlPressedAction(p, ia,  bc, 5);
				break;
			case CAP_7:
				capControlPressedAction(p, ia,  bc, 6);
				break;
			case CAP_8:
				capControlPressedAction(p, ia,  bc, 7);
				break;
			case CAP_9:
				capControlPressedAction(p, ia,  bc, 8);
				break;
			case CAP_10:
				capControlPressedAction(p, ia,  bc, 9);
				break;
			}
		}

		@Override
		public void controlReleased(Command arg0) {
		
		}
	}
	
	@Override
	public void controlPressed(Command command) {
		Personnage p;
		System.out.println(nbTours);
		if(nbTours == 0){
			p = j1;
			nbTours++;
			System.out.println(p.getNom());
		}
		else{
			p = joueurActif();
			System.out.println(p.getNom());
		}
		switch ((BattleCommand) command){
		case ATTACK:
			if(p instanceof IA){
				atk = resolutionAttaque(p, p.getCAP().get(0));
				capAtk = p.getCAP().get(0);
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
				if(atk == 0){
					hud.addLog("Defense le vent");
				}
				else{
					resolutionDefense(p, atk, p.getCAP().get(0));
					hud.addLog(p.getNom() + " s'est defendu !");
				}
			}
			else{
				if(atk == 0){
					hud.addLog("Il n'y a pas eu d'attaque precedemment");
				}
				else{
					BattleGameState.provider.setActive(false);
					hud.addLog("Appuyez sur F1 pour une defense immediate ou F2 pour voir les degats de votre adversaire puis agir");
					dc = new DefenseController(p);
					BattleGameState.defenseProvider.addListener(dc);
				}
			}
			break;
		case HEAL:
			if(p instanceof IA){
				resolutionSoin(p, p.getCAP().get(1));
				hud.addLog(p.getNom() + " s'est soigne !");
			}
			else{
				BattleGameState.provider.setActive(false);
				initCapButton(p, "Soin");
				cc = new CapController(p, (BattleCommand)command);
				BattleGameState.capProvider.addListener(cc);
			}
			break;
		case SURRENDER:
			game.enterState(MapGameState.ID);break;
		default: break;
		}
		if(p.isDead()){
			if(p.equals(player)){
				game.enterState(GameOverGameState.ID);
			}
			else{
				p.setVITMax();
				game.enterState(VictoryGameState.ID);
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
			cleanCapButton();
			BattleGameState.provider.setActive(true);
		}
		else if(bc == BattleCommand.DEFEND){
			resolutionDefense(p, atk, BattleController.capButton.get(0));
			controlPressed(ia.IAprocess(bc));
			BattleGameState.defenseProvider.removeListener(dc);
			BattleGameState.capProvider.removeListener(cc);
			cleanCapButton();
			BattleGameState.provider.setActive(true);
		}
		else if(bc == BattleCommand.REDUCED_DEFEND){
			resolutionDefenseDiminuee(p, atk, BattleController.capButton.get(0));
			controlPressed(ia.IAprocess(BattleCommand.DEFEND));
			BattleGameState.defenseProvider.removeListener(dc);
			BattleGameState.capProvider.removeListener(cc);
			cleanCapButton();
			BattleGameState.provider.setActive(true);
		}
		else if(bc == BattleCommand.REDUCED_ATTACK){
			resolutionAttaqueDiminuee(p, BattleController.capButton.get(0));
			controlPressed(ia.IAprocess(BattleCommand.ATTACK));
			BattleGameState.defenseProvider.removeListener(dc);
			BattleGameState.capProvider.removeListener(cc);
			cleanCapButton();
			BattleGameState.provider.setActive(true);
		}
		else if(bc == BattleCommand.HEAL){
			resolutionSoin(p, BattleController.capButton.get(0));
			controlPressed(ia.IAprocess(BattleCommand.HEAL));
			BattleGameState.capProvider.removeListener(cc);
			cleanCapButton();
			BattleGameState.provider.setActive(true);
		}
	}
	
	public void initCapButton(Personnage p, String nomInterface){
		switch(nomInterface){
		case "Attaque":
			for(int i=0; i<p.getCAP().size(); i++){
				if(p.getCAP().get(i).containInterfaces("Attaque")){
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
		for(int i=0; i<p.getCAP().size(); i++){
			System.out.println(p.getCAP().get(i).getNom());
		}
		for(int i=0; i<capButton.size(); i++){
			System.out.println((i + 1) + " : " + capButton.get(i).getNom());
		}
		if(capButton.isEmpty()){
			System.out.println("Tu es un connard");
		}
		hud.addLog("Quelle compétence choississez vous ?");
		for(int i=0; i<capButton.size(); i++){
			hud.addLog("F" + (i + 1) + " : " + capButton.get(i).getNom());
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
	    	if(p instanceof IA)
	    		hud.addLog(p.getNom() + " vous a attaque !");
	    	else
	    		hud.addLog("Degats de votre attaque : " + EFF);
	    else
	    	if (p instanceof IA)
	    		hud.addLog(p.getNom() + " n'a pas reussi son attaque");
	    	else
	    		hud.addLog("Vous n'avez pas réussi votre attaque");
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
	    	if(p instanceof IA)
	    		hud.addLog(p.getNom() + " vous a attaque !");
	    	else
	    		hud.addLog("Degats de votre attaque : " + EFF);
	    else
	    	if (p instanceof IA)
	    		hud.addLog(p.getNom() + " n'a pas reussi son attaque");
	    	else
	    		hud.addLog("Vous n'avez pas reussi votre attaque");
		return EFF;
	}
	
	public double resolutionDefense(Personnage p,double EFF_A, Capacite c){
		double EFF_D = 0;
		if(c.seDefendre(p))
			EFF_D = c.efficaciteDefense(p);
		if(p.equals(player.getP()))
			player.getP().setVIT(player.getP().getVIT() - (EFF_A - EFF_D));
		else
			ennemy.getP().setVIT(ennemy.getP().getVIT() - (EFF_A - EFF_D));
	    if(EFF_D != 0){
	    	if(p instanceof IA)
	    		hud.addLog(p.getNom() + " se defend");
	    	hud.addLog("Nombre de PV absorbés : " + EFF_D);
	    }
	    else
	    	if(p instanceof IA)
	    		hud.addLog(p.getNom() + " n'a pas reussi sa defense");
	    	else
	    		hud.addLog("Vous n'avez pas reussi votre defense");
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
	    if(EFF_D != 0){
	    	if(p instanceof IA)
	    		hud.addLog(p.getNom() + " se defend");
	    	hud.addLog("Nombre de PV absorbés : " + EFF_D);
	    }
	    else
	    	if(p instanceof IA)
	    		hud.addLog(p.getNom() + " n'a pas reussi sa defense");
	    	else
	    		hud.addLog("Vous n'avez pas reussi votre defense");
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
	    if(EFF != 0){
	    	if(p instanceof IA)
	    		hud.addLog(p.getNom() + " se soigne");
	    	hud.addLog("Nombre de PV regagnes : " + EFF);
	    }
	    else
	    	if(p instanceof IA)
	    		hud.addLog(p.getNom() + " n'a pas reussi son soin");
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

	public KeyControl getkF1() {
		return kF1;
	}

	public void setkF1(KeyControl kF1) {
		this.kF1 = kF1;
	}

	public KeyControl getkF2() {
		return kF2;
	}

	public void setkF2(KeyControl kF2) {
		this.kF2 = kF2;
	}
}
