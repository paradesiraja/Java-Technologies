package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsUpdateTest {
   private static final String STUDENT_QUERY="UPDATE STUDENT SET AVG=AVG+AVG*10/100 WHERE AVG<90 AND SADD=?";
	public static void main(String[] args) {
     try(Scanner sc=new Scanner(System.in);
    		 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
    		 PreparedStatement ps=con.prepareStatement(STUDENT_QUERY)){
    	 int count=0;
    	 if(sc!=null) {
    		 System.out.println("enter city count no::");
    		 count=sc.nextInt();
    	 }
    	 String sadd=null;
    	 for(int i=1;i<=count;++i) {
    		 if(sc!=null) {
    			 System.out.println(i+"Enter a city name::");
    			 sadd=sc.next();
    		 }
    	 
    	 
    	 //set the values
    	 if(ps!=null)
    		 ps.setString(1,sadd );
    	     
    			count=ps.executeUpdate(); 
    		  if(count==0)
    			  System.out.println("no updates");
    		  else
    			  System.out.println("updated city avg"+count);
    		  
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
