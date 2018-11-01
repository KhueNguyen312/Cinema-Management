/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author stari
 */
public class Plan_CinemasDAO {
    private Plan_cinemas createPlan(ResultSet rs){
        Plan_cinemas plan = new Plan_cinemas();
        try {
            plan.setId(rs.getInt("id"));
            plan.setMovie_id(rs.getInt("movie_id"));
            plan.setPrice_ticket(rs.getInt("price_ticket"));
            plan.setRoom_id(rs.getInt("room_id"));
            plan.setShow_date(rs.getDate("show_date"));
            plan.setTime_begin(rs.getTime("time_begin"));
            plan.setType_projector_id(rs.getInt("type_projector_id"));
            
        } catch (Exception e) {
        }
        return plan;
    }
    public List<Plan_cinemas> getListPlan(String sql){
        List<Plan_cinemas> listPlan = new ArrayList<>();
        try {
           ResultSet rs = DBUtil.dbExecute(sql);
         while (rs.next()) {
            Plan_cinemas p = createPlan(rs);
            listPlan.add(p);
         }
      } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
      }
      return listPlan;
    } 
}
