package dogfight_remake.rendering;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import dogfight_remake.entities.ai.TurretAi;
import dogfight_remake.entities.planes.Planes;
import dogfight_remake.entities.weapons.WeaponTypes;
import dogfight_remake.main.GamePlayState;
import dogfight_remake.main.Var;
import dogfight_remake.ui.GameUI;

public class Render {
	public Planes player1;
	public Planes player2;
	public Planes player1_respawn = null;
	public Planes player2_respawn = null;
	public TurretAi turret;

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
		Var.img_turret_barrel = Var.img_turret1.getSubImage(25, 0, 25, 15);
		player1 = new Planes(1, 100, Var.dim_chosen.height / 2, 0,
				Var.player1_type);
		player2 = new Planes(2, Var.dim_chosen.width - 150,
				Var.dim_chosen.height / 2, 180, Var.player2_type);
		turret = new TurretAi(3, 815, 2520, 270, 100, player1,
				WeaponTypes.TURRET_MIDDLE, Var.img_bullet1);
		if (player1_respawn == null && player2_respawn == null) {
			player1_respawn = new Planes(1, 100, Var.dim_chosen.height / 2, 0,
					Var.player1_type);
			player2_respawn = new Planes(2, Var.dim_chosen.width - 150,
					Var.dim_chosen.height / 2, 180, Var.player2_type);
		}
	}

	/**
	 * PaintComponent override
	 */

	public void render(GameContainer gc, Graphics g, int delta) {
		if (Var.singlePlayer) {
			GamePlayState.camera.translateGraphics();
			Var.img_bg.draw(0, 0,
					Var.tmap.getWidth() * Var.tmap.getTileWidth(),
					Var.tmap.getHeight() * Var.tmap.getTileHeight());
			GamePlayState.camera.untranslateGraphics();
			GamePlayState.camera.drawMap();
			GamePlayState.camera.translateGraphics();

			if (player1 != null) {
				player1.render(gc, g, delta);
			}
			if (player2 != null) {
				player2.render(gc, g, delta);
			}
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
			GamePlayState.camera.untranslateGraphics();
			GameUI.renderPlayerInterface(0, 0, gc, g, delta);
		} else if (!Var.singlePlayer) {
			if (Var.vertical_split) {
				g.setWorldClip(0, 0, gc.getWidth() / 2, gc.getHeight());
				GamePlayState.camera.translateGraphics();
				Var.img_bg.draw(0, 0,
						Var.tmap.getWidth() * Var.tmap.getTileWidth(),
						Var.tmap.getHeight() * Var.tmap.getTileHeight());
				GamePlayState.camera.untranslateGraphics();
				GamePlayState.camera.drawMap();
				GamePlayState.camera.translateGraphics();

				if (player1 != null) {
					player1.render(gc, g, delta);
				}
				if (player2 != null) {
					player2.render(gc, g, delta);
				}
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
							GamePlayState.explosions.get(i)
									.render(gc, g, delta);
						} else {
							GamePlayState.explosions.remove(i);
						}
					}
				}
				GamePlayState.camera.untranslateGraphics();
				g.setWorldClip(gc.getWidth() / 2, 0, gc.getWidth() / 2,
						gc.getHeight());
				// ---
				GamePlayState.camera2.translateGraphics();
				Var.img_bg.draw(0, 0,
						Var.tmap.getWidth() * Var.tmap.getTileWidth(),
						Var.tmap.getHeight() * Var.tmap.getTileHeight());
				GamePlayState.camera2.untranslateGraphics();
				GamePlayState.camera2.drawMap();
				GamePlayState.camera2.translateGraphics();
				if (player1 != null) {
					player1.render(gc, g, delta);
				}
				if (player2 != null) {
					player2.render(gc, g, delta);
				}
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
							GamePlayState.explosions.get(i)
									.render(gc, g, delta);
						} else {
							GamePlayState.explosions.remove(i);
						}
					}
				}
				GamePlayState.camera2.untranslateGraphics();
				g.clearWorldClip();
				GameUI.renderPlayerInterface(0, 0, gc, g, delta);
			} else if (!Var.vertical_split) {
				g.setWorldClip(0, 0, gc.getWidth(), gc.getHeight() / 2);
				GamePlayState.camera.translateGraphics();
				Var.img_bg.draw(0, 0,
						Var.tmap.getWidth() * Var.tmap.getTileWidth(),
						Var.tmap.getHeight() * Var.tmap.getTileHeight());
				GamePlayState.camera.untranslateGraphics();
				GamePlayState.camera.drawMap();
				GamePlayState.camera.translateGraphics();

				if (player1 != null) {
					player1.render(gc, g, delta);
				}
				if (player2 != null) {
					player2.render(gc, g, delta);
				}
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
							GamePlayState.explosions.get(i)
									.render(gc, g, delta);
						} else {
							GamePlayState.explosions.remove(i);
						}
					}
				}
				GamePlayState.camera.untranslateGraphics();
				g.setWorldClip(0, gc.getHeight() / 2, gc.getWidth(),
						gc.getHeight() / 2);
				// ---
				GamePlayState.camera2.translateGraphics();
				Var.img_bg.draw(0, 0,
						Var.tmap.getWidth() * Var.tmap.getTileWidth(),
						Var.tmap.getHeight() * Var.tmap.getTileHeight());
				GamePlayState.camera2.untranslateGraphics();
				GamePlayState.camera2.drawMap();
				GamePlayState.camera2.translateGraphics();
				if (player1 != null) {
					player1.render(gc, g, delta);
				}
				if (player2 != null) {
					player2.render(gc, g, delta);
				}
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
							GamePlayState.explosions.get(i)
									.render(gc, g, delta);
						} else {
							GamePlayState.explosions.remove(i);
						}
					}
				}
				GamePlayState.camera2.untranslateGraphics();
				g.clearWorldClip();
				GameUI.renderPlayerInterface(0, 0, gc, g, delta);

			}
		}

	}
}
