package org.example.train_homepage;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class Booking_Travel extends Application implements Initializable {
    @FXML
    private AnchorPane travelPane;
    private String selectedFrom;
    private String selectedTo;
    private LocalDate selectedDate;
    int counter = 0;

    public String time1From,time1To,estimateTime1,estimateTime2,estimateTime3,dateDepart,time2From,time2To,time3From,time3To,Rm1,Rm2,Rm3;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ImageView adult1;

    @FXML
    private ImageView adult10;

    @FXML
    private ImageView adult11;

    @FXML
    private ImageView adult12;

    @FXML
    private ImageView adult2;

    @FXML
    private ImageView adult3;

    @FXML
    private ImageView adult4;

    @FXML
    private ImageView adult5;

    @FXML
    private ImageView adult6;

    @FXML
    private ImageView adult7;

    @FXML
    private ImageView adult8;

    @FXML
    private ImageView adult9;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnMinus;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnlogOut;

    @FXML
    private ChoiceBox<String> choiceFrom;
    private String[] FromPlace = {"Padang Besar","Taiping","Penang","Singapore"};

    @FXML
    private ChoiceBox<String> choiceTo;
    private String[] ToPlace = {"Padang Besar","Taiping","Penang","Singapore"};

    @FXML
    private DatePicker goDate;

    @FXML
    private TextField txtCounter;
    @FXML
    private Label lblMore;

    // Global variable
    public static String currTicketType;

    public static int travellerNum;

    @FXML
    void morePassenger(MouseEvent event) {
        Node sourceNode = (Node) event.getSource();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("More than 12 passengers?");
        alert.setHeaderText(null);
        alert.setContentText("To make a booking with more than 12 passengers, please contact our Call Center\nPhone: 03-23891234\nEmail: railwave@gmail.com");
        alert.getButtonTypes().setAll(ButtonType.OK);

        // Set minimum width based on content length
        Text text = new Text(alert.getContentText());
        text.wrappingWidthProperty().bind(alert.getDialogPane().widthProperty().subtract(40));
        double textHeight = text.getBoundsInLocal().getHeight();
        alert.getDialogPane().setMinHeight(textHeight + 100); // Adjust 100 as needed for padding

        ButtonType result = alert.showAndWait().orElse(ButtonType.OK);
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
        makeFadeOut(() -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
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


    public void switchTravel_Booking(ActionEvent event) throws IOException {
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
    void showProfile(ActionEvent event) {
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
        });

    }

    private void makeFadeOut(Runnable action) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(500));
        fadeTransition.setNode(travelPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(event -> action.run());
        fadeTransition.play();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Add choice box items
        choiceFrom.getItems().addAll(FromPlace);
        choiceTo.getItems().addAll(ToPlace);

        // Disable past dates in the DatePicker
        goDate.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) < 0);
            }
        });

        // Set initial value for counter text field
        txtCounter.setText(String.valueOf(counter));

        // Fade in transition
        travelPane.setOpacity(0);
        makeFadeInTransition();
    }

    @FXML
    void btnMinusClicked(ActionEvent event) {
        counter = Integer.parseInt(txtCounter.getText());
        counter = Math.max(0, counter - 1);//Ensure the counter is not negative
        txtCounter.setText(String.valueOf(counter));
        if (counter==0){
            adult1.setVisible(false) ;
        } else if (counter == 1) {
            adult2.setVisible(false);
        } else if (counter ==2) {
            adult3.setVisible(false);
        }else if (counter == 3) {
            adult4.setVisible(false);
        } else if (counter ==4) {
            adult5.setVisible(false);
        }else if (counter == 5) {
            adult6.setVisible(false);
        } else if (counter ==6) {
            adult7.setVisible(false);
        }else if (counter == 7) {
            adult8.setVisible(false);
        } else if (counter ==8) {
            adult9.setVisible(false);
        }else if (counter ==9) {
            adult10.setVisible(false);
        }else if (counter == 10) {
            adult11.setVisible(false);
        } else if (counter ==11) {
            adult12.setVisible(false);
        }

    }

    @FXML
    void btnAddClicked(ActionEvent event) {
        counter = Integer.parseInt(txtCounter.getText());
        counter = Math.min(12, counter + 1);//Ensure the counter is not negative
//        counter = counter + 1;
        txtCounter.setText(String.valueOf(counter));

        if (counter==1){
            adult1.setOpacity(0);
            adult1.setVisible(true) ;
            FadeTransition fadeTransition= new FadeTransition(Duration.seconds(0.5), adult1);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();

        } else if (counter == 2) {
            adult2.setOpacity(0);
            adult2.setVisible(true);
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), adult2);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();

        } else if (counter ==3) {
            adult3.setOpacity(0);
            adult3.setVisible(true);
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), adult3);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();

        }else if (counter == 4) {
            adult4.setOpacity(0);
            adult4.setVisible(true);
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), adult4);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();

        } else if (counter ==5) {
            adult5.setOpacity(0);
            adult5.setVisible(true);
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), adult5);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();

        }else if (counter == 6) {
            adult6.setOpacity(0);
            adult6.setVisible(true);
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), adult6);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();

        } else if (counter ==7) {
            adult7.setOpacity(0);
            adult7.setVisible(true);
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), adult7);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();

        }else if (counter == 8) {
            adult8.setOpacity(0);
            adult8.setVisible(true);
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), adult8);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();

        } else if (counter ==9) {
            adult9.setOpacity(0);
            adult9.setVisible(true);
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), adult9);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();

        }else if (counter ==10) {
            adult10.setOpacity(0);
            adult10.setVisible(true);
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), adult10);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();

        }else if (counter == 11) {
            adult11.setOpacity(0);
            adult11.setVisible(true);
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), adult11);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();

        } else if (counter ==12) {
            adult12.setOpacity(0);
            adult12.setVisible(true);
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), adult12);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();
        }

    }

    @FXML
    void searching(ActionEvent event) throws IOException {
        if (choiceFrom.getValue() == null || choiceTo.getValue() == null || goDate.getValue() == null || txtCounter.getText().isEmpty()) {
            warningEmpty();
        } else if (choiceFrom.getValue().equals(choiceTo.getValue())) {
            warningSame();
            choiceFrom.setValue("");
            choiceTo.setValue("");
        } else { // pass all error checking
            selectedFrom = choiceFrom.getValue();
            selectedTo = choiceTo.getValue();
            selectedDate = goDate.getValue();



            if (Objects.equals(selectedFrom, "Singapore") && Objects.equals(selectedTo, "Penang")) {
                time1From = "13:30";
                time1To = "18:07";
                estimateTime1 = "04h 37m";
                Rm1 = "RM73";
                time2From = "15:23";
                time2To = "20.02";
                estimateTime2 = "04h 39m";
                Rm2 = "RM75";
                time3From = "17:42";
                time3To = "22:17";
                estimateTime3 = "04h 35m";
                Rm3 = "RM73";


            } else if (Objects.equals(selectedFrom, "Singapore") && Objects.equals(selectedTo, "Taiping")) {
                time1From = "13:30";
                time1To = "17:32";
                estimateTime1 = "04h 02m";
                Rm1 = "RM63";
                time2From = "15:23";
                time2To = "19:24";
                estimateTime2 = "04h 01m";
                Rm2 = "RM64";
                time3From = "17:42";
                time3To = "21:47";
                estimateTime3 = "04h 05m";
                Rm3 = "RM63";

            } else if (Objects.equals(selectedFrom, "Singapore") && Objects.equals(selectedTo, "Padang Besar")){
                time1From = "11:30";
                time1To = "17:12";
                estimateTime1 = "05h 42m";
                Rm1 = "RM90";
                time2From = "14:23";
                time2To = "20:14";
                estimateTime2 = "05h 51m";
                Rm2 = "RM92";
                time3From = "17:00";
                time3To = "23:01";
                estimateTime3 = "06h 01m";
                Rm3 = "RM91";

            } else if (Objects.equals(selectedFrom, "Taiping") && Objects.equals(selectedTo, "Padang Besar")){
                time1From = "09:58";
                time1To = "12:56";
                estimateTime1 = "01h 58m";
                Rm1 = "RM34";
                time2From = "14:08";
                time2To = "16:11";
                estimateTime2 = "02h 03m";
                Rm2 = "RM32";
                time3From = "18:41";
                time3To = "20:45";
                estimateTime3 = "02 04m";
                Rm3 = "RM32";

            } else if (Objects.equals(selectedFrom, "Taiping") && Objects.equals(selectedTo, "Singapore")) {
                time1From = "13:30";
                time1To = "17:32";
                estimateTime1 = "04h 02m";
                Rm1 = "RM62";
                time2From = "15:23";
                time2To = "19:24";
                estimateTime2 = "04h 01m";
                Rm2 = "RM61";
                time3From = "17:42";
                time3To = "21:47";
                estimateTime3 = "04h 05m";
                Rm3 = "RM62";

            } else if (Objects.equals(selectedFrom, "Taiping") && Objects.equals(selectedTo, "Penang")){
                time1From = "12:55";
                time1To = "13:32";
                estimateTime1 = "00h 37m";
                Rm1 = "RM12";
                time2From = "15:12";
                time2To = "15:43";
                estimateTime2 = "00h 31m";
                Rm2 = "RM13";
                time3From = "18:57";
                time3To = "19:37";
                estimateTime3 = "00h 40m";
                Rm3 = "RM12";

            } else if (Objects.equals(selectedFrom, "Penang") && Objects.equals(selectedTo, "Singapore")){
                time1From = "08:30";
                time1To = "13:07";
                estimateTime1 = "04h 37m";
                Rm1 = "RM72";
                time2From = "14:23";
                time2To = "19.02";
                estimateTime2 = "04h 39m";
                Rm2 = "RM71";
                time3From = "16:42";
                time3To = "21:21";
                estimateTime3 = "04h 39m";
                Rm3 = "RM72";

            } else if (Objects.equals(selectedFrom, "Penang") && Objects.equals(selectedTo, "Padang Besar")) {
                time1From = "09:58";
                time1To = "11:13";
                estimateTime1 = "01h 15m";
                Rm1 = "RM26";
                time2From = "11:39";
                time2To = "12:51";
                estimateTime2 = "01h 13m";
                Rm2 = "RM25";
                time3From = "16:41";
                time3To = "17:49";
                estimateTime3 = "01h 08m";
                Rm3 = "RM26";

            } else if (Objects.equals(selectedFrom, "Penang") && Objects.equals(selectedTo, "Taiping")){
                time1From = "14:55";
                time1To = "15:32";
                estimateTime1 = "00h 37m";
                Rm1 = "RM12";
                time2From = "18:12";
                time2To = "18:43";
                estimateTime2 = "00h 31m";
                Rm2 = "RM13";
                time3From = "21:57";
                time3To = "22:37";
                estimateTime3 = "00h 40m";
                Rm3 = "RM12";

            } else if (Objects.equals(selectedFrom, "Padang Besar") && Objects.equals(selectedTo, "Singapore")){
                time1From = "10:22";
                time1To = "16:04";
                estimateTime1 = "05h 42m";
                Rm1 = "RM92";
                time2From = "13:47";
                time2To = "19:38";
                estimateTime2 = "05h 51m";
                Rm2 = "RM93";
                time3From = "18:00";
                time3To = "24:01";
                estimateTime3 = "06h 01m";
                Rm3 = "RM89";

            } else if (Objects.equals(selectedFrom, "Padang Besar") && Objects.equals(selectedTo, "Penang")) {
                time1From = "07:25";
                time1To = "08:40";
                estimateTime1 = "01h 15m";
                Rm1 = "RM24";
                time2From = "13:18";
                time2To = "14:31";
                estimateTime2 = "01h 13m";
                Rm2 = "RM26";
                time3From = "19:41";
                time3To = "20:49";
                estimateTime3 = "01h 08m";
                Rm3 = "RM23";

            } else if (Objects.equals(selectedFrom, "Padang Besar") && Objects.equals(selectedTo, "Taiping")) {
                time1From = "10:34";
                time1To = "12:32";
                estimateTime1 = "01h 58m";
                Rm1 = "RM34";
                time2From = "15:27";
                time2To = "17:30";
                estimateTime2 = "02h 03m";
                Rm2 = "RM32";
                time3From = "19:55";
                time3To = "21:59";
                estimateTime3 = "02 04m";
                Rm3 = "RM33";
            }

            currTicketType = choiceFrom.getValue() + "_" + choiceTo.getValue(); // saved the From.To
            travellerNum = Integer.parseInt(txtCounter.getText()); // saved the traveller num maximum

            System.err.println(currTicketType + " " + travellerNum);    /* Debug usage */

            FXMLLoader loader = new FXMLLoader(getClass().getResource("booking-chooseTicket-view.fxml"));
            root = loader.load();
            Booking_ChooseTicket bookingChooseTicket = loader.getController();
            bookingChooseTicket.displayTicket1(time1From,time1To,selectedFrom,selectedTo,selectedDate,estimateTime1,Rm1);
            bookingChooseTicket.displayTicket2(time2From,time2To,selectedFrom,selectedTo,selectedDate,estimateTime2,Rm2);
            bookingChooseTicket.displayTicket3(time3From,time3To,selectedFrom,selectedTo,selectedDate,estimateTime3,Rm3);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);

            // Hide the current stage
            Stage currentStage = (Stage) btnSearch.getScene().getWindow();
            currentStage.hide();

            stage.show();
        }
    }

    public void warningSame(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setContentText("Unable to choose the same place");
        alert.setHeaderText("Invalid Input");
        alert.showAndWait();
    }
    public void warningEmpty(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setContentText("Blank space not filled in");
        alert.setHeaderText("Invalid Input");
        alert.showAndWait();
    }

    public void SingaporeToPenang(){


    }
    private void makeFadeInTransition() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(500));
        fadeTransition.setNode(travelPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

//    public void restoreState(String selectedFrom, String selectedTo, LocalDate selectedDate, int counter) {
//        this.selectedFrom = selectedFrom;
//        this.selectedTo = selectedTo;
//        this.selectedDate = selectedDate;
//        this.counter = counter;
//
//        // Set the restored state to UI elements
//        // For example:
//        // choiceFrom.setValue(selectedFrom);
//        // choiceTo.setValue(selectedTo);
//        // goDate.setValue(selectedDate);
//        // txtCounter.setText(String.valueOf(counter));
//    }

}