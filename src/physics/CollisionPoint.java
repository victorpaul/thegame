package physics;

/**
 * the most difficult part of collision, needs to be improved
 * @author Victor
 *
 */
public class CollisionPoint {

	public float x;
	public float y;
	private float relatedX;
	private float relatedY;
	public float width;
	public float heigth;
	public float radius;

	private float leftColision = 0;
	private float rightColision = 0;
	private float topColision = 0;
	private float bottomColision = 0;

	private float vRange = 0;
	private float hRange = 0;
	private float vMinRange = 0;
	private float hMinRange = 0;

	private boolean quad = false;

	public CollisionPoint(float x,float y,float width,float height){
		this.relatedX = x;
		this.relatedY = y;
		this.width = width;
		this.heigth = height;
		quad = true;
	}

	public CollisionPoint(float x,float y,float radius){
		this.relatedX = x;
		this.relatedY = y;
		this.radius = radius;
	}
	
	public void setAbsoluteXY(float x,float y){		
		this.x = x+relatedX;
		this.y = y+relatedY;
		
	}

	public byte check(CollisionPoint subject){
		if(quad){
			vRange = y-subject.y;
			hRange = x-subject.x;
			vMinRange = heigth/2+subject.heigth/2;
			hMinRange = width/2+subject.width/2;

			leftColision = -1;
			rightColision = -1;
			topColision = -1;
			bottomColision = -1;

			if(vRange > 0){
				if( vRange <= vMinRange) topColision = vMinRange-vRange;
			}else{
				if( -vRange <= vMinRange) bottomColision = vMinRange+vRange;
			}

			if(hRange > 0){
				if( hRange <= hMinRange) rightColision = hMinRange-hRange;
			}else{
				if( -hRange <= hMinRange) leftColision = hMinRange+hRange;
			}

			//System.out.println("vRange="+vRange+" vMinRange="+vMinRange);
			//System.out.println("leftColision="+leftColision+" rightColision="+rightColision + "topColision="+topColision+" bottomColision"+bottomColision);
			
			if(leftColision>=0 && bottomColision>=0)
				return (leftColision > bottomColision)?Collision.COLLISION_TOP:Collision.COLLISION_RIGHT;

			if(rightColision>=0 && bottomColision>=0)
				return (rightColision > bottomColision)?Collision.COLLISION_TOP:Collision.COLLISION_LEFT;

			if(leftColision>=0 && topColision>=0)
				return (leftColision < topColision)?Collision.COLLISION_RIGHT:Collision.COLLISION_DOWN;

			if(rightColision>=0 && topColision>=0)
				return (rightColision > topColision)?Collision.COLLISION_DOWN:Collision.COLLISION_LEFT;
			
		}else{// TODO: otherwisi it's circle
			
		}

		return Collision.COLLISION_NONE;
	}
	
	public float getRightCollision(){
		return leftColision;
	}
	public float getLeftCollision(){
		return rightColision;
	}
	public float getTopCollision(){
		return bottomColision;
	}
	public float getBottomCollision(){
		return topColision;
	}
}