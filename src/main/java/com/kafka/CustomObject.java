package com.kafka;

import java.util.Date;

public class CustomObject {

    private String name;
    private int age;
    private Date timestamp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public CustomObject(String id, String name, int age) {
        this.name = name;
        this.age = age;
        this.timestamp = new Date();
    }

    public CustomObject() {
    }

    @Override
    public String toString()
    {
        return "CustomObject [name="
                + name
                + ", age="
                + age
                + ", timestamp="
                + timestamp
                + "]";
    }
}
