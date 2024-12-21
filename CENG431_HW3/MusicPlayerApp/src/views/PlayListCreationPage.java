package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.MusicAppController;

public class PlayListCreationPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup playlistGenreButtonGroup = new ButtonGroup();
	private JTextField playlistNameTextField;
	private MusicAppController controller;

	public PlayListCreationPage() {
		setResizable(false);
		setTitle("Playlist Creation Page");
		setMinimumSize(new Dimension(790, 550));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 790, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel header = new JPanel();
		header.setBorder(new LineBorder(new Color(94, 92, 100), 3, true));
		header.setBackground(new Color(192, 191, 188));
		FlowLayout fl_header = (FlowLayout) header.getLayout();
		fl_header.setAlignment(FlowLayout.LEFT);
		contentPane.add(header, BorderLayout.NORTH);

		JLabel labelPlaylistCreationPage = new JLabel("PlayList Creation Page");
		labelPlaylistCreationPage.setFont(new Font("FreeSerif", Font.BOLD | Font.ITALIC, 24));
		labelPlaylistCreationPage.setHorizontalTextPosition(SwingConstants.CENTER);
		labelPlaylistCreationPage.setHorizontalAlignment(SwingConstants.CENTER);
		header.add(labelPlaylistCreationPage);

		JPanel playlistCheckBoxPanel = new JPanel();
		playlistCheckBoxPanel.setBackground(new Color(192, 191, 188));
		contentPane.add(playlistCheckBoxPanel, BorderLayout.WEST);
		playlistCheckBoxPanel.setLayout(new GridLayout(7, 1, 0, 0));

		JLabel labelChosePlaylistType = new JLabel("Chose Playlist Type");
		labelChosePlaylistType.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		labelChosePlaylistType.setHorizontalTextPosition(SwingConstants.CENTER);
		labelChosePlaylistType.setHorizontalAlignment(SwingConstants.CENTER);
		labelChosePlaylistType.setFont(new Font("Noto Sans", Font.BOLD, 14));
		playlistCheckBoxPanel.add(labelChosePlaylistType);

		JCheckBox acousticCheckBox = new JCheckBox("Acoustic");
		acousticCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		acousticCheckBox.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		acousticCheckBox.setBackground(new Color(222, 221, 218));
		playlistGenreButtonGroup.add(acousticCheckBox);
		playlistCheckBoxPanel.add(acousticCheckBox);

		JCheckBox instrumentalCheckBox = new JCheckBox("Instrumental");
		instrumentalCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		instrumentalCheckBox.setBackground(new Color(222, 221, 218));
		playlistGenreButtonGroup.add(instrumentalCheckBox);
		playlistCheckBoxPanel.add(instrumentalCheckBox);

		JCheckBox rockCheckBox = new JCheckBox("Rock");
		rockCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		rockCheckBox.setBackground(new Color(222, 221, 218));
		playlistGenreButtonGroup.add(rockCheckBox);
		playlistCheckBoxPanel.add(rockCheckBox);

		JCheckBox hiphopCheckBox = new JCheckBox("Hip-Hop");
		hiphopCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		hiphopCheckBox.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		hiphopCheckBox.setBackground(new Color(222, 221, 218));
		playlistGenreButtonGroup.add(hiphopCheckBox);
		playlistCheckBoxPanel.add(hiphopCheckBox);

		JCheckBox jazzCheckBox = new JCheckBox("Jazz");
		jazzCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		jazzCheckBox.setBackground(new Color(222, 221, 218));
		playlistGenreButtonGroup.add(jazzCheckBox);
		playlistCheckBoxPanel.add(jazzCheckBox);

		JCheckBox popCheckBox = new JCheckBox("Pop");
		popCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		popCheckBox.setBackground(new Color(222, 221, 218));
		playlistGenreButtonGroup.add(popCheckBox);
		playlistCheckBoxPanel.add(popCheckBox);

		JPanel playlistNameForm = new JPanel();
		playlistNameForm.setBackground(new Color(98, 160, 234));
		contentPane.add(playlistNameForm, BorderLayout.CENTER);
		playlistNameForm.setLayout(null);

		JLabel labelEnterPlaylistName = new JLabel("Enter Playlist Name:");
		labelEnterPlaylistName.setBounds(78, 162, 141, 20);
		labelEnterPlaylistName.setFont(new Font("Noto Sans", Font.BOLD, 14));
		playlistNameForm.add(labelEnterPlaylistName);

		playlistNameTextField = new JTextField();
		playlistNameTextField.setBounds(224, 159, 360, 26);
		playlistNameTextField.setFont(new Font("Noto Sans", Font.PLAIN, 14));
		playlistNameTextField.setBorder(new LineBorder(new Color(119, 118, 123), 3, true));
		playlistNameForm.add(playlistNameTextField);
		playlistNameTextField.setColumns(10);

		JButton createPlaylistButton = new JButton("Create Playlist");
		createPlaylistButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.createPlaylist();
				playlistNameTextField.setText("");
				playlistGenreButtonGroup.clearSelection();
			}
		});

		createPlaylistButton.setBounds(284, 220, 295, 26);
		createPlaylistButton.setBorder(new LineBorder(new Color(119, 118, 123), 3, true));
		createPlaylistButton.setFont(new Font("Noto Sans", Font.BOLD, 14));
		playlistNameForm.add(createPlaylistButton);

		JButton btnGoToNavigation = new JButton("Go to Navigation Page");
		btnGoToNavigation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.moveToNavigationPage(PlayListCreationPage.this);
				controller.setPlaylistCreationPageToDefault();
			}
		});

		btnGoToNavigation.setBounds(284, 281, 295, 26);
		btnGoToNavigation.setBorder(new LineBorder(new Color(119, 118, 123), 3, true));
		btnGoToNavigation.setFont(new Font("Noto Sans", Font.BOLD, 14));
		playlistNameForm.add(btnGoToNavigation);
	}

	public void setController(MusicAppController controller) {
		this.controller = controller;
	}

	public ButtonGroup getPlaylistButtons() {
		return playlistGenreButtonGroup;
	}

	public JTextField getPlaylistName() {
		return playlistNameTextField;
	}

	public String getSelectedPlaylistType() {
		Enumeration<AbstractButton> playlistButtons = playlistGenreButtonGroup.getElements();

		while (playlistButtons.hasMoreElements()) {
			AbstractButton button = playlistButtons.nextElement();

			if (button.isSelected()) {
				return button.getActionCommand();
			}
		}

		return null;
	}

}
