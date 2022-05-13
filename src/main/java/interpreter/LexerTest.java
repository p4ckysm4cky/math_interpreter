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

    @Test
    public void basicInteger() {
        Lexer lex = new Lexer("5");
        lex.scanTokens();
        String expected = "[[NUMBER:5.0]]";
        assertEquals(expected, lex.toString());

        lex = new Lexer("123");
        lex.scanTokens();
        expected = "[[NUMBER:123.0]]";
        assertEquals(expected, lex.toString());

        lex = new Lexer("0");
        lex.scanTokens();
        expected = "[[NUMBER:0.0]]";
        assertEquals(expected, lex.toString());

        lex = new Lexer("9");
        lex.scanTokens();
        expected = "[[NUMBER:9.0]]";
        assertEquals(expected, lex.toString());

        lex = new Lexer("1234567");
        lex.scanTokens();
        expected = "[[NUMBER:1234567.0]]";
        assertEquals(expected, lex.toString());
    }

    @Test
    public void basicFloat() {
        Lexer lex = new Lexer("42.0");
        lex.scanTokens();
        String expected = "[[NUMBER:42.0]]";
        assertEquals(expected, lex.toString());

        lex = new Lexer("12.3");
        lex.scanTokens();
        expected = "[[NUMBER:12.3]]";
        assertEquals(expected, lex.toString());

        lex = new Lexer("99.99");
        lex.scanTokens();
        expected = "[[NUMBER:99.99]]";
        assertEquals(expected, lex.toString());

        lex = new Lexer("000.43689");
        lex.scanTokens();
        expected = "[[NUMBER:0.43689]]";
        assertEquals(expected, lex.toString());

        lex = new Lexer("0103.43689");
        lex.scanTokens();
        expected = "[[NUMBER:103.43689]]";
        assertEquals(expected, lex.toString());
    }

    @Test
    public void testInvalidNums() {
        Lexer lex = new Lexer("42..0");
        lex.scanTokens();
        String expected = "[[NUMBER:42.0], [NUMBER:0.0]]";
        assertEquals(expected, lex.toString());

        lex = new Lexer(".123");
        lex.scanTokens();
        expected = "[[NUMBER:123.0]]";
        assertEquals(expected, lex.toString());

        lex = new Lexer("1337.");
        lex.scanTokens();
        expected = "[[NUMBER:1337.0]]";
        assertEquals(expected, lex.toString());

        lex = new Lexer("420.\\3");
        lex.scanTokens();
        expected = "[[NUMBER:420.0], [NUMBER:3.0]]";
        assertEquals(expected, lex.toString());
    }

    // Need to add further test cases which is basically an entire expression
}