package game;

import org.lwjgl.opengl.GL11;

import characters.Plane;

public class Draw {

	public static void it(Actor actor){
		if(actor.getClass() == Plane.class){
			plane(actor);
		}else{
			unrecognized(actor);
		}

	}

	private static void plane(Actor actor){
		GL11.glPushMatrix();
		GL11.glTranslatef(actor.getX(),actor.getY(), 0);
		GL11.glRotatef(actor.getAngle(), 0f, 0f, 1f);
		GL11.glTranslatef(-actor.getX(), -actor.getY(), 0);

		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor3f(10,0,0);
		GL11.glVertex2f(actor.getX() - 100, actor.getY() - 50);
		GL11.glVertex2f(actor.getX() + 50, actor.getY() - 50);
		GL11.glVertex2f(actor.getX() + 50, actor.getY() + 50);
		GL11.glVertex2f(actor.getX() - 50, actor.getY() + 50);
		GL11.glEnd();

		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor3f(0,10,0);
		GL11.glVertex2f(actor.getX() - 50, actor.getY() - 1);
		GL11.glVertex2f(actor.getX() + 50, actor.getY() - 1);
		GL11.glVertex2f(actor.getX() + 1, actor.getY() + 1);
		GL11.glVertex2f(actor.getX() - 1, actor.getY() + 1);
		GL11.glEnd();

		GL11.glPopMatrix();
	}

	private static void unrecognized(Actor actor){
		GL11.glPushMatrix();
		GL11.glTranslatef(actor.getX(),actor.getY(), 0);
		GL11.glRotatef(actor.getAngle(), 0f, 0f, 1f);
		GL11.glTranslatef(-actor.getX(), -actor.getY(), 0);

		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor3f(10,0,0);
		GL11.glVertex2f(actor.getX() - 20, actor.getY() - 20);
		GL11.glVertex2f(actor.getX() + 20, actor.getY() - 20);
		GL11.glVertex2f(actor.getX() + 20, actor.getY() + 20);
		GL11.glVertex2f(actor.getX() - 20, actor.getY() + 20);
		GL11.glEnd();

		GL11.glPopMatrix();
	}
}