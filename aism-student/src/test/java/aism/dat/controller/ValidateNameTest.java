package aism.dat.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class ValidateNameTest {

    @BeforeEach
    void setUp() {
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/validate_name.csv", numLinesToSkip = 1)
    void validateName(String name, boolean expect) {
        PlaceOrderController placeOrderController = new PlaceOrderController();
        assertEquals(expect, placeOrderController.validateName(name));
    }
}