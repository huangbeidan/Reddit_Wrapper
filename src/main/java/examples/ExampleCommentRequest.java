package examples;


import com.github.jreddit.oauth.RedditOAuthAgent;
import com.github.jreddit.oauth.RedditToken;
import com.github.jreddit.oauth.app.RedditApp;
import com.github.jreddit.oauth.app.RedditInstalledApp;
import com.github.jreddit.oauth.client.RedditClient;
import com.github.jreddit.oauth.client.RedditHttpClient;
import com.github.jreddit.oauth.exception.RedditOAuthException;
import com.github.jreddit.parser.entity.Comment;
import com.github.jreddit.parser.entity.Submission;
import com.github.jreddit.parser.exception.RedditParseException;
import com.github.jreddit.parser.listing.CommentsListingParser;
import com.github.jreddit.parser.listing.SubmissionsListingParser;
import com.github.jreddit.request.retrieval.comments.MoreCommentsRequest;
import com.github.jreddit.request.retrieval.param.SubmissionSort;
import com.github.jreddit.request.retrieval.submissions.SubmissionsOfSubredditRequest;
import org.apache.http.impl.client.HttpClientBuilder;

import java.util.ArrayList;
import java.util.List;

public class ExampleCommentRequest {

    public static void main(String[] args) throws RedditOAuthException, RedditParseException {

        // Information about the app
        String userAgent = "jReddit: Reddit API Wrapper for Java";
//        String clientID = "KN0I2zXecRY4QA";
//        String redirectURI = "https://github.com/jReddit/jReddit";
        String clientID = "PfnhLt3VahLrbg";
        String redirectURI = "https://github.com/snkas/jReddit";

        // Reddit application
        RedditApp redditApp = new RedditInstalledApp(clientID, redirectURI);

        // Create OAuth agent
        RedditOAuthAgent agent = new RedditOAuthAgent(userAgent, redditApp);

        // Create request executor
        RedditClient client = new RedditHttpClient(userAgent, HttpClientBuilder.create().build());

        // Create token (will be valid for 1 hour)
        RedditToken token = agent.tokenAppOnly(false);


        // Create parser for request
        SubmissionsListingParser parser = new SubmissionsListingParser();

        // Create the request
        SubmissionsOfSubredditRequest request = (SubmissionsOfSubredditRequest) new SubmissionsOfSubredditRequest("netflix", SubmissionSort.HOT).setLimit(100);

        // Perform and parse request, and store parsed result
        List<Submission> submissions = parser.parse(client.get(token, request));


        // Create parser for comment request
        CommentsListingParser parser2 = new CommentsListingParser();
        List<String> commentIdentifiers = new ArrayList<String>();
        commentIdentifiers.add("e9rnmkp");
        MoreCommentsRequest request2 = (MoreCommentsRequest) new MoreCommentsRequest("t3_9xead9", commentIdentifiers);

        List<Comment> comment = parser2.parse(client.get(token, request2));

        System.out.println(comment);




    }

}

