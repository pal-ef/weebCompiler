package moe.weeb.weebcompilerultra;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class MainController {
    // Bindings
    @FXML
    private AnchorPane anchor;
    @FXML
    private TextArea textEditor;
    @FXML
    private TextArea lexerResult;

    // Lexer
    Lexer lexer = new Lexer();

    // UI Handling
    public void onAnchorClick() {
        // TODO There must be a better way to handle this
        anchor.requestFocus();
    };

    public void executeButtonOnClick() {
        startLexer();
    }

    // TODO Deal with file opening method
    public void fileOpen() {
        FileChooser chooser = new FileChooser();
        File selectedFile = chooser.showOpenDialog(lexerResult.getContextMenu());
    }

    public void startLexer() {
        lexerResult.setText("");
        String input = textEditor.getText();
        if(!input.isEmpty()) {
            lexer.analyze(input);
            ArrayList<String> tokens = lexer.retrieveTokens();
            for(String s : tokens) lexerResult.appendText(s+"\n");
        } else {
            // TODO Change this for a proper window popup
            textEditor.setPromptText("You should input some text to analyze...");
        }
    }
}