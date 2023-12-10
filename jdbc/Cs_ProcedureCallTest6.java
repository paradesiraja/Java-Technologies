package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

/*CREATE OR REPLACE PROCEDURE P_GET_STUDENT_DETAILS_BY_CHARS 
(
  NAMECHARS IN VARCHAR2 
, DETAILS OUT SYS_REFCURSOR 
) AS 
BEGIN
    OPEN DETAILS FOR
  SELECT SNO,SNAME,SADD,AVG FROM STUDENT WHERE SNAME LIKE NAMECHARS;
END P_GET_STUDENT_DETAILS_BY_CHARS;*/
public class Cs_ProcedureCallTest6 {
private final static String CALL_PROCEDURE="{CALL P_GET_STUDENT_DETAILS_BY_CHARS(?,?)}"; 
	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
	    		CallableStatement cs=con.prepareCall(CALL_PROCEDURE);
	    		Scanner sc=new Scanner(System.in);
	    		){
			//read inputs
			String initchars=null;
			if(sc!=null) {
				System.out.println("Enter student starting character::");
				initchars=sc.next();
			}
			//register OUT params and jdbc types
			if(cs!=null) {
				cs.registerOutParameter(2,OracleTypes.CURSOR);
				//set the IN params
				cs.setString(1, initchars+"%");
				//call PL/SQL procedure
				cs.execute();
				//gather ResutlSet from OUT params
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
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
