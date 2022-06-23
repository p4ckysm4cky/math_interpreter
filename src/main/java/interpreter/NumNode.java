package main.java.interpreter;

public class NumNode implements AstNode {
    private final NumToken number;
    private final double value;

    public NumNode(NumToken number) {
        this.number = number;
        this.value = this.number.getValue();
    }

    public double accept(Visitor v) {
        return v.visitNumNode(this);
    }

    public String toString() {
        return "" + this.number.getValue();
    }

    public double getValue() {
        return this.value;
    }
}
