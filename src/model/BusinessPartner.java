/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author T
 */
public class BusinessPartner {

    private int id;
    private String name;
    private String address;
    private int companyId;

    public BusinessPartner() {
    }

    public BusinessPartner(int id, String name, String address, int companyId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.companyId = companyId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "BusinessPartner{" + "id=" + id + ", name=" + name + ", address=" + address + ", companyId=" + companyId + '}';
    }

    public Object[] toObject() {
        return new Object[]{
            id,
            name,
            address,
            companyId
        };
    }
}
