package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException {
        JDBC.makeConnection();
        JDBC.makePreparedStatement("select * from users",JDBC.getConnection());
        ResultSet result = JDBC.getPreparedStatement().executeQuery();
        while (result.next()){
            System.out.println(result.getString(6));
        }
        launch(args);
        JDBC.closeConnection();
    }
}
