package main.java.interpreter;

import java.util.ArrayList;

public class Lexer {
    private final String text;
    private int currentPos = -1;
    private final  ArrayList<Token> tokens = new ArrayList<Token>();

    Lexer(String text) {
        this.text = text;
    }

    /**
     * Determines if our currentPos has reached the end of text yet
     * @return true if we have reached end, otherwise false
     */
    private boolean isAtEnd() {
        return (currentPos >= text.length());
    }

    /**
     * Returns the current character and increments currentPos forward
     * @return char at currentPos of text
     */
    private char advance() {
        currentPos++;
        char currentChar = text.charAt(currentPos);
        return currentChar;
    }

    /**
     * Quickly generates a token, but cannot be used to make NumToken
     * @param type of token found from the lexer
     */
    private void addToken(TokenType type) {
        Token newToken = new Token(type, currentPos);
        tokens.add(newToken);
    }

}
