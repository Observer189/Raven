package com.example.raven.Model;

import java.util.ArrayList;

public class Chat {
    private int id;
    private int adresatId;
    private String adresatName;

    private ArrayList<Message> messages;

    public Chat()
    {
        messages=new ArrayList<Message>();
    }

    public int getId() {
        return id;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public int getAdresatId() {
        return adresatId;
    }

    public String getAdresatName() {
        return adresatName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAdresatId(int adresatId) {
        this.adresatId = adresatId;
    }

    public Message getLastMessage()
    {
        if(messages.size()>0)
        return messages.get(messages.size()-1);
        else return null;
    }

    public void setAdresatName(String adresatName) {
        this.adresatName = adresatName;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
}
