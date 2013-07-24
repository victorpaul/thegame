package structures;

import game.Actor;

public final class Rock extends Actor{

	public Rock(float x, float y) {
		super(x, y);
		getCollision().addPoint(0,0,40,40);
	}

	public void update(int delta){

	}

}