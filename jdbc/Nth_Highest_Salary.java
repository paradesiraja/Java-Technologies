package com.nt.jdbc;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;



public class Nth_Highest_Salary {
  private static final String Nth_Highest_Salary="SELECT * FROM(SELECT ROW_NUMBER() OVER (ORDER BY SAL DESC)RNO,E. * FROM EMP E)WHERE RNO IN(?)";
	public static void main(String[] args) {
        try(Scanner sc=new Scanner(System.in);
        		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
        	PreparedStatement ps=con.prepareStatement(Nth_Highest_Salary);
        		
        		){
        	int no=0;
        	if(sc!=null) {
        		System.out.println("Enter a row_number::");
        		no=sc.nextInt();
        	}
        	 if(ps!=null)
        		 ps.setInt(1, no);
        	 try(ResultSet rs=ps.executeQuery();
        			 ){
        		 if(rs!=null) {
          		  while(rs.next()) {
      System.out.println(rs.getInt(1)+" "+rs.getInt(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getInt(5)+" "+rs.getString(6)+" "+rs.getFloat(7));
                    
          		   }
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

