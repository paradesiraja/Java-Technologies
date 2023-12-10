package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ps_SENSITIVERSTest1 {

	public static void main(String[] args) {
    try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
    		PreparedStatement ps=con.prepareStatement("SELECT EMPNO,ENAME,JOB,SAL FROM EMP",
    				                                            ResultSet.TYPE_SCROLL_SENSITIVE,
    				                                            ResultSet.CONCUR_UPDATABLE);
    		ResultSet rs=ps.executeQuery()){
    	if(rs!=null) {
    		int count=0;
    			if(count==0) {
    				System.out.println("10 sec to update db table");
    			
    			
    				Thread.sleep(10000);
    			}
    			while(rs.next()) {
    			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
    			
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
