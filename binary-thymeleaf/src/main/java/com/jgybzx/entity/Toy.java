package com.jgybzx.entity;

/**
 * des:
 *
 * @author jgybzx
 * @date 2021/7/14 15:28
 */
public class Toy {
    private String toyName;
    private double price;

    public Toy() {
    }

    public Toy(String toyName, double price) {
        this.toyName = toyName;
        this.price = price;
    }

    public String getToyName() {
        return toyName;
    }

    public void setToyName(String toyName) {
        this.toyName = toyName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
