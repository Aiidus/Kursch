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

public class Registration {

    @FXML
    private Button BackBt;

    @FXML
    private Text ErrorText;

    @FXML
    private TextField FirstName;

    @FXML
    private TextField Group;

    @FXML
    private TextField LastName;

    @FXML
    private TextField Loggin;

    @FXML
    private TextField Password;

    @FXML
    private Button RegButton;

    @FXML
    private TextField RepPassWord;
    @FXML
    void initialize(){


    RegButton.setOnAction(event->{
        if( FirstName.getText().length()>45){
            ErrorText.setText("Имя не должно содержать больше чем 45 символов");
        }else
        if(  LastName.getText().length()>45){
            ErrorText.setText("Фамилия не должна содержать больше чем 45 символов");
        }else
        if(  Loggin.getText().length()>45){
            ErrorText.setText("Логин не должен содержать больше чем 45 символов");
        }else
        if( Group.getText().length()>45){
            ErrorText.setText("Группа не должна содержать больше чем 45 символов");
        }else
        if( Password.getText().length()>45){
            ErrorText.setText("Пароль не должен содержать больше чем 45 символов");
        }else
        if( FirstName.getText()  .equals("")||
            LastName.getText()   .equals("")||
            Loggin.getText()     .equals("")||
            Password.getText()   .equals("")||
            Group.getText()      .equals("")){
            ErrorText.setText("Неверно введенные данные");

        }else

      if (!Password.getText().equals(RepPassWord.getText())){
            ErrorText.setText("Пароли не совпадают");

        }else{ ErrorText.setText("");


          try {
              if(ChekUser()){
                  ErrorText.setText("Логин занят");
              }else {
                  signUpNewUser();

                  Global.LoginInProg=Loggin.getText();
                  RegButton.getScene().getWindow().hide();
                  Parent root = null;
                  try {
                      root = FXMLLoader.load(getClass().getResource("FXML//meinmenu.fxml"));
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
                  Stage primaryStage = new Stage();
                  primaryStage.setTitle("Регистрация");
                  primaryStage.setScene(new Scene(root, 600, 475));
                  primaryStage.setResizable(false);
                  primaryStage.show();


              }
          } catch (SQLException throwables) {
              throwables.printStackTrace();
          }

      }

    });
        BackBt.setOnAction(event->{
            BackBt.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("FXML//loginscreen.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Окно входа");
            primaryStage.setScene(new Scene(root, 600, 475));
            primaryStage.setResizable(false);
            primaryStage.show();

        });
    }



    private void signUpNewUser() {

        DatabaseHandler dbHandler= new DatabaseHandler();
        String firstname = FirstName.getText();
        String lastname= LastName.getText();
        String login = Loggin.getText();
        String password= Password.getText();
        String group=Group.getText();


        User user = new User(firstname, lastname,group,login,password);



        dbHandler.signUpUser(user);

    }



    private boolean ChekUser() throws SQLException {

        DatabaseHandler dbHandler = new DatabaseHandler();
        User user =new User();
        String login = Loggin.getText();
        user.setLogin(login);
        ResultSet result =  dbHandler.ChekUser(user);

        while(result.next()){

            return true;

        }
        return false;


    }




}
