/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.User;
/**
 *
 * @author T
 */
public class UserDAO extends Dao{
    public boolean checkLogin(User u){
        String sql = "SELECT * from tblUser where username=? and password=?";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, u.getUsername());
            st.setString(2, u.getPassword());
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
