package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest2 {

	public static void main(String[] args) {
       Connection con=null;
       Statement st=null;
       ResultSet rs=null;
       Scanner sc=null;
       try {
    	   sc=new Scanner(System.in);
    	   int deptno=0;
    	   if(sc!=null) {
    		   System.out.println("enter Deptn::");
    		   deptno=sc.nextInt();
    	   }
    	   //load class loader
    	   Class.forName("oracle.jdbc.driver.OracleDriver");
    	   //connect jdbc and DB s/w
    	   con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
    	   //create Statement
    	   if(con!=null)
    		   st=con.createStatement();
    	   //SELECT * FROM DEPT WHERE DEPTNO=10;
    	   String query="SELECT * FROM DEPT WHERE DEPTNO="+deptno;
    	   System.out.println(query);
    	   if(st!=null)
    		   rs=st.executeQuery(query);
    	   if(rs!=null){
    		   
    		   if(rs.next()) 
    			   System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
    		   else
    			   System.out.println("record not found");
    		    
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
       finally{
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


