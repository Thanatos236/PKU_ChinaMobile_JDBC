/**
 * PKUStatement - This class implements the java.sql.Statement Interface and act as the 
 * Statement Object of JDBC to the client. It communicate with the PKUStatementServer on
 * the server by RMI. 
 */

package com.pku.cis.PKU_ChinaMobile_JDBC.Client;

import java.sql.*;
import java.rmi.*;

import com.pku.cis.PKU_ChinaMobile_JDBC.Interface.PKUResultSetInterface;
import com.pku.cis.PKU_ChinaMobile_JDBC.Interface.PKUStatementInterface;

// test for git
/**
 * A Statement object is used for executing a static SQL statement and obtaining
 * the results produced by it.
 * 
 * <p>
 * Only one ResultSet per Statement can be open at any point in time. Therefore,
 * if the reading of one ResultSet is interleaved with the reading of another,
 * each must have been generated by different Statements. All statement execute
 * methods implicitly close a statement's current ResultSet if an open one
 * exists.
 * </p>
 * 
 * @see java.sql.Statement
 * @see ResultSet
 */
public class PKUStatement implements java.sql.Statement
{
	//Remote Statement
	private PKUStatementInterface remoteStmt;

	/**
	 * Constructor for creating the PKUStatement instance with PKUStatementInterface
	 */
	public PKUStatement(PKUStatementInterface stmt)
	{
		remoteStmt = stmt;
	}

	/**
	 * Execute a SQL statement that returns a single ResultSet
	 * 
	 * This method executes the SQL query via Remote Statement and then
	 * returns the PKUResultSet holding remote ResultSet.
	 * 
	 * @param sql
	 *            typically a static SQL SELECT statement
	 * 
	 * @return a ResulSet that contains the data produced by the query
	 * 
	 * @exception SQLException
	 *                if a database access error occurs
	 */
	public ResultSet executeQuery(String sqlQuery)
	  throws SQLException
	{
		try
		{
			PKUResultSetInterface remoteRsInstance = (PKUResultSetInterface)remoteStmt.executeQuery(sqlQuery);
			PKUResultSet localRsInstance = new PKUResultSet(remoteRsInstance);
			return (ResultSet)localRsInstance;
		}
		catch(RemoteException ex)
		{
			throw(new SQLException(ex.getMessage()));
		}
	}
	
	/**
	 * Execute a SQL INSERT, UPDATE or DELETE statement. In addition SQL
	 * statements that return nothing such as SQL DDL statements can be executed
	 * 
	 * This method executes the SQL data write/modify statement via Remote Statement 
	 * 
	 * @param sql
	 *            a SQL statement
	 * 
	 * @return either a row count, or 0 for SQL commands
	 * 
	 * @exception SQLException
	 *                if a database access error occurs
	 */
	public int executeUpdate(String sqlQuery)
		  throws SQLException
	{
		try
		{
			int updateCount = remoteStmt.executeUpdate(sqlQuery);
			return updateCount;
		}
		catch(RemoteException ex)
		{
			throw(new SQLException(ex.getMessage()));
		}
	}
	
	public boolean execute(String sqlQuery)
			  throws SQLException
	{
		try
		{
			boolean result = remoteStmt.execute(sqlQuery);
			return result;
		}
		catch(RemoteException ex)
		{
			throw(new SQLException(ex.getMessage()));
		}
	}

	/**
	 * In many cases, it is desirable to immediately release a Statement's
	 * database and JDBC resources instead of waiting for this to happen when it
	 * is automatically closed. The close method provides this immediate
	 * release.
	 * 
	 * <p>
	 * <B>Note:</B> A Statement is automatically closed when it is garbage
	 * collected. When a Statement is closed, its current ResultSet, if one
	 * exists, is also closed.
	 * </p>
	 * 
	 * @exception SQLException
	 *                if a database access error occurs
	 */
	public void close() throws SQLException
	{
		try
		{
			remoteStmt.close();
		}
		catch(RemoteException ex)
		{
			throw(new SQLException(ex.getMessage()));
		}
	}

	////////////////////////////////////////////////////////////////////////////////////
	//Not supported methods
	
	public int getMaxFieldSize() throws SQLException
	{
		throw(new SQLException("Not Supported"));
	}

	public void setMaxFieldSize(int max)
		throws SQLException
	{
		throw(new SQLException("Not Supported"));
	}

	public int getMaxRows() throws SQLException
	{
		throw(new SQLException("Not Supported"));
	}

	public void setMaxRows(int max)
		throws SQLException
	{
		throw(new SQLException("Not Supported"));
	}

	public void setEscapeProcessing(boolean enable)
			throws SQLException
	{
		throw(new SQLException("Not Supported"));
	}

	public int getQueryTimeout()throws SQLException
	{
		throw(new SQLException("Not Supported"));
	}

	public void setQueryTimeout(int seconds)
		 throws SQLException
	{
		throw(new SQLException("Not Supported"));
	}

	public void cancel() throws SQLException
	{
		throw(new SQLException("Not Supported"));
	}

	public SQLWarning getWarnings() throws SQLException
	{
		throw(new SQLException("Not Supported"));
	}

	public void clearWarnings() throws SQLException
	{
		throw(new SQLException("Not Supported"));
	}

	public void setCursorName(String name)
						   throws SQLException
	{
		throw(new SQLException("Not Supported"));
	}


	public ResultSet getResultSet()
	  			   throws SQLException
	{
		throw(new SQLException("Not Supported"));
	}

	public int getUpdateCount()
	  			   throws SQLException
	{
		throw(new SQLException("Not Supported"));
	}

	public boolean getMoreResults()
			throws SQLException
	{
		throw(new SQLException("Not Supported"));
	}

	public void setFetchDirection(int direction)
			throws SQLException
	{
		throw(new SQLException("Not Supported"));
	}

	public int getFetchDirection()
	                      throws SQLException
	{
		throw(new SQLException("Not Supported"));
	}

	public void setFetchSize(int rows)
	  				throws SQLException
	{
		throw(new SQLException("Not Supported"));
	}

	public int getFetchSize() throws SQLException
	{
	   throw(new SQLException("Not Supported"));
	}

	public int getResultSetConcurrency()
	  						   throws SQLException
	{
		throw(new SQLException("Not Supported"));
	}

	public int getResultSetType() throws SQLException
	{
  		throw(new SQLException("Not Supported"));
	}

	public void addBatch(String sql)
		throws SQLException
	{
		throw(new SQLException("Not Supported"));
	}

	public void clearBatch()
		throws SQLException
	{
		throw(new SQLException("Not Supported"));
	}

	public int[] executeBatch()
		   throws SQLException
	{
		throw(new SQLException("Not Supported"));
	}

	public Connection getConnection()
		 throws SQLException
	{
		throw(new SQLException("Not Supported"));
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void closeOnCompletion() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean execute(String arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean execute(String arg0, int[] arg1) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean execute(String arg0, String[] arg1) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int executeUpdate(String arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int executeUpdate(String arg0, int[] arg1) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int executeUpdate(String arg0, String[] arg1) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ResultSet getGeneratedKeys() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getMoreResults(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getResultSetHoldability() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isCloseOnCompletion() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isClosed() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPoolable() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setPoolable(boolean arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}
}
