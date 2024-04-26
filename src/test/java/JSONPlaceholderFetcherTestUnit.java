import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.JSONPlaceholderFetcher;
import org.example.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JSONPlaceholderFetcherTestUnit {


    private JSONPlaceholderFetcher jsonPlaceholderFetcher = new JSONPlaceholderFetcher();


    @Test
    public void getAllPostsReturnList() {
        int size = 100;
        List<Post> posts = jsonPlaceholderFetcher.getAllPostsInList();
        Assertions.assertEquals(size, posts.size());
    }

    @Test
    public void getSinglePostReturnStringCheckResult() {

        String postNr1 = "{\n" +
                "  \"userId\": 1,\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n" +
                "  \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n" +
                "}";


        Assertions.assertEquals(jsonPlaceholderFetcher.getSinglePostReturnString(1), postNr1);
    }

    @Test
    public void getSinglePostReturnPostCheckResult() {

        Post testPost = new Post(1, 1, "sunt aut facere repellat provident occaecati excepturi optio reprehenderit", "quia et suscipit\n"+"suscipit recusandae consequuntur expedita et cum\n"+"reprehenderit molestiae ut ut quas totam\n"+"nostrum rerum est autem sunt rem eveniet architecto");

        Post expectedPost=jsonPlaceholderFetcher.getSinglePostReturnPost(1);

        Assertions.assertEquals(testPost,expectedPost);
    }

    @Test
    public void mapToJonsonTest() {

        Post testPost = new Post(3, 115, "Kaczka Dziwaczka", "Na brzegu opodal krzaczka mieszkala kaczka dziwaczka,\nlecz zamiast trzymac sie rzeczki robila piesze wycieczki");
        String testString = "{\"userId\":3,\"id\":115,\"title\":\"Kaczka Dziwaczka\",\"body\":\"Na brzegu opodal krzaczka mieszkala kaczka dziwaczka,\\nlecz zamiast trzymac sie rzeczki robila piesze wycieczki\"}";


        try {
            Assertions.assertEquals(testString, jsonPlaceholderFetcher.mapToJSON(testPost));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
