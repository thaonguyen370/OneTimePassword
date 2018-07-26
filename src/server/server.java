/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import doiTuong.key;
import doiTuong.user;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.guiMail;
import model.kiemTraDangNhap;
import model.kiemTraDangKi;

/**
 *
 * @author thao
 */
public class server {

    public static boolean flag;
    public static boolean flag_thoiGian;

//    public static synchronized void chienThang(int k, int n, PrintStream ps) {
//        if (k == n) {
//            ps.println("1 Chien thang");
//            flag = true;
//        }
//    }
    public static void main(String[] args) {
        System.out.println("\t\tserver san sang......");
        try {
            ServerSocket ss = new ServerSocket(8888);
            //  double t1 = System.currentTimeMillis();

            flag = false;
            //      ArrayList<Socket> DSClientDoi = new ArrayList<Socket>();
            ArrayList<Socket> al = new ArrayList<Socket>();//danh sach cac client
            int l = 0;

            while (true) {
                Socket client = ss.accept();

                int d = 0;
                for (Socket x : al) {
                    if (x.getPort() == client.getPort()) {
                        d = 1;
                        break;
                    }

                }
                if (d == 0) {
                    al.add(client);
                }
//              
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//
//                        }
//                    }).start();

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            PrintStream ps = new PrintStream(client.getOutputStream());
                            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                            while (true) {
//                            
                                String m = br.readLine().toString();//xet truong hop dang nhap hay đăng kí
                                String m1 = m.substring(1);
                                String m2 = m.substring(0, 1);
                                int dd = 999;
                                try {
                                    dd = Integer.parseInt(m2);
                                } catch (Exception e) {
                                    dd = 88888;
                                }

                                if (dd == 0) {
                                    String pass = br.readLine().toString().trim().substring(1);//nhận password
                                    kiemTraDangNhap x = new kiemTraDangNhap(m1, pass);//m1 la user
                                    if (x.kiemTraThongTin()) {
                                        while (true) {

                                            ps.println("Thanh cong");
                                            String tinHieu = br.readLine().toString().trim();
                                            if (tinHieu.equals("dang xuat")) {
                                                break;
                                            }
                                            if (tinHieu.equals("Bat dau")) {
                                                ps.println("1 phut");
                                                key key = new key();
                                                int key_moi = key.sinh_key_moi();
//                                            //tao doi tuong gui mail
                                                guiMail zzz = new guiMail(m1, pass);
                                                if (zzz.gui(String.valueOf(key_moi))) {
//                                                // cho nhan
                                                    BufferedReader brcc = new BufferedReader(new InputStreamReader(client.getInputStream()));
                                                    //    String keyxxx = brcc.readLine().toString().trim();
                                                    flag_thoiGian = false;
                                                    new Thread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            int d = 0;
                                                            while (true) {

                                                                if (flag_thoiGian == true) {
                                                                    break;
                                                                }
                                                                d++;

                                                                try {
                                                                    Thread.sleep(1000);
                                                                } catch (InterruptedException ex) {
                                                                    Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
                                                                }
                                                                if (d == 60) {
                                                                    ps.println("qua thoi gian!");
                                                                    flag_thoiGian = true;
                                                                    break;
                                                                }

                                                            }

                                                        }
                                                    }).start();
                                                    while (flag_thoiGian == false) {

                                                        String abc = brcc.readLine().toString().trim();
                                                        if (abc.equals("dang xuat")) {
                                                            ps.println("da dang xuat");
                                                            flag_thoiGian = true;
                                                            break;
                                                        }

                                                        System.out.println("tien hanh doi");

                                                        int ma;
                                                        try {
                                                            ma = Integer.parseInt(abc);
                                                        } catch (Exception e) {
                                                            ma = 1;
                                                            flag_thoiGian = true;
                                                        }
                                                        if (ma == key_moi) {

                                                            PrintStream psx = new PrintStream(client.getOutputStream());
                                                            psx.println("Key hop le");
                                                            flag_thoiGian = true;
                                                        } else {
                                                            ps.println("key không đúng mời nhập lại");
                                                        };
                                                    }
                                                    if (flag_thoiGian == true) {
                                                        break;
                                                    }
                                                };

//
                                            } else {

                                                break;
                                            }

                                        }
                                    } else {
                                        ps.println("sai user hoac passsword!");
                                    }
                                } else if (dd == 1) {
                                    String ngaySinh = br.readLine().toString().trim().substring(1);
                                    String user = br.readLine().toString().trim().substring(1);
                                    String password = br.readLine().toString().trim().substring(1);
                                    String lai_password = br.readLine().toString().trim().substring(1);
                                    String email = br.readLine().toString().trim().substring(1);
                                    int soDT = Integer.parseInt(br.readLine().toString().trim().substring(1));
                                    user user1 = new user(m1, ngaySinh, user, password, lai_password, email, soDT);
                                    kiemTraDangKi zz = new kiemTraDangKi(user1);
                                    if (zz.dangKi()) {
                                        while (true) {
                                            ps.println("Thanh cong");
                                            String tinHieu = br.readLine().toString().trim();
                                            if (tinHieu.equals("dang xuat")) {
                                                break;
                                            }
                                            if (tinHieu.equals("Bat dau")) {
                                                ps.println("1 phut");
                                                key key = new key();
                                                int key_moi = key.sinh_key_moi();
//                                            //tao doi tuong gui mail
                                                guiMail zzz = new guiMail(user, password);
                                                if (zzz.gui(String.valueOf(key_moi))) {
//                                                // cho nhan
                                                    BufferedReader brcc = new BufferedReader(new InputStreamReader(client.getInputStream()));
                                                    //    String keyxxx = brcc.readLine().toString().trim();
                                                    flag_thoiGian = false;
                                                    new Thread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            int d = 0;

                                                            while (true) {

                                                                if (flag_thoiGian == true) {
                                                                    break;
                                                                }

                                                                d++;

                                                                try {
                                                                    Thread.sleep(1000);
                                                                } catch (InterruptedException ex) {
                                                                    Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
                                                                }
                                                                if (d == 60) {
                                                                    ps.println("qua thoi gian!");
                                                                    flag_thoiGian = true;
                                                                    break;
                                                                }

                                                            }

                                                        }
                                                    }).start();
                                                    while (flag_thoiGian == false) {

                                                        String abc = brcc.readLine().toString().trim();
                                                        if (abc.equals("dang xuat")) {
                                                            ps.println("da dang xuat");
                                                            flag_thoiGian = true;
                                                            break;
                                                        }

                                                        System.out.println("tien hanh doi");

                                                        int ma;
                                                        try {
                                                            ma = Integer.parseInt(abc);
                                                        } catch (Exception e) {
                                                            ma = 1;
                                                            flag_thoiGian = true;
                                                        }
                                                        if (ma == key_moi) {

                                                            PrintStream psx = new PrintStream(client.getOutputStream());
                                                            psx.println("Key hop le");
                                                            flag_thoiGian = true;
                                                        } else {
                                                            ps.println("key không đúng mời nhập lại");
                                                        };
                                                    }
                                                };

//
                                            } else {

                                                break;
                                            }

                                        }
                                    } else {
                                        ps.println("dang ki ko thanh cong!");

                                    }
//                }
                                } else {
                                    ps.println("da dang xuat");
                                    ps.println("chuc nang nay chua dc cai");
                                }

                            }

                        } catch (IOException ex) {
                            al.remove(client);
                            System.out.println(" may client da disconect");
                        }

                    }
                }).start();
            }
        } catch (IOException ex) {
            System.err.println(" Lỗi port, hãy tắt hết server và bật lại!");
        }
    }
}
