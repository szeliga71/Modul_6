import org.example.JSONMapper;
import org.example.JSONPlaceholderFetcher;
import org.example.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JSONPlaceholderFetcherTest {


    @Mock
    private HttpResponse<String> response;


    private JSONPlaceholderFetcher jsonPlaceholderFetcher = new JSONPlaceholderFetcher();


    private JSONMapper jsonMapper;

    @Test
    public void happyTestPath() {
        when(response.statusCode()).thenReturn(200);
        when(response.body()).thenReturn("{\n" +
                "  \"userId\": 1,\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n" +
                "  \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n" +
                "}");


        Post expectedPost = new Post(1, 1, "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum " +
                        "rerum est autem sunt rem eveniet architecto");

        when(jsonPlaceholderFetcher.getSinglePostReturnString(1)).thenReturn(response.body());
        when(jsonPlaceholderFetcher.getSinglePostReturnPost(1)).thenReturn(expectedPost);


        Assertions.assertEquals(response.body(), jsonPlaceholderFetcher.getSinglePostReturnString(1));
        Assertions.assertEquals(expectedPost, jsonPlaceholderFetcher.getSinglePostReturnPost(1));

    }


    @Test
    public void happyTestPath1() {


        when(response.body()).thenReturn("{\n" +
                "  \"userId\": 1,\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n" +
                "  \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n" +
                "}");
        //when(jsonPlaceholderFetcher.getSinglePostReturnString(1)).thenReturn(response.body());
        Assertions.assertEquals(response.body(), jsonPlaceholderFetcher.getSinglePostReturnString(1));

    }
    @Test
    public void happyTestPath2() {
        Post expectedPost = new Post(1, 1, "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum " +
                        "rerum est autem sunt rem eveniet architecto");

        when(jsonPlaceholderFetcher.getSinglePostReturnPost(1)).thenReturn(expectedPost);
        Assertions.assertEquals(expectedPost, jsonPlaceholderFetcher.getSinglePostReturnPost(1));
    }
}
