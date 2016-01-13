package graphics;

import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;


public class MyWindow extends JFrame implements KeyListener{

	int width = 500;
	int height = 500;
	private Hero blu;
	private Hero merchant;
	BufferedImage landscape;
	boolean merchEaten = false;

	public static void main(String[] args){
		new MyWindow();
	}

	public MyWindow(){
		blu = new Hero("Blu", "/images/heros/megastand.png", 200, 200);
		merchant = new Hero("merchant", "/images/heros/merchant.png", 50, 200);
		landscape = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D)landscape.getGraphics();
		paintLandscape(g2);
		
		
		//following JFrame methods
		setVisible(true);
		setSize(width, height); //units in px
		setLocation(200, 150); //200 px right, 150 px down
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //static constant for reference for special close operation
		addKeyListener(this);
		
	}


	private void paintLandscape(Graphics2D g2) {
		g2.setColor(new Color(104, 152, 76));
		g2.fillOval(0, 0, width, height);
		g2.setColor(Color.DARK_GRAY);
		g2.drawOval(0, 0, width, height);
		
		//x y width height startDeg lengthDeg
		
		for (int i = 100; i < 103 ; i++){
			g2.drawArc(25, i, 200, 100, 0, 180);
			g2.drawArc(240, i, 200, 100, 0, 180);
		}
		for (int i = 150; i < 153; i++){
			g2.drawString("EYE", 60, i);
			g2.drawString("EYE", 310, i);
		}
		
	}

	public void paint(Graphics g){

		//Graphics is like a crayon box
		//Graphics2d is like an art kit
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D)image.getGraphics();
		g2.setColor(Color.white);
		g2.fillRect(0, 0, width, height);
	
		
		g2.setColor(new Color(0, 0, 0));
		g2.fillRect(0, 0, width, height);

		//landscape
		g2.drawImage(landscape, 0, 0, null);
		
		if(Math.abs(blu.getX() - merchant.getX()) + Math.abs(blu.getY() - merchant.getY()) < 10)
			merchEaten = true;
		
		
		if(!merchEaten) g2.drawImage(merchant.getImage(), merchant.getX(), merchant.getY(), null);
		
		//heros ROFL
		g2.drawImage(blu.getImage(), blu.getX(), blu.getY(), null);	
		
		
		
		
	/**
		g2.setColor(new Color(104, 152, 76));
		g2.fillOval(0, 0, width, height);
		g2.setColor(Color.DARK_GRAY);
		g2.drawOval(0, 0, width, height);
		
		//x y width height startDeg lengthDeg
		
		for (int i = 100; i < 103 ; i++){
			g2.drawArc(25, i, 200, 100, 0, 180);
			g2.drawArc(240, i, 200, 100, 0, 180);
		}
		for (int i = 150; i < 153; i++){
			g2.drawString("EYE", 60, i);
			g2.drawString("EYE", 310, i);
		}
		
		//lines: start x, start y, end x, end y
		//g2.drawLine(width, 0, 0, height);
		//g2.drawString("WORDS", 300, 300);
		
		int squareD = 20;
		int margin = 2;
		int c = 1;
		/* make gradiant of blue
		for(int x = 0; x < width; x += squareD+margin){
			for (int y = 0; y < height; y += squareD+margin){
				g2.fillRect(x, y, squareD, squareD);
				g2.setColor(new Color(0, 0, ++c % 255));
			}
		}
		*/
		//draw the bufferedImage on the canvas
		g.drawImage(image, 0, 0, null);
		
	
	}

	public void keyPressed(KeyEvent arg0) {
		int key = arg0.getKeyCode();
		if(key == KeyEvent.VK_UP) {
			blu.moveUp();
		}else if(key == KeyEvent.VK_DOWN) {
			blu.moveDown();
		}else if(key == KeyEvent.VK_LEFT) {
			blu.moveLeft();
		}else if(key == KeyEvent.VK_RIGHT) {
			blu.moveRight();
		}
		repaint();
	}
	public void keyReleased(KeyEvent arg0){
		
	}
	public void keyTyped(KeyEvent arg0){
		
	}




}
