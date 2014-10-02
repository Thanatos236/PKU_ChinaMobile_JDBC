/***
 * ConnectionManager: 网关部分
 * 接口：getConnections、close方法，四个变量
 */
package com.pku.cis.PKU_ChinaMobile_JDBC.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionManager {
	public ArrayList<Connection> cons = new ArrayList<Connection>(); //保存真实Connection对象
	public ArrayList<String> dbs = new ArrayList<String>(); //保存对应数据库名
	public User usr;//用户对象
	int conNum;//连接总数
	ConnectionManager(User _usr)
	{
		usr = _usr;
		cons = new ArrayList<Connection>();
	}
	/**
	 * 关闭所有连接即可
	 * @throws SQLException
	 */
	public void close() throws SQLException
	{
		for(int i = 0; i < conNum; i++ )
			cons.get(i).close();
	}
	/**
	 * 要求根据SQLParse解析的结果，判断出需要的连接，建立这些连接保存在数组中返回
	 * @param sp SQLParse对象
	 * @return 连接数组
	 * @throws SQLException
	 */
	public Connection[] getConnections(SQLParse sp) throws SQLException
	{
		sp.getTableName();
		sp.getTimeHint();
		sp.getLocationHint();
		sp.getIPHint();
		conNum = 2;
		
		Connection temp =  (Connection)DriverManager.getConnection(usr.URLS[0], "root", "06948859");
		Connection temp2 =  (Connection)DriverManager.getConnection(usr.URLS[1], "SYSTEM", "oracle1ORACLE");

		cons.add(temp);
		cons.add(temp2);
		dbs.add(usr.dbName[0]);
		dbs.add(usr.dbName[1]);

		return (Connection[])cons.toArray(new Connection[conNum]);
	}
	
}