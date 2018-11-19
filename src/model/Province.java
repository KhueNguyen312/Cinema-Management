/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author MyPC
 */
public class Province {
     private int IdProvince;
    private String NameProvinceString;
   
    
    private Province(int IdProvince, String NameProvinceString) {
        this.IdProvince = IdProvince;
        this.NameProvinceString = NameProvinceString;
    }

    Province() {

    }
    public int getIdProvince() {
        return IdProvince;
    }

    public String getNameProvinceString() {
        return NameProvinceString;
    }

    public void setIdProvince(int IdProvince) {
        this.IdProvince = IdProvince;
    }

    public void setNameProvinceString(String NameProvinceString) {
        this.NameProvinceString = NameProvinceString;
    }
    
   @Override
   public String toString() {
       return this.NameProvinceString;
   }
}
