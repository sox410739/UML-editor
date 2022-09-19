package mode;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import obj.BasicObject;
import obj.Point;

public class Selector {
	private int x;
	private int y;
	private int width;
	private int height;
	
	private static Selector selector;
	
	private Selector() { this.setAttribute(0, 0, 0, 0); }
	
	private Selector(int x, int y, int width, int height) { this.setAttribute(x, y, width, height); }
	
	public static void createSelector() {
		if (Selector.selector == null) {
			Selector.selector = new Selector();
		}		
	}
	
	public static Selector getSelector() { return Selector.selector; }
	
	public static void removeSelector() { Selector.selector = null; }
	
	public void setAttribute(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public boolean include(Point point) {
		boolean horizontal = point.getCenterX() > this.x && (point.getCenterX() - this.x) < this.width;
		boolean vertical = point.getCenterY() > this.y && (point.getCenterY() - this.y) < this.height;
		
		return horizontal && vertical;
	}
	
	public boolean include(BasicObject basicObject) {
		for (Point point: basicObject.getPoints()) {
			if (!this.include(point)) return false;
		}
		return true;
	}
	
	
	public void draw(Graphics graphic) {
		Color color = Color.BLACK;
		BasicStroke stroke = new BasicStroke(1.5f, BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 10.0f, new float[] { 10.0f }, 0.0f);
		
		Graphics2D graphic2D = (Graphics2D) graphic;
		graphic2D.setColor(color);
		graphic2D.setStroke(stroke);
		graphic2D.drawRect(this.x, this.y, this.width, this.height);
	}
}
