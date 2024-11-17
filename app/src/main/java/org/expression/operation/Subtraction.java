package org.expression.operation;

import org.expression.Expression;
import org.expression.SimpleExpression;

public class Subtraction implements Expression {


    public Expression left;
    private Expression right;

    public Subtraction(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Expression evaluate() {
        if (left.simplify().matches("^-?[0-9]+$") && right.simplify().matches("^-?[0-9]+$")) {
            String val = Integer.toString(Integer.parseInt(left.simplify()) - Integer.parseInt(right.simplify()));
            return new SimpleExpression(val);
        } else if (left.simplify().matches("^-?[0-9]+(\\.[0-9]*)?$") && right.simplify().matches("^-?[0-9]+(\\.[0-9]*)?$")) {
            String val = Double.toString(Double.parseDouble(left.simplify()) - Double.parseDouble(right.simplify()));
            return new SimpleExpression(val);
        } else {
            return this; 
        }
    }

    @Override
    public String stringify() {
        return "(" + left.stringify() + "-" + right.stringify() + ")";
    }
    
    
}