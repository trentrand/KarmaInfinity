package com.appliesinc.KarmaInfinity;


import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.json.simple.parser.ParseException;

import com.alee.laf.WebLookAndFeel;
import com.appliesinc.KarmaInfinity.Utilities.ComponentMover;
import com.appliesinc.KarmaInfinity.Utilities.MessageConsole;
import com.github.jreddit.submissions.Submission;

/** Ideas to Implement for View class:
 * Make the GUI split into tabs, and add any additional UI's to the current tab list.
 * Add instant-payment tab which has a [Start Listener] button
 * 		When it's on, it scans an email account for payments and runs when it gets one
 * Add a tab for human behavior, it will load all of the accounts in an ArrayList and randomly select ArrayList.get(Random.nextInt(ArrayList.length-1))
 * 		Browses reddit and upvotes/downvotes some random shit, has randomized wait() in between to make it feel realistic and NOT just instant browsing
 * 		Logs out when it's done, and removes that account from the array list.. Repeat with ArrayList.get(Random.nextInt(ArrayList.length-1);
 * Add an Account Generator tab, which will use an existing API or algorithm to create some VERY randomized usernames. Their password will be generated this way as well.
 * 		Each account will have a proxy assigned to them tbhat will be permenetly used for now on.
 * 		The accounts will need to be created with a randomized amount of time between them.
 */

/**
 * The Class View.
 */
public class View {

	/** The window. */
	public static View window;

	/** The VoteLogic object to use. */
	public static VoteLogic votelogic;

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
					WebLookAndFeel.install();

					window = new View(); // initialize
					votelogic = new VoteLogic(window);

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

	/** The page. */
	private int page = 1;

	/** The rd btn type subreddit. */
	private JRadioButton rdBtnTypeSubreddit;

	/** The rd btn type user. */
	private JRadioButton rdBtnTypeUser;

	/** The submission list. */
	private DefaultListModel<String> submissionList;


	/** The txt password. */
	private JPasswordField txtPassword;

	/** The txt sub reddit. */
	private JTextField txtSubReddit;

	/** The txt username. */
	private JFormattedTextField txtUsername;
	private JScrollPane scrollPaneSubmission;
	private JPanel panelAccountGenerator;
	private JPanel panelHumanize;
	private JPanel panelConsole;
	private JTextPane txtpnTerminal;
	private JScrollPane scrollPaneTerminal;
	private JSpinner spinner;
	private JPanel panelNumOfAccounts;
	private JTable tableAccountList;
	private JScrollPane scrollPaneAccountList;

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
		frameApplication.setResizable(false);
		frameApplication.setTitle("Karma \u221E");
		frameApplication.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
		frameApplication.setBounds(100, 100, 509, 543);
		frameApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameApplication.getContentPane().setLayout(null);
		frameApplication.setUndecorated(true); // remove title bar from JFrame

		submissionList = new DefaultListModel<String>();

