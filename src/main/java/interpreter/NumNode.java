package main.java.interpreter;

public class NumNode implements AstNode {
    private NumToken number;
    private double value;

    public NumNode(NumToken number) {
         this.number = number;
         this.value = this.number.getValue();
    }

    public String toString() {
        return "" + this.number.getValue();
    }

    public double getValue() {
        return this.value;
    }
}
