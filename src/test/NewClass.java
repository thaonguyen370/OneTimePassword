/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author thao
 */
public class NewClass {
    public int a=5;
    public void xx(){
        a=6;
        System.out.println("test.NewClass.xx()"+a);
    }
    public void yy(){
        a+=2;
        System.out.println("test.NewClass.yy()"+a);
    }
    public static void main(String[] args) {
        NewClass xx=new NewClass();
        xx.xx();
        xx.yy();
        
    }
}
