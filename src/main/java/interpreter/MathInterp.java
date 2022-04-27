package main.java.interpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class MathInterp {

    public static void main(String[] args) throws IOException{
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

        }
    }

    /**
     *
     * @param line, a String that is executed after parsing
     */
    private static void run(String line) {
        ;
    }
}
