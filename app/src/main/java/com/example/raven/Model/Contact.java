package com.example.raven.Model;

public class Contact {
    private String name;
    private int id;
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

    @Override
    public String toString() {
        return getName()+"id:"+id;

    }
}
