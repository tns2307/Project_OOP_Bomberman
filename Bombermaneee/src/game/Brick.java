package game;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Random;

import javax.swing.ImageIcon;

public class Brick{
	//Atribute Image buat brick 
	
//	ImageIcon brickWall = new ImageIcon("fix/brick.png");
	
	void destroyed(Grass location,boolean key_has_obtained) {
		if(new Random().nextInt(10)==9) {
			int x=4;
			if(key_has_obtained) x=3;
			location.assign_item(new Random().nextInt(x));
		}
		location.setBrick(null);
	}
	
//	public Image getImage() {
//		return brickWall.getImage();
//	}
//	
//	protected ImageObserver getImageObserver() {
//		return brickWall.getImageObserver();
//	}
}
