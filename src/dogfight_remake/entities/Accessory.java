package dogfight_remake.entities;

/**
* Different Accesories
* Not sure if only one or two slots
* maybe depending on plane type (bigger = 2 Accessories, smaller = 1)
*/

public enum Accessory {
	FUELTANK, // 2 * FuelCapacity
	FLARES, // Chance of distracting Guided missiles, no use against radar
	IR_CLOUD, // Effective Counter-Measure, distracts Guided and Radar, but higher cooldown
	ECM, // Electronic-Counter-Measures, distracts only radar
	ARMOR_GUN, // better armor against guns
	ARMOR_MISSILE, // better armor against missiles
	LOCKON_ENHANCEMENT; // Radar type missiles have greater cone to target
}
