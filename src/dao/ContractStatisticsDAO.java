/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
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
        ContractDAO contractDAO = new ContractDAO();
        PaymentDAO paymentDAO = new PaymentDAO();
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            List<Customer> listCustomers = new ArrayList<>();

            while (rs.next()) {
                listCustomers.add(new Customer(rs.getInt("id"), rs.getNString("name"), rs.getString("phoneNumber")));
            }

            System.out.println(listCustomers);

            List<ContractStatistics> result = new ArrayList<>();
            for (Customer customer : listCustomers) {
                float outstandingBalance = 0;
                float overdueBalance = 0;
                ContractStatistics contractStatistics = new ContractStatistics();
                contractStatistics.setId(customer.getId());
                contractStatistics.setName(customer.getName());
                contractStatistics.setPhoneNumber(customer.getPhoneNumber());
                
                List<Contract> listContracts = contractDAO.getListContract(customer.getId());
                

                for (Contract contract : listContracts) {
                    List<Payment> listPayments = paymentDAO.getListPayment(contract.getId());
                    

                    outstandingBalance += listPayments.get(listPayments.size() - 1).getRemainingDept();
                    for (int i = 0; i < listPayments.size(); i++) {
                        if (i >= 1 && listPayments.get(i).getPaymentAmount() == 0) {
                            Payment paymentBefore = listPayments.get(i - 1);
                            Payment paymentCurrent = listPayments.get(i);
                            overdueBalance += paymentCurrent.getRemainingDept() - paymentBefore.getRemainingDept();

                        }
                    }
                }
                contractStatistics.setOutstandingBalance(outstandingBalance);
                contractStatistics.setOverdueBalance(overdueBalance);
                result.add(contractStatistics);
            }

            System.out.println(result);
            Collections.sort(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
