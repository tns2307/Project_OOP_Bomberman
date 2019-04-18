package game;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.ImageObserver;
import java.util.Vector;

import javax.swing.ImageIcon;

public class Bomb {
	private int timer = 1500;
	private int range;
	// int up,left,right,down;
	private ImageIcon bomb = new ImageIcon("fix/bom.png");
	private ImageIcon fireCenter = new ImageIcon("fix/flame_center.png");
	private ImageIcon fireBody = new ImageIcon("fix/flame_body.png");
	private ImageIcon fireEdge = new ImageIcon("fix/flame_edge.png");

	Bomb(int assignedRange) {
		this.range = assignedRange;
		// insert image
	}

	void explode(Tiles location, Vector<Vector<Tiles>> mapTiles, boolean key_has_obtained) {
		try {
			Thread.sleep(timer);
		} catch (InterruptedException e) {
			// e.printStackTrace();
		}

		Thread moveLeft = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i < range; i++) {
					// left=i;
					boolean broke = false;
					Vector<Vector<Tiles>> tempTiles = mapTiles;
					Tiles CheckedTiles = tempTiles.get(location.coorY).get(location.coorX - i); // Mungkin kesalahan
					CheckedTiles.drawFire = true;
//					AffineTransform rotate = AffineTransform.getRotateInstance(180);
//					AffineTransformOp op = new AffineTransformOp(rotate, AffineTransformOp.TYPE_BILINEAR);
//					location.drawImage(op.filter(fireBody, null), location.coorX, location.coorY, null);
					if (CheckedTiles instanceof SolidWall)
						break;
					if (CheckedTiles instanceof Grass) {
						if (((Grass) CheckedTiles).getPlayer() != null) {
							Player temp_player = ((Grass) CheckedTiles).getPlayer();
							temp_player.setHealth(temp_player.getHealth() - 1);
							((Grass) CheckedTiles).setPlayer(temp_player);
						} else if (((Grass) CheckedTiles).getItem() != null) {
							((Grass) CheckedTiles).setItem(null);
							broke = true;
						} else if (((Grass) CheckedTiles).getBrick() != null) {
							((Grass) CheckedTiles).getBrick().destroyed(((Grass) CheckedTiles), key_has_obtained);
							broke = true;
						} else if (((Grass) CheckedTiles).getMonster() != null) {
							((Grass) CheckedTiles).getMonster()
									.setHealth(((Grass) CheckedTiles).getMonster().getHealth() - 1);
							broke = true;
						}
					}
					if (broke == true)
						break;
				}
				System.out.println("a");
			}
		});
		Thread moveRight = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i < range; i++) {
					// right=i;
					boolean broke = false;
					Vector<Vector<Tiles>> tempTiles = mapTiles;
					Tiles CheckedTiles = tempTiles.get(location.coorY).get(location.coorX + i);
					CheckedTiles.drawFire = true;
					if (CheckedTiles instanceof SolidWall)
						break;
					if (CheckedTiles instanceof Grass) {
						if (((Grass) CheckedTiles).getPlayer() != null) {
							Player temp_player = ((Grass) CheckedTiles).getPlayer();
							temp_player.setHealth(temp_player.getHealth() - 1);
							((Grass) CheckedTiles).setPlayer(temp_player);
						} else if (((Grass) CheckedTiles).getItem() != null) {
							((Grass) CheckedTiles).setItem(null);
							broke = true;
						} else if (((Grass) CheckedTiles).getBrick() != null) {
							((Grass) CheckedTiles).getBrick().destroyed(((Grass) CheckedTiles), key_has_obtained);
							broke = true;
						} else if (((Grass) CheckedTiles).getMonster() != null) {
							((Grass) CheckedTiles).getMonster()
									.setHealth(((Grass) CheckedTiles).getMonster().getHealth() - 1);
							broke = true;
						}
					}
					if (broke == true)
						break;
					else
						CheckedTiles.drawFire = true;
				}
				System.out.println("b");
			}
		});
		Thread moveUp = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i < range; i++) {
					// up=i;
					boolean broke = false;
					Vector<Vector<Tiles>> tempTiles = mapTiles;
					Tiles CheckedTiles = tempTiles.get(location.coorY - i).get(location.coorX);
					CheckedTiles.drawFire = true;
//					AffineTransform rotate = AffineTransform.getRotateInstance(270);
					if (CheckedTiles instanceof SolidWall)
						break;
					if (CheckedTiles instanceof Grass) {
						if (((Grass) CheckedTiles).getPlayer() != null) {
							Player temp_player = ((Grass) CheckedTiles).getPlayer();
							temp_player.setHealth(temp_player.getHealth() - 1);
							((Grass) CheckedTiles).setPlayer(temp_player);
						} else if (((Grass) CheckedTiles).getItem() != null) {
							((Grass) CheckedTiles).setItem(null);
							broke = true;
						} else if (((Grass) CheckedTiles).getBrick() != null) {
							((Grass) CheckedTiles).getBrick().destroyed(((Grass) CheckedTiles), key_has_obtained);
							broke = true;
						} else if (((Grass) CheckedTiles).getMonster() != null) {
							((Grass) CheckedTiles).getMonster()
									.setHealth(((Grass) CheckedTiles).getMonster().getHealth() - 1);
							broke = true;
						}
					}
					if (broke == true)
						break;
					else
						CheckedTiles.drawFire = true;
				}
				System.out.println("c");
			}

		});
		Thread moveDown = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i < range; i++) {
					boolean broke = false;
					// down=i;
					Vector<Vector<Tiles>> tempTiles = mapTiles;
					Tiles CheckedTiles = tempTiles.get(location.coorY + i).get(location.coorX);
					CheckedTiles.drawFire = true;
//					AffineTransform rotate = AffineTransform.getRotateInstance(90);
					if (CheckedTiles instanceof SolidWall)
						break;
					if (CheckedTiles instanceof Grass) {
						if (((Grass) CheckedTiles).getPlayer() != null) {
							Player temp_player = ((Grass) CheckedTiles).getPlayer();
							temp_player.setHealth(temp_player.getHealth() - 1);
							((Grass) CheckedTiles).setPlayer(temp_player);
						} else if (((Grass) CheckedTiles).getItem() != null) {
							((Grass) CheckedTiles).setItem(null);
							broke = true;
						} else if (((Grass) CheckedTiles).getBrick() != null) {
							((Grass) CheckedTiles).getBrick().destroyed(((Grass) CheckedTiles), key_has_obtained);
							broke = true;
						} else if (((Grass) CheckedTiles).getMonster() != null) {
							((Grass) CheckedTiles).getMonster()
									.setHealth(((Grass) CheckedTiles).getMonster().getHealth() - 1);
							broke = true;
						}
					}
					if (broke == true)
						break;
					else
						CheckedTiles.drawFire = true;
				}
				System.out.println("d");
			}
		});

		moveLeft.start();
		moveRight.start();
		moveUp.start();
		moveDown.start();

		// return false;
		// m.repaint();
	}
	
	public Image getImage() {
		return bomb.getImage();
	}
	
	protected ImageObserver getImageObserver() {
		return bomb.getImageObserver();
	}

}
