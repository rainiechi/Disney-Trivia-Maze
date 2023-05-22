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
     * Builds connection with database RealQuestions
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
                query = "SELECT * FROM questions ORDER BY RANDOM() LIMIT 1";
                rs = stmt.executeQuery(query);


            question = new Question(rs.getString( "QUESTION" ),
                    rs.getString( "ANSWER" ),
                    rs.getString( "CHOICE1" ),
                    rs.getString( "CHOICE2" ),
                    rs.getString( "CHOICE3" ),
                    rs.getString( "CHOICE4" ),
                    rs.getInt("ID"));


        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        return question;
    }



}