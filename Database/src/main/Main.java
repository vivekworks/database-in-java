package main;

import db.ConnectDialog;
import db.Connector;
import db.Database;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Database");
        Properties props = new Properties();
        ConnectDialog conn = new ConnectDialog(frame,"DB Connector",props);
        conn.setVisible(true);
        if(conn.isCancelled)
            System.exit(0);
        Connector connector = new Connector(conn.getProps(), new String(conn.pwd.getPassword()));
        if(!connector.open())
            System.exit(0);
        frame.getContentPane().add(new Database());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750,800);
        frame.setVisible(true);
    }
}
