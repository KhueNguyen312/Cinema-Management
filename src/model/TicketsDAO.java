/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author stari
 */
public class TicketsDAO {
    private Tickets createPlan(ResultSet rs){
        Tickets ticket = new Tickets();
        try {
            ticket.setId(rs.getInt("id"));
            ticket.setInvoice_id(rs.getInt("invoice_id"));
            ticket.setPlan_cinema_id(rs.getInt("plan_cinema_id"));
            ticket.setSeat(rs.getString("seat"));
            
        } catch (Exception e) {
        }
        return ticket;
    }
    public List<Tickets> getListTicket(String sql){
        List<Tickets> listTicket = new ArrayList<>();
        try {
           ResultSet rs = DBUtil.dbExecute(sql);
         while (rs.next()) {
            Tickets t = createPlan(rs);
            listTicket.add(t);
         }
      } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
      }
      return listTicket;
    }
    public void createTicket(Tickets ticket) throws ClassNotFoundException, SQLException{
        String sql = "INSERT INTO `tickets`(`plan_cinema_id`, `seat`, `invoice_id`) VALUES (" 
                +ticket.getPlan_cinema_id() +","+"'"+ticket.getSeat()+"'" +","+ticket.getInvoice_id()+")";
        DBUtil.dbExcecuteQuery(sql);
    }
}
