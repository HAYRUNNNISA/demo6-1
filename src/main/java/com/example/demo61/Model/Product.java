package com.example.demo61.Model;



public class Product {
    private int id;
    private String name;
    private String supplier;
    private int price;

    public Product() {
    }

    public Product(int id, String name, String supplier, int price) {
        this.id = id;
        this.name = name;
        this.price=price;
        this.supplier = supplier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price= price;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", supplier='" + supplier + '\'' +
                ", price='" + price + '\'' +

                '}';
    }
}

