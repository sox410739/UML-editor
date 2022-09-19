package functionButton;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import main.Canvas;
import mode.ModeButton;
import obj.BasicObject;

@SuppressWarnings("serial")
public class RenameButton extends JButton {
	private ModeButton modeButton;
	private Canvas canvas;
	
	public RenameButton(ModeButton modeButton, Canvas canvas) {
		this.modeButton = modeButton;
		this.canvas = canvas;
	}
	
	
	public void initial() {
		this.setText("Rename");
		this.setBackground(Color.GREEN);
		this.setLocation(10, 90);
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
					if (object.getText().equals("")) return;
					
					JTextArea textArea = new JTextArea(5, 5);
					textArea.setText(object.getText());
					int option = JOptionPane.showConfirmDialog(canvas, new JScrollPane(textArea), "Change Name", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION && !textArea.getText().equals("")) {
						object.setText(textArea.getText());
					}
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
