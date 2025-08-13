package bank.management.system;

import javax.swing.*;
        import java.awt.*;
        import java.awt.event.*;
        import java.util.*;
        import java.sql.*;

public class Signup3 extends JFrame implements ActionListener {
    JRadioButton r1, r2, r3, r4;
    JCheckBox c1, c2, c3, c4, c5, c6;
    JButton submit, cancel;
    String formno;

    Signup3(String formno) {
        this.formno = formno;
        setLayout(null);

        JLabel l1 = new JLabel("Page 3: Account Details");
        l1.setFont(new Font("Raleway", Font.BOLD, 22));
        l1.setBounds(280, 40, 400, 40);
        add(l1);

        JLabel type = new JLabel("Account Type:");
        type.setFont(new Font("Raleway", Font.BOLD, 18));
        type.setBounds(100, 140, 200, 30);
        add(type);

        r1 = new JRadioButton("Saving Account");
        r1.setFont(new Font("Raleway", Font.PLAIN, 16));
        r1.setBounds(100, 180, 200, 30);
        add(r1);

        r2 = new JRadioButton("Fixed Deposit Account");
        r2.setFont(new Font("Raleway", Font.PLAIN, 16));
        r2.setBounds(350, 180, 250, 30);
        add(r2);

        r3 = new JRadioButton("Current Account");
        r3.setFont(new Font("Raleway", Font.PLAIN, 16));
        r3.setBounds(100, 220, 200, 30);
        add(r3);

        r4 = new JRadioButton("Recurring Deposit Account");
        r4.setFont(new Font("Raleway", Font.PLAIN, 16));
        r4.setBounds(350, 220, 250, 30);
        add(r4);

        ButtonGroup groupAccount = new ButtonGroup();
        groupAccount.add(r1);
        groupAccount.add(r2);
        groupAccount.add(r3);
        groupAccount.add(r4);

        JLabel card = new JLabel("Card Number:");
        card.setFont(new Font("Raleway", Font.BOLD, 18));
        card.setBounds(100, 300, 200, 30);
        add(card);

        JLabel number = new JLabel("XXXX-XXXX-XXXX-1234");
        number.setFont(new Font("Raleway", Font.BOLD, 18));
        number.setBounds(330, 300, 300, 30);
        add(number);

        JLabel pin = new JLabel("PIN:");
        pin.setFont(new Font("Raleway", Font.BOLD, 18));
        pin.setBounds(100, 350, 200, 30);
        add(pin);

        JLabel pnumber = new JLabel("XXXX");
        pnumber.setFont(new Font("Raleway", Font.BOLD, 18));
        pnumber.setBounds(330, 350, 200, 30);
        add(pnumber);

        JLabel services = new JLabel("Services Required:");
        services.setFont(new Font("Raleway", Font.BOLD, 18));
        services.setBounds(100, 420, 200, 30);
        add(services);

        c1 = new JCheckBox("ATM CARD");
        c1.setFont(new Font("Raleway", Font.PLAIN, 16));
        c1.setBounds(100, 460, 200, 30);
        add(c1);

        c2 = new JCheckBox("Internet Banking");
        c2.setFont(new Font("Raleway", Font.PLAIN, 16));
        c2.setBounds(350, 460, 200, 30);
        add(c2);

        c3 = new JCheckBox("Mobile Banking");
        c3.setFont(new Font("Raleway", Font.PLAIN, 16));
        c3.setBounds(100, 500, 200, 30);
        add(c3);

        c4 = new JCheckBox("EMAIL Alerts");
        c4.setFont(new Font("Raleway", Font.PLAIN, 16));
        c4.setBounds(350, 500, 200, 30);
        add(c4);

        c5 = new JCheckBox("Cheque Book");
        c5.setFont(new Font("Raleway", Font.PLAIN, 16));
        c5.setBounds(100, 540, 200, 30);
        add(c5);

        c6 = new JCheckBox("E-Statement");
        c6.setFont(new Font("Raleway", Font.PLAIN, 16));
        c6.setBounds(350, 540, 200, 30);
        add(c6);

        submit = new JButton("Submit");
        submit.setBounds(220, 600, 100, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(420, 600, 100, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        getContentPane().setBackground(Color.WHITE);
        setSize(850, 800);
        setLocation(350, 10);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String accountType = null;
            if (r1.isSelected()) accountType = "Saving Account";
            else if (r2.isSelected()) accountType = "Fixed Deposit Account";
            else if (r3.isSelected()) accountType = "Current Account";
            else if (r4.isSelected()) accountType = "Recurring Deposit Account";

            Random random = new Random();
            String cardNumber = "" + Math.abs((random.nextLong() % 90000000L) + 5040936000000000L);
            String pinNumber = "" + Math.abs((random.nextLong() % 9000L) + 1000L);

            String facility = "";
            if (c1.isSelected()) facility += "ATM Card, ";
            if (c2.isSelected()) facility += "Internet Banking, ";
            if (c3.isSelected()) facility += "Mobile Banking, ";
            if (c4.isSelected()) facility += "EMAIL Alerts, ";
            if (c5.isSelected()) facility += "Cheque Book, ";
            if (c6.isSelected()) facility += "E-Statement";

            try {
                Conn c = new Conn();
                // Signupthree insert
                String query = "INSERT INTO signupthree (form_no, account_type, card_number, pin, facility) " +
                        "VALUES ('" + formno + "', '" + accountType + "', '" + cardNumber + "', '" + pinNumber + "', '" + facility + "')";

// Login insert (assuming your login table columns are: form_no, card_number, pin)
                String loginquery = "INSERT INTO login (form_no, card_number, pin) " +
                        "VALUES ('" + formno + "', '" + cardNumber + "', '" + pinNumber + "')";

                c.connection.createStatement().executeUpdate(query);
                c.connection.createStatement().executeUpdate(loginquery);

                JOptionPane.showMessageDialog(null, "Card Number: " + cardNumber + "\nPIN: " + pinNumber);

                setVisible(false);
                //new Deposit(pinNumber).setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
            new Login().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Signup3("");
    }
}

