/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Controller.GiaoTiep;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author thao
 */
public class KeyDuocCap implements Serializable, GiaoTiep {
    public String user;
    public ArrayList<Integer> al;

    public KeyDuocCap() {
    }

    public KeyDuocCap(String user, ArrayList<Integer> al) {
        this.user = user;
        this.al = al;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public ArrayList<Integer> getAl() {
        return al;
    }

    public void setAl(ArrayList<Integer> al) {
        this.al = al;
    }
     @Override
    public Object[] toObjects() {
     return new Object[] {
        this.user, this.al
    };
    }
    
}
