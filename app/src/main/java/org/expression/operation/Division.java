package org.expression.operation;

import org.expression.Expression;
import org.expression.SimpleExpression;

public class Division extends Operation {

    
    public Division(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Expression evaluate() {
        if (getRight().simplify().matches("0+(\\.0*)?")) {
            // TODO: throw divide-by-zero error
        }
        if (getLeft().simplify().matches("^-?[0-9]+(\\.[0-9]*)?$") && getRight().simplify().matches("^-?[0-9]+(\\.[0-9]*)?$")) {
            String val = Double.toString(Double.parseDouble(getLeft().simplify()) / Double.parseDouble(getRight().simplify()));
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
        return getLeft().stringify() + "/" + getRight().stringify();
    }
}