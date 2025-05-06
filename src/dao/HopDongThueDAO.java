/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.HopDongThue;
/**
 *
 * @author T
 */
public class HopDongThueDAO extends Dao{
    public List<HopDongThue> getListSinhVien() {
        String sql = "SELECT * FROM HopDongThue";
        List<HopDongThue> list = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                list.add(new HopDongThue(rs.getInt("MaHopDong"), rs.getInt("SoNgayThueItNhat"), rs.getInt("DenBu")));
            }
        } catch (Exception error) {
            System.out.print(error);
        }
        return list;
    }
}
