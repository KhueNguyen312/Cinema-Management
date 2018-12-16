/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
      public ObservableList<Room> getListRoom(){
          String sql = "select * from rooms";
        ObservableList<Room> listRoom = FXCollections.observableArrayList();
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
      public void AddRooms(Room room){
          String sql = "insert into rooms(id, name, cinema_id) values ("+room.getId() + ", '" +room.getName()+"', 1)";
          System.out.println("add rooms: "+ sql);
          try {
              int con = DBUtil.dbExcecuteQuery(sql);
          } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.out.println("Can't add rooms!");
        }
      }
      public void UpdateRooms(Room room){
          String sql = "update rooms set name = '" + room.getName()+"' where id = " +room.getId();
          System.out.println("update rooms: "+ sql);
          try {
              int con = DBUtil.dbExcecuteQuery(sql);
          } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.out.println("Can't update rooms!");
        }
      }
      public void DeleteRooms(int id){
          String sql = "delete from rooms where id = " + id;
          System.out.println("delete rooms: "+ sql);
          try {
              int con = DBUtil.dbExcecuteQuery(sql);
          } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.out.println("Can't delete rooms!");
        }
      }
}
