package app.src.main.java.org.expression;

public interface Expression {
    public Expression evaluate();
    public String stringify();
    public default String simplify() {
        return evaluate().stringify();
    }
}