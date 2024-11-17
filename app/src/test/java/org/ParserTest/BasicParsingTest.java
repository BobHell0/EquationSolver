package app.src.test.java.org.ParserTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import app.src.main.java.org.parser.Parser;

public class BasicParsingTest {
    @Test
    @DisplayName("Basic Addition")
    public void BasicAddition() {
        assertEquals(7, Integer.parseInt(Parser.parseLine("3 + 4")));
    }

    @Test
    @DisplayName("Basic Subtraction")
    public void BasicSubtraction() {
        assertEquals(6, Integer.parseInt(Parser.parseLine("18 - 12")));
    }

    @Test
    @DisplayName("Basic Multiplication")
    public void BasicMultiplication() {
        assertEquals(20, Integer.parseInt(Parser.parseLine("4 * 5")));
    }

    @Test
    @DisplayName("Basic Division")
    public void BasicDivision() {
        assertEquals(3, Integer.parseInt(Parser.parseLine("15 / 5")));
    }

    @Test
    @DisplayName("Negative Plus Positive")
    public void NegativePlusPositive() {
        assertEquals(-1, Integer.parseInt(Parser.parseLine("-8 + 7")));
    }

    @Test
    @DisplayName("Negative Times Positive")
    public void NegativeTimesPositive() {
        assertEquals(-56, Integer.parseInt(Parser.parseLine("-8 * 7")));
    }

    @Test
    @DisplayName("Positve Minus Negative")
    public void DoubleNegative() {
        assertEquals(23, Integer.parseInt(Parser.parseLine("8 - - 15")));
    }

    @Test
    @DisplayName("Negative Minus Negative")
    public void NegativeMinusNegative() {
        assertEquals(10, Integer.parseInt(Parser.parseLine("-13 - -23")));
    }

}
