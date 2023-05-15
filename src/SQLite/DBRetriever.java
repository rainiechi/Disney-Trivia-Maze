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
     */
    public Question retrieveQuestion() {
        Question question = null;
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
            question = new Question(rs.getString( "QUESTION" ),
                    rs.getString( "ANSWER" ),
                    rs.getString( "CHOICE1" ),
                    rs.getString( "CHOICE2" ),
                    rs.getString( "CHOICE3" ),
                    rs.getString( "CHOICE4" ));
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