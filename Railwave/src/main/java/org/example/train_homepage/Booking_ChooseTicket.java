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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Booking_ChooseTicket {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private String selectedFrom;
    private String selectedTo;
    private LocalDate selectedDate;
    private int counter;
    @FXML
    private AnchorPane choosepane;

    @FXML
    private ImageView btnBack;

    @FXML
    private Button btnlogOut;
    @FXML
    private Button seat1;

    @FXML
    private Label lblEstimate1;

    @FXML
    private Label lblEstimate2;

    @FXML
    private Label lblEstimate3;

    @FXML
    private Label lblFromPlace1;

    @FXML
    private Label lblFromPlace2;

    @FXML
    private Label lblFromPlace3;

    @FXML
    private Label lblTimeFrom1;

    @FXML
    private Label lblTimeFrom2;

    @FXML
    private Label lblTimeFrom3;

    @FXML
    private Label lblTimeTo1;

    @FXML
    private Label lblTimeTo2;

    @FXML
    private Label lblTimeTo3;

    @FXML
    private Label lblToPlace1;

    @FXML
    private Label lblToPlace2;

    @FXML
    private Label lblToPlace3;

    @FXML
    private Label lblDate1;

    @FXML
    private Label lblDate2;

    @FXML
    private Label lblDate3;

    @FXML
    private Label lblRm1;

    @FXML
    private Label lblRm2;

    @FXML
    private Label lblRm3;

    @FXML
    private Button seat2;

    @FXML
    private Button seat3;

    // global variable
    public static String departTime;

    @FXML
    void btnBackClicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("booking-view.fxml"));
        Parent root = loader.load();
        Booking_Travel controller = loader.getController();
        //controller.restoreState(selectedFrom, selectedTo, selectedDate, counter);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void btnLogOutClicked(ActionEvent event)throws IOException {
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
        ;
    }

    @FXML
    void switchHomePage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchTravel_Booking(ActionEvent event) throws IOException {
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
    }
    @FXML
    void showProfile(ActionEvent event) throws IOException {
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
        }) ;

    }

    //display for 1 ticket
    public void displayTicket1(String fromTime1, String fromTime2, String fromPlace1, String toPlace2, LocalDate date1,String estimate2, String Rm1){
        lblTimeFrom1.setText(fromTime1);
        lblTimeTo1.setText(fromTime2);
        lblEstimate1.setText(estimate2);
        lblFromPlace1.setText(fromPlace1);
        lblToPlace1.setText(toPlace2);
        lblDate1.setText(date1.toString());
        lblRm1.setText(Rm1);
    }

    public void displayTicket2(String fromTime1, String fromTime2, String fromPlace1, String toPlace2, LocalDate date1,String estimate2, String Rm2){
        lblTimeFrom2.setText(fromTime1);
        lblTimeTo2.setText(fromTime2);
        lblEstimate2.setText(estimate2);
        lblFromPlace2.setText(fromPlace1);
        lblToPlace2.setText(toPlace2);
        lblDate2.setText(date1.toString());
        lblRm2.setText(Rm2);
    }
    public void displayTicket3(String fromTime1, String fromTime2, String fromPlace1, String toPlace2, LocalDate date1,String estimate3, String Rm3){
        lblTimeFrom3.setText(fromTime1);
        lblTimeTo3.setText(fromTime2);
        lblEstimate3.setText(estimate3);
        lblFromPlace3.setText(fromPlace1);
        lblToPlace3.setText(toPlace2);
        lblDate3.setText(date1.toString());
        lblRm3.setText(Rm3);
    }

    @FXML
    void displayseat1(ActionEvent event) {
        Node triggeredNode = (Node) event.getSource();
        String id = triggeredNode.getId();
        switch(id){
            case "seat1":
                departTime = lblTimeFrom1.getText();
                break;
            case "seat2":
                departTime = lblTimeFrom2.getText();
                break;
            case "seat3":
                departTime = lblTimeFrom3.getText();
                break;
        }

        makeFadeOut(() -> {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("select-seat.fxml")));
            Node sourceNode = (Node) event.getSource();
            Stage stage = (Stage) sourceNode.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }});
    }
    private void makeFadeOut(Runnable action) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(500));
        fadeTransition.setNode(choosepane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(event -> action.run());
        fadeTransition.play();
    }

    // Method to set the state when navigating to this view
//    public void restoreState(String selectedFrom, String selectedTo, LocalDate selectedDate, int counter) {
//        this.selectedFrom = selectedFrom;
//        this.selectedTo = selectedTo;
//        this.selectedDate = selectedDate;
//        this.counter = counter;
//    }
}