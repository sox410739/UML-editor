package main;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import functionButton.GroupButton;
import functionButton.RenameButton;
import functionButton.UnGroupButton;
import mode.AddAssociationLineListener;
import mode.AddCircleListener;
import mode.AddCompositionLineListener;
import mode.AddGeneralizationLineListener;
import mode.AddRectangleListener;
import mode.ModeButton;
import mode.SelectListener;

public class Main {

	public static void main(String[] args) throws IOException {
		UI ui = new UI();
		ui.setFrame(Config.Title, Config.SCREEN_X, Config.SCREEN_Y, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		initMode(ui);
		ui.run();
	}
	
	public static void initMode(UI ui) throws IOException {
		Image selectImage = ImageIO.read(new File(Config.IMAGE_PATH + "select.png"));
		Image regtangleImage = ImageIO.read(new File(Config.IMAGE_PATH + "regtangle.png"));
		Image circleImage = ImageIO.read(new File(Config.IMAGE_PATH + "circle.png"));
		Image associationImage = ImageIO.read(new File(Config.IMAGE_PATH + "association.png"));
		Image generalizationImage = ImageIO.read(new File(Config.IMAGE_PATH + "generalization.png"));
		Image compositionImage = ImageIO.read(new File(Config.IMAGE_PATH + "composition.png"));
		
		ModeButton select = new ModeButton(selectImage, ui.getCanvas(), new SelectListener(ui.getCanvas()));
		ModeButton regtangle = new ModeButton(regtangleImage, ui.getCanvas(), new AddRectangleListener(ui.getCanvas()));
		ModeButton circle = new ModeButton(circleImage, ui.getCanvas(), new AddCircleListener(ui.getCanvas()));
		ModeButton association = new ModeButton(associationImage, ui.getCanvas(), new AddAssociationLineListener(ui.getCanvas()));
		ModeButton generalization = new ModeButton(generalizationImage, ui.getCanvas(), new AddGeneralizationLineListener(ui.getCanvas()));
		ModeButton composition = new ModeButton(compositionImage, ui.getCanvas(), new AddCompositionLineListener(ui.getCanvas()));
		
		select.setUpClickEvent();
		regtangle.setUpClickEvent();
		circle.setUpClickEvent();
		association.setUpClickEvent();
		generalization.setUpClickEvent();
		composition.setUpClickEvent();
		
		ui.addMode(select);
		ui.addMode(regtangle);
		ui.addMode(circle);
		ui.addMode(association);
		ui.addMode(generalization);
		ui.addMode(composition);
		
		GroupButton group = new GroupButton(select, ui.getCanvas());
		group.initial();
		ui.addFunctionButton(group);
		
		UnGroupButton unGroup = new UnGroupButton(select, ui.getCanvas());
		unGroup.initial();
		ui.addFunctionButton(unGroup);
		
		RenameButton rename = new RenameButton(select, ui.getCanvas());
		rename.initial();
		ui.addFunctionButton(rename);
	}

}
