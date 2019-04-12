package game;

public class HealthBooster extends Item{
	//assign image
	
	@Override
	void affect_player(Player P) {
		P.setHealth(P.getHealth()+1);
	}
}
