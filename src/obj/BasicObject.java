package obj;

import java.awt.Graphics;

import main.Canvas;

public abstract class BasicObject {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	protected boolean isSelected;
	protected Point[] points;
	protected String text;
	
	public BasicObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.isSelected = false;
		
		this.points = new Point[4];
		this.points[0] = new Point(this.x , this.y + this.height/2);				// 東
		this.points[1] = new Point(this.x + this.width/2, this.y + this.height);	// 南
		this.points[2] = new Point(this.x + this.width, this.y + this.height/2);	// 西
		this.points[3] = new Point(this.x + this.width/2, this.y);					// 北
	}
	
	public int getX() { return this.x; }
	public int getY() { return this.y; }
	public int getWidth() { return this.width; }
	public int getHeight() { return this.height; }
	public boolean getIsSelected() { return this.isSelected; }
	public Point[] getPoints() { return this.points; }
	public String getText() { return this.text; }

	public void setText(String text) { this.text = text; }
	public void setIsSeleted(boolean isSelected) { this.isSelected = isSelected; }
	
	public void setAttribute(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.points[0].setLocation(this.x , this.y + this.height/2);
		this.points[1].setLocation(this.x + this.width/2, this.y + this.height);
		this.points[2].setLocation(this.x + this.width, this.y + this.height/2);
		this.points[3].setLocation(this.x + this.width/2, this.y);
	}
	
	public void setLocation(int x, int y) {
		this.setAttribute(x, y, this.width, this.height);
	}
	
	
	public abstract void draw(Graphics graphics);
	
	protected void drawPoint(Graphics graphic) {
		if (!this.isSelected) return;
		for (Point point: this.points)
			point.draw(graphic);
	}
	
	public abstract boolean include(int x, int y);
	
	public Point closeTo(int x, int y) {
		boolean negativeSlope = this.width*(y-this.y) - this.height*(x-this.x) > 0;
		boolean positiveSlope = (-this.width)*(y-this.y) - this.height*(x-(this.x + this.width)) > 0;
		
		if (negativeSlope && positiveSlope) return this.points[0];
		else if (negativeSlope && !positiveSlope) return this.points[1];
		else if (!negativeSlope && !positiveSlope) return this.points[2];
		else if (!negativeSlope && positiveSlope) return this.points[3];
		
		return null;
	}
	
	public void unGroup(Canvas canvas) {}
}

