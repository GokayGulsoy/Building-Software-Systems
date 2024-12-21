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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controller.MusicAppController;

public class FollowUnfollowOtherUsersPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable followUnfollowUsersTable;
	private MusicAppController controller;

	@SuppressWarnings("serial")
	public FollowUnfollowOtherUsersPage() {
		setResizable(false);
		setMinimumSize(new Dimension(815, 605));
		setTitle("Follow/Unfollow Other Users Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 815, 605);
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

		JLabel labelFollowOrUnfollowHeader = new JLabel("Follow or Unfollow Other Users Page");
		labelFollowOrUnfollowHeader.setFont(new Font("FreeSerif", Font.BOLD | Font.ITALIC, 24));
		header.add(labelFollowOrUnfollowHeader);

		JPanel followUnfollowUsersPanel = new JPanel();
		followUnfollowUsersPanel.setBackground(new Color(98, 160, 234));
		contentPane.add(followUnfollowUsersPanel, BorderLayout.CENTER);
		followUnfollowUsersPanel.setLayout(null);

		JLabel labelSelectUserRow = new JLabel("Select Row in Table In order to Follow or Unfollow User");
		labelSelectUserRow.setBounds(220, 102, 384, 20);
		labelSelectUserRow.setHorizontalAlignment(SwingConstants.CENTER);
		labelSelectUserRow.setFont(new Font("Noto Sans", Font.BOLD, 14));
		followUnfollowUsersPanel.add(labelSelectUserRow);

		JScrollPane usernameScrollPane = new JScrollPane();
		usernameScrollPane.setBounds(220, 127, 384, 175);
		followUnfollowUsersPanel.add(usernameScrollPane);

		followUnfollowUsersTable = new JTable();
		followUnfollowUsersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		followUnfollowUsersTable.setFont(new Font("Noto Sans", Font.PLAIN, 14));
		followUnfollowUsersTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "User Name" }) {
			boolean[] columnEditables = new boolean[] { false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		followUnfollowUsersTable.getColumnModel().getColumn(0).setPreferredWidth(263);
		followUnfollowUsersTable.setBackground(new Color(192, 191, 188));
		followUnfollowUsersTable.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		usernameScrollPane.setViewportView(followUnfollowUsersTable);

		JButton btnFollowUser = new JButton("Follow User");
		btnFollowUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.followUser();
			}
		});

		btnFollowUser.setBounds(280, 337, 324, 24);
		btnFollowUser.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		btnFollowUser.setFont(new Font("Noto Sans", Font.BOLD, 14));
		followUnfollowUsersPanel.add(btnFollowUser);

		JButton btnUnfollowUser = new JButton("Unfollow User");
		btnUnfollowUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.unfollowUser();
			}
		});

		btnUnfollowUser.setBounds(280, 396, 324, 24);
		btnUnfollowUser.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		btnUnfollowUser.setFont(new Font("Noto Sans", Font.BOLD, 14));
		followUnfollowUsersPanel.add(btnUnfollowUser);

		JButton btnGoToNavigation = new JButton("Go to Navigation Page");
		btnGoToNavigation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.moveToNavigationPage(FollowUnfollowOtherUsersPage.this);
			}
		});

		btnGoToNavigation.setBounds(280, 455, 324, 24);
		btnGoToNavigation.setBorder(new LineBorder(new Color(119, 118, 123), 2, true));
		btnGoToNavigation.setFont(new Font("Noto Sans", Font.BOLD, 14));
		followUnfollowUsersPanel.add(btnGoToNavigation);
	}

	public void setController(MusicAppController controller) {
		this.controller = controller;

	}

	public JTable getUserTable() {
		return followUnfollowUsersTable;
	}

}
