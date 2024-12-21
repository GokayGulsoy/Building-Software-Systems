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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.MusicAppController;

public class NavigationPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MusicAppController controller;

	public NavigationPage() {
		setResizable(false);
		setMinimumSize(new Dimension(950, 530));
		setTitle("Navigation Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel header = new JPanel();
		header.setBackground(new Color(192, 191, 188));
		FlowLayout fl_header = (FlowLayout) header.getLayout();
		fl_header.setAlignment(FlowLayout.LEFT);
		contentPane.add(header, BorderLayout.NORTH);

		JLabel labelNavigationPage = new JLabel("Navigation Page");
		labelNavigationPage.setFont(new Font("FreeSerif", Font.BOLD | Font.ITALIC, 24));
		header.add(labelNavigationPage);

		JPanel navigationPanel = new JPanel();
		navigationPanel.setBackground(new Color(98, 160, 234));
		contentPane.add(navigationPanel, BorderLayout.CENTER);
		navigationPanel.setLayout(null);

		JLabel labelNavigation = new JLabel("Select the appropriate button to navigate to desired page");
		labelNavigation.setBounds(243, 109, 465, 55);
		labelNavigation.setBorder(new LineBorder(new Color(94, 92, 100), 3, true));
		labelNavigation.setFont(new Font("Noto Sans", Font.BOLD, 16));
		labelNavigation.setBackground(new Color(192, 191, 188));
		navigationPanel.add(labelNavigation);

		JButton btnProfilePage = new JButton("Profile Page");
		btnProfilePage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.moveFromNavigationPageToProfilePage();
			}
		});
		btnProfilePage.setBounds(48, 259, 110, 24);
		btnProfilePage.setFont(new Font("Noto Sans", Font.BOLD, 14));
		btnProfilePage.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		navigationPanel.add(btnProfilePage);

		JButton btnPlaylistCreationPage = new JButton("Playlist Creation Page");
		btnPlaylistCreationPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.moveFromNavigationPageToPlaylistCreationPage();
			}
		});
		btnPlaylistCreationPage.setBounds(206, 259, 245, 24);
		btnPlaylistCreationPage.setFont(new Font("Noto Sans", Font.BOLD, 14));
		btnPlaylistCreationPage.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		navigationPanel.add(btnPlaylistCreationPage);

		JButton btnStatisticsPage = new JButton("Statistics Page");
		btnStatisticsPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.fillStatisticalInformation();
				controller.moveFromNavigationPageToStatisticsPage();
			}
		});
		btnStatisticsPage.setBounds(499, 259, 185, 24);
		btnStatisticsPage.setFont(new Font("Noto Sans", Font.BOLD, 14));
		btnStatisticsPage.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		navigationPanel.add(btnStatisticsPage);

		JButton btnFollowedUsersPage = new JButton("Followed Users Page");
		btnFollowedUsersPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setFollowedUsersTable();
				controller.moveFromNavigationPageToFollowedUsersPage();
			}
		});
		btnFollowedUsersPage.setBounds(732, 259, 148, 24);
		btnFollowedUsersPage.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		btnFollowedUsersPage.setFont(new Font("Noto Sans", Font.BOLD, 14));
		navigationPanel.add(btnFollowedUsersPage);

		JButton btnLikeSongPage = new JButton("Like and Listen Song Page");
		btnLikeSongPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.moveFromNavigationPageToLikeSongPage();
			}
		});
		btnLikeSongPage.setBounds(66, 318, 234, 24);
		btnLikeSongPage.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		btnLikeSongPage.setFont(new Font("Noto Sans", Font.BOLD, 14));
		navigationPanel.add(btnLikeSongPage);

		JButton btnFollowunfollowOtherUsers = new JButton("Follow/Unfollow Other Users Page");
		btnFollowunfollowOtherUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.loadUserTable();
				controller.moveFromNavigationPageToFollowUnfollowOtherUsersPage();
			}
		});
		btnFollowunfollowOtherUsers.setBounds(366, 318, 245, 24);
		btnFollowunfollowOtherUsers.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		btnFollowunfollowOtherUsers.setFont(new Font("Noto Sans", Font.BOLD, 14));
		navigationPanel.add(btnFollowunfollowOtherUsers);

		JButton btnAddremoveSongPage = new JButton("Add/Remove Song Page");
		btnAddremoveSongPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.moveFromNavigationPageToAddRemoveSongPage();
			}
		});

		btnAddremoveSongPage.setBounds(677, 318, 185, 24);
		btnAddremoveSongPage.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		btnAddremoveSongPage.setFont(new Font("Noto Sans", Font.BOLD, 14));
		navigationPanel.add(btnAddremoveSongPage);

		JButton btnExitApplication = new JButton("Exit Application");
		btnExitApplication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedOption = JOptionPane.showConfirmDialog(btnExitApplication,
						"You are about the leave the application", "Are You Sure About Leaving ?",
						JOptionPane.YES_NO_OPTION);

				if (selectedOption == 0) { // if yes is chosen end the application
					System.exit(0);
				}

			}
		});

		btnExitApplication.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		btnExitApplication.setFont(new Font("Noto Sans", Font.BOLD, 14));
		btnExitApplication.setBounds(366, 375, 245, 25);
		navigationPanel.add(btnExitApplication);
	}

	public void setController(MusicAppController controller) {
		this.controller = controller;
	}

}
