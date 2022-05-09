package sample;

import java.sql.*;

public class DatabaseTest extends Configs {


    Connection dbConnection;
    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {

        String connectionString = "jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection= DriverManager.getConnection(connectionString,dbUser,dbPass);

        return dbConnection;
    }


    public void signUpTest(Test test ) {


    String insert = "INSERT INTO "+ TestConst.TEST_TABLE+"("+"`"+TestConst.TEST_NAME+"`"+","+"`"+TestConst.TEST_TOPIC+"`"+","+"`"+
            TestConst.TEST_AUTHOR+"`"+","+"`"+TestConst.TEST_TEXT+"`"+","+"`"+TestConst.TEST_COL+"`"+")"+"VALUES(?,?,?,?,?)";
        try {
            PreparedStatement prSt= getDbConnection().prepareStatement(insert);

            prSt.setString(1,test.getTestName());
            prSt.setString(2,test.getTopicTest());
            prSt.setString(3,test.getTestAuthor());
            prSt.setString(4,test.getTestText());
            prSt.setInt(5,test.getTestCol());
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void UpdateTest(Test test ) {

        String insert = "UPDATE test SET TestName = '"+test.getTestName()+"', TopicTest = '"+test.getTopicTest()+
                "', TestAuthor = '"+test.getTestAuthor()+"', TestText = '"+test.getTestText()+
                "', TestCol = '"+test.getTestCol()+"' WHERE idTest = '"+Global.IdTest+"'";


        try {
            PreparedStatement prSt= getDbConnection().prepareStatement(insert);


            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



      public ResultSet getTest(){
          ResultSet resSet = null;
        String select = "SELECT * FROM test";
        try {
       Statement prSt= getDbConnection().prepareStatement(select );

       resSet = prSt.executeQuery(select);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



        return resSet;
    }
    public ResultSet getTestId(int Id){
        ResultSet resSet = null;
        String select = "SELECT * FROM test WHERE idTest IN ("+Id+");";
        try {
            Statement prSt= getDbConnection().prepareStatement(select );

            resSet = prSt.executeQuery(select);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



        return resSet;
    }



    public void DeleteTest(int Id){
        String select = "DELETE FROM test WHERE idTest = '"+ Id+"' ;";
        try {
            Statement prSt= getDbConnection().prepareStatement(select );

            prSt.execute(select);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



    }
    public ResultSet getTestAuthor(String text) {
        ResultSet resSet = null;
        String select = "SELECT * FROM test WHERE TestAuthor = '"+ text+"' ;";
        try {
            Statement prSt= getDbConnection().prepareStatement(select );

            resSet = prSt.executeQuery(select);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



        return resSet;

    }


    public ResultSet getTestWord(String text) {
        ResultSet resSet = null;
        String select = "SELECT * FROM test WHERE TestName = '"+ text+"' OR TestAuthor = '"+ text+"' OR TopicTest = '"+ text+"' ;";
        try {
            Statement prSt= getDbConnection().prepareStatement(select );

            resSet = prSt.executeQuery(select);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



        return resSet;

    }
}




