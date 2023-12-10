package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {   //java code 
			String design1=null,design2=null,design3=null;
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Design1::");
				design1=sc.next().toUpperCase();
				System.out.println("Enter Design2::");
				design2=sc.next().toUpperCase();
				System.out.println("Enter Design3::");
				design3=sc.next().toUpperCase();
				
			}
			// SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE JOB IN('CLERK','MANAGER','SALESMAN') ORDER BY JOB;
			String cond="('"+design1+"','"+design2+"','"+design3+"')";
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
			 
			String query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE JOB IN"+ cond +"ORDER BY JOB";
			System.out.println(query);
			 if(con!=null)
				 st=con.createStatement();
			 if(st!=null)
				 rs=st.executeQuery(query);
			 if(rs!=null) {
				 while(rs.next()){
					 System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				 }
			 }		 
		}//try
		catch(SQLException se) {   //know exception
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) { //know exception
			cnf.printStackTrace();
		}
		catch(Exception e) {  //unknown Exceptions
			e.printStackTrace();
		}
	
	finally {  //close all objs
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
	}//finally
   }//main
}//class
	
