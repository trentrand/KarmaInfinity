# KarmaInfinity

### What is KarmaInfinity?
KarmaInfinity is reddit upvote bot. It uses a single proxy per account to mimic reality for each user.

### What can it do?
KarmaInfinity can login with a user, retrieve submissions of /u/ and /r/ pages, vote on submissions, and even cycle through a list of proxied accounts doing each of these actions.

### What's next?
Current I would like to focus attention on creating a stable bot with single user upvoting, multiple user upvoting, and automatic account creation. All of these features will need to be implemented with realistic interaction as well as randomizing so that the accounts remain unbanned. 

## How to contribute?
To contribute please fork the repo and make all alterations on that fork. After your feature is ready to be merged into the main code, send a pull request! I will check over it and accept it if it works as wanted.
Be sure to make the bot realistic and randomized!

Send in a pull request and I'll be happy to merge!

### Dependencies
[jReddit](https://github.com/karan/jReddit)
[JSON-simple](http://code.google.com/p/json-simple/)

### Examples of using the API [More examples](https://github.com/karan/jReddit/blob/master/implemented_methods.md)
I have implemented some of my own methods which can be found in Submission.java and Submissions.java
Upvote every submission on the frontpage of a subreddit

    import im.goel.jreddit.submissions.Submission;
    import im.goel.jreddit.submissions.Submissions;
    import im.goel.jreddit.user.User;

    public final class Test {
	    public static void main(String[] args) throws Exception {
		    User user = new User("username", "password");
		    user.connect();

		    for (Submission submission : Submissions.getSubmissions("programming",
				    Submissions.HOT, Submissions.FRONTPAGE, user)) {
			    submission.upVote();
		    }
	    }
    }

Submit a link and self post

	import im.goel.jreddit.user.User;
	
	public final class Test {
		public static void main(String[] args) throws Exception {
			User user = new User("username", "password");
			user.connect();
	
			user.submitLink(
					"Oracle V Google judge is a programmer!",
					"http://www.i-programmer.info/news/193-android/4224-oracle-v-google-judge-is-a-programmer.html",
					"programming");
			user.submitSelfPost("What's the difference between a duck?",
					"One of its legs are both the same!", "funny");
		}
	}
	
List all submissions made by user called USERNAME_OF_OTHER_USER

	import im.goel.jreddit.submissions.Submission;
	import im.goel.jreddit.user.User;
	
	/**
	 * @author Benjamin Jakobus
	 */
	public final class Test {
		public static void main(String[] args) throws Exception {
			User user = new User("username", "password");
        		user.connect();

        		List<Submission> submissions = User.submissions("USERNAME_OF_OTHER_USER");
        		// To list hidden submissions, user User.hidden("...");
		
			for (Submission s : submissions) {
				// Print info here
			}
		}
	}

Send a message to another user

	import im.goel.jreddit.message.Messages;
	import im.goel.jreddit.user.User;


	public class ComposeTest {

		/**
		 * @author Karan Goel
		 */
		public static void main(String[] args) {
			User user = null;
			username_of_recipient = "other_user";
			try {
				user = new User("username", "password"); // Add your username and password
				user.connect();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
			
			new Messages().compose(user, username_of_recipient, "this is the title", "the message", "", "");
		}

	}
