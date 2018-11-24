/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import static controller.MovieManagementController.autoFitTable;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.Employee;
import model.EmployeeDAO;
import model.InvoiceDetail;
import model.Invoices;
import model.InvoicesDAO;

/**
 *
 * @author MyPC
 */
public class InvoiceManagement implements Initializable {

    @FXML
    private JFXTextField tfID;
    @FXML
    private JFXTextField tfTotal;
    @FXML
    private JFXComboBox<Integer> cmbStatus;
    @FXML
    private JFXButton btnNew;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXTextField tfEmployee;
    @FXML
    private JFXDatePicker DatePickerBuyDate;
    @FXML
    private TableColumn<Invoices, Integer> colSerial;
    @FXML
    private TableColumn<Invoices, Integer> colTotal;
    @FXML
    private TableColumn<Invoices, Date> colBuyDate;
    @FXML
    private TableColumn<Invoices, Employee> colEmployee;
    @FXML
    private TableColumn<Invoices, Integer> colStatus;
    @FXML
    private TableView<Invoices> tbInvoice;
    private InvoicesDAO invoices;
    private EmployeeDAO employeeDAO = new EmployeeDAO();
    ObservableList<Employee> listEmployees = employeeDAO.getEmployee();
    ObservableList<Invoices> list = FXCollections.observableArrayList();
    ObservableList<Integer>  listStatus = FXCollections.observableArrayList();
    @FXML
    private AnchorPane paneInvoice;
    @FXML
    private JFXButton btnDetail;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        invoices = new InvoicesDAO();
        list = invoices.getListinvoice();
        setCellValueFactory();
        tbInvoice.setItems(list);
        loadCombobox(listStatus);
        autoFitTable(tbInvoice);
       
                
        
    }
    void loadCombobox(ObservableList<Integer> listStatus){
         listStatus.add(1);
        listStatus.add(2);
        listStatus.add(3);
        listStatus.add(4);
        cmbStatus.setItems(listStatus);
        
        
    }
    void setCellValueFactory() {
        colSerial.setCellValueFactory(new PropertyValueFactory<Invoices, Integer>("id"));
        colTotal.setCellValueFactory(new PropertyValueFactory<Invoices, Integer>("total"));
        colBuyDate.setCellValueFactory(new PropertyValueFactory<Invoices, Date>("buy_date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<Invoices, Integer>("status"));
        colEmployee.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Invoices, Employee>, ObservableValue<Employee>>() {
            @Override
            public ObservableValue<Employee> call(TableColumn.CellDataFeatures<Invoices, Employee> param) {
                Invoices in = param.getValue();
                int InvoiceId = in.getEmployee_id();
                Employee e = Employee.getById(InvoiceId, listEmployees);
                return new SimpleObjectProperty<Employee>(e);
            }
        });
    }

    @FXML
    private void BtnNewClick(ActionEvent event) {
        tfID.setText(null);
        tfID.setDisable(true);
        tfTotal.setText(null);
        tfEmployee.setText(null);
        cmbStatus.setValue(1);
        DatePickerBuyDate.setValue(LocalDate.now());
    }

    @FXML
    private void btnSaveClick(ActionEvent event) {
        Invoices invoice = new Invoices();
        int id = 0;
        invoice.setTotal(Integer.valueOf(tfTotal.getText()));
        invoice.setEmployee_id(Integer.valueOf(tfEmployee.getText()));
        invoice.setStatus(cmbStatus.getValue());
        invoice.setBuy_date(Date.valueOf(DatePickerBuyDate.getValue()));
        if (tfID.getText() == null) {
            id = list.size() + 1;
            invoice.setId(id);
            invoices.AddInvoice(invoice);
            list.add(invoice);
        } else {
            id = Integer.parseInt(tfID.getText());
            invoice.setId(id);
            invoices.UpdateInvoice(invoice);
            for (Invoices movie : list) {
                id = movie.getId();
                movie = invoice;
            }
        }
        invoices = new InvoicesDAO();
        list = invoices.getListinvoice();
        setCellValueFactory();
        tbInvoice.setItems(list);
        autoFitTable(tbInvoice);
    }

    @FXML
    private void btnDeleteClick(ActionEvent event) {
        Invoices invoice = new Invoices();
        invoice = tbInvoice.getSelectionModel().getSelectedItem();
        invoices.DeleteInvoice(invoice);
        invoices = new InvoicesDAO();
        list = invoices.getListinvoice();
        setCellValueFactory();
        tbInvoice.setItems(list);
        autoFitTable(tbInvoice);
        
    }

    @FXML
    private void TableClick(MouseEvent e) throws IOException {
        if(MouseButton.PRIMARY == e.getButton() && e.getClickCount() == 1){
            
            Invoices invoice = tbInvoice.getSelectionModel().getSelectedItem();
            tfID.setText(String.valueOf(invoice.getId()));
            tfTotal.setText(String.valueOf(invoice.getTotal()));
            tfEmployee.setText(String.valueOf(invoice.getEmployee_id()));
          cmbStatus.setValue(invoice.getStatus());
           DatePickerBuyDate.setValue(LocalDate.parse(String.valueOf(invoice.getBuy_date())));
           
        }
        
            
    }
public void showInvoiceDetail(Invoices invoice) throws IOException{
       
  
    AnchorPane InvoiceDetail = new AnchorPane();
//    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Management/InvoiceDetailManagement.fxml"));
//    InvoiceDetail = loader.load();
    FXMLLoader fxmlLoader = MainViewController.getInstance().createPage(InvoiceDetail, "/Management/InvoiceDetailManagement.fxml");
    fxmlLoader.<InvoiceDetailManagement>getController().initData(invoice.getId());
    paneInvoice.getChildren().add(InvoiceDetail);
    GeneralFuntion.FitChildContent(InvoiceDetail);
}

    @FXML
    private void BtnDetailClick(ActionEvent event) throws IOException {
            Invoices invoice = tbInvoice.getSelectionModel().getSelectedItem();
            showInvoiceDetail(invoice);
    }
    
   
    
 
    
    
}

