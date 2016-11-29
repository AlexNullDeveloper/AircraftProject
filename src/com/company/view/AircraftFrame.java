package com.company.view;

import com.company.controller.WorkPerformer;
import com.company.model.Aircraft;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

public class AircraftFrame extends JFrame {

    //TODO all constants move in properties file
    private static final int ROWS = 1;
    private static final int COLS = 2;
    private static final int MAIN_PANE_HGAP = 10;
    private static final int MAIN_PANE_VGAP = 10;
    private static final int AIRCRAFT_INIT_HEIGHT = 400;
    private static final int AIRCRAFT_INIT_WIDTH = 200;
    private static final int STANDARD_WIDTH_OF_SIDE_IN_PIXELS = 25;
    private static final String WIDTH_LABEL_TEXT = "Width : ";
    private static final String HEIGHT_LABEL_TEXT = "Height : ";
    private static final int TEXT_FIELD_COLUMNS = 30;
    private static final String OK_BUTTON_TEXT = "OK";
    private JPanelRight jPanelRight;
    private JTextField heightTextField;
    private JTextField widthTextField;
    private JPanel pane;
    private Aircraft aircraft;


    public AircraftFrame() {
        createGUI();
    }

    private void createGUI() {
        aircraft = new Aircraft(AIRCRAFT_INIT_HEIGHT, AIRCRAFT_INIT_WIDTH);
        jPanelRight = new JPanelRight(aircraft);

        setScreenResolution();
        initPanels();

        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setScreenResolution() {
        //multiplies and divides for dynamic representation
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int applicationHeight = screen.height*71/100;
        int topPointY = (screen.height - applicationHeight) / 2;
        int applicationWidth = screen.width*53/100;
        int leftPointX = (screen.width - applicationWidth) / 2;
        setBounds(leftPointX, topPointY, applicationWidth, applicationHeight);
    }

    private void initPanels() {
        initMainPanel();
        JPanel jPanelLeft = initLeftPanel();
        pane.add(jPanelLeft);
        initCenterOfLeftPanel(jPanelLeft);
        initBottomOfLeftPanel(jPanelLeft);
        pane.add(jPanelRight);
    }

    private void initBottomOfLeftPanel(JPanel jPanelLeft) {
        JPanel leftBottomPanel = initBottomOfLeftPanel();
        jPanelLeft.add(leftBottomPanel, BorderLayout.SOUTH);
    }

    private JPanel initBottomOfLeftPanel() {
        JPanel leftBottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton okButton = new JButton(OK_BUTTON_TEXT);
        leftBottomPanel.add(okButton);
        okButton.addActionListener(ae -> {
            if (!checkNumberOrNot(widthTextField)) {
                JOptionPane.showMessageDialog(AircraftFrame.this,
                        "Illegal input for width field. got " + widthTextField.getText() + " expected number!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!checkNumberOrNot(heightTextField)) {
                JOptionPane.showMessageDialog(AircraftFrame.this,
                        "Illegal input for height field. got " + heightTextField.getText() + " expected number!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            WorkPerformer.setAircraft(widthTextField, heightTextField, aircraft, jPanelRight);
        });
        return leftBottomPanel;
    }

    private JPanel initLeftPanel() {
        JPanel jPanelLeft = new JPanel(new BorderLayout());
        jPanelLeft.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.gray, 2),
                BorderFactory.createEmptyBorder(STANDARD_WIDTH_OF_SIDE_IN_PIXELS, STANDARD_WIDTH_OF_SIDE_IN_PIXELS,
                        STANDARD_WIDTH_OF_SIDE_IN_PIXELS, STANDARD_WIDTH_OF_SIDE_IN_PIXELS)));
        return jPanelLeft;
    }

    private void initCenterOfLeftPanel(JPanel jPanelLeft) {
        JPanel jPanelCenterOfLeft = new JPanel(new FlowLayout());
        jPanelLeft.add(jPanelCenterOfLeft, BorderLayout.CENTER);
        jPanelCenterOfLeft.add(new JLabel(WIDTH_LABEL_TEXT));
        widthTextField = new JTextField(TEXT_FIELD_COLUMNS);
        jPanelCenterOfLeft.add(widthTextField);
        jPanelCenterOfLeft.add(new JLabel(HEIGHT_LABEL_TEXT));
        heightTextField = new JTextField(TEXT_FIELD_COLUMNS);
        jPanelCenterOfLeft.add(heightTextField);
    }

    private void initMainPanel() {
        pane = (JPanel) getContentPane();
        pane.setLayout(new GridLayout(ROWS, COLS, MAIN_PANE_HGAP, MAIN_PANE_VGAP));
        pane.setBackground(Color.DARK_GRAY);
        pane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.gray, 2),
                BorderFactory.createEmptyBorder(STANDARD_WIDTH_OF_SIDE_IN_PIXELS, STANDARD_WIDTH_OF_SIDE_IN_PIXELS,
                        STANDARD_WIDTH_OF_SIDE_IN_PIXELS, STANDARD_WIDTH_OF_SIDE_IN_PIXELS)));
    }


    private boolean checkNumberOrNot(JTextField jTextField) {
        try {
            Integer.parseInt(jTextField.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
