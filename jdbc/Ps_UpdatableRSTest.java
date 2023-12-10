package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ps_UpdatableRSTest {

	public static void main(String[] args) {
    try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
    		PreparedStatement ps=con.prepareStatement("SELECT SNO,SNAME,SADD,AVG FROM STUDENT",
    		                                             ResultSet.TYPE_SCROLL_INSENSITIVE,
    		                                             ResultSet.CONCUR_UPDATABLE);
    	ResultSet rs=ps.executeQuery();
    		){
    	if(rs!=null) {
    		while(rs.next()) {
    			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));

    			
    		}
    		rs.moveToInsertRow();
    		rs.updateInt(1,121);
    		rs.updateString(2, "reddy");
    		rs.updateString(3, "adoni");
    		rs.updateFloat(4, 96.77f);
    		rs.insertRow();
    		//update operation
    		rs.absolute(4);
    		rs.updateString(3,"delhi");
    		rs.updateRow();
    		//delete operation
    		rs.absolute(6);
    		rs.deleteRow();
    		
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
