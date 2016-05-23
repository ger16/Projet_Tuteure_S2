package fr.ring.gui;

import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;


public class Map {
	private TiledMap tiledMap;
		
	
	public void init() throws SlickException {
		this.tiledMap = new TiledMap("fr/ring/gui/ressources/map/ring_map.tmx");
	}
	
	public void renderBackground() {
		this.tiledMap.render(0, 0, 0);
		this.tiledMap.render(0, 0, 1);
		this.tiledMap.render(0, 0, 2);
		this.tiledMap.render(0, 0, 3);
		this.tiledMap.render(0, 0, 4);
		this.tiledMap.render(0, 0, 5);
	}

	public void renderForeground() {
		this.tiledMap.render(0, 0, 6);
		this.tiledMap.render(0, 0, 7);
	}
	  
	public boolean isCollision(float x, float y) {
		int tileW = this.tiledMap.getTileWidth();
		int tileH = this.tiledMap.getTileHeight();
		int logicLayer = this.tiledMap.getLayerIndex("Collisions");
		Image tile = this.tiledMap.getTileImage((int) x / tileW, (int) y / tileH, logicLayer);
		boolean collision = tile != null;
		if (collision) {
			Color color = tile.getColor((int) x % tileW, (int) y % tileH);
			collision = color.getAlpha() > 0;
		}	
		return collision;
	}
	    
	public void changeMap(String file) throws SlickException {
		this.tiledMap = new TiledMap(file);
	}
	  
	public int getObjectCount() {
		return this.tiledMap.getObjectCount(0);
	}
	public String getObjectType(int objectID) {
		return this.tiledMap.getObjectType(0, objectID);
	}
	public float getObjectX(int objectID) {
		return this.tiledMap.getObjectX(0, objectID);
	}
	public float getObjectY(int objectID) {
		return this.tiledMap.getObjectY(0, objectID);
	}
	public float getObjectWidth(int objectID) {
		return this.tiledMap.getObjectWidth(0, objectID);
	}
	public float getObjectHeight(int objectID) {
		return this.tiledMap.getObjectHeight(0, objectID);
	}
	public String getObjectProperty(int objectID, String propertyName, String def) {
		return this.tiledMap.getObjectProperty(0, objectID, propertyName, def);
	}
}
