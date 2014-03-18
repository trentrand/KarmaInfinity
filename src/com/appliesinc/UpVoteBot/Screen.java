package com.appliesinc.UpVoteBot;

import im.goel.jreddit.submissions.Submission;
import im.goel.jreddit.subreddit.Subreddit;
import im.goel.jreddit.user.User;
import im.goel.jreddit.utils.Utils;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.json.simple.parser.ParseException;

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
	private JLabel lblPageNumber;
	private JButton btnFetchPosts;
	private JLabel lblFetchedPage;
	private ButtonGroup btnGroupPageType;
	private JRadioButton rdBtnTypeUser;
	private JRadioButton rdBtnTypeSubreddit;
	private JList<String> listSubmissions;
	private DefaultListModel<String> submissionList;
	private List<Submission> submissionsFetched;
	private JPanel multiPanel;
	private String[] subreddits = { "pics", "funny", "gaming", "AskReddit", "worldnews", "news", "videos", "iama", "todayilearned", "aww", "technology",           
			"adviceanimals", "science", "music", "movies", "bestof", "books", "earthporn", "explainlikeimfive", "gifs", "television", "askscience",            
			"sports", "mildlyinteresting", "lifeprotips", "woahdude", "unexpected", "reactiongifs", "showerthoughts", "food", "jokes", "photoshopbattles", 
			"firstworldanarchists", "foodporn", "historyporn", "wtf", "leagueoflegends", "cringepics", "trees", ""};      

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.out.println("I love you King Poo Poo Brains :)"); //$NON-NLS-1$
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Screen window = new Screen();
					window.frameApplication.setVisible(true);
				} catch (Exception e) {
					System.out
							.println("Screen's main method invoked an Exception in run()"); //$NON-NLS-1$
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
		// TODO Set the proxy
//		setProxy("190.73.140.191", "8080"); //$NON-NLS-1$ //$NON-NLS-2$
		// TODO Maybe have them browse a subreddit first, then goto propper one,
		try {
			browseRandom();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		// TODO upvote a random then upvote the selected
		// TODO Randomize upvote or downvote so it looks real
		// TODO Set a delay between accounts
		// TODO Loop through accounts
		// TODO Have bots upvote and downvote random submssions so they don't
		// all share the same activity
		// Initialize and setup JFrame
		frameApplication = new JFrame();
		frameApplication.setTitle("Karma \u221E by applies inc.\n"); //$NON-NLS-1$
		frameApplication.setFont(new Font("Courier New", Font.PLAIN, 12)); //$NON-NLS-1$
		frameApplication.setBounds(100, 100, 450, 604);
		frameApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add Buttons to JFrame <frameApplcation>
		frameApplication.getContentPane().setLayout(null);

		// Initialize and setup separator JSeperator
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 165, 438, 12);
		frameApplication.getContentPane().add(separator);

		// Initialize and setup Post-Address JLabel
		JLabel lblPageName = new JLabel("Page Name:"); //$NON-NLS-1$
		lblPageName.setFont(new Font("Courier New", Font.PLAIN, 16)); //$NON-NLS-1$
		lblPageName.setBounds(77, 551, 109, 16);
		frameApplication.getContentPane().add(lblPageName);

		// Initialize and setup Get-Karma JButton
		btnGetKarma = new JButton("Get Karma"); //$NON-NLS-1$
		btnGetKarma.setFont(new Font("Courier New", Font.PLAIN, 13)); //$NON-NLS-1$
		btnGetKarma.setBounds(327, 547, 117, 29);
		btnGetKarma.setEnabled(false);
		frameApplication.getContentPane().add(btnGetKarma);

		txtSubReddit = new JTextField();
		txtSubReddit.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				getLblFetchedPage().setText(
						getSlashType() + getTxtSubReddit().getText());
			}
		});
		txtSubReddit.setFont(new Font("Courier New", Font.PLAIN, 13)); //$NON-NLS-1$
		txtSubReddit.setBounds(183, 545, 143, 28);
		frameApplication.getContentPane().add(txtSubReddit);
		txtSubReddit.setColumns(10);

		submissionList = new DefaultListModel<String>();

		btnPagePrevious = new JButton("Previous"); //$NON-NLS-1$
		btnPagePrevious.setEnabled(false);
		btnPagePrevious.setFont(new Font("Courier New", Font.PLAIN, 13)); //$NON-NLS-1$
		btnPagePrevious.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (page > 1) {
					page--;
				}
			}
		});
		btnPagePrevious.setBounds(38, 182, 105, 29);
		frameApplication.getContentPane().add(btnPagePrevious);

		btnPageNext = new JButton("Next"); //$NON-NLS-1$
		btnPageNext.setEnabled(false);
		btnPageNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				page++;
			}
		});
		btnPageNext.setFont(new Font("Courier New", Font.PLAIN, 13)); //$NON-NLS-1$
		btnPageNext.setBounds(305, 182, 105, 29);
		frameApplication.getContentPane().add(btnPageNext);

		lblPageNumber = new JLabel("Page: " + page); //$NON-NLS-1$
		lblPageNumber.setEnabled(false);
		lblPageNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblPageNumber.setFont(new Font("Courier New", Font.PLAIN, 15)); //$NON-NLS-1$
		lblPageNumber.setBounds(140, 187, 168, 16);
		frameApplication.getContentPane().add(lblPageNumber);

		btnFetchPosts = new JButton("Fetch Posts"); //$NON-NLS-1$
		btnFetchPosts.setEnabled(false);
		btnFetchPosts.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					btnFetchPosts();
				} catch (IOException e1) {
					System.out
							.println("btnFetchPosts invoked an Exception <IOException>"); //$NON-NLS-1$
					e1.printStackTrace();
				} catch (ParseException e1) {
					System.out
							.println("btnFetchPosts invoked an Exception <ParseException>"); //$NON-NLS-1$
					e1.printStackTrace();
				}
			}
		});
		btnFetchPosts.setFont(new Font("Courier New", Font.PLAIN, 13)); //$NON-NLS-1$
		btnFetchPosts.setBounds(315, 501, 117, 29);
		frameApplication.getContentPane().add(btnFetchPosts);

		lblFetchedPage = new JLabel();
		lblFetchedPage.setFont(new Font("Courier New", Font.PLAIN, 13)); //$NON-NLS-1$
		lblFetchedPage.setBounds(28, 501, 285, 18);
		lblFetchedPage.setText(this.getTxtSubReddit().getText());
		frameApplication.getContentPane().add(lblFetchedPage);

		btnGroupPageType = new ButtonGroup();

		rdBtnTypeSubreddit = new JRadioButton("/r/"); //$NON-NLS-1$
		rdBtnTypeSubreddit.setBounds(6, 551, 141, 23);
		rdBtnTypeSubreddit.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				getLblFetchedPage().setText(
						getSlashType() + getTxtSubReddit().getText());
			}
		});

		rdBtnTypeUser = new JRadioButton("/u/"); //$NON-NLS-1$
		rdBtnTypeUser.setBounds(6, 531, 141, 23);
		rdBtnTypeUser.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				getLblFetchedPage().setText(
						getSlashType() + getTxtSubReddit().getText());
			}
		});

		btnGroupPageType.add(rdBtnTypeUser);
		btnGroupPageType.add(rdBtnTypeSubreddit);

		frameApplication.getContentPane().add(rdBtnTypeSubreddit);
		frameApplication.getContentPane().add(rdBtnTypeUser);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(28, 6, 404, 171);
		frameApplication.getContentPane().add(tabbedPane);

		JPanel singlePanel = new JPanel();
		tabbedPane.addTab("Single User", null, singlePanel, null); //$NON-NLS-1$
		singlePanel.setLayout(null);

		// Initialize and setup Username JLabel
		JLabel lblUsername = new JLabel("Username:"); //$NON-NLS-1$
		lblUsername.setBounds(6, 13, 81, 18);
		singlePanel.add(lblUsername);
		lblUsername.setFont(new Font("Courier New", Font.BOLD, 15)); //$NON-NLS-1$

		// Initialize and setup Password JLabel
		JLabel lblPassword = new JLabel("Password:"); //$NON-NLS-1$
		lblPassword.setBounds(6, 43, 81, 18);
		singlePanel.add(lblPassword);
		lblPassword.setFont(new Font("Courier New", Font.BOLD, 15)); //$NON-NLS-1$

		// Initialize and setup Password TextField
		txtPassword = new JPasswordField();
		txtPassword.setBounds(99, 36, 257, 31);
		singlePanel.add(txtPassword);
		txtPassword.setFont(new Font("Courier New", Font.PLAIN, 16)); //$NON-NLS-1$
		txtPassword.setToolTipText("Password"); //$NON-NLS-1$

		txtPassword.setText("password"); //$NON-NLS-1$

		// Initialize and setup Username Textfield
		txtUsername = new JFormattedTextField();
		txtUsername.setBounds(99, 6, 257, 31);
		singlePanel.add(txtUsername);
		txtUsername.setText("KarmaInfinityBot"); //$NON-NLS-1$
		txtUsername.setFont(new Font("Courier New", Font.PLAIN, 16)); //$NON-NLS-1$
		txtUsername.setToolTipText("Username"); //$NON-NLS-1$

		// Initialize and setup Sign-In JButton
		btnSignIn = new JButton("Sign In"); //$NON-NLS-1$
		btnSignIn.setBounds(256, 79, 100, 29);
		singlePanel.add(btnSignIn);
		btnSignIn.setFont(new Font("Courier New", Font.PLAIN, 13)); //$NON-NLS-1$
		btnSignIn.setToolTipText("Click to Sign In"); //$NON-NLS-1$

		multiPanel = new JPanel();
		tabbedPane.addTab("Multiple Users", null, multiPanel, null); //$NON-NLS-1$

		JLabel lblTodo = new JLabel("TODO"); //$NON-NLS-1$
		multiPanel.add(lblTodo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 211, 382, 278);
		frameApplication.getContentPane().add(scrollPane);

		listSubmissions = new JList<String>(submissionList);
		scrollPane.setViewportView(listSubmissions);
		listSubmissions.setBorder(BorderFactory
				.createTitledBorder("Submissions")); //$NON-NLS-1$
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(0, 0, 444, 576);
		frameApplication.getContentPane().add(tabbedPane_1);

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
					System.out.println("User signed in correctly"); //$NON-NLS-1$
					btnSignIn.setEnabled(true); // reenable <btnSignIn> as
												// enabled since UI unfroze
				} catch (Exception e1) {
					btnSignIn.setEnabled(true); // reenable <btnSignIn> as
												// enabled since UI unfroze
					System.out.println("btnSignIn invoked an Exception"); //$NON-NLS-1$
					Screen.getTxtPassword().setForeground(Color.red);
					System.out.println(getTxtPassword().getText());
					System.out.println("Could Not Sign In User"); //$NON-NLS-1$
				}
			}
		});
		btnGetKarma.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					btnGetKarmaPressed(); // make this non-static,
											// using GUI
				} catch (IOException e1) {
					System.out
							.println("btnGetKarmaPressed invoked an Exception <IOException>"); //$NON-NLS-1$
					e1.printStackTrace();
				} catch (ParseException e1) {
					System.out
							.println("btnGetKarmaPressed invoked an Exception <IOException>"); //$NON-NLS-1$
					e1.printStackTrace();
				}
			}
		});
	}

	private void browseRandom() throws IOException, ParseException {
//		//do some random stuff
//		user.hasMail(); //check mail
//		user.linkKarma(); //check linkKarma
//		user.commentKarma(); //check commentKarma
//		
//		List<Subreddit> subreddits = subreddits.getSubreddits(SubredditType.NEW); //grab a random subreddit TODO set random
//		// Assuming user is a connected User instance:
//		List<Submission> sm = user.getDislikedSubmissions();
//		// Assuming user is a connected User instance:
//		List<Submission> sm = user.getHiddenSubmissions();
//		// Assuming user is a connected User instance:
//		List<Submission> sm = user.getLikedSubmissions();
//		// Assuming user is a connected User instance:
//		List<Submission> sm = user.getSubmissions();
//		UserInfo ui = User.about("some_username");
//		List<Comment> comments = User.comments("some_username");
//		// Assuming user is an initialized User instance:
//		List<Submission> saved = user.getSavedSubmissions();

	}

	private void setProxy(String proxy, String port) {
		Utils.setProxyHost(proxy);
		Utils.setProxyPort(port);
	}

	/*
	 * Action Listener Implementations
	 */

	protected void btnSignInPressed() throws Exception {
		user = new User(getTxtUsername().getText(), getTxtPassword().getText());
		user.connect();
		getBtnFetchPosts().setEnabled(true);
	}

	protected void btnGetKarmaPressed() throws IOException, ParseException {
		Submission submission = Submission.getSubmission(getLblFetchedPage()
				.getText(), getListSubmissions().getSelectedIndex(), user);

		System.out.println("Upvoting Post: " + submission.getTitle()); //$NON-NLS-1$
		submission.upVote();

	}

	public String getSlashType() {
		if (this.rdBtnTypeSubreddit.isSelected()) {
			return rdBtnTypeSubreddit.getText();
		} else {
			return rdBtnTypeUser.getText();
		}
	}

	protected void btnFetchPosts() throws IOException, ParseException {
		submissionsFetched = user.getSubmissions(getSlashType()
				+ getTxtSubReddit().getText() + "/"); //$NON-NLS-1$
		for (int i = 0; i < submissionsFetched.size(); i++) {
			submissionList.add(i, "[" + submissionsFetched.get(i).getAuthor() //$NON-NLS-1$
					+ "] " + submissionsFetched.get(i).getTitle()); //$NON-NLS-1$
		}
		getBtnGetKarma().setEnabled(true);
	}

	/**
	 * @return the lblPageNumber
	 */
	public JLabel getLblPageNumber() {
		return lblPageNumber;
	}

	/**
	 * @return the btnGroupPageType
	 */
	public ButtonGroup getBtnGroupPageType() {
		return btnGroupPageType;
	}

	/**
	 * @return the rdBtnTypeUser
	 */
	public JRadioButton getRdBtnTypeUser() {
		return rdBtnTypeUser;
	}

	/**
	 * @return the rdBtnTypeSubreddit
	 */
	public JRadioButton getRdBtnTypeSubreddit() {
		return rdBtnTypeSubreddit;
	}

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
		return lblPageNumber;
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

	/**
	 * @return the submissionList
	 */
	public DefaultListModel<String> getSubmissionList() {
		return submissionList;
	}

	/**
	 * @return the submissionsFetched
	 */
	public List<Submission> getSubmissionsFetched() {
		return submissionsFetched;
	}

	/**
	 * @return the multiPanel
	 */
	public JPanel getMultiPanel() {
		return multiPanel;
	}

	/**
	 * @return the listSubmissions
	 */
	public JList<String> getListSubmissions() {
		return listSubmissions;
	}
}