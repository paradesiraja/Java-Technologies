package com.nt.jdbc;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Sample_Date_Format_code {

	public static void main(String[] args)throws Exception {
		
		//converting string date values to java.util.date
        String d1="28-10-2023";
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date ud1=sdf.parse(d1);
        System.out.println("udf date::"+ud1);
        
        //convert util.Date to sql.Date
        long ms=ud1.getTime();
        java.sql.Date sqd=new java.sql.Date(ms);
        System.out.println("sql date::"+sqd);
        
       //converting string date values of yyyy-MM-dd pattern directly to java.sql.Date class object
        String d2="2023-10-28";
        java.sql.Date sqd1=java.sql.Date.valueOf(d2);
        System.out.println("sql date::"+sqd1);
        
        
    
       /* String d3="12-10-1998";
        SimpleDateFormat sdf2=new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date ud2=sdf2.parse(d3);
        System.out.println("utl date::"+ud2);
        
        long ms1=ud2.getTime();
        java.util.Date ud3=new java.sql.Date(ms1);
        System.out.println("sql date::"+ud3);
        
        String d4="1998-10-05";
        java.sql.Date sqd4=java.sql.Date.valueOf(d4);
        System.out.println("sql date::"+sqd4);   */
        
        //converting java.sql.Date to string values our required pattarn
        SimpleDateFormat sdf1=new SimpleDateFormat("MMM-dd-yyyy");
        String sd2=sdf1.format(sqd1);
        System.out.println("String date::"+sd2);
      
        
        //coverting java.sql.Timestamp to String values
        long ms1=sqd1.getTime();
        java.util.Date ud2=new java.util.Date(ms);
        SimpleDateFormat sdf2=new SimpleDateFormat("hh:mm:ss MMM-dd-yyyy");
        String sd3=sdf1.format(ud2);
        System.out.println("String Timestamp::"+sd3);
        
        
        
	}
	

}
