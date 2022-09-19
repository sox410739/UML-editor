package mode;

import java.awt.Cursor;
import java.awt.event.MouseEvent;

import line.AssociationLine;
import line.GeneralizationLine;
import line.Line;
import main.Canvas;
import obj.BasicObject;
import obj.Point;

public class AddGeneralizationLineListener extends UMLListener {
	private Line line;				// 當前的線物件
	private boolean isConnecting;	// 是否正在連線
	private BasicObject fromObject;	// 連線時的起點物件
	
	public AddGeneralizationLineListener(Canvas canvas) {
		super(canvas);
		this.line = null;
		this.isConnecting = false;
		this.fromObject = null;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		BasicObject top = this.canvas.getTopObject(x, y);
		this.canvas.unSelectAll();
		
		if (top == null) {
			this.isConnecting = false;
			this.canvas.repaint();
			return;
		}
		
		this.fromObject = top;
		this.fromObject.setIsSeleted(true);
		this.isConnecting = true;
		this.line = new GeneralizationLine(this.fromObject.closeTo(x, y), new Point(x, y));
		this.canvas.addLine(this.line);
		this.canvas.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (!this.isConnecting) return;
		int x = e.getX();
		int y = e.getY();
		BasicObject top = this.canvas.getTopObject(x, y);
		
		if (top == null) {
			// 結束十點到空白處
			this.canvas.removeLine(this.line);
			this.line = null;
		} else {
			Point closeTo = top.closeTo(x, y);
			if (this.line.equalFrom(closeTo)) {
				// 起點和終點為同一個點
				this.canvas.removeLine(this.line);
				this.line = null;
			} else {
				this.line.setTo(closeTo);	
			}
		}
		
		this.isConnecting = false;
		this.canvas.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (!this.isConnecting) return;
		int x = e.getX();
		int y = e.getY();
		BasicObject top = this.canvas.getTopObject(x, y);
		
		this.canvas.unSelectAll();
		if (top == null) {
			// 拖曳時滑鼠在空白處
			this.line.setTo(x, y);
		} else {
			// 拖曳時滑鼠在物件上
			Point closeTo = top.closeTo(x, y);
			this.line.setTo(closeTo.getCenterX(), closeTo.getCenterY());
			top.setIsSeleted(true);
		}
		this.fromObject.setIsSeleted(true);
		this.canvas.repaint();
	}

	@Override
	public void changeCursor() {
		this.canvas.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
	}

}
