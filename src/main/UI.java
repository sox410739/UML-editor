package main;
import java.awt.Color;
import java.awt.Cursor;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import mode.ModeButton;

public class UI {
	private JFrame frame;
	private JPanel aside;
	private Canvas canvas;
	private ArrayList<ModeButton> modeButtons;
	private ArrayList<JButton> functionButtons;
	
	public UI() {
		this.frame = new JFrame();
		this.aside = new JPanel();
		this.canvas = new Canvas();
		this.modeButtons = new ArrayList<ModeButton>();
		this.functionButtons = new ArrayList<JButton>();
	}
	
	public Canvas getCanvas() { return this.canvas; }
	
	public void setFrame(String title, int x, int y, int width, int height) {
		this.frame.setTitle(title);
		this.frame.setLocation(x, y);
		this.frame.setSize(width, height);
		this.frame.setLayout(null);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setResizable(false);
		
		int asideWidth = (int)(width*0.11f);
		this.aside.setLocation(0, 0);
		this.aside.setSize(asideWidth, height);
		this.aside.setBackground(new Color(71, 71, 71));
		this.aside.setLayout(null);
		
		int canvasWidth = width - asideWidth;
		this.canvas.setLocation(asideWidth, 0);
		this.canvas.setSize(canvasWidth, height);
		this.canvas.setBackground(Color.WHITE);
		this.canvas.setLayout(null);
		
		this.frame.add(aside);
		this.frame.add(this.canvas);
	}
	
	public void run() {
		this.frame.setVisible(true);
	}
	
	public void addMode(ModeButton modeButton) {
		this.modeButtons.add(modeButton);
		modeButton.setOpaque(false);
		modeButton.setBackground(this.aside.getBackground());
		modeButton.setBorder(null);
		modeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.aside.add(modeButton);
		
		
		int modeCount = this.modeButtons.size();
		int buttonMargin = 6;
		int buttonWidth = this.aside.getWidth() - 2*buttonMargin;
		int buttonHeight = this.aside.getHeight()/ modeCount - 2*buttonMargin;
		
		for (ModeButton button: this.modeButtons) {
			int index = this.modeButtons.indexOf(button);
			button.setLocation(buttonMargin, index*(buttonHeight+buttonMargin)+buttonMargin);
			button.setSize(buttonWidth, buttonHeight);
		}
	}
	
	public void addFunctionButton(JButton button) {
		this.functionButtons.add(button);
		this.canvas.add(button);
	}
}