		btnGroupPageType = new ButtonGroup();

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 510, 552);
		frameApplication.getContentPane().add(tabbedPane);

		// create a new component mover, allows components to act as draggable points
		ComponentMover cm = new ComponentMover(JFrame.class, tabbedPane);
		// allow <arg> components to act as draggable points
		cm.registerComponent(frameApplication, tabbedPane);

		JPanel panelVote = new JPanel();
		tabbedPane.addTab("Vote", (Icon) null, panelVote,
				"Upvote or downvote submissions");
		panelVote.setLayout(null);

		// Initialize and setup Username JLabel
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(28, 19, 81, 18);
		panelVote.add(lblUsername);
		lblUsername.setFont(new Font("Courier New", Font.BOLD, 15));

		// Initialize and setup Password JLabel
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(28, 49, 81, 18);
		panelVote.add(lblPassword);
		lblPassword.setFont(new Font("Courier New", Font.BOLD, 15));

		// Initialize and setup Password TextField
		txtPassword = new JPasswordField();
		txtPassword.setBounds(127, 42, 281, 31);
		panelVote.add(txtPassword);
		txtPassword.setFont(new Font("Courier New", Font.PLAIN, 16));
		txtPassword.setToolTipText("Password");

		txtPassword.setText("password");

		// Initialize and setup Username Textfield
		txtUsername = new JFormattedTextField();
		txtUsername.setBounds(127, 12, 281, 31);
		panelVote.add(txtUsername);
		txtUsername.setText("KarmaInfinityBot");
		txtUsername.setFont(new Font("Courier New", Font.PLAIN, 16));
		txtUsername.setToolTipText("Username");

		// Initialize and setup Sign-In JButton
		btnSignIn = new JButton("Sign In");
		btnSignIn.setBounds(329, 79, 100, 29);
		panelVote.add(btnSignIn);
		btnSignIn.setFont(new Font("Courier New", Font.PLAIN, 13));
		btnSignIn.setToolTipText("Click to Sign In");

		// TODO Actually check if the account list could load (stored in
		// APPDATA?)
		JLabel lblLoadAccountList = new JLabel("Could not load account list!");
		lblLoadAccountList.setFont(new Font("Courier New", Font.PLAIN, 16));
		lblLoadAccountList.setBounds(28, 85, 289, 15);
		panelVote.add(lblLoadAccountList);

		// Initialize and setup separator JSeperator
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 120, 495, 12);
		panelVote.add(separator);

		lblPageNumber = new JLabel("Page: " + page);
		lblPageNumber.setBounds(196, 137, 117, 16);
		panelVote.add(lblPageNumber);
		lblPageNumber.setEnabled(false);
		lblPageNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblPageNumber.setFont(new Font("Courier New", Font.PLAIN, 15));

		btnPagePrevious = new JButton("Previous");
		btnPagePrevious.setBounds(6, 131, 105, 29);
		panelVote.add(btnPagePrevious);
		btnPagePrevious.setEnabled(false);
		btnPagePrevious.setFont(new Font("Courier New", Font.PLAIN, 13));

		btnPageNext = new JButton("Next");
		btnPageNext.setBounds(396, 131, 105, 29);
		panelVote.add(btnPageNext);
		btnPageNext.setEnabled(false);
		btnPageNext.setFont(new Font("Courier New", Font.PLAIN, 13));

		scrollPaneSubmission = new JScrollPane();
		scrollPaneSubmission.setBounds(23, 173, 463, 268);
		panelVote.add(scrollPaneSubmission);

		listSubmissions = new JList<String>(submissionList);
		scrollPaneSubmission.setViewportView(listSubmissions);
		listSubmissions.setBorder(new TitledBorder(null, "Submissions",
				TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(
						59, 59, 59)));

		rdBtnTypeUser = new JRadioButton("/u/");
		rdBtnTypeUser.setBounds(80, 482, 57, 18);
		panelVote.add(rdBtnTypeUser);

		btnGroupPageType.add(rdBtnTypeUser);

		rdBtnTypeSubreddit = new JRadioButton("/r/");
		rdBtnTypeSubreddit.setSelected(true);
		rdBtnTypeSubreddit.setBounds(33, 483, 57, 16);
		panelVote.add(rdBtnTypeSubreddit);
		btnGroupPageType.add(rdBtnTypeSubreddit);

		// Initialize and setup Post-Address JLabel
		JLabel lblPageName = new JLabel("Page Name:");
		lblPageName.setBounds(28, 454, 109, 16);
		panelVote.add(lblPageName);
		lblPageName.setFont(new Font("Courier New", Font.PLAIN, 16));

		txtSubReddit = new JTextField();
		txtSubReddit.setBounds(127, 450, 239, 28);
		panelVote.add(txtSubReddit);
		txtSubReddit.setFont(new Font("Courier New", Font.PLAIN, 13));
		txtSubReddit.setColumns(10);

		btnFetchPosts = new JButton("Fetch Posts");
		btnFetchPosts.setBounds(369, 449, 117, 29);
		panelVote.add(btnFetchPosts);
		btnFetchPosts.setEnabled(false);
		btnFetchPosts.setFont(new Font("Courier New", Font.PLAIN, 13));

		// Initialize and setup Get-Karma JButton
		btnGetKarma = new JButton("Get Karma");
		btnGetKarma.setBounds(369, 478, 117, 29);
		panelVote.add(btnGetKarma);
		btnGetKarma.setFont(new Font("Courier New", Font.PLAIN, 13));
		btnGetKarma.setEnabled(false);

		lblFetchedPage = new JLabel();
		lblFetchedPage.setBounds(127, 482, 239, 18);
		panelVote.add(lblFetchedPage);
		lblFetchedPage.setHorizontalAlignment(SwingConstants.TRAILING);
		lblFetchedPage.setFont(new Font("Courier New", Font.PLAIN, 13));

		btnGetKarma.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				try {
					votelogic.btnGetKarmaPressed();
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
					votelogic.btnFetchPosts();
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
		lblFetchedPage.setText(this.getTxtSubReddit().getText());

		txtSubReddit.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				votelogic.txtSubRedditCaretUpdate();
			}

		});
		rdBtnTypeSubreddit.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				votelogic.rdBtnTypeSubredditStateChanged();
			}
		});
		rdBtnTypeUser.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				votelogic.rdBtnTypeUserStateChanged();
			}
		});

		btnPageNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				votelogic.btnPagePreviousPressed();
			}
		});

		btnPagePrevious.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				votelogic.btnPagePreviousPressed();
			}
		});
		panelVote.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { txtUsername, txtPassword, btnSignIn,
						lblUsername, lblPassword }));

		panelAccountGenerator = new JPanel();
		tabbedPane.addTab("Account Generator", null, panelAccountGenerator,
				"Automatically generate reddit accounts");
		panelAccountGenerator.setLayout(null);
		
		spinner = new JSpinner();
		spinner.setBounds(421, 12, 71, 53);
		panelAccountGenerator.add(spinner);
		
		panelNumOfAccounts = new JPanel();
		panelNumOfAccounts.setBackground(Color.WHITE);
		panelNumOfAccounts.setBorder(new LineBorder(Color.GRAY, 2, true));
		panelNumOfAccounts.setBounds(12, 12, 411, 53);
		panelAccountGenerator.add(panelNumOfAccounts);
		panelNumOfAccounts.setLayout(null);
		
		JLabel lblNumOfAccounts = new JLabel("Number of accounts to generate?");
		lblNumOfAccounts.setBounds(12, 12, 387, 15);
		panelNumOfAccounts.add(lblNumOfAccounts);
		lblNumOfAccounts.setBackground(Color.WHITE);
		lblNumOfAccounts.setFont(new Font("Courier New", Font.PLAIN, 18));
		
		JButton btnGenerateAccounts = new JButton("Generate Accounts");
		btnGenerateAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(votelogic.generateLogin());
			}
		});
		btnGenerateAccounts.setFont(new Font("Courier New", Font.PLAIN, 13));
		btnGenerateAccounts.setBounds(323, 487, 170, 26);
		panelAccountGenerator.add(btnGenerateAccounts);
		
		scrollPaneAccountList = new JScrollPane();
		scrollPaneAccountList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneAccountList.setBounds(12, 77, 480, 306);
		scrollPaneAccountList.setBackground(new Color(0,0,0));
		panelAccountGenerator.add(scrollPaneAccountList);
		
		//FIXME testing to populate JTable with a ArrayList
		ArrayList arrayList = new ArrayList();

		  String[] columns = {"Username","Password"," Proxy:Port"};
		  DefaultTableModel model = new DefaultTableModel(columns, 1);

		  for (Object item : arrayList) {
		     Object[] row = new Object[2];
		     //... fill in row with info from item
		     row[0] = "_karmawhore";
		     row[1] = "xx380813xx,,";
		     row[2] = "192.168.0.1:5499";
		     model.addRow(row);
		  }
		//FIXME End of testing.
		tableAccountList = new JTable(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"Username", "Password", " Proxy:Port"
			}
		));
		scrollPaneAccountList.setViewportView(tableAccountList);
		tableAccountList.setFont(new Font("Courier New", Font.PLAIN, 13));
		tableAccountList.setBorder(new LineBorder(Color.GRAY, 1, true));
	

		
		
		
		tabbedPane.setEnabledAt(1, true);

		panelHumanize = new JPanel();
		tabbedPane.addTab("Humanize Accounts", null, panelHumanize,
				"Cycle through accounts, humanizing their activity");
		panelHumanize.setLayout(null);
		
		JButton btnStartListener = new JButton("");
		btnStartListener.setIcon(new ImageIcon("/Users/trentmrand/Development/KarmaInfinity/startlistener.png"));
		btnStartListener.setBackground(new Color(0, 0, 0, 0));
		btnStartListener.setBounds(453, 465, 40, 40);
		
		panelHumanize.add(btnStartListener);
		tabbedPane.setEnabledAt(2, true);

		panelConsole = new JPanel();
		tabbedPane.addTab("Terminal", null, panelConsole,
				"View the terminal output");
		tabbedPane.setEnabledAt(3, true);
		panelConsole.setLayout(null);

		scrollPaneTerminal = new JScrollPane();
		scrollPaneTerminal.setViewportBorder(new MatteBorder(0, 0, 0, 0,
				(Color) new Color(0, 0, 0)));
		scrollPaneTerminal.setBounds(13, 6, 483, 500);
		scrollPaneTerminal.setForeground(Color.ORANGE);
		panelConsole.add(scrollPaneTerminal);

		txtpnTerminal = new JTextPane();
		txtpnTerminal.setForeground(Color.ORANGE);
		txtpnTerminal.setFont(new Font("Courier New", Font.PLAIN, 14));
		scrollPaneTerminal.setViewportView(txtpnTerminal);
		txtpnTerminal.setText("Terminal Output:");
		txtpnTerminal.setEditable(false);
		txtpnTerminal.setBackground(new Color(237, 237, 237));

		// Used to redirect console output to the terminal tab
		MessageConsole mc = new MessageConsole(txtpnTerminal);
		btnSignIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				votelogic.btnSignInPressed();
			}
		});
		mc.redirectOut(new Color(242, 133, 0), null);
		mc.redirectErr(Color.RED, null);
		mc.setMessageLines(1000);

		/** Action Listeners. */

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
	 * @return the votelogic
	 */
	public VoteLogic getLogic() {
		return votelogic;
	}

	/**
	 * @param votelogic
	 *            the votelogic to set
	 */
	public void setLogic(VoteLogic logic) {
		View.votelogic = logic;
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