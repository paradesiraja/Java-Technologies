package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScrollableRSTest2 {

	public static void main(String[] args) {
   try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
		   PreparedStatement ps=con.prepareStatement("SELECT * FROM EMP",ResultSet.TYPE_SCROLL_INSENSITIVE,
				                                      ResultSet.CONCUR_READ_ONLY);
		   ResultSet rs=ps.executeQuery();){
	   if(rs!=null) {
		   System.out.println("Display record(top to Bottom)");
		   while(rs.next()) {
			   System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
		   }
		   rs.afterLast();
		   System.out.println("Record display (Bottom to top)");
		   while(rs.previous()) {
			   System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
		   }
		   //display randomly or directly
		   System.out.println("Display records randomly or directly");
		   rs.first();
		   System.out.println(rs.getRow()+"--->"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
		   
		   rs.last();
		   
		   System.out.println(rs.getRow()+"--->"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));

		   rs.absolute(3);
		   System.out.println(rs.getRow()+"--->"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));

		   rs.absolute(-5);
		   System.out.println(rs.getRow()+"--->"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));

		   rs.relative(3);
		   System.out.println(rs.getRow()+"--->"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));

		   rs.relative(-3);
		   System.out.println(rs.getRow()+"--->"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));

	   
		
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
