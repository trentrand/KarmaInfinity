package kpp;

import im.goel.jreddit.submissions.Submission;
import im.goel.jreddit.user.User;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import org.json.simple.parser.ParseException;
import javax.swing.SwingConstants;

public class Screen {

	private JFrame frameApplication;
	private static JFormattedTextField txtUsername;
	private static JPasswordField txtPassword;
	private static JButton btnSignIn;
	private static JButton btnGetKarma;

	private static User user; // store the current user
	private JTextField txtSubReddit;

	private int page = 1;
	private JButton btnPagePrevious;
	private JButton btnPageNext;
	private JLabel lblNewLabel;
	private JButton btnFetchPosts;
	private JLabel lblFetchedPage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.out.println("I love you King Poo Poo Brains :)");
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Screen window = new Screen();
					window.frameApplication.setVisible(true);
				} catch (Exception e) {
					System.out
							.println("Screen's main method invoked an Exception in run()");
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Screen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Initialize and setup JFrame
		frameApplication = new JFrame();
		frameApplication.setTitle("Karma \u221E by applies inc.\n");
		frameApplication.setFont(new Font("Courier New", Font.PLAIN, 12));
		frameApplication.setBounds(100, 100, 450, 529);
		frameApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Initialize and setup Username Textfield
		txtUsername = new JFormattedTextField();
		txtUsername.setText("_karmawhore");
		txtUsername.setBounds(112, 23, 283, 27);
		txtUsername.setFont(new Font("Courier New", Font.PLAIN, 16));
		txtUsername.setToolTipText("Username");

		// Initialize and setup Password TextField
		txtPassword = new JPasswordField();
		txtPassword.setBounds(112, 56, 283, 27);
		txtPassword.setFont(new Font("Courier New", Font.PLAIN, 16));
		txtPassword.setToolTipText("Password");

		// TODO Remove this when released (sets the password to xx380813xx,,)
		txtPassword.setText("xx380813xx,,");

		// Initialize and setup Username JLabel
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(28, 32, 81, 18);
		lblUsername.setFont(new Font("Courier New", Font.BOLD, 15));

		// Initialize and setup Password JLabel
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(28, 65, 81, 18);
		lblPassword.setFont(new Font("Courier New", Font.BOLD, 15));

		// Initialize and setup Sign-In JButton
		btnSignIn = new JButton("Sign In");
		btnSignIn.setBounds(295, 95, 100, 29);
		btnSignIn.setFont(new Font("Courier New", Font.PLAIN, 13));
		btnSignIn.setToolTipText("Click to Sign In");

		// Add Buttons to JFrame <frameApplcation>
		frameApplication.getContentPane().setLayout(null);
		frameApplication.getContentPane().add(lblUsername);
		frameApplication.getContentPane().add(txtUsername);
		frameApplication.getContentPane().add(lblPassword);
		frameApplication.getContentPane().add(txtPassword);
		frameApplication.getContentPane().add(btnSignIn);

		// Initialize and setup separator JSeperator
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 121, 438, 12);
		frameApplication.getContentPane().add(separator);

		// Initialize and setup Post-Address JLabel
		JLabel lblPageName = new JLabel("Page Name:");
		lblPageName.setFont(new Font("Courier New", Font.PLAIN, 16));
		lblPageName.setBounds(77, 478, 109, 16);
		frameApplication.getContentPane().add(lblPageName);

		// Initialize and setup Get-Karma JButton
		btnGetKarma = new JButton("Get Karma");
		btnGetKarma.setFont(new Font("Courier New", Font.PLAIN, 13));
		btnGetKarma.setBounds(327, 474, 117, 29);
		btnGetKarma.setEnabled(false);
		frameApplication.getContentPane().add(btnGetKarma);

		txtSubReddit = new JTextField();
		txtSubReddit.setFont(new Font("Courier New", Font.PLAIN, 13));
		txtSubReddit.setBounds(183, 472, 143, 28);
		frameApplication.getContentPane().add(txtSubReddit);
		txtSubReddit.setColumns(10);

		JList listPosts = new JList();
		listPosts.setToolTipText("Posts from " + txtSubReddit.getText());
		listPosts.setBounds(28, 430, 364, -252);
		frameApplication.getContentPane().add(listPosts);

		btnPagePrevious = new JButton("Previous");
		btnPagePrevious.setFont(new Font("Courier New", Font.PLAIN, 13));
		btnPagePrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (page > 1) {
					page--;
				}
			}
		});
		btnPagePrevious.setBounds(16, 127, 105, 29);
		frameApplication.getContentPane().add(btnPagePrevious);

		btnPageNext = new JButton("Next");
		btnPageNext.setFont(new Font("Courier New", Font.PLAIN, 13));
		btnPageNext.setBounds(305, 127, 105, 29);
		frameApplication.getContentPane().add(btnPageNext);

		lblNewLabel = new JLabel("Page: " + page);
		lblNewLabel.setFont(new Font("Courier New", Font.PLAIN, 15));
		lblNewLabel.setBounds(212, 132, 100, 16);
		frameApplication.getContentPane().add(lblNewLabel);

		btnFetchPosts = new JButton("Fetch Posts");
		btnFetchPosts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFetchPosts();
			}
		});
		btnFetchPosts.setFont(new Font("Courier New", Font.PLAIN, 13));
		btnFetchPosts.setBounds(327, 430, 117, 29);
		frameApplication.getContentPane().add(btnFetchPosts);
		
		ButtonGroup btnGroupPageType = new ButtonGroup();
		
		JRadioButton rdBtnTypeUser = new JRadioButton("/u/");
		rdBtnTypeUser.setBounds(6, 458, 141, 23);
		frameApplication.getContentPane().add(rdBtnTypeUser);
		
		JRadioButton rdBtnTypeSubreddit = new JRadioButton("/r/");
		rdBtnTypeSubreddit.setBounds(6, 478, 141, 23);
		frameApplication.getContentPane().add(rdBtnTypeSubreddit);
		
		btnGroupPageType.add(rdBtnTypeUser);
		btnGroupPageType.add(rdBtnTypeSubreddit);
		
		lblFetchedPage = new JLabel();
		lblFetchedPage.setFont(new Font("Courier New", Font.PLAIN, 13));
		lblFetchedPage.setHorizontalAlignment(SwingConstants.CENTER);
		lblFetchedPage.setBounds(112, 132, 109, 16);
		lblFetchedPage.setText(this.getTxtSubReddit().getText());
		frameApplication.getContentPane().add(lblFetchedPage);
		

		/*
		 * Setup Action Listeners
		 */
		btnSignIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getBtnSignIn().setEnabled(false); // doesnt work :/
				try {
					btnSignInPressed();
					Screen.getTxtPassword().setForeground(Color.green);
					System.out.println("User signed in correctly");
					getBtnGetKarma().setEnabled(true); // now that user is
														// signed in, you can
														// use <user> method
														// buttons
					btnSignIn.setEnabled(true); // reenable <btnSignIn> as
												// enabled since UI unfroze
				} catch (Exception e1) {
					btnSignIn.setEnabled(true); // reenable <btnSignIn> as
												// enabled since UI unfroze
					System.out.println("btnSignIn invoked an Exception");
					Screen.getTxtPassword().setForeground(Color.red);
					System.out.println(getTxtPassword().getText());
					System.out.println("Could Not Sign In User");
				}
			}
		});
		btnGetKarma.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					btnGetKarmaPressed("1Voice1Life"); // make this non-static,
														// using GUI
				} catch (IOException e1) {
					System.out
							.println("btnGetKarmaPressed invoked an Exception <IOException>");
					e1.printStackTrace();
				} catch (ParseException e1) {
					System.out
							.println("btnGetKarmaPressed invoked an Exception <IOException>");
					e1.printStackTrace();
				}
			}
		});
	}

	/*
	 * Action Listener Implementations
	 */

	protected void btnSignInPressed() throws Exception {
		user = new User(getTxtUsername().getText(), getTxtPassword().getText());
		user.connect();
	}

	protected void btnGetKarmaPressed(String username) throws IOException,
			ParseException {
//		castUpvote();

	}

	protected void btnFetchPosts() {

	}

	public void castUpvote(String slashType, String subredditName, int page,
			int index, User user) throws IOException, ParseException {

		Submission submission = Submission.getSubmission(slashType, subredditName, page,
				index, user);
		System.out.println("Upvoting Post: " + submission.getTitle());
		submission.upVote();
	}

	/*
	 * Getters
	 */

	/**
	 * @return the frameApplication
	 */
	public JFrame getFrameApplication() {
		return frameApplication;
	}

	/**
	 * @return the btnSignIn
	 */
	public static JButton getBtnSignIn() {
		return btnSignIn;
	}

	/**
	 * @return the txtSubReddit
	 */
	public JTextField getTxtSubReddit() {
		return txtSubReddit;
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @return the btnPagePrevious
	 */
	public JButton getBtnPagePrevious() {
		return btnPagePrevious;
	}

	/**
	 * @return the btnPageNext
	 */
	public JButton getBtnPageNext() {
		return btnPageNext;
	}

	/**
	 * @return the lblNewLabel
	 */
	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	/**
	 * @return the btnFetchPosts
	 */
	public JButton getBtnFetchPosts() {
		return btnFetchPosts;
	}

	/**
	 * @return the lblFetchedPage
	 */
	public JLabel getLblFetchedPage() {
		return lblFetchedPage;
	}

	/**
	 * @return the txtUsername
	 */
	public static JFormattedTextField getTxtUsername() {
		return txtUsername;
	}

	/**
	 * @return the txtPassword
	 */
	public static JPasswordField getTxtPassword() {
		return txtPassword;
	}

	/**
	 * @return the btnGetKarma
	 */
	public static JButton getBtnGetKarma() {
		return btnGetKarma;
	}

	/**
	 * @return the user
	 */
	public static User getUser() {
		return user;
	}
}
