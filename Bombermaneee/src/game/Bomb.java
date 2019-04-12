package game;

import java.util.Vector;

public class Bomb{
	private int timer = 1000;
	private int range;
	//int up,left,right,down;
	Bomb(int assignedRange){
		this.range=assignedRange;
	}
	void explode(Tiles location,Vector<Vector<Tiles>>mapTiles) {
		try {
			Thread.sleep(timer);
		} catch (InterruptedException e) {
			//e.printStackTrace();
		}
		
		Thread moveLeft = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=1;i<range;i++) {
					//left=i;
					boolean broke = false;
					Vector<Vector<Tiles>>tempTiles =mapTiles;
					Tiles CheckedTiles = tempTiles.get(location.coorY).get(location.coorX-i); //Mungkin kesalahan
					CheckedTiles.drawFire=true;
					if(CheckedTiles instanceof SolidWall) break;
					if(CheckedTiles instanceof Grass) {
						if(((Grass)CheckedTiles).getPlayer()!=null) {
							Player temp_player = ((Grass)CheckedTiles).getPlayer();
							temp_player.setHealth(temp_player.getHealth()-1);
							((Grass)CheckedTiles).setPlayer(temp_player);
						}
						else if (((Grass)CheckedTiles).getItem()!=null) {
							((Grass)CheckedTiles).setItem(null);
							broke=true;
						}
						else if (((Grass)CheckedTiles).getBrick()!=null) {
							((Grass)CheckedTiles).getBrick().destroyed(((Grass)CheckedTiles));
							broke=true;
						}
						else if (((Grass)CheckedTiles).getMonster()!=null) {
							((Grass)CheckedTiles).getMonster().setHealth(((Grass)CheckedTiles).getMonster().getHealth()-1);
							broke=true;
						}
					}
					if(broke==true) break;
				}
				System.out.println("a");
			}
		});
		Thread moveRight = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=1;i<range;i++) {
					//right=i;
					boolean broke = false;
					Vector<Vector<Tiles>>tempTiles =mapTiles;
					Tiles CheckedTiles = tempTiles.get(location.coorY).get(location.coorX+i);
					CheckedTiles.drawFire=true;
					if(CheckedTiles instanceof SolidWall) break;
					if(CheckedTiles instanceof Grass) {
						if(((Grass)CheckedTiles).getPlayer()!=null) {
							Player temp_player = ((Grass)CheckedTiles).getPlayer();
							temp_player.setHealth(temp_player.getHealth()-1);
							((Grass)CheckedTiles).setPlayer(temp_player);
						}
						else if (((Grass)CheckedTiles).getItem()!=null) {
							((Grass)CheckedTiles).setItem(null);
							broke=true;
						}
						else if (((Grass)CheckedTiles).getBrick()!=null) {
							((Grass)CheckedTiles).getBrick().destroyed(((Grass)CheckedTiles));
							broke=true;
						}
						else if (((Grass)CheckedTiles).getMonster()!=null) {
							((Grass)CheckedTiles).getMonster().setHealth(((Grass)CheckedTiles).getMonster().getHealth()-1);
							broke=true;
						}
					}
					if(broke==true) break;
					else CheckedTiles.drawFire=true;
				}	
				System.out.println("b");
			}
		});
		Thread moveUp = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=1;i<range;i++) {
					//up=i;
					boolean broke = false;
					Vector<Vector<Tiles>>tempTiles =mapTiles;
					Tiles CheckedTiles = tempTiles.get(location.coorY-i).get(location.coorX);
					CheckedTiles.drawFire=true;
					if(CheckedTiles instanceof SolidWall) break;
					if(CheckedTiles instanceof Grass) {
						if(((Grass)CheckedTiles).getPlayer()!=null) {
							Player temp_player = ((Grass)CheckedTiles).getPlayer();
							temp_player.setHealth(temp_player.getHealth()-1);
							((Grass)CheckedTiles).setPlayer(temp_player);
						}
						else if (((Grass)CheckedTiles).getItem()!=null) {
							((Grass)CheckedTiles).setItem(null);
							broke=true;
						}
						else if (((Grass)CheckedTiles).getBrick()!=null) {
							((Grass)CheckedTiles).getBrick().destroyed(((Grass)CheckedTiles));
							broke=true;
						}
						else if (((Grass)CheckedTiles).getMonster()!=null) {
							((Grass)CheckedTiles).getMonster().setHealth(((Grass)CheckedTiles).getMonster().getHealth()-1);
							broke=true;
						}
					}
					if(broke==true) break;
					else CheckedTiles.drawFire=true;
				}	
				System.out.println("c");
			}
			
		});
		Thread moveDown = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=1;i<range;i++) {
					boolean broke = false;
					//down=i;
					Vector<Vector<Tiles>>tempTiles =mapTiles;
					Tiles CheckedTiles = tempTiles.get(location.coorY+i).get(location.coorX);
					CheckedTiles.drawFire=true;
					if(CheckedTiles instanceof SolidWall) break;
					if(CheckedTiles instanceof Grass) {
						if(((Grass)CheckedTiles).getPlayer()!=null) {
							Player temp_player = ((Grass)CheckedTiles).getPlayer();
							temp_player.setHealth(temp_player.getHealth()-1);
							((Grass)CheckedTiles).setPlayer(temp_player);
						}
						else if (((Grass)CheckedTiles).getItem()!=null) {
							((Grass)CheckedTiles).setItem(null);
							broke=true;
						}
						else if (((Grass)CheckedTiles).getBrick()!=null) {
							((Grass)CheckedTiles).getBrick().destroyed(((Grass)CheckedTiles));
							broke=true;
						}
						else if (((Grass)CheckedTiles).getMonster()!=null) {
							((Grass)CheckedTiles).getMonster().setHealth(((Grass)CheckedTiles).getMonster().getHealth()-1);
							broke=true;
						}
					}
					if(broke==true) break;
					else CheckedTiles.drawFire=true;
				}	
				System.out.println("d");
			}
		});
		
		moveLeft.start();
		moveRight.start();
		moveUp.start();
		moveDown.start();
		
		//m.repaint();
	}
	
}
