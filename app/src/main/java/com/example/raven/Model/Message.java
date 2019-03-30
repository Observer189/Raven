package com.example.raven.Model;

public class Message {
    private String authorName;
    private int authorId;
    private int adrId;
    private String text;
    private long time;
    private String key;

    public Message()
    {

    }

    public Message(int authorId, int adrId, String text, String key) {
        this.authorId = authorId;
        this.adrId = adrId;
        this.text = text;
        this.key = key;
    }

    public Message(int authorId,int adrId,String text)
    {
        this.authorId =authorId;
        this.adrId=adrId;
        this.text=text;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
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
