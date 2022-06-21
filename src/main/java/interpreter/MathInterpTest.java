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
    }
}