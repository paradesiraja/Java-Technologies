package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RequiredDetailsSelectTest {

	public static void main(String[] args) {
    try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
    		Statement st=con.createStatement();
    		ResultSet rs=st.executeQuery("SELECT * FROM STUDENT WHERE ROWNUM<=5");
    		){
    	//process the result
    	
    	if(rs!=null) {
    		
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
