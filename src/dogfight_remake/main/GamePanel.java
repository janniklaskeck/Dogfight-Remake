package dogfight_remake.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import dogfight_remake.controls.KeyInput;
import dogfight_remake.entities.Explosion;
import dogfight_remake.entities.Planes;
import dogfight_remake.entities.weapons.Homing_Missiles;
import dogfight_remake.entities.weapons.Reload;
import dogfight_remake.entities.weapons.WeaponTypes;
import dogfight_remake.entities.weapons.Weapons;
import dogfight_remake.rendering.Render;
import dogfight_remake.sound.Sounds;
import dogfight_remake.ui.MainMenu;

public class GamePanel extends JPanel implements Runnable {
	public static Dimension dim = GlbVar.dim_chosen;

	public static final long RESPAWNTIME_PLAYER = 3000;
	public static double GRAVITY = 6;
	public static ArrayList<Weapons> weapons;
	public static ArrayList<Explosion> explosions;

	public static Rectangle p1;
	public static Rectangle p2;
	private Rectangle map, bounds_left, bounds_right, bounds_top;

	public static Area p1A;
	public Area p2A;
	public Area a;
	public Area b;

	private KeyInput ki;
	public static Render r;
	public static Sounds snd;
	public Random rnd;
	public GlbVar gv;
	public static States state = States.PLAYING;
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

	private static final long serialVersionUID = 1L;

	public static JPanel content;

	public GamePanel() {
		init();
		Thread th = new Thread(this);
		th.start();
		blinker = th;

	}

	/**
	 * Initialize everything
	 */
	public void init() {
		state = States.PLAYING;
		r = new Render();
		GlbVar.setWeapons();
		
		r.init();
		snd = new Sounds();
		weapons = new ArrayList<Weapons>();
		explosions = new ArrayList<Explosion>();
		content = this;
		ki = new KeyInput();
		rnd = new Random();
		GlbVar.music_volume = (float) MainMenu.slider_music.getValue() / 100;
		GlbVar.sounds_volume = (float) MainMenu.slider_sounds.getValue() / 100;
		snd.music1.loop(1, GlbVar.music_volume);
		score_p1 = 0; 
		score_p2 = 0;
	}

	/**
	 * PaintComponent override
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (r != null) {
			r.paintComponent(g);
		}
	}

	private volatile static Thread blinker;

	public static void stop() {
		blinker = null;
	}

	@Override
	public void run() {
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
		Thread thisThread = Thread.currentThread();
		while (blinker == thisThread) {
			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			delta = updateLength / ((double) OPTIMAL_TIME);
			// GameStateManager();
			input();
			if (state == States.PLAYING) {
				if (r.player1 != null && r.player2 != null) {
					r.player1.move(delta);
					r.player2.move(delta);
					Reload.reload_primary(r.player1);
					Reload.reload_primary(r.player2);
					Reload.reload_secondary_1(r.player1);
					Reload.reload_secondary_1(r.player2);
					Reload.reload_secondary_2(r.player1);
					Reload.reload_secondary_2(r.player2);
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
											.getXpos(), weapons.get(i)
											.getYpos(), rnd.nextDouble() * 180,
											Weapons.BOMB_SPLIT_SMALL_DAMAGE,
											WeaponTypes.BOMB_SPLIT_SMALL,
											weapons.get(i).getImage(), weapons
													.get(i).getID());
									Weapons split2 = new Weapons(weapons.get(i)
											.getXpos(), weapons.get(i)
											.getYpos(), rnd.nextDouble() * 180,
											Weapons.BOMB_SPLIT_SMALL_DAMAGE,
											WeaponTypes.BOMB_SPLIT_SMALL,
											weapons.get(i).getImage(), weapons
													.get(i).getID());
									Weapons split3 = new Weapons(weapons.get(i)
											.getXpos(), weapons.get(i)
											.getYpos(), rnd.nextDouble() * 180,
											Weapons.BOMB_SPLIT_SMALL_DAMAGE,
											WeaponTypes.BOMB_SPLIT_SMALL,
											weapons.get(i).getImage(), weapons
													.get(i).getID());
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
										weapons.set(i, Homing_Missiles
												.moveMissile(temp, r.player1,
														r.player2, delta));
									} else {
										weapons.get(i).setTarget(false);
										weapons.get(i).move(delta);
									}
								} else {
									if (r.player1.getHitpoints() > 0
											&& weapons.get(i).hasTarget()) {
										Weapons temp = weapons.get(i);
										weapons.set(i, Homing_Missiles
												.moveMissile(temp, r.player1,
														r.player1, delta));
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
					checkCollision();
					if (r.player1.getHitpoints() <= 0
							|| r.player2.getHitpoints() <= 0) {
						respawn();
					}
					for (int i = 0; i < explosions.size(); i++) {
						explosions.get(i).move(delta);
					}
				}
			}
			repaint();
			try {

				long looptime = (((lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000));
				// if (r.fps < 35) {
				// looptime -= 5;
				// }

				if (looptime > 0) {
					Thread.sleep(looptime);
				}
				if (r.player1 != null)
					if (r.player1.getHitpoints() <= 0
							&& state == States.PLAYING) {
						respawntimer_p1 -= 18;
					}
				if (r.player2 != null)
					if (r.player2.getHitpoints() <= 0
							&& state == States.PLAYING) {
						respawntimer_p2 -= 18;
					}
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Fehler 01");
				return;
			}
			Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		}
	}

	public static enum States {
		PAUSED, PLAYING, MAINMENU, PLANEMENU,
	}

	public void GameStateManager() {

	}

	/**
	 * Keyboard input method
	 */
	private void input() {
		ki.controls();
	}

