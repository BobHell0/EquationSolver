package org.expression.operation;

import org.expression.Expression;
import org.expression.SimpleExpression;

public class Division implements Expression {

    Expression left;
    Expression right;
    
    public Division(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Expression evaluate() {
        if (right.simplify().matches("0+(\\.0*)?")) {
            // TODO: throw divide-by-zero error
        }
        if (left.simplify().matches("^-?[0-9]+(\\.[0-9]*)?$") && right.simplify().matches("^-?[0-9]+(\\.[0-9]*)?$")) {
            String val = Double.toString(Double.parseDouble(left.simplify()) / Double.parseDouble(right.simplify()));
            if (val.matches("^[0-9]*\\.0$")) {
                val = val.split("\\.")[0];
            }
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