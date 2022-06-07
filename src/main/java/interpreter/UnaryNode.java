package main.java.interpreter;

public class UnaryNode implements astNode{
    private Token operator;
    private astNode node;

    public UnaryNode(Token operator, astNode node) {
        this.operator = operator;
        this.node = node;
    }

    public String toString() {
        return "(" + this.operator.toString() + this.node.toString() + ")";
    }
}
