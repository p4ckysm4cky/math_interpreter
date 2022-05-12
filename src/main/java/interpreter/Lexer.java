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
     * Scans String source, and returns all the tokens from source
     *
     * @return ArrayList of Token found from source
     */
    public ArrayList<Token> scanTokens() {
        while (!isAtEnd()) {
            scanToken();
        }
        return this.tokens;
    }

    private void scanToken() {
        char c = advance();
        // DOT is not included, because it is only found in numbers
        switch (c) {
            case '(':
                addToken(TokenType.L_PAREN);
                break;
            case ')':
                addToken(TokenType.R_PAREN);
                break;
            case '*':
                addToken(TokenType.STAR);
                break;
            case '/':
                addToken(TokenType.SLASH);
                break;
            case '+':
                addToken(TokenType.PLUS);
                break;
            case '-':
                addToken(TokenType.MINUS);
                break;
            default:
                // this part is incomplete should be used for numbers and error handling
                System.out.println("Error in scanToken");
        }
    }

    /**
     * Determines if our currentPos has reached the end of text yet
     *
     * @return true if we have reached end, otherwise false
     */
    private boolean isAtEnd() {
        return (currentPos >= text.length() - 1);
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
        return text.charAt(currentPos + 1);
    }

    /**
     * Quickly generates a token, and adds it to
     * the token instance variable
     *
     * @param type of token found from the lexer
     */
    private void addToken(TokenType type) {
        Token newToken = new Token(type, currentPos);
        this.tokens.add(newToken);
    }

    public String toString() {
        return tokens.toString();
    }

}
