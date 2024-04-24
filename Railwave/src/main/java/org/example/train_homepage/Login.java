package org.example.train_homepage;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class Login {
    public String username;
    public String password;
    private Stage stage;
    private Scene scene;
    private Parent root;

    // global variable
    public static String currUsername;

    @FXML
    private Label alertText;

    @FXML
    private ImageView btnBack1;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnclaer;

    @FXML
    private Button btnforgotpass;

    @FXML
    private Button btnlogin;

    @FXML
    private Button btnmain;

    @FXML
    private Label invalidText;

    @FXML
    private BorderPane loginPane;

    @FXML
    private PasswordField passTxt;

    @FXML
    private TextField userTxt;
    @FXML
    public void initialize() {
        loginPane.setOpacity(0);
        makeFadeInTransition();
    }

    @FXML
    public void login(ActionEvent e) {
        username = userTxt.getText();
        password = passTxt.getText();

        if (!username.isBlank() && !password.isBlank()) {
            try (BufferedReader br = new BufferedReader(new FileReader("User.txt"))) {
                String line;
                boolean found = false;
                while ((line = br.readLine()) != null) {
                    String[] userData = line.split(",");
                    if (userData.length >= 4 && userData[0].equals(username) && userData[3].equals(password)) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    showAlert("Login Successful", "Welcome to Railwave", Alert.AlertType.INFORMATION);

                    makeFadeOut(() -> {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
                            root = loader.load();

                            HelloController homeController = loader.getController();
                            homeController.passName(username, password);

                            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                } else {
                    showAlert("Login Error", "Invalid username or password!", Alert.AlertType.ERROR);
                }
            } catch (FileNotFoundException ex) {
                showAlert("File Error", "User.txt not found.", Alert.AlertType.ERROR);
            } catch (IOException ex) {
                showAlert("IO Error", "An error occurred while reading User.txt.", Alert.AlertType.ERROR);
            }
        } else {
            showAlert("Validation Error", "Please enter username and password!", Alert.AlertType.WARNING);
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
        fadeTransition.setNode(loginPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(event -> action.run()); // Execute the action after fade-out
        fadeTransition.play();
    }

    public void register(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("register-view.fxml")));
        Node sourceNode = (Node) e.getSource();
        stage = (Stage) sourceNode.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void clear() {
        userTxt.setText("");
        passTxt.setText("");
        invalidText.setText("");
        alertText.setText("");
    }

    public void forgotPass(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("forgotpassword-view.fxml")));
        Node sourceNode = (Node) e.getSource();
        stage = (Stage) sourceNode.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void main(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));
        Node sourceNode = (Node) e.getSource();
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

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void makeFadeInTransition() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(500));
        fadeTransition.setNode(loginPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);

        fadeTransition.play();
    }
}
