package main.java.interpreter;

import java.util.ArrayList;
import java.lang.StringBuilder;

public class Lexer {
    private final String text;
    private int currentPos = -1;
    private final ArrayList<Token> tokens = new ArrayList<Token>();
    private final ArrayList<Integer> errorPos = new ArrayList<Integer>();

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
            case '^':
                addToken(TokenType.CARET);
                break;
            // ignore whitespaces
            case ' ':
            case '\t':
            case '\n':
                break;
            default:
                // Check if number
                if (isDigit(c)) {
                    genNumber();
                }
                // We add the position of the unknown token to errorPos
                else {
                    errorPos.add(currentPos);
                }
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
     * Returns the character of currentPos + offset
     * without incrementing currentPos
     *
     * @param offset how many characters to the right
     * @return character at the offset position
     */
    private char peek(int offset) {
        if (currentPos + offset >= text.length()) {
            return '\0';
        }
        return text.charAt(currentPos + offset);
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

    /**
     * Checks if the character passed in a number
     *
     * @param c character passed in
     * @return true if number, otherwise false
     */
    public boolean isDigit(char c) {
        return (c >= '0' && c <= '9');
    }

    /**
     * Checks if the next position is at a DOT, if it's at a dot
     * we need to make sure there's numbers after it
     *
     * @return true if current is pointing at '.' and next is a digit
     */
    private boolean validFloat() {
        char nextChar = peek();
        return nextChar == '.' && isDigit(peek(2));
    }

    /**
     * Check if our lexer found any errors after running scanToken
     *
     * @return true if error found, otherwise false
     */
    public boolean containErrors() {
        return (errorPos.size() != 0);
    }

    /**
     * Adds NumToken to this.token
     */
    public void genNumber() {
        // This is our flag to make sure we only see the decimal point once
        boolean dotNotSeen = true;
        StringBuilder strNum = new StringBuilder(text.charAt(currentPos) + "");
        while (isDigit(peek()) || (validFloat()) && dotNotSeen) {
            char currentChar = advance();
            if (currentChar == '.') {
                dotNotSeen = false;
            }
            strNum.append(currentChar);
        }
        tokens.add(new NumToken(Double.parseDouble(strNum.toString()), currentPos));
    }

    /**
     * Returns string of unknown tokens found
     *
     * @return a string of errors, otherwise it will return a blank string
     */
    public String errorLog() {
        if (containErrors()) {
            String returnStr = text + "\n";
            // make next line fill of whitespaces
            StringBuilder secondLine = new StringBuilder();
            for (int i = 0; i < returnStr.length(); i++) {
                secondLine.append(" ");
            }
            // change the positions found in errorPos to '^'
            for (int pos : errorPos) {
                secondLine.setCharAt(pos, '^');
            }
            secondLine.append("\n");
            returnStr += secondLine.toString();
            returnStr += "Invalid token(s) found\n";
            return returnStr;
        }
        else {
            return "";
        }
    }

}
