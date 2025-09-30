/*
 *@ (#) Phong.java        1.0     01/05/2025
 *
 *Copyright (c) 2025 IUH. All rights reserved.
 */

package entity;

import java.util.ArrayList;
import java.util.List;

/*
 *@description:
 *@author: Hung, Nguyen Tan Hung
 *@date: 01/05/2025
 *@version: 1.0
 */
public class Phong {
    private String maPhong;
    private String tenPhong;

    public Phong(String maPhong, String tenPhong) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }


}
