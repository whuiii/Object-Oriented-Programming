package org.example.train_homepage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketObj {
    @FXML
    private ImageView btnDelete;

    @FXML
    private Button btnDeleteBooking;

    @FXML
    private Label lblArrive;

    @FXML
    private Label lblDepart;

    @FXML
    private Label lblDuration;

    @FXML
    private Label lblFrom;

    @FXML
    private Label lblName;

    @FXML
    private Label lblPrice;

    @FXML
    private Label lblSeatID;

    @FXML
    private Label lblTicketID;

    @FXML
    private Label lblTo;

    @FXML
    private Pane paneDetail;

    @FXML
    private Pane panePrice;

    @FXML
    private Pane rootPane;

    @FXML
    private Line ticketLine;

    public void setLblArrive(String arrivedTime) {
        lblArrive.setText(arrivedTime);
    }

    public void setLblDepart(String departTime) {
        lblDepart.setText(departTime);
    }

    public void setLblDuration(String duration) {
        lblDuration.setText(duration);
    }

    public void setLblFrom(String fromLocation) {
        lblFrom.setText(fromLocation);
    }

    public void setLblTo(String toLocation) {
        lblTo.setText(toLocation);
    }

    public void setLblName(String username) {
        lblName.setText(username);
    }

    public void setLblPrice(String price) {
        lblPrice.setText(price);
    }

    public void setLblSeatID(String seatID) {
        lblSeatID.setText(seatID);
    }

    public void setLblTicketID(String ticketID) {
        lblTicketID.setText(ticketID);
    }

    private static final String TICKET_DETAIL_FILE_PATH = "ticketDetail.txt";

    @FXML
    private void handleDeleteBooking(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Booking");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to proceed with the deletion?\n" +
                "Please note that no refund will be provided.");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            // If the Yes button was clicked
            String ticketID = lblTicketID.getText(); // Get the ticket ID to be deleted
            try {
                deleteTicketFromFile(ticketID);
                // Remove this ticket from the UI or perform any other action
                rootPane.getChildren().clear(); // Clear the UI
            } catch (IOException e) {
                e.printStackTrace();
                // Handle file operation errors
            }
        }
    }
    private void deleteTicketFromFile(String ticketID) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(TICKET_DETAIL_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 8 && parts[0].equals(ticketID)) {
                    continue; // Skip this line (don't add to the list) if it matches the ticket ID to be deleted
                }
                lines.add(line); // Add the line to the list
            }
        }
        // Write the updated list back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TICKET_DETAIL_FILE_PATH))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}
