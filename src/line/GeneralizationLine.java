package line;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import obj.Point;

public class GeneralizationLine extends Line {

	public GeneralizationLine(Point from, Point to) {
		super(from, to);
	}

	@Override
	public void draw(Graphics graphic) {
		Color color = Color.BLACK;
		BasicStroke stroke = new BasicStroke(3);
		double arrowAngle = 30;
		int arrowLength = 20;
		Color arrowColor = Color.WHITE;
		Graphics2D graphic2D = (Graphics2D) graphic;
		// 畫主線
		graphic2D.setColor(color);
		graphic2D.setStroke(stroke);
		graphic2D.drawLine(this.from.getCenterX(), this.from.getCenterY(), this.to.getCenterX(), this.to.getCenterY());
		
		// 畫箭頭
		double dx = this.to.getCenterX() - this.from.getCenterX();
		double dy = this.to.getCenterY() - this.from.getCenterY();
		double theta = Math.atan2(dy, dx);
		double rhoUp = theta + Math.toRadians(arrowAngle);
		double rhoDown = theta - Math.toRadians(arrowAngle);
		int xPoints[] = {
			this.to.getCenterX(),
			(int) (this.to.getCenterX() - arrowLength*Math.cos(rhoUp)),
			(int) (this.to.getCenterX() - arrowLength*Math.cos(rhoDown))
		};
		int yPoints[] = {
			this.to.getCenterY(),
			(int) (this.to.getCenterY() - arrowLength*Math.sin(rhoUp)),
			(int) (this.to.getCenterY() - arrowLength*Math.sin(rhoDown))
		};
		
		Polygon arrow = new Polygon(xPoints, yPoints, xPoints.length);
		graphic2D.setColor(arrowColor);
		graphic2D.fillPolygon(arrow);
		graphic2D.setColor(color);
		graphic2D.drawPolygon(arrow);
		
	}
	
}
