/* 
class to store gui input data. 
seperate from gui so that you can make multiple hampers and reuse this component.
probably a better java way to implement this class (interface? idk)

im probably not going to be using this to be honest with u 
*/
package edu.ucalgary.ensf409;

import javax.swing.*;

public class GUIData {
    public int adultMales;
    public int adultFemales;
    public int childrenOverEight;
    public int childrenUnderEight;

    public JLabel adultMalesLabel;
    public JLabel adultFemalesLabel;
    public JLabel childrenOverEightLabel;
    public JLabel childrenUnderEightLabel;

    public JTextField adultMalesInput;
    public JTextField adultFemalesInput;
    public JTextField childrenOverEightInput;
    public JTextField childrenUnderEightInput;

    public GUIData() {
        System.out.print("sup");
    }
}