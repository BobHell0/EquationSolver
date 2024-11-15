import java.util.regex.Matcher;
import java.util.regex.Pattern;

import expression.CompoundExpressionFactory;
import expression.Expression;
import expression.SimpleExpression;

import java.util.HashMap;
import java.util.Map;

public class experimentingWithRegex {


    // Note this method should not be here, it should be in the expression stuff
    public static String createExpression(String s) {
        return s;
    }

    // Note this method should not be here, it should be in the expression stuff
    public static String createCompoundExpression(String left, String right, String op) {
        return left+op+right;
    }

    public static void parseExpression(String line, Map<String, Expression> table) {
        line = line.replaceAll("\s", "");
        System.out.println(line);
        
        Pattern numberPattern = Pattern.compile("^[0-9]+", Pattern.CASE_INSENSITIVE);
        Pattern pronumeralPattern = Pattern.compile("[A-Z][a-z]");

        Pattern operationPattern = Pattern.compile("[-+/*]", Pattern.CASE_INSENSITIVE);

        // crazy stuff – {3} would be an entry in the hash table that would 
        Pattern divAndMultPattern = Pattern.compile("((?:\\\\[0-9]+)|(?:[A-Za-z])|(?:[0-9]+(?:\\.[0-9]*)?))([*/])((?:\\\\[0-9]+)|(?:[A-Za-z])|(?:[0-9]+(?:\\.[0-9]*)?))");

        Pattern addAndSubPattern = Pattern.compile("((?:\\\\[0-9]+)|(?:[A-Za-z])|(?:[0-9]+(?:\\.[0-9]*)?))([-+])((?:\\\\[0-9]+)|(?:[A-Za-z])|(?:[0-9]+(?:\\.[0-9]*)?))");
        

        // recursively handling bracketed expressions
        for(int i = 0; i < line.length(); i++) {
            if (line.charAt(i) != '(') continue;
            else {
                int bracketStack = 1;
                int j = i;
                while (bracketStack > 0 && j < line.length()) {
                    j++;
                    if (line.charAt(j) == '(') {
                        bracketStack++;
                    } else if (line.charAt(j) == ')') {
                        bracketStack--;
                    }
                    
                }
                parseExpression(line.substring(i + 1, j), table);
            }
        }

        System.out.println("About to look for operations in " + line);

        Matcher multAndDivs = divAndMultPattern.matcher(line);


        while (multAndDivs.find()) {

            System.out.println(multAndDivs.group(0));
            System.out.println(multAndDivs.group(1));
            System.out.println(multAndDivs.group(2));
            System.out.println(multAndDivs.group(3));

            int matchLength = multAndDivs.group(0).length();
            int start = line.indexOf(multAndDivs.group(0));
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
            line = line.substring(0, start) + expIdentifier + line.substring(end);

            multAndDivs = divAndMultPattern.matcher(line);
        }


        Matcher addsAndSubs = addAndSubPattern.matcher(line);


        while (addsAndSubs.find()) {
            System.out.println(addsAndSubs.group(0));
            System.out.println(addsAndSubs.group(1));
            System.out.println(addsAndSubs.group(2));
            System.out.println(addsAndSubs.group(3));

            int matchLength = addsAndSubs.group(0).length();
            int start = line.indexOf(addsAndSubs.group(0));
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
            line = line.substring(0, start) + expIdentifier + line.substring(end);

            multAndDivs = divAndMultPattern.matcher(line);
        }

        System.out.println(line);

        // Matcher addAndSubs = addAndSubPattern.matcher(line);
        // if (addAndSubs.find()) {
        //     System.out.println(addAndSubs.group(0));
        //     System.out.println(addAndSubs.group(1));
        //     System.out.println(addAndSubs.group(2));
        //     System.out.println(addAndSubs.group(3));
        // }
    }
    public static void main(String[] args) {
        String expression = new String("3/4 * (2 + 3) * 4.");
        Map<String, Expression> table = new HashMap<>();
        parseExpression(expression, table);
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
