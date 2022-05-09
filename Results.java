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

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Results {

    @FXML
    private Button BackBt;

    @FXML
    private TableColumn<?, ?> LoginCl;

    @FXML
    private TableColumn<?, ?> ResultCl;

    @FXML
    private TableView<Result> Table;

    @FXML
    private TableColumn<?, ?> TimeCl;

    @FXML

    void initialize() {

        LoginCl.setCellValueFactory(new PropertyValueFactory<>("Login"));
        TimeCl.setCellValueFactory(new PropertyValueFactory<>("resultTime"));
        ResultCl.setCellValueFactory(new PropertyValueFactory<>("resultInt"));
        DatabaseResult dbHandler = new DatabaseResult();
        ResultSet result = dbHandler.getResult(Global.IdTest);
        while (true) {
            try {
                if (!result.next()) break;


                int resultInt = result.getInt(2);
                String Time = result.getString(3);
                String Login = result.getString(5);


                Result res = new Result(Time,resultInt,Login);
                Table.getItems().add(res);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        }



        BackBt.setOnAction(event->{
            BackBt.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("FXML//mytest.fxml"));
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
