package db;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

public class ConnectDialog extends JDialog implements ActionListener {
    public boolean isCancelled=true;
    JLabel lhost = new JLabel("Host Name");
    JTextField host = new JTextField();
    JLabel lport = new JLabel("Port");
    JTextField port = new JTextField();
    JLabel ldatabase = new JLabel("Database");
    JTextField database = new JTextField();
    JLabel luser = new JLabel("User");
    JTextField user = new JTextField();
    JLabel lpwd = new JLabel("Password");
    public JPasswordField pwd = new JPasswordField();

    JButton ok = new JButton("Ok");
    JButton cancel = new JButton("Cancel");

    Properties props;

    public ConnectDialog(JFrame owner, String title, Properties p){
        super(owner, title, true);
        setSize(300,200);
        setLocation(650,250);
        props = new Properties(p);
        ok.setPreferredSize(new Dimension(75,25));
        ok.addActionListener(this);
        cancel.setPreferredSize(new Dimension(75,25));
        cancel.addActionListener(this);
        JPanel cpanel = new JPanel();
        JPanel cpanel2 = new JPanel();
        cpanel.setLayout(new GridLayout(5,2));
        cpanel.add(lhost);
        cpanel.add(host);
        cpanel.add(lport);
        cpanel.add(port);
        cpanel.add(ldatabase);
        cpanel.add(database);
        cpanel.add(luser);
        cpanel.add(user);
        cpanel.add(lpwd);
        cpanel.add(pwd);
        cpanel2.add(ok);
        cpanel2.add(cancel);
        add(cpanel, BorderLayout.NORTH);
        add(cpanel2, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent event){
        if(event.getSource() == ok)
            isCancelled = false;
        this.dispose();
    }

    public Properties getProps(){
        props.setProperty("database",database.getText());
        props.setProperty("username",user.getText());
        props.setProperty("hostname",host.getText());
        props.setProperty("port",port.getText());
        return props;
    }
}
