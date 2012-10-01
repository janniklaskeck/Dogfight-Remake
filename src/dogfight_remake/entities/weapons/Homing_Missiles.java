package dogfight_remake.entities.weapons;

import dogfight_remake.entities.ai.TurretAi;
import dogfight_remake.entities.planes.Planes;

public class Homing_Missiles {

	public static Weapons moveMissile(Weapons weapon, Planes pln1, Planes pln2,
			float delta) {
		Weapons wmp = weapon;
		float speed = wmp.getType().getSpeed();
		float wmpx = wmp.getXpos();
		float wmpy = wmp.getYpos();
		float plnx = pln2.getCenterX();
		float plny = pln2.getCenterY();
		float deltaX = plnx - wmpx;
		float deltaY = plny - wmpy;

		double atan2 = Math.atan2(deltaY, deltaX);

		// change atan2 to 0-360 degrees
		if (atan2 < 0) {
			atan2 = Math.abs(atan2);
		} else {
			atan2 = 2 * Math.PI - atan2;
		}
		float angle = Math.round(Math.toDegrees(atan2));
		if (angle >= 360) {
			angle = 0;
		}
		// with help from http://krinstudio.com/?p=523
		angle = (360 - (int) angle) % 360;

		// Keep them on the right side. (Direction is in degrees, not radians)
		if (wmp.getAngle() < 0)
			wmp.increaseAngle(360);
		if (angle < wmp.getAngle())
			angle += 360;

		// Find the difference in the angle.
		float angleDifference = angle - wmp.getAngle();

		// Turn the actual direction towards the target direction.
		if (((angleDifference < 180) && (angleDifference > 0))
				|| ((angleDifference < -180))) {
			wmp.increaseAngle(1.5f);
		} else if (angleDifference == 0) {
			wmp.increaseAngle(0);
		} else {
			wmp.increaseAngle(-1.5f);
		}
		// speed calculation
		float hspeed = (float) (speed * Math.cos(Math.toRadians(wmp.getAngle())) * (float) delta / 17);
		float vspeed = (float) (speed * Math.sin(Math.toRadians(wmp.getAngle())) * (float) delta / 17);
		wmp.setXpos(wmp.getXpos() + hspeed);
		wmp.setYpos(wmp.getYpos() + vspeed);

		return wmp;
	}
	
	public static Weapons moveMissile(Weapons weapon, Planes pln1, TurretAi ta,
			float delta) {
		Weapons wmp = weapon;
		float speed = wmp.getType().getSpeed();
		float wmpx = wmp.getXpos();
		float wmpy = wmp.getYpos();
		float plnx = ta.getCenterX();
		float plny = ta.getCenterY();
		float deltaX = plnx - wmpx;
		float deltaY = plny - wmpy;

		double atan2 = Math.atan2(deltaY, deltaX);

		// change atan2 to 0-360 degrees
		if (atan2 < 0) {
			atan2 = Math.abs(atan2);
		} else {
			atan2 = 2 * Math.PI - atan2;
		}
		float angle = Math.round(Math.toDegrees(atan2));
		if (angle >= 360) {
			angle = 0;
		}
		// with help from http://krinstudio.com/?p=523
		angle = (360 - (int) angle) % 360;

		// Keep them on the right side. (Direction is in degrees, not radians)
		if (wmp.getAngle() < 0)
			wmp.increaseAngle(360);
		if (angle < wmp.getAngle())
			angle += 360;

		// Find the difference in the angle.
		float angleDifference = angle - wmp.getAngle();

		// Turn the actual direction towards the target direction.
		if (((angleDifference < 180) && (angleDifference > 0))
				|| ((angleDifference < -180))) {
			wmp.increaseAngle(1.5f);
		} else if (angleDifference == 0) {
			wmp.increaseAngle(0);
		} else {
			wmp.increaseAngle(-1.5f);
		}
		// speed calculation
		float hspeed = (float) (speed * Math.cos(Math.toRadians(wmp.getAngle())) * (float) delta / 17);
		float vspeed = (float) (speed * Math.sin(Math.toRadians(wmp.getAngle())) * (float) delta / 17);
		wmp.setXpos(wmp.getXpos() + hspeed);
		wmp.setYpos(wmp.getYpos() + vspeed);

		return wmp;
	}
}
