package main.java.interpreter;

public class NumNode implements astNode{
    private NumToken number;
    private double value;

    public NumNode(NumToken number) {
         this.number = number;
         this.value = this.number.getValue();
    }

    public String toString() {
        return this.number.toString();
    }

    public double getValue() {
        return this.value;
    }
}
