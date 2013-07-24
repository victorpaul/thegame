package game;

import interfaces.ICollision;
import physics.Collision;


public class Actor extends Input implements ICollision{
	private float x = 0;
	private float y = 0;
	private float speedOfJumping = 0.45f;
	private float speedOfRotation = 0.15f;
	private float speedOfmoving = 0.15f;
	private int angle = 0;

	private float powerOfThejump = 100f;
	private float powerOfThejumpLeft = 0;

	protected Collision collision = new Collision(this);

	public Actor(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void update(int delta){
		processOfJumpingFalling(delta);
	}

	/**
	 * get collision of current actor
	 */
	public Collision getCollision(){		
		return collision;
	}

	/**
	 * calculate collision of current actor according to another actor 
	 */
	public void getCollision(Actor actor){
		this.collision.check(actor.getCollision(),actor.getX(),actor.getY());
	}

	/* actions */
	protected final void moveLeft(int delta) {
		if(!getCollision().isLeft())
			x -= speedOfmoving * delta;
	}	
	protected final void moveRight(int delta) {
		if(!getCollision().isRight())
			if(powerOfThejumpLeft>0)
				x += speedOfmoving/2 * delta;
			else
				x += speedOfmoving * delta;
	}	
	protected final void moveUp(int delta) {
		if(!getCollision().isTop())
			y += speedOfmoving * delta;
	}	
	protected final void moveDown(int delta) {
		if(!getCollision().isBottom())
			y -= speedOfmoving * delta;
	}	
	protected final void jump(int delta) {
		if (powerOfThejumpLeft <= 0 && getCollision().isBottom())
			powerOfThejumpLeft = powerOfThejump;
	}	
	protected final void moveForwardByAngle(int delta) {
		float r = angle * (float) (Math.PI / 180);
		y += Math.sin(r) * (speedOfmoving * delta);
		x += Math.cos(r) * (speedOfmoving * delta);
	}	
	protected final void moveBackByAngle(int delta) {
		float r = angle * (float) (Math.PI / 180);
		y -= Math.sin(r) * (speedOfmoving * delta);
		x -= Math.cos(r) * (speedOfmoving * delta);
	}	
	protected final void rotateLeft(int delta) {
		normalizaAngle();
		angle += speedOfRotation * delta;
	}	
	protected final void rotateRight(int delta) {
		normalizaAngle();
		angle -= speedOfRotation * delta;
	}
	
	/* utils */
	private void normalizaAngle() {
		if (angle > 360)
			angle -= 360;
		if (angle < 0)
			angle += 360;
	}	
	public final void processOfJumpingFalling(int delta) {
		if (powerOfThejumpLeft > 0) {
			if(getCollision().isTop())powerOfThejumpLeft = 0;
			powerOfThejumpLeft -= speedOfJumping * delta;
			y += (speedOfJumping * delta);
			
		}else if(!getCollision().isBottom()){
			y -= (speedOfJumping * delta);
		}
	}
	public void correctXON(float correct){
		x+=correct;
	}	
	public void correctYON(float correct){
		y+=correct;
	}

	/* getters */
	public final int getAngle() {
		return angle;
	}
	
	public final float getX() {
		return x;
	}
	
	public final float getY() {
		return y;
	}

}