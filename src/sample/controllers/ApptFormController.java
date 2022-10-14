package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.models.Appointment;

import java.io.IOException;

public class ApptFormController {
    public Button cancelBtn;
    public Button addBtn;
    public TextField idFiled;
    public TextField titleField;
    public TextField locField;
    public TextField descField;
    public TextField typeField;
    public ChoiceBox contactBox;
    public DatePicker startPicker;
    public DatePicker endPicker;
    public static Appointment apptModify;
    private boolean modify = apptModify != null;



    public void onCancel(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/sample.fxml"));
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(),630,400);
        stage.setTitle("Customer Form");
        stage.setScene(scene);
    }
}
