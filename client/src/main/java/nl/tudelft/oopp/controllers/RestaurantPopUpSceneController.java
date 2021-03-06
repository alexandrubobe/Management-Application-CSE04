package nl.tudelft.oopp.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.tudelft.oopp.MainApp;
import nl.tudelft.oopp.communication.ServerCommunication;
import nl.tudelft.oopp.views.MainView;

public class RestaurantPopUpSceneController implements Initializable {
    @FXML private Text reservationUserName;
    @FXML Text foodName;
    @FXML Text price;
    @FXML Text restaurant;
    @FXML private Text date;
    @FXML private Text time;

    @FXML private Text resConfirmed;
    @FXML private Button cancelButton;
    @FXML private int foodId;
    private int resId;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reservationUserName.setText(MainApp.user.getUserName());
        foodName.setText(RestaurantSceneController.getFoodName());
        price.setText(String.valueOf(RestaurantSceneController.getPrice()));
        restaurant.setText(String.valueOf(RestaurantSceneController.getRestaurantName()));
        date.setText(RestaurantSceneController.getDate().toString());
        time.setText(RestaurantSceneController.getTimeSlot());
        foodId = RestaurantSceneController.getFoodId();
        resId = RestaurantSceneController.getResId();
    }

    @FXML
    public void backBtnHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * A method that is called when a user clicks on the order button, this method calls another method which
     * communicates with the server and stores the actual food reservation in the DB.
     * @param event an ActionEvent that activates this method whenever a user clicks on the order button
     * @throws URISyntaxException - url exception
     * @throws IOException - input/output exception
     */
    @FXML
    public void makeNewFoodOrder(ActionEvent event) throws URISyntaxException, IOException {
        String[] timeSlot = time.getText().split("-");
        String startTime = timeSlot[0];
        String endTime = timeSlot[1];
        int statusCode = ServerCommunication.createFoodReservation(foodId, resId,
                Date.valueOf(date.getText()), Time.valueOf(startTime), Time.valueOf(endTime));
        if (statusCode == -1) {
            System.out.println("Looks like something went wrong!");
            return;
        }
        System.out.println("Successfully ordered food!");
        backBtnHandler(event);
        MainSceneController.setStatus(3);
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/mainScene.fxml"));
        Parent root = loader.load();
        MainView.getPrimaryStage().setScene(new Scene(root));
    }
}
