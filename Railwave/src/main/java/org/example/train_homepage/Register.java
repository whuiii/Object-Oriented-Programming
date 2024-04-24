package org.example.train_homepage;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.EventObject;
import java.util.Objects;
import java.util.regex.Pattern;

public class Register {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label ConfirmText;

    @FXML
    private Label SuccessfulText;

    @FXML
    private Label alertText;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnclear;

    @FXML
    private PasswordField confirmpasstxt;

    @FXML
    private TextField emailtxt;

    @FXML
    private PasswordField passtxt;

    @FXML
    private TextField phonetxt;

    @FXML
    private AnchorPane signUpPane;

    @FXML
    private TextField usertxt;

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String PHONE_PATTERN = "^[0-9]{10,11}$";

    private boolean validateEmail(String email) {
        return Pattern.matches(EMAIL_PATTERN, email);
    }

    private boolean validatePhone(String phone) {
        return Pattern.matches(PHONE_PATTERN, phone);
    }

    @FXML
    void Register(ActionEvent event) {
        String username = usertxt.getText();
        String email = emailtxt.getText();
        String phone = phonetxt.getText();
        String password = passtxt.getText();
        String confirmPassword = confirmpasstxt.getText();

        if (!password.equals(confirmPassword)) {
            ConfirmText.setText("Password does not match!");
            return;
        }

        if (username.isBlank() || email.isBlank() || phone.isBlank() || password.isBlank()) {
            alertText.setText("Please fill in all fields!");
            return;
        }

        if (!validateEmail(email)) {
            alertText.setText("Invalid email format");
            return;
        }

        if (!validatePhone(phone)) {
            alertText.setText("Invalid phone number format! (10-11 digits)");
            return;
        }

        File file = new File("User.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(username + "," + email + "," + phone + "," + password);
            bw.newLine();
            SuccessfulText.setText("Registration Successful!");
            alertText.setText("");
            showAlert("Registration Successful", "Hello Railwave", Alert.AlertType.INFORMATION);

            makeFadeOut(() -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
                    root = loader.load();
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            SuccessfulText.setText("Registration Failed: Unable to write to file.");
        }
    }

    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void makeFadeOut(Runnable action) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(500));
        fadeTransition.setNode(signUpPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(event -> action.run()); // Execute the action after fade-out
        fadeTransition.play();
    }
    @FXML
    void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-view.fxml")));
        Node sourceNode = (Node) event.getSource();
        stage = (Stage) sourceNode.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnBackClicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        Parent root = loader.load();
        Main controller = loader.getController();
        //controller.restoreState(selectedFrom, selectedTo, selectedDate, counter);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void clear(ActionEvent event) {
        usertxt.setText("");
        emailtxt.setText("");
        phonetxt.setText("");
        passtxt.setText("");
        confirmpasstxt.setText("");
        SuccessfulText.setText("");
        ConfirmText.setText("");
        alertText.setText("");
    }

    @FXML
    public void initialize() {
        signUpPane.setOpacity(0);
        makeFadeInTransition();
    }

    private void makeFadeInTransition() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(500));
        fadeTransition.setNode(signUpPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);

        fadeTransition.play();
    }
}
