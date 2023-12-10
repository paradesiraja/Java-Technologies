package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ExcelInsertTest {
  private final static String EXCEL_INSERT_QUERY="INSERT INTO Sheet1 VALUES(?,?,?,?)";
	public static void main(String[] args) {
    try(Connection con=DriverManager.getConnection("jdbc:Excel:///D:\\Advanced Java\\excel\\Student.xlsx");
    		PreparedStatement ps=con.prepareStatement(EXCEL_INSERT_QUERY);
    		Scanner sc=new Scanner(System.in);
    		){
    	int no=0;
    	String name=null,add=null;
    	float avg=0.0f;
    	if(sc!=null) {
    		System.out.println("Enter student no::");
    		no=sc.nextInt();
    		System.out.println("Enter student name::");
    		name=sc.next();
    		System.out.println("Enter student adds::");
    		add=sc.next();
    		System.out.println("Enter student avg::");
    		avg=sc.nextFloat();
    	}
    	//set the values into the query
    	if(ps!=null) {
    		ps.setInt(1, no);
    		ps.setString(2, name);
    		ps.setString(3, add);
    		ps.setFloat(4, avg);
    		//excute the query
    		int count=ps.executeUpdate();
    		//process the resultSet
    		if(count==0) {
    			System.out.println("record is not inserted");
    		}
    			else
    				System.out.println("Record is inserted");
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
