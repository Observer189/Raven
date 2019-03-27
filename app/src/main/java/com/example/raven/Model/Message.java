package com.example.raven.Model;

public class Message {
    String authorName;
    int authorId;
    int adrId;
    String text;
    long time;
    public Message()
    {

    }
    public Message(int authorId,int adrId,String text)
    {
        this.authorId =authorId;
        this.adrId=adrId;
        this.text=text;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getText() {
        return text;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getAdrId() {
        return adrId;
    }

    public void setAdrId(int adrId) {
        this.adrId = adrId;
    }
}
