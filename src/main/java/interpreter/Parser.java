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
        return tokens.get(currentPos + 1);
    }

    /**
     * Returns the next token, but also increments currentPos as well
     *
     * @return the token at currentPos after incrementing by 1
     */
    private Token advance() {
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

    public AstNode expression() {
        AstNode returnNode = factor();
        while(check(TokenType.PLUS) || check(TokenType.MINUS)) {
            returnNode =  new BinaryNode(returnNode, advance(), factor());
        }
        return returnNode;
    }

    private AstNode factor() {
        AstNode returnNode = power();
        while (check(TokenType.STAR) || check(TokenType.SLASH)) {
            returnNode = new BinaryNode(returnNode, advance(), power());
        }
        return returnNode;
    }

    private AstNode power() {
        AstNode returnNode = unary();
        while (check(TokenType.CARET)) {
            returnNode = new BinaryNode(returnNode, advance(), unary());
        }
        return returnNode;
    }

    private AstNode unary() {
        AstNode returnNode;
        if (check(TokenType.MINUS)) {
            returnNode = new UnaryNode(advance(), primary());
        }
        else {
            returnNode = primary();
        }
        return returnNode;
    }

    private AstNode primary() {
        AstNode returnNode = null;
        if (check(TokenType.L_PAREN)) {
            advance();
            returnNode = expression();
            if (!check(TokenType.R_PAREN)) {
                // This part needs to call a function for dealing with this error
                System.out.println("Error in primary()");
            }

        }
        else {
            Token nextToken = advance();
            if (nextToken instanceof NumToken) {
                returnNode = new NumNode((NumToken) nextToken);
            }
            else {
                // This part needs to call a function for dealing with this error
                System.out.println("Error in primary()");
            }

        }
        return returnNode;
    }






}
