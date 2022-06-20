package main.java.interpreter;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParserTest {

    /**
     * Used to generate expression quickly when given a string
     *
     * @param userInput mathematical expression
     * @return parsed expression
     */
    public AstNode genExpression(String userInput) {
        Lexer lexer = new Lexer(userInput);
        Parser parser = new Parser(lexer.scanTokens());
        return parser.expression();
    }

    @Test
    public void testAddition() {
        AstNode expression = genExpression("1+2");
        System.out.println(expression);
        String expected = "(1.0[PLUS]2.0)";
        assertEquals(expected, expression.toString());

        expression = genExpression("1 + 2 + 3");
        System.out.println(expression);
        expected = "((1.0[PLUS]2.0)[PLUS]3.0)";
        assertEquals(expected, expression.toString());

        expression = genExpression("1.23 + 34 + 54 + 23.5712332");
        System.out.println(expression);
        expected = "(((1.23[PLUS]34.0)[PLUS]54.0)[PLUS]23.5712332)";
        assertEquals(expected, expression.toString());

    }

    @Test
    public void testSubtraction() {
        AstNode expression = genExpression("34 - 10.9");
        System.out.println(expression);
        String expected = "(34.0[MINUS]10.9)";
        assertEquals(expected, expression.toString());

        expression = genExpression("1 - 2 - 3");
        System.out.println(expression);
        expected = "((1.0[MINUS]2.0)[MINUS]3.0)";
        assertEquals(expected, expression.toString());

    }

    @Test
    public void testAddSubtract() {
        AstNode expression = genExpression("34 - 10.9 + 69 - 420 - 21314.39593");
        System.out.println(expression);
        String expected = "((((34.0[MINUS]10.9)[PLUS]69.0)[MINUS]420.0)[MINUS]21314.39593)";
        assertEquals(expected, expression.toString());

        expression = genExpression("3-45+34+656-234");
        System.out.println(expression);
        expected = "((((3.0[MINUS]45.0)[PLUS]34.0)[PLUS]656.0)[MINUS]234.0)";
        assertEquals(expected, expression.toString());
    }

    @Test
    public void testUnaryAndPrevious() {
        AstNode expression = genExpression("-34.96");
        System.out.println(expression);
        String expected = "([MINUS]34.96)";
        assertEquals(expected, expression.toString());

        expression = genExpression("58 + -34.96");
        System.out.println(expression);
        expected = "(58.0[PLUS]([MINUS]34.96))";
        assertEquals(expected, expression.toString());

        expression = genExpression("58 + -34.96 - 44.93");
        System.out.println(expression);
        expected = "((58.0[PLUS]([MINUS]34.96))[MINUS]44.93)";
        assertEquals(expected, expression.toString());

        expression = genExpression("-34 - -22.96");
        System.out.println(expression);
        expected = "(([MINUS]34.0)[MINUS]([MINUS]22.96))";
        assertEquals(expected, expression.toString());

        expression = genExpression("65 - 5 - 5 - -12302 + -9");
        System.out.println(expression);
        expected = "((((65.0[MINUS]5.0)[MINUS]5.0)[MINUS]([MINUS]12302.0))[PLUS]([MINUS]9.0))";
        assertEquals(expected, expression.toString());
    }

    @Test
    public void testFactorAndPrevious() {
        AstNode expression = genExpression("5 * 10");
        System.out.println(expression);
        String expected = "(5.0[STAR]10.0)";
        assertEquals(expected, expression.toString());

        expression = genExpression("5 + 3 * 9");
        System.out.println(expression);
        expected = "(5.0[PLUS](3.0[STAR]9.0))";
        assertEquals(expected, expression.toString());

        expression = genExpression("5 + 3 * -9");
        System.out.println(expression);
        expected = "(5.0[PLUS](3.0[STAR]([MINUS]9.0)))";
        assertEquals(expected, expression.toString());

        expression = genExpression("-32 * -5 + 3 * -9");
        System.out.println(expression);
        expected = "((([MINUS]32.0)[STAR]([MINUS]5.0))[PLUS](3.0[STAR]([MINUS]9.0)))";
        assertEquals(expected, expression.toString());

        expression = genExpression("32 + 45 * 34 + 56 - 32 / 56 / 54");
        System.out.println(expression);
        expected = "(((32.0[PLUS](45.0[STAR]34.0))[PLUS]56.0)[MINUS]((32.0[SLASH]56.0)[SLASH]54.0))";
        assertEquals(expected, expression.toString());
    }

    @Test
    public void testPowerAndPrevious() {
        AstNode expression = genExpression("5 ^ 10");
        System.out.println(expression);
        String expected = "(5.0[CARET]10.0)";
        assertEquals(expected, expression.toString());

        expression = genExpression("5 ^ 3 * 9");
        System.out.println(expression);
        expected = "((5.0[CARET]3.0)[STAR]9.0)";
        assertEquals(expected, expression.toString());

        expression = genExpression("45 * 5 ^ 3 * 9");
        System.out.println(expression);
        expected = "((45.0[STAR](5.0[CARET]3.0))[STAR]9.0)";
        assertEquals(expected, expression.toString());

        expression = genExpression("5^ -45");
        System.out.println(expression);
        expected = "(5.0[CARET]([MINUS]45.0))";
        assertEquals(expected, expression.toString());
    }

    @Test
    public void testPrimaryAndPrevious() {
        AstNode expression = genExpression("32");
        System.out.println(expression);
        String expected = "32.0";
        assertEquals(expected, expression.toString());

        expression = genExpression("(45.32)");
        System.out.println(expression);
        expected = "45.32";
        assertEquals(expected, expression.toString());

        expression = genExpression("(32 + 43) * 56");
        System.out.println(expression);
        expected = "((32.0[PLUS]43.0)[STAR]56.0)";
        assertEquals(expected, expression.toString());

        expression = genExpression("(-12) * -56");
        System.out.println(expression);
        expected = "(([MINUS]12.0)[STAR]([MINUS]56.0))";
        assertEquals(expected, expression.toString());

        expression = genExpression("(((-12))) * -56");
        System.out.println(expression);
        expected = "(([MINUS]12.0)[STAR]([MINUS]56.0))";
        assertEquals(expected, expression.toString());

        expression = genExpression("7 * (5+15) / (2*5)-3");
        System.out.println(expression);
        expected = "(((7.0[STAR](5.0[PLUS]15.0))[SLASH](2.0[STAR]5.0))[MINUS]3.0)";
        assertEquals(expected, expression.toString());

        expression = genExpression("32 ^ (3+56-32) * 43 / (32.32 - 3434224.34343)");
        System.out.println(expression);
        expected = "(((32.0[CARET]((3.0[PLUS]56.0)[MINUS]32.0))[STAR]43.0)[SLASH](32.32[MINUS]3434224.34343))";
        assertEquals(expected, expression.toString());
    }
}