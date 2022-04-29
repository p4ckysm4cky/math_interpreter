package main.java.interpreter;

/**
 * A token produced from a lexer
 */
public class Token {
    final TokenType type;
    // value represents what it looked line in input stream
    // potentially useful for error printing
    final int col;

    Token(TokenType type, int col) {
        this.type = type;
        this.col = col;
    }

    public TokenType getTokenType() {
        return this.type;
    }

    public int getCol() {
        return this.col;
    }

    @Override
    public String toString() {
        String returnStr = "[" + type + "]";
        return returnStr;
    }
}
