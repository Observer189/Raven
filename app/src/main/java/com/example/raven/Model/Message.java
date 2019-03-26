package com.example.raven.Model;

public class Message {
    String author;
    String text;
    long time;
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
