package characters;

import game.Actor;


public final class Plane extends Actor{

	public Plane(float x,float y){
		super(x,y);		
	}

	public void update(int delta){
		if(isPressedDown() && !isPressedUp()){
			moveBackByAngle(delta);
		}
		if(!isPressedDown() && isPressedUp()){
			moveForwardByAngle(delta);
		}
		if(isPressedLeft() && !isPressedRight()){			
			rotateLeft(delta);
		}
		if(!isPressedLeft() && isPressedRight()){
			rotateRight(delta);
		}

	}

}