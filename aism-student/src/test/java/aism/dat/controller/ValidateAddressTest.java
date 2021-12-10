package aism.dat.controller;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.junit.jupiter.api.Assertions.*;

class ValidateAddressTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/validate_address.csv", numLinesToSkip = 1)
    void validateAddress(String address, boolean expect) {
        PlaceOrderController placeOrderController = new PlaceOrderController();
        assertEquals(expect, placeOrderController.validateAddress(address));
    }
}