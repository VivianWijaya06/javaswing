package week;

import javax.swing.*;
import java.awt.*;

public class Sidebar extends JPanel {
    private final CardLayout cardLayout;
    private final JPanel cardPanel;

    public Sidebar(CardLayout cardLayout, JPanel cardPanel) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        
        setBackground(Colors.SIDEBAR_BACKGROUND);
        setPreferredSize(Dimensions.SIDEBAR_SIZE);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
    
        addMenuButtons();
    }

    private void addMenuButtons() {
        add(ButtonFactory.createMenuButton("Home", e -> cardLayout.show(cardPanel, "main")));
        add(Box.createRigidArea(new Dimension(0, 10)));
        
        add(ButtonFactory.createMenuButton("Member", e -> cardLayout.show(cardPanel, "member")));
        add(Box.createRigidArea(new Dimension(0, 10)));
        
        add(ButtonFactory.createMenuButton("NCT U", e -> cardLayout.show(cardPanel, "nctu")));
        add(Box.createRigidArea(new Dimension(0, 10)));
        
        add(ButtonFactory.createMenuButton("EX Member", e -> cardLayout.show(cardPanel, "exmember")));
        add(Box.createRigidArea(new Dimension(0, 10)));
        
        add(ButtonFactory.createMenuButton("NCT Wish", e -> cardLayout.show(cardPanel, "nctwish")));
        add(Box.createRigidArea(new Dimension(0, 10)));
        
        add(ButtonFactory.createMenuButton("NCT 127", e -> cardLayout.show(cardPanel, "nct127")));
        add(Box.createRigidArea(new Dimension(0, 10)));
        
        add(ButtonFactory.createMenuButton("NCT Dream", e -> cardLayout.show(cardPanel, "nctdream")));
        add(Box.createRigidArea(new Dimension(0, 10)));
        
        add(ButtonFactory.createMenuButton("WayV", e -> cardLayout.show(cardPanel, "wayv")));
        add(Box.createRigidArea(new Dimension(0, 10)));
    }
}