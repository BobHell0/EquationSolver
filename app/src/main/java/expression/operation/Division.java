package app.src.main.java.expression.operation;

import app.src.main.java.expression.Expression;
import app.src.main.java.expression.SimpleExpression;

public class Division implements Expression {

    Expression left;
    Expression right;
    
    public Division(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Expression evaluate() {
        System.out.println(left.simplify());
        System.out.println(right.simplify());
        if (left.simplify().matches("^-?[0-9]+(\\.[0-9]*)?$") && right.simplify().matches("^-?[0-9]+(\\.[0-9]*)?$")) {
            String val = Double.toString(Double.parseDouble(left.simplify()) / Double.parseDouble(right.simplify()));
            return new SimpleExpression(val);
        } else {
            return this;
        }
    }

    @Override
    public String stringify() {
        return left.stringify() + "/" + right.stringify();
    }
}