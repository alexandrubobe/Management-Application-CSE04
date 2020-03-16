package nl.tudelft.oopp.controllers;

import static nl.tudelft.oopp.MainApp.switchScene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import nl.tudelft.oopp.MainApp;

public class MainSceneController implements Initializable {

    @FXML
    private Text username;

    @FXML
    private Text res;

    private static int status = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(MainApp.user.getUserName());
        if (status == 1) {
            changeResConfirmed();
            setStatus(0);
        }
    }

    @FXML
    public void reserveRoomButtonHandler(ActionEvent actionEvent) throws IOException {
        switchScene(actionEvent, "/roomReservationScene.fxml", "Reserve a room");
    }

    @FXML
    public void rentBikeButtonHandler(ActionEvent actionEvent) throws IOException {
        switchScene(actionEvent, "/?.fxml");
    }

    @FXML
    public void carParkButtonHandler(ActionEvent actionEvent) throws IOException {
        switchScene(actionEvent, "/?.fxml");
    }

    @FXML
    public void restaurantButtonHandler(ActionEvent actionEvent) throws IOException {
        switchScene(actionEvent, "/?.fxml");
    }

    @FXML
    public void accountButtonHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/accountScene.fxml", "Account Settings");
    }

    public void changeResConfirmed() {
        res.setText("The room has been successfully reserved!");
    }

    public static void setStatus(int newStatus) {
        status = newStatus;
    }
}