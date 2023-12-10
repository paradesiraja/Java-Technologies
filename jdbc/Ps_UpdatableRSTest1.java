package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ps_UpdatableRSTest1 {

	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
	    		PreparedStatement ps=con.prepareStatement("SELECT EMPNO,ENAME,JOB,SAL FROM EMP",
	    				                                            ResultSet.TYPE_SCROLL_INSENSITIVE,
	    				                                            ResultSet.CONCUR_UPDATABLE);
	    		ResultSet rs=ps.executeQuery()){
			if(rs!=null) {
				System.out.println("display top to bottom");
				while(rs.next()) {
	    			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));

	    			
				}
				rs.moveToInsertRow();
				rs.updateInt(1,1000);
				rs.updateString(2, "RAJA");
				rs.updateString(3, "JR.S/W");
				rs.updateFloat(4,5000);
				rs.insertRow();
				
				//rs.absolute(4);
				//rs.updateFloat(4,5000);
				//rs.updateRow();
				//rs.absolute(6);
				//rs.deleteRow();
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
