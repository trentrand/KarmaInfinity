package com.appliesinc.UpVoteBot;

import im.goel.jreddit.submissions.Submission;
import im.goel.jreddit.user.User;

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
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.json.simple.parser.ParseException;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;

import java.awt.FlowLayout;

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
	private JList listSubmissions;
	private DefaultListModel<String> submissionList;
	private List<Submission> submissionsFetched;
	private JPanel multiPanel;

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
		frameApplication.setBounds(100, 100, 450, 604);
		frameApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add Buttons to JFrame <frameApplcation>
		frameApplication.getContentPane().setLayout(null);

		// Initialize and setup separator JSeperator
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 165, 438, 12);
		frameApplication.getContentPane().add(separator);

		// Initialize and setup Post-Address JLabel
		JLabel lblPageName = new JLabel("Page Name:");
		lblPageName.setFont(new Font("Courier New", Font.PLAIN, 16));
		lblPageName.setBounds(77, 551, 109, 16);
		frameApplication.getContentPane().add(lblPageName);

		// Initialize and setup Get-Karma JButton
		btnGetKarma = new JButton("Get Karma");
		btnGetKarma.setFont(new Font("Courier New", Font.PLAIN, 13));
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
		txtSubReddit.setFont(new Font("Courier New", Font.PLAIN, 13));
		txtSubReddit.setBounds(183, 545, 143, 28);
		frameApplication.getContentPane().add(txtSubReddit);
		txtSubReddit.setColumns(10);

		submissionList = new DefaultListModel();

		btnPagePrevious = new JButton("Previous");
		btnPagePrevious.setEnabled(false);
		btnPagePrevious.setFont(new Font("Courier New", Font.PLAIN, 13));
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

		btnPageNext = new JButton("Next");
		btnPageNext.setEnabled(false);
		btnPageNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				page++;
			}
		});
		btnPageNext.setFont(new Font("Courier New", Font.PLAIN, 13));
		btnPageNext.setBounds(305, 182, 105, 29);
		frameApplication.getContentPane().add(btnPageNext);

		lblPageNumber = new JLabel("Page: " + page);
		lblPageNumber.setEnabled(false);
		lblPageNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblPageNumber.setFont(new Font("Courier New", Font.PLAIN, 15));
		lblPageNumber.setBounds(140, 187, 168, 16);
		frameApplication.getContentPane().add(lblPageNumber);

		btnFetchPosts = new JButton("Fetch Posts");
		btnFetchPosts.setEnabled(false);
		btnFetchPosts.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					btnFetchPosts();
				} catch (IOException e1) {
					System.out
							.println("btnFetchPosts invoked an Exception <IOException>");
					e1.printStackTrace();
				} catch (ParseException e1) {
					System.out
							.println("btnFetchPosts invoked an Exception <ParseException>");
					e1.printStackTrace();
				}
			}
		});
		btnFetchPosts.setFont(new Font("Courier New", Font.PLAIN, 13));
		btnFetchPosts.setBounds(315, 501, 117, 29);
		frameApplication.getContentPane().add(btnFetchPosts);

		lblFetchedPage = new JLabel();
		lblFetchedPage.setFont(new Font("Courier New", Font.PLAIN, 13));
		lblFetchedPage.setBounds(28, 501, 285, 18);
		lblFetchedPage.setText(this.getTxtSubReddit().getText());
		frameApplication.getContentPane().add(lblFetchedPage);

		btnGroupPageType = new ButtonGroup();

		rdBtnTypeSubreddit = new JRadioButton("/r/");
		rdBtnTypeSubreddit.setBounds(6, 551, 141, 23);
		rdBtnTypeSubreddit.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				getLblFetchedPage().setText(
						getSlashType() + getTxtSubReddit().getText());
			}
		});

		rdBtnTypeUser = new JRadioButton("/u/");
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
								
										// TODO Remove this when released (sets the password to xx380813xx,,)
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
																listSubmissions.setBorder(BorderFactory.createTitledBorder("Submissions"));

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
					btnGetKarmaPressed(); // make this non-static,
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
		getBtnFetchPosts().setEnabled(true);
	}

	protected void btnGetKarmaPressed() throws IOException, ParseException {
		Submission submission = Submission.getSubmission(getLblFetchedPage()
				.getText(), getListSubmissions().getSelectedIndex(), user);
		
		System.out.println("Upvoting Post: " + submission.getTitle());
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
				+ getTxtSubReddit().getText() + "/");
		for (int i = 0; i < submissionsFetched.size(); i++) {
			submissionList.add(i, "[" +  submissionsFetched.get(i).getAuthor() + "] " + submissionsFetched.get(i).getTitle()); 
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
	public JList getListSubmissions() {
		return listSubmissions;
	}
}