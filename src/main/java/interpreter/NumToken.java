package main.java.interpreter;

public class NumToken extends Token{
    // normal tokens don't have a proper value, so
    // in NumToken we store the number in NumToken
    private double value;

    NumToken(TokenType type,  int col, double value) {
        super(type, col);
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        String returnStr = "[" + type + ":" + value + "]";
        return returnStr;
    }
}
