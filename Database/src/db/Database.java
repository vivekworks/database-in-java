package db;

import javax.swing.*;
import java.awt.*;

public class Database extends JPanel {
    JLabel prompt = new JLabel("Please enter your sql query");
    JButton execute = new JButton("Execute");
    JButton reset = new JButton("Reset");
    static JTextArea sqlArea = new JTextArea(10,70);
    JScrollPane sqlPane = new JScrollPane(sqlArea);
    static JTable resultsTable = new JTable();
    JScrollPane resultPane = new JScrollPane(resultsTable);

    public Database(){
        resultPane.setPreferredSize(new Dimension(700,500));
        //resultsTable.setSize(300,50);
        add(prompt);
        add(sqlPane);
        add(execute);
        add(reset);
        add(resultPane);
    }
}
