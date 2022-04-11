/*
gui for project.

flow is pretty simple: 
1. enter number of children/adults/whatever in a hamper
2. load database into memory
3. create and print the order (if possible) 
4. remove items and write back into database   


*/

package edu.ucalgary.ensf409;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class Main extends JFrame implements ActionListener, MouseListener {

    private ArrayList<GUIData> guiData = new ArrayList<GUIData>();
    
    private JLabel instructions;
    
    private int adultMales;
    private int adultFemales;
    private int childrenOverEight;
    private int childrenUnderEight;

    private JLabel adultMalesLabel = new JLabel("Adult Males:");
    private JLabel adultFemalesLabel;
    private JLabel childrenOverEightLabel;
    private JLabel childrenUnderEightLabel;

    private ArrayList<JTextField> adultMalesInput = new ArrayList<JTextField>();
    private ArrayList<JTextField> adultFemalesInput = new ArrayList<JTextField>();
    private ArrayList<JTextField> childrenOverEightInput = new ArrayList<JTextField>();
    private ArrayList<JTextField> childrenUnderEightInput = new ArrayList<JTextField>();

    private JPanel clientPanel;

    public Main() {
        super("Hamper Order Form "); // extends Jframe -  title of window
        setupGUI(); // create GUI
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void setupGUI() {
        // Pretty basic UI.
        
        instructions = new JLabel("Please enter the number of clients.");
        adultMalesLabel = new JLabel("Adult Males:");
        adultFemalesLabel = new JLabel("Adult Females:");
        childrenOverEightLabel = new JLabel("Children over 8:");
        childrenUnderEightLabel = new JLabel("Children under 8:");

        adultMalesInput.add(new JTextField("0", 15));
        adultFemalesInput.add(new JTextField("0", 15));
        childrenOverEightInput.add(new JTextField("0", 15));
        childrenUnderEightInput.add(new JTextField("0", 15));

        adultMalesInput.get(0).addMouseListener(this);
        adultFemalesInput.get(0).addMouseListener(this);
        childrenOverEightInput.get(0).addMouseListener(this);
        childrenUnderEightInput.get(0).addMouseListener(this);

        JButton submitInfo = new JButton("Submit");
        submitInfo.addActionListener(this);
        JButton addHamper = new JButton("Add additional hamper");
        addHamper.addActionListener(this);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        this.clientPanel = new JPanel();
        this.clientPanel.setLayout(new BoxLayout(this.clientPanel, BoxLayout.Y_AXIS));

        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 25));

        // JButton submitInfo2 = new JButton("Submit2");
        // submitInfo2.addActionListener(this);
        // JPanel submitPanel2 = new JPanel();
        // submitPanel2.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 35));
        // submitPanel2.add(submitInfo2);
        // this.add(submitPanel2, BorderLayout.EAST);
        
        this.setMinimumSize(new Dimension(500, 400));
        
        headerPanel.add(instructions);
        JLabel instrlabel = new JLabel("Hamper 1:");
        Font font = new Font("Default", Font.BOLD,16);
        instrlabel.setFont(font);
        this.clientPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.clientPanel.add(instrlabel);
        this.clientPanel.add(adultMalesLabel);
        this.clientPanel.add(adultMalesInput.get(0));
        this.clientPanel.add(adultFemalesLabel);
        this.clientPanel.add(adultFemalesInput.get(0));
        this.clientPanel.add(childrenOverEightLabel);
        this.clientPanel.add(childrenOverEightInput.get(0));
        this.clientPanel.add(childrenUnderEightLabel);
        this.clientPanel.add(childrenUnderEightInput.get(0));
        submitPanel.add(submitInfo);
        submitPanel.add(addHamper);
        
        // this refers to the jframe since the class extends jframe
        
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(this.clientPanel, BorderLayout.CENTER);
        this.add(submitPanel, BorderLayout.PAGE_END);
        
        
    }

    public void actionPerformed(ActionEvent event) {
        this.adultFemales = Integer.parseInt(adultFemalesInput.get(0).getText());
        this.adultMales = Integer.parseInt(adultMalesInput.get(0).getText());
        this.childrenOverEight = Integer.parseInt(childrenOverEightInput.get(0).getText());
        this.childrenUnderEight = Integer.parseInt(childrenUnderEightInput.get(0).getText());

        if (validateInput()) {
            String petID = idProcessing();
            // JOptionPane.showMessageDialog(this, "Your pet's clinic ID is " + petID);
        }
        
        // create additional hamper
        
        JLabel instrlabel = new JLabel("Hamper 2:");
        Font font = new Font("Default", Font.BOLD, 16);
        instrlabel.setFont(font);
        this.clientPanel.setBorder(new EmptyBorder(10, 10, 10, 10));


        adultMalesInput.add(new JTextField("0", 15));
        adultFemalesInput.add(new JTextField("0", 15));
        childrenOverEightInput.add(new JTextField("0", 15));
        childrenUnderEightInput.add(new JTextField("0", 15));
        // JPanel clientPanel2 = new JPanel();
        // this.clientPanel.setLayout(new BoxLayout(clientPanel2, BoxLayout.Y_AXIS));
        this.clientPanel.add(instrlabel);
        this.clientPanel.add(new JLabel("Adult Males:"));
        this.clientPanel.add(adultMalesInput.get(1));
        this.clientPanel.add(new JLabel("Adult Females:"));
        this.clientPanel.add(adultFemalesInput.get(1));
        this.clientPanel.add(new JLabel("Children Over 8"));
        this.clientPanel.add(childrenOverEightInput.get(1));
        this.clientPanel.add(new JLabel("Children Under 8"));
        this.clientPanel.add(childrenUnderEightInput.get(1));
        
        this.add(this.clientPanel, BorderLayout.CENTER);
        // JButton submitInfo2 = new JButton("Submit2");
        // submitInfo2.addActionListener(this);
        // JPanel submitPanel2 = new JPanel();
        // submitPanel2.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 35));
        // submitPanel2.add(submitInfo2);
        // this.add(submitPanel2, BorderLayout.EAST);
        //redraw if we ask for additional hamper
        this.setSize(500,1000);
        this.validate();
        this.pack();
        this.revalidate();
        this.repaint();
    }

    public void mouseClicked(MouseEvent event) {

        if (event.getSource().equals(adultMalesInput))
            adultMalesInput.get(0).setText("");

        if (event.getSource().equals(adultFemalesInput))
            adultFemalesInput.get(0).setText("");

        if (event.getSource().equals(childrenOverEightInput))
            childrenOverEightInput.get(0).setText("");

        if (event.getSource().equals(childrenUnderEightInput))
            childrenUnderEightInput.get(0).setText("");

    }

    public void mouseEntered(MouseEvent event) {

    }

    public void mouseExited(MouseEvent event) {

    }

    public void mousePressed(MouseEvent event) {

    }

    public void mouseReleased(MouseEvent event) {

    }

    private String idProcessing() {

        // String petID = new String(String.valueOf(adultMales.charAt(0)) + String.valueOf(adultFemales.charAt(0))
        //         + String.valueOf(childrenOverEight.charAt(0)) + String.valueOf(childrenUnderEight));

        // return petID;
        return "NOT IMPLEMENTED";
    }

    private boolean validateInput() {

        boolean allInputValid = true;

        // if (!Character.isUpperCase(firstName.charAt(0)) || firstName.length() < 2 || firstName.length() > 26) {
        //     allInputValid = false;
        //     JOptionPane.showMessageDialog(this, firstName + " is an invalid name input.");
        // }

        // if (!Character.isUpperCase(lastName.charAt(0)) || lastName.length() < 2 || lastName.length() > 26) {
        //     allInputValid = false;
        //     JOptionPane.showMessageDialog(this, lastName + " is an invalid name input.");
        // }

        // if (!Character.isUpperCase(petName.charAt(0)) || petName.length() < 2 || petName.length() > 26) {
        //     allInputValid = false;
        //     JOptionPane.showMessageDialog(this, petName + " is an invalid name input.");
        // }

        // if (birthYear < 1922 || birthYear > 2022) {
        //     allInputValid = false;
        //     JOptionPane.showMessageDialog(this,
        //             birthYear + " is an invalid birth year. Pets must be born between 1922 and 2022.");
        // }

        return allInputValid;

    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }

}
