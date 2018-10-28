/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

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
    private Date create_at;
    private Date update_at;

    public Employee(int id, String name, String email, String password, int sex, int id_card_number, int province_id, String address, Date create_at, Date update_at) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.id_card_number = id_card_number;
        this.province_id = province_id;
        this.address = address;
        this.create_at = create_at;
        this.update_at = update_at;
    }

    public Employee() {
    }



    
    
    

    
    public void setID(int id)
    {
        this.id = id;
    }
    public int getID(){return this.id;}
    public void setName(String name)
    {
        this.name = name;
    }
    public String getName(){return this.name;}
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getEmail(){return this.email;}
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getPassword(){return this.password;}
    public void setSex(int sex)
    {
        this.sex = sex;
    }
    public int getSex(){return this.sex;}
    public void setID_card_number(int id_card_number)
    {
        this.id_card_number = id_card_number;
    }
    public int getID_card_number(){return this.id_card_number;}
    public void setProvince_id(int province_id)
    {
        this.province_id = province_id;
    }
    public int getProvince_id(){return this.province_id;}
    public void setAddress(String address)
    {
        this.address = address;
    }
    public String getAddress(){return this.address;}
    public void setCreate_at(Date create_at)
    {
        this.create_at = create_at;
    }
    public Date getCreate_at() { return this.create_at;}
    public void setUpdate_at(Date update_at)
    {
        this.update_at = update_at;
    }
    public Date getUpdate_at() { return this.update_at;}       
}
