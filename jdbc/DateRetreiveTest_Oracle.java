package com.nt.jdbc;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class DateRetreiveTest_Oracle {
  private final static String GET_CUSTOMER_QUERY="SELECT * FROM CUSTOMER_INFO";
	public static void main(String[] args) {
    try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
    		PreparedStatement ps=con.prepareStatement(GET_CUSTOMER_QUERY);
    		ResultSet rs=ps.executeQuery();
    		){
    	//process the result
    	if(rs!=null) {
    		while(rs.next()) {
    			int cno=rs.getInt(1);
    			String cname=rs.getString(2);
    			float billamt=rs.getFloat(3);
    			java.sql.Date dob=rs.getDate(4);
    			java.sql.Time top=rs.getTime(5);
    			java.sql.Timestamp orderdt=rs.getTimestamp(6);
    			
    			//convert java.sql.date to String values in the Required pattern
    			SimpleDateFormat sdf=new SimpleDateFormat("MMM-dd-yyyy");
    			String sdob=sdf.format(dob);
    			
    			//convert java.sql.Time to String values in the required pattern
    			long ms=top.getTime();
    			java.util.Date utop=new java.util.Date(ms);
    			SimpleDateFormat sdf1=new SimpleDateFormat("hh:mm:ss");
    			String stop=sdf1.format(utop);
    			
    			//convert java.sql.Timestamp to String values in the required pattern
    			long ms1=orderdt.getTime();
    			java.util.Date uorderdt=new java.util.Date(ms1);
    			SimpleDateFormat sdf2=new SimpleDateFormat("MMM-dd-yyyy hh:mm:ss");
    			String sorderdt=sdf2.format(uorderdt);
    			
    			System.out.println("cno::"+cno+" cname::"+cname+" bill amount::"+billamt+" dob::"+dob+" top::"+top+" orderdt::"+orderdt);
    			
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
