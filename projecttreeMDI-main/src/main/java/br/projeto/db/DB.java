package br.projeto.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
    private static Connection conn = null;

    public static Connection getConnection(){
        //String url = "jdbc:sqlite:projetos_de_estimativas.db";
        String dbPath = "src/main/resources/projetos_de_estimativas.db"; 
        String url = "jdbc:sqlite:" + dbPath;
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(url);
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeStatement(Statement st){
            try{
                st.close();
            }catch(SQLException e){
                throw new DbException(e.getMessage());
            }
    }

    public static void closeResultSet(ResultSet rs){
            try{
                rs.close();
            }catch(SQLException e){
                throw new DbException(e.getMessage());
            }
        
    }

}