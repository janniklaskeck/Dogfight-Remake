package dogfight_remake.rendering;

import java.awt.Dimension;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import dogfight_remake.entities.ai.TurretAi;
import dogfight_remake.entities.planes.Planes;
import dogfight_remake.entities.weapons.WeaponTypes;
import dogfight_remake.main.GamePlayState;
import dogfight_remake.main.Var;

public class Render {
	public Planes player1;
	public Planes player2;
	public Planes player1_respawn;
	public Planes player2_respawn;
	public TurretAi turret;
	protected static Dimension dim = Var.dim_chosen;

	// public static Map map = new Map(dim.getWidth(), dim.getHeight());
	public void init() throws SlickException {
		// Weapon 1 p1
		if (Var.wpn1_p1 == WeaponTypes.GUN) {
			Var.wpn1_p1.setImage(Var.img_bullet1);
		} else if (Var.wpn1_p1 == WeaponTypes.MINIGUN) {
			Var.wpn1_p1.setImage(Var.img_bullet1);
		}
		// Weapon 2 p1
		if (Var.wpn2_p1 == WeaponTypes.GUIDED_AIR) {
			Var.wpn2_p1.setImage(Var.img_missile1);
		} else if (Var.wpn2_p1 == WeaponTypes.UNGUIDED) {
			Var.wpn2_p1.setImage(Var.img_missile1);
		} else if (Var.wpn2_p1 == WeaponTypes.GUIDED_GROUND) {
			Var.wpn2_p1.setImage(Var.img_missile1);
		} else if (Var.wpn2_p1 == WeaponTypes.BOMB) {
			Var.wpn2_p1.setImage(Var.img_bomb1);
		} else if (Var.wpn2_p1 == WeaponTypes.BOMB_SPLIT) {
			Var.wpn2_p1.setImage(Var.img_bomb1);
		}
		// Weapon 3 p1
		if (Var.wpn3_p1 == WeaponTypes.GUIDED_AIR) {
			Var.wpn3_p1.setImage(Var.img_missile1);
		} else if (Var.wpn3_p1 == WeaponTypes.UNGUIDED) {
			Var.wpn3_p1.setImage(Var.img_missile1);
		} else if (Var.wpn3_p1 == WeaponTypes.GUIDED_GROUND) {
			Var.wpn3_p1.setImage(Var.img_missile1);
		} else if (Var.wpn3_p1 == WeaponTypes.BOMB) {
			Var.wpn3_p1.setImage(Var.img_bomb1);
		} else if (Var.wpn3_p1 == WeaponTypes.BOMB_SPLIT) {
			Var.wpn3_p1.setImage(Var.img_bomb1);
		}
		// Weapon 1 p2
		if (Var.wpn1_p2 == WeaponTypes.GUN) {
			Var.wpn1_p2.setImage(Var.img_bullet1);
		} else if (Var.wpn1_p2 == WeaponTypes.MINIGUN) {
			Var.wpn1_p2.setImage(Var.img_bullet1);
		}
		// Weapon 2 p2
		if (Var.wpn2_p2 == WeaponTypes.GUIDED_AIR) {
			Var.wpn2_p2.setImage(Var.img_missile1);
		} else if (Var.wpn2_p2 == WeaponTypes.UNGUIDED) {
			Var.wpn2_p2.setImage(Var.img_missile1);
		} else if (Var.wpn2_p2 == WeaponTypes.GUIDED_GROUND) {
			Var.wpn2_p2.setImage(Var.img_missile1);
		} else if (Var.wpn2_p2 == WeaponTypes.BOMB) {
			Var.wpn2_p2.setImage(Var.img_bomb1);
		} else if (Var.wpn2_p2 == WeaponTypes.BOMB_SPLIT) {
			Var.wpn2_p2.setImage(Var.img_bomb1);
		}
		// Weapon 3 p2
		if (Var.wpn3_p2 == WeaponTypes.GUIDED_AIR) {
			Var.wpn3_p2.setImage(Var.img_missile1);
		} else if (Var.wpn3_p2 == WeaponTypes.UNGUIDED) {
			Var.wpn3_p2.setImage(Var.img_missile1);
		} else if (Var.wpn3_p2 == WeaponTypes.GUIDED_GROUND) {
			Var.wpn3_p2.setImage(Var.img_missile1);
		} else if (Var.wpn3_p2 == WeaponTypes.BOMB) {
			Var.wpn3_p2.setImage(Var.img_bomb1);
		} else if (Var.wpn3_p2 == WeaponTypes.BOMB_SPLIT) {
			Var.wpn3_p2.setImage(Var.img_bomb1);
		}
		Var.img_turret_base = Var.img_turret1.getSubImage(0, 0, 25, 15);
		Var.img_turret_barrel = Var.img_turret1
				.getSubImage(25, 0, 25, 15);
		player1 = new Planes(1, 100, dim.height / 2, 0, Var.img_plane1,
				Var.player1);
		player2 = new Planes(2, dim.width - 150, dim.height / 2, 180,
				Var.img_plane2, Var.player2);
		turret = new TurretAi(3, 815, 2520, 270, 100, player1,
				WeaponTypes.TURRET_MIDDLE, Var.img_bullet1);
		player1_respawn = player1;
		player2_respawn = player2;

	}

