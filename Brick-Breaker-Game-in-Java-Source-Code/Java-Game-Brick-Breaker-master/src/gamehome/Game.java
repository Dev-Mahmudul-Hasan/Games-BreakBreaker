package gamehome;

import com.sun.tools.javac.parser.Tokens;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Game {

    public static void main(String[] args) {

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
        b1.setBounds(180, 170, 80, 30);
        f.add(b1);
        b2 = new JButton("Login");
        b2.setBounds(180, 135, 80, 30);
        f.add(b2);
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        f.getContentPane().add(b1);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new registration();

            }
        });
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        f.getContentPane().add(b2);

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Connection con = null;
                    String dbname = "robot";
                    String mysql_username = "root";
                    String mysql_password = "";

                    try {

                        Class.forName("com.mysql.cj.jdbc.Driver");
                        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbname, mysql_username, mysql_password);
                        System.out.println("Successfully");
                    } catch (Exception e) {
                        System.out.println("Error: unable to connect to database!");
                    }
                    Connection conn = con;
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM `sign` WHERE 1");
                    String name = t1.getText();
                    String id = t2.getText();

                    while (rs.next()) {

                        if (rs.getString(1).equals(name) && rs.getString(2).equals(id)) {
                            f.dispose();
                            JFrame obj = new JFrame();
                            Gameplay gamePlay = new Gameplay();

                            obj.setBounds(10, 10, 700, 600);
                            obj.setTitle("Breakout Ball");
                            obj.setResizable(false);
                            obj.setVisible(true);
                            obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            obj.add(gamePlay);

                        }

                        System.out.println(rs.getString(1) + "  " + rs.getInt(2));
                    }

                    conn.close();
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
        });

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(430, 300);
        f.setLayout(null);
        f.setVisible(true);
        f.setResizable(false);

//insert or write value in database
    }

}
