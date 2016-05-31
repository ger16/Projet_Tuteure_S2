package fr.ring.gui.battle;

import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
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
	
	private BattlePlayer player;
	private BattleEnnemy ennemy;
	private Personnage j1;
	private GameContainer container;
	private StateBasedGame game;
	Image buttonImage;
	private BattleHud hud;
	private double atk = 0;
	/*ArrayList<MouseOverArea> capButtonAtk = new ArrayList<MouseOverArea>();
	ArrayList<MouseOverArea> capButtonDef = new ArrayList<MouseOverArea>();
	ArrayList<MouseOverArea> capButtonHeal = new ArrayList<MouseOverArea>();
	MouseOverArea test;*/
		
	public BattleController(BattlePlayer player, BattleEnnemy ennemy,GameContainer container,  StateBasedGame game) throws SlickException{
		this.player = player;
		this.ennemy = ennemy;
		this.game = game;
		this.container = container;
		buttonImage = new Image("fr/ring/gui/ressources/hud/hud_button.png");
	}
	
	public enum BattleCommand implements Command {
		ATTACK, DEFEND, HEAL, SURRENDER
	}
	
	/*class CapButtonListener implements ComponentListener{
		private Personnage p;
		private int i;
		private BattleCommand bc;
		
		public CapButtonListener(Personnage p, int i, BattleCommand bc){
			this.p = p;
			this.i = i;
			this.bc = bc;
		}
		
		@Override
		public void componentActivated(AbstractComponent component) {
			switch(bc){
			case ATTACK:
				atk = resolutionAttaque(p, p.getCAP().get(i));
				for(int i=0; i<capButtonAtk.size(); i++){
					capButtonAtk.remove(i);
				}
				if(ennemy.getP() instanceof IA){
					IA temp;
					temp = (IA) ennemy.getP();
					controlPressed(temp.IAprocess(BattleCommand.ATTACK));
				}
				break;
			case DEFEND:
				break;
			case HEAL:
				break;
			default:
				break;
			}	
		}		
	}*/
	
	@Override
	public void controlPressed(Command command) {
		Personnage p;
		if(nbTours == 0)
			p = j1;
		else
			p = joueurActif();
		if(p.equals(ennemy.getP())){
			BattleGameState.provider.setActive(false);
		}
		if(p.equals(player.getP())){
			switch ((BattleCommand) command){
			case ATTACK:
				System.out.println(p.getCAP().get(0));
				System.out.println(p.getCAP().get(1));
				if(p instanceof IA){
					atk = resolutionAttaque(p, p.getCAP().get(0));
				}
				else{
					for(int i=0; i<p.getCAP().size(); i++){
						hud.addLog(p.getCAP().get(i).toString());
					}
					hud.addLog("Quelle compétence choississez vous ?");
					InputProvider capProvider = new InputProvider(container.getInput());
				}
					
				break;
			case DEFEND: 
				resolutionDefense(player.getP(), resolutionAttaque(ennemy.getP(), ennemy.getP().getCAP().get(0)), player.getP().getCAP().get(0), DEFENSE_IMMEDIATE);break;
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
	    if (player.getP().getVIT() <= 0)
	        game.enterState(MainScreenGameState.ID);
	    if(EFF != 0)
	    	hud.addLog("Degats de votre attaque : " + EFF);
	    else
	    	hud.addLog("Vous n'avez pas réussi votre attaque");
		if(p.equals(player.getP()))
			this.ennemy.getP().setVIT(this.ennemy.getP().getVIT() - EFF);
		return EFF;		
	}
	
	public double resolutionDefense(Personnage p,double EFF_A, Capacite c, int decision){
		double EFF_D = 0;
		
		if(decision == DEFENSE_IMMEDIATE){
			if(c.seDefendre(p))
				EFF_D = c.efficaciteDefense(p);
			if(p == this.player.getP())
				this.ennemy.getP().setVIT(this.ennemy.getP().getVIT() - (EFF_A - EFF_D));
			else
				this.player.getP().setVIT(this.ennemy.getP().getVIT() - (EFF_A - EFF_D));
		}
		else if(decision == DEFENSE_CALCULEE){
			c.seDefendre(p);
			c.setPBA(c.getPBA() - c.getPBA()/4);
			if(c.seDefendre(p)){
				EFF_D = c.efficaciteDefense(p);
				EFF_D -= EFF_D/4;
			}
			if(p == this.player.getP())
				this.ennemy.getP().setVIT(this.ennemy.getP().getVIT() - (EFF_A - EFF_D));
			else
				this.player.getP().setVIT(this.ennemy.getP().getVIT() - (EFF_A - EFF_D));
		}
		else{
			if(p == this.player.getP())
				this.ennemy.getP().setVIT(this.ennemy.getP().getVIT() - EFF_A);
			else
				this.player.getP().setVIT(this.ennemy.getP().getVIT() - EFF_A);
		}
	    if (player.getP().isDead())
	        game.enterState(MainScreenGameState.ID);
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
	
	/*public void render(GameContainer container, Graphics g){
		for(int i=0; i<capButtonAtk.size(); i++){
			capButtonAtk.get(i).render(container, g);
			g.drawString(player.getP().getCAP().get(i).getNom(), capButtonAtk.get(i).getX() + BattleHud.X_PADDING, capButtonAtk.get(i).getY() + BattleHud.Y_PADDING);
		}
		test.render(container, g);
		g.drawString("test", test.getX(), test.getY());
	}*/
	
	public void setHud(BattleHud hud) {
		this.hud = hud;
	}
	
	public BattleHud getHud(){
		return hud;
	}
}
