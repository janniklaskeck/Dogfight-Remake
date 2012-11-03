package dogfight_remake.rendering;

import java.io.IOException;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

public class Effects {
    private ParticleSystem ps;
    private ConfigurableEmitter smokeTrail;
    private ConfigurableEmitter hit_ground_small;
    private ConfigurableEmitter plane_Explosion;
    private ConfigurableEmitter plane_Explosion1;

    public Effects() throws SlickException {
	try {
	    ps = ParticleIO
		    .loadConfiguredSystem("dogfight_remake/images/smoke1s.xml");
	    smokeTrail = ParticleIO
		    .loadEmitter("dogfight_remake/images/smoke1.xml");
	    hit_ground_small = ParticleIO
		    .loadEmitter("dogfight_remake/images/hit_ground_small.xml");
	    plane_Explosion = ParticleIO
		    .loadEmitter("dogfight_remake/images/plane_explosion.xml");
	    plane_Explosion1 = ParticleIO
		    .loadEmitter("dogfight_remake/images/plane_explosion1.xml");
	    ps.setRemoveCompletedEmitters(true);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void update(int delta) {
	ps.update(delta);
    }

    public void render(int delta) {
	ps.render();
    }

    public ConfigurableEmitter getSmokeTrail() {
	ConfigurableEmitter em = smokeTrail.duplicate();
	ps.addEmitter(em);
	return em;
    }

    public ConfigurableEmitter getHit_ground_small() {
	ConfigurableEmitter em = hit_ground_small.duplicate();
	ps.addEmitter(em);
	return em;
    }

    public ConfigurableEmitter getPlane_Explosion() {
	ConfigurableEmitter em = plane_Explosion.duplicate();
	ps.addEmitter(em);
	return em;
    }
    
    public ConfigurableEmitter getPlane_Explosion1() {
	ConfigurableEmitter em = plane_Explosion1.duplicate();
	ps.addEmitter(em);
	return em;
    }

}
