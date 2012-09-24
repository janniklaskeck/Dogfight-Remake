package dogfight_remake.entities;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import dogfight_remake.entities.planes.Planes;

public abstract class Entity {
	// constants
	protected static final float DEFAULT_SPEED = 2;

	// instance variables
	protected float xpos, ypos;
	protected float speed;

	protected float angle;
	protected int hitpoints;

	public Entity(float xpos, float ypos) {
		this.xpos = xpos;
		this.ypos = ypos;
		this.speed = DEFAULT_SPEED;
	}

	public Entity(float xpos, float ypos, float angle) {
		this.angle = angle;
		this.xpos = xpos;
		this.ypos = ypos;
		this.speed = DEFAULT_SPEED;
	}

	public void move() {
	}

	public abstract void render(GameContainer container, Graphics g);

	public void setPosition(float xpos, float ypos) {
		this.xpos = xpos;
		this.ypos = ypos;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getXpos() {
		return xpos;
	}

	public float getYpos() {
		return ypos;
	}

	public void setXpos(float xpos) {
		this.xpos = xpos;
	}

	public void setYpos(float ypos) {
		this.ypos = ypos;
	}

	public float getSpeed() {
		return speed;
	}

	public void incSpeed(float speed) {
		if (this.speed + speed > Planes.MAX_SPEED) {
			this.speed = Planes.MAX_SPEED;
		} else {
			this.speed += speed;
		}
	}

	public void decSpeed(float speed, boolean base) {
		if (base && this.speed < 2) {
			this.speed = 2;
		} else if (!base && this.speed < 0.5) {
			this.speed = (float) 0.5;
		} else {
			this.speed -= speed;
		}
	}
}
