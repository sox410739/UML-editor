package mode;

import java.awt.Cursor;
import java.awt.event.MouseEvent;

import main.Canvas;
import obj.BasicObject;

public class SelectListener extends UMLListener {
	private int pressedX;
	private int pressedY;
	private boolean rangeSelecting;
	private BasicObject draggedTarget;
	private int distanceFromX;
	private int distanceFromY;

	public SelectListener(Canvas canvas) {
		super(canvas);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.pressedX = e.getX();
		this.pressedY = e.getY();
		
		this.draggedTarget = this.canvas.getTopObject(pressedX, pressedY);
		if (this.draggedTarget != null) this.rangeSelecting = false;
		else this.rangeSelecting = true;
		
		
		if (this.rangeSelecting) {
			Selector.createSelector();					
		} else {
			this.distanceFromX = this.pressedX - this.draggedTarget.getX();
			this.distanceFromY = this.pressedY - this.draggedTarget.getY();
			this.canvas.unSelectAll();
			this.draggedTarget.setIsSeleted(true);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int releaseX = e.getX();
		int releaseY = e.getY();
		boolean isSamePoint = this.pressedX == releaseX && this.pressedY == releaseY;
		
		this.canvas.unSelectAll();
		if (isSamePoint) {
			BasicObject top = this.canvas.getTopObject(releaseX, releaseY);
			if (top != null) top.setIsSeleted(true);
			this.canvas.repaint();
		} else {
			if (this.rangeSelecting) {
				Selector selector = Selector.getSelector();
				for (BasicObject basicObject: this.canvas.getObjects()) 
					if (selector.include(basicObject)) basicObject.setIsSeleted(true);
				Selector.removeSelector();
				this.canvas.repaint();				
			} else {
				this.draggedTarget.setIsSeleted(true);
				this.canvas.repaint();
				this.draggedTarget = null;
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		if (this.rangeSelecting) {
			int minX = Math.min(x, this.pressedX);
			int minY = Math.min(y, this.pressedY);
			int width = Math.abs(this.pressedX - x);
			int height = Math.abs(this.pressedY - y);
			
			Selector selector = Selector.getSelector();
			selector.setAttribute(minX, minY, width, height);
			this.canvas.repaint();			
		} else {
			this.draggedTarget.setLocation(x - this.distanceFromX, y - this.distanceFromY);
			this.canvas.repaint();
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		BasicObject top = this.canvas.getTopObject(x, y);
		
		if (top != null) this.canvas.setCursor(new Cursor(Cursor.HAND_CURSOR));
		else this.canvas.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	@Override
	public void changeCursor() {
		this.canvas.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

}
