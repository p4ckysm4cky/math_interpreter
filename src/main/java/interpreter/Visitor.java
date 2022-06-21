package main.java.interpreter;

interface Visitor {
    public double visitUnaryNode(UnaryNode node);
    public double visitBinaryNode(BinaryNode node);
    public double visitNumNode(NumNode node);
}
