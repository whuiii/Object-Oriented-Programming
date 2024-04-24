module org.example.train_homepage {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.train_homepage to javafx.fxml;
    exports org.example.train_homepage;
}