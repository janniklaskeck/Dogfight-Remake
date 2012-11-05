package dogfight_remake.ui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import dogfight_remake.main.Var;

public class MenuContainer {

    private float xpos;
    private float ypos;
    private Image background;
    private String text;
    private int imageWidth;
    private int imageHeight;
    private float yDifLeft;
    private float xposRow_1;

    private TwoStateButton exit;
    private TwoStateButton airCollision;
    private TwoStateButton fullscreen;
    private TwoStateButton verticalSplit;
    private TwoStateButton resolution;
    private TwoStateButton vSync;
    private TwoStateButton fuelUse;
    private TwoStateButton duds;

    public MenuContainer(float xpos, float ypos, Image background, String text,
	    int imageWidth, int imageHeight) {
	this.xpos = xpos;
	this.ypos = ypos;
	this.background = background;
	this.text = text;
	this.imageHeight = imageHeight;
	this.imageWidth = imageWidth;
	this.yDifLeft = 30;
	this.xposRow_1 = xpos + 20;

	exit = new TwoStateButton(xpos + 404, ypos + 10, Var.exitCorner,
		Var.exitCorner, "", 16, 16);
	airCollision = new TwoStateButton(xposRow_1, ypos + yDifLeft + 10,
		Var.button2, Var.button1, "Air Collisions", 16, 16);
	fullscreen = new TwoStateButton(xposRow_1, ypos + yDifLeft * 2 + 10,
		Var.button2, Var.button1, "Fullscreen", 16, 16);
	verticalSplit = new TwoStateButton(xposRow_1, ypos + yDifLeft * 3 + 10,
		Var.button2, Var.button1, "Vertical Splitscreen", 16, 16);
	resolution = new TwoStateButton(xposRow_1, ypos + yDifLeft * 4 + 10,
		Var.button1, Var.button1, "Resolution", 16, 16);
    }

    public void render(Graphics g) {
	background.draw(xpos, ypos, imageWidth, imageHeight);
	// Buttons
	airCollision.render(g);
	fullscreen.render(g);
	verticalSplit.render(g);
	resolution.render(g);
	exit.render(g);
    }

    public void update(int delta) {
	airCollision.setState(Var.getPlayerCollision());
	fullscreen.setState(Var.fullscreen);
	verticalSplit.setState(Var.vertical_split);
    }

    public float getXpos() {
	return xpos;
    }

    public void setXpos(float xpos) {
	this.xpos = xpos;
    }

    public float getYpos() {
	return ypos;
    }

    public void setYpos(float ypos) {
	this.ypos = ypos;
    }

    public Image getBackground() {
	return background;
    }

    public void setBackground(Image background) {
	this.background = background;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public int getImageWidth() {
	return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
	this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
	return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
	this.imageHeight = imageHeight;
    }

}
