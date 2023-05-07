package SQLite;   //package SQLite;

// what do we want
// question?
// right_answer
// choice 1
// choice 2
// choice 3
// choice 4
// Add delete update search
// Id  question right_answer choice1 choice2 choice3 choice4

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class QuestionDataBase {

    JTextField choice1, choice2, choice3, choice4, right_answer;
    JTextArea question;
    JButton addButton, deleteButton, updateButton, searchButton;
    JTable table;
    JScrollPane scrollPane;
    JFrame frame;
    JLabel label_question, label_choice1, label_choice2, label_choice3, label_choice4,
            label_right_answer;
    ArrayList<Question> QuestionList;

    Question QuestionObj;

    String header[] = { "ID", "Question ", "Right Answer", "First Choice ",
            "Second Choice", "Third Choice", "Fourth Choice"};
    DefaultTableModel model = new DefaultTableModel();
//    {
//        @Override
//        public boolean isCellEditable(int row, int column) {
//        return false;
//    }
//    };

    static Connection conn;
    ResultSet resultset;
    // int row, col;

    public static void main(String[] args) throws Exception {
        String url = "jdbc:sqlite:questions.db";
        conn = DriverManager.getConnection(url);
        QuestionDataBase call = new QuestionDataBase();
        call.mainInterface();
        call.checkTables();
        //call.loadData();
    }

    private void mainInterface() {

        frame = new JFrame();
        label_question = new JLabel();
        label_question.setText("Question");
        label_question.setBounds(10, 10, 100, 50);
        frame.add(label_question);

        question = new JTextArea();
        question.setBounds(100, 25, 250, 50);
        frame.add(question);

        label_right_answer = new JLabel();
        label_right_answer.setText("Right Answer");
        label_right_answer.setBounds(10, 85, 100, 50);
        frame.add(label_right_answer);

        right_answer = new JTextField();
        right_answer.setBounds(100, 100, 250, 25);
        frame.add(right_answer);

        label_choice1 = new JLabel();
        label_choice1.setText("First Choice");
        label_choice1.setBounds(10, 130, 100, 50);
        frame.add(label_choice1);

        choice1 = new JTextField();
        choice1.setBounds(100, 145, 250, 25);
        frame.add(choice1);

        label_choice2 = new JLabel();
        label_choice2.setText("Second Choice");
        label_choice2.setBounds(10, 170, 100, 50);
        frame.add(label_choice2);

        choice2 = new JTextField();
        choice2.setBounds(100, 185, 250, 25);
        frame.add(choice2);

        label_choice3 = new JLabel();
        label_choice3.setText("Third Choice");
        label_choice3.setBounds(10, 210, 100, 50);
        frame.add(label_choice3);

        choice3 = new JTextField();
        choice3.setBounds(100, 225, 250, 25);
        frame.add(choice3);

        label_choice4 = new JLabel();
        label_choice4.setText("Fourth Choice");
        label_choice4.setBounds(10, 250, 100, 50);
        frame.add(label_choice4);

        choice4 = new JTextField();
        choice4.setBounds(100, 265, 250, 25);
        frame.add(choice4);


//----------------------------------------------------------------------------------------------------------------------

        addButton = new JButton("Add");
        addButton.setBounds(10, 300, 100, 25);
        addButton.addActionListener(addQuestionListener);
        frame.add(addButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(120, 300, 100, 25);
        deleteButton.addActionListener(delQuestionListener);
        frame.add(deleteButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(230, 300, 100, 25);
        updateButton.addActionListener(updateQuestionListener);
        frame.add(updateButton);

        searchButton = new JButton("Search");
        searchButton.setBounds(340, 300, 100, 25);
        searchButton.addActionListener(searchQuestionListener);
        frame.add(searchButton);

        table = new JTable(model);

        //table.setSize(800,100);
        model.setColumnIdentifiers(header);
        scrollPane = new JScrollPane(table);

        //sp.setBounds(10, 350, 430, 600);
        frame.getContentPane().add(scrollPane);
        table.addMouseListener(mouseListener);

        frame.setSize(480, 800);
        frame.setLayout(null); // using no layout managers
        frame.setVisible(true); // making the frame visible
    }
    private void checkTables() {
        System.out.println("Check table");
        String sql = "CREATE TABLE IF NOT EXISTS questions (" +
                "	id integer PRIMARY KEY AUTOINCREMENT," +
                "	question text NOT NULL," +
                "	right_answer text NOT NULL," +
                "	choice1 text NOT NULL," +
                "	choice2 text NOT NULL," +
                "	choice3 text NOT NULL," +
                "	choice4 text NOT NULL" +
                ");";
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (Exception err) {
            System.out.println(err);
        }
    }


//    JTextField choice1, choice2, choice3, choice4, right_answer;
//    JTextArea question;

    private void loadData() throws SQLException {
        System.out.println("Load data");
        QuestionList = new ArrayList<>();
        Statement stmt = conn.createStatement();
        resultset = stmt.executeQuery("select * from questions");
        QuestionList.clear();

        while (resultset.next()) {
            QuestionList.add(new Question(
                    resultset.getInt(1),
                    resultset.getString(2),
                    resultset.getString(3),
                    resultset.getString(4),
                    resultset.getString(5),
                    resultset.getString(6),
                    resultset.getString(7)));
        }

        // Check for null model
        if (model == null) {
            System.err.println("Data model is not initialized");
            return;
        }

        model.setRowCount(0); // reset data model
        for (Question value : QuestionList) {
            Object[] objs = {
                    value.ID,
                    value.question,
                    value.right_answer,
                    value.choice1,
                    value.choice2,
                    value.choice3,
                    value.choice4
            };
            model.addRow(objs);
        }
    }

    //----------------------------------------------------------------------------------------------------------------------
    MouseInputAdapter mouseListener = new MouseInputAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            int row = table.rowAtPoint(evt.getPoint());
            int col = table.columnAtPoint(evt.getPoint());
            if (row >= 0 && col >= 0) {
//                JTextField choice1, choice2, choice3, choice4, right_answer;
//                JTextArea question;

                question.setText(table.getValueAt(row, 1).toString());
                right_answer.setText(table.getValueAt(row, 2).toString());
                choice1.setText(table.getValueAt(row, 3).toString());
                choice2.setText(table.getValueAt(row, 4).toString());
                choice3.setText(table.getValueAt(row, 5).toString());
                choice4.setText(table.getValueAt(row, 6).toString());

                QuestionObj = new Question(
                        Integer.parseInt(table.getValueAt(row, 0).toString()),
                        table.getValueAt(row, 1).toString(),
                        table.getValueAt(row, 2).toString(),
                        table.getValueAt(row, 3).toString(),
                        table.getValueAt(row, 4).toString(),
                        table.getValueAt(row, 5).toString(),
                        table.getValueAt(row, 6).toString());
            }
        }
    };
    //----------------------------------------------------------------------------------------------------------------------
    ActionListener addQuestionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String mazequestion = question.getText();
            String mazeright_answer = right_answer.getText();
            String mazechoice1 = choice1.getText();
            String mazechoice2 = choice2.getText();
            String mazechoice3 = choice3.getText();
            String mazechoice4 = choice4.getText();

            if (mazequestion.isEmpty() || mazeright_answer.isEmpty() || mazechoice1.isEmpty()
                    || mazechoice2.isEmpty() || mazechoice3.isEmpty() || mazechoice4.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter question info");
                question.requestFocus();
            } else {
                int result = JOptionPane.showConfirmDialog(frame, "Insert this question " + mazequestion + "?", "Insert",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    try {
                        // Changed to PreparedStatement to prevent SQL injection attacks
                        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO questions (question, right_answer, choice1, choice2, choice3, choice4) VALUES (?, ?, ?, ?, ?, ?)");
                        // Set parameters to prevent SQL injection attacks
                        pstmt.setString(1, mazequestion);
                        pstmt.setString(2, mazeright_answer);
                        pstmt.setString(3, mazechoice1);
                        pstmt.setString(4, mazechoice2);
                        pstmt.setString(5, mazechoice3);
                        pstmt.setString(6, mazechoice4);

                        pstmt.executeUpdate();
                        loadData();
                    } catch (Exception err) {
                        System.out.println(err);
                    }
                }
            }
        }
    };


    ActionListener delQuestionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (QuestionObj == null) {
                System.out.println("Null");
            } else {
                int result = JOptionPane.showConfirmDialog(frame, "Delete " + QuestionObj.question + "?", "Swing Tester",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    try {
                        System.out.println("Question " +  QuestionObj.question );
                        Statement stmt = conn.createStatement();
                        stmt.executeUpdate("delete from questions where id = '" +  QuestionObj.ID + "'");
                        loadData();
                    } catch (Exception err) {
                        System.out.println(err);
                    }
                }
            }
        }
    };

    ActionListener updateQuestionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String mazequestion = question.getText();
            String mazeright_answer = right_answer.getText();
            String mazechoice1 = choice1.getText();
            String mazechoice2 = choice2.getText();
            String mazechoice3 = choice3.getText();
            String mazechoice4 = choice4.getText();

            if (QuestionObj == null) {
                System.out.println("Null");
            } else {

                int result = JOptionPane.showConfirmDialog(frame, "Update " + QuestionObj.question + "?", "Swing Tester",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    try {
                        System.out.println("Question " + QuestionObj.question);
                        Statement stmt = conn.createStatement();

                        stmt.executeUpdate("update questions set question = '" + mazequestion +
                                "', mazeright_answer = '" + mazeright_answer +
                                "', choice1 = '" + mazechoice1 +
                                "', choice2='" + mazechoice2 +
                                "', choice3='" + mazechoice3 +
                                "', choice4='" + mazechoice4 +
                                "' where id =" + QuestionObj.ID + "");
                        loadData();
                    } catch (Exception err) {
                        System.out.println(err);
                    }
                }
            }
        }
    };

    ActionListener searchQuestionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchTerm = JOptionPane.showInputDialog(frame, "Enter search term:");
            if (searchTerm == null || searchTerm.isEmpty()) {
                return; // user cancelled or entered empty input
            }
            QuestionList = new ArrayList<>();
            try {
                String sql = "SELECT * FROM questions WHERE question LIKE ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, "%" + searchTerm + "%");
                ResultSet resultset = stmt.executeQuery();
                QuestionList.clear();
                while (resultset.next()) {
                    QuestionList.add(new Question(
                            resultset.getInt("id"),
                            resultset.getString("question"),
                            resultset.getString("right_answer"),
                            resultset.getString("choice1"),
                            resultset.getString("choice2"),
                            resultset.getString("choice3"),
                            resultset.getString("choice4")
                    ));
                }
                model.setRowCount(0);// reset data model
                for (int i = 0; i < QuestionList.size(); i++) {
                    Object[] objs = {
                            QuestionList.get(i).ID,
                            QuestionList.get(i).question,
                            QuestionList.get(i).right_answer,
                            QuestionList.get(i).choice1,
                            QuestionList.get(i).choice2,
                            QuestionList.get(i).choice3,
                            QuestionList.get(i).choice4
                    };
                    model.addRow(objs);
                }
            } catch (SQLException err) {
                JOptionPane.showMessageDialog(frame, "An error occurred while searching for questions.");
                System.err.println(err);
            }
        }
    };


}