package org.example.train_homepage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class SelectSeatController {

    @FXML
    private Button btnBackward;

    @FXML
    private Button btnForward;

    @FXML
    private Label lblCabin;

    @FXML
    private Pane pane_head;

    @FXML
    private Pane pane_middle_1;

    @FXML
    private Pane pane_middle_2;

    @FXML
    private Button btnIcon;

    @FXML
    private Button btnPaymentProcess;

    @FXML
    private Pane pane_tail;

    public static ArrayList<String> selectedSeats;

    public static String file_path_from_src;

    // counter
    // default train_head
    int cabin = 1;

    public void initialize(){
        selectedSeats = new ArrayList<>();
        // Iterate through child nodes of the root Pane (buttons)
        for (javafx.scene.Node node : pane_head.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                // Add event listener to each button
                button.setId("C1" + button.getText());
                button.setOnAction(this::seatButtonOnClick);
            }
        }

        // Iterate through child nodes of the root Pane (buttons)
        for (javafx.scene.Node node : pane_middle_1.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                // Add event listener to each button
                button.setId("C2" + button.getText());
                button.setOnAction(this::seatButtonOnClick);
            }
        }

        for (javafx.scene.Node node : pane_middle_2.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                // Add event listener to each button
                button.setId("C3" + button.getText());
                button.setOnAction(this::seatButtonOnClick);
            }
        }

        for (javafx.scene.Node node : pane_tail.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                // Add event listener to each button
                button.setId("C4" + button.getText());
                button.setOnAction(this::seatButtonOnClick);
            }
        }

        // Delete during the project combination
        // Data generator :: Just for DEMO
        // dataGeneratorForTextFile();

        // Check the occupation when the program start
        // Change the color of button according to occupation
        String[] FromTo = Booking_Travel.currTicketType.split("_");

        // determine the From and assign suitable txt file
        switch (FromTo[0]){
            case "Taiping":
                file_path_from_src = "taiping.txt";
                break;
            case "Penang":
                file_path_from_src = "penang.txt";
                break;
            case "Singapore":
                file_path_from_src = "Singapore.txt";
                break;
            case "Padang Besar":
                file_path_from_src ="padangbesar.txt" ;
                break;
        }

        checkSeatOccupation(file_path_from_src);
    }

    // Method to handle the selection effect
    private void seatButtonOnClick(ActionEvent event) {
        // Handle button click event
        Button clickedButton = (Button) event.getSource();
        // Perform actions based on which button was clicked
        if (!clickedButton.getStyleClass().contains("occupied")){ // it is not an occupied seat

            // change the color for button
            if (!clickedButton.getStyleClass().contains("selected")){ // new select seat
                if (selectedSeats.size() < Booking_Travel.travellerNum){
                    clickedButton.getStyleClass().add("selected");
                    clickedButton.getStyleClass().remove("empty");
                    selectedSeats.add(clickedButton.getId());
                    if (selectedSeats.size() == Booking_Travel.travellerNum){
                        btnPaymentProcess.setDisable(false);
                    } else {
                        btnPaymentProcess.setDisable(true);
                    }
                }
            } else {
                clickedButton.getStyleClass().add("empty");
                clickedButton.getStyleClass().remove("selected");
                selectedSeats.remove(clickedButton.getId());

                // still have another selected button
                btnPaymentProcess.setDisable(true);
            }
        }
    }

    // Method to generate example data for text file
    private void dataGeneratorForTextFile(){
        String[] seats = {"A","B","C","D"};
        String file_path = "padangbesar.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file_path, false))) {
            for (int i = 1; i <= 4 ;i++){ // cabin
                int max;

                if (i == 1 || i == 4){ // head and tail
                    max = 8;
                }else{ // middle 1 and middle 2
                    max = 12;
                }

                for (int j = 1; j <= max; j++){ // seat num
                    for (int k = 0; k < 4; k++){ // seat character
                        bw.write("C" + String.valueOf(i) + String.valueOf(j) + seats[k] + "," + 0);
                        bw.newLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkSeatOccupation(String file_path){
        try (BufferedReader br = new BufferedReader(new FileReader(file_path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                char cabin_num = parts[0].charAt(1); // C [1] 1 A : check for the second one since it is cabin number

                System.out.println("Occupation is :" + parts[1]); /* Debug usage */

                Button button = getButtonObjectFromId(cabin_num, parts[0]);
                setButtonObjectStyle(parts[1], button);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 1 - occupied
    // 0 - empty
    // Method to handle the changing of button style class
    private void setButtonObjectStyle(String occupation_status, Button button){
        switch(occupation_status){
            case "0": // empty
                button.getStyleClass().add("empty");
                break;
            case "1": // occupied
                button.getStyleClass().add("occupied");
                break;
        }
    }

    // Method to get the button Object using button ID
    private Button getButtonObjectFromId(char cabin_num, String btnID){
        switch(cabin_num){
            case '1':
                for (javafx.scene.Node node : pane_head.getChildren()) {
                    if (node instanceof Button) {
                        if (node.getId().equals(btnID)){ // if the current loaded button have the target fx-id
                            return (Button) node; // return the Button
                        }
                    }
                }
                break;
            case '2':
                for (javafx.scene.Node node : pane_middle_1.getChildren()) {
                    if (node instanceof Button) {
                        if (node.getId().equals(btnID)){ // if the current loaded button have the target fx-id
                            return (Button) node; // return the Button
                        }
                    }
                }
                break;
            case '3':
                for (javafx.scene.Node node : pane_middle_2.getChildren()) {
                    if (node instanceof Button) {
                        if (node.getId().equals(btnID)){ // if the current loaded button have the target fx-id
                            return (Button) node; // return the Button
                        }
                    }
                }
                break;
            case '4':
                for (javafx.scene.Node node : pane_tail.getChildren()) {
                    if (node instanceof Button) {
                        if (node.getId().equals(btnID)){ // if the current loaded button have the target fx-id
                            return (Button) node; // return the Button
                        }
                    }
                }
                break;
            default:
                System.err.println("Error in Switch Case");
                break;
        }
        return null;
    }

    @FXML
    void moveBackward(ActionEvent event) {
        btnForward.setDisable(false); // enable the forward button

        // 4 is the most backward cabin
        if (cabin < 4){
            cabin++;
            btnBackward.setDisable(false); // enable the button
        }

        lblCabin.setText("Cabin " + cabin);

        switch(cabin){
            case 2:
                pane_head.setVisible(false);
                break;
            case 3:
                pane_middle_1.setVisible(false);
                break;
            case 4:
                pane_middle_2.setVisible(false);
                btnBackward.setDisable(true); // disable the backward button
                break;
        }
    }

    @FXML
    void moveForward(ActionEvent event) {
        btnBackward.setDisable(false); // enable the backward button

        // 1 is the most forward cabin
        if (cabin > 1){
            cabin--;
            btnForward.setDisable(false); // enable the button
        }

        lblCabin.setText("Cabin " + cabin);

        switch(cabin){
            case 1:
                pane_head.setVisible(true);
                btnForward.setDisable(true); // disable the forward button
                break;
            case 2:
                pane_middle_1.setVisible(true);
                break;
            case 3:
                pane_middle_2.setVisible(true);
                break;
        }
    }

    @FXML
    void onClickButtonPaymentProcess(ActionEvent event) throws IOException {
        // Convert ArrayList to array
        String[] array = selectedSeats.toArray(new String[0]);

        // Convert array to String
        String arrayAsString = Arrays.toString(array);

        // Print the String representation of the array
        System.err.println(arrayAsString);

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("payment.fxml")));
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
    void onClickBackToHome(ActionEvent event) {
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

}