package com.revature.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class CalculatorTest {

    @Test
    public void subtract() {

        // Arrange
        Calculator calculator = new Calculator();
        int x1 = 5;
        int x2 = 3;
        int expected = x1 - x2;

        // Act
        int actual = calculator.subtract(x1, x2);

        // Assert
        Assertions.assertEquals(expected, actual);

    }

    // methodSource: gives you a dataset from a method
    @ParameterizedTest
    @MethodSource("subtractionParameters") // target method is determined by string value
    public void subtractMethodSouce(int x1, int x2, int expected) {

        // Arrange
        Calculator calculator = new Calculator();

        // Act
        int actual = calculator.subtract(x1, x2);

        // Assert
        Assertions.assertEquals(expected, actual);

    }

    public static Stream<Arguments> subtractionParameters() {
        // don't for get the toStream method on collections
        // may return array, or stream of values
        return Stream.of(
                Arguments.of(32,1,31), // x1, x2, expected
                Arguments.of(22,2,21), // expected to fail for demonstration: 22 - 2 = 20
                Arguments.of(44,4,40)
        );
    }

    // csvSource: gives you a dataset from a csv file source
    @ParameterizedTest
    @CsvSource({
            "22,2,20",
            "30,3,32",
            "44,4,40"
    }) // this replaces the subtractionParameters() from before
    // second set will fail 30 - 3 = 27
    public void subtractCSV(int x1, int x2, int expected) {

        // Arrange
        Calculator calculator = new Calculator();

        // Act
        int actual = calculator.subtract(x1, x2);

        // Assert
        Assertions.assertEquals(expected, actual);


    }

    // csvFileSource: gives you a dataset from a csv file
    @ParameterizedTest
    @CsvFileSource(resources="/subtract-data.csv") // we need to give a file path, from resources /* you may have to create, you can mark as test resources root in IntelliJ  under mark directory as*/
    public void subtractCSVFile(int x1, int x2, int expected) {

        // Arrange
        Calculator calculator = new Calculator();

        // Act
        int actual = calculator.subtract(x1, x2);

        // Assert
        Assertions.assertEquals(expected, actual);

    }
    // you may also specify the resources parameter as an array {"/location1.csv", "/location2.csv"}
}
