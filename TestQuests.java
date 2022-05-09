package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javafx.scene.chart.PieChart;
import java.util.concurrent.atomic.AtomicInteger;

public class TestQuests {

    @FXML
    private Button EsqBt;

    @FXML
    private Button GiveAns;
    @FXML
    private PieChart Pie;

    @FXML
    private Text NomQuest;

    @FXML
    private Text QuestionText;

    @FXML
    private RadioButton ans1;

    @FXML
    private RadioButton ans2;

    @FXML
    private RadioButton ans3;

    @FXML
    private RadioButton ans4;

    @FXML
    private ToggleGroup gr1;
    int NowQuestion=0;
    int correctAnswer=0;
    @FXML
    void initialize(){

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

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }


        int nomqt=0;
        int nomstr=0;

        String[][] TestTemp = new String[test.getTestCol()][6];
        NomQuest.setText("Вопрос #"+test.getTestCol());
        String str = test.getTestText();
        String[] words = str.split("\\|");
        for (String word : words) {
           // System.out.println(word);
            if(!word.equals("")){
            TestTemp[nomqt][nomstr]=word;
            nomstr++;
            if(nomstr==6){nomqt++;nomstr=0;}

            }

        }

        NomQuest.setText("Вопрос #"+1);
        QuestionText.setText(TestTemp[0][0]);
        ans1.setText(TestTemp[0][1]);
        ans2.setText(TestTemp[0][2]);
        ans3.setText(TestTemp[0][3]);
        ans4.setText(TestTemp[0][4]);



        Test finalTest = test;

        Test finalTest1 = test;
        GiveAns.setOnAction(event -> {


            String CorrectAns=TestTemp[NowQuestion][Integer.valueOf(TestTemp[NowQuestion][5])];
            RadioButton selectedRadio = (RadioButton) gr1.getSelectedToggle();
            if (selectedRadio != null) {
                String toggleGroupValue = selectedRadio.getText();
                if (toggleGroupValue.equals(CorrectAns)) {
                    System.out.print("WERNO\n");

                    correctAnswer++;
                } else {
                    System.out.print("NEWERNO\n");
                }

                if (NowQuestion+1 != finalTest.getTestCol()) {
                    NowQuestion++;
                    QuestionText.setText( TestTemp[NowQuestion][0]);
                    CorrectAns = TestTemp[NowQuestion][Integer.parseInt(TestTemp[NowQuestion][5])];
                    String[] answers = new String[4];
                    answers[0]=TestTemp[NowQuestion][1];
                    answers[1]=TestTemp[NowQuestion][2];
                    answers[2]=TestTemp[NowQuestion][3];
                    answers[3]=TestTemp[NowQuestion][4];
                    List<String> stringList = Arrays.asList(answers);
                    Collections.shuffle(stringList);
                    NomQuest.setText("Вопрос #"+(NowQuestion+1));
                    ans1.setText(stringList.get(0));
                    ans2.setText(stringList.get(1));
                    ans3.setText(stringList.get(2));
                    ans4.setText(stringList.get(3));


                    selectedRadio.setSelected(false);


                } else {
                    ans1.setVisible(false);
                    ans2.setVisible(false);
                    ans3.setVisible(false);
                    ans4.setVisible(false);
                    GiveAns.setVisible(false);
                    QuestionText.setVisible(false);
                    NomQuest.setText("Верно решенных вопросов  " + correctAnswer + " из "+ finalTest1.getTestCol());
                    double wer=((double)correctAnswer/(double)finalTest1.getTestCol())*100;

                    ObservableList<PieChart.Data> pieChartData
                            = FXCollections.observableArrayList(
                                    new PieChart.Data("Correct", 0+wer),
                            new PieChart.Data("Fail",100-wer)

                    );

                    Pie.setData(pieChartData);
                    Pie.setStartAngle(90);

                    //-------------------------------
                    Date date = new Date();
                    String Str = "";
                    if(date.getHours()<10){Str+="0"+String.valueOf(date.getHours())+":";}else Str+=String.valueOf(date.getHours())+":";
                    if(date.getMinutes()<10){Str+="0"+String.valueOf(date.getMinutes())+":";}else Str+=String.valueOf(date.getMinutes())+":";
                    if(date.getSeconds()<10){Str+="0"+String.valueOf(date.getSeconds());}else Str+=String.valueOf(date.getSeconds());
                    Result res =new Result(Str,(int)wer,Global.IdTest,Global.LoginInProg);
                    DatabaseResult dbHandlerra = new DatabaseResult();
                    dbHandlerra.signUpResult(res);
                    //-------------------------------







                }
            }



        });

        EsqBt.setOnAction(event ->{
            EsqBt.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("FXML//meinmenu.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage primaryStage = new Stage();
            primaryStage.setTitle("menu");
            primaryStage.setScene(new Scene(root, 600, 475));
            primaryStage.setResizable(false);
            primaryStage.show();


        });


















    }

}
