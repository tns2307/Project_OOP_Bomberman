package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Map extends JFrame{
	private int width=1366;
	private int height=768;
	private int tile_size=30;
	private Vector<Vector<Tiles>> mapTiles = new Vector<Vector<Tiles>>(); 
	private Player player;
	private boolean drawLayout=true;
	private boolean drawMap=true;
	private boolean drawFire=false; 
	private int horizontal_tiles_total = 19; // ukuran : 33
	private int vertical_tiles_total = 19; // ukuran : 19
	private boolean key_has_obtained = false;
	private int start_y=60;
	private int start_x=240;
	private int percentage_of_brick=15;
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
	Thread tempThread1 = new Thread(new Runnable() {
		
		@Override
		
		public void run() {
			Scanner in = new Scanner(System.in);
			while(true) {
				System.out.println("Input X");
				int x = in.nextInt();
				System.out.println("Input Y");
				int y = in.nextInt();
				Tiles temp_tiles=mapTiles.get(y).get(x);
				if(temp_tiles instanceof SolidWall) continue;
				if((((Grass)temp_tiles).getBrick()!=null)) System.out.println("Brick");
				else {
					((Grass)temp_tiles).setBomb(new Bomb(5));
					((Grass)temp_tiles).getBomb().explode((Grass)temp_tiles,mapTiles);
					System.out.println("Bomb Placed&exploded");
					drawMap=true;
					drawLayout=true;
					drawFire=true;
					repaint();
				}
				
				//repaint();
			}
		}
	});
	
	Map(){
		fill_tile();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(width,height);
		setVisible(true);
		tempThread1.start();
		//repainter.start();
	}
	Map(int i){
		
	}
	
	void printed_decicer(Graphics g,int loc_x,int loc_y) {
		//if(i==0) 
	}
	
	Color temporary(Tiles tile) {
		if(tile instanceof Grass) {
			if (((Grass)tile).getBrick()==null) return Color.green;
			else return Color.red;
		}
		if(tile instanceof SolidWall) return Color.GRAY;
		return Color.white;
	}
	
	public void paint(Graphics g) {
		
		if(drawLayout==true) {
			g.clearRect(0, 0, 2000, 2000);
			g.drawRect(30, 140, 200, 400); //x start on 40 
			g.drawRect(240, 60, 990, 570); //ukuran map x=1100, y = 600
			drawLayout=false;
		}
		if(drawMap==true) {
			System.out.println(mapTiles.size());
			drawMap=false;
			for(int i=0,curr_y=start_y;i<mapTiles.size();i++) {
				for(int j=0,curr_x=start_x;j<mapTiles.get(0).size();j++) {
					g.setColor(Color.BLACK);
					g.drawRect(curr_x, curr_y, tile_size, tile_size);
					g.setColor(temporary(mapTiles.get(i).get(j))); //TODO: Ganti dengan drawImage!
					g.fillRect(curr_x+1, curr_y+1, tile_size-1, tile_size-1);
					
					curr_x+=tile_size;
					
//					if(curr_x>1240) {
//						//System.out.println("hor: "+j);
//						break;
//					}
					
				}
				curr_y+=tile_size;
				
//				if(curr_y>660) {
//					//System.out.println("ver: "+i);
//					break;
//				}
			}
		}
		if(drawFire==true) {
			//g.fillRect(0, 0, 1000, 1000);
			for(int i=0,curr_y=start_y;i<mapTiles.size();i++) {
				for(int j=0,curr_x=start_x;j<mapTiles.get(0).size();j++) {
					if(mapTiles.get(i).get(j).drawFire==true) {
						g.drawString("F", curr_x+10, curr_y+10);
						mapTiles.get(i).get(j).drawFire=false;
					}
					curr_x+=tile_size;	
				}
				curr_y+=tile_size;
			}
			drawFire=false;
			
				//drawMap=true;
				//repaint();
		}
	}
	
	public void fill_tile() {
		for(int i=0;i<vertical_tiles_total;i++) {
			mapTiles.add(new Vector<Tiles>());
			for(int j=0;j<horizontal_tiles_total;j++) {
				if (i==0||j==0||i==vertical_tiles_total-1||j==horizontal_tiles_total-1||(i%2==0&&j%2==0)) mapTiles.get(mapTiles.size()-1).add((Tiles)new SolidWall());
				else mapTiles.get(mapTiles.size()-1).add((Tiles)new Grass());
				mapTiles.get(i).get(j).coorX=j;
				mapTiles.get(i).get(j).coorY=i;
			}
		}
		for(int i=0;i<horizontal_tiles_total*vertical_tiles_total*percentage_of_brick/100;i++) {
			int x;
			int y;
			while(true) {
				x=new Random().nextInt(horizontal_tiles_total);
				y=new Random().nextInt(vertical_tiles_total);
				if(!(mapTiles.get(y).get(x) instanceof Grass)) continue;
				((Grass)mapTiles.get(y).get(x)).setBrick(new Brick());
				break;
			}
		}
	}
	public static void main(String args[]) {
		//new Map(1);
		new Map();
		
	}
}
