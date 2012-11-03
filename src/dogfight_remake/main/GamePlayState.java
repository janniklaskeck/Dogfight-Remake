package dogfight_remake.main;

import java.awt.Dimension;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import dogfight_remake.controls.KeyControls;
import dogfight_remake.entities.weapons.Reload;
import dogfight_remake.entities.weapons.WeaponTypes_Primary;
import dogfight_remake.entities.weapons.WeaponTypes_Secondary;
import dogfight_remake.entities.weapons.Weapons;
import dogfight_remake.entities.planes.PlaneTypes_Gen5;
import dogfight_remake.rendering.Effects;
import dogfight_remake.rendering.Render;

public class GamePlayState extends BasicGameState {
    public static Dimension dim = Var.dim_chosen;

    public static ArrayList<Weapons> weapons;
    private Rectangle turret;
    private Rectangle wep;
    public static Render r;

    public static Camera camera;
    public static Camera camera2;
    private int time;
    public static Effects ef;

    int stateID = -1;
    private int delta = 0;

    int MaxMax;
    int MinMin;
    int MaxMin;
    int MinMax;

    public GamePlayState(int stateID) {
	this.stateID = stateID;
    }

    @Override
    public int getID() {
	return stateID;
    }

    public static ParticleSystem ps;
    public static ConfigurableEmitter pe;

    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) {
	gc.setVSync(true);
	gc.setShowFPS(false);
	gc.setSmoothDeltas(true);
	gc.setTargetFrameRate(60);
	if (Var.paused) {
	    r = new Render();
	    try {
		r.init();
	    } catch (SlickException e) {
		e.printStackTrace();
	    }
	    weapons = new ArrayList<Weapons>();
	    camera = new Camera(gc, Var.tmap);
	    camera2 = new Camera(gc, Var.tmap);
	    try {
		ef = new Effects();
	    } catch (SlickException e) {
		System.out.println("ERROR WHILE CREATING EFFECTS INSTANCE");
		e.printStackTrace();
	    }
	    // GlbVar.music1.loop(1, GlbVar.music_volume);
	    Var.score_p1 = 0;
	    Var.score_p2 = 0;
	    Var.paused = false;
	    Var.timePassed_sec = 0;
	    time = 0;
	}

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
	    throws SlickException {
	r.render(gc, g, delta);
	if (Var.paused) {
	    Var.img_pause_bg = new Image(1680, 1050);
	    g.copyArea(Var.img_pause_bg, 0, 0);
	    sbg.enterState(Dogfight_Remake.PAUSEDSTATE);
	}
    }

    @Override
    public void init(GameContainer gc, StateBasedGame game)
	    throws SlickException {
	WeaponTypes_Primary.MM30S.init();
	WeaponTypes_Secondary.BOMB.init();
	PlaneTypes_Gen5.NORMAL.init();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
	    throws SlickException {
	ef.update(delta);
	this.delta = delta;
	KeyControls.update(gc, sbg, delta);
	if (r.player1 != null && r.player2 != null && !Var.paused) {
	    time += delta;
	    Var.timePassed_sec = time / 1000;
	    if (Var.timePassed_sec == 60) {
		Var.timePassed_sec = 0;
		time = 0;
		Var.timePassed_min += 1;
	    }
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
		    if (weapons.get(i).getType() == WeaponTypes_Secondary.GUIDED_AIR
			    || weapons.get(i).getType() == WeaponTypes_Secondary.AIM9L) {
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
	int w = Var.tmap.getTileWidth() * Var.tmap.getWidth();
	int h = Var.tmap.getTileHeight() * Var.tmap.getHeight();
	turret = new Rectangle((int) r.turret.getXpos(),
		(int) r.turret.getYpos(), Var.img_turret_base.getWidth(),
		Var.img_turret_base.getHeight());

	for (int i = 0; i < weapons.size(); i++) {
	    Weapons tmp = weapons.get(i);
	    wep = new Rectangle((int) tmp.getXpos(), (int) tmp.getYpos(),
		    (int) tmp.getWidth(), (int) tmp.getHeight());
	    if (weapons.size() > 0) {
		if (wep.getMaxX() < w && wep.getMaxX() > 0 && wep.getMaxY() < h
			&& wep.getMaxY() > 0 && wep.getMinX() < w
			&& wep.getMinX() > 0 && wep.getMinY() < h
			&& wep.getMinY() > 0) {
		    MaxMax = Var.tmap.getTileId(
			    (int) wep.getMaxX() / Var.tmap.getTileWidth(),
			    (int) wep.getMaxY() / Var.tmap.getTileHeight(), 0);
		    MinMin = Var.tmap.getTileId(
			    (int) wep.getMinX() / Var.tmap.getTileWidth(),
			    (int) wep.getMinY() / Var.tmap.getTileHeight(), 0);
		    MaxMin = Var.tmap.getTileId(
			    (int) wep.getMaxX() / Var.tmap.getTileWidth(),
			    (int) wep.getMinY() / Var.tmap.getTileHeight(), 0);
		    MinMax = Var.tmap.getTileId(
			    (int) wep.getMinX() / Var.tmap.getTileWidth(),
			    (int) wep.getMaxY() / Var.tmap.getTileHeight(), 0);
		    if (MaxMax != 0 || MinMin != 0 || MaxMin != 0
			    || MinMax != 0) {
			tmp.setHitTarget();
		    }
		} else {
		    tmp.setHitTarget();
		}
		if (r.player2.getCollision() != null) {
		    if (tmp.getID() != 2) {
			if (r.player2.getCollision().intersects(wep)
				&& r.player2.getHitpoints() > 0
				&& !tmp.isHitTarget()) {
			    tmp.setHitTarget();
			    r.player2.decreaseHitpoints(tmp.getDamage());
			}
		    }
		}
		if (r.player1.getCollision() != null) {
		    if (tmp.getID() != 1) {
			if (r.player1.getCollision().intersects(wep)
				&& r.player1.getHitpoints() > 0
				&& !tmp.isHitTarget()) {
			    tmp.setHitTarget();
			    r.player1.decreaseHitpoints(tmp.getDamage());
			    /*
			     * int x = (int) Math.abs(r.player1.getXpos() -
			     * tmp.getXpos()); int y = (int)
			     * Math.abs(r.player1.getYpos() - tmp.getYpos());
			     * 
			     * Color c = new Color(255, 255, 255, 255); if (x <
			     * Var.plane_collis_generic.getWidth() - 1 && y <
			     * Var.plane_collis_generic.getHeight() - 1) { if
			     * (Var.plane_collis_generic.getColor(x, y)
			     * .equals(c)) { tmp.setHitTarget(); r.player1
			     * .decreaseHitpoints(tmp.getDamage()); } }
			     */
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

	if (r.player1.getCollision() != null) {
	    if (r.player1.getHitpoints() <= 0
		    && r.player1.getRespawn_timer() >= Var.RESPAWNTIME_PLAYER) {
		r.player1.setCollision(null);
		Var.score_p2++;
	    }
	}
	if (r.player2.getCollision() != null) {
	    if (r.player2.getHitpoints() <= 0
		    && r.player2.getRespawn_timer() >= Var.RESPAWNTIME_PLAYER) {
		r.player2.setCollision(null);
		Var.score_p1++;
	    }
	}
	if (turret != null) {
	    if (r.turret.getHitpoints() <= 0
		    && r.turret.getRespawn_timer() >= Var.RESPAWNTIME_TURRET) {
		turret = null;
	    }
	}
	if (r.player1.getCollision() != null
		&& r.player2.getCollision() != null && Var.getPlayerCollision()) {
	    if (r.player1.getCollision().intersects(r.player2.getCollision())
		    && r.player1.getRespawn_timer() >= Var.RESPAWNTIME_PLAYER
		    && r.player2.getRespawn_timer() >= Var.RESPAWNTIME_PLAYER) {
		r.player1.setHitpoints(0);
		r.player2.setHitpoints(0);
		r.player1.setCollision(null);
		r.player2.setCollision(null);
	    }
	}

	if (r.player1.getCollision() != null) {
	    if (r.player1.getCollision().getMaxX() < w
		    && r.player1.getCollision().getMaxX() > 0
		    && r.player1.getCollision().getMaxY() < h
		    && r.player1.getCollision().getMaxY() > 0
		    && r.player1.getCollision().getMinX() < w
		    && r.player1.getCollision().getMinX() > 0
		    && r.player1.getCollision().getMinY() < h
		    && r.player1.getCollision().getMinY() > 0) {

		MaxMax = Var.tmap
			.getTileId((int) r.player1.getCollision().getMaxX()
				/ Var.tmap.getTileWidth(),
				(int) r.player1.getCollision().getMaxY()
					/ Var.tmap.getTileHeight(), 0);
		MinMin = Var.tmap
			.getTileId((int) r.player1.getCollision().getMinX()
				/ Var.tmap.getTileWidth(),
				(int) r.player1.getCollision().getMinY()
					/ Var.tmap.getTileHeight(), 0);
		MaxMin = Var.tmap
			.getTileId((int) r.player1.getCollision().getMaxX()
				/ Var.tmap.getTileWidth(),
				(int) r.player1.getCollision().getMinY()
					/ Var.tmap.getTileHeight(), 0);
		MinMax = Var.tmap
			.getTileId((int) r.player1.getCollision().getMinX()
				/ Var.tmap.getTileWidth(),
				(int) r.player1.getCollision().getMaxY()
					/ Var.tmap.getTileHeight(), 0);
		if (MaxMax != 0 || MinMin != 0 || MaxMin != 0 || MinMax != 0) {
		    r.player1.setHitpoints(0);
		    r.player1.setCollision(null);
		}
	    } else {
		r.player1.setHitpoints(0);
		r.player1.setCollision(null);
	    }
	}
	if (r.player2.getCollision() != null) {
	    if (r.player2.getCollision().getMaxX() < w
		    && r.player2.getCollision().getMaxX() > 0
		    && r.player2.getCollision().getMaxY() < h
		    && r.player2.getCollision().getMaxY() > 0
		    && r.player2.getCollision().getMinX() < w
		    && r.player2.getCollision().getMinX() > 0
		    && r.player2.getCollision().getMinY() < h
		    && r.player2.getCollision().getMinY() > 0) {
		MaxMax = Var.tmap
			.getTileId((int) r.player2.getCollision().getMaxX()
				/ Var.tmap.getTileWidth(),
				(int) r.player2.getCollision().getMaxY()
					/ Var.tmap.getTileHeight(), 0);
		MinMin = Var.tmap
			.getTileId((int) r.player2.getCollision().getMinX()
				/ Var.tmap.getTileWidth(),
				(int) r.player2.getCollision().getMinY()
					/ Var.tmap.getTileHeight(), 0);
		MaxMin = Var.tmap
			.getTileId((int) r.player2.getCollision().getMaxX()
				/ Var.tmap.getTileWidth(),
				(int) r.player2.getCollision().getMinY()
					/ Var.tmap.getTileHeight(), 0);
		MinMax = Var.tmap
			.getTileId((int) r.player2.getCollision().getMinX()
				/ Var.tmap.getTileWidth(),
				(int) r.player2.getCollision().getMaxY()
					/ Var.tmap.getTileHeight(), 0);
		if (MaxMax != 0 || MinMin != 0 || MaxMin != 0 || MinMax != 0) {
		    r.player2.setHitpoints(0);
		    r.player2.setCollision(null);
		}

	    } else {
		r.player2.setHitpoints(0);
		r.player2.setCollision(null);
	    }
	}
    }
}
