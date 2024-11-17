package org.expression.operation;

import org.expression.Expression;
import org.expression.SimpleExpression;

public class Subtraction extends Operation {

    public Subtraction(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Expression evaluate() {
        if (getLeft().simplify().matches("^-?[0-9]+$") && getRight().simplify().matches("^-?[0-9]+$")) {
            String val = Integer.toString(Integer.parseInt(getLeft().simplify()) - Integer.parseInt(getRight().simplify()));
            return new SimpleExpression(val);
        } else if (getLeft().simplify().matches("^-?[0-9]+(\\.[0-9]*)?$") && getRight().simplify().matches("^-?[0-9]+(\\.[0-9]*)?$")) {
            String val = Double.toString(Double.parseDouble(getLeft().simplify()) - Double.parseDouble(getRight().simplify()));
            return new SimpleExpression(val);
        } else {
            return this; 
        }
    }

    @Override
    public String stringify() {
        return "(" + getLeft().stringify() + "-" + getRight().stringify() + ")";
    }
    
    
}