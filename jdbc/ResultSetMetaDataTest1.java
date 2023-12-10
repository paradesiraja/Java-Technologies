package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetMetaDataTest1 {

	public static void main(String[] args) {
     try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
    		 Statement st=con.createStatement();
    		 ResultSet rs=st.executeQuery("SELECT * FROM STUDENT");
    		 ){
    	 ResultSetMetaData rmd=null;
    	 if(rs!=null) {
    		 rmd=rs.getMetaData();
    		 if(rmd!=null) {
    			 int colCount=rmd.getColumnCount();
    			 for(int i=1;i<=colCount;++i) {
    				 System.out.println(rmd.getColumnName(i));
    				 System.out.println(rmd.getColumnDisplaySize(i));
    				 System.out.println(rmd.getColumnType(i));
    				 System.out.println(rmd.getScale(i));
    				 System.out.println(rmd.getPrecision(i));
    				 System.out.println(rmd.getColumnName(i));
    			 }
    			 System.out.println();
    			 
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
