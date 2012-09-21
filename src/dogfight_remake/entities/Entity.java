package dogfight_remake.entities;

import java.awt.Graphics;

public abstract class Entity {
	// constants
	protected static final double DEFAULT_SPEED = 2;

	// instance variables
	protected double xpos, ypos;
	protected double speed;

	protected double angle;
	protected int hitpoints;

	public Entity(double xpos, double ypos) {
		this.xpos = xpos;
		this.ypos = ypos;
		this.speed = DEFAULT_SPEED;
	}

	public Entity(double xpos, double ypos, double angle) {
		this.angle = angle;
		this.xpos = xpos;
		this.ypos = ypos;
		this.speed = DEFAULT_SPEED;
	}

	public void move() {
	}

	public abstract void paintComponent(Graphics g);

	public void setPosition(double xpos, double ypos) {
		this.xpos = xpos;
		this.ypos = ypos;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getXpos() {
		return xpos;
	}

	public double getYpos() {
		return ypos;
	}

	public void setXpos(double xpos) {
		this.xpos = xpos;
	}

	public void setYpos(double ypos) {
		this.ypos = ypos;
	}

	public double getSpeed() {
		return speed;
	}

	public void incSpeed(double speed) {
		if (this.speed + speed > Planes.MAX_SPEED) {
			this.speed = Planes.MAX_SPEED;
		} else {
			this.speed += speed;
		}
	}

	public void decSpeed(double speed, boolean base) {
		if (base && this.speed < 2) {
			this.speed = 2;
		} else if (!base && this.speed < 0.5) {
			this.speed = 0.5;
		} else {
			this.speed -= speed;
		}
	}
}
