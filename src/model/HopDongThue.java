/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author T
 */
public class HopDongThue {
    private int MaHopDong;
    private int SoNgayThueItNhat;
    private int DenBu;

    public HopDongThue() {
    }
    
    public HopDongThue(int MaHopDong, int SoNgayThueItNhat, int DenBu) {
        this.MaHopDong = MaHopDong;
        this.SoNgayThueItNhat = SoNgayThueItNhat;
        this.DenBu = DenBu;
    }

    public int getMaHopDong() {
        return MaHopDong;
    }

    public int getSoNgayThueItNhat() {
        return SoNgayThueItNhat;
    }

    public int getDenBu() {
        return DenBu;
    }

    public void setMaHopDong(int MaHopDong) {
        this.MaHopDong = MaHopDong;
    }

    public void setSoNgayThueItNhat(int SoNgayThueItNhat) {
        this.SoNgayThueItNhat = SoNgayThueItNhat;
    }

    public void setDenBu(int DenBu) {
        this.DenBu = DenBu;
    }

    @Override
    public String toString() {
        return "HopDongThue{" + "MaHopDong=" + MaHopDong + ", SoNgayThueItNhat=" + SoNgayThueItNhat + ", DenBu=" + DenBu + '}';
    }
    
    
}
