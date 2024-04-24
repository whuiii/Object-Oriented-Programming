package org.example.train_homepage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.*;
import java.util.Objects;

public class ForgotPassword {
    @FXML
    private Button btnback;

    @FXML
    private Button btncheck;

    @FXML
    private TextField emailtxt;


    @FXML
    void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-view.fxml")));
        Node sourceNode = (Node) event.getSource();
        Stage stage = (Stage) sourceNode.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private String getPasswordForEmail(String email) {
        // Open the user text file and search for the email
        File file = new File("src/main/resources/org/example/train_homepage/textfile/User.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length > 1 && userData[1].equals(email)) {
                    // Email found, return the corresponding password
                    return userData[3]; // Assuming password is at index 3
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Email not found
        return null;
    }
    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void resetPassword(String email) {
        // Prompt user to enter new password
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Reset Password");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter your new password:");

        // Show dialog and get user input
        dialog.showAndWait().ifPresent(newPassword -> {
            // Update password in user text file
            updatePasswordForEmail(email, newPassword);
        });
    }
    private void updatePasswordForEmail(String email, String newPassword) {
        // Open the user text file and search for the email
        File file = new File("src/main/resources/org/example/train_homepage/textfile/User.txt");
        StringBuilder fileContent = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length > 1 && userData[1].equals(email)) {
                    // Email found, update the password
                    userData[3] = newPassword; // Assuming password is at index 3
                    line = String.join(",", userData);
                }
                fileContent.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write updated content back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(fileContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        showAlert(Alert.AlertType.INFORMATION, "Password reset successful!");
    }

    @FXML
    void check(ActionEvent event) {
        String email = emailtxt.getText().trim(); // Get the entered email address

        if (email.isBlank()) {
            showAlert(Alert.AlertType.WARNING, "Please fill up the email field!!");
            return;
        }

        // Check if email exists in the user text file
        String password = getPasswordForEmail(email);
        if (password != null) {
            // Email exists, prompt user to reset password
            resetPassword(email);
        } else {
            showAlert(Alert.AlertType.WARNING, "Email not found in records!");
        }

    }
}
