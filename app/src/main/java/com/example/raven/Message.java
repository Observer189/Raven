package com.example.raven;

public class Message {
    String author;
    String text;
    public Message()
    {

    }
    public Message(String author,String text)
    {
        this.author=author;
        this.text=text;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setText(String text) {
        this.text = text;
    }
}
