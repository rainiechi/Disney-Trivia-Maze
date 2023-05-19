package View;

import Model.Door;
import Model.MockQuestion;
import Model.Question;
import Model.MockDoor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestPopUp {
    private final static Color LIGHT_BLUE = new Color(228, 246, 248);
    private final static Color BLUE =new Color(210, 246, 250);

    private Door myDoor; //change to real door later
    private Question myQuestion;
    private JDialog dialog;

    JLabel questionLabel;
    JButton optionButton1;
    JButton optionButton2;
    JButton optionButton3;
    JButton optionButton4;

    public TestPopUp(final GamePanel theGP) {
        myDoor = new Door();
        myQuestion = myDoor.getQuestionObject();
        loadQuestion();
        createTriviaDialog(theGP);
    }

    public void loadQuestion() {
        questionLabel = new JLabel(myQuestion.getMyQuestion());
        optionButton1 = new JButton(myQuestion.getMyOption1());
        optionButton2 = new JButton(myQuestion.getMyOption2());
        optionButton3 = new JButton(myQuestion.getMyOption3());
        optionButton4 = new JButton(myQuestion.getMyOption4());
    }

    private void createTriviaDialog(final GamePanel theGP) {
        // Create the JDialog
        dialog = new JDialog((Frame) null, true);
        dialog.setSize(400, 300);
        dialog.setLayout(new BorderLayout());
        dialog.setUndecorated(true);
        dialog.setLocationRelativeTo(theGP);

        // Create a panel for the question
        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BorderLayout());

        // Add the question label to the question panel
        questionLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionPanel.setBackground(LIGHT_BLUE);
        questionPanel.add(questionLabel, BorderLayout.CENTER);

        // Create a panel for the option buttons
        JPanel optionPanel = new JPanel();
        optionPanel.setPreferredSize(new Dimension(400,120));
        optionPanel.setLayout(new GridLayout(2, 2));

        Font buttonFont = new Font("Berlin Sans FB", Font.PLAIN, 16);
        optionButton1.setFont(buttonFont);
        optionButton2.setFont(buttonFont);
        optionButton3.setFont(buttonFont);
        optionButton4.setFont(buttonFont);

        addButtonListener();

        optionButton1.setBackground(BLUE );
        optionButton2.setBackground(Color.WHITE);
        optionButton3.setBackground(Color.WHITE);
        optionButton4.setBackground(BLUE);

        // Add the option buttons to the option panel
        optionPanel.add(optionButton1);
        optionPanel.add(optionButton2);
        optionPanel.add(optionButton3);
        optionPanel.add(optionButton4);

        // Add the question panel and option panel to the dialog
        dialog.add(questionPanel, BorderLayout.CENTER);
        dialog.add(optionPanel, BorderLayout.SOUTH);

        // Set the dialog to be visible
        dialog.setVisible(true);
    }

    //Change later, rn it just closes the dialog
    public void addButtonListener() {
        optionButton1.addActionListener(theEvent -> dialog.dispose());
        optionButton2.addActionListener(theEvent -> dialog.dispose());
        optionButton3.addActionListener(theEvent -> dialog.dispose());
        optionButton4.addActionListener(theEvent -> dialog.dispose());
    }

}


