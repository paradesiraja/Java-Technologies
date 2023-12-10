package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchProcessingTest {

	public static void main(String[] args) {
    try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
    		Statement st=con.createStatement();){
    	if(st!=null) {
    		st.addBatch("INSERT INTO STUDENT VALUES(150,'vinod','hyd',87.99)");
    		st.addBatch("UPDATE STUDENT SET AVG=AVG-10 WHERE SNO>=100 AND SNO<=2000");
    		st.addBatch("DELETE FROM STUDENT WHERE SADD='HYD'");
    		
    		//EXCUTE THE RESULT
    		int result[]=st.executeBatch();
    		//process the result
    		int sum=0;
            for(int i=1;i<result.length;++i) {
            	if(sum==0) {
            	sum=sum+result[i];
            	System.out.println("No.of records are effected::"+sum);
            	
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
