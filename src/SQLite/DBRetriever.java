package SQLite;

import org.sqlite.SQLiteDataSource;

import java.sql.*;

/**
 * DBRetriever accesses the SQL database and retrieves questions.
 */
public class DBRetriever {
    private SQLiteDataSource myDs;
    private String myAnswer;
    private String myQuestion;
    private String myOption1;
    private String myOption2;
    private String myOption3;
    private String myOption4;

    /**
     * DBRetriver constructor
     */
    public DBRetriever() {
        myQuestion = null;
        myAnswer = null;
        myOption1 = null;
        myOption2 = null;
        myOption3 = null;
        myOption4 = null;
        myDs = buildConnection();
        retrieveQuestion(myDs);
    }

    public String getMyAnswer() {
        return myAnswer;
    }

    public String getMyQuestion() {
        return myQuestion;
    }

    public String getMyOption1() {
        return myOption1;
    }
    public String getMyOption2() {
        return myOption2;
    }public String getMyOption3() {
        return myOption3;
    }
    public String getMyOption4() {
        return myOption4;
    }

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
     * @param theDS the DataSource
     */
    public void retrieveQuestion(SQLiteDataSource theDS) {
        String query = "SELECT * FROM questions ORDER BY RANDOM() LIMIT 1";
        try ( Connection conn = theDS.getConnection();
              Statement stmt = conn.createStatement(); ) {
            ResultSet rs = stmt.executeQuery(query);
            int used = rs.getInt("USED");
            while (used == 1) {
                query = "SELECT * FROM questions ORDER BY RANDOM() LIMIT 1";
                rs = stmt.executeQuery(query);
                used = rs.getInt("USED");
            } //need to add something to stop infinite loop when we run out of questions
            myQuestion = rs.getString( "QUESTION" );
            myAnswer = rs.getString( "ANSWER" );
            myOption1 = rs.getString( "CHOICE1" );
            myOption2 = rs.getString( "CHOICE2" );
            myOption3 = rs.getString( "CHOICE3" );
            myOption4 = rs.getString( "CHOICE4" );
            String todo = "UPDATE questions SET USED = 1 WHERE QUESTION = \"" + myQuestion + "\"";
            stmt.executeUpdate(todo);
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
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

    public void resetToUnused(final String theQuestion) {
        try (Connection conn = myDs.getConnection();
             Statement stmt = conn.createStatement(); ) {
            String todo = "UPDATE questions SET USED = 0 WHERE QUESTION = \"" + theQuestion + "\"";
            stmt.executeUpdate(todo);
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    }
}