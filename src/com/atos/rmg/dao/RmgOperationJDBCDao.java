package com.atos.rmg.dao;

import java.sql.SQLException;
import java.util.List;

import com.atos.rmg.beans.Summary;

/**
 * 
 * @author A180562
 *
 */
public interface RmgOperationJDBCDao {

	public List<Summary> getSummaryDetails(String dbURL) throws ClassNotFoundException, SQLException;

}
 
