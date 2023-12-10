package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PersonAgeCalculater_MYSQL {
  private final static String AGE_CALCULATER_QUERY="SELECT (DATEDIFF(NOW(),DOB))/365.25 FROM CUSTOMER_INFO WHERE CNO=?"; 
	public static void main(String[] args) {
      try(Scanner sc=new Scanner(System.in);
    		  Connection con=DriverManager.getConnection("jdbc:mysql:///Raja12345","root","Raja@1998");
    		  PreparedStatement ps=con.prepareStatement(AGE_CALCULATER_QUERY);
    		  ){
    	  //read input values
    	  int no=0;
    	  if(sc!=null) {
    		  System.out.println("Enter customer id::");
    		  no=sc.nextInt();
    	  }
    	  //set values to query
    	  if(ps!=null) {
    		  ps.setInt(1,no);
    	  }
    	  //execute  the query and get the resultset obj
    	  try(ResultSet rs=ps.executeQuery()){
    		  if(rs!=null) {
    			  if(rs.next()) {
    				  System.out.println("Customer Age::"+rs.getFloat(1));
    			  }
    			  else
    				  System.out.println("customer not found");
    			  
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
