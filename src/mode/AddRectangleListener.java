package mode;

import java.awt.Cursor;
import java.awt.event.MouseEvent;

import main.Canvas;
import obj.Rectangle;

public class AddRectangleListener extends UMLListener {
	public AddRectangleListener(Canvas canvas) {
		super(canvas);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		Rectangle rectangle = new Rectangle(x, y);
		this.canvas.addObject(rectangle);
		this.canvas.repaint();
	}

	@Override
	public void changeCursor() {
		this.canvas.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
	}
}
