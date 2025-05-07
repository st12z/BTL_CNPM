/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.*;
import model.Customer;
import model.Contract;

/**
 *
 * @author T
 */
public class ContractStatisticsDAO extends Dao {

    public List<ContractStatistics> getListCustomerByDept() {
        String sql = "SELECT * from tblCustomer ";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            List<Customer> listCustomers = new ArrayList<>();
            if (rs.next()) {
                while (rs.next()) {
                    listCustomers.add(new Customer(rs.getInt("id"), rs.getNString("name"), rs.getString("phoneNumber")));
                }
            }
            System.out.println(listCustomers);
            for (Customer customer : listCustomers) {
                ContractStatistics contractStatistics = new ContractStatistics();
                contractStatistics.setId(customer.getId());
                contractStatistics.setName(customer.getName());
                contractStatistics.setPhoneNumber(customer.getPhoneNumber());
                sql = "SELECT * from tblContract where tblCustomerId=?";
                List<Contract> listContracts = new ArrayList<>();
                st = con.prepareStatement(sql);
                st.setInt(1, customer.getId());
                rs = st.executeQuery();
                if (rs.next()) {
                    while (rs.next()) {
                        listContracts.add(new Contract(rs.getInt("id"), rs.getDate("signDate"),
                                rs.getFloat("totalLoan"), rs.getDate("loanTerm"),
                                rs.getInt("tblCustomerId"), rs.getInt("tblUserId"), rs.getInt("tblBusinessPartnerId")));
                    }
                    System.out.println(listContracts);
                    float outstandingBalance = 0;
                    float overdueBalance = 0;
                    List<Payment> listPayments = new ArrayList<>();
                    for (Contract contract : listContracts) {
                        sql = "SELECT * from tblPayment where tblContractId=?";
                        st = con.prepareStatement(sql);
                        st.setInt(1, contract.getId());
                        rs = st.executeQuery();
                        if (rs.next()) {
                            while (rs.next()) {
                                listPayments.add(new Payment(rs.getInt("id"), rs.getDate("paymentDate"),
                                        rs.getFloat("paymentAmount"), rs.getFloat("remainingDept"),
                                        rs.getInt("tblContractId")));
                            }
                            outstandingBalance += listPayments.get(listPayments.size() - 1).getRemainingDept();
                            for (int i = 0; i < listPayments.size(); i++) {
                                if (i > 1) {
                                    Payment paymentBefore = listPayments.get(i - 1);
                                    Payment paymentCurrent = listPayments.get(i);
                                    if (listPayments.get(i).getPaymentAmount() == 0) {
                                        overdueBalance +=paymentCurrent.getRemainingDept()-paymentBefore.getRemainingDept();
                                    }
                                }
                            }
                        }

                    }
                    contractStatistics.set
                }
            }catch(Exception e){
            e.printStackTrace();
        }
            return null;
        }
    }
