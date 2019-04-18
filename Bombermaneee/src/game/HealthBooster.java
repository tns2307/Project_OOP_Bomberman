package game;

import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class HealthBooster extends Item{
	//assign image
	
	ImageIcon pu_speed = new ImageIcon("fix/pu_speed.png");
	
	@Override
	void affect_player(Player P) {
		P.setHealth(P.getHealth()+1);
	}

	public Image getImage() {
		return pu_speed.getImage();
	}
	
	protected ImageObserver getImageObserver() {
		return pu_speed.getImageObserver();
	}
	
}
