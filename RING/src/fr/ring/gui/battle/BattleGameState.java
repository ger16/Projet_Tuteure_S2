package fr.ring.gui.battle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.ring.gui.battle.BattleController.BattleCommand;
import fr.ring.gui.battle.BattleController.CapController;
import fr.ring.gui.map.MapGameState;
import fr.ring.gui.map.TriggerController;
import fr.ring.personnage.Imp;

public class BattleGameState extends BasicGameState {
	
	public static final int ID = 4;
	public static InputProvider provider;
	public static InputProvider capProvider;
	public static InputProvider defenseProvider;
	public static BattlePlayer player;
	public static BattleEnnemy ennemy;
	
	private Image background;
	private BattleHud battleHud;
	private BattleController controller;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		background = new Image("fr/ring/gui/ressources/map/background/background2.png");
	}
	
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		player = new BattlePlayer(MapGameState.p);
		ennemy = new BattleEnnemy(TriggerController.boss);
		player.init();
		ennemy.init();
	    controller = new BattleController(player, ennemy,container , game);
	    provider = new InputProvider(container.getInput());
	    capProvider = new InputProvider(container.getInput());
	    defenseProvider = new InputProvider(container.getInput());
	    provider.bindCommand(new KeyControl(Input.KEY_A), BattleCommand.ATTACK);
	    provider.bindCommand(new KeyControl(Input.KEY_D), BattleCommand.DEFEND);
	    provider.bindCommand(new KeyControl(Input.KEY_S), BattleCommand.HEAL);
	    provider.bindCommand(new KeyControl(Input.KEY_C), BattleCommand.SURRENDER);
		capProvider.bindCommand(new KeyControl(Input.KEY_F1), BattleController.CapCommand.CAP_1);
		capProvider.bindCommand(new KeyControl(Input.KEY_F2), BattleController.CapCommand.CAP_2);
		capProvider.bindCommand(new KeyControl(Input.KEY_F3), BattleController.CapCommand.CAP_3);
		capProvider.bindCommand(new KeyControl(Input.KEY_F4), BattleController.CapCommand.CAP_4);
		capProvider.bindCommand(new KeyControl(Input.KEY_F5), BattleController.CapCommand.CAP_5);
		capProvider.bindCommand(new KeyControl(Input.KEY_F6), BattleController.CapCommand.CAP_6);
		capProvider.bindCommand(new KeyControl(Input.KEY_F7), BattleController.CapCommand.CAP_7);
		capProvider.bindCommand(new KeyControl(Input.KEY_F8), BattleController.CapCommand.CAP_8);
		capProvider.bindCommand(new KeyControl(Input.KEY_F9), BattleController.CapCommand.CAP_9);
		capProvider.bindCommand(new KeyControl(Input.KEY_F10), BattleController.CapCommand.CAP_10);
		defenseProvider.bindCommand(controller.getkF1(), BattleCommand.DEFEND);
		defenseProvider.bindCommand(controller.getkF2(), BattleCommand.VOIR_ATTAQUE);
	    provider.addListener(controller);
	    this.battleHud = new BattleHud(controller, game);
	    this.battleHud.init(container, game);
	    controller.JoueurPremierTour();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		background.draw(0, 0, container.getWidth(), container.getHeight());
		BattleGameState.player.render(container, g);
		BattleGameState.ennemy.render(container, g);
		this.battleHud.render(container, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
	}

	@Override
	public int getID() {
		return this.ID;
	}
}
