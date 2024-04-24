package org.example.train_homepage;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.Objects;


public class Profile extends Login{
//    RW_4,test,C26A,Penang_Singapore,14:23,19.02,04h 39m,71
    public String referenceNum, seat,fromPlace,toPlace,fromTime,toTime,duration,price;
    public static String username;
    public String password;
    public String emailAddress;

    public String newUsername;
    public String newphoneNo;
    public String newEmail;

    public String phoneNo;
    @FXML
    private Button btnlogOut;

    @FXML
    private Label lblEmailAddress;

    @FXML
    private Label lblName;

    @FXML
    private Label lblPhoneNumber;
    @FXML
    private AnchorPane paneProfile;
    @FXML
    private Label lblTest;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtphoneNo;

    @FXML
    private Button btnEdit;
    @FXML
    private Button btnSave;


    private static final String user_file_pth = "User.txt";


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
    void switchHomePage(ActionEvent event) {
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
    private void makeFadeOut(Runnable action) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(500));
        fadeTransition.setNode(paneProfile);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(event -> action.run());
        fadeTransition.play();
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
    @FXML
    public void initialize() {

        username = HelloController.userName;
        password = HelloController.passWord;

        try (BufferedReader br = new BufferedReader(new FileReader(user_file_pth))) {
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length >= 2 && userData[0].equals(username) && userData[3].equals(password)) {
                    found = true;
                    username = userData[0];
                    emailAddress = userData[1];
                    phoneNo = userData[2];
                    break;
                }
            }
            if (found) {
                txtName.setText(username);
                txtEmail.setText(emailAddress);
                txtphoneNo.setText(phoneNo);
            } else {
                lblTest.setText("No user Found");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }


        paneProfile.setOpacity(0);

        // Create a FadeTransition with a duration of 1.5 seconds
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.0), paneProfile);
        fadeTransition.setFromValue(0);
        // Set the target opacity (1 for fully opaque)
        fadeTransition.setToValue(1);
//
        // Play the fade-in animation when the controller is initialized
        fadeTransition.play();
    }

    @FXML
    void editProfile(ActionEvent event) {
        txtName.setEditable(true);
        txtphoneNo.setEditable(true);
        txtEmail.setEditable(true);
        btnSave.setVisible(true);

    }


    @FXML
    void saveChanges(ActionEvent event) {
        newUsername = txtName.getText();
        newEmail = txtEmail.getText();
        newphoneNo = txtphoneNo.getText();

        try (BufferedReader br = new BufferedReader(new FileReader(user_file_pth));
             BufferedWriter bw = new BufferedWriter(new FileWriter("User_temp.txt"))) {

            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length >= 2 && userData[0].equals(username) && userData[3].equals(password)) {
                    found = true;
                    line = newUsername + "," + newEmail + "," + newphoneNo + "," + password;
                }
                bw.write(line);
                bw.newLine();
            }

            if (found) {
                lblTest.setText("Changes saved successfully.");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Edit Profile");
                alert.setHeaderText(null);
                alert.setContentText("Profile Save Changes Successfully");
                alert.getButtonTypes().setAll(ButtonType.OK);
                ButtonType result = alert.showAndWait().orElse(ButtonType.OK);

            } else {
                lblTest.setText("No user found.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            lblTest.setText("Error occurred while saving changes.");
        }

        //change in the ticket
        String ticket_file_pth = "ticketDetail.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(ticket_file_pth));
             BufferedWriter bw = new BufferedWriter(new FileWriter("ticketDetail_temp.txt"))) {

            String ticketLine;
            boolean found = false;
            while ((ticketLine = br.readLine()) != null) {
                String[] ticketData = ticketLine.split(",");
//                RW_4,test,C26A,Penang_Singapore,14:23,19.02,04h 39m,71
                if (ticketData.length >= 2 && ticketData[1].equals(username)) {
                    found = true;
                    referenceNum = ticketData[0];
                    seat = ticketData[2];
                    fromPlace = ((ticketData[3].split("_")[0]));
                    toPlace = ((ticketData[3].split("_")[1]));
                    fromTime = ticketData[4];
                    toTime = ticketData[5];
                    duration = ticketData[6];
                    price = ticketData[7];

                    ticketLine = referenceNum + "," + newUsername + "," + seat + "," + fromPlace + "_" + toPlace + "," + fromTime + "," + toTime + "," + duration + "," + price;
                }
                bw.write(ticketLine);
                bw.newLine();
            }

//            if (found) {
//                lblTest.setText("Changes saved successfully.");
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Edit Profile");
//                alert.setHeaderText(null);
//                alert.setContentText("Profile Save Changes Successfully");
//                alert.getButtonTypes().setAll(ButtonType.OK);
//                ButtonType result = alert.showAndWait().orElse(ButtonType.OK);
//
//            } else {
//                lblTest.setText("No user found.");
//            }
        } catch (Exception ex) {
            ex.printStackTrace();
            lblTest.setText("Error occurred while saving changes.");
        }
        username = newUsername;
        copyContent();

        btnSave.setVisible(false);
        txtName.setEditable(false);
        txtEmail.setEditable(false);
        txtphoneNo.setEditable(false);
    }
public static void copyContent() {
    try (BufferedReader br = new BufferedReader(new FileReader("User_temp.txt"));
         BufferedWriter bw = new BufferedWriter(new FileWriter(user_file_pth))) {

        String line;
        while ((line = br.readLine()) != null) {

            bw.write(line);
            System.out.println(line);
            bw.newLine();

        }

        System.out.println("File copied successfully.");
    } catch (IOException ex) {
        ex.printStackTrace();
        System.out.println("Error occurred while copying the file.");
    }
    String ticket_path = "ticketDetail.txt";
    try (BufferedReader br = new BufferedReader(new FileReader("ticketDetail_temp.txt"));
         BufferedWriter bw = new BufferedWriter(new FileWriter(ticket_path))) {

        String ticketline;
        while ((ticketline = br.readLine()) != null) {

            bw.write(ticketline);
            System.out.println(ticketline);
            bw.newLine();

        }

        System.out.println("File copied successfully.");
    } catch (IOException ex) {
        ex.printStackTrace();
        System.out.println("Error occurred while copying the file.");
    }
}
}

