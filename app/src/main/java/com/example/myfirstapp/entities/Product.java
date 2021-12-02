package com.example.myfirstapp.entities;

import java.io.Serializable;

@Entity
public class Product implements Serializable {


    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "description")
    public String description;
    @ColumnInfo(name = "price")
    public double price;
    @ColumnInfo(name = "quantityInStock")
    public double quantityInStock;
    @ColumnInfo(name = "alertQuantity")
    public double alertQuantity;
    @ColumnInfo(name = "serverId")
    public int serverId;
    public static String TABLE_NAME = "Product";
    public static String CREATE_TABLE_PRODUCT = "CREATE TABLE "+TABLE_NAME+" (id INTEGER PRIMARY KEY,"+
                                                                               "name VARCHAR(255),"+
                                                                               "description VARCHAR(255),"+
                                                                               "price REAL default 0,"+
                                                                               "quantityInStock REAL default 0,"+
                                                                               "alertQuantity REAL default 0);";

    public Product() {
    }

    public Product(String name, String description, double price, double quantityInStock, double alertQuantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.alertQuantity = alertQuantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantityInStock=" + quantityInStock +
                ", alertQuantity=" + alertQuantity +
                '}';
    }
}
