package com.learn.nolan.spring.lifeCycle.demo1;

/**
 * description:
 *
 * @author zhun.huang 2019-03-19
 */
public class Cat {

    private String nick;

    private int price;

    public Cat(String nick, int price) {
        this.nick = nick;
        this.price = price;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{\"Cat\":{"
                + "\"nick\":\"" + nick + "\""
                + ", \"price\":\"" + price + "\""
                + "}}";
    }
}
