package SQLite;

import Model.Question;
import org.sqlite.SQLiteDataSource;

import java.sql.*;

/**
 * DBRetriever accesses the SQL database and retrieves questions.
 */
public class DBRetriever {
    private SQLiteDataSource myDs;


    /**
     * DBRetriver constructor
     */
    public DBRetriever() {
        myDs = buildConnection();
        //retrieveQuestion(myDs);
    }

//    public String getMyAnswer() {
//        return myAnswer;
//    }
//
//    public String getMyQuestion() {
//        return myQuestion;
//    }
//
//    public String getMyOption1() {
//        return myOption1;
//    }
//    public String getMyOption2() {
//        return myOption2;
//    }public String getMyOption3() {
//        return myOption3;
//    }
//    public String getMyOption4() {
//        return myOption4;
//    }

    /**
     * Builds conection with database RealQuestions
     * @return SQLiteDataSource
     */
    public SQLiteDataSource buildConnection() {
        SQLiteDataSource ds = null;
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:RealQuestions.db");
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println( "Opened database successfully" );
        return ds;
    }

    /**
     * Retrieves a random question that has not been used, then marks the question as used once retrieved.
     */
    public Question retrieveQuestion() {
        Question question = new Question();
        String query = "SELECT * FROM questions ORDER BY RANDOM() LIMIT 1";
        try ( Connection conn = myDs.getConnection();
              Statement stmt = conn.createStatement(); ) {
            ResultSet rs = stmt.executeQuery(query);
            int used = rs.getInt("USED");
            while (used == 1) {
                if (countRemainingProblems() == 0) {
                    throw new RuntimeException("Ran out of questions");
                }
                query = "SELECT * FROM questions ORDER BY RANDOM() LIMIT 1";
                rs = stmt.executeQuery(query);
                used = rs.getInt("USED");
            }
            question.setMyQuestion(rs.getString( "QUESTION" ));
            question.setMyAnswer(rs.getString( "ANSWER" ));
            question.setMyOption1(rs.getString( "CHOICE1" ));
            question.setMyOption2(rs.getString( "CHOICE2" ));
            question.setMyOption3(rs.getString( "CHOICE3" ));
            question.setMyOption4(rs.getString( "CHOICE4" ));
            int id = rs.getInt("ID");
            String todo = "UPDATE questions SET USED = 1 WHERE ID = " + id;
            stmt.executeUpdate(todo);
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        return question;
    }

    /**
     * Resets all questions to the unused state.
     */
    public void resetAllToUnused() {
        try (Connection conn = myDs.getConnection();
             Statement stmt = conn.createStatement(); ) {
            String todo = "UPDATE questions SET USED = 0";
            stmt.executeUpdate(todo);
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    }

    public void resetToUnused(final int theID) {
        try (Connection conn = myDs.getConnection();
             Statement stmt = conn.createStatement(); ) {
            String todo = "UPDATE questions SET USED = 0 WHERE ID = " + theID;
            stmt.executeUpdate(todo);
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    }

    public int countRemainingProblems() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM questions WHERE USED = 0";
        try (Connection conn = myDs.getConnection();
             Statement stmt = conn.createStatement(); ) {
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                count = rs.getInt(1);
                
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        return count;
    }
}