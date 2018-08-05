/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author thao
 */
public class IOFile {
    public void ghi(ArrayList list, String fileName){
        
        String fileName1="C:\\Users\\thao\\Documents\\netbean\\OTP\\src\\Data\\"+fileName;
        try(FileOutputStream fos=new FileOutputStream(new File(fileName1))) {
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(list);
        } catch (Exception e) {
        }
    }
    public ArrayList doc(String fileName){
             String fileName1="C:\\Users\\thao\\Documents\\netbean\\OTP\\src\\Data\\"+fileName;
   
        ArrayList<GiaoTiep> list=new ArrayList();
        try (FileInputStream fis=new FileInputStream(new File(fileName1))){
            ObjectInputStream ois=new ObjectInputStream(fis);
            list=(ArrayList<GiaoTiep>) ois.readObject();
        } catch (Exception e) {
        }
    return list;
}

}
