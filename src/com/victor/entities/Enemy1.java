package com.victor.entities;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.victor.main.Game;
import com.victor.world.AStar;
import com.victor.world.Camera;
import com.victor.world.Vector2i;
import com.victor.world.World;

public class Enemy1 extends Entity{
	
	public boolean right = true, left = false;
	
	private int frames = 0, maxFrames = 25, index = 0, maxIndex = 1;
	
	public int vida = 1;
	
	public BufferedImage[] ENEMY2_RIGHT;
	public BufferedImage[] ENEMY2_LEFT;


	public Enemy1(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		// TODO Auto-generated constructor stub
		
		ENEMY2_RIGHT = new BufferedImage [2];
		ENEMY2_LEFT = new BufferedImage [2];
		
		ENEMY2_RIGHT[0] = Game.spritesheet.getSprite(0, 80, 16, 16);
		ENEMY2_RIGHT[1] = Game.spritesheet.getSprite(16, 80, 16, 16);
		ENEMY2_LEFT[0] = Game.spritesheet.getSprite(0, 96, 16, 16);
		ENEMY2_LEFT[1] = Game.spritesheet.getSprite(16, 96, 16, 16);
		
	}

	public void tick() {
		
		//path = AStar.findPath(Game.world, new Vector2i(World.xINITIAL, World.yINITIAL), new Vector2i(World.xFINAL, World.yFINAL));
		
		// ALGORITMO A*
		
		if(path == null || path.size() == 0) {
			Vector2i start = new Vector2i(this.getX()/16, this.getY()/16);	//posicao inicial
			// Colocamos as posicoes atuais 
			Vector2i end = new Vector2i(World.xFINAL, World.yFINAL);	//destino final
			path = AStar.findPath(Game.world, start, end);
		}
		followPath(path);
		
		// SISTEMA DE DANO
		if(x >= Game.WIDTH) {
			Game.life -= Entity.rand.nextDouble();
			Game.entities.remove(this);
			return;
		}
		
	}
	
	public boolean test(){
		return false;
	}
	
	public void render(Graphics g) {
		/*
		if(right) {
			g.drawImage(ENEMY2_RIGHT[index],this.getX(), this.getY(), null);
		}else if(left) {
			g.drawImage(ENEMY2_LEFT[index],this.getX(), this.getY(), null);
		}
		*/
		
	//DEBUG  MASK
	//g.setColor(Color.BLUE);
	//g.fillRect(getX() - Camera.x, getY() - Camera.y, width, height);
	super.render(g);
	}
}
