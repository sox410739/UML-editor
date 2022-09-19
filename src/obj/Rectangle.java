package obj;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import main.Config;

public class Rectangle extends BasicObject {
	private static int number = 1;

	public Rectangle(int centerX, int centerY) {
		super(centerX - Config.OBJECT_WIDTH/2, centerY - Config.OBJECT_HEIGHT/2, Config.OBJECT_WIDTH, Config.OBJECT_HEIGHT);
		this.text = "Class " + Rectangle.number;
		Rectangle.number++;
	}

	@Override
	public void draw(Graphics graphics) {
		Color color = Color.LIGHT_GRAY;
		Color borderColor = Color.BLACK;
		BasicStroke borderStroke = new BasicStroke(2);
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 16);
		
		Graphics2D graphic2D = (Graphics2D) graphics;
		graphic2D.setColor(color);
		graphic2D.fillRect(this.x, this.y, this.width, this.height);
		graphic2D.setColor(borderColor);
		graphic2D.setStroke(borderStroke);
		graphic2D.drawRect(this.x, this.y, this.width, this.height);
		
		FontMetrics metrics = graphic2D.getFontMetrics(font);
	    graphic2D.setFont(font);
	    String[] lines = this.text.split("\n");
    	int y = this.y + this.height/2 - metrics.getHeight()*lines.length/2 + metrics.getAscent();;
	    for (String line : lines) {
	    	int x = this.x + this.width/2 - metrics.stringWidth(line) / 2;
	    	graphic2D.drawString(line, x, y);
	    	y += metrics.getHeight();
	    }
		
	    this.drawPoint(graphics);
	}

	@Override
	public boolean include(int x, int y) {
		boolean horizontal = x > this.x && (x - this.x) < this.width;
		boolean vertical = y > this.y && (y - this.y) < this.height;
		return horizontal && vertical;
	}
	
}
