package game;

import org.lwjgl.input.Keyboard;

public class Input {

	/* the buttons */
	private boolean pressedUp = false;
	private boolean pressedDown = false;
	private boolean pressedLeft = false;
	private boolean pressedRight = false;
	private boolean pressedSpace = false;

	public void pressButton(int key){
		setKey(key,true);
	}

	public void releaseButton(int key){
		setKey(key,false);
	}

	public void setKey(int key,boolean value){
		switch(key){
			case Keyboard.KEY_UP:
				pressedUp = value;
				break;
			case Keyboard.KEY_DOWN:
				pressedDown = value;
				break;				
			case Keyboard.KEY_LEFT:
				pressedLeft = value;
				break;
			case Keyboard.KEY_RIGHT:
				pressedRight = value;
				break;
			case Keyboard.KEY_SPACE:
				pressedSpace = value;
				break;
		}
	}

	public boolean isPressedUp(){
		return pressedUp;
	}
	public boolean isPressedDown(){
		return pressedDown;	
	}
	public boolean isPressedLeft(){
		return pressedLeft;
	}
	public boolean isPressedRight(){
		return pressedRight;
	}
	public boolean isPressedSpace(){
		return pressedSpace;
	}
}