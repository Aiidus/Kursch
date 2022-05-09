package sample;
import java.sql.*;

public class DatabaseHandler extends Configs{
    Connection dbConnection;
    public Connection getDbConnection()
            throws ClassNotFoundException,SQLException{

        String connectionString = "jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection=DriverManager.getConnection(connectionString,dbUser,dbPass);

        return dbConnection;
    }

    public void signUpUser(User user ) {
        String insert = "INSERT INTO "+ Const.USER_TABLE+"("+"`"+Const.USER_NAME+"`"+","+"`"+Const.USER_LASTNAME+"`"+","+"`"+
                Const.USER_GROUP+"`"+","+"`"+Const.USER_LOGIN+"`"+","+"`"+Const.USER_PASSWORD+"`"+")"+"VALUES(?,?,?,?,?)";
        try {
        PreparedStatement prSt= getDbConnection().prepareStatement(insert);

        prSt.setString(1,user.getFirstName());
        prSt.setString(2,user.getLastName());
        prSt.setString(3,user.getGroup());
        prSt.setString(4,user.getLogin());
        prSt.setString(5,user.getPassword());
        prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUser(User user){
        ResultSet resSet=null;
        String select = "SELECT * FROM "+ Const.USER_TABLE+ " WHERE " +
                Const.USER_LOGIN +"=? AND "+ Const.USER_PASSWORD + "=?";
        try {
        PreparedStatement prSt= getDbConnection().prepareStatement(select );

        prSt.setString(1,user.getLogin());
        prSt.setString(2,user.getPassword());
         resSet=   prSt.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



        return resSet;
    }

    public ResultSet ChekUser(User user){
        ResultSet resSet=null;
        String select = "SELECT * FROM "+ Const.USER_TABLE+ " WHERE " +
                Const.USER_LOGIN +"=?";
        try {
            PreparedStatement prSt= getDbConnection().prepareStatement(select );

            prSt.setString(1,user.getLogin());
            resSet=   prSt.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



        return resSet;
    }

}
