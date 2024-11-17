package org.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BasicParsingVariablesTest {
    @Test
    @DisplayName("Basic Variable Addition - Non-like terms")
    public void VariableAdditionNonLikeTerms() {
        assertEquals(new String("(a+b)"), Parser.parseLine("a + b"));
    }

    // @Test
    // @DisplayName("Basic Variable Addition - Like terms")
    // public void VariableAdditionLikeTerms() {
    //     assertEquals(new String ("(2*a)"), Parser.parseLine("a + a"));

    // }
    
}
