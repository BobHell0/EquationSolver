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
        assertEquals(85.333, 
            Math.round(
                Double.parseDouble(
                    Parser.parseLine("4 * 2 / 3 * 8/3 * 4 * 3")
                ) * 1000
            ) / 1000.0);
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
    @DisplayName("Subtraction and Division")
    public void SubtractionAndDivision() {
        assertEquals(-27.5167, 
            Math.round(
                Double.parseDouble(
                    Parser.parseLine("-4 / 15 - 3 / 4 - 6 - 3/6 - 12 - 8")
                ) * 10000
            ) / 10000.0
        );
    }

    @Test
    @DisplayName("Addition and Division")
    public void AdditionAndDivision() {
        assertEquals(14.833, 
            Math.round(
                Double.parseDouble(
                    Parser.parseLine("3 / 2 + 1 / 2 + 4 + 3 + 4 / 3 + 9 / 2")
                ) * 1000 ) / 1000.0);
    }

    @Test
    @DisplayName("Randomised Calculations")
    public void RandomisedCalculations() {
        assertEquals(0.25, Double.parseDouble(Parser.parseLine("3 / 2 / 3 / 2")));
        assertEquals(-12.5, Double.parseDouble(Parser.parseLine("4 * (-3) + 2 - 3/2*2 + 1- 6/3 + (3 + (3 - 3)) / 2")));
        assertEquals(-9.107, Math.round(
            Double.parseDouble(
                Parser.parseLine("-3/-2 * (-2 + 3/7 + 6/4) - 6 * 3/2")) 
            * 1000) 
        / 1000.0);

        assertEquals(-2, Integer.parseInt(Parser.parseLine("3 + (-4/-2) * 1 - (6 - 3) + 3 * 2 - 7 + 3 * (3-4)")));
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
