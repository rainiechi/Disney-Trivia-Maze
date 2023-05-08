package SQLite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class PopUp implements ActionListener {

    private JFrame frame;
    private JPanel panel;
    private JLabel questionLabel;
    private JRadioButton option1, option2, option3, option4;
    private JButton nextButton;
    private ButtonGroup options;
    private ArrayList<Question> questionList;
    private int currentQuestion = 0;
    private String answer;

    public static void main(String[] args) {
        new PopUp();
    }

    public PopUp() {
        initializeUI();
        loadQuestions();
        displayQuestion(questionList.get(currentQuestion));
    }

    private void initializeUI() {
        frame = new JFrame("Quiz");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        //panel.setBounds(0,20,500,460);
        panel.setLayout(new GridLayout(2, 2));

        questionLabel = new JLabel();
        //questionLabel.setBounds(200,0,20,20);
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        frame.add(questionLabel,BorderLayout.NORTH);

        option1 = new JRadioButton();
        panel.add(option1);

        option2 = new JRadioButton();
        panel.add(option2);

        option3 = new JRadioButton();
        panel.add(option3);

        option4 = new JRadioButton();
        panel.add(option4);

        options = new ButtonGroup();
        options.add(option1);
        options.add(option2);
        options.add(option3);
        options.add(option4);

        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        // nextButton.setSize(100,200);
        // nextButton.setBounds(200,460,20,20);
        frame.add(nextButton,BorderLayout.SOUTH);
       // nextButton.setBorder(BorderFactory.createMatteBorder(400, 0, 500, 500, Color.WHITE));


        frame.add(panel,BorderLayout.CENTER);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    private void loadQuestions() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:questions.db");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM questions");
            questionList = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String question = rs.getString(2);
                String option1 = rs.getString(3);
                String option2 = rs.getString(4);
                String option3 = rs.getString(5);
                String option4 = rs.getString(6);
                String answer = rs.getString(7);
                questionList.add(new Question(id,question, option1, option2, option3, option4, answer));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Collections.shuffle(questionList);
    }

    private void displayQuestion(Question q) {
        questionLabel.setText(q.question);
        option1.setText(q.choice1);
        option1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               answer=q.choice1;
            }
        });
        option2.setText(q.choice2);
        option2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer=q.choice2;
            }
        });
        option3.setText(q.choice3);
        option3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer=q.choice3;
            }
        });
        option4.setText(q.choice4);
        option4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer=q.choice4;
            }
        });
    }

    private void checkAnswer(Question q, String answer) {
        if (q.right_answer.equals(answer)) {
            JOptionPane.showMessageDialog(frame, "Correct!");
        } else {
            JOptionPane.showMessageDialog(frame, "Incorrect!");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            if (options.getSelection() != null) {
                System.out.println("the selection "+answer);
                checkAnswer(questionList.get(currentQuestion), answer);
                options.clearSelection();
                currentQuestion++;
                if (currentQuestion < questionList.size()) {
                    displayQuestion(questionList.get(currentQuestion));
                } else {
                    JOptionPane.showMessageDialog(frame, "Quiz completed!");
                    System.exit(0);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please select an answer.");
            }
        }
    }
}