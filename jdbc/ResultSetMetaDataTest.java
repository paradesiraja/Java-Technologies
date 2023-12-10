package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetMetaDataTest {

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
    				 System.out.print(rmd.getColumnName(i)+"\t");
    			 }
    			 System.out.println();
    			 for(int i=1;i<=colCount;++i) {
    			 System.out.print(rmd.getColumnTypeName(i)+"\t");
    			 }
    			 System.out.println();
    			 while(rs.next()) {
    				 for(int i=1;i<=colCount;++i) {
    					 System.out.print(rs.getString(i)+"\t");
    				 }
    				 System.out.println();
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
