import com.google.gson.Gson;
import org.example.JSONMapper;
import org.example.JSONMapperGSON;
import org.example.Post;
import org.example.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class JSONMapperTest {

    private Gson gson = new Gson();
    private JSONMapper jsonMapper = new JSONMapper();


    @Test
    public void mapStringJSONToPost() {

        String jsonTest = "{\"userId\": 35, \"id\": 235, \"title\": \"mapping test\", \"body\": \"long long texxt....\"}";
        Post post = jsonMapper.mapTo(jsonTest, Post.class);

        Assertions.assertEquals(35, post.getUserId());
        Assertions.assertEquals(235, post.getId());
        Assertions.assertEquals("mapping test", post.getTitle());
        Assertions.assertEquals("long long texxt....", post.getBody());

    }

    @Test
    public void mapStringJSONToPostEmptyString() {

        String jsonTest = "";
        Assertions.assertThrows(RuntimeException.class,()->{jsonMapper.mapTo(jsonTest, Post.class);});
    }
    @Test
    public void mapStringJSONToPostIllegalStringIllegalArgumentEx() {

        String jsonTest = "niepoprawny string 1234";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            jsonMapper.mapTo(jsonTest, Post.class);
        });
    }
    @Test
    public void mapStringJSONToPostNullString() {

        String jsonTest = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            jsonMapper.mapTo(jsonTest, Post.class);
        });
    }

        @Test
    public void mapStringJSONToUser() {

        String jsonTest = "{\"id\": 35, \"name\": \"Nowak\", \"age\": 36}";
        User user = jsonMapper.mapTo(jsonTest, User.class);

        Assertions.assertEquals(35, user.getId());
        Assertions.assertEquals("Nowak", user.getName());
        Assertions.assertEquals(36, user.getAge());
    }

}
