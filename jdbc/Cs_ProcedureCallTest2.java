package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
/*CREATE OR REPLACE PROCEDURE P_GET_EMP_DETAILS_BY_EMPNO 
(
  NO IN NUMBER 
, NAME OUT VARCHAR2 
, SALARY OUT FLOAT 
) AS 
BEGIN
  SELECT ENAME,SAL INTO NAME,SALARY FROM EMP WHERE EMPNO=NO;
END P_GET_EMP_DETAILS_BY_EMPNO;*/

public class Cs_ProcedureCallTest2 {
  private final static String CALL_PROCEDURE="{call P_GET_EMP_DETAILS_BY_EMPNO(?,?,?)}";
	public static void main(String[] args) {
    try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
    		CallableStatement cs=con.prepareCall(CALL_PROCEDURE);
    		Scanner sc=new Scanner(System.in);
    		){
    	int no=0;
    	if(sc!=null) {
    		System.out.println("Enter Employee number::");
    		no=sc.nextInt();
    		
    	}
    	if(cs!=null) {
    	//register OUT params with jdbc types
    	cs.registerOutParameter(2,Types.VARCHAR);
    	cs.registerOutParameter(3,Types.FLOAT);
    	
    	//set the values of IN params
          cs.setInt(1, no);
          //call PL/SQL procudure
          cs.execute();
          //gather results from Out params
          
          System.out.println("Emp name::"+cs.getString(2));
          System.out.println("Emp salary::"+cs.getFloat(3));
          
          
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
