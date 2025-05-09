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

/**
 *
 * @author T
 */
public class ContractDAO extends  Dao{
    public List<Contract> getListContract(int customerId){
        String sql = "SELECT * FROM tblContract WHERE tblCustomerId=?";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, customerId);
            ResultSet rs = st.executeQuery();
            List<Contract> contracts = new ArrayList<>();
            while(rs.next()){
                contracts.add(new Contract(rs.getInt("id"),rs.getDate("signDate") ,
                rs.getFloat("totalLoan"), rs.getDate("loanTerm"),rs.getInt("tblCustomerId"), rs.getInt("tblUserId"), rs.getInt("tblBusinessPartnerId")));
            }
            return contracts;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
