package line;

import java.awt.Graphics;

import obj.Point;

public abstract class Line {
	protected Point from;	// �_�I
	protected Point to;		// ���I
	
	public Line(Point from, Point to) {
		this.from = from;
		this.to = to;
	}
	
	public boolean equalFrom(Point point) { return this.from.equals(point); }
	public void setTo(int x, int y) {
		this.to.setLocation(x, y);
	}
	public void setTo(Point point) { 
		this.to = point;
	}
	
	/** �e�u */
	public abstract void draw(Graphics graphic);
}
