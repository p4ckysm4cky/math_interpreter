package main.java.interpreter;
import java.lang.Math;

public class AstVisitor implements Visitor {
    public double visitUnaryNode(UnaryNode node) {
        switch(node.getOperator().getTokenType()) {
            case MINUS:
                return -1 * node.getNode().accept(this);
            default:
                System.out.println("Error in visitUnaryNode");
                return node.getNode().accept(this);
        }
    }

    public double visitNumNode(NumNode node) {
        return node.getValue();
    }

    public double visitBinaryNode(BinaryNode node) {
        double leftNum = node.getLeft().accept(this);
        double rightNum = node.getRight().accept(this);
        switch(node.getOperator().getTokenType()) {
            case MINUS:
                return leftNum - rightNum;
            case PLUS:
                return leftNum + rightNum;
            case STAR:
                return leftNum * rightNum;
            case SLASH:
                return leftNum / rightNum;
            case CARET:
                return Math.pow(leftNum, rightNum);
            default:
                System.out.println("Error in visitBinaryNode");
                return leftNum;
        }
    }
}
