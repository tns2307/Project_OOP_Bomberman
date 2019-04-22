package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

//TODO: RANDOM MONSTER
//TODO: BIKIN CONTROL CHARACTER
//TODO: RANDOM DOOR (Tambah attribut door_x, door_y untuk mengetahui lokasi door saat menginjak kunci)
//TODO: RANDOM KEY
public class Map extends JFrame {
	private int width = 1366;
	private int height = 768;
	private int tile_size = 30;
	private Vector<Vector<Tiles>> mapTiles = new Vector<Vector<Tiles>>();
	private Player player;
	private boolean drawLayout = true;
	private boolean drawMap = true;
	private boolean drawFire = false;
	private boolean drawBomb;
	private int horizontal_tiles_total = 19; // ukuran : 33
	private int vertical_tiles_total = 19; // ukuran : 19
	private boolean key_has_obtained = false;
	private int start_y = 60;
	private int start_x = 240;
	private int bomb_x, bomb_y;
	private int percentage_of_brick = 50;
	private int pxlX, pxlY, coorX, coorY; // TEMP ->> Will be deleted later

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getTile_size() {
		return tile_size;
	}

	public void setTile_size(int tile_size) {
		this.tile_size = tile_size;
	}

	public Vector<Vector<Tiles>> getMapTiles() {
		return mapTiles;
	}

