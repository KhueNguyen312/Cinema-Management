/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;

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
}
