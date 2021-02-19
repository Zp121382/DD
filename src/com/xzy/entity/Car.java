package com.xzy.entity;

/**
 * @author Invisible
 * @version 0.1
 * @date 2021/2/19 23:09
 */
public class Car {
    private Integer id;
    private String name;
    private Integer price;
    private Integer zkl;
    private Integer zhl;

    public Car() {
    }

    public Car(Integer id, String name, Integer price, Integer zkl, Integer zhl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.zkl = zkl;
        this.zhl = zhl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getZkl() {
        return zkl;
    }

    public void setZkl(Integer zkl) {
        this.zkl = zkl;
    }

    public Integer getZhl() {
        return zhl;
    }

    public void setZhl(Integer zhl) {
        this.zhl = zhl;
    }
}
