package com.example.raven.Model;

public class Contact {
    private String name;
    private int id;
    private Chat chat;
    public Contact(int id,String name)
    {
        this.id=id;
        this.name=name;
    }
    public Contact()
    {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    @Override
    public String toString() {
        return getName()+"id:"+id;

    }
}
