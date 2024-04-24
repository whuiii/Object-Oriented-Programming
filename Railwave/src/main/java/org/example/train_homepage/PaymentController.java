package org.example.train_homepage;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;


import static org.example.train_homepage.SelectSeatController.file_path_from_src;
import static org.example.train_homepage.SelectSeatController.selectedSeats;

public class PaymentController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnPay;

    @FXML
    private ComboBox<String> cmbMonth;

    @FXML
    private ComboBox<String> cmbYear;

    @FXML
    private Label lblRequireCVN;

    @FXML
    private Label lblRequireCardNumber;

    @FXML
    private Label lblRequireCardType;

    @FXML
    private Label lblRequireExpMonth;

    @FXML
    private Label lblRequireExpYear;

    @FXML
    private RadioButton radMastercard;

    @FXML
    private RadioButton radVisa;

    @FXML
    private Pane rootPane;

    @FXML
    private ScrollPane sclSeat;

    @FXML
    private TextField txtCVN;

    @FXML
    private TextField txtCardNum;

    @FXML
    private VBox vboxSeatContainer;

    @FXML
    private Label lblTotalAmount;

    @FXML
    private Label lblCVNLength;

    private String departTime;

    private String arriveTime;

    private String duration;

    private int ticketPrice;

    private int totalAmount;

    public String username;

    private ArrayList<String> occupiedSeats;

    public void initialize() {
        // Add items to the month combo box
        ObservableList<String> monthList = FXCollections.observableArrayList(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        );
        cmbMonth.setItems(monthList);

        // Get the current year and month
        int currentYear = LocalDate.now().getYear();
        int currentMonth = LocalDate.now().getMonthValue();

        // Add items to the year combo box
        ObservableList<String> yearList = FXCollections.observableArrayList();
        for (int year = currentYear; year <= currentYear + 10; year++) { // Let's limit to 10 years in the future
            yearList.add(String.valueOf(year));
        }
        cmbYear.setItems(yearList);

        cmbYear.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null && Integer.parseInt(newValue) == currentYear) {
                // If the selected year is the current year, disable past months
                ObservableList<String> updatedMonthList = FXCollections.observableArrayList();
                for (int i = currentMonth; i <= 12; i++) {
                    updatedMonthList.add(monthList.get(i - 1));
                }
                cmbMonth.setItems(updatedMonthList);
            } else {
                // If the selected year is not the current year, enable all months
                cmbMonth.setItems(monthList);
            }
        });

        vboxSeatContainer.getChildren().clear(); // clear the seatContainer

        for (String seat : selectedSeats) {
            Label label = new Label(seat); // Create a label for the seat
            label.setText(seat);
            setDynamicLabelStyle(label);
            vboxSeatContainer.getChildren().add(label); // Add label to the VBox
        }

        // get the ticket price
        ticketPrice= getTicketPrice();
        totalAmount = ticketPrice * selectedSeats.size();
        lblTotalAmount.setText("RM " + totalAmount);
    }

    private int getTicketPrice(){
        String file_path= "ticket_price.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(file_path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(Booking_Travel.currTicketType)){
                    if (parts[1].equals(Booking_ChooseTicket.departTime)){
                        departTime = parts[1];
                        arriveTime = parts[2];
                        duration = parts[3];
                        return Integer.parseInt(parts[4]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0; // missing information
    }

    void setDynamicLabelStyle(Label label){
        label.setStyle(
                "-fx-background-color: #8635D7;" +
                        "-fx-background-radius: 7.5;" +
                        "-fx-pref-width: 80;" +
                        "-fx-pref-height: 25;"
        );
        label.setTextFill(Color.web("#FFFFFF"));
        label.setAlignment(Pos.CENTER);
    }

    @FXML
    void onClickButtonBack(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("select-seat.fxml")));
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
    void onClickRadVisa(ActionEvent event) {
        radMastercard.setSelected(false);
        radVisa.setSelected(true);

        radVisa.setDisable(true); // Disable the button
        radMastercard.setDisable(true);
        // Create a Timeline with a KeyFrame to enable the button after 2 seconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                radMastercard.setDisable(false); // Enable the button after 3 seconds
                radVisa.setDisable(false);
            }
        }));
        timeline.setCycleCount(1); // Run the timeline once
        timeline.play(); // Start the timeline
    }

    @FXML
    void onClickRadMastercard(ActionEvent event) {
        radVisa.setSelected(false);
        radMastercard.setSelected(true);

        radVisa.setDisable(true); // Disable the button
        radMastercard.setDisable(true);
        // Create a Timeline with a KeyFrame to enable the button after 2 seconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                radMastercard.setDisable(false); // Enable the button after 3 seconds
                radVisa.setDisable(false);
            }
        }));
        timeline.setCycleCount(1); // Run the timeline once
        timeline.play(); // Start the timeline
    }

    @FXML
    void onClickButtonPay(ActionEvent event) throws IOException {
        // error checking for all require control
        if (!radVisa.isSelected() && !radMastercard.isSelected()){ // didn't choose card type
            lblRequireCardType.setVisible(true);
            return;
        }
        lblRequireCardType.setVisible(false);

        if (txtCardNum.getText().isEmpty()){
            lblRequireCardNumber.setVisible(true);
            return;
        }
        lblRequireCardNumber.setVisible(false);

        if (cmbMonth.getSelectionModel().getSelectedItem() == null){
            lblRequireExpMonth.setVisible(true);
            return;
        }
        lblRequireExpMonth.setVisible(false);

        if (cmbYear.getSelectionModel().getSelectedItem() == null){
            lblRequireExpYear.setVisible(true);
            return;
        }
        lblRequireExpYear.setVisible(false);

        if (txtCVN.getText().isEmpty()){
            lblRequireCVN.setVisible(true);
            return;
        }
        lblRequireCVN.setVisible(false);

        if (txtCVN.getText().length() != 3 && txtCVN.getText().length() != 4) {
            lblCVNLength.setTextFill(Color.RED);
            return;
        }
        lblCVNLength.setTextFill(Color.BLACK);

        // Show payment successful message
        showPaymentSuccessfulMessage();

        // update the seat occupation status
        changeSeatsOccupation();

        // add all new ticket detail into text file
        for (String seatId: selectedSeats){
            generateNewTicket(seatId);
        }

        // direct user to display-ticket page
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
    }

    // Method to show the messagebox popup
    private void showPaymentSuccessfulMessage() throws IOException {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Payment Successful");
        alert.setHeaderText(null);
        alert.setContentText("Payment Successfully!");

        alert.showAndWait();
    }

    // Method to generate new ticket detail
    private void generateNewTicket(String seatId) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("ticketDetail.txt" , true))) {
            String lineData = generateUniqueTicketID() + "," + HelloController.userName + "," + seatId + "," + Booking_Travel.currTicketType + "," + departTime + "," + arriveTime + "," + duration + "," + ticketPrice;
            System.err.println(lineData);
            bw.write(lineData);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to get the latest unique ID and add with 1
    private String generateUniqueTicketID() throws IOException {
        String file_path = "ticketDetail.txt";
        if (checkFileForRecords(file_path)){ // have data
            String lastLine = findLastLine(file_path);
            if(lastLine != null){
                String[] parts = lastLine.split(","); // RW_1,title,.....
                String currIndex = parts[0];
                int startIndex = currIndex.indexOf("_") + 1; // Find the index after the underscore

                int number = 0;
                if (startIndex != -1 && startIndex < currIndex.length()) {
                    String numberStr = currIndex.substring(startIndex); // Extract the substring after the underscore
                    number = Integer.parseInt(numberStr) + 1;
                    if (number > 0){
                        return "RW_" + number;
                    }
                }

            }
        }
        return "RW_1"; // no data
    }

    // Method to handle return of the last data line (latest data) in text file
    public static String findLastLine(String filePath) throws IOException {
        String lastLine = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim() != null && line.split(",").length == 8){
                    lastLine = line;
                }
            }
        }
        return lastLine;
    }

    // Method to handle return the file recording status [empty/contain ticket details]
    public boolean checkFileForRecords(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            // If there is at least one line in the file, consider it as having records
            reader.close();
            return true;
        }
        reader.close();
        return false;
    }

    // Method to get all the occupied seatId
    private void saveOccupiedSeats() throws IOException {
        occupiedSeats = new ArrayList<>(selectedSeats);
        BufferedReader reader = new BufferedReader(new FileReader(file_path_from_src));
        String line;
        while ((line = reader.readLine()) != null) {
            // If there is at least one line in the file, consider it as having records
            String[] parts = line.split(",");;
            if (parts[1].equals("1")){ // occupied
                occupiedSeats.add(parts[0]); // seatID
            }
        }
        reader.close();
    }

    // Method to update the seat occupation after payment
    private void changeSeatsOccupation() throws IOException {
        saveOccupiedSeats();
        String[] seats = {"A","B","C","D"};
        String file_path_from_src = SelectSeatController.file_path_from_src;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file_path_from_src , false))) {
            for (int i = 1; i <= 4 ;i++){ // cabin
                int max;

                if (i == 1 || i == 4){ // head and tail
                    max = 8;
                }else{ // middle 1 and middle 2
                    max = 12;
                }

                for (int j = 1; j <= max; j++){ // seat num
                    for (int k = 0; k < 4; k++){ // seat character
                        String Id = "C" + String.valueOf(i) + String.valueOf(j) + seats[k];
                        if (occupiedSeats.contains(Id)){
                            bw.write( Id + "," + 1);
                        }else {
                            bw.write( Id + "," + 0);
                        }
                        bw.newLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

