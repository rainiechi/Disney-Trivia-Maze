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
     * DBRetriever constructor
     */
    public DBRetriever() {
        myDs = buildConnection();
    }

    /**
     * Builds connection with database RealQuestions
     *
     * @return SQLiteDataSource
     */
    public SQLiteDataSource buildConnection() {
        SQLiteDataSource ds = null;
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite::resource:SQLite/RealQuestions.db");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        return ds;
    }

    /**
     * Gets how many questions are in the database.
     *
     * @return how many questions are in the database
     */
    public int getEntryCount() {
        int entryCount = 0;
        String query = "SELECT COUNT(*) FROM questions";

        try (Connection conn = myDs.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                entryCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        return entryCount;
    }

    /**
     * Retrieves a random question that has not been used, then marks the question as used once retrieved.
     */
    public Question retrieveQuestion() {
        Question question = null;
        String query = "SELECT * FROM questions ORDER BY RANDOM() LIMIT 1";

        try (Connection conn = myDs.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                question = new Question(
                        rs.getString("QUESTION"),
                        rs.getString("ANSWER"),
                        rs.getString("CHOICE1"),
                        rs.getString("CHOICE2"),
                        rs.getString("CHOICE3"),
                        rs.getString("CHOICE4"),
                        rs.getInt("ID")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        return question;
    }
}
