package dogfight_remake.ui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import dogfight_remake.controls.KeyControls;
import dogfight_remake.entities.planes.PlaneTypes;
import dogfight_remake.main.Dogfight_Remake;
import dogfight_remake.main.Var;

public class MainMenuState extends BasicGameState {

    int stateID = -1;

    public MainMenuState(int stateID) {
	this.stateID = stateID;
    }

    private float dim;
    private float optionsX;
    private float optionsMenuX;
    private float optionsMenuY;
    private float optionCollisionX;
    private float optionCollisionY;
    private float optionFullscreenX;
    private float optionFullscreenY;
    private float optionResolutionX;
    private float optionResolutionY;
    private float startGameX;
    private float startGameY;
    private float plane_p1X;
    private float plane_p1Y;
    private float plane_p2X;
    private float plane_p2Y;
    private float exitX;
    private float exitY;

    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) {
	dim = Var.dim_chosen.width + Var.dim_chosen.height;

	optionsMenuX = Var.dim_chosen.width / 2 + 30;
	optionsMenuY = Var.dim_chosen.height / 5;
	optionsX = optionsMenuX + 30;
	optionCollisionY = optionsMenuY + 40;
	optionCollisionX = optionsX;
	optionFullscreenY = optionCollisionY + 20;
	optionFullscreenX = optionsX;
	optionResolutionY = optionFullscreenY + 20;
	optionResolutionX = optionsX;

	exitX = Var.dim_chosen.width - Var.dim_chosen.height / 50 - 16;
	exitY = Var.dim_chosen.height / 50;
	startGameX = 50;
	startGameY = Var.dim_chosen.height - 50
		- Var.startGameOption.getHeight();
	plane_p1X = 50;
	plane_p1Y = startGameY - 50 - Var.plane_p1.getHeight();
	plane_p2X = 50;
	plane_p2Y = plane_p1Y - 50 - Var.plane_p2.getHeight();
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg)
	    throws SlickException {
	gc.setVSync(Var.vSync);
	dim = Var.dim_chosen.width + Var.dim_chosen.height;

	optionsMenuX = Var.dim_chosen.width - 50 - (1024 * dim / 6000);
	optionsMenuY = Var.dim_chosen.height / 20;
	optionsX = optionsMenuX + 30;
	optionCollisionY = optionsMenuY + Var.dim_chosen.height / 20;
	optionCollisionX = optionsX;
	optionFullscreenY = optionCollisionY + 20;
	optionFullscreenX = optionsX;
	optionResolutionY = optionFullscreenY + 20;
	optionResolutionX = optionsX;

	exitX = Var.dim_chosen.width - Var.dim_chosen.height / 50 - 16;
	exitY = Var.dim_chosen.height / 50;
	startGameX = 50;
	startGameY = Var.dim_chosen.height - 50
		- Var.startGameOption.getHeight();
	plane_p1X = 50;
	plane_p1Y = startGameY - 50 - Var.plane_p1.getHeight();
	plane_p2X = 50;
	plane_p2Y = plane_p1Y - 50 - Var.plane_p2.getHeight();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
	    throws SlickException {
	// render the background
	Var.background.draw(0, 0, Var.dim_chosen.width, Var.dim_chosen.height);
	// Game Options background
	Var.gameOptionsMenu.draw(optionsMenuX, optionsMenuY, 430, 400);
	// Player Collision
	if (Var.getPlayerCollision()) {
	    Var.button2.draw(optionCollisionX, optionCollisionY, 16, 16);
	} else {
	    Var.button1.draw(optionCollisionX, optionCollisionY, 16, 16);
	}
	g.drawString("Air Collisions", optionCollisionX + 20, optionCollisionY);
	// Fullscreen
	if (Var.fullscreen) {
	    Var.button2.draw(optionFullscreenX, optionFullscreenY, 16, 16);
	} else {
	    Var.button1.draw(optionFullscreenX, optionFullscreenY, 16, 16);
	}
	g.drawString("Fullscreen", optionFullscreenX + 20, optionFullscreenY);
	// Resolution
	Var.button1.draw(optionResolutionX, optionResolutionY, 16, 16);
	g.drawString("Resolution", optionResolutionX + 20, optionResolutionY);

	// Exit button
	Var.exitCorner.draw(exitX, exitY, 16, 16);

	// Start game
	Var.startGameOption.draw(startGameX, startGameY);

	// Plane Menus
	Var.plane_p1.draw(plane_p1X, plane_p1Y);
	Var.plane_p2.draw(plane_p2X, plane_p2Y);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
	    throws SlickException {
	Input input = gc.getInput();
	KeyControls.update(gc, sbg, delta);
	int mouseX = input.getMouseX();
	int mouseY = input.getMouseY();
	// Game parameters
	boolean slowGame = false;
	boolean systemDamage = false;
	boolean fuelUsage = false;
	boolean drawShots = false;
	boolean noSecondary = false;
	boolean friendlyFire = false;
	boolean duds = false; // blindgänger
	boolean oneShot = false;
	boolean baseRebuild = false;
	boolean insidePlCol = false;
	boolean objectsMenu = false;
	boolean respawnTime = false;
	boolean timeLimit = false;
	boolean maxTries = false;
	boolean goal = false;
	boolean level = false;
	// Game start/exit
	boolean insideStartGame = false;
	boolean insideExitCorner = false;
	// Graphics options
	boolean windowedMode = false;
	boolean resolution = false;
	boolean clouds = false;
	boolean thunder = false;
	boolean dynamicSplitscreen = false;
	boolean verticalSplitscreen = false;
	boolean showRadar = false;
	boolean vsync = false;
	boolean showCrosshair = false;
	boolean language = false;
	boolean music = false;

	// Plane Menus
	boolean insidePlane_p1 = false;
	boolean insidePlane_p2 = false;

	if (mouseX >= startGameX
		&& mouseX <= startGameX + Var.startGameOption.getWidth()
		&& mouseY >= startGameY
		&& mouseY <= startGameY + Var.startGameOption.getHeight()) {
	    insideStartGame = true;
	} else if (mouseX >= exitX && mouseX <= exitX + 16 && mouseY >= exitY
		&& mouseY <= exitY + 16) {
	    insideExitCorner = true;
	} else if (mouseX >= optionCollisionX
		&& mouseX <= optionCollisionX + 16
		&& mouseY >= optionCollisionY
		&& mouseY <= optionCollisionY + 16) {
	    insidePlCol = true;
	} else if (mouseX >= optionCollisionX
		&& mouseX <= optionCollisionX + 16
		&& mouseY >= optionCollisionY
		&& mouseY <= optionCollisionY + 16) {
	    slowGame = true;
	} else if (mouseX >= optionCollisionX
		&& mouseX <= optionCollisionX + 16
		&& mouseY >= optionCollisionY
		&& mouseY <= optionCollisionY + 16) {
	    systemDamage = true;
	} else if (mouseX >= optionCollisionX
		&& mouseX <= optionCollisionX + 16
		&& mouseY >= optionCollisionY
		&& mouseY <= optionCollisionY + 16) {
	    fuelUsage = true;
	} else if (mouseX >= optionCollisionX
		&& mouseX <= optionCollisionX + 16
		&& mouseY >= optionCollisionY
		&& mouseY <= optionCollisionY + 16) {
	    drawShots = true;
	} else if (mouseX >= optionCollisionX
		&& mouseX <= optionCollisionX + 16
		&& mouseY >= optionCollisionY
		&& mouseY <= optionCollisionY + 16) {
	    noSecondary = true;
	} else if (mouseX >= optionCollisionX
		&& mouseX <= optionCollisionX + 16
		&& mouseY >= optionCollisionY
		&& mouseY <= optionCollisionY + 16) {
	    friendlyFire = true;
	} else if (mouseX >= optionCollisionX
		&& mouseX <= optionCollisionX + 16
		&& mouseY >= optionCollisionY
		&& mouseY <= optionCollisionY + 16) {
	    duds = true;
	} else if (mouseX >= optionCollisionX
		&& mouseX <= optionCollisionX + 16
		&& mouseY >= optionCollisionY
		&& mouseY <= optionCollisionY + 16) {
	    oneShot = true;
	} else if (mouseX >= optionCollisionX
		&& mouseX <= optionCollisionX + 16
		&& mouseY >= optionCollisionY
		&& mouseY <= optionCollisionY + 16) {
	    baseRebuild = true;
	} else if (mouseX >= optionCollisionX
		&& mouseX <= optionCollisionX + 16
		&& mouseY >= optionCollisionY
		&& mouseY <= optionCollisionY + 16) {
	    objectsMenu = true;
	} else if (mouseX >= optionCollisionX
		&& mouseX <= optionCollisionX + 16
		&& mouseY >= optionCollisionY
		&& mouseY <= optionCollisionY + 16) {
	    respawnTime = true;
	} else if (mouseX >= optionCollisionX
		&& mouseX <= optionCollisionX + 16
		&& mouseY >= optionCollisionY
		&& mouseY <= optionCollisionY + 16) {
	    timeLimit = true;
	} else if (mouseX >= optionCollisionX
		&& mouseX <= optionCollisionX + 16
		&& mouseY >= optionCollisionY
		&& mouseY <= optionCollisionY + 16) {
	    maxTries = true;
	} else if (mouseX >= optionCollisionX
		&& mouseX <= optionCollisionX + 16
		&& mouseY >= optionCollisionY
		&& mouseY <= optionCollisionY + 16) {
	    goal = true;
	} else if (mouseX >= optionCollisionX
		&& mouseX <= optionCollisionX + 16
		&& mouseY >= optionCollisionY
		&& mouseY <= optionCollisionY + 16) {
	    level = true;
	} else if (mouseX >= optionFullscreenX
		&& mouseX <= optionFullscreenX + 16
		&& mouseY >= optionFullscreenY
		&& mouseY <= optionFullscreenY + 16) {
	    windowedMode = true;
	} else if (mouseX >= optionFullscreenX
		&& mouseX <= optionFullscreenX + 16
		&& mouseY >= optionFullscreenY
		&& mouseY <= optionFullscreenY + 16) {
	    clouds = true;
	} else if (mouseX >= optionFullscreenX
		&& mouseX <= optionFullscreenX + 16
		&& mouseY >= optionFullscreenY
		&& mouseY <= optionFullscreenY + 16) {
	    thunder = true;
	} else if (mouseX >= optionFullscreenX
		&& mouseX <= optionFullscreenX + 16
		&& mouseY >= optionFullscreenY
		&& mouseY <= optionFullscreenY + 16) {
	    dynamicSplitscreen = true;
	} else if (mouseX >= optionFullscreenX
		&& mouseX <= optionFullscreenX + 16
		&& mouseY >= optionFullscreenY
		&& mouseY <= optionFullscreenY + 16) {
	    verticalSplitscreen = true;
	} else if (mouseX >= optionFullscreenX
		&& mouseX <= optionFullscreenX + 16
		&& mouseY >= optionFullscreenY
		&& mouseY <= optionFullscreenY + 16) {
	    showRadar = true;
	} else if (mouseX >= optionFullscreenX
		&& mouseX <= optionFullscreenX + 16
		&& mouseY >= optionFullscreenY
		&& mouseY <= optionFullscreenY + 16) {
	    vsync = true;
	} else if (mouseX >= optionFullscreenX
		&& mouseX <= optionFullscreenX + 16
		&& mouseY >= optionFullscreenY
		&& mouseY <= optionFullscreenY + 16) {
	    showCrosshair = true;
	} else if (mouseX >= optionFullscreenX
		&& mouseX <= optionFullscreenX + 16
		&& mouseY >= optionFullscreenY
		&& mouseY <= optionFullscreenY + 16) {
	    language = true;
	} else if (mouseX >= optionFullscreenX
		&& mouseX <= optionFullscreenX + 16
		&& mouseY >= optionFullscreenY
		&& mouseY <= optionFullscreenY + 16) {
	    music = true;
	} else if (mouseX >= optionResolutionX
		&& mouseX <= optionResolutionX + 16
		&& mouseY >= optionResolutionY
		&& mouseY <= optionResolutionY + 16) {
	    resolution = true;
	} else if (mouseX >= plane_p1X && mouseX <= plane_p1X + 161
		&& mouseY >= plane_p1Y && mouseY <= plane_p1Y + 20) {
	    insidePlane_p1 = true;
	} else if (mouseX >= plane_p2X && mouseX <= plane_p2X + 161
		&& mouseY >= plane_p2Y && mouseY <= plane_p2Y + 20) {
	    insidePlane_p2 = true;
	}

	if (insideStartGame) {
	    if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
		if (Var.player1_type == null) {
		    Var.player1_type = PlaneTypes.NORMAL;
		}
		if (Var.player2_type == null) {
		    Var.player2_type = PlaneTypes.NORMAL;
		}
		sbg.enterState(Dogfight_Remake.GAMEPLAYSTATE);
	    }
	} else if (insideExitCorner) {
	    if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
		gc.exit();
	} else if (insidePlCol) {
	    if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
		if (!Var.getPlayerCollision()) {
		    Var.setPlayerCollision(true);
		} else {
		    Var.setPlayerCollision(false);
		}
	} else if (slowGame) {

	} else if (systemDamage) {

	} else if (fuelUsage) {

	} else if (drawShots) {

	} else if (noSecondary) {

	} else if (duds) {

	} else if (friendlyFire) {

	} else if (friendlyFire) {

	} else if (oneShot) {

	} else if (baseRebuild) {

	} else if (objectsMenu) {

	} else if (respawnTime) {

	} else if (timeLimit) {

	} else if (maxTries) {

	} else if (goal) {

	} else if (level) {

	} else if (clouds) {

	} else if (thunder) {

	} else if (dynamicSplitscreen) {

	} else if (verticalSplitscreen) {

	} else if (showRadar) {

	} else if (vsync) {

	} else if (showCrosshair) {

	} else if (language) {

	} else if (music) {

	} else if (windowedMode) {
	    if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
		if (Var.fullscreen) {
		    Var.fullscreen = false;
		    Var.app.setDisplayMode(Var.dim_chosen.width,
			    Var.dim_chosen.height, Var.fullscreen);
		} else if (!Var.fullscreen
			&& Var.dim_chosen.width <= Var.dim_fullscreen.width
			&& Var.dim_chosen.height <= Var.dim_fullscreen.height) {
		    Var.fullscreen = true;
		    Var.app.setDisplayMode(Var.dim_chosen.width,
			    Var.dim_chosen.height, Var.fullscreen);
		}
	    }
	} else if (resolution) {
	    if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
		if (Var.dim_chosen == Var.dim_fullscreen) {
		    Var.dim_chosen = Var.dim_720p;
		} else if (Var.dim_chosen == Var.dim_720p
			&& Var.dim_1080p.width <= Var.dim_fullscreen.width
			&& Var.dim_1080p.height <= Var.dim_fullscreen.height) {
		    Var.dim_chosen = Var.dim_1080p;
		} else {
		    Var.dim_chosen = Var.dim_fullscreen;
		}
		Var.app.setDisplayMode(Var.dim_chosen.width,
			Var.dim_chosen.height, Var.fullscreen);
		init(gc, sbg);
	    }
	} else if (insidePlane_p1) {
	    if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
		Var.plane_id = 1;
		sbg.enterState(Dogfight_Remake.PLANESTATE);
	    }
	} else if (insidePlane_p2) {
	    if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
		Var.plane_id = 2;
		sbg.enterState(Dogfight_Remake.PLANESTATE);
	    }
	}
    }

    @Override
    public int getID() {
	return stateID;
    }

}
