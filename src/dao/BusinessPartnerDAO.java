/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.BusinessPartner;
/**
 *
 * @author T
 */
public class BusinessPartnerDAO extends Dao{
    public BusinessPartner getBusinessPartner(int businessPartnerId){
        String sql = "SELECT * FROM tblBusinessPartner where id=?";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, businessPartnerId);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                BusinessPartner b = new BusinessPartner(rs.getInt("id"),rs.getNString("name"),
                    rs.getString("address"),rs.getInt("tblCompanyId")
                );
                return b;
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
