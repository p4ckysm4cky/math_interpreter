package main.java.interpreter;

import org.junit.Test;

import static org.junit.Assert.*;

public class MathInterpTest {

    public AstNode genAstNode(String expr) {
        return new Parser(new Lexer(expr).scanTokens()).expression();
    }

    @Test
    public void testAddSubtractEval() {
        MathInterp interp = new MathInterp();
        double output = interp.eval(genAstNode("1+2"));
        assertEquals(3.0, output, 0.00000001);

        output = interp.eval(genAstNode("1+2+3"));
        assertEquals(6.0, output, 0.00000001);

        output = interp.eval(genAstNode("1+2-3"));
        assertEquals(0.0, output, 0.00000001);

        output = interp.eval(genAstNode("39-67"));
        assertEquals(-28.0, output, 0.00000001);

        output = interp.eval(genAstNode("1-67-43 - 4 - 4"));
        assertEquals(-117.0, output, 0.00000001);

        output = interp.eval(genAstNode("59293.2434504 - 454543.32020"));
        assertEquals(-395250.0767496, output, 0.00000001);

        output = interp.eval(genAstNode("59 - 32.56 + 68.34 + 56.98 - 45.23 + 495.33 - 999.6544"));
        assertEquals(-397.7944, output, 0.00000001);

        output = interp.eval(genAstNode("0 - 0"));
        assertEquals(0, output, 0.00000001);

        output = interp.eval(genAstNode("0 + 0"));
        assertEquals(0, output, 0.00000001);

        output = interp.eval(genAstNode("999.9999 - 999.999"));
        assertEquals(0.000900000, output, 0.00000001);
    }

    @Test
    public void testMultiplyDivideAndPreviousEval() {
        MathInterp interp = new MathInterp();
        double output = interp.eval(genAstNode("2*3"));
        assertEquals(6.0, output, 0.00000001);

        output = interp.eval(genAstNode("3 + 5 * 2"));
        assertEquals(13, output, 0.00000001);

        output = interp.eval(genAstNode("3 + 5 * 2"));
        assertEquals(13, output, 0.00000001);

        output = interp.eval(genAstNode("90 * 30 - 56"));
        assertEquals(2644, output, 0.00000001);

        output = interp.eval(genAstNode("5 - 9 * 50"));
        assertEquals(-445, output, 0.00000001);

        output = interp.eval(genAstNode("2/3"));
        assertEquals(0.6666666666666666, output, 0.00000001);

        output = interp.eval(genAstNode("20 / 5 + 3"));
        assertEquals(7, output, 0.00000001);

        output = interp.eval(genAstNode("360 / 4 * 32 + 45 / 35 * 34 -56 +23 + 545 * 123"));
        assertEquals(69925.71428571429, output, 0.00000001);

        output = interp.eval(genAstNode("4 * 5 / 0"));
        assertEquals(1/0.0, output, 0.00000001);
    }

    @Test
    public void testPowerAndPreviousEval() {
        MathInterp interp = new MathInterp();
        double output = interp.eval(genAstNode("2^2"));
        assertEquals(4.0, output, 0.00000001);

        output = interp.eval(genAstNode("4 ^ 9"));
        assertEquals(262144, output, 0.00000001);

        output = interp.eval(genAstNode("4 ^ 9 * 3"));
        assertEquals(786432, output, 0.00000001);

        output = interp.eval(genAstNode("4 ^ 9 - 3"));
        assertEquals(262141, output, 0.00000001);

        output = interp.eval(genAstNode("32 ^ 4 + 56 - 45 * 2 ^ 3.25 / 34"));
        assertEquals(1048619.408395253, output, 0.00000001);

        output = interp.eval(genAstNode("5 ^ 3 / 5 * 10"));
        assertEquals(250, output, 0.00000001);
    }

    @Test
    public void testUnaryAndPreviousEval() {
        MathInterp interp = new MathInterp();
        double output = interp.eval(genAstNode("5+ -3"));
        assertEquals(2, output, 0.00000001);

        output = interp.eval(genAstNode("5- -3"));
        assertEquals(8, output, 0.00000001);

        output = interp.eval(genAstNode("-5 + -3"));
        assertEquals(-8, output, 0.00000001);

        output = interp.eval(genAstNode("-3- -3"));
        assertEquals(0, output, 0.00000001);

        output = interp.eval(genAstNode("3* -3"));
        assertEquals(-9, output, 0.00000001);

        output = interp.eval(genAstNode("-4* -3"));
        assertEquals(12, output, 0.00000001);

        output = interp.eval(genAstNode("59 / -34"));
        assertEquals(-1.7352941176470589, output, 0.00000001);

        output = interp.eval(genAstNode("-59 / -34"));
        assertEquals(1.7352941176470589, output, 0.00000001);

        output = interp.eval(genAstNode("4 ^ -1"));
        assertEquals(0.25, output, 0.00000001);

        output = interp.eval(genAstNode("-4 ^ -1"));
        assertEquals(-0.25, output, 0.00000001);

        output = interp.eval(genAstNode("4 * -4 ^ -1"));
        assertEquals(-1, output, 0.00000001);

        output = interp.eval(genAstNode("-4 * -4 ^ -1"));
        assertEquals(1, output, 0.00000001);

        output = interp.eval(genAstNode("-4 * -4 ^ -1 /4 * 35 "));
        assertEquals(8.75, output, 0.00000001);
    }

    @Test
    public void testParenthesisEval() {
        MathInterp interp = new MathInterp();
        double output = interp.eval(genAstNode("5"));
        assertEquals(5, output, 0.00000001);

        output = interp.eval(genAstNode("(6)"));
        assertEquals(6, output, 0.00000001);

        output = interp.eval(genAstNode("(6+3) * 2"));
        assertEquals(18, output, 0.00000001);

        output = interp.eval(genAstNode("-(6+3) * 2"));
        assertEquals(-18, output, 0.00000001);

        output = interp.eval(genAstNode("-(6+3) * -2"));
        assertEquals(18, output, 0.00000001);

        output = interp.eval(genAstNode("3+2^(5+3)"));
        assertEquals(259, output, 0.00000001);

        output = interp.eval(genAstNode("3*2^-(5+3)"));
        assertEquals(0.01171875, output, 0.00000001);

        output = interp.eval(genAstNode("((3*2^-(5+3)))"));
        assertEquals(0.01171875, output, 0.00000001);

        output = interp.eval(genAstNode("-(42 + 18) / -(8-3)"));
        assertEquals(12, output, 0.00000001);

        output = interp.eval(genAstNode("(42 + 18) / -(8-3)"));
        assertEquals(-12, output, 0.00000001);
    }


}