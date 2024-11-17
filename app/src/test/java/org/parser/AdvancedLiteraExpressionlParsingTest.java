package org.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AdvancedLiteraExpressionlParsingTest {
    @Test
    @DisplayName("Addition and Subtraction")
    public void AdditionAndSubtraction() {
        assertEquals(-7, Integer.parseInt(Parser.parseLine("3 + 4 - 2 - 17 + 3 - 2 + 4")));
    }

    @Test
    @DisplayName("Multiplication and Division")
    public void MultiplciationAndDivision() {
        assertEquals(85.3333333333, 
            Math.round(
                Double.parseDouble(
                    Parser.parseLine("4 * 2 / 3 * 8/3 * 4 * 3")
                )
            ), 10);
    }

    @Test
    @DisplayName("Addition and Multiplication")
    public void AdditionAndMultiplciation() {
        assertEquals(64, Integer.parseInt(Parser.parseLine("3 + 4 * 2 * 5 + 4 + 2 * 7 + 3")));
    }

    @Test
    @DisplayName("Subtraction and Multiplication") 
    public void SubtractionAndMultiplcation() {
        assertEquals(-20, Integer.parseInt(Parser.parseLine("-4 * -3 - 2 * 2 - 12 * 2 - 4")));
    }

    @Test
    @DisplayName("No Operations - Simple Value Only")
    public void SimpleValueOnly() {
        assertEquals(4, Integer.parseInt(Parser.parseLine("4")));
    }

    @Test
    @DisplayName("No Operations - Simple Negative Value Only")
    public void SimpleNegativeValueOnly() {
        assertEquals(-4, Integer.parseInt(Parser.parseLine("-4")));
    }
}
