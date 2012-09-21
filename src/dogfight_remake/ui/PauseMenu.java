package dogfight_remake.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import dogfight_remake.main.GamePanel.States;
import dogfight_remake.main.GameFrame;
import dogfight_remake.main.GamePanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class PauseMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PauseMenu frame = new PauseMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PauseMenu() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setResizable(false);

		setBounds(GamePanel.dim.width / 2 - 100,
				GamePanel.dim.height / 2 - 100, 200, 200);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, -1));

		JButton btnContinue = new JButton("Continue");

		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GamePanel.state = States.PLAYING;
				dispose();
			}
		});
		contentPane.add(btnContinue);

		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GamePanel.snd.music1.stop();
				GamePanel.state = States.MAINMENU;
				GameFrame.frame.dispose();
				MainMenu.main(null);
				dispose();
			}
		});
		contentPane.add(btnMainMenu);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(3);
				dispose();
				System.exit(0);
			}
		});
		contentPane.add(btnExit);
	}

}
