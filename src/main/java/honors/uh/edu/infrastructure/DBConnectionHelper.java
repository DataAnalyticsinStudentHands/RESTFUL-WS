package honors.uh.edu.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnectionHelper
{
	private String url;
	private static DBConnectionHelper instance;

	private DBConnectionHelper()
	{
    	String driver = null;
		try {			
				Class.forName("com.mysql.jdbc.Driver");
				url = ("jdbc:mysql://localhost/testApp?"
			              + "user=sqluser&password=iXRu4Kyv");       		        
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}	

	public static Connection getConnection() throws SQLException {
		if (instance == null) {
			instance = new DBConnectionHelper();
		}
		try {
			return DriverManager.getConnection(instance.url);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public static void close(Connection connection)
	{
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}