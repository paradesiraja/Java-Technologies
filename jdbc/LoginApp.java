package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginApp {

	public static void main(String[] args) {
        try(Scanner sc=new Scanner(System.in);
        		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
        		Statement st=con.createStatement();
        				){
	          //input values
        	String uname=null,pwd=null;
        	if(sc!=null) {
                System.out.println("Enter username ::");
                uname=sc.next().toUpperCase();
                System.out.println("Enter pwd ::");
                pwd=sc.next().toUpperCase();
        	}
        	uname="'"+uname+"'";
        	pwd="'"+pwd+"'";
        	//prepare Sql query
        	//SELECT COUNT(*) FROM USER_INFO WHERE UNAME='RAJA' AND PWD='RANI';
        	String query="SELECT COUNT(*) FROM USER_INFO WHERE UNAME="+uname+" AND PWD="+pwd;
        	System.out.println(query);
        	
                
        	//send and excute sql query
        try(ResultSet rs=st.executeQuery(query);
        		){
             if(rs!=null) {
            	 rs.next();
            	  int count=rs.getInt(1);
            	  if(count==0) 
            		  System.out.println("Invalid Credentials");
            	  
            	  else
            		  System.out.println("valid Credential"); 
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

