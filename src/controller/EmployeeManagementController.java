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
import javafx.geometry.Insets;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author MyPC
 */
public class EmployeeManagementController implements Initializable {

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
    private AnchorPane rootPanel;
    @FXML
    private AnchorPane rootpanel;

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
    List<Employee> listEmployees;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colID.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("colID"));
        colName.setCellValueFactory(new PropertyValueFactory<Employee, String>("colName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Employee, String>("colEmail"));
        colPass.setCellValueFactory(new PropertyValueFactory<Employee, String>("colPass"));
        colSex.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("colSex"));
        colCardNumber.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("colCardNumber"));
        colProvince.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("colProvince"));
        colAddress.setCellValueFactory(new PropertyValueFactory<Employee, String>("colAddress"));
//        colCreateAt.setCellValueFactory(new PropertyValueFactory<Employee, Timestamp>("colCreateAt"));
//        colUpdateAt.setCellValueFactory(new PropertyValueFactory<Employee, Timestamp>("colUpdateAt"));
        Employees = new EmployeeDAO();
        listEmployees = Employees.getEmployee();
        FlowPane container = new FlowPane();
        
        ObservableList<Employee> list = FXCollections.observableArrayList(listEmployees);
        tbEmployee.setItems(list);
        tbEmployee.getColumns().addAll(colID, colName, colEmail, colPass, colSex, colCardNumber, colProvince, colAddress);
        
        
        

//        rootpanel.setPadding(new Insets(5));
//        rootpanel.getChildren().add(tbEmployee);
//        
    }

}
