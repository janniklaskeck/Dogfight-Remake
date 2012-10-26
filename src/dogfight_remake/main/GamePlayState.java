package dogfight_remake.main;

import java.awt.Dimension;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import dogfight_remake.controls.KeyControls;
import dogfight_remake.entities.Explosion;
import dogfight_remake.entities.weapons.Reload;
import dogfight_remake.entities.weapons.WeaponTypes_Primary;
import dogfight_remake.entities.weapons.WeaponTypes_Secondary;
import dogfight_remake.entities.weapons.Weapons;
import dogfight_remake.entities.planes.PlaneTypes;
import dogfight_remake.map.Block;
import dogfight_remake.map.BlockMap;
import dogfight_remake.rendering.Render;

public class GamePlayState extends BasicGameState {
    public static Dimension dim = Var.dim_chosen;

    public static ArrayList<Weapons> weapons;
    public static ArrayList<Explosion> explosions;

    private static Rectangle p1;
    private static Rectangle p2;
    private Rectangle turret;
    private Rectangle wep;
    public static Polygon p1A;
    public static Polygon p2A;
    public static Render r;
    private float deltaX, deltaY;

    public static Camera camera;
    public static Camera camera2;
    public int time;

    int stateID = -1;
    private int delta = 0;

    public GamePlayState(int stateID) {
	this.stateID = stateID;
    }

    @Override
    public int getID() {
	return stateID;
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) {
	gc.setVSync(true);
	gc.setShowFPS(false);
	if (Var.paused) {
	    r = new Render();
	    try {
		r.init();
	    } catch (SlickException e) {
		e.printStackTrace();
	    }
	    weapons = new ArrayList<Weapons>();
	    explosions = new ArrayList<Explosion>();
	    camera = new Camera(gc, Var.tmap);
	    camera2 = new Camera(gc, Var.tmap);

	    // GlbVar.music1.loop(1, GlbVar.music_volume);
	    Var.score_p1 = 0;
	    Var.score_p2 = 0;
	    Var.paused = false;
	    Var.timePassed = 0;
	}
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g)
	    throws SlickException {
	r.render(gc, g, delta);
	if (Var.paused) {
	    Var.img_pause_bg = new Image(1680, 1050);
	    g.copyArea(Var.img_pause_bg, 0, 0);
	    game.enterState(Dogfight_Remake.PAUSEDSTATE);
	}
    }

