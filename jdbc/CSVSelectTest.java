package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CSVSelectTest {
     
	public static void main(String[] args) {
     try(Connection con=DriverManager.getConnection("jdbc:Text:///D:\\Advanced Java\\excel");
    		 PreparedStatement ps=con.prepareStatement("SELECT * FROM file2.csv",
                     ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_UPDATABLE);
    		 ResultSet rs=ps.executeQuery();
    		 ){
    	 if(rs!=null) {
    		 while(rs.next()) {
    			 System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
    		 }
    		rs.absolute(1);
    		rs.deleteRow();
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
