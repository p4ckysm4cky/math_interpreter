package main.java.interpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MathInterp {

    public static void main(String[] args) throws IOException {
        runPrompt();

    }

    /**
     * Used to take in an input stream
     */
    public static void runPrompt() throws IOException {
        // We use BufferedReader instead of scanner, because we can
        // read character by character
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        while (true) {
            System.out.printf(">>> ");
            // The program will wait for input for the reader before proceeding
            String line = reader.readLine();
            // return when we get EOF send to terminal
            if (line == null) {
                return;
            }
            // run line
            run(line);

        }
    }

    public static double eval(AstNode root) {
        AstVisitor visitor = new AstVisitor();
        return root.accept(visitor);
    }

    /**
     * @param line, a String that is executed after parsing
     */
    private static void run(String line) {
        Lexer lexer = new Lexer(line);
        ArrayList<Token> lexerTokens = lexer.scanTokens();
        if (lexer.containErrors()) {
            System.out.println(lexer.errorLog());
            return;
        }
        try {
            Parser parser = new Parser(lexerTokens);
            AstNode root = parser.expression();
            System.out.println(eval(root));
        } catch (Exception e) {
            System.out.println("Error in parsing or evaluating");
        }
    }
}
