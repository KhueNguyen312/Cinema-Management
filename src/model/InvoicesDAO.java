/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author stari
 */
public class InvoicesDAO {
    private Invoices createInvoice(ResultSet rs){
        Invoices invoice = new Invoices();
        try {
            invoice.setId(rs.getInt("id"));
            invoice.setBuy_date(rs.getDate("buy_date"));
            invoice.setEmployee_id(rs.getInt("employee_id"));
            invoice.setTotal(rs.getInt("total"));
            invoice.setTotal(rs.getInt("user_id"));
            invoice.setStatus(rs.getInt("status"));
            
        } catch (Exception e) {
        }
        return invoice;
    }
    public int createInvoice(Invoices invoice) throws ClassNotFoundException, SQLException{
//        String sql = "INSERT INTO `invoices`(`total`, `buy_date`, `employee_id`, `user_id`, `status`) VALUES("
//                + invoice.getTotal() + "," + invoice.getBuy_date() + "," + invoice.getEmployee_id() + "," + invoice.getUser_id() + "," + invoice.getStatus() + ")";
        String sql = "INSERT INTO `invoices`(`total`, `buy_date`, `employee_id`, `user_id`, `status`) VALUES(?,?,?,?,?)";
        PreparedStatement statement = null;
        try {
            DBUtil.dbConnect();
            statement = DBUtil.connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1,invoice.getTotal());
            statement.setDate(2, invoice.getBuy_date());
            statement.setInt(3, invoice.getEmployee_id());
            statement.setInt(4, invoice.getUser_id());
            statement.setInt(5, invoice.getStatus());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return (int) generatedKeys.getLong(1);
            }
        } catch (SQLException e) {
            System.out.println("Problem occured at dbExecuteQuery operation " + e);
            //throw  e;
        } finally {
            if (statement != null) {
                statement.close();
                DBUtil.dbDisconnect();
            }
        }
        return -1;
    }
}
