package game;

public class BombCountBooster extends Item{
	//attribute import image
	
	void affect_player(Player p){
		p.setBomb_limit(p.getBomb_limit()+1);
	}
}
