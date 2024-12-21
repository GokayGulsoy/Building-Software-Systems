package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controller.MusicAppController;

public class StatisticsPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable shortestAndLongestPlaylistsTable;
	private MusicAppController controller;
	private JLabel mostFollowedUser;
	private JLabel mostPopularSong;
	private JLabel lblMostLikedSong;
	private JLabel lblMostPopularSong;
	private JLabel lblMostFollowedUser;

	@SuppressWarnings("serial")
	public StatisticsPage() {
		setResizable(false);
		setMinimumSize(new Dimension(920, 560));
		setTitle("Statistics Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 920, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel header = new JPanel();
		header.setBackground(new Color(192, 191, 188));
		FlowLayout fl_header = (FlowLayout) header.getLayout();
		fl_header.setAlignment(FlowLayout.LEFT);
		contentPane.add(header, BorderLayout.NORTH);

		JLabel lblStatisticsPage = new JLabel("Statistics Page");
		lblStatisticsPage.setFont(new Font("FreeSerif", Font.BOLD | Font.ITALIC, 24));
		header.add(lblStatisticsPage);

		JPanel shortestAndLongestPlaylistsPanel = new JPanel();
		shortestAndLongestPlaylistsPanel.setBackground(new Color(98, 160, 234));
		contentPane.add(shortestAndLongestPlaylistsPanel, BorderLayout.CENTER);
		shortestAndLongestPlaylistsPanel.setLayout(null);

		JLabel mostLikedSongIndicator = new JLabel("Most Liked Song");
		mostLikedSongIndicator.setBounds(131, 89, 116, 20);
		mostLikedSongIndicator.setFont(new Font("Noto Sans", Font.BOLD, 14));
		shortestAndLongestPlaylistsPanel.add(mostLikedSongIndicator);

		JLabel MostPopularSongIndicator = new JLabel("Most Popular Song");
		MostPopularSongIndicator.setBounds(378, 89, 132, 20);
		MostPopularSongIndicator.setFont(new Font("Noto Sans", Font.BOLD, 14));
		shortestAndLongestPlaylistsPanel.add(MostPopularSongIndicator);

		JLabel MostFollowedIndicator = new JLabel("Most Followed User");
		MostFollowedIndicator.setBounds(641, 89, 138, 20);
		MostFollowedIndicator.setFont(new Font("Noto Sans", Font.BOLD, 14));
		shortestAndLongestPlaylistsPanel.add(MostFollowedIndicator);

		mostFollowedUser = new JLabel("Empty");
		mostFollowedUser.setBounds(0, 0, 0, 0);
		shortestAndLongestPlaylistsPanel.add(mostFollowedUser);

		mostPopularSong = new JLabel("");
		mostPopularSong.setBounds(0, 0, 0, 0);
		shortestAndLongestPlaylistsPanel.add(mostPopularSong);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(64, 189, 771, 87);
		shortestAndLongestPlaylistsPanel.add(scrollPane);

		shortestAndLongestPlaylistsTable = new JTable();
		shortestAndLongestPlaylistsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		shortestAndLongestPlaylistsTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"Shortest Playlist", "Shortest Playlist Genre", "Longest Playlist", "Longest Playlist Genre" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(shortestAndLongestPlaylistsTable);

		JButton btnGoToNavigation = new JButton("Go to Navigation Page");
		btnGoToNavigation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setStatisticsPageToDefault();
				controller.moveToNavigationPage(StatisticsPage.this);
			}
		});

		btnGoToNavigation.setBounds(341, 330, 227, 24);
		btnGoToNavigation.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		btnGoToNavigation.setFont(new Font("Noto Sans", Font.BOLD, 14));
		shortestAndLongestPlaylistsPanel.add(btnGoToNavigation);

		lblMostLikedSong = new JLabel("Empty");
		lblMostLikedSong.setFont(new Font("Noto Sans", Font.BOLD, 14));
		lblMostLikedSong.setHorizontalAlignment(SwingConstants.CENTER);
		lblMostLikedSong.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblMostLikedSong.setBounds(141, 121, 106, 15);
		shortestAndLongestPlaylistsPanel.add(lblMostLikedSong);

		lblMostPopularSong = new JLabel("Empty");
		lblMostPopularSong.setHorizontalAlignment(SwingConstants.CENTER);
		lblMostPopularSong.setFont(new Font("Noto Sans", Font.BOLD, 14));
		lblMostPopularSong.setAlignmentX(0.5f);
		lblMostPopularSong.setBounds(388, 121, 106, 15);
		shortestAndLongestPlaylistsPanel.add(lblMostPopularSong);

		lblMostFollowedUser = new JLabel("Empty");
		lblMostFollowedUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblMostFollowedUser.setFont(new Font("Noto Sans", Font.BOLD, 14));
		lblMostFollowedUser.setAlignmentX(0.5f);
		lblMostFollowedUser.setBounds(618, 121, 178, 15);
		shortestAndLongestPlaylistsPanel.add(lblMostFollowedUser);
	}

	public void setController(MusicAppController controller) {
		this.controller = controller;
	}

	public JLabel getMostFollowedUser() {
		return lblMostFollowedUser;
	}

	public JLabel getMostPopularSong() {
		return lblMostPopularSong;
	}

	public JLabel getMostLikedSong() {
		return lblMostLikedSong;
	}

	public JTable getShortestAndLongestPlaylistsTable() {
		return shortestAndLongestPlaylistsTable;
	}
}
