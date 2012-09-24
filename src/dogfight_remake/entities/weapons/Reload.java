package dogfight_remake.entities.weapons;

import dogfight_remake.entities.planes.Planes;

public class Reload {

	public static void reload_primary(Planes pln) {
		WeaponTypes wpn = pln.getWeapon(1);
		if (pln.getAmmo(1) == 0) {
			pln.addAmmo(1, wpn, wpn.getAmmoCount());
		}
	}

	public static void reload_secondary_1(Planes pln) {
		WeaponTypes wpn = pln.getWeapon(2);
		if (pln.getAmmo(2) == 0) {
			pln.addAmmo(2, wpn, wpn.getAmmoCount());
		}
	}

	public static void reload_secondary_2(Planes pln) {
		WeaponTypes wpn = pln.getWeapon(3);
		if (pln.getAmmo(3) == 0) {
			pln.addAmmo(3, wpn, wpn.getAmmoCount());
		}
	}
}
