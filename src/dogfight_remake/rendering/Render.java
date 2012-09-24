package dogfight_remake.rendering;

import java.awt.Dimension;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import dogfight_remake.entities.planes.Planes;
import dogfight_remake.entities.weapons.WeaponTypes;
import dogfight_remake.main.GamePlayState;
import dogfight_remake.main.GlbVar;

public class Render {
	public Planes player1;
	public Planes player2;
	public Planes player1_respawn;
	public Planes player2_respawn;
	public static Image img_player1;
	public static Image img_player2;
	public static Image img_missile1;
	public static Image img_bullet1;
	public static Image img_bomb1;
	private Image img_bg;

	protected static Dimension dim = GlbVar.dim_chosen;

	private long firstFrame = 0;
	private int frames = 0;
	private long currentFrame;
	public int fps = 0;

	// public static Map map = new Map(dim.getWidth(), dim.getHeight());

	public void init() throws SlickException {
		try {
			img_player1 = new Image("dogfight_remake/images/plane1.png");
			img_player2 = new Image("dogfight_remake/images/plane2.png");
			img_bg = new Image("dogfight_remake/images/img_bg.jpg");
			img_missile1 = new Image("dogfight_remake/images/missile1.png");
			img_bullet1 = new Image("dogfight_remake/images/bullet1.png");
			img_bomb1 = new Image("dogfight_remake/images/bomb1.png");
		} catch (SlickException e) {
			System.out.println("Error Images 01");
			e.printStackTrace();
		}
		// Weapon 1 p1
		if (GlbVar.wpn1_p1 == WeaponTypes.GUN) {
			GlbVar.wpn1_p1.setImage(img_bullet1);
		} else if (GlbVar.wpn1_p1 == WeaponTypes.MINIGUN) {
			GlbVar.wpn1_p1.setImage(img_bullet1);
		}
		// Weapon 2 p1
		if (GlbVar.wpn2_p1 == WeaponTypes.GUIDED_AIR) {
			GlbVar.wpn2_p1.setImage(img_missile1);
		} else if (GlbVar.wpn2_p1 == WeaponTypes.UNGUIDED) {
			GlbVar.wpn2_p1.setImage(img_missile1);
		} else if (GlbVar.wpn2_p1 == WeaponTypes.GUIDED_GROUND) {
			GlbVar.wpn2_p1.setImage(img_missile1);
		} else if (GlbVar.wpn2_p1 == WeaponTypes.BOMB) {
			GlbVar.wpn2_p1.setImage(img_bomb1);
		} else if (GlbVar.wpn2_p1 == WeaponTypes.BOMB_SPLIT) {
			GlbVar.wpn2_p1.setImage(img_bomb1);
		}
		// Weapon 3 p1
		if (GlbVar.wpn3_p1 == WeaponTypes.GUIDED_AIR) {
			GlbVar.wpn3_p1.setImage(img_missile1);
		} else if (GlbVar.wpn3_p1 == WeaponTypes.UNGUIDED) {
			GlbVar.wpn3_p1.setImage(img_missile1);
		} else if (GlbVar.wpn3_p1 == WeaponTypes.GUIDED_GROUND) {
			GlbVar.wpn3_p1.setImage(img_missile1);
		} else if (GlbVar.wpn3_p1 == WeaponTypes.BOMB) {
			GlbVar.wpn3_p1.setImage(img_bomb1);
		} else if (GlbVar.wpn3_p1 == WeaponTypes.BOMB_SPLIT) {
			GlbVar.wpn3_p1.setImage(img_bomb1);
		}
		// Weapon 1 p2
		if (GlbVar.wpn1_p2 == WeaponTypes.GUN) {
			GlbVar.wpn1_p2.setImage(img_bullet1);
		} else if (GlbVar.wpn1_p2 == WeaponTypes.MINIGUN) {
			GlbVar.wpn1_p2.setImage(img_bullet1);
		}
		// Weapon 2 p2
		if (GlbVar.wpn2_p2 == WeaponTypes.GUIDED_AIR) {
			GlbVar.wpn2_p2.setImage(img_missile1);
		} else if (GlbVar.wpn2_p2 == WeaponTypes.UNGUIDED) {
			GlbVar.wpn2_p2.setImage(img_missile1);
		} else if (GlbVar.wpn2_p2 == WeaponTypes.GUIDED_GROUND) {
			GlbVar.wpn2_p2.setImage(img_missile1);
		} else if (GlbVar.wpn2_p2 == WeaponTypes.BOMB) {
			GlbVar.wpn2_p2.setImage(img_bomb1);
		} else if (GlbVar.wpn2_p2 == WeaponTypes.BOMB_SPLIT) {
			GlbVar.wpn2_p2.setImage(img_bomb1);
		}
		// Weapon 3 p2
		if (GlbVar.wpn3_p2 == WeaponTypes.GUIDED_AIR) {
			GlbVar.wpn3_p2.setImage(img_missile1);
		} else if (GlbVar.wpn3_p2 == WeaponTypes.UNGUIDED) {
			GlbVar.wpn3_p2.setImage(img_missile1);
		} else if (GlbVar.wpn3_p2 == WeaponTypes.GUIDED_GROUND) {
			GlbVar.wpn3_p2.setImage(img_missile1);
		} else if (GlbVar.wpn3_p2 == WeaponTypes.BOMB) {
			GlbVar.wpn3_p2.setImage(img_bomb1);
		} else if (GlbVar.wpn3_p2 == WeaponTypes.BOMB_SPLIT) {
			GlbVar.wpn3_p2.setImage(img_bomb1);
		}

		player1 = new Planes(1, 100, dim.height / 2, 0, img_player1, 100,
				GlbVar.wpn1_p1, GlbVar.wpn2_p1, GlbVar.wpn3_p1);
		player2 = new Planes(2, dim.width - 150, dim.height / 2, 180,
				img_player2, 100, GlbVar.wpn1_p2, GlbVar.wpn2_p2,
				GlbVar.wpn3_p2);
		player1_respawn = player1;
		player2_respawn = player2;
		// map = new Map(dim.getWidth(), dim.getHeight());
	}

	/**
	 * PaintComponent override
	 */

	public void render(GameContainer container, Graphics g) {
		frames++;
		currentFrame = System.currentTimeMillis();
		if (currentFrame > firstFrame + 1000) {
			firstFrame = currentFrame;
			fps = frames;
			frames = 0;
		}
		// Background and Entities

		// g.drawImage(img_bg, 0, 0, null);
		img_bg.draw(0, 0);

		if (player1 != null) {
			player1.render(container, g);
		}
		if (player2 != null) {
			player2.render(container, g);
		}

		if (GamePlayState.weapons != null) {
			for (int i = 0; i < GamePlayState.weapons.size(); i++) {
				GamePlayState.weapons.get(i).render(container, g);
			}
		}
		if (GamePlayState.explosions != null) {
			for (int i = 0; i < GamePlayState.explosions.size(); i++) {
				if (!GamePlayState.explosions.get(i).isMaxRadius()) {
					GamePlayState.explosions.get(i).render(container, g);
				} else {
					GamePlayState.explosions.remove(i);
				}
			}
		}
		// Hitpoints
		if (player1 != null && player2 != null) {
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
			g.drawString("" + GamePlayState.weapons.size(), 400, 400);
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
		}
	}
}
