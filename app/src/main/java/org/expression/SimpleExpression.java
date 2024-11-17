package app.src.main.java.org.expression;

public class SimpleExpression implements Expression {

    String val;

    public SimpleExpression(String val) {
        this.val = val;
    }

    @Override
    public Expression evaluate() {
        return this;
    }

    @Override
    public String stringify() {
        return val;
    }
    
    
}