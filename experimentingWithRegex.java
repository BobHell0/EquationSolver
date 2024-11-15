import java.util.regex.Matcher;
import java.util.regex.Pattern;

import expression.CompoundExpressionFactory;
import expression.Expression;
import expression.SimpleExpression;

import java.util.HashMap;
import java.util.Map;

public class experimentingWithRegex {

    private String line;

    public experimentingWithRegex(String line) {
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

    public static String parseExpression(experimentingWithRegex e, Map<String, Expression> table) {
        e.line = e.line.replaceAll("\s", "");
        System.out.println(e.line);

        // crazy stuff – {3} would be an entry in the hash table that would 
        Pattern divAndMultPattern = Pattern.compile("((?:\\\\[0-9]+)|(?:[A-Za-z])|(?:[0-9]+(?:\\.[0-9]*)?))([*/])((?:\\\\[0-9]+)|(?:[A-Za-z])|(?:[0-9]+(?:\\.[0-9]*)?))");

        Pattern addAndSubPattern = Pattern.compile("((?:\\\\[0-9]+)|(?:[A-Za-z])|(?:[0-9]+(?:\\.[0-9]*)?))([-+])((?:\\\\[0-9]+)|(?:[A-Za-z])|(?:[0-9]+(?:\\.[0-9]*)?))");
        

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
                String parsedBracket = parseExpression(new experimentingWithRegex(e.line.substring(i + 1, j)), table);
                e.line = e.line.substring(0, i) + parsedBracket + e.line.substring(j + 1);
            }
        }

        System.out.println("About to look for operations in " + e.line);

        Matcher multAndDivs = divAndMultPattern.matcher(e.line);


        while (multAndDivs.find()) {

            System.out.println(multAndDivs.group(0));
            System.out.println(multAndDivs.group(1));
            System.out.println(multAndDivs.group(2));
            System.out.println(multAndDivs.group(3));

            int matchLength = multAndDivs.group(0).length();
            int start = e.line.indexOf(multAndDivs.group(0));
            int end = start + matchLength;

            Expression leftExpression;
            if (multAndDivs.group(1).startsWith("\\")) {
                leftExpression = table.get(multAndDivs.group(1));
            } else {
                leftExpression = new SimpleExpression(multAndDivs.group(1));
                // leftExpression = createExpression(multAndDivs.group(1));
            }

            Expression rightExpression;
            if (multAndDivs.group(3).startsWith("\\")) {
                rightExpression = table.get(multAndDivs.group(3));
            } else {
                rightExpression = new SimpleExpression(multAndDivs.group(3));
            }
            Expression finalExpression = CompoundExpressionFactory.expressionFromOperation(multAndDivs.group(2), leftExpression, rightExpression);

            String expIdentifier = "\\" + table.size();

            table.put(expIdentifier, finalExpression);
            e.line = e.line.substring(0, start) + expIdentifier + e.line.substring(end);

            multAndDivs = divAndMultPattern.matcher(e.line);
        }


        Matcher addsAndSubs = addAndSubPattern.matcher(e.line);


        while (addsAndSubs.find()) {
            System.out.println(addsAndSubs.group(0));
            System.out.println(addsAndSubs.group(1));
            System.out.println(addsAndSubs.group(2));
            System.out.println(addsAndSubs.group(3));

            int matchLength = addsAndSubs.group(0).length();
            int start = e.line.indexOf(addsAndSubs.group(0));
            int end = start + matchLength;

            Expression leftExpression;
            if (addsAndSubs.group(1).startsWith("\\")) {
                leftExpression = table.get(addsAndSubs.group(1));
            } else {
                leftExpression = new SimpleExpression(addsAndSubs.group(1));
                // leftExpression = createExpression(multAndDivs.group(1));
            }

            Expression rightExpression;
            if (addsAndSubs.group(3).startsWith("\\")) {
                rightExpression = table.get(addsAndSubs.group(3));
            } else {
                rightExpression = new SimpleExpression(addsAndSubs.group(3));
            }
            Expression finalExpression = CompoundExpressionFactory.expressionFromOperation(addsAndSubs.group(2), leftExpression, rightExpression);

            String expIdentifier = "\\" + table.size();

            table.put(expIdentifier, finalExpression);
            e.line = e.line.substring(0, start) + expIdentifier + e.line.substring(end);

            multAndDivs = divAndMultPattern.matcher(e.line);
        }

        System.out.println(e.line);
        return e.line;

        // Matcher addAndSubs = addAndSubPattern.matcher(line);
        // if (addAndSubs.find()) {
        //     System.out.println(addAndSubs.group(0));
        //     System.out.println(addAndSubs.group(1));
        //     System.out.println(addAndSubs.group(2));
        //     System.out.println(addAndSubs.group(3));
        // }
    }
    public static void main(String[] args) {
        String expression = new String("123 - 321/4.2 * (3 + 2) / 4");
        Map<String, Expression> table = new HashMap<>();
        experimentingWithRegex e = new experimentingWithRegex(expression);
        
        parseExpression(e, table);
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
