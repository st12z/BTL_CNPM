/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Item;

/**
 *
 * @author T
 */
public class ItemDAO extends Dao{
    public Item getItem(int itemId){
        String sql = "SELECT * FROM tblItem where id=?";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, itemId);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                Item i = new Item(rs.getInt("id"),rs.getFloat("unitPrice"),
                    rs.getInt("tblProductId"),rs.getInt("tblBusinessPartnerId")
                );
                return i;
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
