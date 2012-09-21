package dogfight_remake.main;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import dogfight_remake.main.GamePanel.States;
import dogfight_remake.ui.PauseMenu;

public class GameFrame extends JFrame {
	public static Dimension dim = GlbVar.dim_chosen;
	public GamePanel game;
	public static GameFrame frame = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		GameFrame frame = new GameFrame();
		frame.setVisible(true);
	}

	public GameFrame() {

		setUndecorated(true);
		setResizable(false);
		setTitle("Dogfight-Frame");
		setSize(dim);
		setVisible(true);
		game = new GamePanel();
		getContentPane().add(game);
		frame = this;

		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_P) {
					PauseMenu.main(null);

					GamePanel.state = States.PAUSED;
				}

			}
		});
	}
}
