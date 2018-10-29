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
            e.setId(rs.getInt("id"));
            e.setName(rs.getString("name"));
            e.setEmail(rs.getString("email"));
            e.setPassword(rs.getString("email"));
            e.setSex(rs.getInt("sex"));
            e.setId_card_number(rs.getInt("id_card_number"));
            e.setProvince_id(rs.getInt("province_id"));
            e.setAddress(rs.getString("address"));
            

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Can't load database!");
        }
        return e;
    }
    
    public List<Employee> getEmployee() {
        String sql = "select * from employees ";
        List<Employee> list = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Employee e = createEmployee(rs);
                list.add(e);
                e.print();
            }
            
            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.out.println("Can't load database!");
        }
        
        return list;
    }

}
