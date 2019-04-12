package game;

import java.util.Random;

public class Brick {
	//Atribute Image buat brick 
	
	void destroyed(Grass location) {
		if(new Random().nextInt(10)==9) {
			location.assign_item(new Random().nextInt(3));
		}
		location.setBrick(null);
	}
	
}
