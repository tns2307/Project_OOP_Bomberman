package game;

import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class SolidWall extends Tiles{
	
	ImageIcon solidWall = new ImageIcon("fix/solidwall.jpg");
	
	public SolidWall() {
		//import image;
		is_accesible=false;
	}
	
	public Image getImage() {
		return solidWall.getImage();
	}
	
	protected ImageObserver getImageObserver() {
		return solidWall.getImageObserver();
	}
	
}
