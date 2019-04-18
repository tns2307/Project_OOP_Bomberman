package game;

import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Tiles {
	protected ImageIcon icon;
	protected boolean is_accesible = true;
	protected int coorX;
	protected int coorY;
	protected boolean drawFire = false;
//	protected ImageIcon fireCenter = new ImageIcon("fix/flame_center.png");
//	protected ImageIcon fireBody = new ImageIcon("fix/flame_body.png");
//	protected ImageIcon fireEdge = new ImageIcon("fix/flame_edge.png");
	
//	protected void setImage(int x) {
//		if(x == 1) {
//			icon = fireCenter;
//		}else if(x == 2) {
//			icon = fireBody;
//		}else {
//			icon = fireEdge;
//		}
//	}
	
	protected Image getImage() {
		return icon.getImage();
	}
	
	protected ImageObserver getImageObserver() {
		return icon.getImageObserver();
	}
	
	
}
