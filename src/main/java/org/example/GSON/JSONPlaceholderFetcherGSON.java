package org.example.GSON;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.Post;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class JSONPlaceholderFetcherGSON {



    private final String postsURL = "https://jsonplaceholder.typicode.com/posts";

    private final HttpClient client = HttpClient.newHttpClient();

    public Post getSinglePostReturnPostGSON(int id) {

        Gson gson = new Gson();
        JSONMapperGSON jsonMapper = new JSONMapperGSON(gson);

        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(postsURL + "/" + id)).GET().build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return jsonMapper.mapToGSON(response.body(), Post.class);

        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Post> getAllPostsInListGSON() {
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(postsURL)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Gson gson = new Gson();
            Type postListType = new TypeToken<List<Post>>() {
            }.getType();
            return gson.fromJson(response.body(), postListType);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public String mapToJSONGSON(Post post) {
        Gson gson = new Gson();
        return gson.toJson(post);
    }

    public List<Post> getAllPostsInHandMadeUnsafe() {

        Gson gson = new Gson();
        JSONMapperGSON jsonMapper = new JSONMapperGSON(gson);
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(postsURL)).GET().build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


            String postsJson = response.body().substring(response.body().indexOf('[') + 1, response.body().indexOf(']') - 1);
            int firstIndex = postsJson.indexOf('"');

            int lastIndex = postsJson.lastIndexOf('}');

            postsJson = response.body().substring(firstIndex, lastIndex);


            String[] posts = postsJson.split("\\},\\s*\\{");


            List<Post> postsList = new ArrayList<>();

            for (String stringPost : posts) {
                stringPost = stringPost.trim();
                stringPost = '{' + stringPost + '}';
                Post post = jsonMapper.mapToGSON(stringPost, Post.class);
                postsList.add(post);

            }
            return postsList;

        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
