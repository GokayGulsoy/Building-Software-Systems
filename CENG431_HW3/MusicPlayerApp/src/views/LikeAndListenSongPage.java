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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controller.MusicAppController;

public class LikeAndListenSongPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField songGenreTextField;
	private JTable songTable;
	private MusicAppController controller;

	@SuppressWarnings("serial")
	public LikeAndListenSongPage() {
		setResizable(false);
		setMinimumSize(new Dimension(980, 665));
		setTitle("Like and Listen Song Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1020, 665);
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

		JLabel labelLikeSongPage = new JLabel("Like and Listen Song Page");
		labelLikeSongPage.setFont(new Font("FreeSerif", Font.BOLD | Font.ITALIC, 24));
		header.add(labelLikeSongPage);

		JPanel likeSongPanel = new JPanel();
		likeSongPanel.setBackground(new Color(98, 160, 234));
		contentPane.add(likeSongPanel, BorderLayout.CENTER);
		likeSongPanel.setLayout(null);

		JLabel labelPlaylistGenre = new JLabel("Enter the song genre:");
		labelPlaylistGenre.setBounds(204, 35, 151, 24);
		labelPlaylistGenre.setFont(new Font("Noto Sans", Font.BOLD, 14));
		likeSongPanel.add(labelPlaylistGenre);

		songGenreTextField = new JTextField();
		songGenreTextField.setBounds(360, 35, 550, 24);
		songGenreTextField.setFont(new Font("Noto Sans", Font.PLAIN, 14));
		songGenreTextField.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		likeSongPanel.add(songGenreTextField);
		songGenreTextField.setColumns(10);

		JScrollPane songScrollPane = new JScrollPane();
		songScrollPane.setBounds(24, 64, 951, 422);
		likeSongPanel.add(songScrollPane);

		songTable = new JTable();
		songTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		songTable.setBackground(new Color(192, 191, 188));
		songTable.setBorder(new LineBorder(new Color(192, 191, 188), 2, true));
		songTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "genre id", "track id", "song",
				"artist", "duration", "popularity", "number of likes" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		songTable.getColumnModel().getColumn(6).setPreferredWidth(120);
		songScrollPane.setViewportView(songTable);

		JButton btnBringSongsOn = new JButton("Bring songs on the specified genre");
		btnBringSongsOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringSongsForGivenGenre();
			}
		});

		btnBringSongsOn.setBounds(24, 521, 265, 24);
		btnBringSongsOn.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		btnBringSongsOn.setFont(new Font("Noto Sans", Font.BOLD, 14));
		likeSongPanel.add(btnBringSongsOn);

		JButton btnGoToNavigation = new JButton("Go to Navigation Page");
		btnGoToNavigation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.moveToNavigationPage(LikeAndListenSongPage.this);
				controller.setLikeAndListenSongPageToDefault();
			}
		});

		btnGoToNavigation.setBounds(360, 521, 160, 24);
		btnGoToNavigation.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		btnGoToNavigation.setFont(new Font("Noto Sans", Font.BOLD, 14));
		likeSongPanel.add(btnGoToNavigation);

		JButton btnLikeSong = new JButton("Like Song");
		btnLikeSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.likeSong();
			}
		});

		btnLikeSong.setBounds(645, 521, 145, 24);
		btnLikeSong.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		btnLikeSong.setFont(new Font("Noto Sans", Font.BOLD, 14));
		likeSongPanel.add(btnLikeSong);

		JButton btnListenSong = new JButton("Listen Song");
		btnListenSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.listenSong();
			}
		});

		btnListenSong.setBounds(825, 521, 145, 24);
		btnListenSong.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		btnListenSong.setFont(new Font("Noto Sans", Font.BOLD, 14));
		likeSongPanel.add(btnListenSong);
	}

	public void setController(MusicAppController controller) {
		this.controller = controller;

	}

	public JTextField getSongGenre() {
		return songGenreTextField;
	}

	public JTable getSongTable() {
		return songTable;
	}

}
