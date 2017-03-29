package com.example.ccrecyclerview;

/**
 * Created by henryzheng on 2017/3/15.
 */
public class Person {
    private String name;
    private String year;
    public Person(String name, String year) {
        this.name = name;
        this.year = year;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
}
