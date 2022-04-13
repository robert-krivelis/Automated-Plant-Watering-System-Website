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
import java.net.URL;
import java.util.*;
import java.awt.*;

public class Main extends JFrame implements ActionListener, MouseListener {

    public ArrayList<ArrayList<Integer>> allClientTypesFromUser = new ArrayList<ArrayList<Integer>>();
    private JLabel instructions;

    private int numberOfHampersVisible = 0;

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
        setupGUI(); // create GUI
        setSize(500, 300); // sets window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addNewHamper() {
        // create additional hamper
        if (numberOfHampersVisible < 5) {// max limit of 5 for number of hampers for now since it just needs "several"

            // refactor into new function later
            addNewHamperLabelsAndInputs();

            this.add(this.clientPanel, BorderLayout.CENTER);

            // redraw if we ask for additional hamper
            this.setSize(500, 1000);
            this.validate();
            this.pack();
            this.revalidate();
            this.repaint();
        }
    }

    private void addNewHamperLabelsAndInputs() {
        hamperNumberLabel.add(new JLabel("Hamper " + String.valueOf(numberOfHampersVisible + 1) + ":"));
        Font font = new Font("Default", Font.BOLD, 16);
        hamperNumberLabel.get(this.numberOfHampersVisible).setFont(font);
        this.clientPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // padding to make it look not as packed

        adultMalesInput.add(new JTextField("0", 15));
        adultFemalesInput.add(new JTextField("0", 15));
        childrenOverEightInput.add(new JTextField("0", 15));
        childrenUnderEightInput.add(new JTextField("0", 15));
        adultMalesLabel.add(new JLabel("Adult Males:"));
        adultFemalesLabel.add(new JLabel("Adult Females:"));
        childrenOverEightLabel.add(new JLabel("Children over 8:"));
        childrenUnderEightLabel.add(new JLabel("Children under 8:"));

        this.clientPanel.add(hamperNumberLabel.get(this.numberOfHampersVisible));
        this.clientPanel.add(adultMalesLabel.get(this.numberOfHampersVisible));
        this.clientPanel.add(adultMalesInput.get(this.numberOfHampersVisible));
        this.clientPanel.add(adultFemalesLabel.get(this.numberOfHampersVisible));
        this.clientPanel.add(adultFemalesInput.get(this.numberOfHampersVisible));
        this.clientPanel.add(childrenOverEightLabel.get(this.numberOfHampersVisible));
        this.clientPanel.add(childrenOverEightInput.get(this.numberOfHampersVisible));
        this.clientPanel.add(childrenUnderEightLabel.get(this.numberOfHampersVisible));
        this.clientPanel.add(childrenUnderEightInput.get(this.numberOfHampersVisible));
        this.numberOfHampersVisible += 1; // add for next time to know
    }

    public void setupGUI() {

        instructions = new JLabel("Please enter the number of clients.");

        this.clientPanel = new JPanel();
        this.clientPanel.setLayout(new BoxLayout(this.clientPanel, BoxLayout.Y_AXIS));
        addNewHamperLabelsAndInputs(); // add labels and text fields to client panel

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

            if (!validateHamperInputs()) {
                return;
            }
            callCreateHampers();
            // dlg.setModal(false);
            // dialog = JOptionPane.createDialog("Finding best combinations of food
            // ...");//showMessageDialog(null, "Finding best combinations of food ...");
            // reset();

        }

    }

    private void callCreateHampers() {

        this.getContentPane().removeAll();
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        headerPanel.add(new JLabel("Working... Please wait."));
        this.add(headerPanel);
        this.revalidate();
        this.validate();
        this.repaint();

        // DO WORK HERE
        JOptionPane.showMessageDialog(this, "Order Complete! Press OK to make additional orders.");
        reset();

    }

    private void reset() {
        this.dispose(); // close current window
        new Main().setVisible(true); // start up new window

    }

    public void actionPerformed(ActionEvent event) {
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

    private String inputProcessing() {

        // String petID = new String(String.valueOf(adultMales.charAt(0)) +
        // String.valueOf(adultFemales.charAt(0))
        // + String.valueOf(childrenOverEight.charAt(0)) +
        // String.valueOf(childrenUnderEight));

        // return petID;
        return "NOT IMPLEMENTED";
    }


    private boolean validateHamperInputs() {
        String text;
        ArrayList<Integer> clientTypeFromUser = new ArrayList<Integer>();

        // Create a 2D array of the hamper inputs
        ArrayList<ArrayList<JTextField>> hamperInputs = new ArrayList<ArrayList<JTextField>>();
        hamperInputs.add(adultMalesInput);
        hamperInputs.add(adultFemalesInput);
        hamperInputs.add(childrenOverEightInput);
        hamperInputs.add(childrenUnderEightInput);

        for (int clientType = 0; clientType < 4; clientType++) {
            // not really sure what to call this, but it's an array across the hampers of the adultMale/adultFemale/children numbers.
            ArrayList<JTextField> adultMalesForExample = hamperInputs.get(clientType); 

            for (int hamperNumber = 0; hamperNumber < this.numberOfHampersVisible; hamperNumber++) {
                text = adultMalesForExample.get(hamperNumber).getText();

                if (!(text.length() >= 1)) {
                    String errMsg = "Invalid input in Hamper " + String.valueOf(hamperNumber + 1) + ", field number "
                            + String
                                    .valueOf(clientType + 1)
                            + ".";
                    errMsg += "\nClient inputs must have a length of at least 1.";
                    JOptionPane.showMessageDialog(this, errMsg);
                    return false;
                }
                if (!text.matches("[0-9]+")) {
                    String errMsg = "Invalid input hamper " + String.valueOf(hamperNumber + 1) + ", field number "
                            + String
                                    .valueOf(clientType + 1)
                            + ".";
                    ;
                    errMsg += "\nClient inputs must be integers.";
                    JOptionPane.showMessageDialog(this, errMsg);
                    return false;
                }

            }

        }

        return true;

    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }

}
