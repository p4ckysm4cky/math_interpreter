package main.java.interpreter;

public class UnaryNode implements AstNode {
    private Token operator;
    private AstNode node;

    public UnaryNode(Token operator, AstNode node) {
        this.operator = operator;
        this.node = node;
    }

    public double accept(Visitor v) {
        return v.visitUnaryNode(this);
    }

    public String toString() {
        return "(" + this.operator.toString() + this.node.toString() + ")";
    }
}
