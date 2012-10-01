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
import dogfight_remake.main.GlbVar;

public class Render {
	public Planes player1;
	public Planes player2;
	public Planes player1_respawn;
	public Planes player2_respawn;
	public TurretAi turret;

	protected static Dimension dim = GlbVar.dim_chosen;

	// public static Map map = new Map(dim.getWidth(), dim.getHeight());
	public void init() throws SlickException {
		// Weapon 1 p1
		if (GlbVar.wpn1_p1 == WeaponTypes.GUN) {
			GlbVar.wpn1_p1.setImage(GlbVar.img_bullet1);
		} else if (GlbVar.wpn1_p1 == WeaponTypes.MINIGUN) {
			GlbVar.wpn1_p1.setImage(GlbVar.img_bullet1);
		}
		// Weapon 2 p1
		if (GlbVar.wpn2_p1 == WeaponTypes.GUIDED_AIR) {
			GlbVar.wpn2_p1.setImage(GlbVar.img_missile1);
		} else if (GlbVar.wpn2_p1 == WeaponTypes.UNGUIDED) {
			GlbVar.wpn2_p1.setImage(GlbVar.img_missile1);
		} else if (GlbVar.wpn2_p1 == WeaponTypes.GUIDED_GROUND) {
			GlbVar.wpn2_p1.setImage(GlbVar.img_missile1);
		} else if (GlbVar.wpn2_p1 == WeaponTypes.BOMB) {
			GlbVar.wpn2_p1.setImage(GlbVar.img_bomb1);
		} else if (GlbVar.wpn2_p1 == WeaponTypes.BOMB_SPLIT) {
			GlbVar.wpn2_p1.setImage(GlbVar.img_bomb1);
		}
		// Weapon 3 p1
		if (GlbVar.wpn3_p1 == WeaponTypes.GUIDED_AIR) {
			GlbVar.wpn3_p1.setImage(GlbVar.img_missile1);
		} else if (GlbVar.wpn3_p1 == WeaponTypes.UNGUIDED) {
			GlbVar.wpn3_p1.setImage(GlbVar.img_missile1);
		} else if (GlbVar.wpn3_p1 == WeaponTypes.GUIDED_GROUND) {
			GlbVar.wpn3_p1.setImage(GlbVar.img_missile1);
		} else if (GlbVar.wpn3_p1 == WeaponTypes.BOMB) {
			GlbVar.wpn3_p1.setImage(GlbVar.img_bomb1);
		} else if (GlbVar.wpn3_p1 == WeaponTypes.BOMB_SPLIT) {
			GlbVar.wpn3_p1.setImage(GlbVar.img_bomb1);
		}
		// Weapon 1 p2
		if (GlbVar.wpn1_p2 == WeaponTypes.GUN) {
			GlbVar.wpn1_p2.setImage(GlbVar.img_bullet1);
		} else if (GlbVar.wpn1_p2 == WeaponTypes.MINIGUN) {
			GlbVar.wpn1_p2.setImage(GlbVar.img_bullet1);
		}
		// Weapon 2 p2
		if (GlbVar.wpn2_p2 == WeaponTypes.GUIDED_AIR) {
			GlbVar.wpn2_p2.setImage(GlbVar.img_missile1);
		} else if (GlbVar.wpn2_p2 == WeaponTypes.UNGUIDED) {
			GlbVar.wpn2_p2.setImage(GlbVar.img_missile1);
		} else if (GlbVar.wpn2_p2 == WeaponTypes.GUIDED_GROUND) {
			GlbVar.wpn2_p2.setImage(GlbVar.img_missile1);
		} else if (GlbVar.wpn2_p2 == WeaponTypes.BOMB) {
			GlbVar.wpn2_p2.setImage(GlbVar.img_bomb1);
		} else if (GlbVar.wpn2_p2 == WeaponTypes.BOMB_SPLIT) {
			GlbVar.wpn2_p2.setImage(GlbVar.img_bomb1);
		}
		// Weapon 3 p2
		if (GlbVar.wpn3_p2 == WeaponTypes.GUIDED_AIR) {
			GlbVar.wpn3_p2.setImage(GlbVar.img_missile1);
		} else if (GlbVar.wpn3_p2 == WeaponTypes.UNGUIDED) {
			GlbVar.wpn3_p2.setImage(GlbVar.img_missile1);
		} else if (GlbVar.wpn3_p2 == WeaponTypes.GUIDED_GROUND) {
			GlbVar.wpn3_p2.setImage(GlbVar.img_missile1);
		} else if (GlbVar.wpn3_p2 == WeaponTypes.BOMB) {
			GlbVar.wpn3_p2.setImage(GlbVar.img_bomb1);
		} else if (GlbVar.wpn3_p2 == WeaponTypes.BOMB_SPLIT) {
			GlbVar.wpn3_p2.setImage(GlbVar.img_bomb1);
		}
		GlbVar.img_turret_base = GlbVar.img_turret1.getSubImage(0, 0, 25, 15);
		GlbVar.img_turret_barrel = GlbVar.img_turret1
				.getSubImage(25, 0, 25, 15);
		player1 = new Planes(1, 100, dim.height / 2, 0, GlbVar.img_player1,
				10000, GlbVar.wpn1_p1, GlbVar.wpn2_p1, GlbVar.wpn3_p1);
		player2 = new Planes(2, dim.width - 150, dim.height / 2, 180,
				GlbVar.img_player2, 100, GlbVar.wpn1_p2, GlbVar.wpn2_p2,
				GlbVar.wpn3_p2);
		turret = new TurretAi(3, 815, 1623, 270, 100, player1,
				WeaponTypes.TURRET_MIDDLE, GlbVar.img_bullet1);
		player1_respawn = player1;
		player2_respawn = player2;

	}

	/**
	 * PaintComponent override
	 */

	public void render(GameContainer gc, Graphics g, int delta) {
		// Background and Entities

		// g.drawImage(img_bg, 0, 0, null);
		// GlbVar.img_bg.draw(0, 0, 3000, 2240);

		if (turret != null) {
			turret.render(gc, g, delta);
		}

		if (player1 != null) {
			player1.render(gc, g, delta);
		}
		if (player2 != null) {
			player2.render(gc, g, delta);
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
		if (player1 != null && player2 != null) {
			GamePlayState.camera.untranslateGraphics();
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
			if (GamePlayState.respawntimer_p1 < GamePlayState.RESPAWNTIME_PLAYER) {
				g.drawString("Respawn Player1: "
						+ GamePlayState.respawntimer_p1 / 100, dim.width / 5,
						dim.height / 20);
			}
			// g.drawString("" + turret.getHitpoints(), 400, 500);
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
			if (GamePlayState.respawntimer_p2 < GamePlayState.RESPAWNTIME_PLAYER) {
				g.drawString("Respawn Player2: "
						+ GamePlayState.respawntimer_p2 / 100, dim.width
						- dim.width / 3, dim.height / 20);
			}
			// FPS and score
			g.drawString(GamePlayState.score_p1 + " : "
					+ GamePlayState.score_p2, dim.width / 2, dim.height / 10);
			g.drawString(GlbVar.timePassed + "", dim.width / 2, dim.height / 13);
			GamePlayState.camera.translateGraphics();
		}
	}
}
