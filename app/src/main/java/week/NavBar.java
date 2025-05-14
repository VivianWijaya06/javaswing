package week;

import javax.swing.*;
import java.awt.*;

public class NavBar extends JPanel {
    public NavBar(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(new BorderLayout());
        setBackground(Colors.DARK_BACKGROUND);
        setPreferredSize(Dimensions.NAV_BAR_SIZE);
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel title = new JLabel("Neo Culture Technology (NCT)");
        title.setFont(Fonts.TITLE_FONT);
        title.setForeground(Colors.TEXT_COLOR);
        add(title, BorderLayout.WEST);
        add(createNavButtonPanel(), BorderLayout.EAST);
    }

    private JPanel createNavButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(ButtonFactory.createStyledButton("Join Live", new Color(88, 101, 242), Dimensions.NAV_BUTTON_SIZE));
        buttonPanel.add(ButtonFactory.createStyledButton("Buy PC", new Color(255, 105, 120), Dimensions.NAV_BUTTON_SIZE));
        return buttonPanel;
    }
}