package honors.uh.edu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	public Connection getConnection() throws Exception
	{
		try
		{
			final String connectionURL = "jdbc:mysql://localhost:3306/test";
			Connection connection = null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL, "root", "");
			return connection;
		}
		catch (final SQLException e)
		{
			throw e;
		}
		catch (final Exception e)
		{
			throw e;
		}
	}

}

//This is a comment to show how push/merge works
