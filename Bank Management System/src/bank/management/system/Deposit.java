package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {
    String pin;
    TextField textField;
    JButton b1, b2;

    Deposit(String pin) {
        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 1550, 830);
        add(l3);

        JLabel label1 = new JLabel("ENTER AMOUNT TO DEPOSIT");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(460, 220, 400, 35);
        l3.add(label1);

        textField = new TextField();
        textField.setBackground(new Color(65, 125, 128));
        textField.setForeground(Color.WHITE);
        textField.setBounds(460, 260, 320, 25);
        textField.setFont(new Font("Raleway", Font.BOLD, 22));
        l3.add(textField);

        b1 = new JButton("DEPOSIT");
        b1.setBounds(700, 362, 150, 35);
        b1.setBackground(new Color(65, 125, 128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(700, 406, 150, 35);
        b2.setBackground(new Color(65, 125, 128));
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        l3.add(b2);

        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                String amountStr = textField.getText();
                if (amountStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the amount");
                    return;
                }

                double depositAmount = Double.parseDouble(amountStr);
                if (depositAmount <= 0) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid deposit amount");
                    return;
                }

                Conn c = new Conn();

                        // OLD (causing column count mismatch)
// c.statement.executeUpdate(
//     "INSERT INTO bank VALUES('" + pin + "', NOW(), 'Deposit', '" + depositAmount + "')"
// );

// NEW (works with AUTO_INCREMENT id column)
                        c.statement.executeUpdate(
                                "INSERT INTO bank VALUES (NULL, '" + pin + "', NOW(), 'Deposit', '" + depositAmount + "')"
                        );


                JOptionPane.showMessageDialog(null, "Rs. " + depositAmount + " Deposited Successfully");
                setVisible(false);
                new main_Class(pin);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid amount format");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        } else if (e.getSource() == b2) {
            setVisible(false);
            new main_Class(pin);
        }
    }

    public static void main(String[] args) {
        new Deposit("");
    }
}



