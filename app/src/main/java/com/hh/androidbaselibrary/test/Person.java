package com.hh.androidbaselibrary.test;

import com.hh.baselibrary.widget.option.ShowString;

/**
 * Created by hHui on 2020/5/15 0015.
 */
public class Person {

    @ShowString
    private String name;

    private int age;


    public Person() {

    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

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
}
