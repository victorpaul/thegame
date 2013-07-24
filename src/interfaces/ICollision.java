package interfaces;

import game.Actor;
import physics.Collision;

public interface ICollision {

	public Collision getCollision();
	public void getCollision(Actor actor);
}