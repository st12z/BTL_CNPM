/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ItemContract;
/**
 *
 * @author T
 */
public class ItemContractDAO extends Dao{
    public List<ItemContract> getListItemContracts(int contractId){
        String sql = "SELECT * FROM tblItemContract where tblContractId=?";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, contractId);
            ResultSet rs = st.executeQuery();
            List<ItemContract> result = new ArrayList<>();
            while(rs.next()){
                result.add(new ItemContract(rs.getInt("id"),rs.getInt("tblItemId"),
                        rs.getInt("quantity"),rs.getInt("tblContractId")
                ));
            };
            return result;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
