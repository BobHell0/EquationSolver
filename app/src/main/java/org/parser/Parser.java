package org.parser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.expression.CompoundExpressionFactory;
import org.expression.Expression;
import org.expression.SimpleExpression;

import java.util.HashMap;
import java.util.Map;

public class Parser {

    private String line;

    public Parser(String line) {
        this.line = line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    // Note this method should not be here, it should be in the expression stuff
    public static String createExpression(String s) {
        return s;
    }

    // Note this method should not be here, it should be in the expression stuff
    public static String createCompoundExpression(String left, String right, String op) {
        return left+op+right;
    }

    public static String parseExpressionComputation(Parser e, Map<String, Expression> table) {
        System.out.println(e.line);

        System.out.println(e.line);


        Pattern negativeOutFrontPattern = Pattern.compile("^-(\\\\[0-9]+|[A-Za-z]|[0-9]+(?:\\.[0-9]*)?)");

        // notice that the left number of the * or / will not be negative
        Pattern divAndMultPattern = Pattern.compile("((?:\\\\[0-9]+)|(?:[A-Za-z])|(?:[0-9]+(?:\\.[0-9]*)?))([*/])((?:\\\\[0-9]+)|(?:[A-Za-z])|(?:-?[0-9]+(?:\\.[0-9]*)?))");

        Pattern addAndSubPattern = Pattern.compile("((?:\\\\[0-9]+)|(?:[A-Za-z])|(?:-?[0-9]+(?:\\.[0-9]*)?))([-\\+])((?:\\\\[0-9]+)|(?:[A-Za-z])|(?:-?[0-9]+(?:\\.[0-9]*)?))");
        
        Pattern negativeNumberPattern = Pattern.compile("^-((?:\\\\[0-9]+)|(?:[A-Za-z])|(?:-?[0-9]+(?:\\.[0-9]*)?))");
        // recursively handling bracketed expressions
        for(int i = 0; i < e.line.length(); i++) {
            if (e.line.charAt(i) != '(') continue;
            else {
                int bracketStack = 1;
                int j = i;
                while (bracketStack > 0 && j < e.line.length()) {
                    j++;
                    if (e.line.charAt(j) == '(') {
                        bracketStack++;
                    } else if (e.line.charAt(j) == ')') {
                        bracketStack--;
                    }
                    
                }
                String parsedBracket = parseExpressionComputation(new Parser(e.line.substring(i + 1, j)), table);
                e.line = e.line.substring(0, i) + parsedBracket + e.line.substring(j + 1);
            }
        }

        System.out.println("About to look for operations in " + e.line);

        findSingleExpressionOperation(negativeOutFrontPattern, e, table);

        findOperations(divAndMultPattern, e, table);
        findOperations(addAndSubPattern, e, table);

        // findSingleExpressionOperation(negativeNumberPattern, e, table);

        System.out.println(e.line);
        return e.line;
    }

    private static void findSingleExpressionOperation(Pattern p, Parser e, Map<String, Expression> table) {
        Matcher m = p.matcher(e.line);
        if (m.find()) {

            int matchLength = m.group(0).length();
            int start = e.line.indexOf(m.group(0));
            int end = start + matchLength;

            Expression expressionToRight;
            if (m.group(1).startsWith("\\")) {
                expressionToRight = table.get(m.group(1));
            } else {
                expressionToRight = new SimpleExpression(m.group(1));
            }
            Expression finalExpression = CompoundExpressionFactory.negativifyExpression(expressionToRight);

            String expIdentifier = "\\" + table.size();

            table.put(expIdentifier, finalExpression);
            e.line = e.line.substring(0, start) + expIdentifier + e.line.substring(end);
        }
    }

    private static void findOperations(Pattern p, Parser e, Map<String, Expression> table) {
        Matcher m = p.matcher(e.line);
        
        while (m.find()) {

            int matchLength = m.group(0).length();
            int start = e.line.indexOf(m.group(0));
            int end = start + matchLength;

            Expression leftExpression;
            if (m.group(1).startsWith("\\")) {
                leftExpression = table.get(m.group(1));
            } else {
                leftExpression = new SimpleExpression(m.group(1));
                // leftExpression = createExpression(multAndDivs.group(1));
            }

            Expression rightExpression;
            if (m.group(3).startsWith("\\")) {
                rightExpression = table.get(m.group(3));
            } else {
                rightExpression = new SimpleExpression(m.group(3));
            }
            Expression finalExpression = CompoundExpressionFactory.expressionFromOperation(m.group(2).substring(0,1), leftExpression, rightExpression);

            String expIdentifier = "\\" + table.size();

            table.put(expIdentifier, finalExpression);
            e.line = e.line.substring(0, start) + expIdentifier + e.line.substring(end);

            m = p.matcher(e.line);
        }
    }

    public static String parseLine(String line) {

        Map<String, Expression> table = new HashMap<>();
        Parser e = new Parser(line);

        // remove all white space – makes the regex-ing easier
        e.line = e.line.replaceAll("\s", "");
        
        // Assumes there is at least one operaiton in the line, and tries to
        // fill up the hash table with keys to map to relevant expressions
        String finalEncoding = parseExpressionComputation(e, table);

        // This will only happen if there were no operations to break the input
        // line down
        if (!finalEncoding.matches("^\\\\[0-9]+")) {
            return finalEncoding;
        }

        return table.get(finalEncoding).simplify();

    }
    public static void main(String[] args) {
        String expression = new String("-5 * -3");

        Map<String, Expression> table = new HashMap<>();
        Parser e = new Parser(expression);

        e.line = e.line.replaceAll("\s", "");
        // e.line = e.line.replaceAll("-", "+-");

        parseExpressionComputation(e, table);
        System.out.println();
        System.out.println(table.size());

        for (String key : table.keySet()) {
            System.out.println(key);
            System.out.println(table.get(key).getClass());
            System.out.println(table.get(key).stringify());
            System.out.println(table.get(key).evaluate().stringify());
        }
        
    }

}