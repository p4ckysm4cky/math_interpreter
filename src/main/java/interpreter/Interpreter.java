package main.java.interpreter;


public class Interpreter {

    public static void main(String[] args) {
        Token newToken = new Token(TokenType.NUMBER, "32", 0);
        System.out.println(newToken);

    }

    /**
     * Used to take in an input stream
     */
    public static void runtPrompt() {
        ;
    }
}
