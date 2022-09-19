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
	private Line line;				// ��e���u����
	private boolean isConnecting;	// �O�_���b�s�u
	private BasicObject fromObject;	// �s�u�ɪ��_�I����
	
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
			// �����Q�I��ťճB
			this.canvas.removeLine(this.line);
			this.line = null;
		} else {
			Point closeTo = top.closeTo(x, y);
			if (this.line.equalFrom(closeTo)) {
				// �_�I�M���I���P�@���I
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
			// �즲�ɷƹ��b�ťճB
			this.line.setTo(x, y);
		} else {
			// �즲�ɷƹ��b����W
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
