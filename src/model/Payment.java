/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author T
 */
public class Payment {
    private int id;
    private Date paymentDate;
    private float paymentAmount;
    private float remainingDept;
    private int contractId;

    public Payment() {
    }
    
    public Payment(int id, Date paymentDate, float paymentAmount, float remainingDept, int contractId) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.remainingDept = remainingDept;
        this.contractId = contractId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public float getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(float paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public float getRemainingDept() {
        return remainingDept;
    }

    public void setRemainingDept(float remainingDept) {
        this.remainingDept = remainingDept;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    @Override
    public String toString() {
        return "Payment{" + "id=" + id + ", paymentDate=" + paymentDate + ", paymentAmount=" + paymentAmount + ", remainingDept=" + remainingDept + ", contractId=" + contractId + '}';
    }
    public Object[] toObject() {
        return new Object[]{
            id,
            paymentDate,
            paymentAmount,
            remainingDept,
            contractId
        };
    }
}
