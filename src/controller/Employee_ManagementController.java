/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Employee;
import model.EmployeeDAO;
import model.Province;
import model.gender;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author MyPC
 */
public class Employee_ManagementController implements Initializable {

    @FXML
    private JFXTextField tfID;
    @FXML
    private JFXTextField tfName;
    @FXML
    private JFXTextField tfEmail;
    @FXML
    private JFXPasswordField tfPass;

    @FXML
    private JFXTextField tfIDCard;
    @FXML
    private JFXComboBox<Province> cmbProvince;
    @FXML
    private JFXTextField tfAddress;
    @FXML
    private TableView<Employee> tbEmployee;
    @FXML
    private CheckBox CboxGender;
    @FXML
    private TableColumn<Employee, Integer> colSerial;
    @FXML
    private TableColumn<Employee, String> colName;
    @FXML
    private TableColumn<Employee, String> colEmail;
    @FXML
    private TableColumn<Employee, gender> colGender;
    @FXML
    private TableColumn<Employee, Integer> colCardNumber;
    @FXML
    private TableColumn<Employee, String> colProvince_id;
    @FXML
    private TableColumn<Employee, String> colAddress;
    @FXML
    private JFXButton btnNew;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnDelete;
    /**
     * Initializes the controller class.
     */
    private EmployeeDAO Employees;
    ObservableList<Employee> listEmployees = FXCollections.observableArrayList();
    ObservableList<gender> genderList = FXCollections.observableArrayList(gender.values());
    List<Employee> newEmployees = new ArrayList<Employee>();
    ObservableList<Province> listProvince = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Employees = new EmployeeDAO();
        listEmployees = Employees.getEmployee();
        SetValueFactory();
        tbEmployee.setItems(listEmployees);
        tbEmployee.setEditable(true);
        loadCombobox(Employees);

    }

    void SetValueFactory() {
        colSerial.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
        colCardNumber.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id_card_number"));
        colProvince_id.setCellValueFactory(new PropertyValueFactory<Employee, String>("province_id"));
        colAddress.setCellValueFactory(new PropertyValueFactory<Employee, String>("address"));
        colGender.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Employee, gender>, ObservableValue<gender>>() {
            @Override
            public ObservableValue<gender> call(TableColumn.CellDataFeatures<Employee, gender> param) {
                Employee employee = param.getValue();
                int genderCode = employee.getGender();
                gender gen = gender.getByCode(genderCode);
                return new SimpleObjectProperty<gender>(gen);
            }
        });
    }

    void loadCombobox(EmployeeDAO DAO) {
        listProvince = DAO.getProvinces();
        cmbProvince.setItems(listProvince);

    }

    @FXML
    private void TableClick(MouseEvent e) {
        if (MouseButton.PRIMARY == e.getButton() && e.getClickCount() == 1) {
            Employee employee = tbEmployee.getSelectionModel().getSelectedItem();
            tfID.setText(String.valueOf(employee.getId()));
            tfName.setText(employee.getName());
            tfEmail.setText(employee.getEmail());
            tfIDCard.setText(String.valueOf(employee.getId_card_number()));
            tfAddress.setText(employee.getAddress());
            tfPass.setDisable(true);
            if (employee.getGender() == 1) {
                CboxGender.setSelected(true);
            } else {
                CboxGender.setSelected(false);
            }
            String temp = employee.getProvince_id();
            for (Province p : listProvince) {
                if (p.getNameProvinceString().equals(temp)) {
                    cmbProvince.setValue(p);
                }
            }
            cmbProvince.valueProperty();
            int row = tbEmployee.getSelectionModel().getSelectedIndex();

        }
    }

    @FXML
    private void BtnNewClick(ActionEvent event) {
        tfID.setText(null);
        tfID.setDisable(true);
        tfName.setText("");
        tfEmail.setText("");
        tfPass.setText("");
        tfIDCard.setText("");
        tfAddress.setText("");
        CboxGender.setVisible(true);
        CboxGender.setSelected(false);
        tfPass.setDisable(false);
    }

    @FXML
    private void btnSaveClick(ActionEvent event) {
        Employee e = new Employee();
        int id;
        int p = 0;
        e.setName(tfName.getText());
        e.setEmail(tfEmail.getText());
        e.setPassword(tfPass.getText());
        if (CboxGender.isSelected() == true) {
            e.setGender(1);
        } else {
            e.setGender(2);
        }
        e.setId_card_number(Integer.parseInt(tfIDCard.getText()));
        e.setAddress(tfAddress.getText());
        // province
        for (Province province : listProvince) {
            if (province == cmbProvince.getValue()) {
                p = province.getIdProvince();
            }
        }
        if (tfID.getText() == null) {
            id = listEmployees.size() + 1;
            e.setId(id);
            Employees.AddEmployee(e, p);
            listEmployees.add(e);
        } else {
            id = Integer.parseInt(tfID.getText());
            e.setId(id);
            Employees.UpdateEmployee(e, p);
            for (Employee employee : listEmployees) {
                id = employee.getId();
                employee = e;
            }
        }
        Employees = new EmployeeDAO();
        listEmployees = Employees.getEmployee();
        SetValueFactory();
        tbEmployee.setItems(listEmployees);
        tbEmployee.setEditable(true);
  

    }
    @FXML
    private void btnDeleteClick(ActionEvent event) {
            
            Employee employee = tbEmployee.getSelectionModel().getSelectedItem();
           Employees.DeleteEmployee(employee.getId());
            int row = tbEmployee.getSelectionModel().getSelectedIndex();
              Employees = new EmployeeDAO();
        listEmployees = Employees.getEmployee();
        SetValueFactory();
        tbEmployee.setItems(listEmployees);
        tbEmployee.setEditable(true);
    }
    
}
