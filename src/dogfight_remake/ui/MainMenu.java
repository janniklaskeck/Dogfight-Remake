package dogfight_remake.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JButton;

import dogfight_remake.main.GameFrame;
import dogfight_remake.main.GamePanel;
import dogfight_remake.main.GlbVar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSlider;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class MainMenu extends JFrame {
	public static Toolkit toolkit = Toolkit.getDefaultToolkit();
	public static Dimension dim = toolkit.getScreenSize();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static MainMenu instance = new MainMenu();
	public static MainMenu frame = null;
	public static JComboBox<String> plane_p1;
	public static JComboBox<String> Weapon_1_p1;
	public static JComboBox<String> Weapon_2_p1;
	public static JComboBox<String> Weapon_3_p1;
	public static JComboBox<String> plane_p2;
	public static JComboBox<String> Weapon_1_p2;
	public static JComboBox<String> Weapon_2_p2;
	public static JComboBox<String> Weapon_3_p2;
	public static JSlider slider_music;
	public static JSlider slider_sounds;
	public static JComboBox<String> resolution;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	private MainMenu() {
		setUndecorated(true);
		setResizable(false);
		setTitle("Dogfight - Main Menu");
		setSize(dim);
		setAlwaysOnTop(true);
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
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					dispose();
					System.exit(0);
				}
			}
		});

		getContentPane().setLayout(new GridLayout(2, 2, 0, 0));

		JPanel panel_planes = new JPanel();
		getContentPane().add(panel_planes);
		panel_planes.setLayout(new BoxLayout(panel_planes, BoxLayout.X_AXIS));

		JPanel panel_p1 = new JPanel();
		panel_planes.add(panel_p1);
		panel_p1.setLayout(new BoxLayout(panel_p1, BoxLayout.Y_AXIS));

		JPanel panel_planetype_p1 = new JPanel();
		panel_p1.add(panel_planetype_p1);

		plane_p1 = new JComboBox<String>();
		plane_p1.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Standard Plane" }));
		panel_planetype_p1.add(plane_p1);

		JPanel panel_wpn1_p1 = new JPanel();
		panel_p1.add(panel_wpn1_p1);

		Weapon_1_p1 = new JComboBox<String>();
		Weapon_1_p1.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Normal Gun", "MiniGun" }));
		Weapon_1_p1.setMaximumRowCount(3);
		panel_wpn1_p1.add(Weapon_1_p1);

		JPanel panel_wpn2_p1 = new JPanel();
		panel_p1.add(panel_wpn2_p1);

		Weapon_2_p1 = new JComboBox<String>();
		Weapon_2_p1.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Unguided Missiles", "Guided Air Missiles", "Normal Bomb",
				"Split Bombs" }));
		panel_wpn2_p1.add(Weapon_2_p1);

		JPanel panel_wpn3_p1 = new JPanel();
		panel_p1.add(panel_wpn3_p1);

		Weapon_3_p1 = new JComboBox<String>();
		Weapon_3_p1.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Unguided Missiles", "Guided Air Missiles", "Normal Bomb",
				"Split Bombs" }));
		panel_wpn3_p1.add(Weapon_3_p1);

		JPanel panel_p2 = new JPanel();
		panel_planes.add(panel_p2);
		panel_p2.setLayout(new BoxLayout(panel_p2, BoxLayout.Y_AXIS));

		JPanel panel_planetype_p2 = new JPanel();
		panel_p2.add(panel_planetype_p2);

		plane_p2 = new JComboBox<String>();
		plane_p2.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Standard Plane" }));
		panel_planetype_p2.add(plane_p2);

		JPanel panel_wpn1_p2 = new JPanel();
		panel_p2.add(panel_wpn1_p2);

		Weapon_1_p2 = new JComboBox<String>();
		Weapon_1_p2.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Normal Gun", "MiniGun" }));
		panel_wpn1_p2.add(Weapon_1_p2);

		JPanel panel_wpn2_p2 = new JPanel();
		panel_p2.add(panel_wpn2_p2);

		Weapon_2_p2 = new JComboBox<String>();
		Weapon_2_p2.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Unguided Missiles", "Guided Air Missiles", "Normal Bomb",
				"Split Bombs" }));
		panel_wpn2_p2.add(Weapon_2_p2);

		JPanel panel_wpn3_p2 = new JPanel();
		panel_p2.add(panel_wpn3_p2);

		Weapon_3_p2 = new JComboBox<String>();
		Weapon_3_p2.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Unguided Missiles", "Guided Air Missiles", "Normal Bomb",
				"Split Bombs" }));
		panel_wpn3_p2.add(Weapon_3_p2);

		JPanel panel_game_option = new JPanel();
		getContentPane().add(panel_game_option);
		panel_game_option.setLayout(new GridLayout(10, 1, 0, 0));

		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		panel_game_option.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton(
				"New radio button");
		panel_game_option.add(rdbtnNewRadioButton_1);

		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton(
				"New radio button");
		panel_game_option.add(rdbtnNewRadioButton_2);

		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton(
				"New radio button");
		panel_game_option.add(rdbtnNewRadioButton_3);

		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton(
				"New radio button");
		panel_game_option.add(rdbtnNewRadioButton_4);

		JRadioButton rdbtnNewRadioButton_5 = new JRadioButton(
				"New radio button");
		panel_game_option.add(rdbtnNewRadioButton_5);

		JRadioButton rdbtnNewRadioButton_6 = new JRadioButton(
				"New radio button");
		panel_game_option.add(rdbtnNewRadioButton_6);

		JRadioButton rdbtnNewRadioButton_7 = new JRadioButton(
				"New radio button");
		panel_game_option.add(rdbtnNewRadioButton_7);

		JRadioButton rdbtnNewRadioButton_8 = new JRadioButton(
				"New radio button");
		panel_game_option.add(rdbtnNewRadioButton_8);

		final JRadioButton opt_collision = new JRadioButton("Player Collision");

		// check for collision
		if (GlbVar.getPlayerCollision() == true) {
			opt_collision.setSelected(true);
		} else {
			opt_collision.setSelected(false);
		}

		opt_collision.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (opt_collision.isSelected()) {
					GlbVar.setPlayerCollision(true);
				} else {
					GlbVar.setPlayerCollision(false);
				}
			}
		});
		panel_game_option.add(opt_collision);

		JPanel panel_start = new JPanel();
		getContentPane().add(panel_start);
		panel_start.setLayout(new BorderLayout(0, 0));

		JPanel panel_st_brd_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_st_brd_1.getLayout();
		flowLayout.setVgap(100);
		panel_start.add(panel_st_brd_1, BorderLayout.NORTH);

		JPanel panel_st_brd_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_st_brd_2.getLayout();
		flowLayout_1.setVgap(100);
		panel_start.add(panel_st_brd_2, BorderLayout.SOUTH);

		JPanel panel_st_brd_3 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_st_brd_3.getLayout();
		flowLayout_2.setHgap(100);
		panel_start.add(panel_st_brd_3, BorderLayout.WEST);

		JPanel panel_st_brd_4 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_st_brd_4.getLayout();
		flowLayout_3.setHgap(100);
		panel_start.add(panel_st_brd_4, BorderLayout.EAST);

		JPanel panel_st_0 = new JPanel();
		panel_start.add(panel_st_0, BorderLayout.CENTER);
		panel_st_0.setLayout(new BoxLayout(panel_st_0, BoxLayout.Y_AXIS));

		JPanel panel_start_btn = new JPanel();
		panel_st_0.add(panel_start_btn);
		panel_start_btn.setLayout(new BorderLayout(0, 0));

		JButton btnStart = new JButton("Start");
		panel_start_btn.add(btnStart, BorderLayout.CENTER);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GlbVar.setResolution();
				if (GameFrame.frame == null) {
					GameFrame.main(null);
				} else {
					GamePanel.stop();
					GameFrame.frame.dispose();
					GameFrame.main(null);
				}

				dispose();
			}
		});

		JPanel panel_exit_btn = new JPanel();
		panel_st_0.add(panel_exit_btn);
		panel_exit_btn.setLayout(new BorderLayout(0, 0));

		JButton btnExit = new JButton("Exit");
		panel_exit_btn.add(btnExit, BorderLayout.CENTER);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
				System.exit(0);
			}
		});

		JPanel panel_options = new JPanel();
		getContentPane().add(panel_options);
		panel_options.setLayout(new BoxLayout(panel_options, BoxLayout.X_AXIS));

		JPanel panel = new JPanel();
		panel_options.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel panel_music_volume = new JPanel();
		panel.add(panel_music_volume);
		panel_music_volume.setLayout(new BorderLayout(0, 0));

		slider_music = new JSlider(0, 100, 50);
		slider_music.setMinorTickSpacing(5);
		slider_music.setMajorTickSpacing(10);
		slider_music.setSnapToTicks(true);
		slider_music.setPaintTicks(true);
		slider_music.setPaintLabels(true);
		panel_music_volume.add(slider_music);

		JLabel lblVolume = new JLabel("Music Volume");
		lblVolume.setHorizontalAlignment(SwingConstants.CENTER);
		lblVolume.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_music_volume.add(lblVolume, BorderLayout.NORTH);

		JPanel panel_sound_volume = new JPanel();
		panel.add(panel_sound_volume);
		panel_sound_volume.setLayout(new BorderLayout(0, 0));

		JLabel lblSoundEffectVolume = new JLabel("Sound Effect Volume");
		lblSoundEffectVolume.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoundEffectVolume.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_sound_volume.add(lblSoundEffectVolume, BorderLayout.NORTH);

		slider_sounds = new JSlider(0, 100, 20);
		slider_sounds.setMinorTickSpacing(5);
		slider_sounds.setMajorTickSpacing(10);
		slider_sounds.setSnapToTicks(true);
		slider_sounds.setPaintTicks(true);
		slider_sounds.setPaintLabels(true);
		panel_sound_volume.add(slider_sounds);

		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		JPanel panel_8 = new JPanel();
		panel.add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_options.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));

		JLabel lblNewLabel = new JLabel("Resolution");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_5.add(lblNewLabel);

		resolution = new JComboBox<String>();
		resolution.setFont(new Font("Tahoma", Font.BOLD, 12));
		resolution.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Fullscreen", "1920x1080", "1280x720" }));
		panel_5.add(resolution);

		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));

		JPanel panel_7 = new JPanel();
		panel_1.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));

	}

	public static MainMenu getInstance() {
		return instance;
	}
}
