package fr.ring.personnage;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import fr.ring.capacites.Capacite;
import fr.ring.capacites.Epee;
import fr.ring.capacites.PotionSoinsMineure;


public class Imp extends IA{
	
	private  Animation animationAtk = new Animation();
	
	public Imp() {
		super("Imp",20,10,10,10,new ArrayList<Capacite>());
	}

	public Imp(String nom, int fOR, int dEX, int iNT, int cON, ArrayList<Capacite> cAP) {
		super(nom, fOR, dEX, iNT, cON, cAP);
		getCAP().add(new Epee());
		getCAP().add(new PotionSoinsMineure());
	}

	public Imp(Personnage hero) {
		super(hero);
	}
	
	public void initBattle() throws SlickException{
		battleSpriteSheet = new SpriteSheet[1];
		battleSpriteSheet[0]= new SpriteSheet("fr/ring/gui/ressources/map/imp/attack - sword.png", 64, 64);
		/*animationAtk.addFrame(battleSpriteSheetImp.getSprite(1, 4), 100);
		 animationAtk.addFrame(battleSpriteSheetImp.getSprite(2, 4), 100);
		 animationAtk.addFrame(battleSpriteSheetImp.getSprite(3, 4), 100);
		 animationAtk.addFrame(battleSpriteSheetImp.getSprite(4, 4), 100);*/
	}
	
	public void renderBattleEpee(Graphics g) throws SlickException{
		g.drawAnimation(animationAtk, x, y);
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

	public Animation getAnimationAtk() {
		return animationAtk;
	}

	public void setAnimationAtk(Animation animationAtk) {
		this.animationAtk = animationAtk;
	}

	@Override
	public void initWalkCycle() throws SlickException {}

	@Override
	public void renderWalkCycle(Graphics g) throws SlickException {	}

}
