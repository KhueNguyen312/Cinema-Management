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
public class MoviesDAO implements DAO{
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
            e.printStackTrace();
        }
        return m;
    }
    public List<Movies> getMovies(){
        String sql = "Select * from movies order by release_date";
        List<Movies> list = new ArrayList<>();
        try {
         Class.forName(DRIVER);
         Connection con = DriverManager.getConnection(DB_URL,USER,PASS);
         System.out.println("Connected");
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(sql);
         while (rs.next()) {
            Movies p = createMovies(rs);
            list.add(p);
//             System.out.println(rs.getInt(1));
             System.out.println(rs.getString(2));
         }
         rs.close();
         con.close();
      } catch (ClassNotFoundException | SQLException ex) {
      }
      return list;
    }
}
