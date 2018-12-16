/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author stari
 */
public class EmployeeDAO {

    private Employee createEmployee(ResultSet rs) {
        Employee e = new Employee();
        try {
            e.setId(rs.getInt("id"));
            e.setName(rs.getString("name"));
            e.setEmail(rs.getString("email"));
            e.setPassword(rs.getString("password"));
            e.setGender(rs.getInt("gender"));
            e.setId_card_number(rs.getInt("id_card_number"));
            e.setAddress(rs.getString("address"));
            e.setProvince_id(rs.getString("provinceName"));

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Can't load database!");
        }
        return e;
    }

    public List<Employee> getlistEmployee(String sql) {

        List<Employee> list = new ArrayList<Employee>();
        try {
            ResultSet rs = DBUtil.dbExecute(sql);
            while (rs.next()) {
                Employee e = createEmployee(rs);
                list.add(e);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.out.println("Can't load database!");
        }
        return list;
    }

    public ObservableList<Employee> getEmployee() {
        // String sql = "SELECT employees.id, employees.name, employees.email, employees.gender, employees.id_card_number,provinces.name as province, employees.address FROM `employees` INNER JOIN `provinces` on employees.province_id = provinces.id ";
        String sql = "SELECT employees.id, employees.name, employees.email, employees.password, "
                + "employees.gender, employees.id_card_number, provinces.name as provinceName,"
                + " employees.address FROM employees INNER JOIN provinces "
                + "on employees.province_id = provinces.id";
        ObservableList<Employee> list = FXCollections.observableArrayList();
        try {

            ResultSet rs = DBUtil.dbExecute(sql);
            while (rs.next()) {
                Employee e = createEmployee(rs);
                list.add(e);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.out.println("Can't load database!");
        }

        return list;
    }
    public Employee getEmployee(int id){
        String sql = " select employees.id, employees.name, employees.email, employees.password, "
                + "employees.gender, employees.id_card_number, province_id,"
                + " employees.address from employees where id = "+ id;
         Employee employee = null;
        try {
            ResultSet rs = DBUtil.dbExecute(sql);
             employee = createEmployee(rs);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.out.println("Can't load database!");
        }
        return employee;
    }
    private Province createProvince(ResultSet rs) {
        Province p = new Province();
        try {
            p.setIdProvince(rs.getInt("id"));
            p.setNameProvinceString(rs.getString("name"));

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Can't load database!");
        }
        return p;
    }

    public ObservableList<Province> getProvinces() {
        // String sql = "SELECT employees.id, employees.name, employees.email, employees.gender, employees.id_card_number,provinces.name as province, employees.address FROM `employees` INNER JOIN `provinces` on employees.province_id = provinces.id ";
        String sql = "select id, name from provinces";

        ObservableList<Province> list = FXCollections.observableArrayList();
        try {

            ResultSet rs = DBUtil.dbExecute(sql);
            while (rs.next()) {
                Province p = createProvince(rs);
                list.add(p);
            }
            rs.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.out.println("Can't load database!");
        }

        return list;
    }

    public void AddEmployee(Employee e, int p) {
        String sql = "insert into employees(id, name, email, password, gender, id_card_number, province_id, address) "
                + "values ( " + e.getId() + ", '" + e.getName() + "', '" + e.getEmail() + "', '" + e.getPassword()
                + "', " + e.getGender() + ", " + e.getId_card_number() + ", " + p + ", '" + e.getAddress() + "')";
        //System.out.println("sql insert : " + sql);
        try {

            int stmt = DBUtil.dbExcecuteQuery(sql);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.out.println("Can't load database!");
        }

    }

    public void UpdateEmployee(Employee e, int p) {
        String sql = "update employees set  "
                + "name =  '" + e.getName() + "', email = '" + e.getEmail() + "', "
                + " gender = " + e.getGender() + ", id_card_number = " + e.getId_card_number() + ", province_id = " + p + ", address = '" + e.getAddress() + "' where id = " + e.getId();
        System.out.println("sql update : " + sql);
        int stmt = 0;
        try {

            stmt = DBUtil.dbExcecuteQuery(sql);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.out.println("Can't load database!");
        }

    }

    public void DeleteEmployee(int id) {
        String sql = "delete from employees where employees.id = " + id;
        try {
            int stmt = DBUtil.dbExcecuteQuery(sql);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.out.println("Can't load database!");
        }
    }
}
