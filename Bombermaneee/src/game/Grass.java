package game;

public class Grass extends Tiles{
	private Player player;
	private Monster monster;
	private Item item;
	private Brick brick=null;
	private Bomb bomb=null; //TODO: saat player place_bomb, ubah is_accesible menjadi false; saat bomb meledak, ubah is_accessible menjadi true;
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Monster getMonster() {
		return monster;
	}

	public void setMonster(Monster monster) {
		this.monster = monster;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Brick getBrick() {
		return brick;
	}

	public void setBrick(Brick brick) {
		this.brick = brick;
	}

	public Bomb getBomb() {
		return bomb;
	}

	public void setBomb(Bomb bomb) {
		this.bomb = bomb;
	}


	Grass(){
		//import image
		is_accesible=true;
	}
	
	void assign_item(int i) {
		switch(i) {
			case 0:
				item=(Item)new HealthBooster();
				break;
			case 1:
				item=(Item)new RangeBooster();
				break;
			case 2:
				item=(Item)new BombCountBooster();
				break;
			
		}
	}
}
