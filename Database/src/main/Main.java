package main;

import db.ConnectDialog;
import db.Database;

import javax.swing.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args){
        JFrame frame = new JFrame("Database");
        Properties props = new Properties();
        ConnectDialog conn = new ConnectDialog(frame,"DB Connector",props);
        conn.setVisible(true);
        if(conn.isCancelled)
            System.exit(0);
        frame.getContentPane().add(new Database());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750,800);
        frame.setVisible(true);
    }
}
