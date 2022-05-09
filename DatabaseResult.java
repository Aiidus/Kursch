


    package sample;

import java.sql.*;

    public class DatabaseResult  extends Configs {


        Connection dbConnection;
        public Connection getDbConnection()
                throws ClassNotFoundException, SQLException {

            String connectionString = "jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName;

            Class.forName("com.mysql.cj.jdbc.Driver");

            dbConnection= DriverManager.getConnection(connectionString,dbUser,dbPass);

            return dbConnection;
        }


        public void signUpResult(Result result ) {


            String insert = "INSERT INTO "+ ResultConst.RESULT_TABLE+"("+"`"+ResultConst.RESULT_RESULTINT+"`"+","+"`"+ResultConst.RESULT_TIME+"`"+","+"`"+
                    ResultConst.RESULT_IDTEST+"`"+","+"`"+ResultConst.RESULT_LOGIN+"`"+")"+"VALUES(?,?,?,?)";
            try {
                PreparedStatement prSt= getDbConnection().prepareStatement(insert);

                prSt.setInt(1,result.getResultInt());
                prSt.setString(2,result.getResultTime());
                prSt.setInt(3,result.getIdTest());
                prSt.setString(4,result.getLogin());
                prSt.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }



        public ResultSet getResult(int Id) {
            ResultSet resSet = null;
            String select = "SELECT * FROM result WHERE IdTest = '"+ Id+"' ;";
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






