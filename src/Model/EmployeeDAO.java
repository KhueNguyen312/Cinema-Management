/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;



public class EmployeeDAO implements DAO {

    private Employee createEmployee(ResultSet rs) {
        Employee e = new Employee();
        try {
            e.setID(rs.getInt("id"));
            e.setName(rs.getString("name"));
            e.setEmail(rs.getString("email"));
            e.setPassword(rs.getString("email"));
            e.setSex(rs.getInt("sex"));
            e.setID_card_number(rs.getInt("id_card_number"));
            e.setProvince_id(rs.getInt("province_id"));
            e.setAddress(rs.getString("address"));
            e.setCreate_at(rs.getDate("create_at"));
            e.setUpdate_at(rs.getDate("update_at"));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return e;
    }
    ObservableList<Employee> oblist = FXCollections.observableArrayList();
    
    public List<Employee> getEmployee() {
        String sql = "select * from Employees order by created_at";
        List<Employee> list = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected");
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                 oblist.add(new Employee(rs.getInt("ID"), rs.getString("Name"),rs.getString("Email"),
                        rs.getString("Password"),rs.getInt("Sex"),rs.getInt("Card Number"),rs.getInt("Province"),
                        rs.getString("Address"),rs.getDate("create at"),rs.getDate("update at")));
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        
        return list;
    }

}
