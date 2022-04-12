/*
gui for project.

typical user steps: 
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

    private JLabel instructions;

    private int numberOfHampersVisible = 1;

    // STORE LABELS HERE
    private ArrayList<JLabel> hamperNumberLabel = new ArrayList<JLabel>();
    private ArrayList<JLabel> adultMalesLabel = new ArrayList<JLabel>();
    private ArrayList<JLabel> adultFemalesLabel = new ArrayList<JLabel>();
    private ArrayList<JLabel> childrenOverEightLabel = new ArrayList<JLabel>();
    private ArrayList<JLabel> childrenUnderEightLabel = new ArrayList<JLabel>();

    // private JLabel adultMalesLabel;
    // private JLabel adultFemalesLabel;
    // private JLabel childrenOverEightLabel;
    // private JLabel childrenUnderEightLabel;

    // STORE INPUT VALUES HERE
    private ArrayList<JTextField> adultMalesInput = new ArrayList<JTextField>();
    private ArrayList<JTextField> adultFemalesInput = new ArrayList<JTextField>();
    private ArrayList<JTextField> childrenOverEightInput = new ArrayList<JTextField>();
    private ArrayList<JTextField> childrenUnderEightInput = new ArrayList<JTextField>();

    private JPanel clientPanel;
    private JPanel submitPanel;

    public Main() {
        super("Hamper Order Form "); // extends Jframe - sets the title of the window
        // initializeGUILabels();
        setupGUI(); // create GUI
        setSize(500, 300); // sets window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // private void initializeGUILabels(){
    // ArrayList<JLabel>[] = new ArrayList<JLabel>
    // for (arr:)
    // }

    public void addNewHamper() {
        // create additional hamper
        if (numberOfHampersVisible < 5) {// max limit of 5 for number of hampers for now since it just needs "several"
            hamperNumberLabel.add(new JLabel("Hamper " + String.valueOf(numberOfHampersVisible + 1) + ":"));
            Font font = new Font("Default", Font.BOLD, 16);
            hamperNumberLabel.get(numberOfHampersVisible).setFont(font);
            this.clientPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // padding to make it look not as packed

            // refactor into new function later
            adultMalesInput.add(new JTextField("0", 15));
            adultFemalesInput.add(new JTextField("0", 15));
            childrenOverEightInput.add(new JTextField("0", 15));
            childrenUnderEightInput.add(new JTextField("0", 15));
            adultMalesLabel.add(new JLabel("Adult Males:"));
            adultFemalesLabel.add(new JLabel("Adult Females:"));
            childrenOverEightLabel.add(new JLabel("Children over 8:"));
            childrenUnderEightLabel.add(new JLabel("Children under 8:"));

            this.clientPanel.add(hamperNumberLabel.get(numberOfHampersVisible));
            this.clientPanel.add(adultMalesLabel.get(this.numberOfHampersVisible));
            this.clientPanel.add(adultMalesInput.get(this.numberOfHampersVisible));
            this.clientPanel.add(adultFemalesLabel.get(this.numberOfHampersVisible));
            this.clientPanel.add(adultFemalesInput.get(this.numberOfHampersVisible));
            this.clientPanel.add(childrenOverEightLabel.get(this.numberOfHampersVisible));
            this.clientPanel.add(childrenOverEightInput.get(this.numberOfHampersVisible));
            this.clientPanel.add(childrenUnderEightLabel.get(this.numberOfHampersVisible));
            this.clientPanel.add(childrenUnderEightInput.get(this.numberOfHampersVisible));
            this.numberOfHampersVisible += 1; // add for next time to know

            this.add(this.clientPanel, BorderLayout.CENTER);

            // redraw if we ask for additional hamper
            this.setSize(500, 1000);
            this.validate();
            this.pack();
            this.revalidate();
            this.repaint();
        }
    }

    public void setupGUI() {

        instructions = new JLabel("Please enter the number of clients.");

        adultMalesLabel.add(new JLabel("Adult Males:"));
        adultFemalesLabel.add(new JLabel("Adult Females:"));
        childrenOverEightLabel.add(new JLabel("Children over 8:"));
        childrenUnderEightLabel.add(new JLabel("Children under 8:"));

        adultMalesInput.add(new JTextField("0", 15));
        adultFemalesInput.add(new JTextField("0", 15));
        childrenOverEightInput.add(new JTextField("0", 15));
        childrenUnderEightInput.add(new JTextField("0", 15));

        adultMalesInput.get(0).addMouseListener(this);
        adultFemalesInput.get(0).addMouseListener(this);
        childrenOverEightInput.get(0).addMouseListener(this);
        childrenUnderEightInput.get(0).addMouseListener(this);

        JButton submitInfo = new JButton("Submit");
        // submitInfo.addActionListener(this);
        submitInfo.addActionListener(new buttonListener());

        JButton addHamper = new JButton("Add additional hamper");
        addHamper.addActionListener(this);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        this.clientPanel = new JPanel();
        this.clientPanel.setLayout(new BoxLayout(this.clientPanel, BoxLayout.Y_AXIS));

        this.submitPanel = new JPanel();
        this.submitPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 20, 25));

        // JButton submitInfo2 = new JButton("Submit2");
        // submitInfo2.addActionListener(this);
        // JPanel submitPanel2 = new JPanel();
        // submitPanel2.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 35));
        // submitPanel2.add(submitInfo2);
        // this.add(submitPanel2, BorderLayout.EAST);
        // this.setSize(new Dimension(500, 400));
        this.setMinimumSize(new Dimension(500, 400));

        headerPanel.add(instructions);
        hamperNumberLabel.add(new JLabel("Hamper " + String.valueOf(numberOfHampersVisible) + ":"));
        Font font = new Font("Default", Font.BOLD, 16);
        hamperNumberLabel.get(0).setFont(font);
        this.clientPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.clientPanel.add(hamperNumberLabel.get(0));
        this.clientPanel.add(adultMalesLabel.get(0));
        this.clientPanel.add(adultMalesInput.get(0));
        this.clientPanel.add(adultFemalesLabel.get(0));
        this.clientPanel.add(adultFemalesInput.get(0));
        this.clientPanel.add(childrenOverEightLabel.get(0));
        this.clientPanel.add(childrenOverEightInput.get(0));
        this.clientPanel.add(childrenUnderEightLabel.get(0));
        this.clientPanel.add(childrenUnderEightInput.get(0));
        this.submitPanel.add(submitInfo);
        this.submitPanel.add(addHamper);

        // this refers to the jframe since the class extends jframe

        this.add(headerPanel, BorderLayout.NORTH);
        this.add(this.clientPanel, BorderLayout.CENTER);
        this.add(submitPanel, BorderLayout.PAGE_END);

    }

    private class buttonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "I was clicked and handled by buttonListener!");
            reset();

        }

    }

    private void reset() {
        this.dispose();
        new Main().setVisible(true);
        
        // // no easy way to reset the gui so you literally have to remove components from
        // // existing gui.
        // for (int i = 1; i < this.numberOfHampersVisible; i++) {
        //     this.setLayout(new BorderLayout());
        //     this.clientPanel.remove(hamperNumberLabel.get(i));
        //     this.clientPanel.remove(adultMalesLabel.get(i));
        //     this.clientPanel.remove(adultMalesInput.get(i));
        //     this.clientPanel.remove(adultFemalesLabel.get(i));
        //     this.clientPanel.remove(adultFemalesInput.get(i));
        //     this.clientPanel.remove(childrenOverEightLabel.get(i));
        //     this.clientPanel.remove(childrenOverEightInput.get(i));
        //     this.clientPanel.remove(childrenUnderEightLabel.get(i));
        //     this.clientPanel.remove(childrenUnderEightInput.get(i));
        // }
        // this.numberOfHampersVisible = 1; // default

        // // this.add(panel, BorderLayout.CENTER); // add panel to window center
        // // this.add(reset, BorderLayout.SOUTH); // add reset button to window bottom
        // this.pack();

        // this.validate();

        // this.revalidate();
        // this.setLocationByPlatform(true);
        // this.setVisible(true);
        // this.setMinimumSize(new Dimension(500, 500));
        // // this.setSize(new Dimension(500, 500));
        // this.repaint();
        // removeAll();// or remove(JComponent)
        // revalidate();
        // repaint();
    }

    public void actionPerformed(ActionEvent event) {
        int adultMales = Integer.parseInt(adultMalesInput.get(0).getText());
        int adultFemales = Integer.parseInt(adultFemalesInput.get(0).getText());
        int childrenOverEight = Integer.parseInt(childrenOverEightInput.get(0).getText());
        int childrenUnderEight = Integer.parseInt(childrenUnderEightInput.get(0).getText());

        if (validateInput()) {
            String petID = idProcessing();
            // JOptionPane.showMessageDialog(this, "Your pet's clinic ID is " + petID);
        }
        addNewHamper();

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

        // String petID = new String(String.valueOf(adultMales.charAt(0)) +
        // String.valueOf(adultFemales.charAt(0))
        // + String.valueOf(childrenOverEight.charAt(0)) +
        // String.valueOf(childrenUnderEight));

        // return petID;
        return "NOT IMPLEMENTED";
    }

    private boolean validateInput() {

        boolean allInputValid = true;

        // if (!Character.isUpperCase(firstName.charAt(0)) || firstName.length() < 2 ||
        // firstName.length() > 26) {
        // allInputValid = false;
        // JOptionPane.showMessageDialog(this, firstName + " is an invalid name
        // input.");
        // }

        // if (!Character.isUpperCase(lastName.charAt(0)) || lastName.length() < 2 ||
        // lastName.length() > 26) {
        // allInputValid = false;
        // JOptionPane.showMessageDialog(this, lastName + " is an invalid name input.");
        // }

        // if (!Character.isUpperCase(petName.charAt(0)) || petName.length() < 2 ||
        // petName.length() > 26) {
        // allInputValid = false;
        // JOptionPane.showMessageDialog(this, petName + " is an invalid name input.");
        // }

        // if (birthYear < 1922 || birthYear > 2022) {
        // allInputValid = false;
        // JOptionPane.showMessageDialog(this,
        // birthYear + " is an invalid birth year. Pets must be born between 1922 and
        // 2022.");
        // }

        return allInputValid;

    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }

}
