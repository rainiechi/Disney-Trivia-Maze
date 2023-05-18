package View;

import Model.Door;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PopUp implements ActionListener {
    /**
     * The JFrame for the GUI.
     */
    private final JFrame myFrame;
    /**
     * The Question Panel contains the question
     */
    private final JPanel myQuestionPanel;
    /**
     * The text area for the Question.
     */
    private final JTextArea myQuestionArea;
    /**
     * JPanel to contain the options.
     */
    private final JPanel myOptionPanel;
    /**
     * Radio buttons for option1
     */
    private final JRadioButton myOption1;
    /**
     * Radio buttons for option2
     */
    private final JRadioButton myOption2;
    /**
     * Radio buttons for option3
     */
    private final JRadioButton myOption3;
    /**
     * Radio buttons for option4
     */
    private final JRadioButton myOption4;
    /**
     * Option group that hold all the option Radio buttons
     */
    private final ButtonGroup myOptionGroup;
    /**
     * It is the correct answer to the question.
     */
    private String myCorrectAnswer;
    private Door myDoor;

//    public static void main(String[] args) {
//
//        new PopUp();
//    }

    public PopUp(Door theDoor) {
        myFrame = new JFrame();
        myDoor = theDoor;
        myQuestionPanel = new JPanel();
        myQuestionArea = new JTextArea();
        myOptionPanel = new JPanel();
        myOption1 = new JRadioButton();
        myOption2 = new JRadioButton();
        myOption3 = new JRadioButton();
        myOption4 = new JRadioButton();
        myOptionGroup = new ButtonGroup();

        loadQuestions("The question is should be here and if the question is long it should not matter hopefully "
                ,"option1",
                "option2", "option3", "option4", "option2" );
        initializeUI();
        displayQuestion(true);
    }

    /**
     * To set up the GUI for the pop-up on the door.
     */
    private void initializeUI()
    {
        // JFrame for the pop up.
        myFrame.setTitle("Who want to be Disney Expert");
        myFrame.setResizable(false);
        myFrame.setSize(400,400);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLayout(null);
        myFrame.setLocationRelativeTo(null);

        // Question Panel to contain the question for the door.
        myQuestionPanel.setBounds(0,0,myFrame.getWidth(),myFrame.getHeight()/2);
        myQuestionPanel.setLayout(null);
        myQuestionPanel.setBackground(Color.WHITE);

        // Text Area to contain the question.
        myQuestionArea.setFont( new Font("Times New Roman", Font.PLAIN, 24));
        myQuestionArea.setLineWrap(true);
        myQuestionArea.setWrapStyleWord(true);
        myQuestionArea.setBounds(20,40,myFrame.getWidth()-50,myFrame.getHeight()/2);

        myQuestionPanel.add(myQuestionArea);
        //------------------------------------------------------------------------------------------------------------

        // All the Radio button to select the answer from.
        myOption1.addActionListener(this);
        myOption1.setFont( new Font("Times New Roman", Font.PLAIN, 18));
        myOption2.addActionListener(this);
        myOption2.setFont( new Font("Times New Roman", Font.PLAIN, 18));
        myOption3.addActionListener(this);
        myOption3.setFont( new Font("Times New Roman", Font.PLAIN, 18));
        myOption4.addActionListener(this);
        myOption4.setFont( new Font("Times New Roman", Font.PLAIN, 18));

        // Adding all the button to the option group.
        myOptionGroup.add(myOption1);
        myOptionGroup.add(myOption2);
        myOptionGroup.add(myOption3);
        myOptionGroup.add(myOption4);

        myOptionPanel.add(myOption1);
        myOptionPanel.add(myOption2);
        myOptionPanel.add(myOption3);
        myOptionPanel.add(myOption4);

        myOptionPanel.setBounds(50, myFrame.getHeight()/2 ,myFrame.getWidth(),myFrame.getHeight()/2-50);
        myOptionPanel.setLayout(new GridLayout(2,2));
        myOptionPanel.setOpaque(true);

        myFrame.add(myQuestionPanel);
        myFrame.add(myOptionPanel);

    }

    /**
     * Takes in different parameters such as questions options and answer and assign different texts
     * to buttons based on parameters
     *
     * @param theQuestion  It is the question to display on the screen
     * @param theOption1   It is the first option displayed to the res.player.
     * @param theOption2   It is the second option displayed to the res.player.
     * @param theOption3   It is the third option displayed to the res.player.
     * @param theOption4   It is the fourth option displayed to the res.player.
     * @param theCorrectAnswer It is the correct answer to the problem
     */
    private void loadQuestions( final String theQuestion, final String theOption1, final String theOption2,
                                final String theOption3, final String theOption4, final String theCorrectAnswer  ) {
        myQuestionArea.setText(theQuestion);
        myOption1.setText(theOption1);
        myOption2.setText(theOption2);
        myOption3.setText(theOption3);
        myOption4.setText(theOption4);
        myCorrectAnswer = theCorrectAnswer;

    }

    /**
     * Display the GUI in the screen.
     * @param theQuestionDisplay It is used to decide whether to display the frame or not.
     */
    private void displayQuestion(final boolean theQuestionDisplay) {
        myFrame.setVisible(theQuestionDisplay);
    }

    /**
     * This method take in two parameters and check whether the user has selected the right answer.
     * @param theCorrectAnswer It is the correct answer to the question.
     * @param thePlayerAnswers It is the answer that the res.player selected.
     */
    private void checkAnswer(final String theCorrectAnswer, final String thePlayerAnswers) {
        if (theCorrectAnswer.equals(thePlayerAnswers)) {
            JOptionPane.showMessageDialog(myFrame, "Correct!");
            myDoor.setAttempted(true);

        } else {
            JOptionPane.showMessageDialog(myFrame, "Incorrect!");
            myDoor.setAttempted(true);
        }
    }

    /**
     * Action perform methods for all the button. It records the user answer.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed( final ActionEvent e) {

        String playerAnswer;
        if(e.getSource() == myOption1) {
            playerAnswer = myOption1.getText();
            checkAnswer(myCorrectAnswer, playerAnswer);
        }
        else if(e.getSource() == myOption2) {
            playerAnswer = myOption2.getText();
            checkAnswer(myCorrectAnswer, playerAnswer);
        }
        else if(e.getSource() == myOption3) {
            playerAnswer = myOption3.getText();
            checkAnswer(myCorrectAnswer, playerAnswer);
        }
        else if(e.getSource() == myOption4) {
            playerAnswer = myOption4.getText();
            checkAnswer(myCorrectAnswer, playerAnswer);
        }
    }
}