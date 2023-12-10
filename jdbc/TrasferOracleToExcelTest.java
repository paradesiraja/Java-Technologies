package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TrasferOracleToExcelTest {
	private final static String ORACLE_QUERY="SELECT * FROM PRODUCT";
	private final static String INSERT_EXCEL_QUERY="INSERT INTO sheet1 VALUES(?,?,?,?)";

	public static void main(String[] args) {
    try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
    		Connection con1=DriverManager.getConnection("jdbc:Excel:///D:\\Advanced Java\\excel\\Product.xlsx");
    		Statement st=con.createStatement();
    		PreparedStatement ps=con1.prepareStatement(INSERT_EXCEL_QUERY);
    		Scanner sc=new Scanner(System.in);
    		){
    	try(ResultSet rs=st.executeQuery(ORACLE_QUERY);){
    		
    	
    	if(st!=null && ps!=null)   {
    		while(rs.next()) {
    			int no=rs.getInt(1);
    			String name=rs.getString(2);
    			float price=rs.getFloat(3);
    			float qty=rs.getFloat(4);
    			
    		
    		
    			ps.setInt(1, no);
    			ps.setString(2, name);
    			ps.setFloat(3, price);
    			ps.setFloat(4, qty);
    			
    			int count=ps.executeUpdate();
    			if(count==0) {
    				System.out.println("Record is not inserted");
    			}
    				else
    					System.out.println("Records is inserted");
    					
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
