package main.java.interpreter;
import java.util.ArrayList;

public class Parser {
    private final ArrayList<Token> tokens;
    private final int tokenSize;
    private int currentPos = -1;

    Parser(ArrayList<Token> tokens) {
        // We need this to know when we've reached the end of the tokens
        tokens.add(new Token(TokenType.EOF));
        tokenSize = tokens.size();
        this.tokens = tokens;
    }

    /**
     * Checks if we've reached the end of our tokens
     *
     * @return returns true when nextPos will be EOF
     */
    private boolean isAtEnd() {
        return (tokens.get(currentPos + 1).getTokenType() == TokenType.EOF);
    }

    /**
     * Returns the next token
     * Note: does not check if we are at the end or not, so you might need
     * to use isAtEnd() as well when using
     *
     * @return the token at currentPos + 1
     */
    private Token peek() {
        currentPos++;
        return tokens.get(currentPos);
    }


    /**
     * Checks the next token with the type required
     *
     *
     * @param type This is the type that we are expecting
     * @return boolean status indicating if the next token is of the same type as the argument provided
     */
    private boolean check(TokenType type) {
        // Make sure the next token is not EOF
        if (isAtEnd())
            return false;
        return (peek().getTokenType() == type);
    }








}
