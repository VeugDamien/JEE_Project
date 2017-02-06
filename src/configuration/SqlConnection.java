package configuration;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class SqlConnection {

	private static SqlConnection instance;
	private Properties props;
	
	private SqlConnection() {
		InputStream input = null;
		props = new Properties();
		
		try {
			input = SqlConnection.class.getClassLoader().getResourceAsStream("/configuration/database.properties");
			props.load(input);
			
			Class.forName(props.getProperty("database.driver"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	public static SqlConnection getInstance(){
		synchronized (SqlConnection.class) {
			if (null == instance){
				instance = new SqlConnection();
			}	
		}
		return instance;
	}
	
	public Connection getconnection(){
		Connection connection = null;
		
		try{
			connection = DriverManager.getConnection(props.getProperty("database.url"),props.getProperty("database.user"),props.getProperty("database.password"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public boolean releaseDBResource(AutoCloseable resource) {
	    boolean status = false;

	    try {
	      if (resource != null) {
	        resource.close();
	      }
	      status = true;
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return status;
	  }
}
