/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author thao
 */
public class soBaoHiemCanada {
    
         public static boolean kimTraKeyHopLe(int keyKT) {
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
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        int d=0;
        long pos, t;
        long sin;
        //Random vk=new Random();
        int xxx=100000000;
        do {            
            if(kimTraKeyHopLe(xxx)){
                d++;
                System.out.println("số SIN là: "+d);
            }
            xxx++;
       //   xxx=vk.nextInt(1000000000);   
        } while (xxx<1000000000);
        System.out.println("số SIn là: "+d);
        
//        Scanner sc = new Scanner(System.in);
//        int kk = 1;
//        while (kk == 1) {
//            System.out.println("SIN (0 de thoat)");
//            sin = sc.nextLong();
//            if (sin == 0) {
//                break;
//            }
//            long sum = sin % 10;
//            sin /= 10;
//            for (pos = 0; pos < 8 && sin > 0; sin /= 10, ++pos) {
//                t = sin % 10;
//                if (pos % 2 == 1) {
//                    sum += t;
//                } else {
//                    sum += (2 * t) / 10 + (2 * t) % 10;
//                }
//            }
//            if (pos < 8 || sin > 0 || sum % 10 == 1) {
//                System.out.println("khong hop le");
//            } else {
//                System.out.println("so hop le!");
//
//            }
//        }
    }
}
