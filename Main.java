package com.ASAtech;

import javax.swing.*;
import java.awt.*;

public class Main {

    //Main method
    public static void main(String[] args) throws AWTException {

        Validate auth = new Validate();  //Creating an object of Validate class
        auth.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        auth.setSize(500, 500);
        auth.setResizable(false);
        auth.setLocationRelativeTo(null);
        auth.setVisible(true);
    }
    
    //Open source is love
}
