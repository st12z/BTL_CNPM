/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Payment;
/**
 *
 * @author T
 */
public class PaymentDAO extends Dao{
    public List<Payment> getListPayment(int contractId){
        String sql = "SELECT * FROM tblPayment where tblContractId=?";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, contractId);
            ResultSet rs = st.executeQuery();
            List<Payment> payments = new ArrayList<>();
            while(rs.next()){
                payments.add(new Payment(rs.getInt("id"),rs.getDate("paymentDate"),rs.getFloat("paymentAmount"),
                        rs.getFloat("remainingDebt"),rs.getInt("tblContractId")
                ));
            }
            return payments;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
