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

    @Test
    public void testBinaryNode() {
        NumToken a = new NumToken(56.12);
        NumToken b = new NumToken(34.34);
        NumNode a_node = new NumNode(a);
        NumNode b_node = new NumNode(b);
        Token plus = new Token(TokenType.PLUS);
        BinaryNode c = new BinaryNode(a_node, plus, b_node);
        System.out.println(c);
        String expected = "(56.12[PLUS]34.34)";
        assertEquals(c.toString(), expected);

    }

    @Test
    public void testCombo() {
        NumToken a = new NumToken(56.12);
        NumNode a_node = new NumNode(a);
        UnaryNode b_unary = new UnaryNode(new Token(TokenType.MINUS), new NumNode(new NumToken(89.23)));
        Token multiply = new Token(TokenType.STAR);
        BinaryNode c = new BinaryNode(a_node, multiply, b_unary);
        System.out.println(c);
        String expected = "(56.12[STAR]([MINUS]89.23))";
        assertEquals(c.toString(), expected);

        BinaryNode lhs = new BinaryNode(new NumNode(new NumToken(32,0)), new Token(TokenType.PLUS), new NumNode(new NumToken(10)));
        BinaryNode rhs = c;
        BinaryNode resultNode = new BinaryNode(lhs, new Token(TokenType.MINUS) ,rhs);
        System.out.println(resultNode);
        expected = "((32.0[PLUS]10.0)[MINUS](56.12[STAR]([MINUS]89.23)))";
        assertEquals(resultNode.toString(), expected);
    }

}