package splash.com.db;

public class DbConnection {
	
	String driverClass ; 
	String userName; 
	String Password;
	String connectionString;
	public String getDriverClass() {
		return driverClass;
	}
	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getConnectionString() {
		return connectionString;
	}
	public void setConnectionString(String connectionString) {
		this.connectionString = connectionString;
	}
	@Override
	public String toString() {
		return "DbConnection [driverClass=" + driverClass + ", userName=" + userName + ", Password=" + Password
				+ ", connectionString=" + connectionString + "]";
	}
	public DbConnection() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DbConnection(String driverClass, String userName, String password, String connectionString) {
		super();
		this.driverClass = driverClass;
		this.userName = userName;
		Password = password;
		this.connectionString = connectionString;
	}
	
	
	
	
	

}
