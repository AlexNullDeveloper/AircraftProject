package com.company.view;

import com.company.model.Aircraft;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

public class JPanelRight extends JPanel {

    private static final int STANDARD_WIDTH_OF_SIDE_IN_PIXELS = 25;
    Aircraft aircraft;

    public JPanelRight(Aircraft aircraft) {
        this.aircraft = aircraft;
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.gray, 2),
                BorderFactory.createEmptyBorder(STANDARD_WIDTH_OF_SIDE_IN_PIXELS, STANDARD_WIDTH_OF_SIDE_IN_PIXELS,
                        STANDARD_WIDTH_OF_SIDE_IN_PIXELS, STANDARD_WIDTH_OF_SIDE_IN_PIXELS)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = this.getWidth();
        int height = this.getHeight();
        int halfOfWidth = width / 2;
        int halfOfHeight = height / 2;
        int halfOfAircraftHeight = aircraft.getHeight() / 2;
        int halfOfAircraftWidth = aircraft.getWidth() / 2;
        int verticalLineStart = halfOfHeight - halfOfAircraftHeight;
        int verticalLineEnd = halfOfHeight + halfOfAircraftHeight;
        int distance = verticalLineEnd - verticalLineStart;
        int oneThirdOfDistance = distance / 3;

        //vertical
        g.drawLine(halfOfWidth, verticalLineStart, halfOfWidth, verticalLineEnd);
        //horizontal
        g.drawLine(halfOfWidth - halfOfAircraftWidth, verticalLineStart + oneThirdOfDistance,
                halfOfWidth + halfOfAircraftWidth, verticalLineStart + oneThirdOfDistance);
    }
}
