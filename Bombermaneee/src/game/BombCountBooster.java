package game;

import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class BombCountBooster extends Item{
	//attribute import image
	
	ImageIcon pu_bomb = new ImageIcon("fix/pu_bomb.png");
	
	void affect_player(Player p){
		p.setBomb_limit(p.getBomb_limit()+1);
	}

	public Image getImage() {
		return pu_bomb.getImage();
	}
	
	protected ImageObserver getImageObserver() {
		return pu_bomb.getImageObserver();
	}
	
}
