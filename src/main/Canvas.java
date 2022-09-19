package main;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import line.Line;
import mode.ModeButton;
import mode.Selector;
import obj.BasicObject;

@SuppressWarnings("serial")
public class Canvas extends JPanel {
	public ModeButton currentMode;
	private ArrayList<BasicObject> basicObjects;
	private ArrayList<Line> lines;

	public Canvas() {
		this.basicObjects = new ArrayList<BasicObject>();
		this.lines = new ArrayList<Line>();
	}
	
	public void addObject(BasicObject basicObject) { this.basicObjects.add(basicObject); }
	public void removeObject(BasicObject basicObject) { this.basicObjects.remove(basicObject); }
	public ArrayList<BasicObject> getObjects() { return this.basicObjects; }
	public void addLine(Line line) { this.lines.add(line); }
	public void removeLine(Line line) { this.lines.remove(line); }
	
	public BasicObject getTopObject(int x, int y) {
		for (int index=this.basicObjects.size()-1; index>=0; index--) {
			if (this.basicObjects.get(index).include(x, y)) {
				return this.basicObjects.get(index);
			}
		}
		return null;
	}
	
	public void unSelectAll() {
		for (BasicObject basicObject: this.basicObjects) { 
			basicObject.setIsSeleted(false);
		}
		this.repaint();
	}
	
	@Override
	public void paint(Graphics graphic) {
		super.paintComponent(graphic);
		super.paintBorder(graphic);
		
		// draw unselected
		for (BasicObject basicObject: this.basicObjects) {
			if (!basicObject.getIsSelected()) basicObject.draw(graphic);
		}
		// draw selected
		for (BasicObject basicObject: this.basicObjects) {
			if (basicObject.getIsSelected()) basicObject.draw(graphic);
		}
		
		for (Line line: this.lines) {
			line.draw(graphic);
		}
		
		Selector selector = Selector.getSelector();
		if (selector != null) selector.draw(graphic);
		
		super.paintChildren(graphic);
	}
}