	public void setMapTiles(Vector<Vector<Tiles>> mapTiles) {
		this.mapTiles = mapTiles;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public boolean isDrawLayout() {
		return drawLayout;
	}

	public void setDrawLayout(boolean drawLayout) {
		this.drawLayout = drawLayout;
	}

	public boolean isDrawMap() {
		return drawMap;
	}

	public void setDrawMap(boolean drawMap) {
		this.drawMap = drawMap;
	}

	public int getHorizontal_tiles_total() {
		return horizontal_tiles_total;
	}

	public void setHorizontal_tiles_total(int horizontal_tiles_total) {
		this.horizontal_tiles_total = horizontal_tiles_total;
	}

	public int getVertical_tiles_total() {
		return vertical_tiles_total;
	}

	public void setVertical_tiles_total(int vertical_tiles_total) {
		this.vertical_tiles_total = vertical_tiles_total;
	}

	public boolean isKey_has_obtained() {
		return key_has_obtained;
	}

	public void setKey_has_obtained(boolean key_has_obtained) {
		this.key_has_obtained = key_has_obtained;
	}

	public int getStart_y() {
		return start_y;
	}

	public void setStart_y(int start_y) {
		this.start_y = start_y;
	}

	public int getStart_x() {
		return start_x;
	}

	public void setStart_x(int start_x) {
		this.start_x = start_x;
	}

	public int getPercentage_of_brick() {
		return percentage_of_brick;
	}

	public void setPercentage_of_brick(int percentage_of_brick) {
		this.percentage_of_brick = percentage_of_brick;
	}

	public Thread getTempThread1() {
		return tempThread1;
	}

	public void setTempThread1(Thread tempThread1) {
		this.tempThread1 = tempThread1;
	}

	public int get_random() {
		if (key_has_obtained == false)
			return 4;
		else
			return 3;
	}

	private boolean drawPlayer = true;
	Thread tempThread1 = new Thread(new Runnable() {

		@Override

		public void run() {
			Scanner in = new Scanner(System.in);
			while (true) {
				System.out.println("Input X");
				int x = in.nextInt();
				System.out.println("Input Y");
				int y = in.nextInt();
				bomb_x = x;
				bomb_y = y;
				drawBomb = true;
				repaint();
				Tiles temp_tiles = mapTiles.get(y).get(x);
				if (temp_tiles instanceof SolidWall)
					continue;

				if ((((Grass) temp_tiles).getBrick() != null))
					System.out.println("Brick");
				else {
					((Grass) temp_tiles).setBomb(new Bomb(2));
					((Grass) temp_tiles).getBomb().explode((Grass) temp_tiles, mapTiles, key_has_obtained);
					System.out.println("Bomb Placed&exploded");
					drawMap = true;
					drawLayout = true;
					drawFire = true;
					repaint();
				}

				// repaint();
			}
		}
	});

	Map() {
		fill_tile();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(width, height);
		setVisible(true);
		tempThread1.start();
		move();
		// repainter.start();
	}

	Map(int i) {

	}

	void printed_decicer(Graphics g, int loc_x, int loc_y) {
		// if(i==0)
	}

//	Color temporary(Tiles tile) {
//		if (tile instanceof Grass) {
//			if (((Grass) tile).getBrick() == null) {
//				tile.is_accesible = true;
//				return Color.green;
//			} else {
//				tile.is_accesible = false;
//				return Color.red;
//			}
//		}
//		if (tile instanceof SolidWall) {
//			tile.is_accesible = false;
//			return Color.GRAY;
//		}
//		return Color.white;
//	}

	Image temp(Tiles tile) {
		return tile.getImage();
	}

	public void paint(Graphics g) {

		if (drawLayout == true) {
			g.clearRect(0, 0, 2000, 2000);
			g.drawRect(30, 140, 200, 400); // x start on 40
			g.drawRect(240, 60, 990, 570); // ukuran map x=1100, y = 600
			drawLayout = false;
		}
		if (drawMap == true) {
			System.out.println(mapTiles.size());
			drawMap = false;
			for (int i = 0, curr_y = start_y; i < mapTiles.size(); i++) {
				for (int j = 0, curr_x = start_x; j < mapTiles.get(0).size(); j++) {
					g.setColor(Color.BLACK);
					g.drawRect(curr_x, curr_y, tile_size, tile_size);
					g.drawImage(temp(mapTiles.get(i).get(j)), curr_x, curr_y, tile_size, tile_size,
							mapTiles.get(i).get(j).getImageObserver());
//					g.setColor(temporary(mapTiles.get(i).get(j))); //TODO: Ganti dengan drawImage!
//					g.fillRect(curr_x+1, curr_y+1, tile_size-1, tile_size-1);
					if (mapTiles.get(i).get(j).is_accesible == true) {
						if (((Grass) mapTiles.get(i).get(j)).getItem() instanceof Key)
							key_has_obtained = true;
					}
					curr_x += tile_size;

//					if(curr_x>1240) {
//						//System.out.println("hor: "+j);
//						break;
//					}

				}
				curr_y += tile_size;

//				if(curr_y>660) {
//					//System.out.println("ver: "+i);
//					break;
//				}
			}
		}
		if (drawBomb == true) {
			Bomb bomb = new Bomb(2);
//			g.setFont(new Font("Arial", Font.BOLD, 15));
//			g.drawString("B", start_x + bomb_x * tile_size + 10, start_y + bomb_y * tile_size + 10);
			g.drawImage(bomb.getImage(), start_x + bomb_x * tile_size, 
					start_y + bomb_y * tile_size, tile_size, tile_size,
					mapTiles.get(bomb_x).get(bomb_y).getImageObserver());
			drawBomb = false;
		}
		if (drawFire == true) {
			// g.fillRect(0, 0, 1000, 1000);
			for (int i = 0, curr_y = start_y; i < mapTiles.size(); i++) {
				for (int j = 0, curr_x = start_x; j < mapTiles.get(0).size(); j++) {
					if (mapTiles.get(i).get(j).drawFire == true) {
//						g.drawString("F", curr_x, curr_y); // +30
						g.drawImage(temp(mapTiles.get(i).get(j)), curr_x, curr_y, tile_size, tile_size, mapTiles.get(i).get(j).getImageObserver());
						mapTiles.get(i).get(j).drawFire = false;
					}
					curr_x += tile_size;
				}
				curr_y += tile_size;
			}
			drawFire = false;

			// drawMap=true;
			// repaint();
		}
		if (drawPlayer == true) {
			System.out.println(mapTiles.size());
			drawPlayer = false;
			for (int i = 0, curr_y = start_y; i < mapTiles.size(); i++) {
				for (int j = 0, curr_x = start_x; j < mapTiles.get(0).size(); j++) {
					if (i == 1 && j == 1)
						System.out.println(curr_x + " & " + curr_y);
//					g.setColor(Color.BLACK);
//					g.drawRect(curr_x, curr_y, tile_size, tile_size);
					g.drawImage(temp(mapTiles.get(i).get(j)), curr_x, curr_y, tile_size, tile_size,
							mapTiles.get(i).get(j).getImageObserver());
//					g.setColor(temporary(mapTiles.get(i).get(j))); //TODO: Ganti dengan drawImage!
//					g.fillRect(curr_x+1, curr_y+1, tile_size-1, tile_size-1);
					if (mapTiles.get(i).get(j).is_accesible == true) {
						if (((Grass) mapTiles.get(i).get(j)).getItem() instanceof Key)
							key_has_obtained = true;
					}
					curr_x += tile_size;

//					if(curr_x>1240) {
//						//System.out.println("hor: "+j);
//						break;
//					}

				}
				curr_y += tile_size;
			}
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.fillRect(pxlX, pxlY, tile_size, tile_size);
			System.out.println("x: " + coorX + " y: " + coorY);
			System.out.println("x: " + pxlX + " y: " + pxlY);
		}
	}

	public void fill_tile() {
		for (int i = 0; i < vertical_tiles_total; i++) {
			mapTiles.add(new Vector<Tiles>());
			for (int j = 0; j < horizontal_tiles_total; j++) {
				if (i == 0 || j == 0 || i == vertical_tiles_total - 1 || j == horizontal_tiles_total - 1
						|| (i % 2 == 0 && j % 2 == 0))
					mapTiles.get(mapTiles.size() - 1).add((Tiles) new SolidWall());
				else
					mapTiles.get(mapTiles.size() - 1).add((Tiles) new Grass());
				mapTiles.get(i).get(j).coorX = j;
				mapTiles.get(i).get(j).coorY = i;
			}
		}
		for (int i = 0; i < horizontal_tiles_total * vertical_tiles_total * percentage_of_brick / 100; i++) {
			int x;
			int y;
			while (true) {
				x = new Random().nextInt(horizontal_tiles_total);
				y = new Random().nextInt(vertical_tiles_total);
				if (!(mapTiles.get(y).get(x) instanceof Grass))
					continue;
				((Grass) mapTiles.get(y).get(x)).setBrick(new Brick());
				((Grass) mapTiles.get(y).get(x)).is_accesible = false;
				break;
			}
		}
		((Grass) mapTiles.get(1).get(1)).setBrick(null);
		((Grass) mapTiles.get(1).get(2)).setBrick(null);
		((Grass) mapTiles.get(2).get(1)).setBrick(null);
		((Grass) mapTiles.get(2).get(1)).is_accesible = true;
		((Grass) mapTiles.get(1).get(2)).is_accesible = true;
		((Grass) mapTiles.get(1).get(1)).is_accesible = true;
		coorX = 1;
		coorY = 1;
		pxlX = coorX * tile_size + start_x;
		pxlY = coorY * tile_size + start_y;
	}

	private void move() {

		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				System.out.println("asdasd");
				switch (key) {
				case KeyEvent.VK_RIGHT:
					if (mapTiles.get(coorY).get(coorX + 1) instanceof SolidWall && pxlX % 30 != 15)
						break;
					if (((Grass) mapTiles.get(coorY).get(coorX + 1)).getBrick() != null && pxlX % 30 != 15)
						break;
					if (pxlY % 30 == 15)
						break;
					pxlX += 15;
					System.out.println("*");
					if (pxlX % 30 == 15) {
						coorX = (pxlX - start_x) / 30 + 1;
					} else {
						coorX = (pxlX - start_x) / 30;
					}
					break;
				case KeyEvent.VK_LEFT:
					if (mapTiles.get(coorY).get(coorX - 1) instanceof SolidWall && pxlX % 30 != 15)
						break;
					if (((Grass) mapTiles.get(coorY).get(coorX - 1)).getBrick() != null && pxlX % 30 != 15)
						break;
					if (pxlY % 30 == 15)
						break;
					pxlX -= 15;
					if (pxlX % 30 == 0) {
						coorX = (pxlX - start_x) / 30;
					} else {
						coorX = (pxlX - start_x) / 30 + 1;
					}
					break;
				case KeyEvent.VK_UP:
					if (mapTiles.get(coorY - 1).get(coorX) instanceof SolidWall && pxlY % 30 != 15)
						break;
					if (((Grass) mapTiles.get(coorY - 1).get(coorX)).getBrick() != null && pxlY % 30 != 15)
						break;
					if (pxlX % 30 == 15)
						break;
					pxlY -= 15;
					if (pxlY % 30 == 0) {
						coorY = (pxlY - start_y) / 30;
					} else {
						coorY = (pxlY - start_y) / 30 + 1;
					}
					break;
				case KeyEvent.VK_DOWN:
					if (mapTiles.get(coorY + 1).get(coorX) instanceof SolidWall && pxlY % 30 != 15)
						break;
					if (((Grass) mapTiles.get(coorY + 1).get(coorX)).getBrick() != null && pxlY % 30 != 15)
						break;
					if (pxlX % 30 == 15)
						break;
					pxlY += 15;
					if (pxlY % 30 == 15) {
						coorY = (pxlY - start_y) / 30 + 1;
					}
					coorY = (pxlY - start_y) / 30;
					break;
				}
//				coorX=(pxlX-start_x)-coorX*tile_size>15?coorX+1:coorX;
//				coorY=(pxlY-start_y)-coorY*tile_size>15?coorY+1:coorY;
//				coorX=(pxlX-start_x)/30;
//				coorY=(pxlY-start_y)/30;
//				System.out.println(coorX+" + "+coorY+ "| startX "+start_x+ " + startY " + start_y);
				System.out.println("Diagnose Start: ");
				System.out.println(pxlX + " + " + pxlY);
				System.out.println("Diagnose End");
//				for(int i=0;i<19;i++) {
//					for(int j=0;j<19;j++) {
//						System.out.print("x: "+j+" y: "+i+" || ");
//						if(mapTiles.get(i).get(j) instanceof SolidWall) System.out.println("Solidwall");
//						else if (mapTiles.get(i).get(j) instanceof Grass) {
//							if(((Grass)mapTiles.get(i).get(j)).getBrick()==null) System.out.println("Grass");
//							else System.out.println("Brick");
//						}
//					}
//				}
				drawPlayer = true;
				repaint();
			}
		});
	}

	public static void main(String args[]) {
		// new Map(1);
		new Map();

	}
}
