package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Player extends JFrame{
	private int health;
	private int bomb_limit;
	private int speed;
	private int range;
	public int coorX;
	public int coorY;
	public int temp_coorX;
	public int temp_coorY;
	public int locX;
	public int locY;
	public int temp_locX;
	public int temp_locY;

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getBomb_limit() {
		return bomb_limit;
	}

	public void setBomb_limit(int bomb_limit) {
		this.bomb_limit = bomb_limit;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}
	
}
