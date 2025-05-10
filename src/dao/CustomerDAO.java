/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Contract;
import model.Customer;
/**
 *
 * @author T
 */
public class CustomerDAO extends  Dao{
    public Customer getCustomer(int customerId){
        String sql = "SELECT * FROM tblCustomer where id=?";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, customerId);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                Customer c= new Customer(rs.getInt("id"),rs.getNString("name"),
                    rs.getString("phoneNumber")
                );
                return c;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
