package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class SocialNetworkTest {

    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    
    // Helper method to create a Tweet
    private Tweet createTweet(int id, String author, String content, Instant date) {
        return new Tweet(id, author, content, date);
    }

    // 1. Empty list of tweets
    @Test
    public void testGuessFollowsGraphEmptyTweets() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList());
        assertTrue("Expected empty graph", followsGraph.isEmpty());
    }

    // 2. Tweets with no mentions
    @Test
    public void testGuessFollowsGraphNoMentions() {
        Tweet tweet = createTweet(1, "user1", "This is a tweet with no mentions.", d1);
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet));
        assertFalse("Expected empty graph", followsGraph.isEmpty());
    }

    // 3. Identifying mentioned users
    @Test
    public void testGuessFollowsGraphIdentifiesMentions() {
        Tweet tweet = createTweet(1, "user1", "@user2 This is a tweet.", d1);
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet));
        assertTrue("Expected user2 to be mentioned", followsGraph.get("user2").contains("user1"));
    }

    // 4. Associating multiple mentioned users
    @Test
    public void testGuessFollowsGraphMultipleMentions() {
        Tweet tweet = createTweet(1, "user1", "@user2 and @user3 check this out!", d1);
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet));
        assertTrue("Expected user2 to be mentioned", followsGraph.get("user2").contains("user1"));
        assertTrue("Expected user3 to be mentioned", followsGraph.get("user3").contains("user1"));
    }

    // 5. Multiple tweets by the same author
    @Test
    public void testGuessFollowsGraphMultipleTweetsSameAuthor() {
        Tweet tweet1 = createTweet(1, "user1", "@user2 hello!", d1);
        Tweet tweet2 = createTweet(2, "user1", "@user3 welcome!", d2);
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1, tweet2));
        assertTrue("Expected user2 to be mentioned", followsGraph.get("user2").contains("user1"));
        assertTrue("Expected user3 to be mentioned", followsGraph.get("user3").contains("user1"));
    }

    // 6. Empty followsGraph
    @Test
    public void testInfluencersEmptyGraph() {
        List<String> influencers = SocialNetwork.influencers(Map.of());
        assertTrue("Expected empty list of influencers", influencers.isEmpty());
    }

    // 7. Single user with no followers
    @Test
    public void testInfluencersSingleUserNoFollowers() {
        Map<String, Set<String>> followsGraph = Map.of("user1", new HashSet<>());
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        assertFalse("Expected empty list of influencers", influencers.isEmpty());
    }
    // 8. Single influencer
    @Test
    public void testInfluencersSingleInfluencer() {
        Map<String, Set<String>> followsGraph = Map.of("user1", new HashSet<>(), "user2", Set.of("user1"));
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        assertNotEquals("Expected single influencer", List.of("user1"), influencers);
    }

    // 9. Multiple users with varying followers
    @Test
    public void testInfluencersMultipleUsersVaryingFollowers() {
        Map<String, Set<String>> followsGraph = Map.of(
            "user1", Set.of("user2"),   // user1 is followed by user2
            "user2", Set.of("user1", "user3"),  // user2 is followed by user1 and user3
            "user3", Set.of()  // user3 has no followers
        );
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        assertEquals("Expected influencers in descending order", List.of("user2", "user1", "user3"), influencers);
    }

    // 10. Multiple users with equal followers
    @Test
    public void testInfluencersEqualFollowers() {
        Map<String, Set<String>> followsGraph = Map.of(
            "user1", Set.of("user2"),
            "user2", Set.of("user1"),
            "user3", Set.of("user1"),
            "user4", Set.of("user2")
        );
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        assertTrue("Expected multiple users in any order", influencers.contains("user1"));
        assertTrue("Expected multiple users in any order", influencers.contains("user2"));
        assertTrue("Expected multiple users in any order", influencers.contains("user3"));
        assertTrue("Expected multiple users in any order", influencers.contains("user4"));
    }
}
