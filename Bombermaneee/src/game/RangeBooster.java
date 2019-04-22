package game;

import java.awt.Image;

import javax.swing.ImageIcon;

public class RangeBooster extends Item{
	//assign image
	
	ImageIcon pu_fire = new ImageIcon("fix/pu_fire.png");
	
	@Override
	void affect_player(Player P) {
		P.setRange(P.getRange()+1);
	}
	
	public Image getImage() {
		return pu_fire.getImage();
	}
}
