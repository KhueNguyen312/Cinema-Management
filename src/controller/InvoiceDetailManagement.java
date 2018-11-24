/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import model.InvoiceDetail;
import model.InvoiceDetailDAO;
import model.Invoices;
import model.Product;
import model.ProductDAO;

/**
 *
 * @author MyPC
 */
public class InvoiceDetailManagement implements Initializable{

    @FXML
    private JFXTextField tfID;
    @FXML
    private JFXTextField tfInvoiceID;
    @FXML
    private JFXButton btnNew;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXTextField tfPrice;
    @FXML
    private JFXTextField tfProduct;
    @FXML
    private JFXTextField tfQuality;
    @FXML
    private TableColumn<InvoiceDetail, Integer> colSerial;
    @FXML
    private TableColumn<InvoiceDetail, Product> colProduct;
    @FXML
    private TableColumn<InvoiceDetail, Integer> colPrice;
    @FXML
    private TableColumn<InvoiceDetail, Integer> colQuality;
    private int invoiceID;
    private InvoiceDetailDAO invoiceDetailDAO;
    private ProductDAO productDAO;
    ObservableList<Product> listProduct = FXCollections.observableArrayList();
    ObservableList<InvoiceDetail> listInvoiceDetails = FXCollections.observableArrayList();
    public  int InvoiceId;
    @FXML
    private TableView<InvoiceDetail> tbInvoiceDetail;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
        
        
    }
    public void setInvoiceID(int id){
        invoiceID  = id;
    }
        
    public void initData(int invoice_id)
    {
        invoiceDetailDAO = new InvoiceDetailDAO();
        productDAO = new ProductDAO();
        listInvoiceDetails = invoiceDetailDAO.getlistDetail(invoice_id);
        String sql = "select * from products";
        listProduct = (ObservableList<Product>) productDAO.getListProduct(sql);
        setCellValueFactory();
        tbInvoiceDetail.setItems(listInvoiceDetails);
        MovieManagementController.autoFitTable(tbInvoiceDetail);
        InvoiceId = invoice_id;
    }
    void setCellValueFactory(){
        colSerial.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, Integer>("id"));
        colPrice.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, Integer>("price"));
        colQuality.setCellValueFactory(new PropertyValueFactory<InvoiceDetail, Integer>("qty"));
      
        colProduct.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InvoiceDetail, Product>, ObservableValue<Product>>() {
            @Override
            public ObservableValue<Product> call(TableColumn.CellDataFeatures<InvoiceDetail, Product> param) {
           InvoiceDetail invoiceDetail = param.getValue();
           int ProductId = invoiceDetail.getProductId();
          Product product = Product.getbyId(ProductId, listProduct);
          return new SimpleObjectProperty<>(product);
            }
        });
        
    }
    @FXML
    private void BtnNewClick(ActionEvent event) {
        tfID.setText(null);
        tfID.setDisable(true);
        tfInvoiceID.setDisable(true);
        tfProduct.setText(null);
        tfQuality.setText(null);
        tfPrice.setText(null);
        
    }

    @FXML
    private void btnSaveClick(ActionEvent event) {
        InvoiceDetail detail = new InvoiceDetail();
        int id = 0;
        
        detail.setInvoiceId(InvoiceId);
        detail.setProductId(Integer.parseInt(tfProduct.getText()));
        detail.setQty(Integer.parseInt(tfQuality.getText()));
        detail.setPrice(Integer.parseInt(tfPrice.getText()));
        if (tfID.getText() == null) {
            id = listInvoiceDetails.size() + 1;
            detail.setId(id);
            invoiceDetailDAO.AddInvoiceDetail(detail);
            listInvoiceDetails.add(detail);
        } else {
            id = Integer.parseInt(tfID.getText());
            detail.setId(id);
            invoiceDetailDAO.UpdateInvoiceDetail(detail);
            for (InvoiceDetail d : listInvoiceDetails) {
                id = d.getId();
                d = detail;
            }
        }
          
        invoiceDetailDAO = new InvoiceDetailDAO();
        listInvoiceDetails = invoiceDetailDAO.getlistDetail(InvoiceId);
        setCellValueFactory();
        tbInvoiceDetail.setItems(listInvoiceDetails);
    }

    @FXML
    private void btnDeleteClick(ActionEvent event) {
        InvoiceDetail detail = new InvoiceDetail();
        detail = tbInvoiceDetail.getSelectionModel().getSelectedItem();
        invoiceDetailDAO.DeleteInvoiceDetail(detail);
         invoiceDetailDAO = new InvoiceDetailDAO();
        listInvoiceDetails = invoiceDetailDAO.getlistDetail(InvoiceId);
        setCellValueFactory();
        tbInvoiceDetail.setItems(listInvoiceDetails);
    }

    @FXML
    private void TableClick(MouseEvent e) {
         if(MouseButton.PRIMARY == e.getButton() && e.getClickCount() == 1){
             InvoiceDetail detail = tbInvoiceDetail.getSelectionModel().getSelectedItem();
             tfID.setText(String.valueOf(detail.getId()));
             tfInvoiceID.setText(String.valueOf(detail.getInvoiceId()));
             tfInvoiceID.setDisable(true);
             tfProduct.setText(String.valueOf(detail.getProductId()));
             tfQuality.setText(String.valueOf(detail.getQty()));
             tfPrice.setText(String.valueOf(detail.getPrice()));
             
         }
    }
public  AnchorPane mainPane;
    @FXML
    private void btnUndoInvoice(MouseEvent event) {
        AnchorPane tmp = MainViewController.getInstance().rootPanel;
        tmp.getChildren().remove(1, tmp.getChildren().size());
        if(tmp.getChildren().get(0) == null)
            MainViewController.getInstance().createPage(mainPane, "/view/Layout/ManagementView.fxml");
        else
            tmp.getChildren().get(0).toFront();
    }
    
    
}
