package splash.com.runner;

import splash.com.db.DbConnection;
import splash.com.db.DbHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getInstance();
		try {
			instance.readConfig();

		DbHandler handler= new DbHandler(instance.dbConnection);
		instance.preparedStatement=handler.getStatement(Constants.SMSSelectQuery);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException throwables) {
			System.out.println(throwables.getMessage());
			throwables.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		SmsSenderThread thread= new SmsSenderThread();
		exec.scheduleAtFixedRate(thread,0,1, TimeUnit.SECONDS);

	}


	public PreparedStatement preparedStatement;
	public  DbConnection dbConnection;
	public static  Application instance;
	public static Application getInstance() {
		if(instance==null){
			instance = new Application();
		}
		return instance;
	}

	String result = "";
	static InputStream inputStream;
	public  void readConfig() throws IOException {

		dbConnection=new DbConnection();
		try{
		Properties prop = new Properties();

		String propFileName =  "application.properties";

		inputStream = Application.class.getClassLoader().getResourceAsStream(propFileName);

		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}

		Date time = new Date(System.currentTimeMillis());

		// get the property value and print it out
		String dburl = prop.getProperty("Databaseurl");
		String dbusername = prop.getProperty("Databaseusername");
		String dbpassword = prop.getProperty("Databasepassword");


		// System.out.println(dburl +"  , " +dbusername +" , "+dbpassword + "\nProgram Ran on " + time );

		if(!dburl.isEmpty() || !dbusername.isEmpty() || !dbpassword.isEmpty()){
			dbConnection.setConnectionString(dburl);
			dbConnection.setUserName(dbusername);
			dbConnection.setPassword(dbpassword);
		}
	} catch (Exception e) {
		System.out.println("Exception: " + e);
	} finally {
		inputStream.close();
	}
	}
}
