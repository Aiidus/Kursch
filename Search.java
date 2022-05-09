
    package sample;

    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.scene.control.Button;
    import javafx.scene.control.TableColumn;
    import javafx.scene.control.TableView;
    import javafx.scene.control.TextField;
    import javafx.scene.control.cell.PropertyValueFactory;
    import javafx.scene.text.Text;
    import javafx.stage.Stage;

    import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
    import java.util.Objects;

    public class Search {
        @FXML
        private TableColumn<?,?> AuthorCl;

        @FXML
        private Button BackBt;

        @FXML
        private TableColumn<?,?> CountQuestCl1;

        @FXML
        private TextField FindArea;

        @FXML
        private TableColumn<?,?> NameCl;

        @FXML
        private Text ErrorText;


        @FXML
        private Button OpenBt;

        @FXML
        private Button SearchBt;

        @FXML
        private TableView<Test> Table;

        @FXML
        private TableColumn<?,?> TopicCl;
        @FXML

        void initialize(){



            NameCl.setCellValueFactory(new PropertyValueFactory<>("testName"));
            TopicCl.setCellValueFactory(new PropertyValueFactory<>("topicTest"));
            AuthorCl.setCellValueFactory(new PropertyValueFactory<>("testAuthor"));
            CountQuestCl1.setCellValueFactory(new PropertyValueFactory<>("testCol"));


            SearchBt.setOnAction(event->{

                Table.getItems().clear();


                if(FindArea.getText().equals("")) {
                    DatabaseTest dbHandler = new DatabaseTest();
                    ResultSet result = dbHandler.getTest();
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
                }else {
                    DatabaseTest dbHandler = new DatabaseTest();
                    ResultSet result = dbHandler.getTestWord(FindArea.getText());
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


                }
            });

            OpenBt.setOnAction(event->{
                if (Table.getSelectionModel().getSelectedItem() != null) {
                    Test test;
                    test = Table.getSelectionModel().getSelectedItem();


                     Global.IdTest = test.getTestId();
                    OpenBt.getScene().getWindow().hide();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FXML//test.fxml")));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage primaryStage = new Stage();
                    primaryStage.setTitle("Тесты");
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
                primaryStage.setTitle("Меню");
                primaryStage.setScene(new Scene(root, 600, 475));
                primaryStage.setResizable(false);
                primaryStage.show();


            });






        }


    }


