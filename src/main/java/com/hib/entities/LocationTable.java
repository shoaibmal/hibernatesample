package com.hib.entities;

import java.sql.SQLException;

import org.apache.log4j.Logger;

public class LocationTable {
	
public int getClientID(String dasherooId){
	int clientId = 0;
	 try {
		resultSet = statement
			      .executeQuery("select Client_ID from location_table WHERE Dasheroo_ID='"+dasherooId+"'");
		if(resultSet.next()){
			clientId =  resultSet.getInt(1);	
		}
	} catch (SQLException e) {
		log.fatal(e);
		e.printStackTrace();
	}
	 return clientId;
}
}
