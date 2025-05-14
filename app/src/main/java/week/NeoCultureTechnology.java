package week;

import javax.swing.*;
import java.awt.*;

public class NeoCultureTechnology {
    private static CardLayout cardLayout;
    private static JPanel cardPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NeoCultureTechnology::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("NCT Member Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Colors.DARK_BACKGROUND);

        // Setup card layout for page switching
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBackground(Colors.DARK_BACKGROUND);
        
        // Add all pages
        cardPanel.add(PageCreator.createMainPage(), "main");
        cardPanel.add(PageCreator.createMemberPage(), "member");
        cardPanel.add(PageCreator.createNCT127Page(), "nct127");
        cardPanel.add(PageCreator.createNCTDreamPage(), "nctdream");
        cardPanel.add(PageCreator.createWayVPage(), "wayv");
        cardPanel.add(PageCreator.createNCTUPage(), "nctu");
        cardPanel.add(PageCreator.createExMemberPage(), "exmember");
        cardPanel.add(PageCreator.createNCTWishPage(), "nctwish");
        
        frame.add(new NavBar(cardLayout, cardPanel), BorderLayout.NORTH);
        frame.add(cardPanel, BorderLayout.CENTER);
        frame.add(new Sidebar(cardLayout, cardPanel), BorderLayout.WEST);
        
        frame.setVisible(true);
    }
}