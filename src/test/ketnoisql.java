/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author thao
 */
public class ketnoisql {
public static void main(String[] args) { 
String url = "jdbc:mysql://localhost:3306/"; 
String dbName = "otp"; 
String driver = "com.mysql.jdbc.Driver"; 
String userName = "root"; 
String password = ""; 
try { 
Class.forName(driver).newInstance(); 
Connection conn = DriverManager.getConnection(url+dbName,userName,password); 
Statement st = conn.createStatement(); 
ResultSet res = st.executeQuery("SELECT * FROM user"); 
while (res.next()) { 
String id = res.getString("hoTen"); 
String msg = res.getString("password"); 
System.out.println(id + "\t" + msg); 
} 
String  hoTen1="nguyen van teo";
String ngaySinh1="1/1/1996";
String user1="teo";
String password1="12345";
String email1="nguyenvanteo@gmail.com";
int soDT1=19008098;

//INSERT INTO `user` (`hoTen`, `ngaySinh`, `user`, `password`, `lai_password`, `email`, `soDT`) VALUES ('Doan ngoc vuong', '5/5/1995', 'vuongthieu', 'vuongthieu37', 'vuongthieu37', 'vuong@gmail.com', '0987028610');
int val = st.executeUpdate("INSERT into user VALUES('"+hoTen1+"', '"+ngaySinh1+"', '"+user1+"', '"+password1+"', '"+password1+"', '"+email1+"', '"+soDT1+"')"); 
if(val==1) System.out.print("Successfully inserted value"); 
conn.close(); 
} 
catch (Exception e) { e.printStackTrace(); 
} 
} 
}