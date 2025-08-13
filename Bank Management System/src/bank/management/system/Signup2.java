package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Signup2 extends JFrame implements ActionListener {

    JComboBox<String> religionBox, categoryBox, incomeBox, educationBox, occupationBox;
    JTextField panField, aadharField;
    JRadioButton seniorYes, seniorNo, existingYes, existingNo;
    JButton nextButton;
    String formno;

    Signup2(String formno) {
        this.formno = formno;

        setLayout(null);
        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2");

        JLabel additionalDetails = new JLabel("Page 2: Additional Details");
        additionalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        additionalDetails.setBounds(290, 80, 400, 30);
        add(additionalDetails);

        // Religion
        JLabel religion = new JLabel("Religion:");
        religion.setFont(new Font("Raleway", Font.BOLD, 18));
        religion.setBounds(100, 140, 100, 30);
        add(religion);

        String[] valReligion = {"Hindu", "Muslim", "Sikh", "Christian", "Other"};
        religionBox = new JComboBox<>(valReligion);
        religionBox.setBounds(300, 140, 400, 30);
        add(religionBox);

        // Category
        JLabel category = new JLabel("Category:");
        category.setFont(new Font("Raleway", Font.BOLD, 18));
        category.setBounds(100, 190, 200, 30);
        add(category);

        String[] valCategory = {"General", "OBC", "SC", "ST", "Other"};
        categoryBox = new JComboBox<>(valCategory);
        categoryBox.setBounds(300, 190, 400, 30);
        add(categoryBox);

        // Income
        JLabel income = new JLabel("Income:");
        income.setFont(new Font("Raleway", Font.BOLD, 18));
        income.setBounds(100, 240, 200, 30);
        add(income);

        String[] valIncome = {"Null", "<1,50,000", "<2,50,000", "<5,00,000", "Upto 10,00,000", "Above 10,00,000"};
        incomeBox = new JComboBox<>(valIncome);
        incomeBox.setBounds(300, 240, 400, 30);
        add(incomeBox);

        // Education
        JLabel education = new JLabel("Educational");
        education.setFont(new Font("Raleway", Font.BOLD, 18));
        education.setBounds(100, 290, 200, 30);
        add(education);

        JLabel qualification = new JLabel("Qualification:");
        qualification.setFont(new Font("Raleway", Font.BOLD, 18));
        qualification.setBounds(100, 315, 200, 30);
        add(qualification);

        String[] valEducation = {"Non-Graduate", "Graduate", "Post-Graduate", "Doctrate", "Others"};
        educationBox = new JComboBox<>(valEducation);
        educationBox.setBounds(300, 315, 400, 30);
        add(educationBox);

        // Occupation
        JLabel occupation = new JLabel("Occupation:");
        occupation.setFont(new Font("Raleway", Font.BOLD, 18));
        occupation.setBounds(100, 390, 200, 30);
        add(occupation);

        String[] valOccupation = {"Salaried", "Self-Employed", "Business", "Student", "Retired", "Others"};
        occupationBox = new JComboBox<>(valOccupation);
        occupationBox.setBounds(300, 390, 400, 30);
        add(occupationBox);

        // PAN
        JLabel pan = new JLabel("PAN Number:");
        pan.setFont(new Font("Raleway", Font.BOLD, 18));
        pan.setBounds(100, 440, 200, 30);
        add(pan);

        panField = new JTextField();
        panField.setFont(new Font("Raleway", Font.BOLD, 14));
        panField.setBounds(300, 440, 400, 30);
        add(panField);

        // Aadhar
        JLabel aadhar = new JLabel("Aadhar Number:");
        aadhar.setFont(new Font("Raleway", Font.BOLD, 18));
        aadhar.setBounds(100, 490, 200, 30);
        add(aadhar);

        aadharField = new JTextField();
        aadharField.setFont(new Font("Raleway", Font.BOLD, 14));
        aadharField.setBounds(300, 490, 400, 30);
        add(aadharField);

        // Senior Citizen
        JLabel seniorCitizen = new JLabel("Senior Citizen:");
        seniorCitizen.setFont(new Font("Raleway", Font.BOLD, 18));
        seniorCitizen.setBounds(100, 540, 200, 30);
        add(seniorCitizen);

        seniorYes = new JRadioButton("Yes");
        seniorYes.setBounds(300, 540, 100, 30);
        seniorYes.setBackground(Color.WHITE);
        add(seniorYes);

        seniorNo = new JRadioButton("No");
        seniorNo.setBounds(450, 540, 100, 30);
        seniorNo.setBackground(Color.WHITE);
        add(seniorNo);

        ButtonGroup seniorGroup = new ButtonGroup();
        seniorGroup.add(seniorYes);
        seniorGroup.add(seniorNo);

        // Existing Account
        JLabel existingAccount = new JLabel("Existing Account:");
        existingAccount.setFont(new Font("Raleway", Font.BOLD, 18));
        existingAccount.setBounds(100, 590, 200, 30);
        add(existingAccount);

        existingYes = new JRadioButton("Yes");
        existingYes.setBounds(300, 590, 100, 30);
        existingYes.setBackground(Color.WHITE);
        add(existingYes);

        existingNo = new JRadioButton("No");
        existingNo.setBounds(450, 590, 100, 30);
        existingNo.setBackground(Color.WHITE);
        add(existingNo);

        ButtonGroup existingGroup = new ButtonGroup();
        existingGroup.add(existingYes);
        existingGroup.add(existingNo);

        // Next Button
        nextButton = new JButton("Next");
        nextButton.setBackground(Color.BLACK);
        nextButton.setForeground(Color.WHITE);
        nextButton.setFont(new Font("Raleway", Font.BOLD, 14));
        nextButton.setBounds(620, 660, 80, 30);
        nextButton.addActionListener(this);
        add(nextButton);

        getContentPane().setBackground(Color.WHITE);
        setSize(850, 800);
        setLocation(350, 10);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String religion = (String) religionBox.getSelectedItem();
        String category = (String) categoryBox.getSelectedItem();
        String income = (String) incomeBox.getSelectedItem();
        String education = (String) educationBox.getSelectedItem();
        String occupation = (String) occupationBox.getSelectedItem();

        String pan = panField.getText();
        String aadhar = aadharField.getText();

        String senior = (seniorYes.isSelected()) ? "Yes" : "No";
        String existing = (existingYes.isSelected()) ? "Yes" : "No";

        try {
            Conn c = new Conn();
            String query = "INSERT INTO signuptwo VALUES('" + formno + "','" + religion + "','" + category + "','" + income + "','" + education + "','" + occupation + "','" + pan + "','" + aadhar + "','" + senior + "','" + existing + "')";
            c.connection.createStatement().executeUpdate(query);

            // Go to Signup3
            setVisible(false);
            new Signup3(formno).setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Signup2("");
    }
}

