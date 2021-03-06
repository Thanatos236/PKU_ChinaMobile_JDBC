/**
 * PKUConnection - This class implements the java.sql.Connection Interface and act as the 
 * Connection Object of JDBC to the client. It communicate with the PKUConnectionServer on
 * the server by RMI. 
 */
package com.pku.cis.PKU_ChinaMobile_JDBC.Client;

import java.sql.*;
import java.util.*;
import java.util.concurrent.Executor;
import java.rmi.*;

import com.pku.cis.PKU_ChinaMobile_JDBC.Interface.PKUConnectionInterface;
import com.pku.cis.PKU_ChinaMobile_JDBC.Interface.PKUStatementInterface;

/**
 * A Connection represents a session with a specific database. Within the
 * context of a Connection, SQL statements are executed and results are
 * returned.
 * <P>
 * A Connection's database is able to provide information describing its tables,
 * its supported SQL grammar, its stored procedures, the capabilities of this
 * connection, etc. This information is obtained with the getMetaData method.
 * </p>
 * 
 * @see java.sql.Connection
 */
public class PKUConnection implements Connection
{
	//remote Connection object
	private PKUConnectionInterface  remoteConnection; 
	
	/**
	 * constructor for creating the PKUConnection instance with PKUConnectionInterface
	 */
	public PKUConnection(PKUConnectionInterface remCon)
	{
		remoteConnection = remCon;
	}

	/**
	 * Set special type of database to connect
	 * 0-all
	 * 1-oracle
	 * 2-mysql
	 * 3-teradata
	 * 4-hive
	 * @param index
	 * @throws SQLException
	 */
	public void setDst(int index) throws SQLException 
	{
		try
		{
		 	remoteConnection.setDst(index);
		}
		catch(RemoteException ex)
		{
			throw ((new SQLException("RemoteException:" + ex.getMessage())));
		}
	}
	/**
	 * SQL statements without parameters are normally executed using Statement
	 * objects. If the same SQL statement is executed many times, it is more
	 * efficient to use a PreparedStatement
	 * 
	 * Here create a remote Statement Object- PKUStatementServer, and return
     * the PKUStatement holding a remote Object.
     * 
	 * @return a new Statement object
	 * @throws SQLException
	 *             passed through from the constructor
	 */
	public Statement createStatement()
	     throws SQLException
	{
		try
		{
			PKUStatementInterface remStmt = (PKUStatementInterface)remoteConnection.createStatement();
			PKUStatement localStmtInstance = new PKUStatement(remStmt);
			return (Statement)localStmtInstance;
		
		}		
		catch(Exception ex)
		{
			throw(new SQLException("RemoteException:" + ex.getMessage()));
		}
	}

	/**
     * In some cases, it is desirable to immediately release a
     * Connection's database and JDBC resources instead of waiting for
     * them to be automatically released; the close method provides this
     * immediate release. 
     *
     * <P><B>Note:</B> A Connection is automatically closed when it is
     * garbage collected. Certain fatal errors also result in a closed
     * Connection.
     */
	public void close() throws SQLException
	{
		try
		{
		 	remoteConnection.closeConnection();
		}
		catch(RemoteException ex)
		{
			throw ((new SQLException("RemoteException:" + ex.getMessage())));
		}
	}

	/**
     * A driver may convert the JDBC sql grammar into its system's
     * native SQL grammar prior to sending it; nativeSQL returns the
     * native form of the statement that the driver would have sent.
     *
     * @param sql a SQL statement that may contain one or more '?'
     * parameter placeholders
     *
     * @return the native form of this statement
     */
	public String nativeSQL(String sql)
	 	throws SQLException
	{
		return sql; //本项目驱动发送给透明网关的sql语句在发送过程中没有改变
	}
	 
	 public void setAutoCommit(boolean autoCommit)
	 	throws SQLException
	{
		 throw(new SQLException("Transaction Not Supported"));
	}

