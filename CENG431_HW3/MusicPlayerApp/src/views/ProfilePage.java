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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controller.MusicAppController;

public class ProfilePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField playListNameTextField;
	private JTable playlistSongsTable;
	private JTable followingUsersTable;
	private JTable followerUsersTable;
	private MusicAppController controller;
	private JLabel lblEmptyname;

	@SuppressWarnings("serial")
	public ProfilePage() {
		setResizable(false);
		setTitle("Profile Page");
		setMinimumSize(new Dimension(1100, 650));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1101, 711);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel header = new JPanel();
		header.setBorder(new LineBorder(new Color(94, 92, 100), 3, true));
		header.setBackground(new Color(192, 191, 188));
		FlowLayout flowLayout = (FlowLayout) header.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(header, BorderLayout.NORTH);

		JLabel lblProfilePage = new JLabel("Profile Page");
		lblProfilePage.setFont(new Font("FreeSerif", Font.BOLD | Font.ITALIC, 24));
		header.add(lblProfilePage);

		JPanel leftPanelProfilePage = new JPanel();
		leftPanelProfilePage.setBackground(new Color(154, 153, 150));
		contentPane.add(leftPanelProfilePage, BorderLayout.WEST);

		JLabel lblUsername = new JLabel("UserName:");
		lblUsername.setFont(new Font("Noto Sans", Font.BOLD, 14));
		leftPanelProfilePage.add(lblUsername);

		lblEmptyname = new JLabel("EmptyName");
		lblEmptyname.setFont(new Font("Noto Sans", Font.BOLD, 14));
		leftPanelProfilePage.add(lblEmptyname);

		JPanel tablesPanel = new JPanel();
		tablesPanel.setBackground(new Color(98, 160, 234));
		contentPane.add(tablesPanel, BorderLayout.CENTER);
		tablesPanel.setLayout(null);

		JLabel lblPlaylistName = new JLabel("Enter Playlist Name:");
		lblPlaylistName.setBounds(143, 135, 141, 20);
		lblPlaylistName.setFont(new Font("Noto Sans", Font.BOLD, 14));
		tablesPanel.add(lblPlaylistName);

		playListNameTextField = new JTextField();
		playListNameTextField.setBounds(379, 133, 445, 24);
		playListNameTextField.setFont(new Font("Noto Sans", Font.PLAIN, 14));
		playListNameTextField.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		tablesPanel.add(playListNameTextField);
		playListNameTextField.setColumns(10);

		JScrollPane playListSongsScrollPane = new JScrollPane();
		playListSongsScrollPane.setBounds(83, 162, 741, 145);
		playListSongsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tablesPanel.add(playListSongsScrollPane);

		playlistSongsTable = new JTable();
		playlistSongsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		playlistSongsTable.setBackground(new Color(192, 191, 188));
		playListSongsScrollPane.setViewportView(playlistSongsTable);
		playlistSongsTable.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		playlistSongsTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "genre id", "track id",
				"song", "artist", "duration", "popularity", "number of likes" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		JLabel lblFollowingUsers = new JLabel("Following Users");
		lblFollowingUsers.setBounds(128, 342, 111, 20);
		lblFollowingUsers.setFont(new Font("Noto Sans", Font.BOLD, 14));
		tablesPanel.add(lblFollowingUsers);

		JLabel lblNewLabel = new JLabel("Follower Users");
		lblNewLabel.setBounds(661, 342, 103, 20);
		lblNewLabel.setFont(new Font("Noto Sans", Font.BOLD, 14));
		tablesPanel.add(lblNewLabel);

		JScrollPane followingUsersScrollPane = new JScrollPane();
		followingUsersScrollPane.setBounds(83, 367, 201, 149);
		tablesPanel.add(followingUsersScrollPane);

		followingUsersTable = new JTable();
		followingUsersTable.setBackground(new Color(192, 191, 188));
		followingUsersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		followingUsersScrollPane.setViewportView(followingUsersTable);
		followingUsersTable.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		followingUsersTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Following Username" }) {
			boolean[] columnEditables = new boolean[] { false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		JScrollPane followerUsersScrollPane = new JScrollPane();
		followerUsersScrollPane.setBounds(589, 367, 235, 149);
		tablesPanel.add(followerUsersScrollPane);

		followerUsersTable = new JTable();
		followerUsersTable.setBackground(new Color(192, 191, 188));
		followerUsersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		followerUsersScrollPane.setViewportView(followerUsersTable);
		followerUsersTable.setBorder(new LineBorder(new Color(119, 118, 123), 2));
		followerUsersTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Follower Username" }) {
			boolean[] columnEditables = new boolean[] { false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		JButton btnGoToNavigation = new JButton("Go to Navigation Page");
		btnGoToNavigation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.moveToNavigationPage(ProfilePage.this);
				controller.setProfilePageToDefault();
			}
		});

		btnGoToNavigation.setBounds(339, 399, 215, 24);
		btnGoToNavigation.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		btnGoToNavigation.setFont(new Font("Noto Sans", Font.BOLD, 14));
		tablesPanel.add(btnGoToNavigation);

		JButton btnBringSongsOn = new JButton("Bring Songs on Playlist");
		btnBringSongsOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringSongsOnPlaylist();
			}
		});
		btnBringSongsOn.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		btnBringSongsOn.setFont(new Font("Noto Sans", Font.BOLD, 14));
		btnBringSongsOn.setBounds(339, 435, 215, 25);
		tablesPanel.add(btnBringSongsOn);
	}

	public void setController(MusicAppController controller) {
		this.controller = controller;
	}

	public void setProfileNameLabel(String username) {
		lblEmptyname.setText(username);
	}

	public String getProfileName() {
		return lblEmptyname.getText();
	}

	public JTable getFollowingUsersTable() {
		return this.followingUsersTable;
	}

	public JTable getFollowerUsersTable() {
		return this.followerUsersTable;
	}

	public JTable getPlaylistSongsTable() {
		return this.playlistSongsTable;
	}

	public JTextField getPlaylistName() {
		return this.playListNameTextField;
	}

}
