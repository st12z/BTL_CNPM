/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author T
 */
public class ContractStatistics extends Customer{
   private float outstandingBalance;
   private float overdueBalance;

    public ContractStatistics() {
    }

    public ContractStatistics(float outstandingBalance, float overdueBalance, int id, String name, String phoneNumber) {
        super(id, name, phoneNumber);
        this.outstandingBalance = outstandingBalance;
        this.overdueBalance = overdueBalance;
    }

    public float getOutstandingBalance() {
        return outstandingBalance;
    }

    public float getOverdueBalance() {
        return overdueBalance;
    }

    public void setOutstandingBalance(float outstandingBalance) {
        this.outstandingBalance = outstandingBalance;
    }

    public void setOverdueBalance(float overdueBalance) {
        this.overdueBalance = overdueBalance;
    }

    @Override
    public String toString() {
        return "ContractStatistics{" + "outstandingBalance=" + outstandingBalance + ", overdueBalance=" + overdueBalance + '}';
    }

   public Object[] toObject() {
        return new Object[]{
            outstandingBalance,
            overdueBalance
        };
    }
   
}
