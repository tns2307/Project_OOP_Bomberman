package game;

public class RangeBooster extends Item{
	//assign image
	@Override
	void affect_player(Player P) {
		P.setRange(P.getRange()+1);
	}
}
