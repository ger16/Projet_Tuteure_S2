package fr.ring.gui.battle;

import java.io.IOException;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.ring.gui.map.MapGameState;
import fr.ring.personnage.Personnage;

import shionn.slick.ui.TextArea;
import shionn.slick.ui.align.VerticalAlignement;

public class GameOverGameState extends BasicGameState {
	
	public static final int ID = 7, SPACE = 5;
	private Personnage p;
	private StateBasedGame game;
	private Image background;
	private TextArea log;
	private CapController cc;
	
	
	public GameOverGameState() {
	}
	
	class CapController implements InputProviderListener{

		@Override
		public void controlPressed(Command command) {
			try{
				switch((BattleController.CapCommand) command){
					case CAP_1:
						MapGameState.p.getCAP().remove(0);
						BattleGameState.capProvider.removeListener(cc);
						break;
					case CAP_2:
						MapGameState.p.getCAP().remove(1);
						BattleGameState.capProvider.removeListener(cc);
						break;
					case CAP_3:
						MapGameState.p.getCAP().remove(2);
						BattleGameState.capProvider.removeListener(cc);
						break;
					case CAP_4:
						MapGameState.p.getCAP().remove(3);
						BattleGameState.capProvider.removeListener(cc);
						break;
					case CAP_5:
						MapGameState.p.getCAP().remove(4);
						BattleGameState.capProvider.removeListener(cc);
						break;
					case CAP_6:
						MapGameState.p.getCAP().remove(5);
						BattleGameState.capProvider.removeListener(cc);
						break;
					case CAP_7:
						MapGameState.p.getCAP().remove(6);
						BattleGameState.capProvider.removeListener(cc);
						break;
					case CAP_8:
						MapGameState.p.getCAP().remove(7);
						BattleGameState.capProvider.removeListener(cc);
						break;
					case CAP_9:
						MapGameState.p.getCAP().remove(8);
						BattleGameState.capProvider.removeListener(cc);
						break;
					case CAP_10:
						MapGameState.p.getCAP().remove(9);
						BattleGameState.capProvider.removeListener(cc);
						break;
				}
			}
			catch(IndexOutOfBoundsException | NullPointerException e){
				e.printStackTrace();
			}
		}

		@Override
		public void controlReleased(Command arg0) {
			
		}
		
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		background = new Image("fr/ring/gui/ressources/map/background/defeatScreen.jpg");
		log = new TextArea(container.getWidth()/3 + SPACE * 10, container.getHeight()/2, container.getWidth()/2, container.getHeight()/3);
		log.setBottomUp(true);
		log.setDefaultFont(container.getDefaultFont());
	}
	
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		if(MapGameState.p.isDead()){
			addLog("Votre personnage est mort, vous ne pourrez plus le reutiliser, appuyez sur echap pour retourner au menu");
			MapGameState.p.supprimerHero();
		}
		
		else{
			addLog("Vous avez capitule, votre experience et vos aptitudes seront affectes en consequence");
			if(MapGameState.p.getEXP() > 1){	
				Random rand = new Random();
				switch(rand.nextInt(4)){
				case 0: MapGameState.p.setFOR(MapGameState.p.getFOR() - 1);break;
				case 1: MapGameState.p.setDEX(MapGameState.p.getDEX() - 1);break;
				case 2: MapGameState.p.setINT(MapGameState.p.getINT() - 1);break;
				case 3: MapGameState.p.setCON(MapGameState.p.getCON() - 1);break;
				default:break;
				}
				MapGameState.p.setEXP(MapGameState.p.getEXP() - 1);
				if(MapGameState.p.getEXP() > 6 && MapGameState.p.getCAP().size() > MapGameState.p.getEXP() /2){
					addLog("Vous devez choisir une competence a supprimer pour respecter les conditions de niveau");
					cc = new CapController();
					BattleGameState.capProvider.addListener(cc);
					addLog("Quelle competence choississez vous ?");
					for(int i=0; i<MapGameState.p.getCAP().size(); i++){
						addLog("F" + (i + 1) + " : " + MapGameState.p.getCAP().get(i).getNom());
					}
				}
			}
			try {
				MapGameState.p.sauvergarderHero();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.setColor(Color.white);
		background.draw(0, 0, container.getWidth(), container.getHeight());
		log.render();
		g.setColor(Color.black);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

	}
	
	public void addLog(String text) {
		log.addFirstText(text, VerticalAlignement.LEFT);
	}


	@Override
	public int getID() {
		return ID;
	}

	public Personnage getP() {
		return p;
	}

	public void setP(Personnage p) {
		this.p = p;
	}

}
