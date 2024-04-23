package org.example;

public class User {

    private long id;
    private String name;
    private int age;

    public User(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
