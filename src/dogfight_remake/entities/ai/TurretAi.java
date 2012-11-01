package dogfight_remake.entities.ai;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;

import dogfight_remake.entities.Entity;
import dogfight_remake.entities.Explosion;
import dogfight_remake.entities.planes.Planes;
import dogfight_remake.entities.weapons.WeaponTypes_Primary;
import dogfight_remake.entities.weapons.Weapons;
import dogfight_remake.main.GamePlayState;
import dogfight_remake.main.Var;
import dogfight_remake.rendering.Effects;

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
    private WeaponTypes_Primary wmp;
    private int height, width;
    private int hitpoints;
    private Rectangle turret;
    private long respawn_timer;
    boolean cpos = false;

    float xpos_reset;
    float ypos_reset;
    int hitpoints_reset;
    float angle_reset;

    public TurretAi(int id, float xpos, float ypos, float angle, int hitpoints,
	    Planes target, WeaponTypes_Primary wmp, Image image) {
	super(xpos, ypos, angle);
	this.id = id;
	this.target = target;
	this.angle = angle;
	this.image = image;
	this.wmp = wmp;
	this.hitpoints = hitpoints;
	turret = new Rectangle(xpos, ypos, image.getWidth(), image.getHeight());
	xpos_reset = xpos;
	ypos_reset = ypos;
	hitpoints_reset = hitpoints;
	angle_reset = angle;
	this.respawn_timer = Var.RESPAWNTIME_TURRET;
    }

    @Override
    public void render(GameContainer gc, Graphics g, float delta) {
	if (broken) {
	    return;
	}

	lifetime++;
	barrel = new Polygon(new float[] { xpos, ypos, xpos, ypos + 6,
		xpos + 15, ypos + 6, xpos + 15, ypos });
	if (angle < -120) {
	    Var.img_turret_barrel.setRotation(-120);
	} else if (angle > -60) {
	    Var.img_turret_barrel.setRotation(-60);
	} else {
	    Var.img_turret_barrel.setRotation(angle);
	}
	Var.img_turret_base.draw(xpos, ypos);
	Var.img_turret_barrel.draw(xpos - 3, ypos - 10);

    }

    @Override
    public void update(float delta) {
	if (!cpos) {
	    down();
	}
	if (broken && respawn_timer >= Var.RESPAWNTIME_TURRET) {
	    GamePlayState.explosions.add(new Explosion(xpos, ypos, 4));
	    Var.explode.play(1, Var.sounds_volume);
	}
	if (hitpoints <= 0) {
	    respawn_timer -= delta;
	}
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
	target = getNearestTarget();
	if (targetInRange(getTarget())) {
	    shoot();
	}
    }

    /**
     * Shoots this turrets weapon
     * 
     * @return
     */
    public void shoot() {
	float angle = this.angle;
	if (broken || !inRange) {
	    return;
	}
	random = new Random();
	double rnd = random.nextDouble() * 2;
	double rnd1 = random.nextInt();
	if (rnd1 % 2 == 0) {
	    angle += rnd;
	} else if (rnd % 2 != 0) {
	    angle -= rnd;
	}
	long time = System.currentTimeMillis();
	if (Math.abs(lastshot_prim - time) < wmp.getShoot_delay()) {
	    return;
	}
	lastshot_prim = time;
	float x = (float) (xpos + Math.cos(Math.toRadians(angle)));
	float y = (float) (ypos + Math.sin(Math.toRadians(angle)) - 50);
	wmp.getSound().play(1, Var.sounds_volume);
	GamePlayState.weapons.add(new Weapons(x, y, angle, wmp, 0, id, null));
    }

    /**
	 * 
	 */

    public void reset() {
	xpos = xpos_reset;
	ypos = ypos_reset;
	hitpoints = hitpoints_reset;
	angle = angle_reset;
	broken = false;
    }

    public void down() {
	float xpos1 = xpos / Var.tmap.getTileWidth();
	float ypos1 = ypos / Var.tmap.getTileHeight();

	if (Var.tmap.getTileId((int) xpos1, (int) ypos1, 0) != 0) {
	    ypos = ypos - Var.tmap.getTileHeight();
	    cpos = true;
	    ypos_reset = ypos;
	} else {
	    ypos += 4;
	    cpos = false;
	}
    }

    /**
     * Returns nearest target (Player 1 or Player 2)
     * 
     * @return
     */
    public Planes getNearestTarget() {
	double xDiff_p1 = xpos - GamePlayState.r.player1.getXpos();
	double yDiff_p1 = ypos - GamePlayState.r.player1.getYpos();
	double xDiff_p2 = xpos - GamePlayState.r.player2.getXpos();
	double yDiff_p2 = ypos - GamePlayState.r.player2.getYpos();
	if (Math.sqrt(xDiff_p1 * xDiff_p1 + yDiff_p1 * yDiff_p1) > Math
		.sqrt(xDiff_p2 * xDiff_p2 + yDiff_p2 * yDiff_p2)) {
	    return GamePlayState.r.player2;
	} else {
	    return GamePlayState.r.player1;
	}
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
     * Sets Hitpoints to desired value
     * 
     * @param hitpoints
     */
    public void setHitpoints(int hitpoints) {
	this.hitpoints = hitpoints;
	if (hitpoints == 0)
	    broken = true;
    }

    /**
     * Returns current Hitpoints
     * 
     * @return
     */
    public int getHitpoints() {
	if (hitpoints < 0) {
	    return 0;
	} else {
	    return hitpoints;
	}

    }

    /**
     * Returns players plane image
     * 
     * @return
     */
    public Image getImage() {
	return image;
    }

    /**
     * Decreases hitpoints
     */
    public void decreaseHitpoints(int damage) {
	hitpoints -= damage;
	if (hitpoints <= 0)
	    broken = true;
    }

    /**
     * Returns plane rectangle
     * 
     * @return
     */
    public Rectangle getPlane() {
	return turret;
    }

    /**
     * Returns Center xpos of plane
     * 
     * @return
     */
    public float getCenterX() {
	if (turret != null) {
	    return turret.getCenterX();
	} else {
	    return xpos;
	}
    }

    /**
     * Returns Center ypos of plane
     * 
     * @return
     */
    public float getCenterY() {
	if (turret != null) {
	    return turret.getCenterY();
	} else {
	    return ypos;
	}
    }

    /**
     * Returns wether a weapon is 'alive'. Alive means not destroyed or max
     * range achieved
     * 
     * @return
     */
    public boolean isAlive() {
	if (lifetime > wmp.getLife_time()) {
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

    public boolean targetInRange(Planes target) {
	if (Math.abs((xpos + ypos) - (target.getXpos() + target.getYpos())) <= wmp
		.getLife_time() - 100) {
	    return true;
	} else {
	    return false;
	}
    }

    public long getRespawn_timer() {
	return respawn_timer;
    }

    public void setRespawn_timer(long respawn_timer) {
	this.respawn_timer = respawn_timer;
    }
}
