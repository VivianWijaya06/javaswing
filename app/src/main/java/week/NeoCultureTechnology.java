package week;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;

public class NeoCultureTechnology {
    private static final Color DARK_BACKGROUND = new Color(0, 120, 107);
    private static final Color SIDEBAR_BACKGROUND = new Color(100, 220, 180);
    private static final Color CARD_BACKGROUND = new Color(40, 40, 50);
    private static final Color GRID_BACKGROUND = new Color(0, 180, 137);
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color BUTTON_COLOR = new Color(50, 50, 60);
    private static final Color BUTTON_PRESSED_COLOR = new Color(30, 30, 40);
    private static final Color BUTTON_HOVER_COLOR = new Color(70, 70, 80);

    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 24);
    private static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 14);
    private static final Font MENU_FONT = new Font("Arial", Font.PLAIN, 16);
    private static final Font NAME_FONT = new Font("Arial", Font.BOLD, 16);
    private static final Font PLACEHOLDER_FONT = new Font("Arial", Font.ITALIC, 12);
    
    private static final Dimension NAV_BAR_SIZE = new Dimension(0, 60);
    private static final Dimension SIDEBAR_SIZE = new Dimension(200, 0);
    private static final Dimension NAV_BUTTON_SIZE = new Dimension(120, 40);
    private static final Dimension MENU_BUTTON_SIZE = new Dimension(180, 40);
    private static final Dimension IMAGE_SIZE = new Dimension(200, 200);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NeoCultureTechnology::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = initializeMainFrame();
        frame.add(createNavBar(), BorderLayout.NORTH);
        frame.add(createMainContent(), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static JFrame initializeMainFrame() {
        JFrame frame = new JFrame("NCT Member Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(DARK_BACKGROUND);
        return frame;
    }

    interface Displayable {
        JComponent createDisplay();
    }

    abstract static class NCTMember implements Displayable {
        protected String name;
        protected String imagePath;
        protected String unit;

        public NCTMember(String name, String imagePath, String unit) {
            this.name = name;
            this.imagePath = imagePath;
            this.unit = unit;
        }

        @Override
        public JComponent createDisplay() {
            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            card.setBackground(CARD_BACKGROUND);
            card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            card.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    card.setBackground(CARD_BACKGROUND.brighter());
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    card.setBackground(CARD_BACKGROUND);
                }
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    card.setBackground(CARD_BACKGROUND.darker());
                }
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    card.setBackground(CARD_BACKGROUND.brighter());
                }
            });

            card.add(createMemberImage());
            card.add(Box.createRigidArea(new Dimension(0, 10)));
            card.add(createMemberNameLabel());
            card.add(createMemberUnitLabel());
            return card;
        }

        private JLabel createMemberImage() {
            JLabel imageLabel;
            try {
                ImageIcon icon = new ImageIcon(new URL(imagePath));
                Image scaledImage = icon.getImage().getScaledInstance(
                    IMAGE_SIZE.width, IMAGE_SIZE.height, Image.SCALE_SMOOTH);
                imageLabel = new JLabel(new ImageIcon(scaledImage));
            } catch (Exception e) {
                imageLabel = new JLabel("Image not found", SwingConstants.CENTER);
                imageLabel.setBackground(new Color(60, 60, 80));
                imageLabel.setOpaque(true);
                imageLabel.setForeground(Color.LIGHT_GRAY);
                imageLabel.setFont(PLACEHOLDER_FONT);
            }
            imageLabel.setPreferredSize(IMAGE_SIZE);
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            return imageLabel;
        }

        private JLabel createMemberNameLabel() {
            JLabel label = new JLabel(name, SwingConstants.CENTER);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setForeground(TEXT_COLOR);
            label.setFont(NAME_FONT);
            return label;
        }

        private JLabel createMemberUnitLabel() {
            JLabel label = new JLabel(unit, SwingConstants.CENTER);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setForeground(getUnitColor());
            return label;
        }

        protected abstract Color getUnitColor();
    }

    static class NCT127Member extends NCTMember {
        public NCT127Member(String name, String imagePath) {
            super(name, imagePath, "NCT 127");
        }

        @Override
        protected Color getUnitColor() {
            return new Color(255, 100, 100); 
        }
    }

    static class NCTDreamMember extends NCTMember {
        public NCTDreamMember(String name, String imagePath) {
            super(name, imagePath, "NCT Dream");
        }

        @Override
        protected Color getUnitColor() {
            return new Color(100, 255, 100); 
        }
    }

    static class WayVMember extends NCTMember {
        public WayVMember(String name, String imagePath) {
            super(name, imagePath, "WayV");
        }

        @Override
        protected Color getUnitColor() {
            return new Color(100, 100, 255);
        }
    }

    private static JPanel createNavBar() {
        JPanel navBar = new JPanel(new BorderLayout());
        navBar.setBackground(DARK_BACKGROUND);
        navBar.setPreferredSize(NAV_BAR_SIZE);
        navBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel title = new JLabel("Neo Culture Technology (NCT)");
        title.setFont(TITLE_FONT);
        title.setForeground(TEXT_COLOR);
        navBar.add(title, BorderLayout.WEST);
        navBar.add(createNavButtonPanel(), BorderLayout.EAST);
        return navBar;
    }

    private static JPanel createNavButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(createStyledButton("Join Live", new Color(88, 101, 242), NAV_BUTTON_SIZE));
        buttonPanel.add(createStyledButton("Buy PC", new Color(255, 105, 120), NAV_BUTTON_SIZE));
        return buttonPanel;
    }

    private static JButton createStyledButton(String text, Color bgColor, Dimension size) {
        JButton button = new JButton(text);
        button.setFont(BUTTON_FONT);
        button.setForeground(TEXT_COLOR);
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

    private static JPanel createMainContent() {
        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.add(createSidebar(), BorderLayout.WEST);
        mainContent.add(createMemberGrid(), BorderLayout.CENTER);
        return mainContent;
    }

    private static JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setBackground(SIDEBAR_BACKGROUND);
        sidebar.setPreferredSize(SIDEBAR_SIZE);
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        String[] menuItems = {"Home", "Member", "Song List", "Biodata", 
                             "Light Stick", "NCT 127", "NCT Dream", "WayV"};
        
        for (String item : menuItems) {
            sidebar.add(createMenuButton(item));
            sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        return sidebar;
    }

    private static JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setFont(MENU_FONT);
        button.setForeground(TEXT_COLOR);
        button.setBackground(BUTTON_COLOR);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setPreferredSize(MENU_BUTTON_SIZE);
        button.setMaximumSize(MENU_BUTTON_SIZE);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_PRESSED_COLOR);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_COLOR);
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_HOVER_COLOR);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_COLOR);
            }
        });
        
        button.addActionListener(e -> {
            System.out.println("Menu item clicked: " + text);
        });
        
        return button;
    }

    private static JScrollPane createMemberGrid() {
        JPanel gridPanel = new JPanel(new GridLayout(0, 4, 20, 20));
        gridPanel.setBackground(GRID_BACKGROUND);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        createMemberList().forEach(member -> 
            gridPanel.add(member.createDisplay())
        );

        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        return scrollPane;
    }

    private static List<NCTMember> createMemberList() {
        List<NCTMember> members = new ArrayList<>();
        members.add(new NCT127Member("Taeyong", "https://kprofiles.com/wp-content/uploads/2016/09/20230814_175052-533x800.jpg"));
        members.add(new NCT127Member("Johnny", "https://kprofiles.com/wp-content/uploads/2016/09/20230817_111103-533x800.jpg"));
        members.add(new NCT127Member("Yuta", "https://kprofiles.com/wp-content/uploads/2016/09/20230814_174948-534x800.jpg"));
        members.add(new NCT127Member("Doyoung", "https://kprofiles.com/wp-content/uploads/2016/09/20230817_110602-533x800.jpg"));
        members.add(new NCT127Member("Jaehyun", "https://kprofiles.com/wp-content/uploads/2016/09/20230814_174538-533x800.jpg"));
        members.add(new NCT127Member("Jungwoo", "https://kprofiles.com/wp-content/uploads/2016/09/20230815_104342-533x800.jpg"));
        members.add(new NCT127Member("Mark", "https://kprofiles.com/wp-content/uploads/2016/09/20230817_110911-534x800.jpg"));
        members.add(new NCT127Member("Haechan", "https://kprofiles.com/wp-content/uploads/2016/09/20230817_110439-533x800.jpg"));
        members.add(new NCTDreamMember("Renjun", "https://kprofiles.com/wp-content/uploads/2016/09/20230814_174437-534x800.jpg"));
        members.add(new NCTDreamMember("Jeno", "https://kprofiles.com/wp-content/uploads/2016/09/20230814_174719-533x800.jpg"));
        members.add(new NCTDreamMember("Jaemin", "https://kprofiles.com/wp-content/uploads/2016/09/20230817_110341-533x800.jpg"));
        members.add(new NCTDreamMember("Chenle", "https://kprofiles.com/wp-content/uploads/2016/09/20230817_110744-533x800.jpg"));
        members.add(new NCTDreamMember("Jisung", "https://kprofiles.com/wp-content/uploads/2016/09/20230815_104116-533x800.jpg"));
        members.add(new WayVMember("Kun", "https://kprofiles.com/wp-content/uploads/2016/09/20230814_174815-533x800.jpg"));
        members.add(new WayVMember("Ten", "https://kprofiles.com/wp-content/uploads/2016/09/20230814_174611-534x800.jpg"));
        members.add(new WayVMember("WinWin", "https://kprofiles.com/wp-content/uploads/2016/09/20230817_110948-534x800.jpg"));
        members.add(new WayVMember("Xiaojun", "https://kprofiles.com/wp-content/uploads/2016/09/20230815_104151-533x800.jpg"));
        members.add(new WayVMember("Hendery", "https://kprofiles.com/wp-content/uploads/2016/09/20230817_110546-534x800.jpg"));
        members.add(new WayVMember("YangYang", "https://kprofiles.com/wp-content/uploads/2016/09/20230814_174158-534x800.jpg"));
        return members;
    }
}