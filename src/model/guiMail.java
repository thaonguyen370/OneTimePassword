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
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author thao
 */
public class guiMail {

    public String user2;
    public String password2;

    public guiMail() {
    }

    public guiMail(String user, String password) {
        this.user2 = user;
        this.password2 = password;
    }

    public boolean gui(String maKey) {
        String emailx = "";
        //
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
                String email = res.getString("email");
                if (user1.equals(user2) && pass1.equals(password2) ) {
                    System.out.println("xxxx"+email);
                    emailx = email;
                    break;
                }
            }
             System.out.println("mail la: "+emailx);
            if (emailx.equals("")!=true) {
                //
               
                Properties props = new Properties();

                props.put("mail.smtp.host", "smtp.gmail.com");

                props.put("mail.smtp.socketFactory.port", "465");

                props.put("mail.smtp.socketFactory.class",
                        "javax.net.ssl.SSLSocketFactory");
                props.put(
                        "mail.smtp.auth", "true");
                props.put(
                        "mail.smtp.port", "465");

                Session session = Session.getDefaultInstance(props,
                        new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {

                        return new PasswordAuthentication("nguyenvanthao95@gmail.com", "01689058370");

                    }
                }
                );

                try {

                    Message message = new MimeMessage(session);

                    message.setFrom(new InternetAddress("nguyenvanthao8370@gmail.com"));

                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse("nguyenvanthao95@gmail.com")
                    );

                    message.setSubject("xac nhan OTP");

                    message.setText("OTP," + "\n\n key la: "+maKey);

                    Transport.send(message);

                } catch (MessagingException e) {
                    System.out.println("loi gui mail");
                    return false;

                }

            }else{
                return false;
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        //
        return true;
    }
}
