package dogfight_remake.main;

import java.awt.Dimension;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import dogfight_remake.controls.KeyControls;
import dogfight_remake.entities.Explosion;
import dogfight_remake.entities.ai.TurretAi;
import dogfight_remake.entities.planes.Planes;
import dogfight_remake.entities.weapons.Reload;
import dogfight_remake.entities.weapons.WeaponTypes;
import dogfight_remake.entities.weapons.Weapons;
import dogfight_remake.map.Block;
import dogfight_remake.map.BlockMap;
import dogfight_remake.rendering.Render;

public class GamePlayState extends BasicGameState {
	public static Dimension dim = GlbVar.dim_chosen;

	public static ArrayList<Weapons> weapons;
	public static ArrayList<Explosion> explosions;

	private static Rectangle p1;
	private static Rectangle p2;
	private Rectangle turret;
	private Rectangle wep;
	private Ellipse wep_ellipse;
	private Polygon p1A;
	private Polygon p2A;
	private Polygon a;
	private Polygon b;
	public static Render r;
	private Random rnd;

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
		if (GlbVar.paused) {
			r = new Render();
			try {
				r.init();
			} catch (SlickException e) {
				e.printStackTrace();
			}
			weapons = new ArrayList<Weapons>();
			explosions = new ArrayList<Explosion>();
			camera = new Camera(gc, GlbVar.tmap);
			camera2 = new Camera(gc, GlbVar.tmap);
			rnd = new Random();
			//GlbVar.music1.loop(1, GlbVar.music_volume);
			GlbVar.score_p1 = 0;
			GlbVar.score_p2 = 0;
			GlbVar.paused = false;
		}

	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g)
			throws SlickException {

		r.render(gc, g, delta);

		if (GlbVar.paused) {
			GlbVar.img_pause_bg = new Image(1680, 1050);
			g.copyArea(GlbVar.img_pause_bg, 0, 0);
			game.enterState(Dogfight_Remake.PAUSEDSTATE);
		}
	}

	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		this.delta = delta;
		KeyControls.update(gc, sbg, delta);
		if (r.player1 != null && r.player2 != null && !GlbVar.paused) {
			time += delta;
			GlbVar.timePassed = time / 1000;

			Weapons wmp = r.turret.shoot();
			if (wmp != null) {
				GlbVar.prim_gun_middle.play(1, GlbVar.sounds_volume);
				weapons.add(wmp);
			}
			r.player1.update(delta);
			r.player2.update(delta);
			Reload.reload_primary(r.player1, delta);
			Reload.reload_primary(r.player2, delta);
			Reload.reload_secondary_1(r.player1, delta);
			Reload.reload_secondary_1(r.player2, delta);
			Reload.reload_secondary_2(r.player1, delta);
			Reload.reload_secondary_2(r.player2, delta);
			r.turret.update(delta);
			checkCollision(gc);
			for (int i = 0; i < weapons.size(); i++) {
				if (!weapons.get(i).isHit() && weapons.get(i).isAlive()) {
					if (weapons.get(i).getType() == WeaponTypes.GUN
							|| weapons.get(i).getType() == WeaponTypes.BOMB
							|| weapons.get(i).getType() == WeaponTypes.UNGUIDED
							|| weapons.get(i).getType() == WeaponTypes.MINIGUN
							|| weapons.get(i).getType() == WeaponTypes.TURRET_MIDDLE) {
						weapons.get(i).update(delta);
					} else if (weapons.get(i).getType() == WeaponTypes.BOMB_SPLIT
							|| weapons.get(i).getType() == WeaponTypes.BOMB_SPLIT_SMALL) {
						if (weapons.get(i).canSplit()) {
							Weapons split1 = new Weapons(weapons.get(i)
									.getXpos(), weapons.get(i).getYpos(),
									rnd.nextFloat() * 180,
									WeaponTypes.BOMB_SPLIT_SMALL, 0, weapons
											.get(i).getImage(), weapons.get(i)
											.getID());
							Weapons split2 = new Weapons(weapons.get(i)
									.getXpos(), weapons.get(i).getYpos(),
									rnd.nextFloat() * 180,
									WeaponTypes.BOMB_SPLIT_SMALL, 0, weapons
											.get(i).getImage(), weapons.get(i)
											.getID());
							Weapons split3 = new Weapons(weapons.get(i)
									.getXpos(), weapons.get(i).getYpos(),
									rnd.nextFloat() * 180,
									WeaponTypes.BOMB_SPLIT_SMALL, 0, weapons
											.get(i).getImage(), weapons.get(i)
											.getID());
							weapons.add(split1);
							weapons.add(split2);
							weapons.add(split3);
							weapons.add(split1);
							weapons.get(i).setSplit();
						}
						weapons.get(i).update(delta);
					} else if (weapons.get(i).getType() == WeaponTypes.GUIDED_AIR) {
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
					} else if (weapons.get(i).getType() == WeaponTypes.GUIDED_GROUND) {
						if (r.turret.getHitpoints() > 0
								&& weapons.get(i).hasTarget()) {
							weapons.get(i).update(r.player1, r.turret, delta);
						} else {
							weapons.get(i).setTarget(false);
							weapons.get(i).update(delta);
						}
					}
				} else {
					weapons.remove(i);
				}
			}
			if (r.player1.getHitpoints() <= 0 || r.player2.getHitpoints() <= 0
					|| r.turret.getHitpoints() <= 0) {
				respawn();
			}
			for (int i = 0; i < explosions.size(); i++)
				explosions.get(i).update(delta);
			if (r.player1 != null) {
				if (r.player1.getHitpoints() <= 0) {
					GlbVar.respawntimer_p1 -= delta;
				}
			}
			if (r.player2 != null) {
				if (r.player2.getHitpoints() <= 0) {
					GlbVar.respawntimer_p2 -= delta;
				}
			}
			if (r.turret != null)
				if (r.turret.getHitpoints() <= 0) {
					GlbVar.respawntimer_turret -= delta;
				}
		}

		camera.centerOn(r.player1.getXpos(), r.player1.getYpos());
		camera2.centerOn(r.player2.getXpos(), r.player2.getYpos());
	}

	/**
	 * Respawn method
	 */
	private void respawn() {
		if (GlbVar.respawntimer_p1 <= 0) {
			r.player1 = new Planes(1, 100, dim.height / 2, 0,
					GlbVar.img_player1, GlbVar.player1, GlbVar.wpn1_p1,
					GlbVar.wpn2_p1, GlbVar.wpn3_p1);

			GlbVar.respawntimer_p1 = GlbVar.RESPAWNTIME_PLAYER;
		}
		if (GlbVar.respawntimer_p2 <= 0) {
			r.player2 = new Planes(2, dim.width - 150, dim.height / 2, 180,
					GlbVar.img_player2, GlbVar.player2, GlbVar.wpn1_p2,
					GlbVar.wpn2_p2, GlbVar.wpn3_p2);
			GlbVar.respawntimer_p2 = GlbVar.RESPAWNTIME_PLAYER;
		}
		if (GlbVar.respawntimer_turret <= 0) {
			r.turret = new TurretAi(3, 815, 2520, 270, 100, null,
					WeaponTypes.TURRET_MIDDLE, GlbVar.img_bullet1);
			GlbVar.respawntimer_turret = GlbVar.RESPAWNTIME_TURRET;
		}
	}

	/**
	 * Check for Collision (plane X plane or plane X weapon)
	 */

	private void checkCollision(GameContainer gc) {
		/**
		 * WIP: -Plane to Weapon -Weapon to Map
		 * 
		 */

		p1 = new Rectangle((int) r.player1.getXpos(),
				(int) r.player1.getYpos(), GlbVar.img_player1.getWidth(),
				GlbVar.img_player1.getHeight());
		p2 = new Rectangle((int) r.player2.getXpos(),
				(int) r.player2.getYpos(), GlbVar.img_player2.getWidth(),
				GlbVar.img_player2.getHeight());
		a = new Polygon(new float[] { p1.getX(), p1.getY(),
				p1.getX() + p1.getWidth(), p1.getY(),
				p1.getX() + p1.getWidth(), p1.getY() + p1.getHeight(),
				p1.getX(), p1.getY() + p1.getHeight() }); // Player 1 Area
		b = new Polygon(new float[] { p2.getX(), p2.getY(),
				p2.getX() + p2.getWidth(), p2.getY(),
				p2.getX() + p2.getWidth(), p2.getY() + p2.getHeight(),
				p2.getX(), p2.getY() + p2.getHeight() }); // Player 2 Area
		turret = new Rectangle((int) r.turret.getXpos(),
				(int) r.turret.getYpos(), GlbVar.img_turret_base.getWidth(),
				GlbVar.img_turret_base.getHeight());
		if (r.player1.getHitpoints() > 0) {
			if (r.player1.getAim() != null) {
				float deltaX = r.player1.getCenterX()
						- r.player1.getAim().getCenterX();
				float deltaY = r.player1.getCenterY()
						- r.player1.getAim().getCenterY();
				p1A = (Polygon) a.transform(Transform.createRotateTransform(
						(float) Math.atan2(deltaY, deltaX),
						r.player1.getCenterX(), r.player1.getCenterY()));
			}
		}

		if (r.player2.getHitpoints() > 0) {
			if (r.player2.getAim() != null) {
				float deltaX = r.player2.getCenterX()
						- r.player2.getAim().getCenterX();
				float deltaY = r.player2.getCenterY()
						- r.player2.getAim().getCenterY();
				p2A = (Polygon) b.transform(Transform.createRotateTransform(
						(float) Math.atan2(deltaY, deltaX),
						r.player2.getCenterX(), r.player2.getCenterY()));
			}
		}

		for (int i = 0; i < weapons.size(); i++) {
			Weapons tmp = weapons.get(i);

			wep = new Rectangle((int) tmp.getXpos(), (int) tmp.getYpos(),
					(int) tmp.getWidth(), (int) tmp.getHeight());
			wep_ellipse = new Ellipse(wep.getCenterX(), wep.getCenterY(),
					Explosion.MAX_RADIUS * tmp.getType().getExploSize(),
					Explosion.MAX_RADIUS * tmp.getType().getExploSize());
			if (weapons.size() > 0) {
				for (int o = 0; o < BlockMap.entities.size(); o++) {
					Block entity1 = (Block) BlockMap.entities.get(o);
					if (wep.intersects(entity1.poly)) {
						explosions.add(new Explosion(tmp.getXpos(), tmp
								.getYpos(), tmp.getType().getExploSize()));
						tmp.setHit();
					}
				}

				if (p2A != null) {
					if (tmp.getID() == 1 || tmp.getID() == 3) {
						if (p2A.intersects(wep) && r.player2.getHitpoints() > 0
								&& !tmp.isHit()) {
							explosions.add(new Explosion(tmp.getXpos(), tmp
									.getYpos(), tmp.getType().getExploSize()));
							GlbVar.hit.play(1, GlbVar.sounds_volume);
							tmp.setHit();
							r.player2.decreaseHitpoints(tmp.getDamage());
						}
						if (p2A.intersects(wep_ellipse)
								&& r.player2.getHitpoints() > 0 && tmp.isHit()) {
							explosions.add(new Explosion(tmp.getXpos(), tmp
									.getYpos(), tmp.getType().getExploSize()));
							GlbVar.hit.play(1, GlbVar.sounds_volume);
							r.player2.decreaseHitpoints(tmp.getDamage() / 3);
						}
					}
				}
				if (p1A != null) {
					if (tmp.getID() == 2 || tmp.getID() == 3) {
						if (p1A.intersects(wep) && r.player1.getHitpoints() > 0
								&& !tmp.isHit()) {
							explosions.add(new Explosion(tmp.getXpos(), tmp
									.getYpos(), tmp.getType().getExploSize()));
							GlbVar.hit.play(1, GlbVar.sounds_volume);
							tmp.setHit();
							r.player1.decreaseHitpoints(tmp.getDamage());
						}
						if (p1A.intersects(wep_ellipse)
								&& r.player1.getHitpoints() > 0 && tmp.isHit()) {
							explosions.add(new Explosion(tmp.getXpos(), tmp
									.getYpos(), tmp.getType().getExploSize()));
							GlbVar.hit.play(1, GlbVar.sounds_volume);
							r.player1.decreaseHitpoints(tmp.getDamage() / 3);
						}
					}
				}
				if (turret != null) {
					if (tmp.getID() == 1 || tmp.getID() == 2) {
						if (turret.intersects(wep)
								&& r.turret.getHitpoints() > 0 && !tmp.isHit()) {
							explosions.add(new Explosion(tmp.getXpos(), tmp
									.getYpos(), tmp.getType().getExploSize()));
							GlbVar.hit.play(1, GlbVar.sounds_volume);
							tmp.setHit();
							r.turret.decreaseHitpoints(tmp.getDamage());
						}
						if (turret.intersects(wep_ellipse)
								&& r.turret.getHitpoints() > 0 && tmp.isHit()) {
							explosions.add(new Explosion(tmp.getXpos(), tmp
									.getYpos(), tmp.getType().getExploSize()));
							GlbVar.hit.play(1, GlbVar.sounds_volume);
							r.turret.decreaseHitpoints(tmp.getDamage() / 3);
						}
					}
				}
			}
		}

		if (p1A != null) {
			if (r.player1.getHitpoints() <= 0
					&& GlbVar.respawntimer_p1 >= GlbVar.RESPAWNTIME_PLAYER) {
				explosions.add(new Explosion(r.player1.getXpos(), r.player1
						.getYpos(), 4));
				p1A = null;
				GlbVar.score_p2++;
				GlbVar.explode.play(1, GlbVar.sounds_volume);
			}
		}
		if (p2A != null) {
			if (r.player2.getHitpoints() <= 0
					&& GlbVar.respawntimer_p2 >= GlbVar.RESPAWNTIME_PLAYER) {
				explosions.add(new Explosion(r.player2.getXpos(), r.player2
						.getYpos(), 4));
				p2A = null;
				GlbVar.score_p1++;
				GlbVar.explode.play(1, GlbVar.sounds_volume);
			}
		}
		if (turret != null) {
			if (r.turret.getHitpoints() <= 0
					&& GlbVar.respawntimer_turret >= GlbVar.RESPAWNTIME_TURRET) {
				explosions.add(new Explosion(r.turret.getXpos(), r.turret
						.getYpos(), 4));
				r.turret.setHit();
				turret = null;
				GlbVar.explode.play(1, GlbVar.sounds_volume);
			}
		}
		if (p1A != null && p2A != null && GlbVar.getPlayerCollision()) {
			if (p1A.intersects(p2A)
					&& GlbVar.respawntimer_p1 >= GlbVar.RESPAWNTIME_PLAYER
					&& GlbVar.respawntimer_p2 >= GlbVar.RESPAWNTIME_PLAYER) {
				explosions.add(new Explosion(r.player1.getXpos(), r.player1
						.getYpos(), 4));
				explosions.add(new Explosion(r.player2.getXpos(), r.player2
						.getYpos(), 4));
				p1A = null;
				p2A = null;
				GlbVar.explode.play(1, GlbVar.sounds_volume);
				r.player1.setHitpoints(0);
				r.player2.setHitpoints(0);
			}
		}
		if (r.player1.getPlane() != null && r.player2.getPlane() != null) {
			for (int i = 0; i < BlockMap.entities.size(); i++) {
				Block entity1 = (Block) BlockMap.entities.get(i);
				if (r.player1.getPlane().intersects(entity1.poly)
						&& GlbVar.respawntimer_p1 >= GlbVar.RESPAWNTIME_PLAYER) {
					explosions.add(new Explosion(r.player1.getXpos(), r.player1
							.getYpos(), 4));
					p1A = null;
					GlbVar.explode.play(1, GlbVar.sounds_volume);
					r.player1.setHitpoints(0);
				}
				if (r.player2.getPlane().intersects(entity1.poly)
						&& GlbVar.respawntimer_p2 >= GlbVar.RESPAWNTIME_PLAYER) {
					explosions.add(new Explosion(r.player2.getXpos(), r.player2
							.getYpos(), 4));
					p2A = null;
					GlbVar.explode.play(1, GlbVar.sounds_volume);
					r.player2.setHitpoints(0);
				}
			}
		}

		Rectangle bounds_left = new Rectangle(0, 0, 1, GlbVar.tmap.getHeight());
		Rectangle bounds_right = new Rectangle(GlbVar.tmap.getWidth() - 1, 0,
				1, GlbVar.tmap.getHeight());
		Rectangle bounds_up = new Rectangle(0, 0, GlbVar.tmap.getWidth(), 1);
		// Collision of planes with frame bounds
		if (r.player1.getHitpoints() > 0 && p1A != null) {
			if (p1A.intersects(bounds_left) || p1A.intersects(bounds_right)
					|| p1A.intersects(bounds_up)) {
				r.player1.setHitpoints(0);
				GlbVar.explode.play(1, GlbVar.sounds_volume);
				explosions.add(new Explosion(r.player1.getXpos(), r.player1
						.getYpos(), 2));
			}
		}
		if (r.player2.getHitpoints() > 0 && p2A != null) {
			if (p2A.intersects(bounds_left) || p2A.intersects(bounds_right)
					|| p2A.intersects(bounds_up)) {
				r.player2.setHitpoints(0);
				GlbVar.explode.play(1, GlbVar.sounds_volume);
				explosions.add(new Explosion(r.player2.getXpos(), r.player2
						.getYpos(), 2));
			}
		}
	}
}
