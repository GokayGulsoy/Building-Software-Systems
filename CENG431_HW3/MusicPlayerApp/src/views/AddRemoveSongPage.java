package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.MusicAppController;

public class AddRemoveSongPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField songNameTextField;
	private JTextField playlistNameTextField;
	private MusicAppController controller;

	public AddRemoveSongPage() {
		setResizable(false);
		setMinimumSize(new Dimension(800, 570));
		setTitle("Add/Remove Song Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 570);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(222, 221, 218));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel header = new JPanel();
		header.setBorder(new LineBorder(new Color(94, 92, 100), 3, true));
		header.setBackground(new Color(192, 191, 188));
		FlowLayout flowLayout = (FlowLayout) header.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(header, BorderLayout.NORTH);

		JLabel lblAddOrRemove = new JLabel("Add or Remove Song Page");
		lblAddOrRemove.setFont(new Font("FreeSerif", Font.BOLD | Font.ITALIC, 24));
		header.add(lblAddOrRemove);

		JPanel addRemoveSongPanel = new JPanel();
		addRemoveSongPanel.setBackground(new Color(98, 160, 234));
		contentPane.add(addRemoveSongPanel, BorderLayout.CENTER);
		addRemoveSongPanel.setLayout(null);

		JLabel labelEnterPlaylistName = new JLabel("Enter the Name of Playlist:");
		labelEnterPlaylistName.setBounds(174, 85, 186, 20);
		labelEnterPlaylistName.setFont(new Font("Noto Sans", Font.BOLD, 14));
		addRemoveSongPanel.add(labelEnterPlaylistName);

		playlistNameTextField = new JTextField();
		playlistNameTextField.setBounds(365, 83, 300, 24);
		playlistNameTextField.setFont(new Font("Noto Sans", Font.PLAIN, 14));
		playlistNameTextField.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		addRemoveSongPanel.add(playlistNameTextField);
		playlistNameTextField.setColumns(10);

		JLabel labelEnterTheSongName = new JLabel("Enter the Name of Song:");
		labelEnterTheSongName.setBounds(174, 142, 186, 24);
		labelEnterTheSongName.setFont(new Font("Noto Sans", Font.BOLD, 14));
		addRemoveSongPanel.add(labelEnterTheSongName);

		songNameTextField = new JTextField();
		songNameTextField.setBounds(365, 142, 300, 24);
		songNameTextField.setFont(new Font("Noto Sans", Font.PLAIN, 14));
		songNameTextField.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		addRemoveSongPanel.add(songNameTextField);
		songNameTextField.setColumns(10);

		JButton btnAddSong = new JButton("Add Song");
		btnAddSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.addSong();
			}
		});
		btnAddSong.setBounds(395, 203, 270, 24);
		btnAddSong.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		btnAddSong.setFont(new Font("Noto Sans", Font.BOLD, 14));
		addRemoveSongPanel.add(btnAddSong);

		JButton btnRemoveSong = new JButton("Remove Song");
		btnRemoveSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.removeSong();
			}
		});
		btnRemoveSong.setBounds(395, 262, 270, 24);
		btnRemoveSong.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		btnRemoveSong.setFont(new Font("Noto Sans", Font.BOLD, 14));
		addRemoveSongPanel.add(btnRemoveSong);

		JButton btnGoToNavigation = new JButton("Go to Navigation Page");
		btnGoToNavigation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.moveToNavigationPage(AddRemoveSongPage.this);
				controller.setAddRemoveSongPageToDefault();
			}
		});
		btnGoToNavigation.setBounds(395, 321, 265, 24);
		btnGoToNavigation.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		btnGoToNavigation.setFont(new Font("Noto Sans", Font.BOLD, 14));
		addRemoveSongPanel.add(btnGoToNavigation);
	}

	public void setController(MusicAppController controller) {
		this.controller = controller;

	}

	public JTextField getSongNameTextField() {
		return songNameTextField;
	}

	public JTextField getPlaylistNameTextField() {
		return playlistNameTextField;
	}

}
