package main.java.interpreter;

public class BinaryNode implements astNode{
    private astNode left;
    private Token operator;
    private astNode right;

    public BinaryNode(astNode left, Token operator, astNode right) {
        this.left= left;
        this.operator = operator;
        this.right = right;
    }

    public String toString() {
        return "(" + left.toString() + operator.toString() + right.toString() + ")";
    }
}
