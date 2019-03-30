package com.example.raven.Model;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Chat {
    private int id;
    private int adresatId;
    private String adresatName;
    private SecretKey key;  //НАДо УБРАТЬ

    private ArrayList<Message> messages;

    public Chat(SecretKey key)
    {
        messages=new ArrayList<>();
        this.key = key;
    }

    public Chat()
    {
        messages=new ArrayList<>();
                              //НАДо УБРАТЬ
        try {
            key = KeyGenerator.getInstance("DES").generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
                              //НАДо УБРАТЬ
    }

    public int getId() {
        return id;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

                      //НАДо УБРАТЬ
    public void setKey(SecretKey key) {
        this.key = key;
    }

    public SecretKey getKey() {
        return key;
    }
                              //НАДо УБРАТЬ

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
