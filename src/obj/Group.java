package obj;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import main.Canvas;

public class Group extends BasicObject {
	private ArrayList<BasicObject> objects;

	public Group() {
		super(0, 0, 0, 0);
		this.objects = new ArrayList<BasicObject>();
	}
	
	public ArrayList<BasicObject> getObjects() { return this.objects; }
	
	public void addObject(BasicObject object) {
		this.objects.add(object);
		
		int top = Integer.MAX_VALUE;
		int left = Integer.MAX_VALUE;
		int down = Integer.MIN_VALUE;
		int right = Integer.MIN_VALUE;
		
		for (BasicObject basicObject: this.objects) {
			top = Math.min(basicObject.getY(), top);
			left = Math.min(basicObject.getX(), left);
			down = Math.max(basicObject.getY() + basicObject.getHeight(), down);
			right = Math.max(basicObject.getX() + basicObject.getWidth(), right);
		}
		this.setAttribute(left, top, right-left, down-top);
	}
	
	@Override
	public void setLocation(int x, int y) {
		// 所有物件一起移動
		for (BasicObject basicObject: this.objects) {
			basicObject.setLocation(basicObject.getX() + x - this.x, basicObject.getY() + y - this.y);
		}
		super.setLocation(x, y);
	}
	
	@Override
	public void setIsSeleted(boolean isSelected) {
		super.setIsSeleted(isSelected);
		// 所有物件一起被選取
		for (BasicObject basicObject: this.objects) {
			basicObject.setIsSeleted(isSelected);
		}
	}
	
	/** 其中一個物件有包含此座標就選取 */
	@Override
	public boolean include(int x, int y) {
		for (BasicObject basicObject: this.objects) {
			if (basicObject.include(x, y)) return true;
		}
		return false;
	}
	
	@Override
	public Point closeTo(int x, int y) {
		for (int index=this.objects.size()-1; index>=0; index--) {
			if (this.objects.get(index).include(x, y)) return this.objects.get(index).closeTo(x, y);
		}
		return null;
	}

	@Override
	public void draw(Graphics graphics) {
		Graphics2D graphic2D = (Graphics2D) graphics;
		// 畫所有物件
		for (BasicObject basicObject: this.objects) {
			basicObject.draw(graphics);
		}
		
		// 如果被選取時畫邊邊
		if (!this.isSelected) return;
		
		Color color = Color.BLACK;
		BasicStroke stroke = new BasicStroke(1.5f, BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 10.0f, new float[] { 2f, 0f, 2f }, 0.0f);
		graphic2D.setColor(color);
		graphic2D.setStroke(stroke);
		graphic2D.drawRect(this.x, this.y, this.width, this.height);
	}
	
	@Override
	public void unGroup(Canvas canvas) {
		canvas.removeObject(this);
		for (BasicObject object: this.objects) {
			canvas.addObject(object);
		}
	}
	
	@Override
	public String getText() { return ""; }

	@Override
	public void setText(String text) {}

}
