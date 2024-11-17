package app.src.test.java.org.ParserTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import app.src.main.java.org.parser.Parser;

public class BasicParsing {
    @Test
    @DisplayName("Basic Addition")
    public void BasicAddition() {
        assertEquals("7", Parser.parseLine("3 + 4"));
    }
}
