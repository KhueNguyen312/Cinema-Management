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
public enum gender {
    FEMALE(1, "Female"), MALE(2, "Male");
 
   private int code;
   private String text;
 
   private gender(int  code, String text) {
       this.code = code;
       this.text = text;
   }
 
   public int  getCode() {
       return code;
   }
 
   public String getText() {
       return text;
   }
 
   public static gender getByCode(int  genderCode) {
       for (gender g : gender.values()) {
           if (g.code == genderCode) {
               return g;
           }
       }
       return null;
   }
 
   @Override
   public String toString() {
       return this.text;
   }
}
