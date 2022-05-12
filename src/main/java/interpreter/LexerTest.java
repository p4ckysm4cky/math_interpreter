package main.java.interpreter;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LexerTest {

    @Test
    public void testSingleTokens() {
        Lexer lex = new Lexer("(");
        lex.scanTokens();
        String expected = "[[L_PAREN]]";
        assertEquals(lex.toString(), expected);

        lex = new Lexer(")");
        lex.scanTokens();
        expected = "[[R_PAREN]]";
        assertEquals(lex.toString(), expected);

        lex = new Lexer("*");
        lex.scanTokens();
        expected = "[[STAR]]";
        assertEquals(lex.toString(), expected);

        lex = new Lexer("/");
        lex.scanTokens();
        expected = "[[SLASH]]";
        assertEquals(lex.toString(), expected);

        lex = new Lexer("+");
        lex.scanTokens();
        expected = "[[PLUS]]";
        assertEquals(lex.toString(), expected);

        lex = new Lexer("-");
        lex.scanTokens();
        expected = "[[MINUS]]";
        assertEquals(lex.toString(), expected);
    }

    @Test
    public void testUnknownTokens() {
        Lexer lex = new Lexer("{s;dlfkjsfldksjfdslkjfs");
        lex.scanTokens();
        String expected = "[]";
        assertEquals(lex.toString(), expected);
    }

    @Test
    public void testMultipleTokensWithError() {
        Lexer lex = new Lexer("(){");
        lex.scanTokens();
        String expected = "[[L_PAREN], [R_PAREN]]";
        assertEquals(lex.toString(), expected);
    }

    @Test
    public void allCharTokensTogether() {
        Lexer lex = new Lexer("()*/+-");
        lex.scanTokens();
        ArrayList<Token> expected = new ArrayList<Token>();
        expected.add(new Token(TokenType.L_PAREN, 1));
        expected.add(new Token(TokenType.R_PAREN, 1));
        expected.add(new Token(TokenType.STAR, 1));
        expected.add(new Token(TokenType.SLASH, 1));
        expected.add(new Token(TokenType.PLUS, 1));
        expected.add(new Token(TokenType.MINUS, 1));
        assertEquals(lex.toString(), expected.toString());
    }

    @Test
    public void allCharTokensTogetherWithUnknown() {
        Lexer lex = new Lexer("()c*b/}a;.,?a+d-fh{");
        lex.scanTokens();
        ArrayList<Token> expected = new ArrayList<Token>();
        expected.add(new Token(TokenType.L_PAREN, 1));
        expected.add(new Token(TokenType.R_PAREN, 1));
        expected.add(new Token(TokenType.STAR, 1));
        expected.add(new Token(TokenType.SLASH, 1));
        expected.add(new Token(TokenType.PLUS, 1));
        expected.add(new Token(TokenType.MINUS, 1));
        assertEquals(lex.toString(), expected.toString());
    }
}