	public boolean getAutoCommit()
	 	throws SQLException
	{
	 	throw(new SQLException("Transaction Not Supported"));
	}

	public void commit() throws SQLException
	{
	 	throw(new SQLException("Transaction Not Supported"));
	}

	public void rollback() throws SQLException
	{
	 	throw(new SQLException("Transaction Not Supported"));
	}

	/**
     * Tests to see if a Connection is closed.
     *
     * @return true if the connection is closed; false if it's still open
     */
	public boolean isClosed()throws SQLException
	{
		try
		{
			return remoteConnection.isClosed();
		}		
		catch(Exception ex)
		{
			throw(new SQLException("RemoteException:" + ex.getMessage()));
		}
	}

	public DatabaseMetaData getMetaData()
         throws SQLException
	{
			throw(new SQLException("Not Supported"));
	}

	/**
     * You can put a connection in read-only mode as a hint to enable 
     * database optimizations.
     *
     * This method will call the responding method of all remote Connection
     * objects, if user hasn't created the Connection object, it will 
     * create all of Connection objects which user is granted.
     * 
     * Only when all connections can be set into read-only mode, we enable
     * read-only mode. Otherwise, we disable read-only mode.
     * 
     * <P><B>Note:</B> setReadOnly cannot be called while in the
     * middle of a transaction.
     *
     * @param readOnly true enables read-only mode; false disables
     * read-only mode.  
     */
	
	public void setReadOnly(boolean readOnly)
	      throws SQLException
	{
		try{
			remoteConnection.setReadOnly(readOnly);
		}catch(RemoteException e){
			throw new java.sql.SQLException(e.getMessage());
		}
	}

	/**
     * Tests to see if the connection is in read-only mode.
     *
     * This method will call the responding method of all remote Connection
     * objects, if user hasn't created the Connection object, it will 
     * create all of Connection objects which user is granted.
     *
     * If one of the Connection objects is not read-only mode, we regard the 
     * connection as not in read-only mode.
     * 
     * @return true if connection is read-only
     */
	public boolean isReadOnly()
	       throws SQLException
	{
		try{
			return remoteConnection.isReadOnly();
		}catch(RemoteException ex){
			throw(new SQLException("RemoteException:" + ex.getMessage()));
		}
	}
	/**
     * A sub-space of this Connection's database may be selected by setting a
     * catalog name. If the driver does not support catalogs it will
     * silently ignore this request.
     * 
     * This method will call the responding method of all remote Connection
     * objects, if user hasn't created the Connection object, it will 
     * create all of Connection objects which user is granted.
     */
	public void setCatalog(String catalog)
           throws SQLException
	{
		try{
			remoteConnection.setCatalog(catalog);
		}catch(RemoteException ex){
			throw(new SQLException("RemoteException:" + ex.getMessage()));
		}
	}
	
	 /**
     * Return the Connection's current catalog name.
     *
     * This method will call the responding method of all remote Connection
     * objects, if user hasn't created the Connection object, it will 
     * create all of Connection objects which user is granted.
     * 
     * @return the current catalog name or null
     */
	public String getCatalog()
	     throws SQLException
	{
		try{
			return remoteConnection.getCatalog();
		}catch(RemoteException ex){
			throw(new SQLException("RemoteException:" + ex.getMessage()));
		}
	}
	
	 /**
     * Transactions are not supported. 
     */
    int TRANSACTION_NONE	     = 0;

    /**
     * Dirty reads, non-repeatable reads and phantom reads can occur.
     */
    int TRANSACTION_READ_UNCOMMITTED = 1;

    /**
     * Dirty reads are prevented; non-repeatable reads and phantom
     * reads can occur.
     */
    int TRANSACTION_READ_COMMITTED   = 2;

    /**
     * Dirty reads and non-repeatable reads are prevented; phantom
     * reads can occur.     
     */
    int TRANSACTION_REPEATABLE_READ  = 4;

