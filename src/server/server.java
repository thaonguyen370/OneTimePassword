/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import Controller.GiaoTiep;
import Controller.IOFile;
import doiTuong.key;
import doiTuong.user;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.guiMail;
import model.kiemTraDangNhap;
import model.kiemTraDangKi;
import view_client.QLDSClient;
import model.DoiTuongClient;

/**
 *
 * @author thao
 */
public class server {

    public static ArrayList<GiaoTiep> alx;
    private IOFile iOFile;
    public static QLDSClient QL;
    public static DoiTuongClient doiTuongClient;
    public static ArrayList<DoiTuongClient> DSDoiTuongClient;
    public static boolean flag;
    public static boolean flag_thoiGian;
    public static ArrayList<Socket> al_s;
    public static ServerSocket ss;

    public static boolean KiemTraUserDangSuDung(String user) {
        for (DoiTuongClient x : DSDoiTuongClient) {
            if (x.getUser().equals(user)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        alx = new ArrayList<>();
        QL = new QLDSClient();

        QL.setVisible(true);

        DSDoiTuongClient = new ArrayList();

        System.out.println("\t\tserver san sang......");
        try {
            ss = new ServerSocket(8888);
            //  double t1 = System.currentTimeMillis();

            flag = false;
            //      ArrayList<Socket> DSClientDoi = new ArrayList<Socket>();
            al_s = new ArrayList<Socket>();//danh sach cac client
            int l = 0;

            while (true) {
                Socket client = ss.accept();// chờ 1 kết nối tới server
                doiTuongClient = new DoiTuongClient();//tạo đối tượng để ghi vào file
                int d = 0;
                for (Socket x : al_s) {//kiem tra xem client da có chưa, nếu chưa có thì add vào mạng
                    if (x.getPort() == client.getPort()) {
                        d = 1;
                        break;
                    }

                }
                if (d == 0) {
                    al_s.add(client);

                }

//              
                new Thread(new Runnable() {//bắt đầu 1 tiến trình phục vụ client
                    @Override
                    public void run() {

                        try {
                            PrintStream ps = new PrintStream(client.getOutputStream());//tạo luồng gửi
                            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));//tạo luồng nhận
                            while (true) {
                                int flagz = 0;//cờ phục vụ cho việc add đối tượng để ghi vào file
                                if (client.isClosed()) {//kiểm tra xem có còn kết nối không, nếu không thì dừng tiến trình
                                    break;
                                }
//                            
                                String m = br.readLine().toString().trim();//nhận gói đầu tiên gói đầu tiên để biết thuôc trường hợp đăng kí hay đăng nhập
                                String m1 = m.substring(1);//m1 chính là tách gói đầu tiên,nếu là đăng nhập tách ra là tên user, còn đăng kí tách ra là họ tên
                                String m2 = m.substring(0, 1);// m2 dùng để kiểm tra là số 0 hay là số 1, nếu số 0 thì nó là đăng nhâp, nếu 1 là đăng kí

                                int dd;//mục đích bắt các trường hợp ngoại lệ
                                try {
                                    dd = Integer.parseInt(m2);//chuyển từ dạng từ chuổi sang số
                                } catch (Exception e) {
                                    dd = 88888;//trường hợp xảy ra lỗi thì nó mặc định là 8888
                                }

                                if (dd == 0) {//đây là trường hợp đăng nhập                                 

                                    String pass = br.readLine().toString().trim().substring(1);//nhận password
                                    kiemTraDangNhap x = new kiemTraDangNhap(m1, pass);//m1 la user
                                    if (x.kiemTraThongTin()) {//hàm kiểm tra trong bảng data base(user) xem nó đã có chưa
                                        if (flagz == 0) {// cái này phục vụ ghi file lần đầu client kết nối tới
                                            if (KiemTraUserDangSuDung(m1)) {
                                                System.err.println("cảnh báo 1 user đã đăng nhập 2 nơi!");
                                                ps.println("user này đang được sử dụng");
                                                break;
                                            }
                                            doiTuongClient.setIp(client.getInetAddress().toString());
                                            doiTuongClient.setPort(String.valueOf(client.getPort()));
                                            doiTuongClient.setUser(m1);
                                            doiTuongClient.setTrangThai("Đang hoạt động");
                                            DSDoiTuongClient.add(doiTuongClient);
                                            alx.add(doiTuongClient);//phục vụ cho việc 1 user chỉ có 1 client được đăng nhập tại một thời điểm
                                            QL.them(doiTuongClient);//thêm vào để hiện thị lên bảng
                                            flagz = 1;//gắn lại để cho lần sau ko ghi lại nữa

                                        }

                                        ps.println("Thanh cong");//gửi 1 thông báo về client
                                        while (true) {//chạy vòng lặp để tiếp yêu cầu 
                                            String tinHieu = br.readLine().toString().trim();
                                            if (tinHieu.equals("dang xuat")) {
                                                try {
                                                     DSDoiTuongClient.remove(doiTuongClient);
                                                    QL.xoaClient(client);
                                                  
                                                } catch (Exception e) {
                                                    System.out.println("canh bao");
                                                }
                                                break;
                                            }
                                            if (tinHieu.equals("Bat dau")) {//nhân đc yêu cầu lấy mã từ client thì nó sẽ xử lí
                                                ps.println("1 phut");//server sẽ gửi về thời gian 1 phút
                                                key key = new key();
                                                int key_moi = key.sinh_key_moi();//tạo ra 1 cái key mới
//                                            //tao doi tuong gui mail
                                                guiMail zzz = new guiMail(m1, pass);
                                                if (zzz.gui(String.valueOf(key_moi))) {//thực hiện gửi mail về client
//                                                // cho nhan
                                                    BufferedReader brcc = new BufferedReader(new InputStreamReader(client.getInputStream()));
                                                    //    String keyxxx = brcc.readLine().toString().trim();
                                                    flag_thoiGian = false;
                                                    new Thread(new Runnable() {// tiến trình chạy để kiểm tra thời gian tồn tại của mã, nếu qua thời gian thì sẽ gắn lại cái cờ quá thời gian, đồng thời break tiến trình đang đợi mã, gửi thông báo về cho client
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
                                                    while (flag_thoiGian == false) {//vòng lặp luôn đợi mã được gửi từ client để so sánh, nếu ko đúng thì nó sẽ gửi thông báo về client và yêu cầu nhập lại, nếu quá thời gian cũng gửi thông báo về client, và dừng vòng lặp

                                                        String abc = brcc.readLine().toString().trim();//nhận chuỗi dạng mã số(key)
                                                        if (abc.equals("dang xuat")) {//nếu đang nhân mã mà có yêu cầu ko nhận mã nữa thì nó sẽ thoát vong nhận
                                                            ps.println("da dang xuat");
                                                            flag_thoiGian = true;
                                                            try {
                                                                DSDoiTuongClient.remove(doiTuongClient);
                                                                QL.xoaClient(client);

                                                            } catch (Exception e) {
                                                                System.out.println("canh bao");
                                                            }
                                                            break;
                                                        }

                                                        System.out.println("tien hanh doi");

                                                        int ma;
                                                        try {
                                                            ma = Integer.parseInt(abc);// chuyển dạng mã chuổi sang dạng mã số để so sánh
                                                        } catch (Exception e) {
                                                            ma = 1;
                                                            flag_thoiGian = true;
                                                        }
                                                        if (ma == key_moi) {//so sánh key_moi là key được sinh ra ở trên(dòng 136)
                                                            ps.println("Key hop le");//nếu họp lệ thì nó gửi thông báo về client
                                                            flag_thoiGian = true;//và gắn lại cờ để thoát vòng lặp kiểm tra thời gian và vòng nhận mã
                                                             try {
                                                                DSDoiTuongClient.remove(doiTuongClient);
                                                                QL.xoaClient(client);

                                                            } catch (Exception e) {
                                                                System.out.println("da dang xuat");
                                                            }
                                                        } else {
                                                            ps.println("key không đúng mời nhập lại");
                                                        };
                                                    }
                                                    if (flag_thoiGian == true) {//thoát vòng
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
                                } else if (dd == 1) {// trường hợp =1 thì đắng kí
                                    String ngaySinh = br.readLine().toString().trim().substring(1);
                                    String user = br.readLine().toString().trim().substring(1);
                                    String password = br.readLine().toString().trim().substring(1);
                                    String lai_password = br.readLine().toString().trim().substring(1);
                                    String email = br.readLine().toString().trim().substring(1);
                                    int soDT = Integer.parseInt(br.readLine().toString().trim().substring(1));
                                    user user1 = new user(m1, ngaySinh, user, password, lai_password, email, soDT);
                                    kiemTraDangKi zz = new kiemTraDangKi(user1);
                                    if (zz.dangKi()) {
                                        if (flagz == 0) {
                                            doiTuongClient.setIp(client.getInetAddress().toString());
                                            doiTuongClient.setPort(String.valueOf(client.getPort()));
                                            doiTuongClient.setUser(m1);
                                            doiTuongClient.setTrangThai("Đang hoạt động");
                                            DSDoiTuongClient.add(doiTuongClient);
                                            alx.add(doiTuongClient);
                                            QL.them(doiTuongClient);
                                            flagz = 1;
                                        }
                                        ps.println("Thanh cong");
                                        while (true) {

                                            String tinHieu = br.readLine().toString().trim();
                                            if (tinHieu.equals("dang xuat")) {
                                                try {
                                                     DSDoiTuongClient.remove(doiTuongClient);
                                                    QL.xoaClient(client);
                                                  
                                                } catch (Exception e) {
                                                    System.out.println("canh bao");
                                                }
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
                                                            try {
                                                     DSDoiTuongClient.remove(doiTuongClient);
                                                    QL.xoaClient(client);
                                                  
                                                } catch (Exception e) {
                                                    System.out.println("canh bao");
                                                }
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
                                                            try {
                                                     DSDoiTuongClient.remove(doiTuongClient);
                                                    QL.xoaClient(client);
                                                  
                                                } catch (Exception e) {
                                                    System.out.println("canh bao");
                                                }
                                                        } else {
                                                            ps.println("key không đúng mời nhập lại");
                                                        };
                                                    }
                                                } else {
                                                    System.out.println("lỗi server ko gửi được mail, xin kiểm tra lại đường truyền");
                                                    break;
                                                }

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
                            try {
                                QL.xoaClient(client);
                            } catch (Exception e) {
                                System.out.println("canh bao");
                            }

                            al_s.remove(client);
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