	/**
	 * Respawn method
	 */
	private void respawn() {
		if (respawntimer_p1 <= 0) {
			r.player1 = new Planes(1, 100, dim.height / 2, 0, r.img_player1,
					100, GlbVar.wpn1_p1, GlbVar.wpn2_p1, GlbVar.wpn3_p1);
			respawntimer_p1 = RESPAWNTIME_PLAYER;
		}
		if (respawntimer_p2 <= 0) {
			r.player2 = new Planes(2, dim.width - 150, dim.height / 2, 180,
					r.img_player2, 100, GlbVar.wpn1_p2, GlbVar.wpn2_p2,
					GlbVar.wpn3_p2);
			respawntimer_p2 = RESPAWNTIME_PLAYER;
		}
	}

	/**
	 * Check for Collision (plane X plane or plane X weapon)
	 */
	private void checkCollision() {
		p1 = new Rectangle((int) r.player1.getXpos(),
				(int) r.player1.getYpos(), r.player1.getImage().getWidth(null),
				r.player1.getImage().getHeight(null));
		p2 = new Rectangle((int) r.player2.getXpos(),
				(int) r.player2.getYpos(), r.player2.getImage().getWidth(null),
				r.player2.getImage().getHeight(null));
		AffineTransform p1f = new AffineTransform();
		p1f.rotate(Math.toRadians(r.player1.getAngle()),
				r.player1.getCenterX(), r.player1.getCenterY());
		AffineTransform p2f = new AffineTransform();
		p2f.rotate(Math.toRadians(r.player2.getAngle()),
				r.player2.getCenterX(), r.player2.getCenterY());
		a = new Area(p1); // Player 1 Area
		b = new Area(p2); // Player 2 Area

		if (r.player1.getHitpoints() > 0) {
			p1A = a.createTransformedArea(p1f);// p1r is the rotated a, a is
												// unchanged
		}
		if (r.player2.getHitpoints() > 0) {
			p2A = b.createTransformedArea(p2f);// p2r is the rotated b, b is
												// unchanged
		}

		for (int i = 0; i < weapons.size(); i++) {
			Weapons tmp = weapons.get(i);
			Rectangle wep = new Rectangle((int) tmp.getXpos(),
					(int) tmp.getYpos(), (int) tmp.getWidth(),
					(int) tmp.getHeight());
			if (p2A != null) {
				if (tmp.getID() == 1) {
					if (p2A.intersects(wep) && r.player2.getHitpoints() > 0) {
						if (tmp.getType() == WeaponTypes.GUN
								|| tmp.getType() == WeaponTypes.MINIGUN) {
							explosions.add(new Explosion(tmp.getXpos(), tmp
									.getYpos(), 0.5));
							snd.hit.play(1, GlbVar.sounds_volume);
							tmp.setHit();
							weapons.remove(i);
							r.player2.decreaseHitpoints(tmp.getDamage());
						} else if (tmp.getType() == WeaponTypes.UNGUIDED) {
							explosions.add(new Explosion(tmp.getXpos(), tmp
									.getYpos(), 2));
							snd.hit.play(1, GlbVar.sounds_volume);
							tmp.setHit();
							weapons.remove(i);
							r.player2.decreaseHitpoints(tmp.getDamage());
						} else if (tmp.getType() == WeaponTypes.GUIDED_AIR) {
							explosions.add(new Explosion(tmp.getXpos(), tmp
									.getYpos(), 2));
							snd.hit.play(1, GlbVar.sounds_volume);
							tmp.setHit();
							weapons.remove(i);
							r.player2.decreaseHitpoints(tmp.getDamage());
						} else if (tmp.getType() == WeaponTypes.BOMB) {
							explosions.add(new Explosion(tmp.getXpos(), tmp
									.getYpos(), 3));
							snd.hit.play(1, GlbVar.sounds_volume);
							tmp.setHit();
							weapons.remove(i);
							r.player2.decreaseHitpoints(tmp.getDamage());
						} else if (tmp.getType() == WeaponTypes.BOMB_SPLIT) {
							explosions.add(new Explosion(tmp.getXpos(), tmp
									.getYpos(), 2));
							snd.hit.play(1, GlbVar.sounds_volume);
							tmp.setHit();
							weapons.remove(i);
							r.player2.decreaseHitpoints(tmp.getDamage());
						} else if (tmp.getType() == WeaponTypes.BOMB_SPLIT_SMALL) {
							explosions.add(new Explosion(tmp.getXpos(), tmp
									.getYpos(), 1));
							snd.hit.play(1, GlbVar.sounds_volume);
							tmp.setHit();
							weapons.remove(i);
							r.player2.decreaseHitpoints(tmp.getDamage());
						}
					}
				} else if (p1A != null) {
					if (p1A.intersects(wep) && r.player1.getHitpoints() > 0) {
						if (tmp.getType() == WeaponTypes.GUN) {
							explosions.add(new Explosion(tmp.getXpos(), tmp
									.getYpos(), 0.5));
							snd.hit.play(1, GlbVar.sounds_volume);
							tmp.setHit();
							weapons.remove(i);
							r.player1.decreaseHitpoints(tmp.getDamage());
						} else if (tmp.getType() == WeaponTypes.UNGUIDED) {
							explosions.add(new Explosion(tmp.getXpos(), tmp
									.getYpos(), 2));
							snd.hit.play(1, GlbVar.sounds_volume);
							tmp.setHit();
							weapons.remove(i);
							r.player1.decreaseHitpoints(tmp.getDamage());
						} else if (tmp.getType() == WeaponTypes.GUIDED_AIR) {
							explosions.add(new Explosion(tmp.getXpos(), tmp
									.getYpos(), 2));
							snd.hit.play(1, GlbVar.sounds_volume);
							tmp.setHit();
							weapons.remove(i);
							r.player1.decreaseHitpoints(tmp.getDamage());
						} else if (tmp.getType() == WeaponTypes.BOMB) {
							explosions.add(new Explosion(tmp.getXpos(), tmp
									.getYpos(), 3));
							snd.hit.play(1, GlbVar.sounds_volume);
							tmp.setHit();
							weapons.remove(i);
							r.player1.decreaseHitpoints(tmp.getDamage());
						} else if (tmp.getType() == WeaponTypes.BOMB_SPLIT) {
							explosions.add(new Explosion(tmp.getXpos(), tmp
									.getYpos(), 2));
							snd.hit.play(1, GlbVar.sounds_volume);
							tmp.setHit();
							weapons.remove(i);
							r.player1.decreaseHitpoints(tmp.getDamage());
						} else if (tmp.getType() == WeaponTypes.BOMB_SPLIT_SMALL) {
							explosions.add(new Explosion(tmp.getXpos(), tmp
									.getYpos(), 1));
							snd.hit.play(1, GlbVar.sounds_volume);
							tmp.setHit();
							weapons.remove(i);
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
				snd.explode.play(1, GlbVar.sounds_volume);
			}
		}
		if (p2A != null) {
			if (r.player2.getHitpoints() <= 0
					&& respawntimer_p2 >= RESPAWNTIME_PLAYER) {
				explosions.add(new Explosion(r.player2.getXpos(), r.player2
						.getYpos(), 4));
				p2A = null;
				score_p1++;
				snd.explode.play(1, GlbVar.sounds_volume);
			}
		}
		if (p1A != null && p2A != null && GlbVar.getPlayerCollision()) {
			if (p1A.intersects(p2A.getBounds2D())
					&& respawntimer_p1 >= RESPAWNTIME_PLAYER) {
				explosions.add(new Explosion(r.player1.getXpos(), r.player1
						.getYpos(), 4));
				explosions.add(new Explosion(r.player2.getXpos(), r.player2
						.getYpos(), 4));
				p1A = null;
				p2A = null;
				snd.explode.play(1, GlbVar.sounds_volume);
				r.player1.setHitpoints(0);
				r.player2.setHitpoints(0);
			}
		}
		// Collision of Planes with map
		map = new Rectangle(0, (int) Render.map.getMapHeight(),
				(int) dim.getWidth(), 1);
		if (r.player1.getHitpoints() > 0 && p1A.intersects(map.getBounds2D())) {
			explosions.add(new Explosion(r.player1.getXpos(), r.player1
					.getYpos(), 4));
			snd.explode.play(1, GlbVar.sounds_volume);
			r.player1.setHitpoints(0);

		}
		if (r.player2.getHitpoints() > 0 && p2A.intersects(map.getBounds2D())) {
			explosions.add(new Explosion(r.player2.getXpos(), r.player2
					.getYpos(), 4));
			snd.explode.play(1, GlbVar.sounds_volume);
			r.player2.setHitpoints(0);
		}
		// Collision of Weapons with frame bounds or map
		for (int i = 0; i < weapons.size(); i++) {
			Weapons tmp = weapons.get(i);
			Rectangle wep = new Rectangle((int) tmp.getXpos(),
					(int) tmp.getYpos(), (int) tmp.getWidth(),
					(int) tmp.getHeight());
			if (wep.intersects(map.getBounds2D())
					|| wep.intersects(bounds_left.getBounds2D())
					|| wep.intersects(bounds_right.getBounds2D())
					|| wep.intersects(bounds_top.getBounds2D())
					|| tmp.getYpos() > Render.map.getMapHeight()) {
				if (tmp.getType() == WeaponTypes.BOMB
						|| tmp.getType() == WeaponTypes.BOMB_SPLIT
						|| tmp.getType() == WeaponTypes.BOMB_SPLIT_SMALL) {
					tmp.setHit();
					weapons.remove(i);
					explosions.add(new Explosion(tmp.getXpos(), tmp.getYpos(),
							7));
				} else if (tmp.getType() == WeaponTypes.GUIDED_AIR
						|| tmp.getType() == WeaponTypes.UNGUIDED) {
					tmp.setHit();
					weapons.remove(i);
					explosions.add(new Explosion(tmp.getXpos(), tmp.getYpos(),
							5));
				} else if (tmp.getType() == WeaponTypes.GUN) {
					tmp.setHit();
					weapons.remove(i);
					explosions.add(new Explosion(tmp.getXpos(), tmp.getYpos(),
							1));
				}
			} else if (wep.getY() <= map.getHeight()) {
				tmp.setHit();
				weapons.remove(i);
				explosions
						.add(new Explosion(tmp.getXpos(), tmp.getYpos(), 0.5));
			}
		}

		// Collision of planes with frame bounds
		bounds_left = new Rectangle(0, 0, 1, (int) dim.getHeight());
		bounds_right = new Rectangle((int) dim.getWidth(), 0, 1,
				(int) dim.getHeight());
		bounds_top = new Rectangle(0, 0, (int) dim.getWidth(), 1);
		if (r.player1.getHitpoints() > 0
				&& (p1A.intersects(bounds_left.getBounds2D())
						|| p1A.intersects(bounds_right.getBounds2D()) || p1A
							.intersects(bounds_top.getBounds2D()))) {
			explosions.add(new Explosion(r.player1.getXpos(), r.player1
					.getYpos(), 2));
			snd.explode.play(1, GlbVar.sounds_volume);
			score_p2++;
			r.player1.setHitpoints(0);
		}
		if (r.player2.getHitpoints() > 0
				&& (p2A.intersects(bounds_left.getBounds2D())
						|| p2A.intersects(bounds_right.getBounds2D()) || p2A
							.intersects(bounds_top.getBounds2D()))) {
			explosions.add(new Explosion(r.player2.getXpos(), r.player2
					.getYpos(), 2));
			snd.explode.play(1, GlbVar.sounds_volume);
			score_p1++;
			r.player2.setHitpoints(0);
		}
	}

}
