package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsInsertTest {
      private final static String STUDENT_INSERT_QUERY="INSERT INTO STUDENT VALUES(?,?,?,?)";
	public static void main(String[] args) {
          try(Scanner sc=new Scanner(System.in);
        		  Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
        		  PreparedStatement ps=con.prepareStatement(STUDENT_INSERT_QUERY);
        		  ){
        	  int count=0;
        	  if(sc!=null) {
        		  System.out.println("Enter student count numbers::");
        		  count=sc.nextInt();
        		      }
        	  if(sc!=null && ps!=null)
        	  for(int i=1;i<=count;++i) {
        		  System.out.println("Enter"+i+" student details:");
                 System.out.println("Enter student no:");
                int no=sc.nextInt();
                System.out.println("Enter student name");
                String name=sc.next();
                System.out.println("Enter student address");
                String addrs=sc.next();
                System.out.println("Enter student avg");
                float avg=sc.nextFloat();
                //set each student details to pre-compiled SQL Quuery as query param values(?)
                ps.setInt(1, no);
                ps.setString(2, name);
                ps.setString(3, addrs);
                ps.setFloat(4, avg);
                //execute the Query
                int result=ps.executeUpdate();
                //process the result
                if(result==0)
                	System.out.println(i+ "Student record is not inserted");
                else
                	System.out.println(i+ "Student records are inserted");
        	  
                 
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
