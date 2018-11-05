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
public class VersionDAO {
    private Version createVersion(ResultSet rs) {
        Version v = new Version();
        try {
            v.setId(rs.getInt("id"));
            v.setName(rs.getString("name"));
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return v;
    }
    
    public List<Version> getListVersionMovie(String sql){
        List<Version> listVersion = new ArrayList<>();
        try {
           ResultSet rs = DBUtil.dbExecute(sql);
         while (rs.next()) {
            Version v = createVersion(rs);
            listVersion.add(v);
         }
      } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
      }
      return listVersion;
    }
}
