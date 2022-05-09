package sample;

import javafx.event.Event;
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
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditTest {

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
    private Text Errortext;

    @FXML
    private Button FinishBt;

    @FXML
    private Button NextQuest;

    @FXML
    private Text NomerQuest;

    @FXML
    private Button PredQuest;

    @FXML
    private TextArea QuestionArea;

    @FXML
    private TextField TestName;

    @FXML
    private TextField TestTopic;

    @FXML
    private Text TextTT;
    @FXML
    private Button AddQuest;
    @FXML
    private Button DeleteQuest;
    @FXML
    private RadioButton answer1;

    @FXML
    private RadioButton answer2;

    @FXML
    private RadioButton answer3;

    @FXML
    private RadioButton answer4;

    int NowQuestion=0;
    int CountQuest;






    @FXML
    void initialize() {

        Test test = null;
        DatabaseTest dbHandler = new DatabaseTest();
        ResultSet result =  dbHandler.getTestId(Global.IdTest);
        while (true){
            try {
                if (!result.next()) break;
                int id= result.getInt(1);
                String Name=result.getString(2);
                String Topic=result.getString(3);
                String Author=result.getString(4);
                String Text=result.getString(5);
                int Count=result.getInt(6);
                 test=new Test(Name,Topic,Author,Text,Count);

                System.out.println( test.toString());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

        int nomqt=0;
        int nomstr=0;

        String[][] TestTemp = new String[test.getTestCol()][6];
        NomerQuest.setText("Вопрос #"+test.getTestCol());
        String str = test.getTestText();
        String[] words = str.split("\\|");
        for (String word : words) {

            if(!word.equals("")){
                TestTemp[nomqt][nomstr]=word;
                nomstr++;
                if(nomstr==6){nomqt++;nomstr=0;}

            }

        }

        NomerQuest.setText("Вопрос #"+1);
        QuestionArea.setText(TestTemp[0][0]);
        TestName.setText(test.getTestName());
        TestTopic.setText(test.getTopicTest());
       AnswerText1.setText(TestTemp[0][1]);
        AnswerText2.setText(TestTemp[0][2]);
        AnswerText3.setText(TestTemp[0][3]);
        AnswerText4.setText(TestTemp[0][4]);
        if(TestTemp[0][5].equals("1")){
            answer1.setSelected(true);
        }else if(TestTemp[0][5].equals("2")){
            answer2.setSelected(true);
        }else if(TestTemp[0][5].equals("3")){
            answer3.setSelected(true);
        }else answer4.setSelected(true);

        PredQuest.setVisible(false);



        Test finalTest = test;
        Test finalTest1 = test;
        Test finalTest2 = test;
        PredQuest.setVisible(false);

        FinishBt.setOnAction(event -> {


            String temp1=TestName.getText()+TestTopic.getText()+QuestionArea.getText()+AnswerText1.getText()+AnswerText2.getText()+AnswerText3.getText()+AnswerText4.getText();

            int index3 = temp1.indexOf('|');
            if(TestName.getText().length()>45){Errortext.setText("Имя теста не может быть больше чем 45 символов");}else
            if(TestTopic.getText().length()>45){Errortext.setText("Тема теста не может быть больше чем 45 символов");}else
            if(index3!=-1){Errortext.setText("Недопустимый символ |");}else

                Errortext.setText("");
            finalTest.setTestName(TestName.getText());
            finalTest.setTopicTest(TestTopic.getText());
            RadioButton selectedRadio = (RadioButton)CorrectAns.getSelectedToggle();
            TestTemp[NowQuestion][0]=QuestionArea.getText();
            TestTemp[NowQuestion][1]=AnswerText1.getText();
            TestTemp[NowQuestion][2]=AnswerText2.getText();
            TestTemp[NowQuestion][3]=AnswerText3.getText();
            TestTemp[NowQuestion][4]=AnswerText4.getText();
            String toggleGroupValue = selectedRadio.getId();
            char temp = toggleGroupValue.charAt(toggleGroupValue.length() - 1);

            TestTemp[NowQuestion][5]= String.valueOf(temp);


            String textTemp = "";
            if(QuestionArea.getText().equals("")){Errortext.setText("Заполните поле вопроса");}else
            if(AnswerText1.getText().equals("")||AnswerText2.getText().equals("")||
                    AnswerText3.getText().equals("")||AnswerText4.getText().equals("")){
                Errortext.setText("Заполните варианты ответов");

            }else



              for(int i = 0; i< finalTest1.getTestCol(); i++){
                textTemp += "|" + TestTemp[i][0] + "|" + TestTemp[i][1] + "|" + TestTemp[i][2] + "|" +
                        TestTemp[i][3] + "|" + TestTemp[i][4] + "|" +TestTemp[i][5]+ "|";}


              Test testtemp= new Test(TestName.getText(),TestTopic.getText(),Global.LoginInProg,textTemp, finalTest2.getTestCol());


            DatabaseTest dbHandlerr = new DatabaseTest();
          dbHandlerr.UpdateTest(testtemp);


            FinishBt.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("mytest.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Мои тесты");
            primaryStage.setScene(new Scene(root, 600, 475));
            primaryStage.setResizable(false);
            primaryStage.show();




        });


        Test finalTest3 = test;
        if(NowQuestion + 1 == finalTest3.getTestCol()){
            NextQuest.setVisible(false);
        }else  NextQuest.setVisible(true);


        NextQuest.setOnAction(event -> {
            if(NowQuestion+1== finalTest3.getTestCol()){}

            String temp1=TestName.getText()+TestTopic.getText()+QuestionArea.getText()+AnswerText1.getText()+AnswerText2.getText()+AnswerText3.getText()+AnswerText4.getText();

            int index3 = temp1.indexOf('|');
            if(TestName.getText().length()>45){Errortext.setText("Имя теста не может быть больше чем 45 символов");}else
            if(TestTopic.getText().length()>45){Errortext.setText("Тема теста не может быть больше чем 45 символов");}else
            if(index3!=-1){Errortext.setText("Недопустимый символ |");}
            else{PredQuest.setVisible(true);

                Errortext.setText("");
                RadioButton selectedRadio = (RadioButton)CorrectAns.getSelectedToggle();
                TestTemp[NowQuestion][0]=QuestionArea.getText();
                TestTemp[NowQuestion][1]=AnswerText1.getText();
                TestTemp[NowQuestion][2]=AnswerText2.getText();
                TestTemp[NowQuestion][3]=AnswerText3.getText();
                TestTemp[NowQuestion][4]=AnswerText4.getText();
                String toggleGroupValue = selectedRadio.getId();
                char temp = toggleGroupValue.charAt(toggleGroupValue.length() - 1);

                TestTemp[NowQuestion][5]= String.valueOf(temp);




                NowQuestion++;
                if(NowQuestion+1== finalTest3.getTestCol()){NextQuest.setVisible(false);}
                NomerQuest.setText("Вопрос #"+(NowQuestion+1));
                QuestionArea.setText(TestTemp[NowQuestion][0]);
                AnswerText1.setText( TestTemp[NowQuestion][1]);
                AnswerText2.setText( TestTemp[NowQuestion][2]);
                AnswerText3.setText( TestTemp[NowQuestion][3]);
                AnswerText4.setText( TestTemp[NowQuestion][4]);
                if(TestTemp[NowQuestion][5].equals("1")){
                    answer1.setSelected(true);
                }else if(TestTemp[NowQuestion][5].equals("2")){
                    answer2.setSelected(true);
                }else if(TestTemp[NowQuestion][5].equals("3")){
                    answer3.setSelected(true);
                }else answer4.setSelected(true);


            }

        });


        PredQuest.setOnAction(event->{

            String temp1=TestName.getText()+TestTopic.getText()+QuestionArea.getText()+AnswerText1.getText()+AnswerText2.getText()+AnswerText3.getText()+AnswerText4.getText();

            int index3 = temp1.indexOf('|');
            if(TestName.getText().length()>45){Errortext.setText("Имя теста не может быть больше чем 45 символов");}else
            if(TestTopic.getText().length()>45){Errortext.setText("Тема теста не может быть больше чем 45 символов");}else
            if(index3!=-1){Errortext.setText("Недопустимый символ |");}else
            if(NowQuestion-1<0){

            }
            else {
                NextQuest.setVisible(true);

                Errortext.setText("");
                RadioButton selectedRadio = (RadioButton) CorrectAns.getSelectedToggle();
                TestTemp[NowQuestion][0] = QuestionArea.getText();
                TestTemp[NowQuestion][1] = AnswerText1.getText();
                TestTemp[NowQuestion][2] = AnswerText2.getText();
                TestTemp[NowQuestion][3] = AnswerText3.getText();
                TestTemp[NowQuestion][4] = AnswerText4.getText();
                String toggleGroupValue = selectedRadio.getId();
                char temp = toggleGroupValue.charAt(toggleGroupValue.length() - 1);

                TestTemp[NowQuestion][5] = String.valueOf(temp);

                if(NowQuestion - 1 == 0){
                    PredQuest.setVisible(false);}



                NowQuestion--;

                NomerQuest.setText("Вопрос #" + (NowQuestion + 1));
                QuestionArea.setText(TestTemp[NowQuestion][0]);
                AnswerText1.setText(TestTemp[NowQuestion][1]);
                AnswerText2.setText(TestTemp[NowQuestion][2]);
                AnswerText3.setText(TestTemp[NowQuestion][3]);
                AnswerText4.setText(TestTemp[NowQuestion][4]);
                if (TestTemp[NowQuestion][5].equals("1")) {
                    answer1.setSelected(true);
                } else if (TestTemp[NowQuestion][5].equals("2")) {
                    answer2.setSelected(true);
                } else if (TestTemp[NowQuestion][5].equals("3")) {
                    answer3.setSelected(true);
                } else answer4.setSelected(true);


            }

            });


        BackBt.setOnAction(event -> {
            BackBt.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("FXML//mytest.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Меню");
            primaryStage.setScene(new Scene(root, 600, 475));
            primaryStage.setResizable(false);
            primaryStage.show();


        });
    }
}
