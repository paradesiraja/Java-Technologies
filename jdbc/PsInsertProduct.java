package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsInsertProduct {
  private final static String PRODUCT_INSERT_QUERY="INSERT INTO PRODUCT VALUES(?,?,?,?)";
	public static void main(String[] args) {
       try(Scanner sc=new Scanner(System.in);
    		   Connection con=DriverManager.getConnection("jdbc:mysql:///Raja1234","root","Raja@1998");
    		   PreparedStatement ps=con.prepareStatement(PRODUCT_INSERT_QUERY)){
    	   int count=0;
    	   if(sc!=null) {
    		   System.out.println("Enter product count ::");
    		 count=sc.nextInt();
    	   }
    	   if(sc!=null && ps!=null) {
    		   for(int i=1;i<=count;++i) {
    			   System.out.println("Enter a product no");
    			  int pno=sc.nextInt();
    			  System.out.println("Enter product name::");
    			  String pname=sc.next();
    			  System.out.println("Enter product price::");
    			  float pprice=sc.nextFloat();
    			  System.out.println("Enter product qty::");
    			  float qty=sc.nextFloat();
    			  //set the product details to pre compiled sql query param values
    			  ps.setInt(1, pno);
    			  ps.setString(2, pname);
    			  ps.setFloat(3, pprice);
    			  ps.setFloat(4, qty);
    			  
    			  //execute the query
    			  int result=ps.executeUpdate();
    			  //process the query
    			  if(result==0) 
    				  System.out.println(i+"product details are not inserted");
    			  else
    				  System.out.println(i+"product details are inserted");
    			  
    				  
    			  }//for
    		   }//if
    	   }//try
	
catch(SQLException se) {
	se.printStackTrace();
          }
       catch(Exception e) {
    	   e.printStackTrace();
       }
   }//main
}//class
