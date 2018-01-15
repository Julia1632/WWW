package com.vk_2.DataModel;

/**
 * Created by zigin on 11.10.2017.
 */

public class Item {
    private String name;
    private int lenght;
    private String url;

    public Item(String name, int lenght, String url) {
        this.name = name;
        this.lenght = lenght;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
