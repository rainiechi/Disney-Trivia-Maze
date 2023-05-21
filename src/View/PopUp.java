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
//    /**
//     * The JFrame for the GUI.
//     */
//    private final JFrame myFrame;
    /**
     *
     */
    private JDialog myDialog;

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


        loadQuestions("Hello world and if the question is long it will wrap around "
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

//        myQuestionPanel.setBackground(LIGHT_BLUE);
//        myQuestionPanel.setLayout(null);
//
//        // Text Area to contain the question.
//        myQuestionArea.setFont( new Font("Berlin Sans FB", Font.PLAIN, 20));
//        myQuestionArea.setBackground(LIGHT_BLUE);
//
//        int GaponBothSides = 45;
//        int GaponTop = 60;
//
//        myQuestionArea.setBounds(GaponBothSides,GaponTop,
//                myDialog.getWidth()-2*GaponBothSides,180 - GaponTop);
//        myQuestionArea.setLineWrap(true);
//        myQuestionArea.setWrapStyleWord(true);
//        myQuestionArea.setEditable(false);
//        myQuestionArea.setFocusable(false);
//
//        myQuestionPanel.add(myQuestionArea);


        //myQuestionPanel.setBackground(LIGHT_BLUE);
        myQuestionPanel.setLayout(new GridBagLayout());

        myQuestionArea.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        myQuestionArea.setBackground(LIGHT_BLUE);
        myQuestionArea.setLineWrap(true);
        myQuestionArea.setWrapStyleWord(true);
        myQuestionArea.setEditable(false);
        myQuestionArea.setFocusable(false);

        myQuestionArea.setSize(340,1);


        // Create a GridBagConstraints instance
        GridBagConstraints gbc = new GridBagConstraints();

        //gbc.gridx=0;
        //gbc.gridy=0;
        //gbc.ipadx=1;
        //gbc.gridwidth = 360;
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        //gbc.insets = new Insets(10, 10, 10, 10);
        //gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.anchor = GridBagConstraints.CENTER;

        System.out.println("GridBagConstraints values:");
        System.out.println("Grid x: " + gbc.gridx);
        System.out.println("Grid y: " + gbc.gridy);
        System.out.println("Grid width: " + gbc.gridwidth);
        System.out.println("Grid height: " + gbc.gridheight);
        System.out.println("Anchor: " + gbc.anchor);
        System.out.println("Fill: " + gbc.fill);
        System.out.println("Insets: " + gbc.insets);
        System.out.println("IPad x: " + gbc.ipadx);
        System.out.println("IPad y: " + gbc.ipady);
        System.out.println("Weight x: " + gbc.weightx);
        System.out.println("Weight y: " + gbc.weighty);

        // Add the JTextArea to the JPanel with GridBagConstraints
        //myQuestionPanel.setBounds(20,10,360,160);
        myQuestionPanel.add(myQuestionArea, gbc);

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

        myQuestionPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.anchor = GridBagConstraints.CENTER;
        myQuestionPanel.add(myQuestionArea, gbc);

        JTextArea Option1Area = new JTextArea(theOption1);
        Option1Area.setLineWrap(true);
        Option1Area.setWrapStyleWord(true);

        myOption1.setLayout(new GridBagLayout());
        myOption1.add(Option1Area, gbc);

        myOption2.setText(theOption2);
        myOption3.setText(theOption3);
        myOption4.setText(theOption4);
        myCorrectAnswer = theCorrectAnswer;

    }

    public void setGridBagLayoutForComponents(String Text, Component components) {
        Container container = null;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.anchor = GridBagConstraints.CENTER;
        myQuestionPanel.add(myQuestionArea, gbc);

        if (components instanceof Container) {
            container = (Container) components;
            container.setLayout(new GridBagLayout());



            //container.add(components, constraints);
        }
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