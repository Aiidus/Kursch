package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Loginscr {

    @FXML
    private Button enterbt;

    @FXML
    private Text errorlogin;

    @FXML
    private TextField loginword;

    @FXML
    private TextField password;

    @FXML
    private Button registrbt;

    @FXML
    void initialize(){

        registrbt.setOnAction(event ->{
            enterbt.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("FXML//registration.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Регистрация");
            primaryStage.setScene(new Scene(root, 600, 475));
            primaryStage.setResizable(false);
            primaryStage.show();

        });

        enterbt.setOnAction(event ->{

            String Login= loginword.getText().trim();
            String Password= password.getText().trim();

            if(!Login.equals("")&&!Password.equals("")){
                try {
                    LoginUser(Login,Password);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }else if(Login.equals("")&&Password.equals("")){
                errorlogin.setText("Введите логин и пароль");
           }
            else if(Login.equals("")&&!Password.equals("")){errorlogin.setText("Введите логин");}
            else errorlogin.setText("Введите пароль");
/*
            enterbt.getScene().getWindow().hide();

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("meinmenu.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage primaryStage = new Stage();
            primaryStage.setTitle("MeinMenu ");
            primaryStage.setScene(new Scene(root, 600, 475));
            primaryStage.setResizable(false);
            primaryStage.show();*/
        });

    }



    private void LoginUser(String login, String Password) throws SQLException {

        DatabaseHandler dbHandler = new DatabaseHandler();
        User user =new User();
        user.setLogin(login);
        user.setPassword(Password);
        ResultSet result =  dbHandler.getUser(user);

        int counter =0;
        while(result.next()){
            counter++;


        }
        if(counter>=1){ enterbt.getScene().getWindow().hide();

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("FXML//meinmenu.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Global.LoginInProg=loginword.getText();
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Меню");
            primaryStage.setScene(new Scene(root, 600, 475));
            primaryStage.setResizable(false);
            primaryStage.show();}else errorlogin.setText("Такого пользователя нет");

    }

}
