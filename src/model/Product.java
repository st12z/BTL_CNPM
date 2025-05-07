/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author T
 */
public class Product {
    private int id;
    private String name;
    private String unit;
    private int categoryId;

    public Product() {
    }

    public Product(int id, String name, String unit, int categoryId) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.categoryId = categoryId;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", unit=" + unit + ", categoryId=" + categoryId + '}';
    }
    public Object[] toObject() {
        return new Object[]{
            id,
            name,
            unit,
            categoryId,
        };
    }
}
