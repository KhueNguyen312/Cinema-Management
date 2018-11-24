/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author stari
 */
public class InvoiceDetail {
    private int id;
    private int invoiceId;
    private int productId;
    private int qty;
    private int price;

    public InvoiceDetail() {
    }

    public InvoiceDetail(int invoiceId, int productId, int qty, int price) {
        this.invoiceId = invoiceId;
        this.productId = productId;
        this.qty = qty;
        this.price = price;
    }

    public InvoiceDetail(int invoiceId) {
        this.invoiceId = invoiceId;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
