package physics;

import game.Actor;

import java.util.ArrayList;
import java.util.List;

public class Collision {
	public final static byte COLLISION_NONE = 0;
	public final static byte COLLISION_LEFT = 1;
	public final static byte COLLISION_RIGHT = 2;
	public final static byte COLLISION_TOP = 3;
	public final static byte COLLISION_DOWN = 4;
	public final static byte COLLISION_CIRCLE = 5;
	
	private boolean collisionLeft = false;
	private boolean collisionRight = false;
	private boolean collisionTop = false;
	private boolean collisionBottom = false;
	
	Actor actor = null;

	private List<CollisionPoint> points = new ArrayList<CollisionPoint>();

	public Collision(Actor actor){
		this.actor = actor;
	}

	public void addPoint(float x,float y,float width,float height){
		points.add(new CollisionPoint(x, y, width, height));
	}

	public void addPoint(float x,float y,float radius){
		points.add(new CollisionPoint(x, y, radius));
	}
	
	public Collision update(float x,float y){
		for(int i=0;i<getPoints().size();i++)
			getPoint(i).setAbsoluteXY(x,y);
		return this;
	}

	public void clear(){
		collisionLeft = false;
		collisionRight = false;
		collisionTop = false;
		collisionBottom = false;
	}

	public void check(Collision collision,float x,float y){
		for(int i=0;i<points.size();i++){
			getPoint(i).setAbsoluteXY(actor.getX(),actor.getY());

			for(int j=0;j<collision.getPoints().size();j++){
				collision.getPoint(j).setAbsoluteXY(x, y);
				switch(getPoint(i).check(collision.getPoint(j))){
					case Collision.COLLISION_LEFT:
						collisionLeft = true;
						actor.correctXON(getPoint(i).getLeftCollision());
						break;
					case Collision.COLLISION_RIGHT:
						collisionRight = true;
						actor.correctXON(-getPoint(i).getRightCollision());
						break;
					case Collision.COLLISION_TOP:
						collisionTop = true;
						actor.correctYON(-getPoint(i).getTopCollision());
						break;
					case Collision.COLLISION_DOWN:
						collisionBottom = true;
						actor.correctYON(getPoint(i).getBottomCollision());
						break;
				}

			}

		}
	}

	public boolean isLeft(){
		return collisionLeft;
	}
	public boolean isRight(){
		return collisionRight;
	}
	public boolean isTop(){
		return collisionTop;
	}
	public boolean isBottom(){
		return collisionBottom;
	}

	public List<CollisionPoint> getPoints(){
		return points;
	}
	public CollisionPoint getPoint(int i){
		return points.get(i);
	}
}