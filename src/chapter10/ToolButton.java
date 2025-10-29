package chapter10;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ToolButton extends JButton implements ActionListener {

    // data for customizing this JButton
    protected ToolPanel.ShapeType shapeType;
    protected ToolPanel toolPanel;
    protected boolean isSelected = false;
    
    public ToolButton(ToolPanel toolPanel, ToolPanel.ShapeType shapeType, String buttonText) {
        super(buttonText);
        this.toolPanel = toolPanel;
        this.shapeType = shapeType;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        toolPanel.currentShape = shapeType;
        toolPanel.deselectButtons();
        isSelected = true;
        this.setBackground(Color.BLUE);
        this.setForeground(Color.WHITE);
    }

    @Override
    public void paint(Graphics g) {
        if (isSelected) {
            this.setBackground(Color.BLUE);
            this.setForeground(Color.WHITE);
        } else {
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);    
        }
        super.paint(g);
    }



}
