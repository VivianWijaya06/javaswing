package week;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MemberCard extends JPanel {
    private final String name;
    private final String imagePath;
    private final Color unitColor;

    public MemberCard(String name, String imagePath, Color unitColor) {
        this.name = name;
        this.imagePath = imagePath;
        this.unitColor = unitColor;
        initializeCard();
    }

    public Color getUnitColor() {
        return this.unitColor;
    }

    private void initializeCard() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Colors.CARD_BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setPreferredSize(Dimensions.CARD_SIZE);
        setMaximumSize(Dimensions.CARD_SIZE);
        setMinimumSize(Dimensions.CARD_SIZE);
        
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(Colors.CARD_BACKGROUND.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(Colors.CARD_BACKGROUND);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                setBackground(Colors.CARD_BACKGROUND.darker());
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBackground(Colors.CARD_BACKGROUND.brighter());
            }
        });

        add(createMemberImage());
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(createMemberNameLabel());
        add(createMemberUnitLabel());
    }

    private JLabel createMemberImage() {
        JLabel imageLabel;
        try {
            ImageIcon icon = new ImageIcon(new URL(imagePath));
            Image scaledImage = icon.getImage().getScaledInstance(
                Dimensions.IMAGE_SIZE.width, Dimensions.IMAGE_SIZE.height, Image.SCALE_SMOOTH);
            imageLabel = new JLabel(new ImageIcon(scaledImage));
            
            imageLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    ImageDialog.showEnlargedImage(imagePath);
                }
            });
            imageLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
        } catch (Exception e) {
            imageLabel = new JLabel("Image not found", SwingConstants.CENTER);
            imageLabel.setBackground(new Color(60, 60, 80));
            imageLabel.setOpaque(true);
            imageLabel.setForeground(Color.LIGHT_GRAY);
            imageLabel.setFont(Fonts.PLACEHOLDER_FONT);
        }
        imageLabel.setPreferredSize(Dimensions.IMAGE_SIZE);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return imageLabel;
    }

    private JLabel createMemberNameLabel() {
        JLabel label = new JLabel(name, SwingConstants.CENTER);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setForeground(Colors.TEXT_COLOR);
        label.setFont(Fonts.NAME_FONT);
        return label;
    }

    private JLabel createMemberUnitLabel() {
        JLabel label = new JLabel(getUnitName(), SwingConstants.CENTER);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setForeground(unitColor);
        return label;
    }

    private String getUnitName() {
        if (unitColor.equals(Colors.NCT127_COLOR)) return "NCT 127";
        if (unitColor.equals(Colors.NCTDREAM_COLOR)) return "NCT Dream";
        if (unitColor.equals(Colors.WAYV_COLOR)) return "WayV";
        if (unitColor.equals(Colors.NCTWISH_COLOR)) return "NCT Wish";
        if (unitColor.equals(Colors.EXMEMBER_COLOR)) return "Ex Member";
        if (unitColor.equals(Colors.NCTU_COLOR)) return "NCT U";
        return "NCT";
    }

}