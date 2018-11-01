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
public class MoviesDAO{
    private Movies createMovies(ResultSet rs){
        Movies m = new Movies();
        try {
            m.setID(rs.getInt("id"));
            m.setName(rs.getString("name"));
            m.setImg(rs.getString("image"));
            m.setDuration(rs.getInt("duration"));
            m.setDirector(rs.getString("director"));
            m.setCast(rs.getString("cast"));
            m.setGenre(rs.getString("genre"));
            m.setLanguage(rs.getString("language"));
            m.setReleaseDate(rs.getDate("release_date"));
            m.setRated(rs.getString("rated"));
            m.setContent(rs.getString("content"));
        } catch (Exception e) {
        }
        return m;
    }
    public List<Movies> getMovies(){
        String sql = "Select * from movies order by release_date";
        List<Movies> list = new ArrayList<>();
        try {
           ResultSet rs = DBUtil.dbExecute(sql);
         while (rs.next()) {
            Movies p = createMovies(rs);
            list.add(p);
         }
      } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
      }
      return list;
    }
}
