package main.java.interpreter;

interface Visitor {
    double visitUnaryNode(UnaryNode node);

    double visitBinaryNode(BinaryNode node);

    double visitNumNode(NumNode node);
}
