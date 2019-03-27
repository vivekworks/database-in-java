package db;

import java.sql.*;
import java.util.Properties;

public class Connector {
    Statement stmt;
    Connection conn;
    static String url, database, username, password, hostname, port, driver;

    public Connector(Properties properties, String pass) throws SQLException {
        driver = "oracle.jdbc.driver.OracleDriver";
        database = properties.getProperty("database");
        username = properties.getProperty("username");
        password=pass;
        hostname = properties.getProperty("hostname");
        port = properties.getProperty("port");
        url = "jdbc:oracle:thin:@"+hostname+":"+port+":"+database;
    }

    public boolean open() throws Exception{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        System.out.println("Driver loaded");
        try {
            conn = DriverManager.getConnection(driver,username,password);
        } catch (Exception e){
            e.printStackTrace();
        }

        if(conn == null)
            return false;
        stmt = conn.createStatement();
        System.out.println("Connected!");
        return true;
    }
}
