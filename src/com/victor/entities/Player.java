package com.victor.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.victor.main.Game;
import com.victor.world.Camera;
import com.victor.world.World;


public class Player extends Entity {
	
	
	public int xTarget, yTarget;
	public boolean isAttack = false;
	
	public Player(int x, int y, int width, int height,double speed, BufferedImage sprite) {
		super(x, y, width, height,speed, sprite);
	
	
	}
	
	public void tick() {
		
		// CALCULO DE DISTANCIA ENTRE TOWER E ENEMY
		Enemy1 enemy1 = null;
		for(int i = 0; i < Game.entities.size(); i++) {
			 Entity e = Game.entities.get(i);
			 if(e instanceof Enemy1) {
				 
				 int xEnemy1 = e.getX();
				 int yEnemy1 = e.getY();
				 
				 if(Entity.calculateDistance(this.getX(), this.getY(), xEnemy1, yEnemy1) < 40) {
					 enemy1 = (Enemy1)e;
				 }
			 }
		}
		
		if (enemy1 != null) {
			isAttack = true;
			xTarget = enemy1.getX();
			yTarget = enemy1.getY();
			
			//SISTEMA DE DANO
			if(Entity.rand.nextInt(100) < 50) {
				enemy1.vida -= Entity.rand.nextDouble();
			}
		}else {
			isAttack = false;
		}
		
	}
		
	public void render(Graphics g) {
		super.render(g);
		
		if(isAttack) {
			g.setColor(Color.blue);
			g.drawLine((int)x + 8, (int) y + 8, xTarget, yTarget);
		}
		
	}

}

