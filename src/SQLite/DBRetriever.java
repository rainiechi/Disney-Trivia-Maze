package SQLite;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DBRetriever {
    private String myAnswer;
    private String myQuestion;
    private String myOption1;
    private String myOption2;
    private String myOption3;
    private String myOption4;

    public DBRetriever() {
        myQuestion = null;
        myAnswer = null;
        myOption1 = null;
        myOption2 = null;
        myOption3 = null;
        myOption4 = null;
        SQLiteDataSource ds = buildConnection();
        retrieveQuestion(ds);
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



    public SQLiteDataSource buildConnection() {
        SQLiteDataSource ds = null;
        //establish connection (creates db file if it does not exist :-)
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

    public void retrieveQuestion(SQLiteDataSource theDS) {
        String query = "SELECT * FROM questions";
        try ( Connection conn = theDS.getConnection();
              Statement stmt = conn.createStatement(); ) {
            ResultSet rs = stmt.executeQuery(query);
            myQuestion = rs.getString( "QUESTION" );
            myAnswer = rs.getString( "ANSWER" );
            myOption1 = rs.getString( "CHOICE1" );
            myOption2 = rs.getString( "CHOICE2" );
            myOption3 = rs.getString( "CHOICE3" );
            myOption4 = rs.getString( "CHOICE4" );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    }
}

