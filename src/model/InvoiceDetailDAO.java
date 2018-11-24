/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author stari
 */
public class InvoiceDetailDAO {
    
    
    public void createInvoiceDetail(InvoiceDetail invoiceDetail) throws ClassNotFoundException, SQLException{
        String sql = "INSERT INTO `invoice_details`(`invoice_id`, `product_id`, `qty`, `price`) VALUES (" 
                +invoiceDetail.getInvoiceId()+","+ invoiceDetail.getProductId()+","+invoiceDetail.getQty()+","+invoiceDetail.getPrice()+ ")";
        DBUtil.dbExcecuteQuery(sql);
    }
     private InvoiceDetail createInvoiceDetails(ResultSet rs) {
        InvoiceDetail invoiceDetail = new InvoiceDetail();
        try {
            invoiceDetail.setId(rs.getInt("id"));
            invoiceDetail.setInvoiceId(rs.getInt("invoice_id"));
            invoiceDetail.setProductId(rs.getInt("product_id"));
            invoiceDetail.setPrice(rs.getInt("price"));
            invoiceDetail.setQty(rs.getInt("qty"));

        } catch (Exception e) {
        }
        return invoiceDetail;
    }
     public  ObservableList<InvoiceDetail> getlistDetail(int invoice_id)
     {
         String sql = "select * from invoice_details where invoice_id = " + invoice_id;
         ObservableList<InvoiceDetail> list = FXCollections.observableArrayList();
         try {
             ResultSet rs = DBUtil.dbExecute(sql);
             while (rs.next()) {                 
                 InvoiceDetail invoiceDetail = createInvoiceDetails(rs);
                 list.add(invoiceDetail);
             }
         } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return list;
     }
     public void AddInvoiceDetail(InvoiceDetail detail){
         String sql = "insert into invoice_details(id, invoice_id, product_id, qty, price) values (" + detail.getId()
                + "," + detail.getInvoiceId()+ ", '" + detail.getProductId() + "', " + detail.getQty() +
                 ", " + detail.getPrice() + ")";
        System.out.print(sql);
        try {
            int stmt = DBUtil.dbExcecuteQuery(sql);

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.out.println("Can't load database!");
        }
     }
     public void UpdateInvoiceDetail(InvoiceDetail detail){
         String sql = "update invoice_details set invoice_id = " + detail.getInvoiceId()+ ", product_id = '" + detail.getProductId() + "', qty = " + detail.getQty() +
                 ", price = " + detail.getPrice() + " where id = " + detail.getId();
        System.out.print(sql);
        try {
            int stmt = DBUtil.dbExcecuteQuery(sql);

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.out.println("Can't load database!");
        }
     }
     public void DeleteInvoiceDetail(InvoiceDetail detail){
         String sql = "delete from invoice_details where id = " + detail.getId();
        System.out.print(sql);
        try {
            int stmt = DBUtil.dbExcecuteQuery(sql);

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.out.println("Can't load database!");
        }
     }
             
}
