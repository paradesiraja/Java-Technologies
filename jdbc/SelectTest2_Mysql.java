package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest2_Mysql {

	public static void main(String[] args) {
      try(Scanner sc=new Scanner(System.in);
    		  Connection con=DriverManager.getConnection("jdbc:mysql:///Raja1234","root","Raja@1998");
    		  Statement st=con.createStatement();){
    	  //read inputs
    		  int startqty=0, endqty=0;
    		  if(sc!=null) {
    			  System.out.println("Enter STARTINGQTY ::");
    			  startqty=sc.nextInt();
    			  System.out.println("Enter end qty ::");
    			  endqty=sc.nextInt();
    		  }
    		  //prepare query
    		  //SELECT * FROM PRODUCT WHERE QTY>=2 AND QTY<=9;
    		  //SELECT * FROM PRODUCT WHERE QTY>=3 AND QTY<=9;
    		  String query="SELECT * FROM PRODUCT WHERE QTY>="+startqty+" AND QTY<="+endqty;
    		  System.out.println(query);
    		  if(st!=null)
    			 try( ResultSet rs=st.executeQuery(query);
    					 ){
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

