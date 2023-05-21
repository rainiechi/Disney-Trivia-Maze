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

    public DialogForYesNoAnswer(Frame parent, String theImage) throws IOException {

        myDialog = new JDialog((Frame) null, true);
        myDialog.setTitle("Who want to be Disney Expert");
        myDialog.setLayout(new BorderLayout());
        myDialog.setSize(400, 300);
        myDialog.setLocationRelativeTo(parent);

        // Create components
        ImageIcon originalIcon = new ImageIcon(theImage);


        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(400, 205, Image.SCALE_SMOOTH);

        JLabel messageLabel = new JLabel();
        messageLabel.setIcon(new ImageIcon(resizedImage));

        // Add components to the dialog

        myDialog.add(messageLabel, BorderLayout.NORTH);


        //------------------------------------------------------------------------------------------------------------
        JButton yesButton = new JButton("Yes");
        yesButton.setFocusable(false);
        yesButton.setBackground(new Color(42,187,156)); // Green color for the yes button

        JButton noButton = new JButton("No");
        noButton.setFocusable(false);
        noButton.setBackground(new Color(229,77,66)); // Red color for the yes button

        JPanel buttonPanel = new JPanel(new GridLayout(1,2));
        buttonPanel.setPreferredSize(new Dimension(400,60));
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

    public boolean getplayerAnswer() {
        return playerAnswer;
    }

}