package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PopUpAttemptDoor {
    private boolean retryAttempt;
    JDialog myDialog;

    public PopUpAttemptDoor(Frame parent) throws IOException {

        myDialog = new JDialog((Frame) null, true);
        myDialog.setSize(400, 300);
        myDialog.setLocationRelativeTo(parent);

        // Create components
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\12063\\Desktop\\Intellij Projects\\Trivia Maze" +
                "\\Disney-Trivia-Maze\\src\\res\\Miscellaneous_images\\3d-question-Image.png");

        System.out.println(imageIcon);
        JLabel messageLabel = new JLabel();
        //messageLabel.setText("Do you want to attempt the door again?");
        messageLabel.setIcon(imageIcon);

        messageLabel.setFont( new Font("Berlin Sans FB", Font.PLAIN, 20));

        JButton yesButton = new JButton("Yes");
        yesButton.setFocusable(false);
        yesButton.setBackground(new Color(42,187,156)); // Green color for the yes button

        JButton noButton = new JButton("No");
        noButton.setFocusable(false);
        noButton.setBackground(new Color(229,77,66)); // Red color for the yes button

        // Set layout
        myDialog.setLayout(new BorderLayout());

        // Add components to the dialog
        JPanel messagePanel = new JPanel(new FlowLayout());
        messagePanel.add(messageLabel);
        myDialog.add(messagePanel, BorderLayout.NORTH);


        //------------------------------------------------------------------------------------------------------------

        JPanel buttonPanel = new JPanel(new GridLayout(1,2));
        buttonPanel.setPreferredSize(new Dimension(400,60));
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        myDialog.add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retryAttempt = true;
                myDialog.dispose();
            }
        });

        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retryAttempt = false;
                myDialog.dispose();
            }
        });

        // Set dialog properties
        myDialog.setResizable(false);
    }

    public boolean showDialog() {
        myDialog.setVisible(true);
        return retryAttempt;
    }

    public static void main(String[] args) throws IOException {

        Frame frame = null;
        PopUpAttemptDoor dialog = new PopUpAttemptDoor(frame);
        boolean retry = dialog.showDialog();
        System.out.println("Retry attempt: " + retry);

    }
}