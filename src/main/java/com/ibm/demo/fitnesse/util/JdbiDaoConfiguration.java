package com.ibm.demo.fitnesse.util;

import java.sql.SQLException;

import org.skife.jdbi.v2.DBI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.demo.fitnesse.config.FitnesseConfiguration;

//import oracle.jdbc.pool.OracleDataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

@Component
public class JdbiDaoConfiguration {
	
	@Autowired
	private FitnesseConfiguration fitnesseConfiguration;
	
	private DBI dbi;
		
	private DBI getJdbiSession() throws SQLException{
		
		if(dbi == null){
			
			MysqlDataSource mysqlDatasource = new MysqlDataSource();
		    mysqlDatasource.setURL(fitnesseConfiguration.getDbUrl());
		    mysqlDatasource.setUser(fitnesseConfiguration.getDbUser());
		    mysqlDatasource.setPassword(fitnesseConfiguration.getDbPassword());
			
		    dbi = new DBI(mysqlDatasource); 
		}
		
	    return dbi;
	} 
	
	public <T> T getDao(Class<T> clazz) throws SQLException {
		DBI jdbi = getJdbiSession();
		return jdbi.onDemand(clazz);
	}
}
