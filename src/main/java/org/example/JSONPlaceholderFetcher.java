package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;



public class JSONPlaceholderFetcher {


    private final String postsURL = "https://jsonplaceholder.typicode.com/posts";

    private final HttpClient client = HttpClient.newHttpClient();

    //private ObjectMapper objectMapper = new ObjectMapper();


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

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response.body(),Post.class);                       // mapTo(response.body(), Post.class);

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


            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response.body(), new TypeReference<List<Post>>() {});

        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


    public String mapToJSON(Post post) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(post);
    }

    //====================================================================================
    //=================================== GSON ===========================================

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
        ObjectMapper objectMapper = new ObjectMapper();
        Gson gson = new Gson();
        return gson.toJson(post);
    }
    public List<Post> getAllPostsInHandMadeUnsafe() {

        Gson gson = new Gson();
        JSONMapperGSON jsonMapper = new JSONMapperGSON(gson);
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(postsURL)).GET().build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


            String postsJson= response.body().substring(response.body().indexOf('[') + 1, response.body().indexOf(']') - 1);
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



