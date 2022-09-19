package functionButton;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.Canvas;
import mode.ModeButton;
import obj.BasicObject;
import obj.Group;

@SuppressWarnings("serial")
public class GroupButton extends JButton {
	private ModeButton modeButton;
	private Canvas canvas;
	
	public GroupButton(ModeButton modeButton, Canvas canvas) {
		this.modeButton = modeButton;
		this.canvas = canvas;
	}
	
	public void initial() {
		this.setText("Group");
		this.setBackground(Color.GREEN);
		this.setLocation(10, 10);
		this.setSize(120, 30);
		this.setBorder(null);
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedObjectsCount = 0;
				for (BasicObject basicObject: canvas.getObjects()) {
					if (basicObject.getIsSelected()) selectedObjectsCount++;
				}
				
				if (selectedObjectsCount > 1) {
					Group group = new Group();
					for (BasicObject basicObject: canvas.getObjects()) {
						if (basicObject.getIsSelected()) group.addObject(basicObject);
					}
					for (BasicObject basicObject: group.getObjects()) {
						canvas.removeObject(basicObject);
					}
					group.setIsSeleted(true);
					canvas.addObject(group);
					canvas.repaint();					
				}
			}
		});
	}
	
	@Override
	public void paint(Graphics graphic) {
		boolean canGroup = this.canvas.currentMode != null && this.canvas.currentMode.equals(this.modeButton);
		
		if (canGroup) super.paint(graphic);
	}
	
	
}
