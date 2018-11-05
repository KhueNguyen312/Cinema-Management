/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            e.setSex(rs.getInt("gender"));
            e.setId_card_number(rs.getInt("id_card_number"));
            e.setProvince_id(rs.getInt("province_id"));
            e.setAddress(rs.getString("address"));
            
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Can't load database!");
        }
        return e;
    }
    public List<Employee> getListEmployee(String sql){
        List<Employee> listEmployee = new ArrayList<>();
        try {
           ResultSet rs = DBUtil.dbExecute(sql);
         while (rs.next()) {
            Employee e = createEmployee(rs);
            listEmployee.add(e);
         }
      } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
      }
      return listEmployee;
    }
}
