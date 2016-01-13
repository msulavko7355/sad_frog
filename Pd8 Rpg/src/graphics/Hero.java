package graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Hero {

	BufferedImage sprite;
	String name;
	int x;
	int y;
	
	public Hero(String name, String imageLocation, int locationX,
			int locationY) {
		// TODO Auto-generated constructor stub
		this.name = name;
		int width = 244;
		int height = 190;
		x = locationX;
		y = locationY;
		sprite = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		URL url = getClass().getResource(imageLocation);
		try {
			BufferedImage original = ImageIO.read(url);
			//Draws image on canvas scaled
			Graphics2D g = (Graphics2D) sprite.getGraphics();
			int w = original.getWidth();
			int h = original.getHeight();
			//what to draw, where start (x,y), width/height of canvas relative to start, where to start from (x,y), width/heigth of original, null
			g.drawImage(original, 0, 0, width, height, 0, 0, w, h, null);
		} catch (IOException e){
			e.printStackTrace();
		}
	
	}
	public BufferedImage getImage() {
		return sprite;
	}
	public String getName() {
		return name;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void moveUp(){
		y-= 3;
	}
	public void moveDown(){
		y+= 3;
	}
	public void moveLeft(){
		x-= 3;
	}
	public void moveRight(){
		x+= 3;
	}
}
