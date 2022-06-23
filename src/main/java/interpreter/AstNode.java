package main.java.interpreter;

public interface AstNode {
    String toString();

    double accept(Visitor v);
}
