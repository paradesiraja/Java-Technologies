package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest1_Mysql {

	public static void main(String[] args) {
      try(Scanner sc=new Scanner(System.in);
    		  Connection con=DriverManager.getConnection("jdbc:mysql:///Raja1234","root","Raja@1998");
    		  Statement st=con.createStatement();){
    	  //read inputs
    		  float startprice=0.0f, endprice=0.0f;
    		  if(sc!=null) {
    			  System.out.println("Enter starting price ::");
    			  startprice=sc.nextFloat();
    			  System.out.println("Enter starting price ::");
    			  endprice=sc.nextFloat();
    		  }
    		  //prepare query
    		  //SELECT * FROM PRODUCT WHERE PRICE>=100 AND PRICE<=900;
    		  String query="SELECT * FROM PRODUCT WHERE PRICE>="+startprice+"AND PRICE<="+endprice;
    			 try( ResultSet rs=st.executeQuery(query);){
    				//process the query
    				 if(rs!=null) {
    					 boolean isEmpty=true;
    					 while(rs.next()) {
    						 isEmpty=false;
    						 System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getFloat(4));
    					 }
    					 if(isEmpty)
    						 System.out.println("Record not found");
    					 else 
    						 System.out.println("Record founded");
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
