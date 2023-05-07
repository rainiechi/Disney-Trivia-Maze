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
    JFrame frame;
    JLabel label_question, label_choice1, label_choice2, label_choice3, label_choice4,
            label_right_answer;
    ArrayList<Question> QuestionList;

    Question QuestionObj;

    String header[] = new String[] { "ID", "Question ", "Right Answer", "First Choice ",
            "Second Choice", "Third Choice", "Fourth Choice"};
    DefaultTableModel dtm = new DefaultTableModel(0, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    static Connection conn;
    ResultSet rs;
    int row, col;

    public static void main(String[] args) throws Exception {
        String url = "jdbc:sqlite:questions.db";
        conn = DriverManager.getConnection(url);
        QuestionDataBase call = new QuestionDataBase();
        call.mainInterface();
        call.checkTables();
        call.loadData();
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

        table = new JTable();
        table.setModel(dtm);
        //table.setSize(800,100);
        dtm.setColumnIdentifiers(header);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(10, 350, 430, 600);
        frame.add(sp);
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
                "	choice1 text NOT NULL" +
                "	choice2 text NOT NULL" +
                "	choice3 text NOT NULL" +
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
        rs = stmt.executeQuery("select * from questions");
        QuestionList.clear();

        while (rs.next()) {
            QuestionList.add(new Question(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7)));
        }
        dtm.setRowCount(0); // reset data model
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
            dtm.addRow(objs);
        }
    }
//----------------------------------------------------------------------------------------------------------------------

    ActionListener searchQuestionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            String search = JOptionPane.showInputDialog("Enter Question");
            System.out.println(search);

            QuestionList = new ArrayList<>();
            try {

                Statement stmt = conn.createStatement();
                rs = stmt.executeQuery("select * from questions where question LIKE '%" + search + "%'");
                QuestionList.clear();

                while (rs.next()) {
                    QuestionList.add(new Question
                            (rs.getInt(1),
                                    rs.getString(2),
                                    rs.getString(3),
                                    rs.getString(4),
                                    rs.getString(5),
                                    rs.getString(6),
                                    rs.getString(7)));
                }
                dtm.setRowCount(0);// reset data model
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
                    dtm.addRow(objs);
                }
            } catch (Exception err) {
                System.out.println(err);
            }
        }

    };

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

    ActionListener updateQuestionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String mazequestion = question.getText().toString();
            String mazeright_answer = right_answer.getText().toString();
            String mazechoice1 = choice1.getText().toString();
            String mazechoice2 = choice2.getText().toString();
            String mazechoice3 = choice3.getText().toString();
            String mazechoice4 = choice4.getText().toString();

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
                                ", question = " + mazequestion +
                                ", choice1 = " + mazechoice1 +
                                ", choice2='" + mazechoice2 +
                                ", choice3='" + mazechoice3 +
                                ", choice4='" + mazechoice4 +
                                "' where id =" + QuestionObj.ID + "");
                        loadData();
                    } catch (Exception err) {
                        System.out.println(err);
                    }
                }
            }
        }
    };

    ActionListener addQuestionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String mazequestion = question.getText().toString();
            String mazeright_answer = right_answer.getText().toString();
            String mazechoice1 = choice1.getText().toString();
            String mazechoice2 = choice2.getText().toString();
            String mazechoice3 = choice3.getText().toString();
            String mazechoice4 = choice4.getText().toString();

            if (mazequestion.isEmpty() || mazeright_answer.isEmpty() || mazechoice1.isEmpty()
                    || mazechoice2.isEmpty() || mazechoice3.isEmpty() || mazechoice4.isEmpty()   ) {
                JOptionPane.showMessageDialog(frame, "Please enter question info");
                question.requestFocus();
            } else {
                int result = JOptionPane.showConfirmDialog(frame, "Insert this question " + mazequestion + "?", "Insert",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    try {
                        Statement stmt = conn.createStatement();

                        stmt.executeUpdate("insert into questions (`question`, `right_answer`, `choice1`,'choice2', 'choice3' ,'choice4') VALUES ('" +
                                mazequestion + "','" + mazeright_answer + "','" + mazechoice1 +  mazechoice2 + "','" + mazechoice3 + "','" + mazechoice4 +"')");
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
}