    @Override
    public void init(GameContainer gc, StateBasedGame game)
	    throws SlickException {
	WeaponTypes_Primary.MM30S.init();
	WeaponTypes_Secondary.BOMB.init();
	PlaneTypes.NORMAL.init();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
	    throws SlickException {
	this.delta = delta;
	KeyControls.update(gc, sbg, delta);
	if (r.player1 != null && r.player2 != null && !Var.paused) {
	    time += delta;
	    Var.timePassed = time / 1000;
	    r.player1.update(delta);
	    r.player2.update(delta);
	    r.turret.update(delta);
	    Reload.reload_primary(r.player1, delta);
	    Reload.reload_primary(r.player2, delta);
	    Reload.reload_secondary_1(r.player1, delta);
	    Reload.reload_secondary_1(r.player2, delta);
	    Reload.reload_secondary_2(r.player1, delta);
	    Reload.reload_secondary_2(r.player2, delta);
	    checkCollision(gc);
	    for (int i = 0; i < weapons.size(); i++) {
		if (!weapons.get(i).isHit() && weapons.get(i).isAlive()) {
		    if (weapons.get(i).getType() == WeaponTypes_Secondary.GUIDED_AIR) {
			if (weapons.get(i).getID() == 1) {
			    if (r.player2.getHitpoints() > 0
				    && weapons.get(i).hasTarget()) {
				weapons.get(i).update(r.player1, r.player2,
					delta);
			    } else {
				weapons.get(i).setTarget(false);
				weapons.get(i).update(delta);
			    }
			} else {
			    if (r.player1.getHitpoints() > 0
				    && weapons.get(i).hasTarget()) {
				weapons.get(i).update(r.player2, r.player1,
					delta);
			    } else {
				weapons.get(i).setTarget(false);
				weapons.get(i).update(delta);
			    }
			}
		    } else {
			weapons.get(i).update(delta);
		    }
		} else {
		    weapons.remove(i);
		}
	    }
	    if (r.player1.getHitpoints() <= 0 || r.player2.getHitpoints() <= 0
		    || r.turret.getHitpoints() <= 0) {
		respawn();
	    }
	    for (int i = 0; i < explosions.size(); i++) {
		explosions.get(i).update(delta);
		if (explosions.get(i).isBroken()) {
		    explosions.remove(i);
		}
	    }
	}
	if (Var.singlePlayer) {
	    camera.centerOn(r.player1.getXpos(), r.player1.getYpos(), 1);
	} else if (!Var.singlePlayer) {
	    camera.centerOn(r.player1.getXpos(), r.player1.getYpos(), 1);
	    camera2.centerOn(r.player2.getXpos(), r.player2.getYpos(), 2);
	}
    }

    /**
     * Respawn method
     */
    private void respawn() {
	if (r.player1.getRespawn_timer() <= 0) {
	    r.player1.reset();
	    r.player1.setRespawn_timer(Var.RESPAWNTIME_PLAYER);
	}
	if (r.player2.getRespawn_timer() <= 0) {
	    r.player2.reset();
	    r.player2.setRespawn_timer(Var.RESPAWNTIME_PLAYER);
	}
	if (r.turret.getRespawn_timer() <= 0) {
	    r.turret.reset();
	    r.turret.setRespawn_timer(Var.RESPAWNTIME_TURRET);
	}
    }

    /**
     * Check for Collision (plane X plane or plane X weapon)
     */

    private void checkCollision(GameContainer gc) {
	p1 = new Rectangle((int) r.player1.getXpos(),
		(int) r.player1.getYpos(), r.player1.getImage().getWidth(),
		r.player1.getImage().getHeight());
	p2 = new Rectangle((int) r.player2.getXpos(),
		(int) r.player2.getYpos(), r.player2.getImage().getWidth(),
		r.player2.getImage().getHeight());
	turret = new Rectangle((int) r.turret.getXpos(),
		(int) r.turret.getYpos(), Var.img_turret_base.getWidth(),
		Var.img_turret_base.getHeight());
	if (r.player1.getHitpoints() > 0) {
	    if (r.player1.getAim() != null) {
		deltaX = r.player1.getCenterX()
			- r.player1.getAim().getCenterX();
		deltaY = r.player1.getCenterY()
			- r.player1.getAim().getCenterY();
		p1A = (Polygon) p1.transform(Transform.createRotateTransform(
			(float) Math.atan2(deltaY, deltaX),
			r.player1.getCenterX(), r.player1.getCenterY()));
	    }
	}

	if (r.player2.getHitpoints() > 0) {
	    if (r.player2.getAim() != null) {
		deltaX = r.player2.getCenterX()
			- r.player2.getAim().getCenterX();
		deltaY = r.player2.getCenterY()
			- r.player2.getAim().getCenterY();
		p2A = (Polygon) p2.transform(Transform.createRotateTransform(
			(float) Math.atan2(deltaY, deltaX),
			r.player2.getCenterX(), r.player2.getCenterY()));
	    }
	}

	for (int i = 0; i < weapons.size(); i++) {
	    Weapons tmp = weapons.get(i);
	    wep = new Rectangle((int) tmp.getXpos(), (int) tmp.getYpos(),
		    (int) tmp.getWidth(), (int) tmp.getHeight());
	    if (weapons.size() > 0) {
		for (int o = 0; o < BlockMap.entities.size(); o++) {
		    Block entity1 = (Block) BlockMap.entities.get(o);
		    if (wep.intersects(entity1.poly)) {
			tmp.setHitTarget();
		    }
		}
		if (p2A != null) {
		    if (tmp.getID() == 1 || tmp.getID() == 3) {
			if (p2A.intersects(wep) && r.player2.getHitpoints() > 0
				&& !tmp.isHitTarget()) {
			    tmp.setHitTarget();
			    r.player2.decreaseHitpoints(tmp.getDamage());
			}
		    }
		}
		if (p1A != null) {
		    if (tmp.getID() == 2 || tmp.getID() == 3) {
			if (p1A.intersects(wep) && r.player1.getHitpoints() > 0
				&& !tmp.isHitTarget()) {

			    tmp.setHitTarget();
			    r.player1.decreaseHitpoints(tmp.getDamage());
			}
		    }
		}
		if (turret != null) {
		    if (tmp.getID() == 1 || tmp.getID() == 2) {
			if (turret.intersects(wep)
				&& r.turret.getHitpoints() > 0
				&& !tmp.isHitTarget()) {
			    tmp.setHitTarget();
			    r.turret.decreaseHitpoints(tmp.getDamage());
			}
		    }
		}
	    }
	}

	if (p1A != null) {
	    if (r.player1.getHitpoints() <= 0
		    && r.player1.getRespawn_timer() >= Var.RESPAWNTIME_PLAYER) {
		p1A = null;
		Var.score_p2++;
	    }
	}
	if (p2A != null) {
	    if (r.player2.getHitpoints() <= 0
		    && r.player2.getRespawn_timer() >= Var.RESPAWNTIME_PLAYER) {
		explosions.add(new Explosion(r.player2.getXpos(), r.player2
			.getYpos(), 4));
		p2A = null;
		Var.score_p1++;
	    }
	}
	if (turret != null) {
	    if (r.turret.getHitpoints() <= 0
		    && r.turret.getRespawn_timer() >= Var.RESPAWNTIME_TURRET) {
		turret = null;
	    }
	}
	if (p1A != null && p2A != null && Var.getPlayerCollision()) {
	    if (p1A.intersects(p2A)
		    && r.player1.getRespawn_timer() >= Var.RESPAWNTIME_PLAYER
		    && r.player2.getRespawn_timer() >= Var.RESPAWNTIME_PLAYER) {
		r.player1.setHitpoints(0);
		r.player2.setHitpoints(0);
		p1A = null;
		p2A = null;
	    }
	}
	if (p1A != null) {
	    if (Var.tmap.getTileId(
		    (int) p1A.getMaxX() / Var.tmap.getTileWidth(),
		    (int) p1A.getMaxY() / Var.tmap.getTileHeight(), 0) == 2) {
		
		r.player1.setHitpoints(0);
		p1A = null;
	    }
	}
	if (p2A != null) {
	    if (Var.tmap.getTileId(
		    (int) p2A.getMaxX() / Var.tmap.getTileWidth(),
		    (int) p2A.getMaxY() / Var.tmap.getTileHeight(), 0) == 2) {
		
		r.player2.setHitpoints(0);
		p2A = null;
	    }
	}

	Rectangle bounds_left = new Rectangle(0, 0, 1, Var.tmap.getHeight()
		* Var.tmap.getTileHeight());
	Rectangle bounds_right = new Rectangle(Var.tmap.getWidth()
		* Var.tmap.getTileWidth() - 1, 0, 1, Var.tmap.getHeight()
		* Var.tmap.getTileHeight());
	Rectangle bounds_up = new Rectangle(0, 0, Var.tmap.getWidth()
		* Var.tmap.getTileWidth(), 1);
	// Collision of planes with frame bounds
	if (r.player1.getHitpoints() > 0 && p1A != null) {
	    if (p1A.intersects(bounds_left) || p1A.intersects(bounds_right)
		    || p1A.intersects(bounds_up)) {
		r.player1.setHitpoints(0);
	    }
	}
	if (r.player2.getHitpoints() > 0 && p2A != null) {
	    if (p2A.intersects(bounds_left) || p2A.intersects(bounds_right)
		    || p2A.intersects(bounds_up)) {
		r.player2.setHitpoints(0);
	    }
	}
    }
}
