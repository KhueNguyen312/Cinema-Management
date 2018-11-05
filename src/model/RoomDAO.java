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
public class RoomDAO {
    private Room creatRoom(ResultSet rs) {
        Room r = new Room();
        try {
            r.setId(rs.getInt("id"));
            r.setName(rs.getString("name"));
            r.setCinema_id(rs.getInt("cinema_id"));
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return r;
    }
     public List<Room> getListRoom(String sql){
        List<Room> listRoom = new ArrayList<>();
        try {
           ResultSet rs = DBUtil.dbExecute(sql);
         while (rs.next()) {
            Room r = creatRoom(rs);
            listRoom.add(r);
         }
      } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
      }
      return listRoom;
    }
}
