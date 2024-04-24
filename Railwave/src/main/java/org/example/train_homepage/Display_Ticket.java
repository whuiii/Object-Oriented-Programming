package org.example.train_homepage;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class Display_Ticket {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button btnlogOut;

    @FXML
    private VBox vboxTicketContainer;

    @FXML
    private AnchorPane paneTicket;

    @FXML
    private Pane paneRezise;

    public String username;

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

    @FXML
    void switchTravel_Booking(ActionEvent event) {
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
    public void initialize() {
//        paneRezise.setPrefSize(paneRezise.getPrefHeight(),paneRezise.getPrefWidth());
//        paneRezise.setPrefHeight(paneRezise.getPrefHeight());
        // Set the image to be fully transparent initially
        paneTicket.setOpacity(0);
        // Create a FadeTransition with a duration of 1.5 seconds
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2.0), paneTicket);
        fadeTransition.setFromValue(0);
        // Set the target opacity (1 for fully opaque)
        fadeTransition.setToValue(1);
        // Play the fade-in animation when the controller is initialized
        fadeTransition.play();

        // read adn display the ticket details
        displayTicketDetails();
    }

    private void displayTicketDetails() {
//        System.out.println(Login.currUsername);
        System.out.println(Profile.username);// use to check the name

        String ticket_detail_file_path = "ticketDetail.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(ticket_detail_file_path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(",");
                /*
                 RW_1,jj666,C12C,Taiping_Singapore,13:30,17:32,04h 02m,62.0
                */
                    if ((parts.length == 8 && parts[1].equals(Profile.username)) || (parts.length == 8 && parts[1].equals(HelloController.userName))){
                        // create a new ticket by passing all the info from data line
                        createNewTicket(parts);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Error: Invalid ticket detail format - " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: Ticket detail file not found.");
        } catch (IOException e) {
            System.err.println("Error: Failed to read ticket details.");
        }
    }


    private void createNewTicket(String[] ticketData) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ticket_obj.fxml"));
        Pane ticketPane = loader.load();

        // Access the CustomController instance
        TicketObj ticketObj = loader.getController();

        ticketObj.setLblTicketID(ticketData[0]);
        ticketObj.setLblName(ticketData[1]);
        ticketObj.setLblSeatID(ticketData[2]);
        ticketObj.setLblFrom((ticketData[3].split("_")[0]));
        ticketObj.setLblTo((ticketData[3].split("_")[1]));
        ticketObj.setLblDepart(ticketData[4]);
        ticketObj.setLblArrive(ticketData[5]);
        ticketObj.setLblDuration(ticketData[6]);
        ticketObj.setLblPrice("RM " + ticketData[7]);

        // Add the taskBar to the VBox
        vboxTicketContainer.getChildren().add(ticketPane);
    }

    private Pane addPane(){
        Pane canvas = new Pane();
        canvas.setStyle("-fx-background-color: white;-fx-background-radius: 10px;");
        canvas.setPrefSize(682,274);
        canvas.getChildren().add(addImage());
        canvas.getChildren().add(addRailwave());
        canvas.getChildren().add(addImageArrow());
        canvas.getChildren().add(addlblFrom());
        canvas.getChildren().add(addlblTo());
        canvas.getChildren().add(passName());
        canvas.getChildren().add(seat());
        canvas.getChildren().add(timeDepart());
        canvas.getChildren().add(timeReach());
        canvas.getChildren().add(duration());
        canvas.getChildren().add(addImageDot1());
//        canvas.getChildren().add(addImageDot2());
//        canvas.getChildren().add(addImageDot3());
        canvas.getChildren().add(addImageDot4());
        canvas.getChildren().add(slogan());
        canvas.getChildren().add(btnDelete());
        canvas.getChildren().add(addImageTicket());
        canvas.getChildren().add(lblReferenceCode());

        return canvas;
    }

    private ImageView addImage(){
        Image image = new Image("file:///C:/Users/HP/Desktop/Undergraduate/1st Year 2nd Sem/Object Oriented Programming/Project/train_homepage_latest/src/main/resources/images/RailWave_noWord.png");
        ImageView logo = new ImageView(image);
        logo.setFitHeight(34);
        logo.setFitWidth(71);
        logo.setLayoutX(14);
        logo.setLayoutY(8);
        return logo;
    }

    private Label addRailwave(){
        Label lblRailwave = new Label();
        lblRailwave.setText("Railwave");
        lblRailwave.setFont(Font.font("System", FontWeight.BOLD,24));
        lblRailwave.setLayoutX(93);
        lblRailwave.setLayoutY(6);
        return lblRailwave;
    }
    private Label addlblFrom(){
        Label lblFrom = new Label();
        //read from text file the depart place
        lblFrom.setText("Singapore");
        lblFrom.setFont(Font.font("System", FontWeight.BOLD,20));
        lblFrom.setLayoutX(32);
        lblFrom.setLayoutY(51);
        return lblFrom;
    }

    private Label addlblTo(){
        Label lblTo = new Label();
        //read from text file the depart place
        lblTo.setText("Penang");
        lblTo.setFont(Font.font("System", FontWeight.BOLD,20));
        lblTo.setLayoutX(204);
        lblTo.setLayoutY(51);
        return lblTo;
    }
    private ImageView addImageArrow(){
        Image image = new Image("file:///C:/Users/HP/Desktop/Undergraduate/1st Year 2nd Sem/Object Oriented Programming/Project/train_homepage_latest/src/main/resources/images/rightArrow.png");
        ImageView imageArrow = new ImageView(image);
        imageArrow.setFitHeight(34);
        imageArrow.setFitWidth(34);
        imageArrow.setLayoutX(160);
        imageArrow.setLayoutY(51);
        return imageArrow;
    }

    private Label passName(){
        Label lblpassName = new Label();
        String name;
        //read from text file the depart place
        name = "Name: " + "wen hui";
        lblpassName.setText(name);
        lblpassName.setFont(Font.font("System", FontWeight.NORMAL,14));
        lblpassName.setLayoutX(22);
        lblpassName.setLayoutY(92);
        return lblpassName;
    }

    private Label seat(){
        Label lblSeat = new Label();
        String seat;
        //read from text file the depart place
        seat = "Seat: " + "B25";
        lblSeat.setText(seat);
        lblSeat.setFont(Font.font("System", FontWeight.NORMAL,14));
        lblSeat.setLayoutX(22);
        lblSeat.setLayoutY(125);
        return lblSeat;
    }

    private Label timeDepart(){
        Label lbltimeDepart = new Label();
        //read from text file the depart place
        lbltimeDepart.setText("13:30PM");
        lbltimeDepart.setFont(Font.font("System", FontWeight.NORMAL,14));
        lbltimeDepart.setLayoutX(120);
        lbltimeDepart.setLayoutY(159);
        return lbltimeDepart;
    }

    private Label timeReach(){
        Label lbltimeReach = new Label();
        //read from text file the depart place
        lbltimeReach.setText("18:07PM");
        lbltimeReach.setFont(Font.font("System", FontWeight.NORMAL,14));
        lbltimeReach.setLayoutX(550);
        lbltimeReach.setLayoutY(159);
        return lbltimeReach;
    }

    private Label duration(){
        Label lblDuration = new Label();
        //read from text file the depart place
        lblDuration.setText("04h 37m");
        lblDuration.setFont(Font.font("System", FontWeight.NORMAL,14));
        lblDuration.setLayoutX(330);
        lblDuration.setLayoutY(202);
        return lblDuration;
    }
    private ImageView addImageTicket(){
        Image image = new Image("file:///C:/Users/HP/Desktop/Undergraduate/1st Year 2nd Sem/Object Oriented Programming/Project/train_homepage_latest/src/main/resources/images/RailWave_noWord.png");
        ImageView logo = new ImageView(image);
        logo.setFitHeight(34);
        logo.setFitWidth(71);
        logo.setLayoutX(330);
        logo.setLayoutY(169);
        return logo;
    }

    private ImageView addImageDot1(){
        Image image = new Image("file:///C:/Users/HP/Desktop/Undergraduate/1st Year 2nd Sem/Object Oriented Programming/Project/train_homepage_latest/src/main/resources/images/dashLine.png");
        ImageView logo = new ImageView(image);
        logo.setFitHeight(60);
        logo.setFitWidth(93);
        logo.setLayoutX(210);
        logo.setLayoutY(156);
        return logo;
    }

    private ImageView addImageDot2(){
        Image image = new Image("file:///C:/Users/HP/Desktop/Undergraduate/1st Year 2nd Sem/Object Oriented Programming/Project/train_homepage_latest/src/main/resources/images/dashLine.png");
        ImageView logo = new ImageView(image);
        logo.setFitHeight(60);
        logo.setFitWidth(93);
        logo.setLayoutX(264);
        logo.setLayoutY(156);
        return logo;
    }
    private ImageView addImageDot3(){
        Image image = new Image("file:///C:/Users/HP/Desktop/Undergraduate/1st Year 2nd Sem/Object Oriented Programming/Project/train_homepage_latest/src/main/resources/images/dashLine.png");
        ImageView logo = new ImageView(image);
        logo.setFitHeight(60);
        logo.setFitWidth(93);
        logo.setLayoutX(394);
        logo.setLayoutY(156);
        return logo;
    }
    private ImageView addImageDot4(){
        Image image = new Image("file:///C:/Users/HP/Desktop/Undergraduate/1st Year 2nd Sem/Object Oriented Programming/Project/train_homepage_latest/src/main/resources/images/dashLine.png");
        ImageView logo = new ImageView(image);
        logo.setFitHeight(60);
        logo.setFitWidth(93);
        logo.setLayoutX(430);
        logo.setLayoutY(156);
        return logo;
    }

    private Label slogan(){
        Label lblSlogan = new Label();
        //read from text file the depart place
        lblSlogan.setText("Go. Book. Enjoy");
        lblSlogan.setFont(Font.font("System", FontWeight.BOLD,14));
        lblSlogan.setLayoutX(565);
        lblSlogan.setLayoutY(235);
        return lblSlogan;
    }

    private Button btnDelete(){
        Button btnDelete = new Button();
        btnDelete.setStyle("-fx-background-color:  #8635D7;-fx-background-radius: 10px;");
        btnDelete.setFont(Font.font("System", FontWeight.BOLD,12));
        btnDelete.setText("Delete Booking");
        btnDelete.setTextFill(Color.WHITE);
        btnDelete.setLayoutX(20);
        btnDelete.setLayoutY(235);
        btnDelete.setOnAction(event -> {
            // Define the behavior when the button is clicked
            // For example, you can call a method to handle the delete operation
            // handleDeleteBooking();
        });
        return btnDelete;
    }


    private Label lblReferenceCode(){
        Label lblReference = new Label();
        //read from text file the depart place
        lblReference.setText("RW20142");
        lblReference.setFont(Font.font("System", FontWeight.BOLD,14));
        lblReference.setLayoutX(606);
        lblReference.setLayoutY(6);
        return lblReference;
    }
    //    private void makeFadeInTransition() {
//        FadeTransition fadeTransition = new FadeTransition();
//        fadeTransition.setDuration(Duration.millis(500));
//        fadeTransition.setNode(mainPane);
//        fadeTransition.setFromValue(0);
//        fadeTransition.setToValue(1);
//
//        fadeTransition.play();
//    }
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
    void showSeat(ActionEvent event) {
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
        fadeTransition.setNode(paneTicket);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(event -> action.run());
        fadeTransition.play();
    }





    public static void main(String[] args){


    }
}
