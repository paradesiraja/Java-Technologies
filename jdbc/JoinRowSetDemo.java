package com.nt.jdbc;

import java.sql.SQLException;

import oracle.jdbc.rowset.OracleCachedRowSet;
import oracle.jdbc.rowset.OracleJoinRowSet;

public class JoinRowSetDemo {

	public static void main(String[] args) {
         try(OracleCachedRowSet crs=new OracleCachedRowSet();
        		 OracleCachedRowSet crs1=new OracleCachedRowSet();
        		 OracleJoinRowSet jrs=new OracleJoinRowSet()){
        	 //set jdbc properties
        	 crs.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        	 crs.setUsername("xe");
        	 crs.setPassword("xe");
        	 crs.setMatchColumn(5);
        	 crs.setCommand("SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP");
        	 crs.execute();
        	 //set 2 jdbc properties
        	 crs1.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        	 crs1.setUsername("xe");
        	 crs1.setPassword("xe");
        	 crs1.setMatchColumn(1);
        	 crs1.setCommand("SELECT DEPTNO,DNAME,LOC FROM DEPT");
        	 crs1.execute();
        	 jrs.addRowSet(crs1);
        	 jrs.addRowSet(crs);
        	 
        	 //process the results
        	 while(jrs.next()) {
        		 System.out.println(jrs.getInt(1)+" "+jrs.getString(2)+" "+jrs.getString(3)+" "+jrs.getString(4)+jrs.getString(5)+" "+jrs.getString(6)+" "+jrs.getString(7));
        	 }
        	 
         }
         catch(SQLException se) {
        	 se.printStackTrace();
         }
        
	}

}
