package dogfight_remake.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import dogfight_remake.main.GamePlayState;
import dogfight_remake.main.Var;
import dogfight_remake.rendering.Render;

public class GameUI {
    static Render r = GamePlayState.r;

    public static void renderPlayerInterface(float xpos, float ypos,
	    GameContainer gc, Graphics g, int delta) {
	
	float xpos_p2 = Var.dim_chosen.width;
	if (r.player1 != null && r.player2 != null) {
	    // Player 1
	    Var.img_player_ui.draw(0, 0);
	    Var.img_player_ui_text.draw(0, 0);
	    if (r.player1.getHitpoints() < 20) {
		g.setColor(Color.red);
	    } else if (r.player1.getHitpoints() < 60) {
		g.setColor(Color.yellow);
	    } else {
		g.setColor(Color.green);
	    }
	    for (int i = 0; i < r.player1.getHitpoints(); i++) {
		g.drawRect(31 + i * 0.45f, 9, 0.45f, 5);
	    }
	    if (r.player1.getHeat_prim() > 80) {
		g.setColor(Color.red);
	    } else if (r.player1.getHeat_prim() > 50) {
		g.setColor(Color.yellow);
	    } else {
		g.setColor(Color.green);
	    }
	    for (int i = 0; i < r.player1.getHeat_prim(); i++) {
		g.fillRect(83 + i * 1.2f, 67, 1.2f, 6);
	    }
	    g.setColor(Color.black);
	    r.player1.getWeapon(3).getImage().setRotation(0);
	    r.player1.getWeapon(4).getImage().setRotation(0);

	    r.player1.getWeapon(3).getImage().draw(86, 10);
	    g.drawString("" + r.player1.getAmmo(3), 50 + 86, 6);
	    r.player1.getWeapon(4).getImage().draw(157, 10);
	    g.drawString("" + r.player1.getAmmo(4), 50 + 157, 6);
	    if (r.player1.getRespawn_timer() < Var.RESPAWNTIME_PLAYER) {
		g.drawString("" + r.player1.getRespawn_timer() / 100, 100, 0);
	    }
	    g.drawString(gc.getFPS() + "", 400, 500);

	    g.setColor(r.player1.getEngineStatus());
	    g.drawString("E", 84, 78);
	    g.setColor(r.player1.getAfterBurnerStatus());
	    g.drawString("A", 96, 78);
	    g.setColor(r.player1.getTankStatus());
	    g.drawString("T", 108, 78);
	    g.setColor(r.player1.getFuelStatus());
	    g.drawString("F", 120, 78);
	    g.setColor(r.player1.getPrim1Status());
	    g.drawString("P", 136, 78);
	    g.setColor(r.player1.getPrim2Status());
	    g.drawString("P", 148, 78);
	    g.setColor(r.player1.getSec1Status());
	    g.drawString("S", 164, 78);
	    g.setColor(r.player1.getSec2Status());
	    g.drawString("S", 176, 78);

	    // Player 2
	    Var.img_player_ui.getFlippedCopy(true, false).draw(
		    Var.dim_chosen.width - 225, 0);
	    Var.img_player_ui_text.draw(Var.dim_chosen.width - 28, 0);
	    // Health
	    if (r.player2.getHitpoints() < 20) {
		g.setColor(Color.red);
	    } else if (r.player2.getHitpoints() < 60) {
		g.setColor(Color.yellow);
	    } else {
		g.setColor(Color.green);
	    }
	    for (int i = 0; i < r.player2.getHitpoints(); i++) {
		g.drawRect(xpos_p2 - 33 - i * 0.45f, 9, 0.45f, 5);
	    }

	    // Primary Weapon Heat
	    if (r.player2.getHeat_prim() > 80) {
		g.setColor(Color.red);
	    } else if (r.player2.getHeat_prim() > 50) {
		g.setColor(Color.yellow);
	    } else {
		g.setColor(Color.green);
	    }
	    for (int i = 0; i < r.player2.getHeat_prim(); i++) {
		g.fillRect(xpos_p2 - 84 - i * 1.2f, 67, 1.2f, 6);
	    }

	    g.setColor(Color.black);
	    r.player2.getWeapon(3).getImage().setRotation(0);
	    r.player2.getWeapon(4).getImage().setRotation(0);
	    r.player2.getWeapon(3).getImage().draw(xpos_p2 - 147, 10);
	    g.drawString("" + r.player2.getAmmo(3), xpos_p2 - 147 + 50, 6);
	    r.player2.getWeapon(4).getImage().draw(xpos_p2 - 217, 10);
	    g.drawString("" + r.player2.getAmmo(4), xpos_p2 - 217 + 50, 6);
	    if (r.player2.getRespawn_timer() < Var.RESPAWNTIME_PLAYER) {
		g.drawString("" + r.player2.getRespawn_timer() / 100,
			Var.dim_chosen.width - Var.dim_chosen.width / 3,
			Var.dim_chosen.height / 2);
	    }
	    // FPS and score
	    g.drawString(Var.score_p1 + " : " + Var.score_p2,
		    Var.dim_chosen.width / 2, Var.dim_chosen.height / 10);
	    Var.img_timer.draw(Var.dim_chosen.width / 2 - 30, 0);
	    g.setColor(Color.white);

	    g.drawString(Var.timePassed_min + ":" + Var.timePassed_sec,
		    Var.dim_chosen.width / 2 - 12, 0);
	}
    }
}
