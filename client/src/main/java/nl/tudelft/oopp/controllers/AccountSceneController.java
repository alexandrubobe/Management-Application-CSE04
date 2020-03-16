package nl.tudelft.oopp.controllers;

import static nl.tudelft.oopp.MainApp.switchScene;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import nl.tudelft.oopp.MainApp;
import nl.tudelft.oopp.communication.ServerCommunication;
import nl.tudelft.oopp.communication.UserReservationInfo;


public class AccountSceneController implements Initializable {

    @FXML
    private Text accountId;

    @FXML
    private Text accountRole;

    @FXML
    private Text accountEmail;

    @FXML
    private Text accountUsername;

    @FXML
    private VBox userReservationInfoList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accountId.setText(Integer.toString(MainApp.user.getUserId()));
        accountRole.setText(MainApp.user.getRole().getRoleName());
        accountEmail.setText(MainApp.user.getEmail());
        accountUsername.setText(MainApp.user.getUserName());
        try {
            ArrayList<UserReservationInfo> reservations = ServerCommunication.getUserReserationInfo(MainApp.user.getUserId());
            userReservationInfoList.getChildren().clear();
            for (UserReservationInfo uri: reservations) {
                displayUserReservationInfo(uri);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method creates a list of all the room reservations and adds them to the Vbox.
     * @author Kanish Dwivedi
     * @param uri - the UserReservationInfo object which is to be displayed.
     */
    public void displayUserReservationInfo(UserReservationInfo uri) {

        Text information = new Text("Day: " + uri.getDay() + " ,StartTime: " + uri.getStartTime() + ", EndTime: " + uri.getEndTime()
            + ", Building: " + uri.getBuildingName() + ", Room: " + uri.getRoomName() + " (" + uri.getName() + ") " + "\n Unique reservationID: " + uri.getReservationID());
        HBox reservationinfo = new HBox(information);
        reservationinfo.setPadding(new Insets(10, 0, 10, 0));
        userReservationInfoList.getChildren().add(reservationinfo);
    }



    @FXML
    public void logoutButtonController(MouseEvent mouseEvent) throws IOException {
        MainApp.user = null;
        switchScene(mouseEvent, "/Welcome.fxml", "Welcome to the application");
    }

    @FXML
    public void bckButtonController(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/mainScene.fxml", "Tu Delft Reservation Application");
    }


}