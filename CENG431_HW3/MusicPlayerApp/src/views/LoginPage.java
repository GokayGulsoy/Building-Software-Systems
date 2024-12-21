package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.MusicAppController;

public class LoginPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private MusicAppController controller;

	/**
	 * LoginPage for taking username and password as input and forwarding user to
	 * profile page iff given username and password exists and matches with the
	 * username and password in the XML file
	 */
	public LoginPage() {
		setResizable(false);
		setMinimumSize(new Dimension(795, 530));
		setName("loginFrame");
		setTitle("Login Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel header = new JPanel();
		header.setBorder(new LineBorder(new Color(94, 92, 100), 3, true));
		header.setBackground(new Color(192, 191, 188));
		contentPane.add(header, BorderLayout.NORTH);
		header.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));

		JLabel lblLoginPage = new JLabel("Login Page");
		lblLoginPage.setBackground(new Color(255, 255, 255));
		lblLoginPage.setFont(new Font("FreeSerif", Font.BOLD | Font.ITALIC, 24));
		lblLoginPage.setHorizontalTextPosition(SwingConstants.CENTER);
		lblLoginPage.setHorizontalAlignment(SwingConstants.CENTER);
		header.add(lblLoginPage);

		JPanel westLoginIconPanel = new JPanel();
		westLoginIconPanel.setBackground(new Color(154, 153, 150));
		contentPane.add(westLoginIconPanel, BorderLayout.WEST);
		westLoginIconPanel.setLayout(new BorderLayout(0, 0));

		JLabel label = new JLabel("");
		label.setAlignmentX(0.8f);
		label.setFont(new Font("Dialog", Font.BOLD, 20));
		label.setIcon(
				new ImageIcon("/home/gokay/eclipse-workspace/MusicPlayerApplication/resources/icons8-login-48.png"));
		westLoginIconPanel.add(label);
		label.setHorizontalAlignment(SwingConstants.LEFT);

		JPanel loginForm = new JPanel();
		loginForm.setBackground(new Color(44, 198, 142));
		contentPane.add(loginForm, BorderLayout.CENTER);
		loginForm.setLayout(null);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(97, 92, 82, 23);
		lblUsername.setFont(new Font("Noto Sans", Font.BOLD, 16));
		loginForm.add(lblUsername);

		usernameField = new JTextField();
		usernameField.setBounds(244, 92, 450, 24);
		usernameField.setFont(new Font("Noto Sans", Font.PLAIN, 14));
		usernameField.setBorder(new LineBorder(new Color(94, 92, 100), 2, true));
		loginForm.add(usernameField);
		usernameField.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(102, 151, 77, 23);
		lblPassword.setFont(new Font("Noto Sans", Font.BOLD, 16));
		loginForm.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(244, 151, 450, 24);
		passwordField.setFont(new Font("Noto Sans", Font.PLAIN, 14));
		passwordField.setBorder(new LineBorder(new Color(94, 92, 100), 2, true));
		loginForm.add(passwordField);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				char[] password = passwordField.getPassword();

				String passwordString = "";
				for (int i = 0; i < password.length; i++) {
					passwordString += password[i];
				}

				controller.navigateFromLoginToProfilePage(username, passwordString);
			}
		});

		btnLogin.setBounds(334, 210, 360, 27);
		btnLogin.setBorder(new LineBorder(new Color(94, 92, 100), 2, true));
		btnLogin.setFont(new Font("Noto Sans", Font.BOLD, 16));
		loginForm.add(btnLogin);
	}

	public void setController(MusicAppController controller) {
		this.controller = controller;
	}

}
