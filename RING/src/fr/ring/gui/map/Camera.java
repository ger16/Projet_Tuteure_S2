package fr.ring.gui.map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;


public class Camera {
	private Player player;
	private float xCamera, yCamera;
	
	public Camera(Player player) {
		this.player = player;
		this.xCamera = player.p.getX();
		this.yCamera = player.p.getY();
	}
	
	public void place(GameContainer container, Graphics g) {
		g.translate(container.getWidth() / 2 - (int) this.xCamera,
				container.getHeight() / 2 - (int) this.yCamera);
	}
	
	public void update(GameContainer container) {
		int w = container.getWidth() / 4;
		if (this.player.p.getX() > this.xCamera + w) {
			this.xCamera = this.player.p.getX() - w;
		} 
		else if (this.player.p.getX() < this.xCamera - w) {
			this.xCamera = this.player.p.getX() + w;
		}
		int h = container.getHeight() / 4;
		if (this.player.p.getY() > this.yCamera + h) {
			this.yCamera = this.player.p.getY() - h;
		} 
		else if (this.player.p.getY() < this.yCamera - h) {
			this.yCamera = this.player.p.getY() + h;
		}
	}
	
}
