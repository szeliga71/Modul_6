package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.w3c.dom.ls.LSOutput;

import java.net.http.HttpResponse;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        JSONPlaceholderFetcher jpF225 = new JSONPlaceholderFetcher();
        System.out.println(jpF225.getAllPosts());

        /*System.out.println(jpF.getSinglePost(1));


        System.out.println(jpF.addPost("Ala ma kota"));

        System.out.println(jpF.getAllPosts());

        System.out.println();

        String jonson ="{\"userID\": 1,\n" +
                "            \"id\": 10,\n" +
                "            \"title\": \"optio molestias id quia eum\",\n" +
                "            \"body\": \"quo et expedita modi cum officia vel magni\\ndoloribus qui repudiandae\\nvero nisi sit\\nquos veniam quod sed accusamus veritatis error\"}";*/

        //jonson=jonson.trim();
        //int start=jonson.toLowerCase().                     indexOf("userid\":");

        //System.out.println(start);
        //long userId = Long.parseLong(jonson.substring(start, jonson.indexOf(",")).trim());
        //System.out.println(userId+22);


       /* Gson gson = new Gson();
        Gson gson1 = new Gson();
        JSONMapperGSON jmap=new JSONMapperGSON(gson);

        Post post= jmap.mapTo(jonson,Post.class);



        System.out.println(post.getBody());
        System.out.println(post.getUserID());
        System.out.println(post.getId());
        System.out.println(post.getTitle());


        JSONMapperGSON jmap1=new JSONMapperGSON(gson1);

        String jsonUser="{\"id\": 123,\n"+
        "\"name\": \"John Doe\",\n"+
        "\"age\": 30 }";


        User u1=jmap1.mapTo(jsonUser,User.class);

        System.out.println(u1.getId());
        System.out.println(u1.getName());
        System.out.println(u1.getAge());


        String jsonUse1="{\"id\": 15,\n"+
                "\"name\": \"Jack Daniels\",\n"+
                "\"age\": 25 }";

        JSONMapper jmom=new JSONMapper();

        User u2=jmom.mapToJackson(jsonUse1,User.class);

        System.out.println(u2.getId());
        System.out.println(u2.getName());
        System.out.println(u2.getAge());*/


        JSONPlaceholderFetcher jpF1 = new JSONPlaceholderFetcher();
        List<Post> listOfPost;
        listOfPost = jpF1.getAllPostsInList();

        for (Post post : listOfPost) {

            System.out.println("--" + post.getId());
        }

        List<Post> listOfPosts2 = jpF1.getAllPostsInList();

        for (Post post : listOfPosts2) {

            System.out.println("--" + post.getBody());
        }

        System.out.println();
        Post post = jpF1.getSinglePostReturnPost(2);


        try {
            System.out.println(jpF1.mapToJSON(post));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        //for(Post pos11 : jpF.getAllPostsInList()){
        //  System.out.println(pos11.getBody());
        //}


    }

}