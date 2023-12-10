package com.nt.jdbc;

import java.sql.SQLException;

import oracle.jdbc.rowset.OracleJDBCRowSet;

public class JdbcRowsetDemo {

	public static void main(String[] args) {
    try(OracleJDBCRowSet jrs=new OracleJDBCRowSet()){
    	//set the jdbc properties
    	jrs.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
    	jrs.setUsername("xe");
    	jrs.setPassword("xe");
    	jrs.setCommand("SELECT * FROM STUDENT");
    	jrs.execute();
    	//process the result
    	while(jrs.next()) {
    		System.out.println(jrs.getInt(1)+" "+jrs.getString(2)+" "+jrs.getString(3)+" "+jrs.getFloat(4));
    		
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
