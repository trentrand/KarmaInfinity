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
	private JScrollPane scrollPane;

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
		frameApplication.setTitle("Karma \u221E by trent rand");
		frameApplication.setFont(new Font("Courier New", Font.PLAIN, 12));
		frameApplication.setBounds(100, 100, 488, 604);
		frameApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameApplication.getContentPane().setLayout(null);

		submissionList = new DefaultListModel<String>();

		btnGroupPageType = new ButtonGroup();

		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 498, 576);
		frameApplication.getContentPane().add(panel);
		panel.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(47, 6, 404, 171);
		panel.add(tabbedPane);

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

		// Initialize and setup separator JSeperator
		JSeparator separator = new JSeparator();
		separator.setBounds(35, 170, 428, 12);
		panel.add(separator);

		lblPageNumber = new JLabel("Page: " + page);
		lblPageNumber.setBounds(190, 194, 117, 16);
		panel.add(lblPageNumber);
		lblPageNumber.setEnabled(false);
		lblPageNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblPageNumber.setFont(new Font("Courier New", Font.PLAIN, 15));

		btnPagePrevious = new JButton("Previous");
		btnPagePrevious.setBounds(35, 189, 105, 29);
		panel.add(btnPagePrevious);
		btnPagePrevious.setEnabled(false);
		btnPagePrevious.setFont(new Font("Courier New", Font.PLAIN, 13));

		btnPageNext = new JButton("Next");
		btnPageNext.setBounds(346, 189, 105, 29);
		panel.add(btnPageNext);
		btnPageNext.setEnabled(false);
		btnPageNext.setFont(new Font("Courier New", Font.PLAIN, 13));

		scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 222, 419, 274);
		panel.add(scrollPane);

		listSubmissions = new JList<String>(submissionList);
		scrollPane.setViewportView(listSubmissions);
		listSubmissions.setBorder(BorderFactory
				.createTitledBorder("Submissions"));

		btnFetchPosts = new JButton("Fetch Posts");
		btnFetchPosts.setBounds(346, 514, 117, 29);
		panel.add(btnFetchPosts);
		btnFetchPosts.setEnabled(false);
		btnFetchPosts.setFont(new Font("Courier New", Font.PLAIN, 13));

		// Initialize and setup Get-Karma JButton
		btnGetKarma = new JButton("Get Karma");
		btnGetKarma.setBounds(346, 539, 117, 29);
		panel.add(btnGetKarma);
		btnGetKarma.setFont(new Font("Courier New", Font.PLAIN, 13));
		btnGetKarma.setEnabled(false);

		txtSubReddit = new JTextField();
		txtSubReddit.setBounds(153, 539, 191, 28);
		panel.add(txtSubReddit);
		txtSubReddit.setFont(new Font("Courier New", Font.PLAIN, 13));
		txtSubReddit.setColumns(10);

		// Initialize and setup Post-Address JLabel
		JLabel lblPageName = new JLabel("Page Name:");
		lblPageName.setBounds(35, 543, 109, 16);
		panel.add(lblPageName);
		lblPageName.setFont(new Font("Courier New", Font.PLAIN, 16));

		rdBtnTypeSubreddit = new JRadioButton("/r/");
		rdBtnTypeSubreddit.setBounds(35, 520, 57, 23);
		panel.add(rdBtnTypeSubreddit);
		btnGroupPageType.add(rdBtnTypeSubreddit);

		rdBtnTypeUser = new JRadioButton("/u/");
		rdBtnTypeUser.setBounds(35, 500, 57, 23);
		panel.add(rdBtnTypeUser);

		btnGroupPageType.add(rdBtnTypeUser);

		lblFetchedPage = new JLabel();
		lblFetchedPage.setHorizontalAlignment(SwingConstants.TRAILING);
		lblFetchedPage.setBounds(243, 500, 207, 18);
		panel.add(lblFetchedPage);
		lblFetchedPage.setFont(new Font("Courier New", Font.PLAIN, 13));
		lblFetchedPage.setText(this.getTxtSubReddit().getText());
		rdBtnTypeUser.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				logic.rdBtnTypeUserStateChanged();
			}
		});
		rdBtnTypeSubreddit.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				logic.rdBtnTypeSubredditStateChanged();
			}
		});

		txtSubReddit.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				logic.txtSubRedditCaretUpdate();
			}

		});

		btnGetKarma.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				try {
					logic.btnGetKarmaPressed();
				} catch (IOException e) {
					System.out.println("Exception: " + e.getMessage()
							+ "in btnGetKarmaPressed()");
					e.printStackTrace();
				} catch (ParseException e) {
					System.out.println("Exception: " + e.getMessage()
							+ "in btnGetKarmaPressed()");
					e.printStackTrace();
				}
			}
		});

		btnFetchPosts.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				try {
					logic.btnFetchPosts();
				} catch (IOException e) {
					System.out.println("Exception: " + e.getMessage()
							+ "in btnFetchPosts()");
					e.printStackTrace();
				} catch (ParseException e) {
					System.out.println("Exception: " + e.getMessage()
							+ "in btnFetchPosts()");
					e.printStackTrace();
				}
			}
		});

		btnPageNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				logic.btnPagePreviousPressed();
			}
		});

		btnPagePrevious.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				logic.btnPagePreviousPressed();
			}
		});

		/** Action Listeners. */
		btnSignIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				logic.btnSignInPressed();
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