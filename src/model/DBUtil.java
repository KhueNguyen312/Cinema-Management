/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import com.sun.rowset.CachedRowSetImpl;
import java.sql.*;

/**
 *
 * @author stari
 */
public class DBUtil {
    public static Connection connection = null;
    private static final String DB_URL = "jdbc:mysql://34.219.230.39:3306/cinema_java";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "cinema_java";
    private static final String PASS = "VanLoc@354";
    
    
    public static void dbConnect() throws ClassNotFoundException, SQLException{
        try{
            Class.forName(DRIVER);
        }
        catch(ClassNotFoundException e){
            System.out.println("MySQL Driver not found");
            e.printStackTrace();
            throw  e;
        }
        try{
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
        }
        catch(SQLException e){
            System.out.println("Connection failed!");
            throw e;
        }
        System.out.println("Connected");
        
    }
    public static void dbDisconnect() throws SQLException{
        try {
            if(connection != null && !connection.isClosed())
                connection.close();
        } catch (Exception e) {
            throw e;
        }
    }
    // This is for insert/delete/update operation
    public static int dbExcecuteQuery(String sqlStmt) throws ClassNotFoundException, SQLException{
        Statement stmt = null;
        int rowEffect = 0;
        try {
            dbConnect();
            stmt = connection.createStatement();
            rowEffect = stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem occured at dbExecuteQuery operation "+ e);
            //throw  e;
        }
        finally{
            if(stmt!=null){
                stmt.close();
            }
            dbDisconnect();
        }
        return rowEffect;
    }

    //This is for retriving the records from the db
    public static ResultSet dbExecute(String sqlQuery) throws ClassNotFoundException, SQLException{
        Statement stmt = null;
        ResultSet rs = null;
        CachedRowSetImpl crs = null;
        
        try{
            dbConnect();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sqlQuery);
            crs = new CachedRowSetImpl();
            crs.populate(rs);
        }
        catch(SQLException e){
            System.out.println("Error occured at dbExecute operation "+ e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if(stmt != null){
                stmt.close();
            }
            dbDisconnect();
        }
        return crs;
    }
}
