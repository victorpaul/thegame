package characters;

import game.Actor;
import interfaces.ICharacter;

public final class Mario extends Actor implements ICharacter{

	public Mario(float x, float y) {
		super(x, y);

		getCollision().addPoint(0,0,40,40);		
	}

	public void update(int delta){
		super.update(delta);
		
		//System.out.println("left="+getCollision().isLeft()+" right="+getCollision().isRight() + " top="+getCollision().isTop()+" bottom="+getCollision().isBottom());
		
		if(isPressedDown() && !isPressedUp()){			
			moveDown(delta);
		}
		if(!isPressedDown() && isPressedUp()){
			moveUp(delta);
		}
		if(isPressedLeft() && !isPressedRight()){
			moveLeft(delta);
		}
		if(!isPressedLeft() && isPressedRight()){
			moveRight(delta);
		}
		if(isPressedSpace()){
			jump(delta);
		}

	}

}