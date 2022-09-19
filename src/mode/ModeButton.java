package mode;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import main.Canvas;

@SuppressWarnings("serial")
public class ModeButton extends JButton {
	private Image image;
	private UMLListener listener;
	private Canvas canvas;
	
	public ModeButton(Image image, Canvas canvas, UMLListener listner) {
		this.image = image;
		this.canvas = canvas;
		this.listener = listner;
	}
	
	public UMLListener getUMLListener() { return this.listener; }
	
	@Override
	public void paintComponent(Graphics graphic) {
		graphic.drawImage(this.image, 0, 0, this.getBackground(), this);
	}
	
	public void setUpClickEvent() {
		ModeButton thisButton = this;
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UMLListener modeListener = thisButton.getUMLListener();
				Color pressed = new Color(53, 53, 53);
				Color unPressed = new Color(71, 71, 71);
				
				canvas.unSelectAll();
				if (canvas.currentMode != null) canvas.currentMode.setBackground(unPressed);
				for (MouseListener listener: canvas.getMouseListeners())
					canvas.removeMouseListener(listener);
				for (MouseMotionListener listener: canvas.getMouseMotionListeners())
					canvas.removeMouseMotionListener(listener);
				canvas.addMouseListener(modeListener);
				canvas.addMouseMotionListener(modeListener);
				modeListener.changeCursor();
				canvas.currentMode = thisButton;
				thisButton.setBackground(pressed);
			};
		});
	}
}
