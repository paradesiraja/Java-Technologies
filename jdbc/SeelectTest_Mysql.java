package com.nt.jdbc;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SeelectTest_Mysql {

	public static void main(String[] args) {
      try(Connection con=DriverManager.getConnection("jdbc:mysql:///Raja1234","root","Raja@1998");
    		  Statement st=con.createStatement();
    		  ResultSet rs=st.executeQuery("SELECT * FROM PRODUCT")
    				  ){
    	  if(rs!=null) {
    		  boolean isEmpty=true;
    		  while(rs.next()) {
    			  isEmpty=false;
    			  System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getFloat(4));
    		  }
    		  if(isEmpty)
    			  System.out.println("No records found");
    		  else
    		  System.out.println("record is founded");
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




