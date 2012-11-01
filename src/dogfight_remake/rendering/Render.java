package dogfight_remake.rendering;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import dogfight_remake.entities.ai.TurretAi;
import dogfight_remake.entities.planes.Planes;
import dogfight_remake.entities.weapons.WeaponTypes_Primary;
import dogfight_remake.main.GamePlayState;
import dogfight_remake.main.Var;
import dogfight_remake.ui.GameUI;

public class Render {
    public Planes player1;
    public Planes player2;
    public TurretAi turret;

    public void init() throws SlickException {
	Var.img_turret_base = Var.img_turret1.getSubImage(0, 0, 25, 15);
	Var.img_turret_barrel = Var.img_turret1.getSubImage(25, 0, 25, 15);
	player1 = new Planes(1, 100, Var.dim_chosen.height / 2, 0,
		Var.player1_type);
	player2 = new Planes(2, Var.tmap.getWidth() * Var.tmap.getTileWidth()
		- 150, Var.dim_chosen.height / 2, 180, Var.player2_type);
	turret = new TurretAi(3, 600, 1000, 270, 100, player1,
		WeaponTypes_Primary.TURRET_MIDDLE, Var.img_bullet1);

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
	    GamePlayState.ef.render(delta);
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
		    if (!GamePlayState.explosions.get(i).isBroken()) {
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
		g.drawRect((gc.getScreenWidth() / 2) - 1, 0, 1,
			gc.getScreenHeight());
		GamePlayState.camera.translateGraphics();
		GamePlayState.ef.render(delta);
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
			if (!GamePlayState.explosions.get(i).isBroken()) {
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
		GamePlayState.ef.render(delta);
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
			if (!GamePlayState.explosions.get(i).isBroken()) {
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
		g.drawRect(0, (gc.getScreenHeight() / 2) - 9,
			gc.getScreenWidth(), 1);
		GamePlayState.camera.translateGraphics();
		GamePlayState.ef.render(delta);
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
			GamePlayState.explosions.get(i).render(gc, g, delta);
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
		GamePlayState.ef.render(delta);
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
			GamePlayState.explosions.get(i).render(gc, g, delta);
		    }
		}
		GamePlayState.camera2.untranslateGraphics();
		g.clearWorldClip();
		GameUI.renderPlayerInterface(0, 0, gc, g, delta);

	    }
	}
	
    }
}
