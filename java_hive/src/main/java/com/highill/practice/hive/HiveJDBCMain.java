package com.highill.practice.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HiveJDBCMain {

	private static final String DRIVER_NAME = "org.apache.hive.jdbc.HiveDriver";

	/**
	 * print ResultSet
	 * 
	 * @param resultSet
	 * @throws SQLException
	 */
	public static void printResultSet(ResultSet resultSet) throws SQLException
	{
		if (resultSet != null)
		{
			System.out.println("----- ----- resultSet=" + resultSet);

			ResultSetMetaData resultMeta = resultSet.getMetaData();
			int columnCount = resultMeta.getColumnCount();
			ArrayList<String> columnNameList = new ArrayList<String>();

			for (int columnPosition = 1; columnPosition <= columnCount; columnPosition++)
			{
				columnNameList.add(resultMeta.getColumnName(columnPosition));
			}

			for (; resultSet.next();)
			{
				for (int dataPosition = 1; dataPosition <= columnCount; dataPosition++)
				{
					String columnName = columnNameList.get(dataPosition - 1);
					String columnValue = resultSet.getString(dataPosition);
					System.out.print(" " + columnName + "=" + columnValue + "    ");
				}
				System.out.println();

			}
		} else
		{
			System.out.println("----- ----- resultSet is null");
		}
	}

	public static void main(String[] args)
	{

		try
		{
			Class.forName(DRIVER_NAME);
			Connection connection = DriverManager.getConnection("jdbc:hive2://192.168.1.100:10000/default", "", "");
			System.out.println("----- ----- connection=" + connection);

			// create and drop table
			Statement statement = connection.createStatement();
			String tableName = "java_hive_data1";
			statement.execute("drop table if exists " + tableName);
			statement.execute("create table " + tableName + "(id int, name string, phone string)");
			ResultSet showResult = statement.executeQuery("show tables '" + tableName + "'");
			printResultSet(showResult);

			// select exist table
			ResultSet selectResult = statement.executeQuery("select * from hive_test1");
			printResultSet(selectResult);

			ResultSet selectResult2 = statement.executeQuery("select * from hive_test1 where name = 'tName3'");
			printResultSet(selectResult2);

		} catch (ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
