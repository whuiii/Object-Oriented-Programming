package org.example.train_homepage;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class HelloController {

    @FXML
    private AnchorPane homePane;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ImageView image;
    @FXML
    private Button btnlogOut;
    @FXML
    private Label lblHello;

    public static String userName;
    public static String passWord;



    @FXML
    void switchTravel_Booking(ActionEvent event) throws IOException {
        makeFadeOut(() -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("booking-view.fxml")));
                Node sourceNode = (Node) event.getSource();
                Stage stage = (Stage) sourceNode.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

    }

    @FXML
    void showAboutUs(ActionEvent event) {
        makeFadeOut(() -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("about-us.fxml")));
                Node sourceNode = (Node) event.getSource();
                Stage stage = (Stage) sourceNode.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }


    @FXML
    void switchProfile(ActionEvent event) throws IOException {
        makeFadeOut(() -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("profile.fxml")));
                Node sourceNode = (Node) event.getSource();
                Stage stage = (Stage) sourceNode.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
    @FXML
    void btnLogOutClicked(ActionEvent event)throws IOException {
        makeFadeOut(() -> {
            try {
                // Load the login-view.fxml after fade-out
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-view.fxml")));
                Node sourceNode = (Node) event.getSource();
                Stage stage = (Stage) sourceNode.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    @FXML
    public void initialize() {
        // Set the image to be fully transparent initially
        homePane.setOpacity(0);

        // Create a FadeTransition with a duration of 1.5 seconds
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2.0), homePane);
        fadeTransition.setFromValue(0);
        // Set the target opacity (1 for fully opaque)
        fadeTransition.setToValue(1);

        // Play the fade-in animation when the controller is initialized
        fadeTransition.play();


    }

    public void passName(String username, String password){
//        lblHello.setText("Hello: " + username + "    " + password);
        userName = username;
        passWord = password;
    }
    public static void main(String[] args){
        userName = Profile.username;
    }

    @FXML
    void showTicket(ActionEvent event) {
        makeFadeOut(() -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("displatTicket-view.fxml")));
                Node sourceNode = (Node) event.getSource();
                Stage stage = (Stage) sourceNode.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void makeFadeOut(Runnable action) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(500));
        fadeTransition.setNode(homePane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(event -> action.run());
        fadeTransition.play();
    }

}