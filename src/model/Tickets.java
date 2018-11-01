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
public class Tickets {
    private int id;
    private int plan_cinema_id;
    private String seat;
    private int invoice_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlan_cinema_id() {
        return plan_cinema_id;
    }

    public void setPlan_cinema_id(int plan_cinema_id) {
        this.plan_cinema_id = plan_cinema_id;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public Tickets(int plan_cinema_id, String seat, int invoice_id) {
        this.plan_cinema_id = plan_cinema_id;
        this.seat = seat;
        this.invoice_id = invoice_id;
    }
    public Tickets(){
        
    }
}
