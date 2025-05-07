/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author T
 */
public class Item {
    private int id;
    private float unitPrice;
    private int productId;
    private int businessPartnerId;

    public Item() {
    }

    public Item(int id, float unitPrice, int productId, int businessPartnerId) {
        this.id = id;
        this.unitPrice = unitPrice;
        this.productId = productId;
        this.businessPartnerId = businessPartnerId;
    }

    public int getId() {
        return id;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public int getProductId() {
        return productId;
    }

    public int getBusinessPartnerId() {
        return businessPartnerId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setBusinessPartnerId(int businessPartnerId) {
        this.businessPartnerId = businessPartnerId;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", unitPrice=" + unitPrice + ", productId=" + productId + ", businessPartnerId=" + businessPartnerId + '}';
    }
    public Object[] toObject() {
        return new Object[]{
            id,
            unitPrice,
            productId,
            businessPartnerId
        };
    }
}
