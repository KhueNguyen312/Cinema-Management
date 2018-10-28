/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static Model.DAO.DB_URL;
import static Model.DAO.DRIVER;
import static Model.DAO.PASS;
import static Model.DAO.USER;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import Model.Employee;
import Model.EmployeeDAO;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author MyPC
 */
public class EmployeeManagementController implements Initializable{

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<Employee> tbEmployee;
    @FXML
    private TableColumn<Employee, Integer> colID;
    @FXML
    private TableColumn<Employee, String> colName;
    @FXML
    private TableColumn<Employee, String> colEmail;
    @FXML
    private TableColumn<Employee, String> colPass;
    @FXML
    private TableColumn<Employee, Integer> colSex;
    @FXML
    private TableColumn<Employee, Integer> colCardNumber;
    @FXML
    private TableColumn<Employee, Integer> colProvince;
    @FXML
    private TableColumn<Employee, String> colAddress;
    @FXML
    private TableColumn<Employee, Date> colCreateAt;
    @FXML
    private TableColumn<Employee, Date> colUpdateAt;

    @FXML
    private void btnAddClick(ActionEvent event) {
    }

    @FXML
    private void btnEditClick(ActionEvent event) {
    }

    @FXML
    private void btnDeleteClick(ActionEvent event) {
    }
    private EmployeeDAO Employees;
   ObservableList<Employee> listEmployees = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String sql = "select * from Employees order by created_at";
        List<Employee> list = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                
                listEmployees.add(new Employee(rs.getInt("ID"), rs.getString("Name"),rs.getString("Email"),
                        rs.getString("Password"),rs.getInt("Sex"),rs.getInt("Card Number"),rs.getInt("Province"),
                        rs.getString("Address"),rs.getDate("create at"),rs.getDate("update at")));
            }
//            rs.close();
//            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
       colID.setCellFactory(new PropertyValueFactory("ID"));
       colName.setCellFactory(new PropertyValueFactory("Name"));
       colEmail.setCellFactory(new PropertyValueFactory("Email"));
       colPass.setCellFactory(new PropertyValueFactory("Password"));
      colSex.setCellFactory(new PropertyValueFactory("Sex"));
       colCardNumber.setCellFactory(new PropertyValueFactory("Card Number"));
       colProvince.setCellFactory(new PropertyValueFactory("Province"));
       colAddress.setCellFactory(new PropertyValueFactory("Address"));
      colCreateAt.setCellFactory(new PropertyValueFactory("create at"));
       colUpdateAt.setCellFactory(new PropertyValueFactory("update at"));
        
        tbEmployee.setItems(listEmployees);
        

    }
    
}
