/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import doiTuong.user;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author thao
 */
public class kiemTraDangKi {

    public user user1;

    public kiemTraDangKi() {
    }

    public kiemTraDangKi(user user1) {
        this.user1 = user1;
    }

    public boolean kiemTraRong() {
        if (user1.getHoten().length() < 1 || user1.getNgaySinh().length() < 1 || user1.getUser().length() < 1 || user1.getSoDT() < 0) {
            return false;
        }
        return true;
    }

    public boolean dangKi() {
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
                String id = res.getString("user");
               
                if(id.equals(user1.getUser())){
                    return false;
                }
                
            }
            String hoTen1 = user1.getHoten();
            String ngaySinh1 = user1.getNgaySinh();
            String user11 = user1.getUser();
            String password1 = user1.getPassword();
            String lai_password = user1.getLaiPassword();
            String email1 = user1.getEmail();
            int soDT1 = user1.getSoDT();

//INSERT INTO `user` (`hoTen`, `ngaySinh`, `user`, `password`, `lai_password`, `email`, `soDT`) VALUES ('Doan ngoc vuong', '5/5/1995', 'vuongthieu', 'vuongthieu37', 'vuongthieu37', 'vuong@gmail.com', '0987028610');
            int val = st.executeUpdate("INSERT into user VALUES('" + hoTen1 + "', '" + ngaySinh1 + "', '" + user11 + "', '" + password1 + "', '" + lai_password + "', '" + email1 + "', '" + soDT1 + "')");
            
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
