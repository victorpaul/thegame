package game;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import structures.Rock;
import characters.Mario;

public class Game {

	// all static thing in the game
	List<Actor> brick = new ArrayList<Actor>();
	
	// all dinamic actors in the game
	List<Actor> actor = new ArrayList<Actor>();

	/** time at last frame */
	long lastFrame;	
	/** frames per second */
	int fps;
	/** last fps time */
	long lastFPS;
	
	public void start() {
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}		
		initGL(); // init OpenGL
		getDelta(); // call once before loop to initialise lastFrame
		lastFPS = getTime(); // call before loop to initialise fps timer

		for(int i=0;i<10;i++)brick.add(new Rock(i*50,100));		
		for(int i=2;i<6;i++)brick.add(new Rock(50,i*60));
		for(int i=2;i<6;i++)brick.add(new Rock(550,i*60));
		
		brick.add(new Rock(150,150));
		brick.add(new Rock(250,150));
		brick.add(new Rock(400,230));
		
		brick.add(new Rock(505,185));
		
		brick.add(new Rock(505,30));		
		brick.add(new Rock(565,20));
		brick.add(new Rock(600,60));
		
		//actor.add(new Plane(300,300));
		actor.add(new Mario(101,300));		

		while (!Display.isCloseRequested()) {
			int delta = getDelta();
			update(delta);
			renderGL();
			Display.update();
			Display.sync(60); // cap fps to 60fps
		}
		
		Display.destroy();
	}

	public void update(int delta) {

		while (Keyboard.next()) {
		    if (Keyboard.getEventKeyState()) {
		    	for(int i=0;i<actor.size();i++)
					actor.get(i).pressButton(Keyboard.getEventKey());
		    } else {
		    	for(int i=0;i<actor.size();i++)
					actor.get(i).releaseButton(Keyboard.getEventKey());
		    }
		}

		for(int i=0;i<actor.size();i++){
			actor.get(i).getCollision().clear();
			for(int j=0;j<brick.size();j++)
				actor.get(i).getCollision(brick.get(j));
			actor.get(i).update(delta);
		}
	}

	/**
	 * Calculate how many milliseconds have passed
	 * since last frame.
	 * 
	 * @return milliseconds passed since last frame
	 */
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		return delta;
	}

	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	public void initGL() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

	public void renderGL() {
		// Clear The Screen And The Depth Buffer
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		// R,G,B,A Set The Color To Blue One Time Only
		GL11.glColor3f(0.5f, 0.5f, 1.0f);

		for(int i=0;i<actor.size();i++)
			Draw.it(actor.get(i));
		for(int i=0;i<brick.size();i++)
			Draw.it(brick.get(i));
	}

	public static void main(String[] argv) {
		Game game = new Game();
		game.start();
	}
}