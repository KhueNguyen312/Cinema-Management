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
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
/**
 *
 * @author stari
 */
public class MoviesDAO{
    private Movies createMovies(ResultSet rs){
        Movies m = new Movies();
        try {
           m.setId(rs.getInt("id"));
           m.setName(rs.getString("name"));
           m.setImage(rs.getString("image"));
           m.setDuration(rs.getInt("duration"));
           m.setLinkTrailer(rs.getString("link_trailer"));
           m.setDirector(rs.getString("director"));
           m.setCast(rs.getString("cast"));
           m.setGenre(rs.getString("genre"));
           m.setLanguage(rs.getString("language"));
           m.setRated(rs.getString("rated"));
           m.setContent(rs.getString("content"));
           m.setRelease_date(rs.getDate("release_date"));
         
           
        } catch (Exception e) {
        }
        return m;
    }
    public ObservableList<Movies> getMovies(){
        String sql = "Select * from movies order by release_date";
        ObservableList<Movies> list = FXCollections.observableArrayList();
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
    public  void AddMovies(Movies m)
    {
        String sql = "INSERT INTO movies (id, name, image, duration,"
                + " link_trailer, director, cast, genre, language, release_date, rated, content)"
                + "VALUES ( "+m.getId()+ ", '"+m.getName()+"', '"+m.getImage()+"', "+ m.getDuration()
                +", '"+m.getLinkTrailer()+"', '"+m.getDirector()+"', '"+m.getCast()+"', '"+m.getGenre()+""
                + "', '"+m.getLanguage()+"', '"+m.getRelease_date()+"', '"+m.getRated()+"', '"+m.getContent()+")";
        System.out.println(sql);
         try {

            int stmt = DBUtil.dbExcecuteQuery(sql);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.out.println("Can't add movies!");
        }
    }
    public void UpdateMovies(Movies m){
        String sql = "update movies set name = '"+m.getName()+"', image = '"+m.getImage()+ "', duration = "+m.getDuration()
                +", link_trailer = '"+m.getLinkTrailer()+"', director = '"+m.getDirector()+"', cast = '"+m.getCast()
                +"', genre = '"+ m.getGenre()+ "', language = '" +m.getLanguage()+"',"
                + "release_date = '"+m.getRelease_date()+"', rated = '"+ m.getRated()+ "', content = '"+ m.getContent()+"'"
                + " where id = "+ m.getId();
        System.out.println(sql);
        int stmt = 0;
        try {

            stmt = DBUtil.dbExcecuteQuery(sql);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.out.println("Can't update movies!");
        }
    }
    public void DeleteEmployee(int id) {
        String sql = "delete from movies where id = " + id;
        try {
            int stmt = DBUtil.dbExcecuteQuery(sql);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.out.println("Can't delete movie!");
        }
    }
}
