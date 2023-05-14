package SQLite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Component.CENTER_ALIGNMENT;
import static java.awt.Component.TOP_ALIGNMENT;

public class PopUp implements ActionListener {

    private JFrame myFrame;
    private JPanel myQuestionPanel;
    private JTextArea myQuestionArea;
    private JPanel myOptionPanel;

    private JRadioButton myoption1, myoption2, myoption3, myoption4;

    private ButtonGroup myOptionGroup;
    //private ArrayList<Question> questionList;
    private String myCorrectAnswer;
    private String myPlayerAnswer;

    public static void main(String[] args) {

        new PopUp();
    }

    public PopUp() {
        myFrame = new JFrame();
        myQuestionPanel = new JPanel();
        myQuestionArea = new JTextArea();
        myOptionPanel = new JPanel();
        myoption1 = new JRadioButton();
        myoption2 = new JRadioButton();
        myoption3 = new JRadioButton();
        myoption4 = new JRadioButton();
        myOptionGroup = new ButtonGroup();

//        loadQuestions("The question is should be here and if the question is long it should not matter hopefully "
//                ,"option1",
//                "option2", "option3", "option4", "option2" );

        initializeUI();
        displayQuestion(true);
    }

    private void initializeUI()
    {

        myFrame.setTitle("Who want to be Disney Expert");
        myFrame.setResizable(false);
        myFrame.setSize(400,400);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLayout(null);
        myFrame.setLocationRelativeTo(null);


        myQuestionPanel.setBounds(0,0,myFrame.getWidth(),myFrame.getHeight()/2);
        myQuestionPanel.setLayout(null);
        myQuestionPanel.setBackground(Color.WHITE);

        myQuestionArea.setFont( new Font("Times New Roman", Font.PLAIN, 24));
        myQuestionArea.setLineWrap(true);
        myQuestionArea.setWrapStyleWord(true);
        myQuestionArea.setBounds(0,50,myFrame.getWidth(),myFrame.getHeight()/2);


        myQuestionPanel.add(myQuestionArea);
        //------------------------------------------------------------------------------------------------------------


        myoption1.addActionListener(this);
        myoption1.setFont( new Font("Times New Roman", Font.PLAIN, 18));
        myoption2.addActionListener(this);
        myoption2.setFont( new Font("Times New Roman", Font.PLAIN, 18));
        myoption3.addActionListener(this);
        myoption3.setFont( new Font("Times New Roman", Font.PLAIN, 18));
        myoption4.addActionListener(this);
        myoption4.setFont( new Font("Times New Roman", Font.PLAIN, 18));

        myOptionGroup.add(myoption1);
        myOptionGroup.add(myoption2);
        myOptionGroup.add(myoption3);
        myOptionGroup.add(myoption4);


        myOptionPanel.add(myoption1);
        myOptionPanel.add(myoption2);
        myOptionPanel.add(myoption3);
        myOptionPanel.add(myoption4);

        myOptionPanel.setBounds(50, myFrame.getHeight()/2 ,myFrame.getWidth(),myFrame.getHeight()/2-50);
        myOptionPanel.setLayout(new GridLayout(2,2));
        myOptionPanel.setOpaque(true);

        myFrame.add(myQuestionPanel);
        myFrame.add(myOptionPanel);

    }

    private void loadQuestions(String theQuestion, String theOption1, String theOption2,
                               String theOption3, String theOption4, String theCorrectAnswer  ) {
        myQuestionArea.setText(theQuestion);
        myoption1.setText(theOption1);
        myoption2.setText(theOption2);
        myoption3.setText(theOption3);
        myoption4.setText(theOption4);
        myCorrectAnswer = theCorrectAnswer;

    }

    private void displayQuestion(boolean theQuestionDisplay) {
        myFrame.setVisible(theQuestionDisplay);
    }

    private void checkAnswer(String theCorrectAnswer, String thePlayerAnswers) {
        if (theCorrectAnswer.equals(thePlayerAnswers)) {
            JOptionPane.showMessageDialog(myFrame, "Correct!");
        } else {
            JOptionPane.showMessageDialog(myFrame, "Incorrect!");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == myoption1 ) {
            myPlayerAnswer = myoption1.getText();
            checkAnswer(myCorrectAnswer, myPlayerAnswer);
        }
        else if(e.getSource() == myoption2 ) {
            myPlayerAnswer = myoption2.getText();
            checkAnswer(myCorrectAnswer, myPlayerAnswer);
        }
        else if(e.getSource() == myoption3 ) {
            myPlayerAnswer = myoption3.getText();
            checkAnswer(myCorrectAnswer, myPlayerAnswer);
        }
        else if(e.getSource() == myoption4 ) {
            myPlayerAnswer = myoption4.getText();
            checkAnswer(myCorrectAnswer, myPlayerAnswer);
        }
    }
}