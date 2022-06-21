package main.java.interpreter;

public interface AstNode {
    public String toString();
    public double accept(Visitor v);
}
