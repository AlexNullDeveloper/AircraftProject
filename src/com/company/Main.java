package com.company;

import com.company.view.AircraftFrame;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        //JDK 8 required
        SwingUtilities.invokeLater(AircraftFrame::new);
    }
}
