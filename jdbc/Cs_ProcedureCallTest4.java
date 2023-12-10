package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*CREATE OR REPLACE PROCEDURE P_GET_STUDENT_DETAILS_BY_NO 
(
  STDNO IN NUMBER 
, STDNAME OUT VARCHAR2 
, AVG OUT FLOAT 
) AS 
BEGIN
  SELECT SNAME,AVG INTO STDNAME,AVG FROM STUDENT WHERE SNO=STDNO;
END P_GET_STUDENT_DETAILS_BY_NO;
*/
public class Cs_ProcedureCallTest4 {
  private final static String CALL_PROCEDURE="{CALL P_GET_STUDENT_DETAILS_BY_NO(?,?,?)}"; 
	public static void main(String[] args) {
    try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
    		CallableStatement cs=con.prepareCall(CALL_PROCEDURE);
    		Scanner sc=new Scanner(System.in);
    		){
    	int no=0;
    	if(sc!=null) {
    		System.out.println("Enter std no::");
    		no=sc.nextInt();
    	}
    	//register OUT params and jdbc types
    	if(cs!=null) {
    		cs.registerOutParameter(2,Types.VARCHAR);
    		cs.registerOutParameter(3,Types.FLOAT);
    		//set the In params
    		cs.setInt(1, no);
    		//call PL/SQL procedure
    		cs.execute();
    		//gather results from OUT param
    		System.out.println("student name::"+cs.getString(2));
    		System.out.println("Student avg::"+cs.getFloat(3));
    		
    	}
    	
    }
    catch(SQLException se) {
    	if(se.getErrorCode()==1403)
    		System.out.println("student data not found");
    }
    catch(Exception e) {
    	e.printStackTrace();
    }
	}

}
