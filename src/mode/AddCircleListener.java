package mode;

import java.awt.Cursor;
import java.awt.event.MouseEvent;

import main.Canvas;
import obj.Circle;

public class AddCircleListener extends UMLListener {

	public AddCircleListener(Canvas canvas) {
		super(canvas);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		Circle circle = new Circle(x, y);
		this.canvas.addObject(circle);
		this.canvas.repaint();
	}

	@Override
	public void changeCursor() {
		this.canvas.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
	}

}
