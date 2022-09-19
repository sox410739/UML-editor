package obj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import main.Config;

public class Point {
	private int x;
	private int y;
	private int radius;
	
	public Point(int centerX, int centerY) {
		this.radius = Config.POINT_RADIUS;
		this.setLocation(centerX, centerY);
	}
	
	public int getCenterX() { return this.x+this.radius; }
	public int getCenterY() { return this.y+this.radius; }
	
	public void setLocation(int centerX, int centerY) {
		
		this.x = centerX - this.radius;
		this.y = centerY - this.radius;
	}
	
	public void draw(Graphics graphic) {
		Color color = Color.BLACK;
		
		Graphics2D graphic2D = (Graphics2D) graphic;
		graphic2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphic2D.setColor(color);
		graphic2D.fillOval(this.x, this.y, this.radius*2, this.radius*2);
	}
}
