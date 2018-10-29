/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.javafx.animation.TickCalculation;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author MyPC
 */
public class Employee {
    private int id;
    private String name;
    private String email;
    private String password;
    private int sex;
    private int id_card_number;
    private int province_id;
    private String address;
//    private Timestamp create_at;
//    private Timestamp update_at;

    public Employee(int id, String name, String email, String password, int sex, int id_card_number, int province_id, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.id_card_number = id_card_number;
        this.province_id = province_id;
        this.address = address;
//        this.create_at = create_at;
//        this.update_at = update_at;
    }

    Employee() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getSex() {
        return sex;
    }

    public int getId_card_number() {
        return id_card_number;
    }

    public int getProvince_id() {
        return province_id;
    }

    public String getAddress() {
        return address;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setId_card_number(int id_card_number) {
        this.id_card_number = id_card_number;
    }

    public void setProvince_id(int province_id) {
        this.province_id = province_id;
    }

    public void setAddress(String address) {
        this.address = address;
    }
public  void print(){
            System.out.println(this.id);
            System.out.println(this.name);
            System.out.println(this.email);
            System.out.println(this.sex);
            System.out.println(this.password);
            System.out.println(this.id_card_number);
         
             
}

    
}
