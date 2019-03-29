package com.example.raven.Model;

import javax.crypto.SecretKey;

public class Key {
    private int authorId;
    private int adrId;
    private SecretKey key;

    public Key(int authorId, int adrId, SecretKey key) {
        this.authorId = authorId;
        this.adrId = adrId;
        this.key = key;
    }

    void setKey(SecretKey key) {
        this.key = key;
    }

    void setAdrId(int adrId) {
        this.adrId = adrId;
    }

    void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    SecretKey getKey() {
        return key;
    }

    int getAdrId() {
        return adrId;
    }

    int getAuthorId() {
        return authorId;
    }
}
