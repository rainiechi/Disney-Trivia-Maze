package View;

import Model.Door;
import Model.Question;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;


public class PopUp implements ActionListener {
    private final static Color LIGHT_BLUE = new Color(228, 246, 248);
    private final static Color BLUE =new Color(210, 246, 250);

    /**
     *
     */
    private JDialog myDialog;

    /**
     * The Question Panel contains the question
     */
    private JPanel myQuestionPanel;
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
    private final JButton myOption1;
    /**
     * Radio buttons for option2
     */
    private final JButton myOption2;
    /**
     * Radio buttons for option3
     */
    private final JButton myOption3;
    /**
     * Radio buttons for option4
     */
    private final JButton myOption4;
    /**
     * It is the correct answer to the question.
     */
    private String myCorrectAnswer;

    private Door myDoor;

    public static void main(String[] args) {

        new PopUp();

    }

    public PopUp() {
        //myFrame = new JFrame();
        myDialog = new JDialog((Frame) null, true);
        myQuestionPanel = new JPanel();
        myQuestionArea = new JTextArea();
        myOptionPanel = new JPanel();
        myOption1 = new JButton();
        myOption2 = new JButton();
        myOption3 = new JButton();
        myOption4 = new JButton();


        loadQuestions("Hello world very long "
                ,"option1 should be long and what if it is very very very long",
                "option2", "option3", "option4", "option2" );
        initializeUI(new GamePanel());

        //displayQuestion(true);
    }

    //    public PopUp(final Door theDoor) {
//        if (theDoor == null) {
//            throw new NullPointerException("Door cannot be null");
//        }
//        myDoor = theDoor;
//        myDialog = new JDialog((Frame) null, true);
//        myQuestionPanel = new JPanel();
//        myQuestionArea = new JTextArea();
//        myOptionPanel = new JPanel();
//        myOption1 = new JButton();
//        myOption2 = new JButton();
//        myOption3 = new JButton();
//        myOption4 = new JButton();
//        loadQuestion(myDoor.getQuestionObject());
//        initializeUI(new GamePanel());
//    }
    private void loadQuestion(final Question theQuestion) {
        myQuestionArea.setText(theQuestion.getMyQuestion());
        myOption1.setText(theQuestion.getMyOption1());
        myOption2.setText(theQuestion.getMyOption2());
        myOption3.setText(theQuestion.getMyOption3());
        myOption4.setText(theQuestion.getMyOption4());
        myCorrectAnswer = theQuestion.getMyAnswer();
    }

    /**
     * To set up the GUI for the pop-up on the door.
     */
    private void initializeUI(final GamePanel theGP) {


        // JFrame for the pop up.
        myDialog.setTitle("Who want to be Disney Expert");
        myDialog.setSize(400,300);
        myDialog.setLayout(new BorderLayout());
        myDialog.setUndecorated(true);
        myDialog.setLocationRelativeTo(theGP);

        int cornerRadius = 40; // Adjust the value as per your preference
        myDialog.setShape(new RoundRectangle2D.Double(0, 0, 400, 300, cornerRadius, cornerRadius));


        myQuestionPanel.setSize(new Dimension(400,180));
        setGridBagLayoutForComponents(myQuestionArea.getText()
                ,myQuestionPanel,
                new Font("Berlin Sans FB", Font.PLAIN, 20),
                LIGHT_BLUE);



        //------------------------------------------------------------------------------------------------------------

        Font fontForButtons = new Font("Berlin Sans FB", Font.PLAIN, 16);

        // All the Radio button to select the answer from.
        myOption1.addActionListener(this);
        myOption1.setFont( fontForButtons);
        myOption1.setBackground(BLUE );
        myOption1.setFocusable(false);

        myOption2.addActionListener(this);
        myOption2.setFont( fontForButtons);
        myOption2.setBackground(Color.WHITE);
        myOption2.setFocusable(false);

        myOption3.addActionListener(this);
        myOption3.setFont( fontForButtons);
        myOption3.setBackground(Color.WHITE);
        myOption3.setFocusable(false);

        myOption4.addActionListener(this);
        myOption4.setFont( fontForButtons);
        myOption4.setBackground(BLUE);
        myOption4.setFocusable(false);

        myOptionPanel.add(myOption1);
        myOptionPanel.add(myOption2);
        myOptionPanel.add(myOption3);
        myOptionPanel.add(myOption4);

        myOptionPanel.setLayout(new GridLayout(2,2));
        myOptionPanel.setPreferredSize(new Dimension(400,120));
        //myOptionPanel.setOpaque(true);

        // Add the question panel and option panel to the dialog
        myDialog.add(myQuestionPanel, BorderLayout.CENTER);
        myDialog.add(myOptionPanel, BorderLayout.SOUTH);

        // Set the dialog to be visible
        myDialog.setVisible(true);

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
     * Method that allow the components to have text roll over to the next line.
     * @param theText
     * @param panel
     */
    public void setGridBagLayoutForComponents(String theText, JPanel  panel, Font theFont, Color theColor) {

        JTextArea textArea = new JTextArea();
        textArea.setText(theText);

        textArea.setFont(theFont);
        textArea.setBackground(theColor);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setFocusable(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 360;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        //gbc.fill = GridBagConstraints.CENTER;

        gbc.insets = new Insets(0, 20, 0, 20); // Set the desired insets (top, left, bottom, right)
        //gbc.ipadx=1;
        //gbc.anchor = GridBagConstraints.CENTER;


        panel.setLayout(new GridBagLayout());
        System.out.println(panel.getSize());
        System.out.println((panel.getWidth()*panel.getHeight())/360);

        //textArea.setSize(new Dimension( 360,(panel.getWidth()*panel.getHeight())/360));
        //panel.setBackground(theColor);
        panel.add(textArea, gbc);
    }


    /**
     * Display the GUI in the screen.
     * @param theQuestionDisplay It is used to decide whether to display the frame or not.
     */
    private void displayQuestion(final boolean theQuestionDisplay) {
        myDialog.setVisible(theQuestionDisplay);
    }

    /**
     * This method take in two parameters and check whether the user has selected the right answer.
     * @param theCorrectAnswer It is the correct answer to the question.
     * @param thePlayerAnswers It is the answer that the res.player selected.
     */
    private void checkAnswer(final String theCorrectAnswer, final String thePlayerAnswers) {
        if (theCorrectAnswer.equals(thePlayerAnswers)) {
            myDoor.setAttempted(true);
            myDoor.setMyUnlock(true);
            JOptionPane.showMessageDialog(myDialog, "Correct!");
        } else {
            myDoor.setAttempted(true);
            JOptionPane.showMessageDialog(myDialog, "Incorrect!");
        }
        myDialog.dispose();
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