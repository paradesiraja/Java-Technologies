package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PsLoginApp {
    private static final String AUTH_QUERY="SELECT COUNT(*) FROM USER_INFO WHERE UNAME=? AND PWD=?";
	public static void main(String[] args) {
        try(Scanner sc=new Scanner(System.in);
        		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
        		PreparedStatement ps=con.prepareStatement(AUTH_QUERY)){
        	String uname=null,pwd=null;
        	if(sc!=null) {
        		System.out.println("Enter user name::");
        		uname=sc.nextLine().toUpperCase();
        		System.out.println("Enter  password::");
        		pwd=sc.nextLine().toUpperCase();	
        	}
        	//set the query
        	if(ps!=null)
        		ps.setString(1,uname);
                ps.setString(2, pwd);
                //pass the query
               try(ResultSet rs=ps.executeQuery();
            		   ){
            	   if(rs!=null) {
            		   rs.next();
            		   int count=rs.getInt(1);
                  if(count==0)
                	  System.out.println("Inavalid credentials");
                  else
                	  System.out.println("Valid Credentials ");
                	  
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