    /**
     * Dirty reads, non-repeatable reads and phantom reads are prevented.
     */
    int TRANSACTION_SERIALIZABLE     = 8;
    
	
	public void setTransactionIsolation(int level)
         throws SQLException
	{
			throw(new SQLException("Transaction Not Supported "));
	}
	public int getTransactionIsolation()
	      throws SQLException
	{
			throw(new SQLException("Transaction Not Supported"));
	}

	/**
     * The first warning reported by calls on remote Connection is
     * returned.  
     * 
     * <P><B>Note:</B> Subsequent warnings will be chained to this
     * SQLWarning.
     *
     * @return the first SQLWarning or null 
     */
	public SQLWarning getWarnings()
	      throws SQLException
	{
		try{
			return remoteConnection.getWarnings();
		}catch(RemoteException ex){
			throw(new SQLException("RemoteException:" + ex.getMessage()));
		}
	}

	/**
     * After this call, getWarnings returns null until a new warning is
     * reported for this Connection.  
     */
	public void clearWarnings()
	      throws SQLException
	{
		try{
			remoteConnection.clearWarnings();
		}catch(RemoteException ex){
			throw(new SQLException("RemoteException:" + ex.getMessage()));
		}
	}

	public PreparedStatement prepareStatement(String sql)
	              throws SQLException
	{
		   		 throw(new SQLException("Not Supported"));
	}

	public CallableStatement prepareCall(String sql)
		          throws SQLException
	{
		   	   	 throw(new SQLException("Not Supported"));
	}
	
	public PreparedStatement prepareStatement(String sql,int resultSetType,int resultSetConcurrency)
	         throws SQLException
	{
		throw(new SQLException("Not Supported"));
	}

	public CallableStatement prepareCall(String sql,int resultSetType,int resultSetConcurrency)
	        throws SQLException
	{
		throw(new SQLException("Not Supported"));
	}

	public Statement createStatement(int resultSetType,int resultSetConcurrency)
	         throws SQLException
    {
		throw(new SQLException("Not Supported"));
	}

	private Map typeMap;
	/**
	 * JDBC 2.0 Install a type-map object as the default type-map for this
	 * connection
	 * 
	 * @param map
	 *            the type mapping
	 * @throws SQLException
	 *             if a database error occurs.
	 */
	public synchronized void setTypeMap(java.util.Map map) throws SQLException {
		this.typeMap = map;
	}
	/**
	 * JDBC 2.0 Get the type-map object associated with this connection. By
	 * default, the map returned is empty.
	 * 
	 * @return the type map
	 * @throws SQLException
	 *             if a database error occurs
	 */
	public synchronized java.util.Map getTypeMap() throws SQLException {
		if (this.typeMap == null) {
			this.typeMap = new HashMap();
		}

		return this.typeMap;
	}
	

	//------------------------JDBC 4.0
	@Override
	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void abort(Executor arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Array createArrayOf(String arg0, Object[] arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Blob createBlob() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Clob createClob() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public NClob createNClob() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public SQLXML createSQLXML() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Statement createStatement(int arg0, int arg1, int arg2)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Struct createStruct(String arg0, Object[] arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Properties getClientInfo() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getClientInfo(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getHoldability() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int getNetworkTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public String getSchema() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean isValid(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public CallableStatement prepareCall(String arg0, int arg1, int arg2,
			int arg3) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public PreparedStatement prepareStatement(String arg0, int arg1)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public PreparedStatement prepareStatement(String arg0, int[] arg1)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public PreparedStatement prepareStatement(String arg0, String[] arg1)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public PreparedStatement prepareStatement(String arg0, int arg1, int arg2,
			int arg3) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void releaseSavepoint(Savepoint arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void rollback(Savepoint arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setClientInfo(Properties arg0) throws SQLClientInfoException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setClientInfo(String arg0, String arg1)
			throws SQLClientInfoException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setHoldability(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setNetworkTimeout(Executor arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Savepoint setSavepoint() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Savepoint setSavepoint(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setSchema(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}
}







