package moe.weeb.weebcompilerultra;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**Lexer takes a String and retrieves all the valid symbols
 * for invalid symbols it returns an invalid symbol */
public class Lexer {
    String input;
    HashMap<String, String> dictionary = new HashMap<String, String>();
    ArrayList<String> tokens;
    String specialToken;

    Lexer() {
        tokens = new ArrayList<String>();
        populateDictionary();
        //verifyDictionaryContent();
    }

    private void cleanToken() {
        this.tokens = new ArrayList<String>();
    }

    private void populateDictionary() {
        // Read every line from the rules.miku and add it local dictionary
        // TODO Change this to a dynamic path
        File f = new File("/Users/egpalaci/Documents/java/WeebCompilerUltra/src/main/resources/moe/weeb/weebcompilerultra/rules.miku");
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while((line = br.readLine()) != null) {
                // Skip commented lines and blank lines
                if(line.isBlank() || line.startsWith("#")) continue;
                // Split line by ':' character
                String[] parts = line.split(":");
                // Add to local dictionary keys and values
                dictionary.put(parts[0], parts[1]);
            }
            System.out.println("Rules loaded! (ﾉ◕ヮ◕)ﾉ*:･ﾟ✧");
        } catch (IOException e) {
            System.out.println("FATAL ERROR: RULES NOT FOUND");
            throw new RuntimeException(e);
        }
    }
    private static boolean isValidIdentifier(String s) {
        String regexPattern = "^[a-zA-Z_$][a-zA-Z_$0-9]*$";
        return Pattern.compile(regexPattern).matcher(s).matches();
    }

    private boolean isSpecialChar(char c) {
        switch (c) {
            case ' ' -> {
                specialToken = "";
                return true;
            }
            case ';' -> {
                specialToken = ";";
                return true;
            }
            case '=' -> {
                specialToken = "assignment: =";
                return true;
            }
        }
        specialToken = "";
        return false;
    }

    private boolean isInteger(String str) {
        return str.matches("-?\\d+");
    }

    private static boolean isFloat(String str) {
        return  str.matches("[+-]?([0-9]*[.])?[0-9]+");
    }

    /**Takes in String and verifies given string is a valid symbol
     * if true it  inserts a validToken
     * if false it inserts an invalidToken */
    private void isValidSymbol(String symbol) {
        if(symbol.isBlank()) return;
        // Determine if symbol is inside rules
        if(dictionary.containsKey(symbol)) {
            // If so add corresponding token to Tokens
            tokens.add(dictionary.get(symbol) + ": " + symbol);
            return;
        }
        // Determine if symbol is an adequate identifier
        if(isValidIdentifier(symbol)) {
            tokens.add("identifier: " + symbol);
            return;
        }
        // Determine if symbol is a integer or float
        if(isInteger(symbol)) {
            tokens.add("integer: " + symbol);
            return;
        };
        if(isFloat(symbol)) {
            tokens.add("float: " + symbol);
            return;
        }
        // Invalid token, thus inserting a invalidToken
        tokens.add(String.format("UnidentifiedToken: %s", symbol));
    }

    public void verifyDictionaryContent() {
        // For debugging purposes
        for(Map.Entry<String, String> entry : dictionary.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.printf("KEY: %s VALUE: %s %n", key, value);
        }
    }

    /**Returns tokens obtained thus far */
    public ArrayList<String> retrieveTokens() {
        return this.tokens;
    };

    /** analyze takes a String input and returns all the valid symbols */
    public void analyze(String input) {
        // Ensure that tokens is clear
        cleanToken();

        // Read character by character and determine if is valid symbol
        StringBuilder currentString = new StringBuilder();
        char current;

        for (int i = 0; i < input.length(); i++) {
            current = input.charAt(i);
            currentString.append(current);

            // If special char is detected send symbol to evaluation
            if (isSpecialChar(current)) {
                // Send to evaluation the currentString minus 1
                String symbol = currentString.toString();
                isValidSymbol(symbol.substring(0, symbol.length()-1).trim());
                if(!specialToken.isBlank()) tokens.add(specialToken);
                currentString = new StringBuilder();
            }
        }
        // In case that input string had no special chars nor whitespaces
        if(!currentString.isEmpty()) {
            String symbol = currentString.toString();
            isValidSymbol(symbol.trim());
        }
    };
}
