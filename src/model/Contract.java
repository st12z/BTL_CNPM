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
public class Contract {

    private int id;
    private Date signDate;
    private float totalLoan;
    private Date loanTerm;
    private int customerId;
    private int userId;
    private int businessPartnerId;

    public Contract() {
    }

    public Contract(int id, Date signDate, float totalLoan, Date loanTerm, int customerId, int userId, int businessPartnerId) {
        this.id = id;
        this.signDate = signDate;
        this.totalLoan = totalLoan;
        this.loanTerm = loanTerm;
        this.customerId = customerId;
        this.userId = userId;
        this.businessPartnerId = businessPartnerId;
    }

    public int getId() {
        return id;
    }

    public Date getSignDate() {
        return signDate;
    }

    public float getTotalLoan() {
        return totalLoan;
    }

    public Date getLoanTerm() {
        return loanTerm;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getUserId() {
        return userId;
    }

    public int getBusinessPartnerId() {
        return businessPartnerId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public void setTotalLoan(float totalLoan) {
        this.totalLoan = totalLoan;
    }

    public void setLoanTerm(Date loanTerm) {
        this.loanTerm = loanTerm;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setBusinessPartnerId(int businessPartnerId) {
        this.businessPartnerId = businessPartnerId;
    }

    @Override
    public String toString() {
        return "Contact{" + "id=" + id + ", signDate=" + signDate + ", totalLoan=" + totalLoan + ", loanTerm=" + loanTerm + ", customerId=" + customerId + ", userId=" + userId + ", businessPartnerId=" + businessPartnerId + '}';
    }

    public Object[] toObject() {
        return new Object[]{
            id,
            signDate,
            totalLoan,
            loanTerm,
            customerId,
            userId,
            businessPartnerId,};
    }
}
