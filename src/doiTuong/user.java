/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doiTuong;

import java.io.Serializable;



/**
 *
 * @author thao
 */
public class user implements Serializable{
    private String hoten;
    private String ngaySinh;
    private String user;
    private String password;
    private String laiPassword;
    private String email;
    private int soDT;
 

    public user() {
    }

    public user(String hoten, String ngaySinh, String user, String password, String laiPassword, String email, int soDT) {
        this.hoten = hoten;
        this.ngaySinh = ngaySinh;
        this.user = user;
        this.password = password;
        this.laiPassword = laiPassword;
        this.email = email;
        this.soDT = soDT;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLaiPassword() {
        return laiPassword;
    }

    public void setLaiPassword(String laiPassword) {
        this.laiPassword = laiPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSoDT() {
        return soDT;
    }

    public void setSoDT(int soDT) {
        this.soDT = soDT;
    }
    
}
