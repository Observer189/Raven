package com.example.raven.Model;

import java.util.ArrayList;

public class Chat {
    private int id;
    private int adresatId;
    private String adresatName;
    private Message lastMessage;
    private ArrayList<Message> messages;

    public int getId() {
        return id;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public int getAdresatId() {
        return adresatId;
    }

    public Message getLastMessage() {
        return lastMessage;
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

    public void setAdresatName(String adresatName) {
        this.adresatName = adresatName;
    }

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
}
