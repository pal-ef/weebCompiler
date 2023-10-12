module moe.weeb.weebcompilerultra {
    requires javafx.controls;
    requires javafx.fxml;


    opens moe.weeb.weebcompilerultra to javafx.fxml;
    exports moe.weeb.weebcompilerultra;
}