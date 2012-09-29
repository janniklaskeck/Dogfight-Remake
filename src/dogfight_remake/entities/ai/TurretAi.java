package dogfight_remake.entities.ai;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Polygon;
import dogfight_remake.entities.Entity;
import dogfight_remake.entities.planes.Planes;
import dogfight_remake.entities.weapons.WeaponTypes;
import dogfight_remake.entities.weapons.Weapons;
import dogfight_remake.main.GlbVar;

public class TurretAi extends Entity {
	private int lifetime = 0;
	private int id;
	private Planes target;
	private Polygon barrel;
	private float angle;
	private Random random;
	private boolean broken = false;
	private boolean inRange = true;
	private long lastshot_prim;
	private Image image;
	private WeaponTypes wmp;
	private int height, width;

	public TurretAi(int id, float xpos, float ypos, float angle, Planes target,
			WeaponTypes wmp, Image image) {
		super(xpos, ypos, angle);
		this.id = id;
		this.target = target;
		this.angle = angle;
		this.image = image;
		this.wmp = wmp;
	}

	@Override
	public void render(GameContainer gc, Graphics g, int delta) {
		lifetime++;
		barrel = new Polygon(new float[] { xpos, ypos, xpos, ypos + 6,
				xpos + 15, ypos + 6, xpos + 15, ypos });
		if (angle < -120) {
			GlbVar.img_turret_barrel.setRotation(-120);
		} else if (angle > -60) {
			GlbVar.img_turret_barrel.setRotation(-60);
		} else {
			GlbVar.img_turret_barrel.setRotation(angle);
		}
		GlbVar.img_turret_base.draw(xpos, ypos);
		GlbVar.img_turret_barrel.draw(xpos - 3, ypos - 10);

	}

	public void update(GameContainer gc, int delta) {

		if (angle > -120 && angle < -60) {
			inRange = true;
		} else {
			inRange = false;
		}
		if (barrel != null) {
			float deltaX = target.getCenterX() - xpos;
			float deltaY = target.getCenterY() - ypos;

			angle = (float) Math.toDegrees(Math.atan2(deltaY, deltaX));

		}
	}

	public Weapons shoot() {

		float angle = this.angle;
		if (broken || !inRange) {
			return null;
		}
		random = new Random();
		double rnd = random.nextDouble() * 2;
		double rnd1 = random.nextInt();
		if (rnd1 % 2 == 0) {
			angle += rnd;
		} else if (rnd % 2 != 0) {
			angle -= rnd;
		}

		int delay_prim = 0;
		long time = System.currentTimeMillis();

		delay_prim = wmp.getShoot_delay();

		if (Math.abs(lastshot_prim - time) < delay_prim) {
			return null;
		}
		lastshot_prim = time;
		float x = (float) (xpos + Math.cos(Math.toRadians(angle)));
		float y = (float) (ypos + Math.sin(Math.toRadians(angle)) - 50);
		return new Weapons(x, y, angle, wmp.getDamage(), wmp, 0, image, id);

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Sets this weapons hit status to true
	 */
	public void setHit() {
		broken = true;
	}

	/**
	 * Returns wether this weapon has hit something
	 * 
	 * @return
	 */
	public boolean isHit() {
		return broken;
	}

	/**
	 * Returns width of weapon
	 * 
	 * @return
	 */
	public float getWidth() {
		return width;
	}

	/**
	 * Returns height of weapon
	 * 
	 * @return
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * Returns wether a weapon is 'alive'. Alive means not destroyed or max
	 * range achieved
	 * 
	 * @return
	 */
	public boolean isAlive() {
		if (lifetime > Weapons.MAX_LIFETIME_GUN) {
			return false;
		}
		return true;
	}

	public void setTarget(Planes plane) {
		target = plane;
	}

	public Planes getTarget() {
		return target;
	}
}
