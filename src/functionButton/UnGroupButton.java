package functionButton;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import main.Canvas;
import mode.ModeButton;
import obj.BasicObject;

@SuppressWarnings("serial")
public class UnGroupButton extends JButton {
	private ModeButton modeButton;
	private Canvas canvas;
	
	public UnGroupButton(ModeButton modeButton, Canvas canvas) {
		this.modeButton = modeButton;
		this.canvas = canvas;
	}
	
	public void initial() {
		this.setText("UnGroup");
		this.setBackground(Color.GREEN);
		this.setLocation(10, 50);
		this.setSize(120, 30);
		this.setBorder(null);
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<BasicObject> selectedObjects = new ArrayList<BasicObject>();
				for (BasicObject basicObject: canvas.getObjects()) {
					if (basicObject.getIsSelected()) selectedObjects.add(basicObject);
				}
				
				if (selectedObjects.size() == 1) {
					BasicObject object = selectedObjects.get(0);
					object.unGroup(canvas);
					canvas.repaint();
				}
			}
		});
	}
	
	
	@Override
	public void paint(Graphics graphic) {
		boolean canUnGroup = this.canvas.currentMode != null && this.canvas.currentMode.equals(this.modeButton);
		
		if (canUnGroup) super.paint(graphic);
	}
}
