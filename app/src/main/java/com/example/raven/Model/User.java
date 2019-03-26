package com.example.raven.Model;

public class User {
    String name;
    int id;
    public User(String name,int id)
    {
        this.name=name;
        this.id=id;
    }
    public User()
    {

    }
    public void generateName()
    {
        name="User"+Integer.toString((int)(Math.random()*90000000)+10000000);
    }
    public void generateId()
    {
        id=(int)(Math.random()*90000000)+10000000;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }
}
