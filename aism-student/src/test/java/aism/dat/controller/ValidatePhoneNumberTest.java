package aism.dat.controller;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class ValidatePhoneNumberTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/validate_phone.csv", numLinesToSkip = 1)
    void validatePhoneNumber(String phone, boolean expect) {
        PlaceOrderController placeOrderController = new PlaceOrderController();
        assertEquals(placeOrderController.validatePhoneNumber(phone), expect);
    }
}