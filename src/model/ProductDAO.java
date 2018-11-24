/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author stari
 */
public class ProductDAO {
    private Product createProduct(ResultSet rs){
        Product product = new Product();
        try {
            product.setId(rs.getInt("id"));
            product.setImg(rs.getString("image"));
            product.setPrice(rs.getInt("price"));
            product.setDes(rs.getString("description"));
            product.setName(rs.getString("name"));
        } catch (Exception e) {
        }
        return product;
    }
    public ObservableList<Product> getListProduct(String sql){
       ObservableList<Product> listProduct = FXCollections.observableArrayList();
        try {
           ResultSet rs = DBUtil.dbExecute(sql);
         while (rs.next()) {
            Product p = createProduct(rs);
            listProduct.add(p);
         }
      } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
      }
      return listProduct;
    } 
}
