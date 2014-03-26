package com.appliesinc.UpVoteBot;

import im.goel.jreddit.submissions.Submission;
import im.goel.jreddit.user.User;

import java.awt.Color;
import java.io.IOException;
import java.util.Random;

import org.json.simple.parser.ParseException;

/** Ideas to Implement for Logic class:
 * // TODO Set the proxy with setProxy("190.73.140.191", "8080"); 
 * // TODO Maybe have them browse a subreddit first, then goto proper one
 * // TODO upvote a random then upvote the selected 
 * // TODO Randomize upvote or downvote so it looks real 
 * // TODO Set a delay between accounts 
 * // TODO Loop through accounts 
 * // TODO Have bots upvote and downvote random submssions so they don't all share the same activity
 */

/**
 * The Class Logic.
 */
public class Logic {

	/**
	 * The user currently signed in.
	 */
	private User user;

	/**
	 * The View passed in constructor.
	 */
	private View view;

	/**
	 * The subreddit list of 103 top subreddits.
	 */
	private String[] subredditList = { "pics", "funny", "gaming", "AskReddit",
			"worldnews", "news", "videos", "iama", "todayilearned", "aww",
			"technology", "adviceanimals", "science", "music", "movies",
			"bestof", "books", "earthporn", "explainlikeimfive", "gifs",
			"television", "askscience", "sports", "mildlyinteresting",
			"lifeprotips", "woahdude", "unexpected", "reactiongifs",
			"showerthoughts", "food", "jokes", "photoshopbattles",
			"firstworldanarchists", "foodporn", "historyporn", "wtf",
			"leagueoflegends", "cringepics", "twitchplayspokemon", "trees",
			"4chan", "MakeupAddiction", "pokemon", "politics", "pcmasterrace",
			"soccer", "DotA2", "gentlemanboners", "Minecraft", "circlejerk",
			"trollxchromosomes", "atheism", "starcraft", "nfl", "games", "nba",
			"globaloffensive", "dogecoin", "fffffffuuuuuuuuuuuu", "hockey",
			"cats", "bitcoin", "hiphopheads", "formula1", "teenagers",
			"conspiracy", "mylittlepony", "celebs", "skyrim", "polandball",
			"tumblrinaction", "tumblr", "titanfall", "anime", "squaredcircle",
			"facepalm", "battlefield_4", "tattoos", "android", "gameofthrones",
			"redditlaqueristas", "ladyboners", "talesfromtechsupport",
			"hearthstone", "grandtheftautov", "mildlyinfuriating", "awwnime",
			"darksouls2", "australia", "comics", "talesfromretail",
			"doctorwho", "collegebasketball", "thathappened", "gamegrumps",
			"standupshots", "cringe", "carporn", "murica", "guns", "wow",
			"mma", "starwars" };

	/**
	 * Instantiates a new logic object.
	 * 
	 * @param _view
	 *            the _view
	 */
	public Logic(View _view) {
		view = _view;
	}

	/*
	 * Action Listener Implementations
	 */

	/**
	 * Btn sign in pressed.
	 */
	@SuppressWarnings("deprecation")
	protected void btnSignInPressed() {
		try {
			user = new User(view.getTxtUsername().getText(), view
					.getTxtPassword().getText());
			user.connect();
			view.getTxtPassword().setForeground(Color.green);
			System.out.println("User signed in correctly");
			view.getBtnSignIn().setEnabled(true); // reenable <btnSignIn> as
			// enabled since UI unfroze
			view.getBtnFetchPosts().setEnabled(true);
		} catch (Exception e1) {
			view.getBtnSignIn().setEnabled(true); // reenable <btnSignIn> as
			// enabled since UI unfroze
			System.out.println("btnSignIn invoked an Exception");
			view.getTxtPassword().setForeground(Color.red);
			System.out.println(view.getTxtPassword().getText());
			System.out.println("Could Not Sign In User");
		}
	}

	/**
	 * Btn get karma pressed.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ParseException
	 *             the parse exception
	 */
	protected void btnGetKarmaPressed() throws IOException, ParseException {
		Submission submission = Submission.getSubmission(view
				.getLblFetchedPage().getText(), view.getListSubmissions()
				.getSelectedIndex(), user);

		System.out.println("Upvoting Post: " + submission.getTitle());
		submission.upVote();

	}

	/**
	 * Txt Sub Reddit caret update.
	 */
	public void txtSubRedditCaretUpdate() {
		view.getLblFetchedPage().setText(
				getSlashType() + view.getTxtSubReddit().getText());
	}

	/**
	 * Btn page previous pressed.
	 */
	protected void btnPagePreviousPressed() {
		if (view.getPage() > 1) {
			view.setPage(view.getPage() - 1);
		}
	}

	/**
	 * Btn page next pressed.
	 */
	protected void btnPageNextPressed() {
		view.setPage(view.getPage() + 1);
	}

	/**
	 * Gets the slash type.
	 * 
	 * @return the slash type
	 */
	public String getSlashType() {
		if (view.getRdBtnTypeSubreddit().isSelected()) {
			return view.getRdBtnTypeSubreddit().getText();
		} else {
			return view.getRdBtnTypeUser().getText();
		}
	}

	/**
	 * Btn fetch posts.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ParseException
	 *             the parse exception
	 */
	protected void btnFetchPosts() throws IOException, ParseException {
		view.setSubmissionsFetched(user.getSubmissions(getSlashType()
				+ view.getTxtSubReddit().getText() + "/"));
		for (int i = 0; i < view.getSubmissionsFetched().size(); i++) {
			view.getSubmissionList().add(
					i,
					"[" + view.getSubmissionsFetched().get(i).getAuthor()
							+ "] "
							+ view.getSubmissionsFetched().get(i).getTitle());
		}
		view.getBtnGetKarma().setEnabled(true);
	}

	/**
	 * Radio btn type subreddit state changed.
	 */
	protected void rdBtnTypeSubredditStateChanged() {
		view.getLblFetchedPage().setText(
				getSlashType() + view.getTxtSubReddit().getText());
	}

	/**
	 * Radio btn type user state changed.
	 */
	protected void rdBtnTypeUserStateChanged() {
		view.getLblFetchedPage().setText(
				getSlashType() + view.getTxtSubReddit().getText());
	}

	/**
	 * Browse random.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ParseException
	 *             the parse exception
	 */
	private void browseRandom() throws IOException, ParseException {
		Random r = new Random(); // create a random <r> for use with randomizing
									// human interaction

		// Lets test if we want to do a random thing, 50/50
		if (r.nextInt(1) > .50) {
			System.out.println("Went Random");
		} else {
			System.out.println("No random");

		}

		/* List of random stuff */
		// user.hasMail(); //check mail
		// user.linkKarma(); //check linkKarma
		// user.commentKarma(); //check commentKarma
		//
		// List<Subreddit> subreddits =
		// subreddits.getSubreddits(SubredditType.NEW); //grab a random
		// subreddit TODO set random
		// // Assuming user is a connected User instance:
		// List<Submission> sm = user.getDislikedSubmissions();
		// // Assuming user is a connected User instance:
		// List<Submission> sm = user.getHiddenSubmissions();
		// // Assuming user is a connected User instance:
		// List<Submission> sm = user.getLikedSubmissions();
		// // Assuming user is a connected User instance:
		// List<Submission> sm = user.getSubmissions();
		// UserInfo ui = User.about("some_username");
		// List<Comment> comments = User.comments("some_username");
		// // Assuming user is an initialized User instance:
		// List<Submission> saved = user.getSavedSubmissions();

	}
}

