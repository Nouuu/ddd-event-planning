package org.esgi.ddd_event_planning.conference.domain.model;

import org.esgi.ddd_event_planning.conference.domain.model.Montant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MontantTest {
    @Test
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