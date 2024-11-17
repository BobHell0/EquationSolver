package org.expression;

import org.expression.operation.Addition;
import org.expression.operation.Division;
import org.expression.operation.Multiplication;
import org.expression.operation.Subtraction;

public class CompoundExpressionFactory {
    public static Expression expressionFromOperation(String operation, Expression exp1, Expression exp2) {
        if (operation.equals("*")) {
            return new Multiplication(exp1, exp2);
        } else if (operation.equals("/")) {
            return new Division(exp1, exp2);
        } else if (operation.equals("+")) {
            return new Addition(exp1, exp2);
        } else if (operation.equals("-")) {
            return new Subtraction(exp1, exp2);
        }
        
        else {
            throw new Error("Operation appears to be invalid");
        }
    }

    public static Expression negativifyExpression(Expression exp) {
        return new Subtraction(new SimpleExpression("0"), exp);
    }
}