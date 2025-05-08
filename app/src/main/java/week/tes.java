package week;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;

public class tes {
    // Colors
    private static final Color BACKGROUND_COLOR = new Color(30, 31, 46);
    private static final Color NAVBAR_COLOR = new Color(44, 45, 60);
    private static final Color SIDEBAR_COLOR = new Color(50, 51, 70);
    private static final Color BUTTON_COLOR = new Color(65, 66, 90);
    private static final Color BUTTON_PRESSED_COLOR = new Color(45, 46, 70);
    private static final Color BUTTON_HOVER_COLOR = new Color(75, 76, 100);
    private static final Color CARD_COLOR = new Color(40, 41, 56);
    private static final Color TEXT_COLOR = Color.WHITE;

    // Fonts
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 24);
    private static final Font BUTTON_FONT = new Font("Arial", Font.PLAIN, 14);
    private static final Font NAME_FONT = new Font("Arial", Font.BOLD, 16);

    // Sizes
    private static final Dimension NAVBAR_SIZE = new Dimension(0, 60);
    private static final Dimension SIDEBAR_SIZE = new Dimension(200, 0);
    private static final Dimension CARD_SIZE = new Dimension(200, 250);
    private static final Dimension IMAGE_SIZE = new Dimension(180, 180);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                createAndShowGUI();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Honkai: Star Rail Character Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(BACKGROUND_COLOR);

        frame.add(createNavBar(), BorderLayout.NORTH);
        frame.add(createMainContent(), BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    interface Displayable {
        JComponent createDisplay();
    }

    abstract static class GameCharacter implements Displayable {
        protected String name;
        protected String imageUrl;
        protected String type;

        public GameCharacter(String name, String imageUrl, String type) {
            this.name = name;
            this.imageUrl = imageUrl;
            this.type = type;
        }

        @Override
        public JComponent createDisplay() {
            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            card.setBackground(CARD_COLOR);
            card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            card.setPreferredSize(CARD_SIZE);

            // Add hover and press effects to character cards
            card.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    card.setBackground(CARD_COLOR.brighter());
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    card.setBackground(CARD_COLOR);
                }

                public void mousePressed(java.awt.event.MouseEvent evt) {
                    card.setBackground(CARD_COLOR.darker());
                }

                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    card.setBackground(CARD_COLOR.brighter());
                }
            });

            try {
                ImageIcon icon = new ImageIcon(new URL(imageUrl));
                Image scaledImage = icon.getImage().getScaledInstance(
                    IMAGE_SIZE.width, IMAGE_SIZE.height, Image.SCALE_SMOOTH);
                JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
                imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                card.add(imageLabel);
            } catch (Exception e) {
                JLabel errorLabel = new JLabel("Image not available", SwingConstants.CENTER);
                errorLabel.setForeground(Color.RED);
                card.add(errorLabel);
            }

            card.add(Box.createRigidArea(new Dimension(0, 10)));

            JLabel nameLabel = new JLabel(name, SwingConstants.CENTER);
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            nameLabel.setForeground(TEXT_COLOR);
            nameLabel.setFont(NAME_FONT);
            card.add(nameLabel);

            JLabel typeLabel = new JLabel(type, SwingConstants.CENTER);
            typeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            typeLabel.setForeground(getTypeColor());
            card.add(typeLabel);

            return card;
        }

        protected abstract Color getTypeColor();
    }

    static class DPSCharacter extends GameCharacter {
        public DPSCharacter(String name, String imageUrl) {
            super(name, imageUrl, "DPS");
        }

        @Override
        protected Color getTypeColor() {
            return new Color(255, 100, 100);
        }
    }

    static class SupportCharacter extends GameCharacter {
        public SupportCharacter(String name, String imageUrl) {
            super(name, imageUrl, "Support");
        }

        @Override
        protected Color getTypeColor() {
            return new Color(100, 255, 100);
        }
    }

    static class TankCharacter extends GameCharacter {
        public TankCharacter(String name, String imageUrl) {
            super(name, imageUrl, "Tank");
        }

        @Override
        protected Color getTypeColor() {
            return new Color(100, 100, 255);
        }
    }

    private static JPanel createNavBar() {
        JPanel navBar = new JPanel(new BorderLayout());
        navBar.setBackground(NAVBAR_COLOR);
        navBar.setPreferredSize(NAVBAR_SIZE);
        navBar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel title = new JLabel("Honkai: Star Rail Character List");
        title.setFont(TITLE_FONT);
        title.setForeground(TEXT_COLOR);
        navBar.add(title, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setOpaque(false);

        JButton discordBtn = createStyledButton("Join Discord", new Color(88, 101, 242));
        discordBtn.setPreferredSize(new Dimension(150, 40));

        JButton kofiBtn = createStyledButton("Buy us a Ko-Fi", new Color(255, 85, 85));
        kofiBtn.setPreferredSize(new Dimension(150, 40));

        buttonPanel.add(discordBtn);
        buttonPanel.add(kofiBtn);
        navBar.add(buttonPanel, BorderLayout.EAST);

        return navBar;
    }

    private static JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(BUTTON_FONT);
        button.setForeground(TEXT_COLOR);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        
        // Add press and hover effects
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
        mainContent.add(createCharacterGrid(), BorderLayout.CENTER);
        return mainContent;
    }

    private static JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setBackground(SIDEBAR_COLOR);
        sidebar.setPreferredSize(SIDEBAR_SIZE);
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Create each sidebar button with effects
        JButton homeBtn = createMenuButton("Home");
        JButton charactersBtn = createMenuButton("Characters");
        JButton tierListBtn = createMenuButton("Tier List");
        JButton memoryBtn = createMenuButton("Memory of Chaos");
        JButton lightConesBtn = createMenuButton("Light Cones");
        JButton relicsBtn = createMenuButton("Relics");
        JButton guidesBtn = createMenuButton("Guides");
        JButton toolsBtn = createMenuButton("Tools");

        // Add buttons with spacing
        sidebar.add(homeBtn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(charactersBtn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(tierListBtn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(memoryBtn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(lightConesBtn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(relicsBtn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(guidesBtn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(toolsBtn);

        return sidebar;
    }

    private static JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setFont(BUTTON_FONT);
        button.setForeground(TEXT_COLOR);
        button.setBackground(BUTTON_COLOR);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        
        // Add press and hover effects
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
        
        return button;
    }

    private static JScrollPane createCharacterGrid() {
        JPanel gridPanel = new JPanel(new GridLayout(0, 4, 20, 20));
        gridPanel.setBackground(BACKGROUND_COLOR);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        List<GameCharacter> characters = new ArrayList<>();
        characters.add(new DPSCharacter("Acheron", "https://example.com/acheron.jpg"));
        characters.add(new SupportCharacter("Asta", "https://example.com/asta.jpg"));
        characters.add(new TankCharacter("Aventurine", "https://example.com/aventurine.jpg"));
        characters.add(new DPSCharacter("Argenti", "https://example.com/argenti.jpg"));
        characters.add(new SupportCharacter("Bailu", "https://example.com/bailu.jpg"));
        characters.add(new TankCharacter("Fire MC", "https://example.com/firemc.jpg"));
        characters.add(new DPSCharacter("Kafka", "https://example.com/kafka.jpg"));
        characters.add(new SupportCharacter("Luocha", "https://example.com/luocha.jpg"));
        characters.add(new TankCharacter("Gepard", "https://example.com/gepard.jpg"));
        characters.add(new DPSCharacter("Seele", "https://example.com/seele.jpg"));
        characters.add(new SupportCharacter("Tingyun", "https://example.com/tingyun.jpg"));
        characters.add(new TankCharacter("March 7th", "https://example.com/march7th.jpg"));
        characters.add(new DPSCharacter("Dan Heng", "https://example.com/danheng.jpg"));
        characters.add(new SupportCharacter("Bronya", "https://example.com/bronya.jpg"));
        characters.add(new TankCharacter("Clara", "https://example.com/clara.jpg"));

        for (GameCharacter character : characters) {
            gridPanel.add(character.createDisplay());
        }

        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        return scrollPane;
    }
}