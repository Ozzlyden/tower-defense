package com.victor.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.victor.entities.Player;
import com.victor.main.Game;


public class UI {
	
	public static BufferedImage HEART = Game.spritesheet.getSprite(0, 16, 8, 8);

	public void render(Graphics g) {
		
		for (int i = 0; i< (int)(Game.life); i++) {
			g.drawImage(HEART, 15 + (i * 40), 5, 36, 36, null);
		}
		
		g.setFont(new Font ("arial", Font.BOLD, 24));
		g.setColor(Color.yellow);
		g.drawString("$" + Game.money, (Game.WIDTH * Game.SCALE) - 80, 24);
	}
	
}
