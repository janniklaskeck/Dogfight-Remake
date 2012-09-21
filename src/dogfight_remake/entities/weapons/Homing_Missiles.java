package dogfight_remake.entities.weapons;

import dogfight_remake.entities.Planes;

public class Homing_Missiles {

	public static Weapons moveMissile(Weapons weapon, Planes pln1, Planes pln2,
			double delta) {
		Weapons wmp = weapon;
		double speed = Weapons.DEFAULT_SPEED_GUIDED;
		double wmpx = wmp.getXpos();
		double wmpy = wmp.getYpos();
		double plnx = pln2.getCenterX();
		double plny = pln2.getCenterY();
		double deltaX = plnx - wmpx;
		double deltaY = plny - wmpy;

		double atan2 = Math.atan2(deltaY, deltaX);

		// change atan2 to 0-360 degrees
		if (atan2 < 0) {
			atan2 = Math.abs(atan2);
		} else {
			atan2 = 2 * Math.PI - atan2;
		}
		double angle = Math.round(Math.toDegrees(atan2));
		if (angle == 360) {
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
		double angleDifference = angle - wmp.getAngle();

		// Turn the actual direction towards the target direction.
		if (((angleDifference < 180) && (angleDifference > 0))
				|| ((angleDifference < -180))) {
			wmp.increaseAngle(3);
		} else if (angleDifference == 0) {
			wmp.increaseAngle(0);
		} else {
			wmp.increaseAngle(-3);
		}

		// speed calculation
		double hspeed = speed * Math.cos(Math.toRadians(wmp.getAngle()))
				* delta;
		double vspeed = speed * Math.sin(Math.toRadians(wmp.getAngle()))
				* delta;
		wmp.setXpos(wmp.getXpos() + hspeed);
		wmp.setYpos(wmp.getYpos() + vspeed);

		return wmp;
	}
}
