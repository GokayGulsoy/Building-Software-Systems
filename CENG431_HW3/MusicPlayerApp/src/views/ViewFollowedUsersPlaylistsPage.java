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
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controller.MusicAppController;

public class ViewFollowedUsersPlaylistsPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable songsTable;
	private JTable usernameTable;
	private JTable playlistTable;
	private MusicAppController controller;

	@SuppressWarnings("serial")
	public ViewFollowedUsersPlaylistsPage() {
		setResizable(false);
		setMinimumSize(new Dimension(1075, 660));
		setTitle("View Followed Users Playlists Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1075, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel header = new JPanel();
		header.setBorder(new LineBorder(new Color(119, 118, 123), 3, true));
		header.setBackground(new Color(192, 191, 188));
		FlowLayout fl_header = (FlowLayout) header.getLayout();
		fl_header.setAlignment(FlowLayout.LEFT);
		contentPane.add(header, BorderLayout.NORTH);

		JLabel lblViewFollowedUsers = new JLabel("View Followed Users Playlists Page");
		lblViewFollowedUsers.setFont(new Font("FreeSerif", Font.BOLD | Font.ITALIC, 24));
		header.add(lblViewFollowedUsers);

		JPanel followedUsersPlaylistsPanel = new JPanel();
		followedUsersPlaylistsPanel.setBackground(new Color(98, 160, 234));
		contentPane.add(followedUsersPlaylistsPanel, BorderLayout.CENTER);
		followedUsersPlaylistsPanel.setLayout(null);

		JLabel labelForUserName = new JLabel("Select row for user which you want to see playlists");
		labelForUserName.setBounds(41, 68, 351, 20);
		labelForUserName.setFont(new Font("Noto Sans", Font.BOLD, 14));
		followedUsersPlaylistsPanel.add(labelForUserName);

		JLabel labelForPlayList = new JLabel("Select playlist for which you want to see songs");
		labelForPlayList.setBounds(690, 68, 324, 20);
		labelForPlayList.setFont(new Font("Noto Sans", Font.BOLD, 14));
		followedUsersPlaylistsPanel.add(labelForPlayList);

		JScrollPane usernameScrollPane = new JScrollPane();
		usernameScrollPane.setBounds(41, 93, 351, 174);
		followedUsersPlaylistsPanel.add(usernameScrollPane);

		usernameTable = new JTable();
		usernameTable.setBackground(new Color(192, 191, 188));
		usernameTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		usernameTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		usernameTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "User Name" }) {
			boolean[] columnEditables = new boolean[] { false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		usernameScrollPane.setViewportView(usernameTable);

		JScrollPane playlistScrollPane = new JScrollPane();
		playlistScrollPane.setBounds(690, 93, 324, 174);
		followedUsersPlaylistsPanel.add(playlistScrollPane);

		playlistTable = new JTable();
		playlistTable.setBackground(new Color(192, 191, 188));
		playlistTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		playlistTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Playlist Name", "Genre" }) {
			boolean[] columnEditables = new boolean[] { false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		playlistScrollPane.setViewportView(playlistTable);

		JButton btnGoToNavigation = new JButton("Go to Navigation Page");
		btnGoToNavigation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setViewFollowedUsersPageToDefault();
				controller.moveToNavigationPage(ViewFollowedUsersPlaylistsPage.this);
			}
		});

		btnGoToNavigation.setBounds(421, 123, 241, 24);
		btnGoToNavigation.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		btnGoToNavigation.setFont(new Font("Noto Sans", Font.BOLD, 14));
		followedUsersPlaylistsPanel.add(btnGoToNavigation);

		JLabel labelForSongs = new JLabel("Songs belong to selected playlist");
		labelForSongs.setBounds(457, 302, 228, 20);
		labelForSongs.setFont(new Font("Noto Sans", Font.BOLD, 14));
		followedUsersPlaylistsPanel.add(labelForSongs);

		JScrollPane songsScrollPane = new JScrollPane();
		songsScrollPane.setBounds(41, 327, 973, 180);
		followedUsersPlaylistsPanel.add(songsScrollPane);

		songsTable = new JTable();
		songsTable.setBackground(new Color(192, 191, 188));
		songsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		songsTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "genre id", "track id", "song",
				"artist", "duration", "popularity", "number of likes" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		songsScrollPane.setViewportView(songsTable);

		JButton btnNewButton = new JButton("Bring Playlists");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.loadPlaylistsOfFollowedUser();
			}
		});

		btnNewButton.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		btnNewButton.setFont(new Font("Noto Sans", Font.BOLD, 14));
		btnNewButton.setBounds(421, 169, 241, 25);
		followedUsersPlaylistsPanel.add(btnNewButton);

		JButton btnBringSongsIn = new JButton("Bring Songs In Playlist");
		btnBringSongsIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.loadSongsOfSelectedPlaylist();
			}
		});

		btnBringSongsIn.setFont(new Font("Noto Sans", Font.BOLD, 14));
		btnBringSongsIn.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		btnBringSongsIn.setBounds(421, 220, 241, 25);
		followedUsersPlaylistsPanel.add(btnBringSongsIn);
	}

	public void setController(MusicAppController controller) {
		this.controller = controller;
	}

	public JTable getFollowedUsersTable() {
		return usernameTable;
	}

	public JTable getPlaylistsTable() {
		return playlistTable;
	}

	public JTable getSongsTable() {
		return songsTable;
	}

}
