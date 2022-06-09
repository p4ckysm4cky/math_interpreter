package main.java.interpreter;

import org.junit.Test;

import static org.junit.Assert.*;

// This Test class also tests NumNode and UnaryNode as well
public class BinaryNodeTest {

    @Test
    public void testNumNode() {
        NumToken tempNum = new NumToken(56);
        NumNode numA = new NumNode(tempNum);
        String expected = "56.0";
        assertEquals(numA.toString(), expected);

        tempNum = new NumToken(13.37);
        numA = new NumNode(tempNum);
        expected = "13.37";
        assertEquals(numA.toString(), expected);
    }

    @Test
    public void testUnaryNode() {
        NumToken tempNum = new NumToken(56);
        NumNode numA = new NumNode(tempNum);
        Token aToken = new Token(TokenType.MINUS);
        UnaryNode unary = new UnaryNode(aToken, numA);
        String expected = "([MINUS]56.0)";
        assertEquals(unary.toString(), expected);

    }

}