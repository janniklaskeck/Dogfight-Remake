package dogfight_remake.main;

import java.awt.Dimension;

import java.util.ArrayList;
import java.util.Random;

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
import dogfight_remake.entities.planes.Planes;
import dogfight_remake.entities.weapons.Homing_Missiles;
import dogfight_remake.entities.weapons.Reload;
import dogfight_remake.entities.weapons.WeaponTypes;
import dogfight_remake.entities.weapons.Weapons;
import dogfight_remake.map.Block;
import dogfight_remake.map.BlockMap;
import dogfight_remake.rendering.Render;

public class GamePlayState extends BasicGameState {

	public static Dimension dim = GlbVar.dim_chosen;

	public static final long RESPAWNTIME_PLAYER = 3000;
	public static float GRAVITY = 6;
	public static ArrayList<Weapons> weapons;
	public static ArrayList<Explosion> explosions;

	public static Rectangle p1;
	public static Rectangle p2;
	Rectangle wep;
	public Polygon p1A;
	public Polygon p2A;
	public Polygon a;
	public Polygon b;
	public static Render r;
	public Random rnd;
	public GlbVar gv;
	public static int score_p1 = 0, score_p2 = 0;
	public static long respawntimer_p1 = RESPAWNTIME_PLAYER;
	public static long respawntimer_p2 = RESPAWNTIME_PLAYER;

	public static boolean[] key_state_up = new boolean[256]; // true if pressed
	public static boolean[] key_state_down = new boolean[256]; // true if not
																// pressed
	long lastLoopTime = System.nanoTime();
	final long OPTIMAL_FPS = 59;
	final long OPTIMAL_TIME = 1000000000 / OPTIMAL_FPS;
	public static double delta;
	public static Image plane;

	int stateID = -1;

	GamePlayState(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public int getID() {
		return stateID;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		r.render(container, g);
		GlbVar.tmap.render(0, 0);

	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		container.setVSync(true);
		r = new Render();
		r.init();
		weapons = new ArrayList<Weapons>();
		explosions = new ArrayList<Explosion>();

		rnd = new Random();
		// GlbVar.music1.loop(1, GlbVar.music_volume);
		score_p1 = 0;
		score_p2 = 0;

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		KeyControls.update(container, game, delta);
		if (r.player1 != null && r.player2 != null) {
			r.player1.move(delta);
			r.player2.move(delta);
			Reload.reload_primary(r.player1);
			Reload.reload_primary(r.player2);
			Reload.reload_secondary_1(r.player1);
			Reload.reload_secondary_1(r.player2);
			Reload.reload_secondary_2(r.player1);
			Reload.reload_secondary_2(r.player2);
			checkCollision(container);
			for (int i = 0; i < weapons.size(); i++) {
				if (!weapons.get(i).isHit() && weapons.get(i).isAlive()) {
					if (weapons.get(i).getType() == WeaponTypes.GUN
							|| weapons.get(i).getType() == WeaponTypes.BOMB
							|| weapons.get(i).getType() == WeaponTypes.UNGUIDED
							|| weapons.get(i).getType() == WeaponTypes.MINIGUN) {
						weapons.get(i).move(delta);
					} else if (weapons.get(i).getType() == WeaponTypes.BOMB_SPLIT
							|| weapons.get(i).getType() == WeaponTypes.BOMB_SPLIT_SMALL) {
						if (weapons.get(i).canSplit()) {
							Weapons split1 = new Weapons(weapons.get(i)
									.getXpos(), weapons.get(i).getYpos(),
									rnd.nextFloat() * 180,
									Weapons.BOMB_SPLIT_SMALL_DAMAGE,
									WeaponTypes.BOMB_SPLIT_SMALL, weapons
											.get(i).getImage(), weapons.get(i)
											.getID());
							Weapons split2 = new Weapons(weapons.get(i)
									.getXpos(), weapons.get(i).getYpos(),
									rnd.nextFloat() * 180,
									Weapons.BOMB_SPLIT_SMALL_DAMAGE,
									WeaponTypes.BOMB_SPLIT_SMALL, weapons
											.get(i).getImage(), weapons.get(i)
											.getID());
							Weapons split3 = new Weapons(weapons.get(i)
									.getXpos(), weapons.get(i).getYpos(),
									rnd.nextFloat() * 180,
									Weapons.BOMB_SPLIT_SMALL_DAMAGE,
									WeaponTypes.BOMB_SPLIT_SMALL, weapons
											.get(i).getImage(), weapons.get(i)
											.getID());
							weapons.add(split1);
							weapons.add(split2);
							weapons.add(split3);
							weapons.add(split1);
							weapons.get(i).setSplit();
						}
						weapons.get(i).move(delta);
					} else if (weapons.get(i).getType() == WeaponTypes.GUIDED_AIR) {
						if (weapons.get(i).getID() == 1) {
							if (r.player2.getHitpoints() > 0
									&& weapons.get(i).hasTarget()) {
								Weapons temp = weapons.get(i);
								weapons.set(i, Homing_Missiles.moveMissile(
										temp, r.player1, r.player2, delta));
							} else {
								weapons.get(i).setTarget(false);
								weapons.get(i).move(delta);
							}
						} else {
							if (r.player1.getHitpoints() > 0
									&& weapons.get(i).hasTarget()) {
								Weapons temp = weapons.get(i);
								weapons.set(i, Homing_Missiles.moveMissile(
										temp, r.player1, r.player1, delta));
							} else {
								weapons.get(i).setTarget(false);
								weapons.get(i).move(delta);
							}
						}
					}
				} else {
					weapons.remove(i);
				}
			}

			if (r.player1.getHitpoints() <= 0 || r.player2.getHitpoints() <= 0) {
				respawn();
			}
			for (int i = 0; i < explosions.size(); i++) {
				explosions.get(i).move(delta);
			}
			if (r.player1 != null)
				if (r.player1.getHitpoints() <= 0) {
					respawntimer_p1 -= 18;
				}
			if (r.player2 != null)
				if (r.player2.getHitpoints() <= 0) {
					respawntimer_p2 -= 18;
				}
		}
	}

	/**
	 * Respawn method
	 */
	private void respawn() {
		if (respawntimer_p1 <= 0) {
			r.player1 = new Planes(1, 100, dim.height / 2, 0,
					GlbVar.img_player1, 100, GlbVar.wpn1_p1, GlbVar.wpn2_p1,
					GlbVar.wpn3_p1);
			respawntimer_p1 = RESPAWNTIME_PLAYER;
		}
		if (respawntimer_p2 <= 0) {
			r.player2 = new Planes(2, dim.width - 150, dim.height / 2, 180,
					GlbVar.img_player2, 100, GlbVar.wpn1_p2, GlbVar.wpn2_p2,
					GlbVar.wpn3_p2);
			respawntimer_p2 = RESPAWNTIME_PLAYER;
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
					if (tmp.getID() == 1) {
						if (p2A.intersects(wep) && r.player2.getHitpoints() > 0) {
							explosions.add(new Explosion(tmp.getXpos(), tmp
									.getYpos(), tmp.getType().getExploSize()));
							GlbVar.hit.play(1, GlbVar.sounds_volume);
							tmp.setHit();
							r.player2.decreaseHitpoints(tmp.getDamage());
						}
					}
				} else if (p1A != null) {
					if (tmp.getID() == 2) {
						if (p1A.intersects(wep) && r.player1.getHitpoints() > 0) {
							explosions.add(new Explosion(tmp.getXpos(), tmp
									.getYpos(), tmp.getType().getExploSize()));
							GlbVar.hit.play(1, GlbVar.sounds_volume);
							tmp.setHit();
							r.player1.decreaseHitpoints(tmp.getDamage());
						}
					}
				}
			}
		}

