package main.java.interpreter;

/**
 * A token produced from a lexer
 */
public class Token {
    final TokenType type;
    // value represents what it looked line in input stream
    final String value;
    // potentially useful for error printing
    final int col;

    Token(TokenType type, String value, int col) {
        this.type = type;
        this.value = value;
        this.col = col;
    }

    @Override
    public String toString() {
        String returnStr = type + ":" + value;
        return returnStr;
    }
}
