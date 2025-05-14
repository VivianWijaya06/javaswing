package week;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonFactory {
    public static JButton createStyledButton(String text, Color bgColor, Dimension size) {
        JButton button = new JButton(text);
        button.setFont(Fonts.BUTTON_FONT);
        button.setForeground(Colors.TEXT_COLOR);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setPreferredSize(size);
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
        
        return button;
    }

    public static JButton createMenuButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setFont(Fonts.MENU_FONT);
        button.setForeground(Colors.TEXT_COLOR);
        button.setBackground(Colors.BUTTON_COLOR);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setPreferredSize(Dimensions.MENU_BUTTON_SIZE);
        button.setMaximumSize(Dimensions.MENU_BUTTON_SIZE);
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.setBackground(Colors.BUTTON_PRESSED_COLOR);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button.setBackground(Colors.BUTTON_COLOR);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Colors.BUTTON_HOVER_COLOR);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Colors.BUTTON_COLOR);
            }
        });
        
        button.addActionListener(action);
        return button;
    }
}