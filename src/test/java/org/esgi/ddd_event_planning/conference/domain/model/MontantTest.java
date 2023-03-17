package org.esgi.ddd_event_planning.conference.domain.model;

import org.esgi.ddd_event_planning.conference.domain.model.Montant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Montant")
class MontantTest {
    @Test
    @DisplayName("Cannot be negative")
    void createMontantWithNegativeValue() {
        // Arrange
        // ACT
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            new Montant(-10, "EUR");
        });
        //ASSERT
        assertTrue(thrown.getMessage().contains("Montant cannot be negative"));
    }

    @Test
    @DisplayName("Should be 20 when adding 10 with 10")
    void addTwoMontant() {
        // Arrange
        var montant1 = new Montant(10, "EUR");
        var montant2 = new Montant(10, "EUR");
        //ACT
        var result = montant1.add(montant2);
        //ASSERT
        assertEquals(20, result.montant());
        assertEquals("EUR", result.devise());
    }

    @Test
    @DisplayName("Cannot add two montant with different devise")
    void addTwoMontantWithDifferentDevise() {
        // Arrange
        var montant1 = new Montant(10, "EUR");
        var montant2 = new Montant(10, "BTC");
        //ACT

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            montant1.add(montant2);
        });
        //ASSERT
        assertTrue(thrown.getMessage().contains("Cannot operate with two Montant with different devise"));
    }

    @Test
    @DisplayName("Should be 0 when substracting 10 with 10")
    void subtract() {
        // Arrange
        var montant1 = new Montant(10, "EUR");
        var montant2 = new Montant(10, "EUR");
        //ACT
        var result = montant1.subtract(montant2);
        //ASSERT
        assertEquals(0, result.montant());
        assertEquals("EUR", result.devise());
    }

    @Test
    @DisplayName("Cannot substract two montant with different devise")
    void subtractWithDifferentDevise() {
        // Arrange
        var montant1 = new Montant(10, "EUR");
        var montant2 = new Montant(10, "ETH");
        // ACT
        // ASSERT
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            montant1.subtract(montant2);
        });
    }

    @Test
    @DisplayName("Cannot be negative after substracting")
    void subtractWithBiggerValue() {
        // Arrange
        var montant1 = new Montant(10, "EUR");
        var montant2 = new Montant(100, "EUR");
        // ACT
        // ASSERT
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            montant1.subtract(montant2);
        });
    }

    @Test
    @DisplayName("Should be 12 when multiplying 10 with 1.2")
    void multiply() {
        // Arrange
        var montant1 = new Montant(10, "EUR");
        //ACT
        var result = montant1.multiply(1.2);
        //ASSERT
        assertEquals(12, result.montant());
        assertEquals("EUR", result.devise());
    }

    @Test
    @DisplayName("Should be 5 when dividing 10 by 2")
    void divide() {
        // Arrange
        var montant1 = new Montant(10, "EUR");
        //ACT
        var result = montant1.divide(2);
        //ASSERT
        assertEquals(5, result.montant());
        assertEquals("EUR", result.devise());
    }

    @Test
    @DisplayName("Cannot be divided by zero")
    void divideByZero() {
        // Arrange
        var montant1 = new Montant(10, "EUR");
        //ACT
        //ASSERT
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            montant1.divide(0);
        });
    }

    @Test
    @DisplayName("Should be 5 when dividing 10 by 2 with another Montant")
    void divideByAnotherMontant() {
        // Arrange
        var montant1 = new Montant(10, "EUR");
        var montant2 = new Montant(2, "EUR");
        //ACT
        var result = montant1.divide(montant2);
        //ASSERT
        assertEquals(5, result.montant());
        assertEquals("EUR", result.devise());
    }

    @Test
    @DisplayName("Cannot be divided by another Montant of zero")
    void divideByAnotherMontantOfZero() {
        // Arrange
        var montant1 = new Montant(10, "EUR");
        var montant2 = new Montant(0, "EUR");
        //ACT
        //ASSERT
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            montant1.divide(montant2);
        });
    }
    @Test
    @DisplayName("Cannot be divided by another Montant with different devise")
    void divideByAnotherMontantInDifferentDevise() {
        // Arrange
        var montant1 = new Montant(10, "EUR");
        var montant2 = new Montant(0, "BTC");
        //ACT
        //ASSERT
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            montant1.divide(montant2);
        });
    }
}