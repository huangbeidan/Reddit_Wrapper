package examples;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import cleancode.clearquote;
import cleancode.stringsplit;
import com.github.jreddit.parser.listing.CommentsListingParser;
import org.apache.http.impl.client.HttpClientBuilder;

import com.github.jreddit.oauth.RedditOAuthAgent;
import com.github.jreddit.oauth.RedditToken;
import com.github.jreddit.oauth.app.RedditApp;
import com.github.jreddit.oauth.app.RedditInstalledApp;
import com.github.jreddit.oauth.client.RedditClient;
import com.github.jreddit.oauth.client.RedditHttpClient;
import com.github.jreddit.oauth.exception.RedditOAuthException;
import com.github.jreddit.parser.entity.Submission;
import com.github.jreddit.parser.exception.RedditParseException;
import com.github.jreddit.parser.listing.SubmissionsListingParser;
import com.github.jreddit.request.retrieval.param.SubmissionSort;
import com.github.jreddit.request.retrieval.submissions.SubmissionsOfSubredditRequest;

public class testevery {

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




        // Now print out the result (don't care about formatting)
        System.out.println(submissions);
        Object[] myArray = submissions.toArray();


        int id=0;
        for (Object myObject : myArray) {
            String myobject = myObject.toString();
            String after = clearquote.clearsinglequote(myobject);
            String[] cleared = stringsplit.mysplit(after);

            String sql = "INSERT INTO Reddit (FULLNAME,TYPE,CONTENT) "
                    + "VALUES (\'"+cleared[0]+"\'"+","+"\'"+cleared[1]+"\'"+","+"\'"+cleared[2]+"\');";
            System.out.println(sql);
        }

        System.out.println("Records created successfully");
    }
}

