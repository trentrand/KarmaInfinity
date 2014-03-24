package com.appliesinc.UpVoteBot;

import im.goel.jreddit.submissions.Submission;

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

// TODO: Auto-generated Javadoc
/**
 * The Class View.
 */
public class View {

	/** The window. */
	public static View window;

	/** The Logic object to use. */
	public static Logic logic;
	
	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		/**
		 * Arnolyn wrote this haha, she's such a great coder
		 * System.out.println("I love you King Poo Poo Brains :)");
		 */
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					window = new View(); // initialize
					logic = new Logic(window);

					window.frameApplication.setVisible(true);
				} catch (Exception e) {
					System.out
							.println("Screen's main method invoked an Exception in run()");
					e.printStackTrace();
				}
			}
		});
	}

	/** The btn fetch posts. */
	private JButton btnFetchPosts;

	/** The btn get karma. */
	private JButton btnGetKarma;

	/** The btn group page type. */
	private ButtonGroup btnGroupPageType;

	/** The btn page next. */
	private JButton btnPageNext;

	/** The btn page previous. */
	private JButton btnPagePrevious;

	/** The btn sign in. */
	private JButton btnSignIn;

	/** The frame application. */
	private JFrame frameApplication;

	/** The lbl fetched page. */
	private JLabel lblFetchedPage;

	/** The lbl page number. */
	private JLabel lblPageNumber;

	/** The list submissions. */
	private JList<String> listSubmissions;

	/** The multi panel. */
	private JPanel multiPanel;

	/** The page. */
	private int page = 1;

	/** The rd btn type subreddit. */
	private JRadioButton rdBtnTypeSubreddit;

	/** The rd btn type user. */
	private JRadioButton rdBtnTypeUser;

	/** The submission list. */
	private DefaultListModel<String> submissionList;

	/** The submissions fetched. */
	private List<Submission> submissionsFetched;

	/** The txt password. */
	private JPasswordField txtPassword;

	/** The txt sub reddit. */
	private JTextField txtSubReddit;

	/** The txt username. */
	private JFormattedTextField txtUsername;

	/**
	 * Instantiates a new view.
	 */
	public View() {
		initialize();
	}

	/**
	 * Initialize.
	 */
	private void initialize() {

		// Initialize and setup frameApplication JFrame
		frameApplication = new JFrame();
		frameApplication.setTitle("Karma \u221E by applies inc.\n");
		frameApplication.setFont(new Font("Courier New", Font.PLAIN, 12));
		frameApplication.setBounds(100, 100, 451, 604);
		frameApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameApplication.getContentPane().setLayout(null);

		// Initialize and setup separator JSeperator
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 165, 438, 12);
		frameApplication.getContentPane().add(separator);

		// Initialize and setup Post-Address JLabel
		JLabel lblPageName = new JLabel("Page Name:");
		lblPageName.setBounds(77, 551, 109, 16);
		lblPageName.setFont(new Font("Courier New", Font.PLAIN, 16));
		frameApplication.getContentPane().add(lblPageName);

		// Initialize and setup Get-Karma JButton
		btnGetKarma = new JButton("Get Karma");
		btnGetKarma.setBounds(327, 547, 117, 29);
		btnGetKarma.setFont(new Font("Courier New", Font.PLAIN, 13));
		btnGetKarma.setEnabled(false);
		frameApplication.getContentPane().add(btnGetKarma);

		txtSubReddit = new JTextField();
		txtSubReddit.setBounds(183, 545, 143, 28);
		txtSubReddit.setFont(new Font("Courier New", Font.PLAIN, 13));
		frameApplication.getContentPane().add(txtSubReddit);
		txtSubReddit.setColumns(10);

		submissionList = new DefaultListModel<String>();

		btnPagePrevious = new JButton("Previous");
		btnPagePrevious.setBounds(38, 182, 105, 29);
		btnPagePrevious.setEnabled(false);
		btnPagePrevious.setFont(new Font("Courier New", Font.PLAIN, 13));
		frameApplication.getContentPane().add(btnPagePrevious);

		btnPageNext = new JButton("Next");
		btnPageNext.setBounds(305, 182, 105, 29);
		btnPageNext.setEnabled(false);
		btnPageNext.setFont(new Font("Courier New", Font.PLAIN, 13));
		frameApplication.getContentPane().add(btnPageNext);

		lblPageNumber = new JLabel("Page: " + page);
		lblPageNumber.setBounds(140, 187, 168, 16);
		lblPageNumber.setEnabled(false);
		lblPageNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblPageNumber.setFont(new Font("Courier New", Font.PLAIN, 15));
		frameApplication.getContentPane().add(lblPageNumber);

		btnFetchPosts = new JButton("Fetch Posts");
		btnFetchPosts.setBounds(315, 501, 117, 29);
		btnFetchPosts.setEnabled(false);
		btnFetchPosts.setFont(new Font("Courier New", Font.PLAIN, 13));
		frameApplication.getContentPane().add(btnFetchPosts);

		lblFetchedPage = new JLabel();
		lblFetchedPage.setBounds(28, 501, 285, 18);
		lblFetchedPage.setFont(new Font("Courier New", Font.PLAIN, 13));
		lblFetchedPage.setText(this.getTxtSubReddit().getText());
		frameApplication.getContentPane().add(lblFetchedPage);

		btnGroupPageType = new ButtonGroup();

		rdBtnTypeSubreddit = new JRadioButton("/r/");
		rdBtnTypeSubreddit.setBounds(6, 551, 141, 23);

		rdBtnTypeUser = new JRadioButton("/u/");
		rdBtnTypeUser.setBounds(6, 531, 141, 23);

		btnGroupPageType.add(rdBtnTypeUser);
		btnGroupPageType.add(rdBtnTypeSubreddit);

		frameApplication.getContentPane().add(rdBtnTypeSubreddit);
		frameApplication.getContentPane().add(rdBtnTypeUser);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(28, 6, 404, 171);
		frameApplication.getContentPane().add(tabbedPane);

		JPanel singlePanel = new JPanel();
		tabbedPane.addTab("Single User", null, singlePanel, null);
		singlePanel.setLayout(null);

		// Initialize and setup Username JLabel
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(6, 13, 81, 18);
		singlePanel.add(lblUsername);
		lblUsername.setFont(new Font("Courier New", Font.BOLD, 15));

		// Initialize and setup Password JLabel
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(6, 43, 81, 18);
		singlePanel.add(lblPassword);
		lblPassword.setFont(new Font("Courier New", Font.BOLD, 15));

		// Initialize and setup Password TextField
		txtPassword = new JPasswordField();
		txtPassword.setBounds(99, 36, 257, 31);
		singlePanel.add(txtPassword);
		txtPassword.setFont(new Font("Courier New", Font.PLAIN, 16));
		txtPassword.setToolTipText("Password");

		txtPassword.setText("password");

		// Initialize and setup Username Textfield
		txtUsername = new JFormattedTextField();
		txtUsername.setBounds(99, 6, 257, 31);
		singlePanel.add(txtUsername);
		txtUsername.setText("KarmaInfinityBot");
		txtUsername.setFont(new Font("Courier New", Font.PLAIN, 16));
		txtUsername.setToolTipText("Username");

		// Initialize and setup Sign-In JButton
		btnSignIn = new JButton("Sign In");
		btnSignIn.setBounds(256, 79, 100, 29);
		singlePanel.add(btnSignIn);
		btnSignIn.setFont(new Font("Courier New", Font.PLAIN, 13));
		btnSignIn.setToolTipText("Click to Sign In");

		multiPanel = new JPanel();
		tabbedPane.addTab("Multiple Users", null, multiPanel, null);

		JLabel lblTodo = new JLabel("TODO");
		multiPanel.add(lblTodo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 211, 382, 278);
		frameApplication.getContentPane().add(scrollPane);

		listSubmissions = new JList<String>(submissionList);
		scrollPane.setViewportView(listSubmissions);
		listSubmissions.setBorder(BorderFactory
				.createTitledBorder("Submissions"));


		
		/** Action Listeners. */
		btnSignIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				logic.btnSignInPressed();
			}
		});

		btnGetKarma.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				try {
					logic.btnGetKarmaPressed();
				} catch (IOException e) {
					System.out.println("Exception: " + e.getMessage() + "in btnGetKarmaPressed()");
					e.printStackTrace();
				} catch (ParseException e) {
					System.out.println("Exception: " + e.getMessage() + "in btnGetKarmaPressed()");
					e.printStackTrace();
				}
			}
		});

		txtSubReddit.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				logic.txtSubRedditCaretUpdate();
			}

		});

		btnPagePrevious.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				logic.btnPagePreviousPressed();
			}
		});

		btnPageNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				logic.btnPagePreviousPressed();
			}
		});

		btnFetchPosts.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
					try {
						logic.btnFetchPosts();
					} catch (IOException e) {
						System.out.println("Exception: " + e.getMessage() + "in btnFetchPosts()");
						e.printStackTrace();
					} catch (ParseException e) {
						System.out.println("Exception: " + e.getMessage() + "in btnFetchPosts()");
						e.printStackTrace();
					}
			}
		});
		rdBtnTypeSubreddit.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				logic.rdBtnTypeSubredditStateChanged();
			}
		});
		rdBtnTypeUser.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				logic.rdBtnTypeUserStateChanged();
			}
		});

	}

	/**
	 * Getters and Setters.
	 */

	/**
	 * @return the window
	 */
	public static View getWindow() {
		return window;
	}

	/**
	 * @param window
	 *            the window to set
	 */
	public static void setWindow(View window) {
		View.window = window;
	}

	/**
	 * @return the btnFetchPosts
	 */
	public JButton getBtnFetchPosts() {
		return btnFetchPosts;
	}

	/**
	 * @param btnFetchPosts
	 *            the btnFetchPosts to set
	 */
	public void setBtnFetchPosts(JButton btnFetchPosts) {
		this.btnFetchPosts = btnFetchPosts;
	}

	/**
	 * @return the btnGetKarma
	 */
	public JButton getBtnGetKarma() {
		return btnGetKarma;
	}

	/**
	 * @param btnGetKarma
	 *            the btnGetKarma to set
	 */
	public void setBtnGetKarma(JButton btnGetKarma) {
		this.btnGetKarma = btnGetKarma;
	}

	/**
	 * @return the btnGroupPageType
	 */
	public ButtonGroup getBtnGroupPageType() {
		return btnGroupPageType;
	}

	/**
	 * @param btnGroupPageType
	 *            the btnGroupPageType to set
	 */
	public void setBtnGroupPageType(ButtonGroup btnGroupPageType) {
		this.btnGroupPageType = btnGroupPageType;
	}

	/**
	 * @return the btnPageNext
	 */
	public JButton getBtnPageNext() {
		return btnPageNext;
	}

	/**
	 * @param btnPageNext
	 *            the btnPageNext to set
	 */
	public void setBtnPageNext(JButton btnPageNext) {
		this.btnPageNext = btnPageNext;
	}

	/**
	 * @return the btnPagePrevious
	 */
	public JButton getBtnPagePrevious() {
		return btnPagePrevious;
	}

	/**
	 * @param btnPagePrevious
	 *            the btnPagePrevious to set
	 */
	public void setBtnPagePrevious(JButton btnPagePrevious) {
		this.btnPagePrevious = btnPagePrevious;
	}

	/**
	 * @return the btnSignIn
	 */
	public JButton getBtnSignIn() {
		return btnSignIn;
	}

	/**
	 * @param btnSignIn
	 *            the btnSignIn to set
	 */
	public void setBtnSignIn(JButton btnSignIn) {
		this.btnSignIn = btnSignIn;
	}

	/**
	 * @return the frameApplication
	 */
	public JFrame getFrameApplication() {
		return frameApplication;
	}

	/**
	 * @param frameApplication
	 *            the frameApplication to set
	 */
	public void setFrameApplication(JFrame frameApplication) {
		this.frameApplication = frameApplication;
	}

	/**
	 * @return the lblFetchedPage
	 */
	public JLabel getLblFetchedPage() {
		return lblFetchedPage;
	}

	/**
	 * @param lblFetchedPage
	 *            the lblFetchedPage to set
	 */
	public void setLblFetchedPage(JLabel lblFetchedPage) {
		this.lblFetchedPage = lblFetchedPage;
	}

	/**
	 * @return the lblPageNumber
	 */
	public JLabel getLblPageNumber() {
		return lblPageNumber;
	}

	/**
	 * @param lblPageNumber
	 *            the lblPageNumber to set
	 */
	public void setLblPageNumber(JLabel lblPageNumber) {
		this.lblPageNumber = lblPageNumber;
	}

	/**
	 * @return the listSubmissions
	 */
	public JList<String> getListSubmissions() {
		return listSubmissions;
	}

	/**
	 * @param listSubmissions
	 *            the listSubmissions to set
	 */
	public void setListSubmissions(JList<String> listSubmissions) {
		this.listSubmissions = listSubmissions;
	}

	/**
	 * @return the logic
	 */
	public Logic getLogic() {
		return logic;
	}

	/**
	 * @param logic
	 *            the logic to set
	 */
	public void setLogic(Logic logic) {
		this.logic = logic;
	}

	/**
	 * @return the multiPanel
	 */
	public JPanel getMultiPanel() {
		return multiPanel;
	}

	/**
	 * @param multiPanel
	 *            the multiPanel to set
	 */
	public void setMultiPanel(JPanel multiPanel) {
		this.multiPanel = multiPanel;
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the rdBtnTypeSubreddit
	 */
	public JRadioButton getRdBtnTypeSubreddit() {
		return rdBtnTypeSubreddit;
	}

	/**
	 * @param rdBtnTypeSubreddit
	 *            the rdBtnTypeSubreddit to set
	 */
	public void setRdBtnTypeSubreddit(JRadioButton rdBtnTypeSubreddit) {
		this.rdBtnTypeSubreddit = rdBtnTypeSubreddit;
	}

	/**
	 * @return the rdBtnTypeUser
	 */
	public JRadioButton getRdBtnTypeUser() {
		return rdBtnTypeUser;
	}

	/**
	 * @param rdBtnTypeUser
	 *            the rdBtnTypeUser to set
	 */
	public void setRdBtnTypeUser(JRadioButton rdBtnTypeUser) {
		this.rdBtnTypeUser = rdBtnTypeUser;
	}

	/**
	 * @return the submissionList
	 */
	public DefaultListModel<String> getSubmissionList() {
		return submissionList;
	}

	/**
	 * @param submissionList
	 *            the submissionList to set
	 */
	public void setSubmissionList(DefaultListModel<String> submissionList) {
		this.submissionList = submissionList;
	}

	/**
	 * @return the submissionsFetched
	 */
	public List<Submission> getSubmissionsFetched() {
		return submissionsFetched;
	}

	/**
	 * @param submissionsFetched
	 *            the submissionsFetched to set
	 */
	public void setSubmissionsFetched(List<Submission> submissionsFetched) {
		this.submissionsFetched = submissionsFetched;
	}

	/**
	 * @return the txtPassword
	 */
	public JPasswordField getTxtPassword() {
		return txtPassword;
	}

	/**
	 * @param txtPassword
	 *            the txtPassword to set
	 */
	public void setTxtPassword(JPasswordField txtPassword) {
		this.txtPassword = txtPassword;
	}

	/**
	 * @return the txtSubReddit
	 */
	public JTextField getTxtSubReddit() {
		return txtSubReddit;
	}

	/**
	 * @param txtSubReddit
	 *            the txtSubReddit to set
	 */
	public void setTxtSubReddit(JTextField txtSubReddit) {
		this.txtSubReddit = txtSubReddit;
	}

	/**
	 * @return the txtUsername
	 */
	public JFormattedTextField getTxtUsername() {
		return txtUsername;
	}

	/**
	 * @param txtUsername
	 *            the txtUsername to set
	 */
	public void setTxtUsername(JFormattedTextField txtUsername) {
		this.txtUsername = txtUsername;
	}

}