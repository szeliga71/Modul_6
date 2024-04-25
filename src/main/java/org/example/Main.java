package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public class Main {
    public static void main(String[] args) {


        JSONPlaceholderFetcher jpF225 = new JSONPlaceholderFetcher();
        System.out.println(jpF225.getAllPosts());


        System.out.println(jpF225.getSinglePostReturnString(1));


        System.out.println(jpF225.addPost("Ala ma kota"));

        System.out.println(jpF225.getAllPosts());

        System.out.println();

        String jonson = "{\"userId\": 1,\n" +
                "            \"id\": 10,\n" +
                "            \"title\": \"optio molestias id quia eum\",\n" +
                "            \"body\": \"quo et expedita modi cum officia vel magni\\ndoloribus qui repudiandae\\nvero nisi sit\\nquos veniam quod sed accusamus veritatis error\"}";


        JSONMapper jsonMapper = new JSONMapper();

        Post post = jsonMapper.mapTo(jonson, Post.class);


        System.out.println(post.getBody());
        System.out.println(post.getUserId());
        System.out.println(post.getId());
        System.out.println(post.getTitle());


        String jsonUser = "{\"id\": 123,\n" +
                "\"name\": \"John Doe\",\n" +
                "\"age\": 30 }";


        User u1 = jsonMapper.mapTo(jsonUser, User.class);

        System.out.println(u1.getId());
        System.out.println(u1.getName());
        System.out.println(u1.getAge());


        String jsonUser1 = "{\"id\": 15,\n" +
                "\"name\": \"Jack Daniels\",\n" +
                "\"age\": 25 }";


        User u2 = jsonMapper.mapTo(jsonUser1, User.class);

        System.out.println(u2.getId());
        System.out.println(u2.getName());
        System.out.println(u2.getAge());


        JSONPlaceholderFetcher jpF1 = new JSONPlaceholderFetcher();
        List<Post> listOfPost;
        listOfPost = jpF1.getAllPostsInList();

        for (Post postTemp : listOfPost) {

            System.out.println("--" + postTemp.getId());
        }

        List<Post> listOfPosts2 = jpF1.getAllPostsInList();

        for (Post postTemp1 : listOfPosts2) {

            System.out.println("--" + postTemp1.getBody());
        }

        System.out.println();
        Post postTemp2 = jpF1.getSinglePostReturnPost(2);


        try {
            System.out.println(jpF1.mapToJSON(postTemp2));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }

}