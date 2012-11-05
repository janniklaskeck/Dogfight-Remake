package dogfight_remake.ui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class TwoStateButton {

    private float xpos;
    private float ypos;
    private Image active;
    private Image inActive;
    private String text;
    private int imageWidth;
    private int imageHeight;
    private boolean state;

    public TwoStateButton(float xpos, float ypos, Image active, Image inActive,
	    String text, int imageWidth, int imageHeight) {
	this.xpos = xpos;
	this.ypos = ypos;
	this.active = active;
	this.inActive = inActive;
	this.text = text;
	this.imageHeight = imageHeight;
	this.imageWidth = imageWidth;
    }

    public void render(Graphics g) {
	if (state) {
	    active.draw(xpos, ypos, imageWidth, imageHeight);
	}
	if (!state) {
	    inActive.draw(xpos, ypos, imageWidth, imageHeight);
	}
	g.drawString(text, xpos + imageWidth + 2, ypos);
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

    public void setPosition(float xpos, float ypos) {
	this.xpos = xpos;
	this.ypos = ypos;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public Image getActive() {
	return active;
    }

    public void setActive(Image active) {
	this.active = active;
    }

    public Image getInActive() {
	return inActive;
    }

    public void setInActive(Image inActive) {
	this.inActive = inActive;
    }

    public boolean isState() {
	return state;
    }

    public void setState(boolean state) {
	this.state = state;
    }

}
