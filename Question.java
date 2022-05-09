package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Question {

    @FXML
    private Text Errortext;
    @FXML
    private TextField AnswerText1;

    @FXML
    private TextField AnswerText2;

    @FXML
    private TextField AnswerText3;

    @FXML
    private TextField AnswerText4;

    @FXML
    private Button BackBt;

    @FXML
    private ToggleGroup CorrectAns;

    @FXML
    private Button FinishBt;

    @FXML
    private Text NomerQuest;

    @FXML
    private TextArea QuestionArea;

    @FXML
    private TextField TestName;

    @FXML
    private TextField TestTopic;

    @FXML
    private Button addQuestion;

    @FXML
    private RadioButton answer1;

    @FXML
    private RadioButton answer2;

    @FXML
    private RadioButton answer3;

    @FXML
    private RadioButton answer4;

    protected String textTemp="";
    protected int ColTemp=0;
    @FXML
    void initialize(){
        BackBt.setOnAction(event->{
            BackBt.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("FXML//meinmenu.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Меню");
            primaryStage.setScene(new Scene(root, 600, 475));
            primaryStage.setResizable(false);
            primaryStage.show();


        });









        FinishBt.setOnAction(event->{




            String temp1=TestName.getText()+TestTopic.getText();
            int index3 = temp1.indexOf('|');
            if(TestName.getText().length()>45){Errortext.setText("Имя теста не может быть больше чем 45 символов");}else
            if(TestTopic.getText().length()>45){Errortext.setText("Тема теста не может быть больше чем 45 символов");}else
            if(index3!=-1){Errortext.setText("Недопустимый символ |");}else

            if(TestName.getText().equals("")){
                Errortext.setText("Заполните поле названия теста");
            }else
                if(TestTopic.getText().equals("")){ Errortext.setText("Заполните поле темы теста");}
else if(ColTemp==0){Errortext.setText("Вы не добавили ни одного теста");

                } else{
            FinishBt.getScene().getWindow().hide();
            DatabaseTest dbHandler= new DatabaseTest();

            String testname = TestName.getText();
            String testtopic= TestTopic.getText();
            String testauthor= Global.LoginInProg;
            String testtext= textTemp;
            int testcol= ColTemp;
            Test test = new Test(testname,testtopic,testauthor,testtext,testcol);
            dbHandler.signUpTest(test);



            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("FXML//meinmenu.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Меню");
            primaryStage.setScene(new Scene(root, 600, 475));
            primaryStage.setResizable(false);
            primaryStage.show();}

        });



        addQuestion.setOnAction(event->{

                    RadioButton selectedRadio = (RadioButton)CorrectAns.getSelectedToggle();
                    if(QuestionArea.getText().equals("")){Errortext.setText("Заполните поле вопроса");}else
                    if(AnswerText1.getText().equals("")||AnswerText2.getText().equals("")||
                            AnswerText3.getText().equals("")||AnswerText4.getText().equals("")){
                        Errortext.setText("Заполните варианты ответов");

                    }else

                    if(selectedRadio!= null) {
                        String temp1=QuestionArea.getText()+AnswerText1.getText()+AnswerText2.getText()+AnswerText3.getText()+AnswerText4.getText();
                        int index3 = temp1.indexOf('|');
                        if(index3!=-1){Errortext.setText("Недопустимый символ |");}else {
                            String toggleGroupValue = selectedRadio.getId();
                            char temp = toggleGroupValue.charAt(toggleGroupValue.length() - 1);
                            ColTemp++;
                            textTemp += "|" + QuestionArea.getText() + "|" + AnswerText1.getText() + "|" + AnswerText2.getText() + "|" +
                                    AnswerText3.getText() + "|" + AnswerText4.getText() + "|" + temp + "|";


                            Errortext.setText("");
                            QuestionArea.clear();
                            AnswerText1.clear();
                            AnswerText2.clear();
                            AnswerText3.clear();
                            AnswerText4.clear();

                            NomerQuest.setText("Вопрос #" + (ColTemp + 1));


                        }
                    }else Errortext.setText("Выберите верный ответ");



        });

    }
/*
    private void signUpNewUser() {

        DatabaseHandler dbHandler= new DatabaseHandler();
        String testname =  FirstName.getText();
        String testtopic=   LastName.getText();
        String testauthor  Loggin.getText();
        String testtext=   Password.getText();
        String testcol=   Group.getText();


        User user = new User(firstname, lastname,group,login,password);



        dbHandler.signUpUser(user);

    }*/



}
