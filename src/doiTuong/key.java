/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doiTuong;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author thao
 */
public class key {

    private int keyKiemTra;
    private int keySinhRa;

    public int getKeySinhRa() {
        return keySinhRa;
    }

    public void setKeySinhRa(int keySinhRa) {
        this.keySinhRa = keySinhRa;
    }

    public key() {
    }

    public key(int keyKiemTra) {
        this.keyKiemTra = keyKiemTra;
    }

    public int sinh_key_moi() {
        Random vk = new Random();
        long pos, t;
        long sin;
        while (true) {
            int xxx;
            do {
                xxx = vk.nextInt(1000000000);
            } while (xxx >= 1000000000 || xxx < 100000000);//dung de chon ra so 9 chu so
            sin = xxx;
            long sum = sin % 10;
            sin /= 10;
            for (pos = 0; pos < 8 && sin > 0; sin /= 10, ++pos) {
                t = sin % 10;
                if (pos % 2 == 1) {
                    sum += t;
                } else {
                    sum += (2 * t) / 10 + (2 * t) % 10;
                }
            }
            if (pos < 8 || sin > 0 || sum % 10 == 1) {
                // System.out.println("khong hop le");
            } else {
                System.out.println("so hop le!");
                System.out.println("key la: " + xxx);
                keySinhRa = xxx;
                return xxx;

            }
        }
    }

    public boolean kimTraKeyHopLe(int keyKT) {
        if (keyKT < 100000000||keyKT>1000000000) {
            return false;
        }
        long pos, t;
        long sin = keyKT;
        long sum = sin % 10;
        sin /= 10;
        for (pos = 0; pos < 8 && sin > 0; sin /= 10, ++pos) {
            t = sin % 10;
            if (pos % 2 == 1) {
                sum += t;
            } else {
                sum += (2 * t) / 10 + (2 * t) % 10;
            }
        }
        if (pos < 8 || sin > 0 || sum % 10 == 1) {
            System.out.println("khong hop le");
            return false;
        }

        return true;
    }
    public ArrayList<Integer> Sinh3Key(){
        ArrayList<Integer> al=new ArrayList<>();
        for(int i=0;i<3;i++){
            al.add(sinh_key_moi());
        }
        System.out.println("sinh key thanh cong");
        return  al;
    }
}
