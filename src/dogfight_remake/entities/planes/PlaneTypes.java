package dogfight_remake.entities.planes;

public enum PlaneTypes {
	NORMAL("Normal", 100, 1, 1), FAST("Fast", 75, 1.25f, 0.75f), SLOW("Slow",
			150, 0.75f, 1.25f);

	private int hitpoints;
	private float speed;
	private float damage;
	private String name;

	PlaneTypes(String name, int hitpoins, float speed, float damage) {
		this.setHitpoints(hitpoins);
		this.setName(name);
		this.setSpeed(speed);
		this.setDamage(damage);
	}

	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public int getHitpoints() {
		return hitpoints;
	}

	public void setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
