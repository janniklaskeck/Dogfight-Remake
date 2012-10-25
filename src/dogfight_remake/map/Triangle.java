package dogfight_remake.map;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;

public class Triangle {
    public Polygon poly;
    public int tileID;

    public Triangle(int x, int y, int test[], String type, int tileID) {
	poly = new Polygon(new float[] { x + test[0], y + test[1], x + test[2],
		y + test[3], x + test[4], y + test[5] });
	this.tileID = tileID;
    }

    public void update(int delta) {
    }

    public void draw(Graphics g) {
	g.draw(poly);
    }
}
