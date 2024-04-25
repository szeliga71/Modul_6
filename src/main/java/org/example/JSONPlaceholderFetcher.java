package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


public class JSONPlaceholderFetcher {


    private final String postsURL = "https://jsonplaceholder.typicode.com/posts";

    private final HttpClient client = HttpClient.newHttpClient();

    private ObjectMapper objectMapper = new ObjectMapper();


    public String getSinglePostReturnString(int id) {

        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(postsURL + "/" + id)).GET().build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();

        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public Post getSinglePostReturnPost(int id) {


        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(postsURL + "/" + id)).GET().build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return objectMapper.readValue(response.body(), Post.class);

        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean addPost(String content) {

        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(postsURL))
                    .POST(HttpRequest.BodyPublishers.ofString(content))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                return true;
            } else {
                return false;
            }
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);

        }
    }

    public String getAllPosts() {

        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(postsURL)).GET().build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();

        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Post> getAllPostsInList() {
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(postsURL)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return objectMapper.readValue(response.body(), new TypeReference<List<Post>>() {
            });

        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


    public String mapToJSON(Post post) throws JsonProcessingException {
        return objectMapper.writeValueAsString(post);
    }


}



