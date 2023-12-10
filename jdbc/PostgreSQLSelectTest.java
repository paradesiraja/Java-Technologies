package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgreSQLSelectTest {

	public static void main(String[] args) {
     try(Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Raja12345","postgres","Raja@1998");
    		PreparedStatement ps=con.prepareStatement("SELECT * FROM PRODUCT");
    		 ResultSet rs=ps.executeQuery();
    		 ){
    	 if(rs!=null) {
    		 while(rs.next()) {
    			 System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getFloat(4));
    			 
    		 }
    		 
    	 }
    	 
     }
     catch(SQLException se) {
    	 se.printStackTrace();
     }
     catch(Exception e) {
    	 e.printStackTrace();
     }
	}

}
