package com.nt.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.util.Scanner;

public class SelectTest1 {

	public static void main(String[] args) {
       Scanner sc=null;
       Connection con=null;
       Statement st=null;
       ResultSet rs=null;
     
       
       try {
    	   sc=new Scanner(System.in);
    	   String city1=null,city2=null,city3=null;
    	   if(sc!=null) {
    		   
    	   System.out.println("Enter city1::");
    	   city1=sc.next();
    	   System.out.println("Enter city2::");
    	   city2=sc.next();
    	   System.out.println("Enter city3::");
    	   city3=sc.next();
    	   }
    	   //SELECT SNO,SNAME,SADD,AVG FROM STUDENT WHERE SADD IN('hyd','vizag','delhi');
    	  String cond="('"+city1+"','"+city2+"','"+city3+"')";
    	  System.out.println(cond);
    	  
    	  Class.forName("oracle.jdbc.driver.OracleDriver");
    	  
    	  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
    	  if(con!=null)
    		 st=con.createStatement();
    	  
    	//SELECT SNO,SNAME,SADD,AVG FROM STUDENT WHERE SADD IN('hyd','vizag','delhi');
         String query="SELECT SNO,SNAME,SADD,AVG FROM STUDENT WHERE SADD IN"+cond;
         System.out.println(query);
    	  if(st!=null)
    		  
    		  rs=st.executeQuery(query);
    	  if(rs!=null) {
    		 boolean rsEmpty=true;
    		  while(rs.next()) {
    			  rsEmpty=false;
    			  
    			  System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
    		  }   	
    		  if(rsEmpty==true) 
    			  System.out.println("No Records Found");
    			  else 
    				  System.out.println("Recards Found and Display");
    				  
    			  }
    		  
    	   
       }
       catch(SQLException se) {
    	   se.printStackTrace();
       }
       catch(ClassNotFoundException cnf) {
    	   cnf.printStackTrace();
       }
       catch(Exception e) {
    	   e.printStackTrace();
       }
       finally {
    	   try {
    		   if(rs!=null)
    			   rs.close();
    	   }
    	   catch(SQLException se) {
    		   se.printStackTrace();
    	   }
    	   try {
    	   if(st!=null)
			   st.close();
	   }
	   catch(SQLException se) {
		   se.printStackTrace();
	   }
    	   try {
    		   
       if(con!=null)
		   con.close();
   }
   catch(SQLException se) {
	   se.printStackTrace();
   }
    try {	   
   if(sc!=null)
	   sc.close();
}
catch(Exception e) {
   e.printStackTrace();
          }
       }  
	}
}
