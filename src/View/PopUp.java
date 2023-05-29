package View;
import Model.Door;
import Model.Question;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.Random;

public class PopUp implements ActionListener {
    private final static Color LIGHT_BLUE = new Color(228, 246, 248);
    private final static Color BLUE = new Color(210, 246, 250);

    private final static Color DARK_BLUE = new Color(123, 195, 203);

    private GamePanel myGP;
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

    private Timer timer;
    private final int myCountdown;
    private JLabel timerLabel;
    public PopUp(final Door theDoor, final GamePanel theGP){
        if (theDoor == null || theGP == null) {
            throw new IllegalArgumentException("Door and GamePanel cannot be null");
        }
        myGP = theGP;
        myCountdown = myGP.getMyGame().getTime();; // Countdown duration in seconds
        myDoor = theDoor;
        myDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(theGP), true); //attach the dialog to the frame so they dont seperate
        myQuestionPanel = new JPanel();
        myQuestionArea = new JTextArea();
        myOptionPanel = new JPanel();
        myOption1 = new JButton();
        myOption2 = new JButton();
        myOption3 = new JButton();
        myOption4 = new JButton();
        loadQuestion(theDoor.getQuestionObject());

        timerGUISetup();
        startTimer();
        initializeUI();
        //displayQuestion(true);
    }

    public void timerGUISetup() {
        timerLabel = new JLabel();
        timerLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 17));
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timerLabel.setVerticalAlignment(SwingConstants.CENTER);
        timerLabel.setPreferredSize(new Dimension(100, 50));
        timerLabel.setOpaque(true);
        timerLabel.setBackground(LIGHT_BLUE);
        timerLabel.setForeground(DARK_BLUE);
    }
    public void startTimer() {
        // Create the timer with a 1-second delay
        timer = new Timer(1000, new ActionListener() {
            private int remainingTime = myCountdown;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (remainingTime <= 0) {
                    timer.stop();
                    myDialog.dispose();
                    // Perform actions when time is up
                    myDoor.setAttempted(true);
                    myDoor.setMyUnlock(false);
                } else {
                    // Update the countdown label or perform other actions
                    System.out.println("Time Remaining: " + remainingTime);
                    timerLabel.setText("Time Remaining: " + remainingTime);
                    remainingTime--;
                }
            }
        });

        timer.start(); // Start the timer
    }

    /**
     * Loads the Question object to the popup.
     * @param theQuestion the Question to be loaded
     */
    private void loadQuestion ( final Question theQuestion){
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
    private void initializeUI () {
        // JFrame for the pop up.
        myDialog.setTitle("Who want to be Disney Expert");
        myDialog.setSize(400, 300);
        myDialog.setLayout(new BorderLayout());
        myDialog.setUndecorated(true);
        myDialog.setLocationRelativeTo(myGP);
        int cornerRadius = 40; // Adjust the value as per your preference
        myDialog.setShape(new RoundRectangle2D.Double(0, 0, 400, 300, cornerRadius, cornerRadius));
        myQuestionPanel.setBackground(LIGHT_BLUE);
        myQuestionPanel.setLayout(null);
        // Text Area to contain the question.
        myQuestionArea.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        myQuestionArea.setBackground(LIGHT_BLUE);
        int GaponBothSides = 45;
        int GaponTop = 25;
        myQuestionArea.setBounds(GaponBothSides, GaponTop,
                myDialog.getWidth() - 2 * GaponBothSides, 180 - GaponTop);
        myQuestionArea.setLineWrap(true);
        myQuestionArea.setWrapStyleWord(true);
        myQuestionArea.setEditable(false);
        myQuestionArea.setFocusable(false);
        myQuestionPanel.add(myQuestionArea);
        //------------------------------------------------------------------------------------------------------------
        Font fontForButtons = new Font("Berlin Sans FB", Font.PLAIN, 16);
        // All the Radio button to select the answer from.
        myOption1.addActionListener(this);
        myOption1.setFont(fontForButtons);
        myOption1.setBackground(BLUE);
        myOption2.addActionListener(this);
        myOption2.setFont(fontForButtons);
        myOption2.setBackground(Color.WHITE);
        myOption3.addActionListener(this);
        myOption3.setFont(fontForButtons);
        myOption3.setBackground(Color.WHITE);
        myOption4.addActionListener(this);
        myOption4.setFont(fontForButtons);
        myOption4.setBackground(BLUE);
        myOptionPanel.add(myOption1);
        myOptionPanel.add(myOption2);
        myOptionPanel.add(myOption3);
        myOptionPanel.add(myOption4);
        myOptionPanel.setLayout(new GridLayout(2, 2));
        myOptionPanel.setPreferredSize(new Dimension(400, 120));
        //myOptionPanel.setOpaque(true);
        // Add the question panel and option panel to the dialog
        myDialog.add(timerLabel, BorderLayout.NORTH);
        myDialog.add(myQuestionPanel, BorderLayout.CENTER);
        myDialog.add(myOptionPanel, BorderLayout.SOUTH);
        // Set the dialog to be visible
        myDialog.setVisible(true);
    }




    /**
     * Display the GUI in the screen.
     * @param theQuestionDisplay It is used to decide whether to display the frame or not.
     */
    private void displayQuestion ( final boolean theQuestionDisplay){
        myDialog.setVisible(theQuestionDisplay);
    }
    /**
     * This method take in two parameters and check whether the user has selected the right answer.
     * @param theCorrectAnswer It is the correct answer to the question.
     * @param thePlayerAnswers It is the answer that the res.player selected.
     */
    private void checkAnswer(final String theCorrectAnswer, final String thePlayerAnswers){
        if (theCorrectAnswer.equals(thePlayerAnswers)) {
            myDoor.setMyUnlock(true);
            showResultDialog(true);
        } else {
            myDoor.setAttempted(true);
            myDoor.setMyUnlock(false);
            showResultDialog(false);
        }
    }

    void disableWrongAnswerButton(int theNumberOfButtons){
        if (theNumberOfButtons >= 0 &&  theNumberOfButtons <= 3   ) {
            int counter=0;
            Random random = new Random();
            int randomNumber;
            while(counter<theNumberOfButtons){
                randomNumber = random.nextInt(4) + 1;
                switch (randomNumber) {
                    case 1:
                        if(!myOption1.getText().equals(myCorrectAnswer)  && myOption1.isEnabled()){
                            myOption1.setEnabled(false);
                            counter++;
                        }
                        break;
                    case 2:
                        if(!myOption2.getText().equals(myCorrectAnswer)  && myOption2.isEnabled()){
                            myOption2.setEnabled(false);
                            counter++;
                        }
                        break;
                    case 3:
                        if(!myOption3.getText().equals(myCorrectAnswer)  && myOption3.isEnabled() ){
                            myOption3.setEnabled(false);
                            counter++;
                        }
                        break;
                    case 4:
                        if(!myOption4.getText().equals(myCorrectAnswer)  && myOption4.isEnabled()){
                            myOption4.setEnabled(false);
                            counter++;
                        }
                        break;
                    default:
                        System.out.println("Invalid case");
                }
            }
        } else {
            myOption1.setEnabled(false);
            myOption2.setEnabled(false);
            myOption3.setEnabled(false);
            myOption4.setEnabled(false);

        }
    }

    /**
     * Action perform methods for all the button. It records the user answer.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed ( final ActionEvent e){
        String playerAnswer;
        if (e.getSource() == myOption1) {
            timer.stop();
            playerAnswer = myOption1.getText();
            checkAnswer(myCorrectAnswer, playerAnswer);
        } else if (e.getSource() == myOption2) {
            timer.stop();
            playerAnswer = myOption2.getText();
            checkAnswer(myCorrectAnswer, playerAnswer);
        } else if (e.getSource() == myOption3) {
            timer.stop();
            playerAnswer = myOption3.getText();
            checkAnswer(myCorrectAnswer, playerAnswer);
        } else if (e.getSource() == myOption4) {
            timer.stop();
            playerAnswer = myOption4.getText();
            checkAnswer(myCorrectAnswer, playerAnswer);
        }
    }


    /**
     * Helper method that displays the result panel once player answers.
     * @param theCorrect whether the question was correctly answered
     */
    private void showResultDialog(final boolean theCorrect) {
        ResultPanel panel = new ResultPanel(theCorrect);
        myDialog.dispose(); //closes the trivia dialog
        JDialog dialog = new JDialog(myDialog, "Dialog", true);
        dialog.getContentPane().add(panel);
        dialog.setUndecorated(true);
        dialog.pack();
        dialog.setLocationRelativeTo(myDialog);
        dialog.setVisible(true);
    }

    /**
     * Helper class to create the result popup once player answers
     */
    class ResultPanel extends JPanel {
        private static final int BORDER = 15;
        private static final Color LIGHT_PINK = new Color(255, 182, 193);
        private static final Color DARK_PINK = new Color(162, 72, 87);
        private static final Color RED = new Color(139, 0, 0);
        private static final Color BABY_PINK = new Color(245,218,223);
        private static final Color BABY_GREEN = new Color(230,255,239);
        private static final Color GREEN = new Color(95, 133, 117);
        private static final Color DARK_GREEN = new Color(53, 94, 59);
        private static final Color PASTEL_GREEN = new Color(193, 225, 193);

        public ResultPanel(Boolean correct) {
            JButton continueButton = new JButton("CONTINUE");
            JLabel resultLabel1;
            JLabel resultLabel2;
            if (correct) {
                setBackground(PASTEL_GREEN);
                resultLabel1 = new JLabel("CORRECT");
                resultLabel1.setForeground(DARK_GREEN);
                resultLabel2 = new JLabel("You've unlocked this door!");
                resultLabel2.setForeground(GREEN);
                continueButton.setBackground(BABY_GREEN);
                continueButton.setForeground(DARK_GREEN);
                continueButton.setBorder(BorderFactory.createLineBorder(GREEN, 1));
            } else {
                setBackground(LIGHT_PINK);
                resultLabel1 = new JLabel("INCORRECT");
                resultLabel1.setForeground(RED);
                resultLabel2 = new JLabel("The door will remain locked!");
                resultLabel2.setForeground(DARK_PINK);
                continueButton.setBackground(BABY_PINK);
                continueButton.setForeground(RED);
                continueButton.setBorder(BorderFactory.createLineBorder(DARK_PINK, 1));
            }


            JPanel resultPanel1 = new JPanel();
            resultPanel1.setOpaque(false);
            resultPanel1.add(resultLabel1);

            JPanel resultPanel2 = new JPanel();
            resultPanel2.setOpaque(false);
            resultPanel2.add(resultLabel2);

            setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER,BORDER));
            setLayout(new GridLayout(3, 1, 10, 10));
            add(resultPanel1);
            add(resultPanel2);
            add(continueButton);

            //disposes the window and resumes game
            continueButton.addActionListener(theEvent -> {
                Component comp = (Component) theEvent.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();
            });

        }

    }
}

