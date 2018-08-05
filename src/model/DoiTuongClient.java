/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Controller.GiaoTiep;
import java.io.Serializable;

/**
 *
 * @author thao
 */
public class DoiTuongClient implements Serializable, GiaoTiep{
private String ip;
private String port;
private String user;

private String trangThai;

    public DoiTuongClient() {
    }

    public DoiTuongClient(String ip, String port, String user, String trangThai) {
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.trangThai = trangThai;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

 

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    


    @Override
    public Object[] toObjects() {
     return new Object[] {
        this.ip, this.port, this.user, this.trangThai
    };
    }
    
}
