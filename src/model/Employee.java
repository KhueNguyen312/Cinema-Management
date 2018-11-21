/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author stari
 */
public class Employee {

    private int id;
    private String name;
    private String email;
    private String password;
    private int gender;
    private int id_card_number;
    private String province_id;
    private String address;

    public Employee(int id, String name, String email, String password, int gender, int id_card_number, String province, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.id_card_number = id_card_number;
        this.province_id = province;
        this.address = address;
    }

    public Employee() {
    }

    public Employee(int id) {
        this.id = id;
        this.name = null;
        this.email = null;
        this.password = null;
        this.gender = 0;
        //this.id_card_number = 0;
        this.province_id = null;
        this.address = null;
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

    public int getGender() {
        return gender;
    }

    public int getId_card_number() {
        return id_card_number;
    }

    public String getProvince_id() {
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

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setId_card_number(int id_card_number) {
        this.id_card_number = id_card_number;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return this.getName();
    }
    public  static Employee getById(int id, List<Employee> list)
    {
        for(Employee e : list)
        {
            if(e.getId() == id)
                return e;
        }
        return null;
    }
}
