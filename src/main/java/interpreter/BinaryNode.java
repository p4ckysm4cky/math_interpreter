package main.java.interpreter;

public class BinaryNode implements AstNode {
    private AstNode left;
    private Token operator;
    private AstNode right;

    public BinaryNode(AstNode left, Token operator, AstNode right) {
        this.left= left;
        this.operator = operator;
        this.right = right;
    }

    public double accept(Visitor v) {
        return v.visitBinaryNode(this);
    }

    public AstNode getLeft() {
        return this.left;
    }

    public AstNode getRight() {
        return this.right;
    }

    public Token getOperator() {
        return operator;
    }

    public String toString() {
        return "(" + left.toString() + operator.toString() + right.toString() + ")";
    }
}