		if (p1A != null) {
			if (r.player1.getHitpoints() <= 0
					&& respawntimer_p1 >= RESPAWNTIME_PLAYER) {
				explosions.add(new Explosion(r.player1.getXpos(), r.player1
						.getYpos(), 4));
				p1A = null;
				score_p2++;
				GlbVar.explode.play(1, GlbVar.sounds_volume);
			}
		}
		if (p2A != null) {
			if (r.player2.getHitpoints() <= 0
					&& respawntimer_p2 >= RESPAWNTIME_PLAYER) {
				explosions.add(new Explosion(r.player2.getXpos(), r.player2
						.getYpos(), 4));
				p2A = null;
				score_p1++;
				GlbVar.explode.play(1, GlbVar.sounds_volume);
			}
		}
		if (p1A != null && p2A != null && GlbVar.getPlayerCollision()) {
			if (p1A.intersects(p2A) && respawntimer_p1 >= RESPAWNTIME_PLAYER && respawntimer_p2 >= RESPAWNTIME_PLAYER) {
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
				if (r.player1.getPlane().intersects(entity1.poly) && respawntimer_p1 >= RESPAWNTIME_PLAYER) {
					explosions.add(new Explosion(r.player1.getXpos(), r.player1
							.getYpos(), 4));
					p1A = null;
					GlbVar.explode.play(1, GlbVar.sounds_volume);
					r.player1.setHitpoints(0);
				}
				if (r.player2.getPlane().intersects(entity1.poly) && respawntimer_p2 >= RESPAWNTIME_PLAYER) {
					explosions.add(new Explosion(r.player2.getXpos(), r.player2
							.getYpos(), 4));
					p2A = null;
					GlbVar.explode.play(1, GlbVar.sounds_volume);
					r.player2.setHitpoints(0);
				}
			}
		}

		Rectangle bounds_left = new Rectangle(0, 0, 1, gc.getHeight());
		Rectangle bounds_right = new Rectangle(gc.getWidth() - 1, 0, 1,
				gc.getHeight());
		Rectangle bounds_up = new Rectangle(0, 0, gc.getWidth(), 1);
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
