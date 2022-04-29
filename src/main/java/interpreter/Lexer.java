package main.java.interpreter;

import java.util.ArrayList;

public class Lexer {
    private final String text;
    private int currentPos = -1;
    private final ArrayList<Token> tokens = new ArrayList<Token>();

    Lexer(String text) {
        this.text = text;
    }

    /**
     * Determines if our currentPos has reached the end of text yet
     *
     * @return true if we have reached end, otherwise false
     */
    private boolean isAtEnd() {
        return (currentPos >= text.length());
    }

    /**
     * Returns the next character of text by incrementing currentPos
     *
     * @return next character in text
     */
    private char advance() {
        currentPos++;
        char currentChar = text.charAt(currentPos);
        return currentChar;
    }

    /**
     * Returns the next character in text
     * without incrementing currentPos
     *
     * @return next character in text, unless EOF then nul char
     */
    private char peek() {
        if (isAtEnd()) {
            return '\0';
        }
        return text.charAt(currentPos+1);
    }

    /**
     * Quickly generates a token, but cannot be used to make NumToken
     *
     * @param type of token found from the lexer
     */
    private void addToken(TokenType type) {
        Token newToken = new Token(type, currentPos);
        tokens.add(newToken);
    }

}
