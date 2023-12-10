package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectNonSelectTest {

	public static void main(String[] args) {
       try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
    		   Statement st=con.createStatement();
    				   Scanner sc=new Scanner(System.in);){
    	   String Query=null;
    	   if(sc!=null) {
    		   System.out.println("Enter Query ::");
    		   Query=sc.nextLine();
    	   }
    	   //execute the query
    	   if(st!=null) {
    	   boolean flag=st.execute(Query);
    		   if(flag) {
    			   System.out.println("Sql query is excuted");
    		   try(ResultSet rs=st.getResultSet()){
    			   if(rs!=null) {
    				   while(rs.next()) {
    					   System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
    					   
    				   }
    			   }
    		   }
    			   
    		   }
    		   else {
    			   System.out.println("Non Select sql query is excuted");
    			   int count=st.getUpdateCount();
    			   System.out.println("NO.of records are effected::"+count);
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


