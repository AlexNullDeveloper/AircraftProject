package com.company.controller;

import com.company.model.Aircraft;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class WorkPerformer {
    /*
    * setting width and height of aircraft object then calling repaint on jpanel
    * */
    public static void setAircraft(JTextField widthTextField, JTextField heightTextField, Aircraft aircraft, JPanel jPanel) {
        aircraft.setHeight(Integer.parseInt(heightTextField.getText()));
        aircraft.setWidth(Integer.parseInt(widthTextField.getText()));
        jPanel.repaint();
    }
}
