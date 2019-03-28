package db;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Database extends JPanel {
    JLabel prompt = new JLabel("Please enter your sql query");
    JButton execute = new JButton("Execute");
    JButton reset = new JButton("Reset");
    static JTextArea sqlArea = new JTextArea(10, 70);
    JScrollPane sqlPane = new JScrollPane(sqlArea);
    static JTable resultsTable = new JTable();
    JScrollPane resultPane = new JScrollPane(resultsTable);
    DefaultTableModel model = (DefaultTableModel) resultsTable.getModel();
    static Connector connector;

    public Database(Connector conn) {
        connector = conn;
        resultPane.setPreferredSize(new Dimension(700, 500));
        add(prompt);
        add(sqlPane);
        add(execute);
        add(reset);
        add(resultPane);
        execute.addActionListener(this::action);
        reset.addActionListener(this::action);
    }

    public void action(ActionEvent event) {
        if (event.getSource().equals(execute)) {
            model.setColumnCount(0);
            model.setRowCount(0);
            try {
                String query = sqlArea.getText();
                ResultSet set;
                if (query.length() >= 6 && query.substring(0, 6).equalsIgnoreCase("SELECT")) {
                    set = connector.executeQuery(query);
                    ResultSetMetaData resultSetMD = set.getMetaData();
                    for (int i = 0; i < resultSetMD.getColumnCount(); i++)
                        model.addColumn(resultSetMD.getColumnName(i + 1));
                    while (set.next()) {
                        String[] rowData = new String[resultSetMD.getColumnCount()];
                        for (int i = 0; i < resultSetMD.getColumnCount(); i++)
                            rowData[i] = set.getString(i + 1);
                        model.addRow(rowData);
                    }
                } else
                    connector.executeUpdate(query);
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        } else if (event.getSource().equals(reset)) {
            model.setColumnCount(0);
            model.setRowCount(0);
        }
    }
}
