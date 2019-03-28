package db;

import java.sql.*;
import java.util.Properties;

public class Connector {
    Statement stmt;
    Connection conn;
    static String url, database, username, password, hostname, port, driver;

    public Connector(Properties properties, String pass) {
        driver = "oracle.jdbc.driver.OracleDriver";
        database = properties.getProperty("database");
        username = properties.getProperty("username");
        password=pass;
        hostname = properties.getProperty("hostname");
        port = properties.getProperty("port");
        url = "jdbc:oracle:thin:@"+hostname+":"+port+":"+database;
    }

    public boolean open() throws Exception{
        Class.forName(driver);
        try {
            /*OracleDataSource ods = new OracleDataSource();
            ods.setURL(url);
            ods.setUser(username);
            ods.setPassword(password);
            conn = ods.getConnection();*/
            conn = DriverManager.getConnection(url,username,password);
        } catch (Exception e){
            e.printStackTrace();
        }
        if(conn == null)
            return false;
        stmt=conn.createStatement();
        System.out.println("Connected!");
        return true;
    }

    public ResultSet executeQuery(String s) throws SQLException{
        return stmt.executeQuery(s);
    }

    public void executeUpdate(String s) throws SQLException{
        stmt.executeUpdate(s);
    }
}
