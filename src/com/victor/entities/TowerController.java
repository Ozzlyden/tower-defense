package com.victor.entities;

import java.awt.image.BufferedImage;

import com.victor.main.Game;
import com.victor.world.World;

public class TowerController extends Entity{
	
	public boolean isPressed = false;
	boolean liberado = true;
	public int xTarget, yTarget;
	

	public TowerController(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);

	}
	
	public void tick() {
		
		if(isPressed) {
			isPressed = false;
			liberado = true;
			int xx = (xTarget/16) * 16;
			int yy = (yTarget/16) * 16;
			Player player = new Player(xx, yy, 16, 16, 0, Game.spritesheet.getSprite(0, 48, 16, 16));
			
			// VERIFICACAO DE TOWER EM CIMA DA OUTRA
			for (int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof Player) {
					if(Entity.isColliding(e, player)) {
						liberado = false;
						System.out.println("Ja tem uma TOWER nessa area");
					}
				}
			}
			
			// VERIFICACAO DE TOWER EM CIMA DOS ENIMIES
			if(World.isFree(xx, yy)) {
				liberado = false;
				System.out.println("TOWER nao pode ser colocada ai");
			}
			
			if(liberado) {
				if(Game.money >= 2) {
					Game.entities.add(player);
					Game.money-=10;
				}else {
					System.out.println("Dinheiro insuficiente");
				}
			}
			
		}
		
		if(Game.life <= 0) {
			System.exit(1);	// Game over
		}
		
		
	}

}
