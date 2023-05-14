package SQLite;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopUp implements ActionListener {

    private JFrame myFrame;
    private JPanel myQuestionPanel;
    private JLabel myQuestionLabel;
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
        initializeUI();
        loadQuestions("The question is should be here","option1",
                "option2", "option3", "option4", "option2" );
        displayQuestion(true);
    }

    private void initializeUI()
    {
        myFrame = new JFrame();
        myFrame.setTitle("Who want to be Disney Expert");
        myFrame.setResizable(false);
        myFrame.setSize(500,500);
        //myFrame.setLocation();  // Set to the center of the screen

        myQuestionPanel = new JPanel();
        myQuestionPanel.setLocation(100,100);
        myQuestionPanel.setSize(100,100);

        myQuestionLabel = new JLabel();
        myQuestionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        myQuestionLabel.setVerticalAlignment(SwingConstants.CENTER);
        myQuestionPanel.add(myQuestionLabel);

        myOptionPanel = new JPanel();
        myOptionPanel.setLocation(100,300);
        myOptionPanel.setSize(100,100);

        myoption1 = new JRadioButton();
        myoption1.addActionListener(this);
        myoption2 = new JRadioButton();
        myoption2.addActionListener(this);
        myoption3 = new JRadioButton();
        myoption3.addActionListener(this);
        myoption4 = new JRadioButton();
        myoption4.addActionListener(this);

        myOptionGroup = new ButtonGroup();

        myFrame.setVisible(true);

    }

    private void loadQuestions(String theQuestion, String theOption1, String theOption2,
                               String theOption3, String theOption4, String theCorrectAnswer  ) {
        myQuestionLabel.setText(theQuestion);
        myoption1.setText(theOption1);
        myoption2.setText(theOption2);
        myoption3.setText(theOption3);
        myoption4.setText(theOption4);
        myCorrectAnswer = theCorrectAnswer;

    }

    private void displayQuestion(boolean theQuestionDisplay) {
              myFrame.setVisible(theQuestionDisplay);
    }

    private void checkAnswer(Question q, String answer) {
        if (q.right_answer.equals(answer)) {
            JOptionPane.showMessageDialog(myFrame, "Correct!");
        } else {
            JOptionPane.showMessageDialog(myFrame, "Incorrect!");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == myoption1 ) {
           myPlayerAnswer = myoption1.getText();
       }
        else if(e.getSource() == myoption2 ) {
            myPlayerAnswer = myoption2.getText();
        }
        else if(e.getSource() == myoption3 ) {
            myPlayerAnswer = myoption3.getText();
        }
        else if(e.getSource() == myoption4 ) {
            myPlayerAnswer = myoption4.getText();
        }
    }
}