package com.jgybzx.entity;

/**
 * des:
 *
 * @author jgybzx
 * @date 2021/7/14 10:44
 */
public class User {
    private String name;
    private int age;
    private String address;
    private Toy toy;

    public User() {
    }

    public User(String name, int age, Toy toy) {
        this.name = name;
        this.age = age;
        this.toy = toy;
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

    public Toy getToy() {
        return toy;
    }

    public void setToy(Toy toy) {
        this.toy = toy;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
