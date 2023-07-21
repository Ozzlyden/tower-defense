package com.victor.main;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
//import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.victor.entities.Enemy1;
import com.victor.entities.Entity;
import com.victor.entities.Player;
import com.victor.graficos.Spritesheet;
import com.victor.graficos.UI;
import com.victor.world.World;

public class Game extends Canvas implements Runnable,KeyListener,MouseListener, MouseMotionListener{
	
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	public static final int WIDTH = 240;
	public static final int HEIGHT = 160;
	public static final int SCALE = 3;
	
	private BufferedImage image;
	
	public static World world;
	public static List<Entity> entities;
	public static List<Enemy1> enemies1;
	
	public static Spritesheet spritesheet;
	
	public static double life = 10;
	public static int money = 100;
	
	public UI ui;			
	
	public Game() {
		
		//CHAMANDO BIBLIOTECAS
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		
		//FULLSCREEN
		//setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		
		//JANELA
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		intFrame();
		
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		spritesheet = new Spritesheet("/spritesheet.png");	//chamando o arquivo res/spritesheet.png
		
		//Instanciando Entities
		entities = new ArrayList<Entity>();
		enemies1 = new ArrayList<Enemy1>();
		
		//INICIALIZANDO OBJETOS
		world = new World("/level1.png");
		ui = new UI();
		
	}
	
	public void intFrame() {
		frame = new JFrame("Tower Defense");
		frame.add(this);
		//frame.setUndecorated(true);	//vai tirar as barras da janela
		frame.setResizable(false);
		frame.pack();
		
		//ICONE DA JANELA
		Image icon = null;
		try {
			icon = ImageIO.read(getClass().getResource("/icon.png"));	//tentar buscar a imagem
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		frame.setIconImage(icon);	//troca o icon do java pelo seu
		frame.setAlwaysOnTop(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main (String [] args) {
		//Sound.Clips.music1.loop();
		Game game = new Game();
		game.start();
		
	}
	
	
	public void tick () {
				
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
		}
	}
	
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		//OTIMIZACAO DE RENDER, para veriguar o Buffer
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		
		g.setColor(new Color (86, 198, 255));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//RENDER DO GAME
		world.render(g);
		Collections.sort(entities, Entity.nodeSorter);	//Organizar camadas de render
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}
		
		g.dispose();
		g = bs.getDrawGraphics();
		
		g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);	//modo janela
		
		//fullscreen 
		//g.drawImage(image, 0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height, null); 
		
		ui.render(g);
		bs.show();	
	}


	//GAME LOOP PROFISSIONAL
	@Override
	public void run() {
		
		requestFocus();	//foco na janela do game ao iniciar
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		requestFocus(); 	//Comando para n ter que clicar na janela para se mxer
		
		while (isRunning ) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS:" + frames);
				frames = 0;
				timer+=1000;
			}
			
		}
		stop();
	}
	
	//TECLADO
	@Override
	public void keyPressed(KeyEvent e) {
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
	
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	//MOUSE
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
	
}