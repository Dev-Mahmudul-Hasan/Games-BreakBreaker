package gamehome;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class registration {

    public registration() {

        JTextField t1, t2, t3;
        JLabel a1, a2, a3, a4, a5, a6, a7;
        JButton b1, b2, b3;
        JFrame f = new JFrame();

        a1 = new JLabel("NAME:");
        a1.setBounds(100, 37, 50, 40);
        f.add(a1);

        t1 = new JTextField();
        t1.setBounds(150, 50, 150, 20);
        f.add(t1);

        a1 = new JLabel("PASS:");
        a1.setBounds(100, 87, 50, 40);
        f.add(a1);

        t2 = new JTextField();
        t2.setBounds(150, 100, 150, 20);
        f.add(t2);

        b1 = new JButton("Signup");
        b1.setBounds(180, 135, 80, 30);
        f.add(b1);

        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        f.getContentPane().add(b1);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                Connection con = null;
                String dbname = "robot";
                String mysql_username = "root";
                String mysql_password = "";

                try {

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbname, mysql_username, mysql_password);
                    System.out.println("Successfully");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Database Not Connected");
                }
                String name = t1.getText();
                String id = t2.getText();

                try {
                    String query = "insert into sign(name,id) values ('" + name + "','" + id + "')";
                    PreparedStatement preparedStmt = con.prepareStatement(query);
                    preparedStmt.execute();
                    JOptionPane.showMessageDialog(null, "Registration Completed");
                    f.dispose();

                } catch (SQLException | HeadlessException e) {
                    JOptionPane.showMessageDialog(null, e);
                }

            }
        });

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(430, 300);
        f.setLayout(null);
        f.setVisible(true);
        f.setResizable(false);
    }

}
