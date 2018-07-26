/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author thao
 */
public class kiemTraDangNhap {

    private String user2;
    private String password2;

    public kiemTraDangNhap() {
    }

    public kiemTraDangNhap(String user, String password) {
        this.user2 = user;
        this.password2= password;
    }

    public boolean kiemTraRong() {
        if (user2.length() < 1 || password2.length() < 1) {
            return false;
        }
        return true;
    }

    public boolean kiemTraThongTin() {
        if(kiemTraRong()!=true){
            return false;
        }
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "otp";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "";
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url + dbName, userName, password);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM user");
            while (res.next()) {
                String user1 = res.getString("user");
                String pass1 = res.getString("password");
                
                if(user1.equals(user2)&&pass1.equals(password2)){
                 
                     return true;
                }
            }
          


            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }
}