	/**
	 * PaintComponent override
	 */

	public void render(GameContainer gc, Graphics g, int delta) {

		// g.setClip(0, 0, 1680, 525);
		Var.img_bg.draw(0, 0,
				Var.tmap.getWidth() * Var.tmap.getTileWidth(),
				Var.tmap.getHeight() * Var.tmap.getTileHeight());
		GamePlayState.camera.drawMap();
		GamePlayState.camera.translateGraphics();
		if (player1 != null) {
			player1.render(gc, g, delta);
		}
		if (player2 != null) {
			player2.render(gc, g, delta);
		}

		/*
		 * g.setClip(0, 525, 1680, 525); GlbVar.img_bg.draw(0, 525,
		 * GlbVar.tmap.getWidth() * GlbVar.tmap.getTileWidth(),
		 * GlbVar.tmap.getHeight() * GlbVar.tmap.getTileHeight());
		 * GamePlayState.camera.untranslateGraphics();
		 * GamePlayState.camera2.drawMap();
		 * GamePlayState.camera2.translateGraphics(); if (player1 != null) {
		 * player1.render(gc, g, delta); } if (player2 != null) {
		 * player2.render(gc, g, delta); }
		 * GamePlayState.camera2.untranslateGraphics(); g.clearClip();
		 */
		if (turret != null) {
			turret.render(gc, g, delta);
		}
		if (GamePlayState.weapons != null) {
			for (int i = 0; i < GamePlayState.weapons.size(); i++) {
				GamePlayState.weapons.get(i).render(gc, g, delta);
			}
		}
		if (GamePlayState.explosions != null) {
			for (int i = 0; i < GamePlayState.explosions.size(); i++) {
				if (!GamePlayState.explosions.get(i).isMaxRadius()) {
					GamePlayState.explosions.get(i).render(gc, g, delta);
				} else {
					GamePlayState.explosions.remove(i);
				}
			}
		}
		// Hitpoints
		g.clearClip();
		if (player1 != null && player2 != null) {
			GamePlayState.camera.untranslateGraphics();
			// GamePlayState.camera2.untranslateGraphics();
			g.setColor(Color.black);
			// Player 1

			g.drawString("Player1: " + player1.getHitpoints(), dim.width / 20,
					dim.height / 20);
			g.drawString(
					player1.getWeapon(1).getName() + ": " + player1.getAmmo(1),
					dim.width / 20, dim.height / 20 + dim.height / 30);
			g.drawString(
					player1.getWeapon(2).getName() + ": " + player1.getAmmo(2),
					dim.width / 20, dim.height / 20 + dim.height / 15);
			g.drawString(
					player1.getWeapon(3).getName() + ": " + player1.getAmmo(3),
					dim.width / 20, (dim.height / 20) + (dim.height / 30)
							+ (dim.height / 15));
			if (Var.respawntimer_p1 < Var.RESPAWNTIME_PLAYER) {
				g.drawString(
						"Respawn Player1: " + Var.respawntimer_p1 / 100,
						dim.width / 5, dim.height / 20);
			}
			// g.drawString("", 400, 500);
			// Player 2
			g.drawString("Player2: " + player2.getHitpoints(), dim.width
					- dim.width / 7, dim.height / 20);
			g.drawString(
					player2.getWeapon(1).getName() + ": " + player2.getAmmo(1),
					dim.width - dim.width / 7, dim.height / 20 + dim.height
							/ 30);
			g.drawString(
					player2.getWeapon(2).getName() + ": " + player2.getAmmo(2),
					dim.width - dim.width / 7, dim.height / 20 + dim.height
							/ 15);
			g.drawString(
					player2.getWeapon(3).getName() + ": " + player2.getAmmo(3),
					dim.width - dim.width / 7, (dim.height / 20)
							+ (dim.height / 30) + (dim.height / 15));
			if (Var.respawntimer_p2 < Var.RESPAWNTIME_PLAYER) {
				g.drawString(
						"Respawn Player2: " + Var.respawntimer_p2 / 100,
						dim.width - dim.width / 3, dim.height / 20);
			}
			// FPS and score
			g.drawString(Var.score_p1 + " : " + Var.score_p2,
					dim.width / 2, dim.height / 10);
			g.drawString(Var.timePassed + "", dim.width / 2, dim.height / 13);
			GamePlayState.camera.translateGraphics();
			// GamePlayState.camera2.translateGraphics();
		}

	}
}
