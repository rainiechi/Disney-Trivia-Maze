package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DialogForYesNoAnswer {

    private boolean playerAnswer;
    JDialog myDialog;
    public DialogForYesNoAnswer(Frame parent, String labelText, Font labelFont, Color labelBackground,
                                Color labelForeground) throws IOException {

        myDialog =  new JDialog(parent, true);
        myDialog.setTitle("Who wants to be a Disney Expert");
        myDialog.setLayout(new BorderLayout());
        myDialog.setSize(400, 300);
        myDialog.setLocationRelativeTo(parent);

        // Create components
        JLabel messageLabel = new JLabel(labelText);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setVerticalAlignment(JLabel.CENTER);
        messageLabel.setFont(labelFont);
        messageLabel.setBackground(labelBackground);
        messageLabel.setForeground(labelForeground);
        messageLabel.setOpaque(true);

        // Add components to the dialog
        myDialog.add(messageLabel, BorderLayout.CENTER);

        JButton yesButton = new JButton("Yes");
        yesButton.setFocusable(false);
        yesButton.setBackground(new Color(42, 187, 156)); // Green color for the yes button

        JButton noButton = new JButton("No");
        noButton.setFocusable(false);
        noButton.setBackground(new Color(229, 77, 66)); // Red color for the no button

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.setPreferredSize(new Dimension(400, 60));
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        myDialog.add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerAnswer = true;
                myDialog.dispose();
            }
        });

        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerAnswer = false;
                myDialog.dispose();
            }
        });

        // Set dialog properties
        myDialog.setResizable(false);
        myDialog.setVisible(true);
    }

    public boolean getPlayerAnswer() {
        return playerAnswer;
    }
}