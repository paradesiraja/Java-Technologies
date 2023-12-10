package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;
/*CREATE OR REPLACE PROCEDURE P_GET_EMP_DETAILS_ENAME_CHARS 
(
  NAMECHARS IN VARCHAR2 
, DETAILS OUT SYS_REFCURSOR 
) AS 
BEGIN
    OPEN DETAILS FOR
  SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE ENAME LIKE NAMECHARS;
END P_GET_EMP_DETAILS_ENAME_CHARS;*/
public class Cs_ProcedureCallTest5 {
  private final static String CALL_PROCEDURE="{CALL P_GET_EMP_DETAILS_ENAME_CHARS(?,?)}"; 
	public static void main(String[] args) {
    try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
    		CallableStatement cs=con.prepareCall(CALL_PROCEDURE);
    		Scanner sc=new Scanner(System.in);
    		){
    	//read input values
    	String initchars=null;
    	if(sc!=null) {
    		System.out.println("Enter name character::");
    		initchars=sc.next().toUpperCase();
    	}
    	//register OUT params 
    	if(cs!=null) {
    		cs.registerOutParameter(2,OracleTypes.CURSOR);
    		//SET the values IN params
    		cs.setString(1, initchars+"%");
    		//CALL PL/SQL procedure
    		cs.execute();
    		//gather ResultSet from OUT params
    		try(ResultSet rs=(ResultSet)cs.getObject(2)){
    			if(rs!=null) {
    				
    				while(rs.next()) {
    					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
    				
    				}
    			
    			}
    		}
    	}
    	
    }
    catch(SQLException se) {
    	if(se.getErrorCode()==1403) 
    		System.out.println("Emp number not found");
    	else if(se.getErrorCode()==1017)
    		System.out.println("invalid cedentials");
    	else
    		System.out.println("some db problem");
    }
    catch(Exception e) {
    	e.printStackTrace();
    }
	}

}
