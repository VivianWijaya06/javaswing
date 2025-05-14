package week;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageDialog {
    public static void showEnlargedImage(String imageUrl) {
        JDialog dialog = new JDialog();
        dialog.setLayout(new BorderLayout());
        dialog.setModal(true);
        
        try {
            ImageIcon originalIcon = new ImageIcon(new URL(imageUrl));
            Image image = originalIcon.getImage();
            Image scaledImage = image.getScaledInstance(600, 500, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            imageLabel.setHorizontalAlignment(JLabel.CENTER);
            
            JScrollPane scrollPane = new JScrollPane(imageLabel);
            dialog.add(scrollPane, BorderLayout.CENTER);
        } catch (Exception e) {
            JLabel errorLabel = new JLabel("Cannot load image", JLabel.CENTER);
            dialog.add(errorLabel);
        }
        
        dialog.setSize(650, 600);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}