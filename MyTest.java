package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class MyTest {
    @FXML
    private Button Result;
    @FXML
    private TableColumn<?, ?> AuthorCl;
    @FXML
    private Text ErrorText;
    @FXML
    private Button BackBt;

    @FXML
    private TableColumn<?, ?> CountQuestCl1;

    @FXML
    private Button DeleteBt;

    @FXML
    private Button EditBt;

    @FXML
    private TableColumn<?, ?> NameCl;

    @FXML
    private TableColumn<?, ?> NmCl;

    @FXML
    private TableView<Test> Table;

    @FXML
    private TableColumn<?, ?> TopicCl;

    @FXML

    void initialize() {


        NameCl.setCellValueFactory(new PropertyValueFactory<>("testName"));
        TopicCl.setCellValueFactory(new PropertyValueFactory<>("topicTest"));
        AuthorCl.setCellValueFactory(new PropertyValueFactory<>("testAuthor"));
        CountQuestCl1.setCellValueFactory(new PropertyValueFactory<>("testCol"));
        DatabaseTest dbHandler = new DatabaseTest();
        ResultSet result = dbHandler.getTestAuthor(Global.LoginInProg);
        while (true) {
            try {
                if (!result.next()) break;

                int id = result.getInt(1);
                String Name = result.getString(2);
                String Topic = result.getString(3);
                String Author = result.getString(4);
                int Count = result.getInt(6);


                Test test = new Test(id, Name, Topic, Author, Count);
                Table.getItems().add(test);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        }



        DeleteBt.setOnAction(event->{




            if (Table.getSelectionModel().getSelectedItem() != null) {
                Test test;
                test = Table.getSelectionModel().getSelectedItem();



                dbHandler.DeleteTest(test.getTestId());


                Table.getItems().clear();
                NmCl.setCellValueFactory(new PropertyValueFactory<>("testId"));
                NameCl.setCellValueFactory(new PropertyValueFactory<>("testName"));
                TopicCl.setCellValueFactory(new PropertyValueFactory<>("topicTest"));
                AuthorCl.setCellValueFactory(new PropertyValueFactory<>("testAuthor"));
                CountQuestCl1.setCellValueFactory(new PropertyValueFactory<>("testCol"));
                DatabaseTest dbHandlerrr = new DatabaseTest();
                ResultSet resultr = dbHandlerrr.getTestAuthor(Global.LoginInProg);
                while (true) {
                    try {
                        if (!resultr.next()) break;

                        int id = resultr.getInt(1);
                        String Name = resultr.getString(2);
                        String Topic = resultr.getString(3);
                        String Author = resultr.getString(4);
                        int Count = resultr.getInt(6);


                        Test testr = new Test(id, Name, Topic, Author, Count);
                        Table.getItems().add(testr);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }


                }



            }else ErrorText.setText("Вы не выбрали тест");







        });

        EditBt.setOnAction(event-> {


            if (Table.getSelectionModel().getSelectedItem() != null) {
                Test test;
                test = Table.getSelectionModel().getSelectedItem();


               Global.IdTest= (test.getTestId());
                EditBt.getScene().getWindow().hide();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("FXML//qwe.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Редактирование теста");
                primaryStage.setScene(new Scene(root, 600, 475));
                primaryStage.setResizable(false);
                primaryStage.show();



            }else ErrorText.setText("Вы не выбрали тест");







































        });


        Result.setOnAction(event->{
            if (Table.getSelectionModel().getSelectedItem() != null) {

                Test test;
                test = Table.getSelectionModel().getSelectedItem();


                Global.IdTest= (test.getTestId());
            Result.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("FXML//Result.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Результаты");
            primaryStage.setScene(new Scene(root, 600, 475));
            primaryStage.setResizable(false);
            primaryStage.show();
            }else ErrorText.setText("Вы не выбрали тест");

        });


        BackBt.setOnAction(event->{
            BackBt.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("FXML//meinmenu.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Мои тесты");
            primaryStage.setScene(new Scene(root, 600, 475));
            primaryStage.setResizable(false);
            primaryStage.show();


        });












    }
}
