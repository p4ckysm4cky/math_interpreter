package main.java.interpreter;

public class UnaryNode implements AstNode {
    private final Token operator;
    private final AstNode node;

    public UnaryNode(Token operator, AstNode node) {
        this.operator = operator;
        this.node = node;
    }

    public double accept(Visitor v) {
        return v.visitUnaryNode(this);
    }

    public Token getOperator() {
        return operator;
    }

    public AstNode getNode() {
        return node;
    }

    public String toString() {
        return "(" + this.operator.toString() + this.node.toString() + ")";
    }
}
