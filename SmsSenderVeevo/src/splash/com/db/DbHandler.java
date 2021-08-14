package splash.com.db;

import splash.com.runner.Application;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DbHandler {

    DbConnection connection;
    Connection connectionDb;


    public DbHandler(DbConnection connection) {
        this.connection = connection;
    }

    public Connection getConnection() throws SQLException {


        if(connection==null){
        return null;
        }else{
            if (connectionDb==null){
                connectionDb=  DriverManager. getConnection(connection.getConnectionString(), connection.getUserName(), connection.getPassword());
            }

         return connectionDb;
        }

    }

    public PreparedStatement getStatement(String query) throws SQLException,Exception {

        if(query!=null){
           PreparedStatement statement= getConnection().prepareStatement(query);
           return statement;
        }else{
            throw new Exception("Empty or Null Query");

        }
    }


    public List<SmsModel> fetchUnsendSms() throws Exception {


        PreparedStatement statement= Application.getInstance().preparedStatement;
        ResultSet resultSet=   statement.executeQuery();
        List<SmsModel> list=new ArrayList<>();
//        statement.close();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            int userid= resultSet.getInt("userid");
            String phoneno=resultSet.getString("phoneno");
            String msg = resultSet.getString("smstext");
            String status = resultSet.getString("status");
            Date date= resultSet.getDate("senttime");
            String response = resultSet.getString("response");

            list.add(new SmsModel(id,userid,phoneno,msg,status,date,response));

        }
        resultSet.close();
        return  list;


    }

    public void updateSmsSender(int id ,String status, String response ) throws Exception {
        String query = " update  worthywa_splash.sms set senttime = current_time() ,status = '"+status +"' ,response= '"+response +"' where id ='"+id+"'";
           PreparedStatement statement=getStatement(query);
           statement.executeUpdate();
           statement.close();




    }


}




